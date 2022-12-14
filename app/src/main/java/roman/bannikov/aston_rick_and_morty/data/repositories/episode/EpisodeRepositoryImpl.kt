package roman.bannikov.aston_rick_and_morty.data.repositories.episode

import android.util.Log
import androidx.paging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import roman.bannikov.aston_rick_and_morty.data.api.episode.EpisodeApi
import roman.bannikov.aston_rick_and_morty.data.api.episode.EpisodeDetailsApi
import roman.bannikov.aston_rick_and_morty.data.mapper.EpisodeDataToEpisodeDomain
import roman.bannikov.aston_rick_and_morty.data.models.episode.EpisodeData
import roman.bannikov.aston_rick_and_morty.data.paging.EpisodeRemoteMediator
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeRepository
import java.io.IOException


@ExperimentalPagingApi
class EpisodeRepositoryImpl(
    private val episodeApi: EpisodeApi,
    private val episodeDetailsApi: EpisodeDetailsApi,
    private val database: AppDatabase
) : EpisodeRepository {

    override fun getAllEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeDomain>> {

        val pagingSourceFactory =
            {
                database.getEpisodeDao().getFilteredEpisodes(
                    name = name,
                    episode = episode
                )
            }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true,
            ),
            remoteMediator = EpisodeRemoteMediator(
                episodeApi = episodeApi,
                database = database,
                name = name,
                episode = episode
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it ->
                EpisodeDataToEpisodeDomain().transform(it)
            }
        }
    }

    override suspend fun getAllEpisodesByIds(ids: List<Int>): List<EpisodeDomain> =

        withContext(Dispatchers.IO) {
            try {
                if (ids.size > 1) {
                    val idsString: String = ids.joinToString(separator = ",")
                    val episodesFromApi: Response<List<EpisodeData>> =
                        episodeApi.getEpisodesByIds(ids = idsString)
                    if (episodesFromApi.isSuccessful) {
                        episodesFromApi.body()
                            ?.let { database.getEpisodeDao().insertAllEpisodes(episodeData = it) }
                    }
                }
                if (ids.size == 1) {
                    val episodeDataFromApi: Response<EpisodeData> =
                        episodeDetailsApi.getEpisodeById(id = ids[0])
                    if (episodeDataFromApi.isSuccessful) {
                        episodeDataFromApi.body()
                            ?.let { database.getEpisodeDao().insertEpisode(episodeData = it) }
                    }
                }

            } catch (e: HttpException) {
                Log.e("Log", "${e.code()}")
            } catch (e: IOException) {
                Log.e("Log", "${e.message}")
            }

            return@withContext database.getEpisodeDao().getEpisodesByIds(ids = ids).map {
                EpisodeDataToEpisodeDomain().transform(it)
            }
        }
}
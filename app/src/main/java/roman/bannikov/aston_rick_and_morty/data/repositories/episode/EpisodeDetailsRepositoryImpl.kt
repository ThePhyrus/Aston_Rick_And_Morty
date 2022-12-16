package roman.bannikov.aston_rick_and_morty.data.repositories.episode

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import roman.bannikov.aston_rick_and_morty.data.mapper.EpisodeDataToEpisodeDomain
import roman.bannikov.aston_rick_and_morty.data.models.episode.EpisodeData
import roman.bannikov.aston_rick_and_morty.data.api.episode.EpisodeDetailsApi
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeDetailsRepository
import java.io.IOException


class EpisodeDetailsRepositoryImpl(
    private val episodeDetailsApi: EpisodeDetailsApi,
    private val db: AppDatabase
) : EpisodeDetailsRepository {

    override suspend fun getEpisodeById(id: Int): EpisodeDomain = withContext(Dispatchers.IO) {
        try {
            val episodeDataFromApi: Response<EpisodeData> =
                episodeDetailsApi.getEpisodeById(id = id)
            if (episodeDataFromApi.isSuccessful) {
                episodeDataFromApi.body()
                    ?.let { db.getEpisodeDao().insertEpisode(episodeData = it) }
            }

        } catch (e: HttpException) {
            Log.e("Log", "${e.code()}")
        } catch (e: IOException) {
            Log.e("Log", "${e.message}")
        }

        return@withContext EpisodeDataToEpisodeDomain().transform(
            db.getEpisodeDao().getEpisodeById(id = id)
        )
    }
}
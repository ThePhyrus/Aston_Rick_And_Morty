package roman.bannikov.aston_rick_and_morty.data.repositories.episodes_repositories

import android.util.Log
import roman.bannikov.aston_rick_and_morty.data.mapper.entity_to_domain_model.EpisodeEntityToDomainModel
import roman.bannikov.aston_rick_and_morty.data.models.episodes.Episode
import roman.bannikov.aston_rick_and_morty.data.remote.api.episodes.EpisodeDetailsApi
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeModel
import roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories.EpisodeDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class EpisodeDetailsRepositoryImpl(
    private val episodeDetailsApi: EpisodeDetailsApi,
    private val db: RickAndMortyDatabase
) : EpisodeDetailsRepository {

    override suspend fun getEpisodeById(id: Int): EpisodeModel = withContext(Dispatchers.IO) {
        try {
            val episodeFromApi: Response<Episode> =
                episodeDetailsApi.getEpisodeById(id = id)
            if (episodeFromApi.isSuccessful) {
                episodeFromApi.body()
                    ?.let { db.getEpisodeDao().insertEpisode(episode = it) }
            }

        } catch (e: HttpException) {
            Log.e("Log", "${e.code()}")
        } catch (e: IOException) {
            Log.e("Log", "${e.message}")
        }

        return@withContext EpisodeEntityToDomainModel().transform(
            db.getEpisodeDao().getEpisodeById(id = id)
        )
    }
}
package mazer.arthur.gitrepositoryapp.data

import mazer.arthur.gitrepositoryapp.domain.models.Repository
import retrofit2.http.GET

interface ApiNetwork {

    @GET("repositories")
    suspend fun fetchRepositories(): ArrayList<Repository>
}

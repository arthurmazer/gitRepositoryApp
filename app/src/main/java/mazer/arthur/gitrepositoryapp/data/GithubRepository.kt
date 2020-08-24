package mazer.arthur.gitrepositoryapp.data

class GithubRepository(private val apiHelper: ApiHelper) {

    suspend fun getRepositories() = apiHelper.getRepositories()
}
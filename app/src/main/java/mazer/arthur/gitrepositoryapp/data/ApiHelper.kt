package mazer.arthur.gitrepositoryapp.data

class ApiHelper(private val api: ApiNetwork) {

    suspend fun getRepositories() = api.fetchRepositories()
}
package mazer.arthur.gitrepositoryapp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mazer.arthur.gitrepositoryapp.data.GithubRepository
import mazer.arthur.gitrepositoryapp.domain.models.Repository

class MainViewModel(private val gitRepository: GithubRepository): ViewModel()  {

    var repositoriesLiveData = MutableLiveData<ResponseEvents>()

    sealed class ResponseEvents {
        object Loading: ResponseEvents()
        class Success(val repoList: ArrayList<Repository>): ResponseEvents()
        object Error: ResponseEvents()
    }

    fun getRepositories(){
        repositoriesLiveData.postValue(ResponseEvents.Loading)
        try{
            GlobalScope.launch {
                val response = gitRepository.getRepositories()
                repositoriesLiveData.postValue(ResponseEvents.Success(response))
            }
        } catch (ex: Exception){
            repositoriesLiveData.postValue(ResponseEvents.Error)
        }
    }
}
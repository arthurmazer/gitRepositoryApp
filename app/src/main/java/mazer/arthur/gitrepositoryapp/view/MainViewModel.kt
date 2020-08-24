package mazer.arthur.gitrepositoryapp.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import mazer.arthur.gitrepositoryapp.data.GithubRepository
import mazer.arthur.gitrepositoryapp.data.Response

class MainViewModel(private val productsRepository: GithubRepository): ViewModel()  {

    fun getRepositories() = liveData(Dispatchers.IO){
        emit(Response.loading())
        try{
            emit(Response.success(productsRepository.getRepositories()))
        } catch (ex: Exception){
            emit(Response.error(null, "Error fetching repositories list"))
        }
    }

    fun openRepositoryLink(context: Context, url: String){

    }
}
package mazer.arthur.gitrepositoryapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mazer.arthur.gitrepositoryapp.data.ApiHelper
import mazer.arthur.gitrepositoryapp.data.GithubRepository
import mazer.arthur.gitrepositoryapp.view.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                GithubRepository(
                    apiHelper
                )
            ) as T
        }
        throw IllegalArgumentException("Class name not found")
    }
}
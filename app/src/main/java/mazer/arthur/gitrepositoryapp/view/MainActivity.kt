package mazer.arthur.gitrepositoryapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mazer.arthur.gitrepositoryapp.R
import mazer.arthur.gitrepositoryapp.data.ApiHelper
import mazer.arthur.gitrepositoryapp.data.RetrofitHelper
import mazer.arthur.gitrepositoryapp.domain.models.Status
import mazer.arthur.gitrepositoryapp.utils.ViewModelFactory
import mazer.arthur.gitrepositoryapp.utils.listeners.OnRepoClicked
import mazer.arthur.gitrepositoryapp.view.adapters.RepoAdapter

class MainActivity : AppCompatActivity(), OnRepoClicked {

    private lateinit var viewModel: MainViewModel

    private var repoAdapter = RepoAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        registerObservers()
        setupView()
    }

    private fun setupView() {
        setupRepositoriesRecyclerView()
    }

    private fun registerObservers(){
        viewModel.getRepositories().observe(this, Observer {
            it.let{ response ->
                when (response.status){
                    Status.SUCCESS -> {
                        showLoading(false)
                        response.data.let { repoList ->
                            repoAdapter.repoList = repoList ?: return@Observer
                        }
                    }
                    Status.LOADING -> {
                        showLoading(true)
                    }
                    Status.ERROR -> {
                        showErrorMessage(R.string.error_fetching_repo)
                    }
                }


            }
        })
    }

    private fun setupRepositoriesRecyclerView() {
        rvRepositories.layoutManager = LinearLayoutManager(this)
        rvRepositories.itemAnimator = DefaultItemAnimator()
        rvRepositories.adapter = repoAdapter
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this,
            ViewModelFactory(
                ApiHelper(
                    RetrofitHelper.api
                )
            )
        ).get(MainViewModel::class.java)
    }

    fun showLoading(show: Boolean){
        if (show){
            progressBar.visibility = View.VISIBLE
            tvLoadingText.visibility = View.VISIBLE
            rvRepositories.visibility = View.GONE
        }else{
            progressBar.visibility = View.GONE
            tvLoadingText.visibility = View.GONE
            rvRepositories.visibility = View.VISIBLE
        }
    }

    @SuppressLint("ShowToast")
    fun showErrorMessage(id: Int){
        Toast.makeText(this, id, Toast.LENGTH_SHORT)
    }

    override fun onRepoClicked(url: String) {
        try {
            val viewIntent = Intent(
                "android.intent.action.VIEW",
                Uri.parse(url)
            )
            startActivity(viewIntent)
        }catch (ex: Exception){
            showErrorMessage(R.string.error_opening_repo)
        }
    }

}
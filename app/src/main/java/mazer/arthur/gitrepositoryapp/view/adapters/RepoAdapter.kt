package mazer.arthur.gitrepositoryapp.view.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mazer.arthur.gitrepositoryapp.R
import mazer.arthur.gitrepositoryapp.domain.models.Repository
import mazer.arthur.gitrepositoryapp.utils.extensions.inflate
import mazer.arthur.gitrepositoryapp.utils.listeners.OnRepoClicked

class RepoAdapter(val listener: OnRepoClicked): RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    var repoList: ArrayList<Repository> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapter.ViewHolder {
        return ViewHolder(inflate(R.layout.item_repository,parent))
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoAdapter.ViewHolder, position: Int) {
        val repository = repoList[position]
        holder.title.text = repository.name
        holder.author.text = repository.owner.login
        Picasso.get().load(repository.owner.avatar_url).into(holder.userPhoto)

        holder.itemView.setOnClickListener {
            listener.onRepoClicked(repository.html_url)
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.tvRepoName)
        var author: TextView = view.findViewById(R.id.tvAuthor)
        var userPhoto: ImageView = view.findViewById(R.id.ivUserPhoto)
    }

}
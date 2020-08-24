package mazer.arthur.gitrepositoryapp.domain.models

data class Repository (
    val id : Int,
    val node_id : String,
    val name : String,
    val full_name : String,
    val private : Boolean,
    val owner : Owner,
    val html_url : String,
    val description : String,
    val fork : Boolean,
    val url : String
)
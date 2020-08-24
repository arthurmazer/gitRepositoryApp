package mazer.arthur.gitrepositoryapp.domain.models

data class Owner (
	val login : String,
	val id : Int,
	val node_id : String,
	val avatar_url : String,
	val gravatar_id : String,
	val url : String,
	val html_url : String
)
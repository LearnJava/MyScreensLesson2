package ru.konstantin.myscreenslesson2.model

class GithubUsersRepo {

    private val repositories = (1..99).map { GithubUser("login $it") }

    fun getUsers(): List<GithubUser> = repositories
}
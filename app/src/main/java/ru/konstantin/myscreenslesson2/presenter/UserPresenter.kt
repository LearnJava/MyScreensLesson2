package ru.konstantin.myscreenslesson2.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.konstantin.myscreenslesson2.model.GithubUser

class UserPresenter(val user: GithubUser, private val router: Router) : MvpPresenter<IUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.backTo(null)
        return true
    }
}
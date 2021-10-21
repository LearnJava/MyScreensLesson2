package ru.konstantin.myscreenslesson2.presenter

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.konstantin.myscreenslesson2.model.GithubUser
import ru.konstantin.myscreenslesson2.ui.UserFragment
import ru.konstantin.myscreenslesson2.ui.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user) }
}
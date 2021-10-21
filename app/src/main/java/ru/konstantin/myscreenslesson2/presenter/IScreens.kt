package ru.konstantin.myscreenslesson2.presenter

import com.github.terrakok.cicerone.Screen
import ru.konstantin.myscreenslesson2.model.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
}
package ru.konstantin.myscreenslesson2.ui

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.konstantin.myscreenslesson2.App
import ru.konstantin.myscreenslesson2.R
import ru.konstantin.myscreenslesson2.databinding.ActivityMainBinding
import ru.konstantin.myscreenslesson2.presenter.AndroidScreens
import ru.konstantin.myscreenslesson2.presenter.IBackButtonListener
import ru.konstantin.myscreenslesson2.presenter.IMainView
import ru.konstantin.myscreenslesson2.presenter.ScreenPresenter

class MainActivity : MvpAppCompatActivity(), IMainView {


    private val navigation = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        ScreenPresenter(
            App.instance.router,
            AndroidScreens()
        )
    }

    private val vb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigation)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}
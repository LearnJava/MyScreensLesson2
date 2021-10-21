package ru.konstantin.myscreenslesson2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.konstantin.myscreenslesson2.App
import ru.konstantin.myscreenslesson2.databinding.FragmentUserBinding
import ru.konstantin.myscreenslesson2.model.GithubUser
import ru.konstantin.myscreenslesson2.presenter.IBackButtonListener
import ru.konstantin.myscreenslesson2.presenter.IUserView
import ru.konstantin.myscreenslesson2.presenter.UserPresenter

class UserFragment : MvpAppCompatFragment(), IUserView, IBackButtonListener {

    private val presenter by moxyPresenter {
        UserPresenter(requireNotNull(arguments?.getParcelable(KEY_ARG_USER)), App.instance.router)
    }

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.run {
            userLogin.text = presenter.user.login
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    companion object {

        private const val KEY_ARG_USER = "KEY_ARG_USER"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            Bundle().apply {
                putParcelable(KEY_ARG_USER, user)
            }.also {
                arguments = it
            }
        }
    }
}
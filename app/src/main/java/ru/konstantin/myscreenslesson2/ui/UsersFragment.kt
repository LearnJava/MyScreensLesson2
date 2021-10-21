package ru.konstantin.myscreenslesson2.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.konstantin.myscreenslesson2.App
import ru.konstantin.myscreenslesson2.databinding.FragmentUsersBinding
import ru.konstantin.myscreenslesson2.model.GithubUsersRepo
import ru.konstantin.myscreenslesson2.presenter.AndroidScreens
import ru.konstantin.myscreenslesson2.presenter.IBackButtonListener
import ru.konstantin.myscreenslesson2.presenter.UsersPresenter
import ru.konstantin.myscreenslesson2.presenter.IUsersView

class UsersFragment : MvpAppCompatFragment(), IUsersView, IBackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!


    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router, AndroidScreens())
    }
    private val adapter: UsersRVAdapter by lazy {
        UsersRVAdapter(presenter.usersListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUsersBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(context)
            rvUsers.adapter = adapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}
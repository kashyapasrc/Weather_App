package com.kashyap.weather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kashyap.weather.R
import com.kashyap.weather.domain.models.BookMarkModel
import com.kashyap.weather.ui.base.BaseFragment
import com.kashyap.weather.ui.MainActivity
import com.kashyap.weather.ui.home.adapters.BookMarkAdapter
import com.kashyap.weather.utils.initViewModel
import com.mindorks.example.coroutines.utils.Status

class HomeFragment : BaseFragment(), View.OnClickListener, IBookmarksClickListener {

    private lateinit var adapter: BookMarkAdapter
    private lateinit var viewModel: BookMarkViewModel
    private lateinit var rootView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list: ArrayList<BookMarkModel>? = null

    companion object {
        private val TAG = HomeFragment::class.java.simpleName
        public fun getInstance(): HomeFragment {
            val fragment = HomeFragment();
            return fragment;
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        initViews()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setupObserver()
    }

    private fun setUpViewModel() {

        viewModel = initViewModel()


    }

    fun setNewList(list: ArrayList<BookMarkModel>) {
        adapter.setNewList(list)
    }

    fun initViews() {
        fab = rootView.findViewById<FloatingActionButton>(R.id.fab)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.list_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BookMarkAdapter(list = ArrayList<BookMarkModel>(), this);
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        fab.setOnClickListener(this)

    }


    private fun setupObserver() {
        viewModel.getBookMarks().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { bookMarkList -> setNewList(bookMarkList) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    dispalyErrorMsg(it.message)
                }
            }
        })
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.fab -> if (activity is MainActivity) {
                    (activity as MainActivity).setGoogleMap();
                }
            }
        }
    }

    override fun onDeleteClick(bookMarkModel: BookMarkModel) {
        viewModel.deleteBookMarks(bookMarkModel)
    }

    override fun onItemSelected(bookMarkModel: BookMarkModel) {
        if (activity is MainActivity) {
            (activity as MainActivity).setCityScreen(bookMarkModel)
        }

    }
}
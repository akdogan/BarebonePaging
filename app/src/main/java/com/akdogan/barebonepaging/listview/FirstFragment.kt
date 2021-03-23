package com.akdogan.barebonepaging.listview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.akdogan.barebonepaging.R
import com.akdogan.barebonepaging.repository.MyRepository
import com.akdogan.barebonepaging.repository.fakenetwork.FakeNetworkService
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val PAGING_TRACING_TAG = "PAGING_TRACING_INITIAL_LOAD"

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    val viewModel: ListViewModel by viewModels {
        ListViewModelFactory(MyRepository(FakeNetworkService))
    }
    val adapter = MyListViewAdapter()
    lateinit var recView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //adapter.dataset = getRandomCharList(50)
        recView = view.findViewById<RecyclerView>(R.id.my_recyclerview)
        recView.adapter = adapter
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recView.addItemDecoration(decoration)

        search()


        //initDataRequest()



    }

    fun initDataRequest(){
        viewLifecycleOwner.lifecycleScope.launch{
            adapter.loadStateFlow.collectLatest {
                recView.scrollToPosition(0)
            }
        }
    }

    // Call the repository
    fun search(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchRepo().collectLatest { pagingData ->
                adapter.submitData(pagingData)

            }
        }
    }
}
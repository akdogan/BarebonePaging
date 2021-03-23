package com.akdogan.barebonepaging.listview

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.akdogan.barebonepaging.repository.FantasyCharacter
import com.akdogan.barebonepaging.repository.LOAD_PAGE_SIZE
import com.akdogan.barebonepaging.repository.MyPagingSource
import com.akdogan.barebonepaging.repository.MyRepository
import com.akdogan.barebonepaging.repository.fakenetwork.FakeNetworkService
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException

class ListViewModelFactory(private val repo: MyRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}

class ListViewModel(private val repo: MyRepository) : ViewModel(){

    // Alternatively, declare a flow val that gets is data from (ideally) the repository
    val dataFlow = Pager(
        PagingConfig(pageSize = LOAD_PAGE_SIZE)
    ) {
        MyPagingSource(FakeNetworkService)
    }.flow
        .cachedIn(viewModelScope)


    // Call the repository to return a flow
    fun searchRepo(): Flow<PagingData<FantasyCharacter>> {
        Log.i(PAGING_TRACING_TAG, "ViewModel SearchRepo Called")
        val result : Flow<PagingData<FantasyCharacter>> = repo.getSearchResultStream()
            .cachedIn(viewModelScope)

        return result
    }

}
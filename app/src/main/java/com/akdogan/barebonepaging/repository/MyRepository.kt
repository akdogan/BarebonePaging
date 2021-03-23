package com.akdogan.barebonepaging.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.akdogan.barebonepaging.listview.PAGING_TRACING_TAG
import com.akdogan.barebonepaging.repository.fakenetwork.NetworkService
import kotlinx.coroutines.flow.Flow

class MyRepository(private val service: NetworkService) {

    fun getSearchResultStream(): Flow<PagingData<FantasyCharacter>> {
        Log.i(PAGING_TRACING_TAG, "Repo getSearchResultStream Called")
        return Pager(
            config = PagingConfig(
                pageSize = LOAD_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {MyPagingSource(service)}
        ).flow
    }

}
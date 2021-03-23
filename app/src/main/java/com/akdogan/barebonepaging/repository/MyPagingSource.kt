package com.akdogan.barebonepaging.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.akdogan.barebonepaging.listview.PAGING_TRACING_TAG
import com.akdogan.barebonepaging.repository.fakenetwork.NetworkService

class MyPagingSource (
    private val service: NetworkService,
): PagingSource<Int, FantasyCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, FantasyCharacter>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FantasyCharacter> {
        val position = params.key ?: 1
        Log.i(PAGING_TRACING_TAG, "PagingSource load Called with params.key = ${params.key} and position = $position")
        return try {
            val response = service.getItems(position, params.loadSize)

            val nextKey = if (response.isEmpty()){
                null
            } else {
                position + (params.loadSize / LOAD_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}

const val LOAD_PAGE_SIZE = 50
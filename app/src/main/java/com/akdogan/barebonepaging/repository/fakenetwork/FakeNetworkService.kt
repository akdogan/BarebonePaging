package com.akdogan.barebonepaging.repository.fakenetwork

import android.util.Log
import com.akdogan.barebonepaging.listview.PAGING_TRACING_TAG
import com.akdogan.barebonepaging.repository.FantasyCharacter
import com.akdogan.barebonepaging.util.getRandomCharList


interface NetworkService {

    suspend fun getItems(
        page: Int,
        itemsPerPage: Int
    ) : List<FantasyCharacter>
}

object FakeNetworkService : NetworkService {

    val fakeServerData = getRandomCharList(500, true)

    override suspend fun getItems(page: Int, itemsPerPage: Int): List<FantasyCharacter> {

        //delay(3000)
        val pageWithOffset = page - 1
        val startIndex = itemsPerPage * pageWithOffset
        val stopIndex = itemsPerPage * (pageWithOffset + 1)
        val resultList = fakeServerData.subList(startIndex, stopIndex)
        Log.i(PAGING_TRACING_TAG, "FakeNetwork Service getItems Called with number of items: ${resultList.size} and start / stop: $startIndex / $stopIndex")
        return resultList
    }

}
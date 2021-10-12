package com.ps.psdemo.data.repository

import com.ps.psdemo.data.PostData
import com.ps.psdemo.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getPost(): Flow<List<PostData>> = flow {
        emit(apiServiceImpl.getPost())
    }.flowOn(Dispatchers.IO)
}
package com.ps.psdemo.network

import com.ps.psdemo.data.PostData
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getPost(): List<PostData> = apiService.getPostData()
}
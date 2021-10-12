package com.ps.psdemo.network

import com.ps.psdemo.data.PostData
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPostData(): List<PostData>
}
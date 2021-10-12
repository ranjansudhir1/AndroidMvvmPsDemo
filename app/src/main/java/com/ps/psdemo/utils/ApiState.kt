package com.ps.psdemo.utils

import com.ps.psdemo.data.PostData

sealed class ApiState {
    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data: List<PostData>) : ApiState()
    object Empty : ApiState()
}

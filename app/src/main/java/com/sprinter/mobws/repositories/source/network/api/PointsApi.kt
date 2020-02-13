package com.sprinter.mobws.repositories.source.network.api

import com.sprinter.mobws.repositories.models.Point
import com.sprinter.mobws.repositories.models.PointsRequest
import com.sprinter.mobws.repositories.models.UglyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PointsApi {

    @POST("mobws/json/pointsList")
    suspend fun getPoints(
        @Query("count") count: Int,
        @Query("version") version: String = "1.1",
        @Body body: PointsRequest = PointsRequest(count, version)
    ): Response<UglyResponse<List<Point>>>
}

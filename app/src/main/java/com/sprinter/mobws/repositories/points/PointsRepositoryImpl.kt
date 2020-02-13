package com.sprinter.mobws.repositories.points

import com.sprinter.mobws.extensions.checkInternalError
import com.sprinter.mobws.extensions.checkResponse
import com.sprinter.mobws.repositories.Result
import com.sprinter.mobws.repositories.models.Point
import com.sprinter.mobws.repositories.source.network.api.PointsApi
import com.sprinter.mobws.utils.ExceptionHandler
import java.net.HttpURLConnection

class PointsRepositoryImpl(
    private val pointsApi: PointsApi,
    private val exceptionHandler: ExceptionHandler
) : PointsRepository {

    override suspend fun getPoints(count: Int): Result<List<Point>> {
        return try {
            Result(
                pointsApi
                    .getPoints(count)
                    .checkResponse(HttpURLConnection.HTTP_OK)
                    .checkInternalError()
                    .response
            )

        } catch (e: Throwable) {
            exceptionHandler.sendException(e)
            Result(emptyList(), e)
        }
    }
}

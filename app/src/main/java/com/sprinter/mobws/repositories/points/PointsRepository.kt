package com.sprinter.mobws.repositories.points

import com.sprinter.mobws.repositories.Result
import com.sprinter.mobws.repositories.models.Point

interface PointsRepository {

    suspend fun getPoints(count: Int): Result<List<Point>>
}

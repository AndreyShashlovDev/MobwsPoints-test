package com.sprinter.mobws.di.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.sprinter.mobws.repositories.models.UglyResponse
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class UglyResponseDeserializer : JsonDeserializer<UglyResponse<*>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): UglyResponse<*> {

        val rawResponse = json?.asJsonObject?.get("response")
        val rawPoints = rawResponse?.asJsonObject?.get("points")
        val message = String(
            android.util.Base64.decode(
                (rawResponse?.asJsonObject?.get("message")?.asString ?: ""),
                android.util.Base64.DEFAULT
            )
        ) + rawResponse?.asJsonObject?.get("message")?.asString

        val actualErrorCode = getActualErrorCode(json)

        return try {
            val parameterizedType = typeOfT as ParameterizedType
            val resultList = if (rawResponse != null &&
                parameterizedType.actualTypeArguments.size >= 0 &&
                actualErrorCode >= 0
            ) {
                val itemType = parameterizedType.actualTypeArguments[0]
                // here we should have Strategy pattern for deserialize any types
                val list = if (rawPoints == null) listOf<Any>() else context.deserialize<Any>(
                    rawPoints,
                    itemType
                )

                list
            } else {
                emptyList<Any>()
            }

            UglyResponse(actualErrorCode, resultList, message)

        } catch (e: ClassCastException) {
            Timber.e(e)
            return UglyResponse(actualErrorCode, emptyList<Any>(), e.localizedMessage)
        }
    }

    private fun getActualErrorCode(json: JsonElement?): Int {
        val rootResult = json?.asJsonObject?.get("result")?.asInt ?: 0
        val rawResponse = json?.asJsonObject?.get("response")
        val secondResult = rawResponse?.asJsonObject?.get("result")?.asInt ?: 0

        return if (rootResult != 0 && secondResult == 0) rootResult else secondResult
    }
}
package com.example.voicenotes

import RocketLaunch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Greeting {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    @Throws(Exception::class)
    suspend fun greeting(): String {
        val rockets: List<RocketLaunch> =
            httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }

        return "Hello, ${Platform().platform}!" +
            "\nThere are only ${daysUntilNewYear()} left until New Year! 🎅🏼 " +
            "\nThe last successful launch was ${lastSuccessLaunch.launchDateUTC} 🚀"
    }
}

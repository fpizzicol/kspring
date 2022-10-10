package tech.talk.kspring

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.koin.core.context.startKoin
import org.slf4j.event.*
import tech.talk.kspring.di.appModule
import tech.talk.kspring.router.configureRouting

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    startKoin {
        modules(appModule)
    }

    install(CallLogging) {
        level = Level.INFO
        filter { call ->
            call.request.path().startsWith("/api/v1")
        }
        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            "Status: $status, HTTP method: $httpMethod, User agent: $userAgent"
        }
    }

    embeddedServer(Netty, port = 4567, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
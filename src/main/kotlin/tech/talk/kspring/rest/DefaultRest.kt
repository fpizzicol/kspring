package tech.talk.kspring.rest

import kotlinx.serialization.Serializable
import org.slf4j.LoggerFactory

class DefaultRest {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun getData(): DefaultDataResponse {
        logger.info("Default logger call")
        return DefaultDataResponse()
    }
}

@Serializable
data class DefaultDataResponse(
    var name: String = "unknown",
    var group: String = "unknown",
    var version: String = "unknown",
    var timestamp: String = "unknown"
) {
//    constructor(environment: Environment) : this(
//        environment.getProperty("build.name", "unknown"),
//        environment.getProperty("build.group", "unknown"),
//        environment.getProperty("build.version", "unknown"),
//        environment.getProperty("build.timestamp", "unknown")
//    )
}

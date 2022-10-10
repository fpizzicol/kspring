package tech.talk.kspring.rest

import kotlinx.serialization.Serializable
import org.slf4j.LoggerFactory
import java.util.Properties

private const val CONFIG = "data.properties"

class DefaultRest {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    fun getData(): DefaultDataResponse {
        logger.info("Default logger call")
        val properties = Properties()
        val file = this::class.java.classLoader.getResourceAsStream(CONFIG)
        properties.load(file)

        return DefaultDataResponse(properties)
    }
}

@Serializable
data class DefaultDataResponse(
    var writtenBy: String = "unknown",
    var name: String = "unknown",
    var group: String = "unknown",
    var version: String = "unknown",
    var timestamp: String = "unknown",
) {
    constructor(properties: Properties) : this(
        name = properties.getProperty("build.name", "unknown"),
        group = properties.getProperty("build.group", "unknown"),
        version = properties.getProperty("build.version", "unknown"),
        timestamp = properties.getProperty("build.timestamp", "unknown"),
        writtenBy = properties.getProperty("written.by", "unknown"),
    )
}

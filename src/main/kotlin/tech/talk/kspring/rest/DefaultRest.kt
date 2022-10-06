package tech.talk.kspring.rest

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/", produces = [MediaType.APPLICATION_JSON_VALUE])
class DefaultRest {

    @Autowired
    private lateinit var logger: Logger

    @Autowired
    private lateinit var environment: Environment

    @GetMapping("/")
    fun getDefault(): DefaultDataResponse {
        logger.info("Request received")

        return DefaultDataResponse(environment)
    }
}

data class DefaultDataResponse(
    var name: String = "unknown",
    var group: String = "unknown",
    var version: String = "unknown",
    var timestamp: String = "unknown"
) {
    constructor(environment: Environment) : this(
        environment.getProperty("build.name", "unknown"),
        environment.getProperty("build.group", "unknown"),
        environment.getProperty("build.version", "unknown"),
        environment.getProperty("build.timestamp", "unknown")
    )
}

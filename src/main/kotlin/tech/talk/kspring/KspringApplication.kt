package tech.talk.kspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KspringApplication

fun main(args: Array<String>) {
	runApplication<KspringApplication>(*args)
}

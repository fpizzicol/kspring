package tech.talk.kspring.configuration

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class LogConfiguration {

    @Bean
    @Scope("prototype")
    fun produceLogger(injectionPoint: InjectionPoint) = LoggerFactory.getLogger(injectionPoint.member.declaringClass)


}
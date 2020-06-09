package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.scheduling.annotation.EnableAsync

@EnableMongoRepositories
@EnableAsync
@SpringBootApplication
class NoncoroutineappApplication

fun main(args: Array<String>) {
	runApplication<NoncoroutineappApplication>(*args)
}

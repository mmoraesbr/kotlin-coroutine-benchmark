package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@SpringBootApplication
@EnableReactiveMongoRepositories
class CoroutineappApplication

fun main(args: Array<String>) {
    runApplication<CoroutineappApplication>(*args)
}

package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NoncoroutineappApplication

fun main(args: Array<String>) {
	runApplication<NoncoroutineappApplication>(*args)
}

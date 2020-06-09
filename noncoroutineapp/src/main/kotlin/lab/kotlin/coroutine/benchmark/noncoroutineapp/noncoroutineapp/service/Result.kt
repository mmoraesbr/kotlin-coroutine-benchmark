package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.service

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Result(
        val resultA: ResultA,
        val resultB: ResultB,
        val resultC: ResultC
) {
    @Id
    var id: String = UUID.randomUUID().toString()
        private set
}
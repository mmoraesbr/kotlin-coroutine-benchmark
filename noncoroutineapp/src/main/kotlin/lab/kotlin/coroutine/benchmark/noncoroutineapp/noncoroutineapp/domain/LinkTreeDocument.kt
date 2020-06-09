package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class LinkTreeDocument(
        url: String) {
    @Id
    var url: String = url
        private set

    var lastVisited: LocalDateTime = LocalDateTime.now()
        private set
}
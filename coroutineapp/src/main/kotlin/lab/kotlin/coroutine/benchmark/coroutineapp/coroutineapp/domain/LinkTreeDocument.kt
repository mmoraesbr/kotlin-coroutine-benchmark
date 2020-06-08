package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class LinkTreeDocument(
        val url: String) {
    @Id
    var id: String = ""
        private set

    var lastVisited: LocalDateTime = LocalDateTime.now()
        private set

    fun visited() {
        this.lastVisited = LocalDateTime.now()
    }
}
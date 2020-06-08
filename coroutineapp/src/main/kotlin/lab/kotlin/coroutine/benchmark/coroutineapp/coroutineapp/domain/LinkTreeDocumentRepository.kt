package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LinkTreeDocumentRepository : ReactiveCrudRepository<LinkTreeDocument, String> {
    suspend fun findByUrl(url: String): LinkTreeDocument?
}
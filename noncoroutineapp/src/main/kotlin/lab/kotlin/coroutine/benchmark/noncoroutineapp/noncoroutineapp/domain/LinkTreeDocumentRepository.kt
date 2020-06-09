package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LinkTreeDocumentRepository : CrudRepository<LinkTreeDocument, String> {
    fun findByUrl(url: String): LinkTreeDocument?
}
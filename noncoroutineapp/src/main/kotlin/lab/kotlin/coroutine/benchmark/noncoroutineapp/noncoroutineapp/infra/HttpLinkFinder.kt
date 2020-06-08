package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.infra

import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain.LinkFinder
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.BasicResponseHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class HttpLinkFinder : LinkFinder {

    private val externalLinkPattern = "\"(http(s)?://.*?)\""

    private val regex = externalLinkPattern.toRegex()

    @Autowired
    private lateinit var httpClient: HttpClient

    private val handler = BasicResponseHandler()

    override fun find(url: String): Set<String> = try {
        val response = httpClient.execute(HttpGet(url))
        val content = handler.handleResponse(response)

        regex.findAll(content).map {
            it.groups[1]?.value ?: ""
        }.toSet()
    } catch (e: Exception) {
        setOf("ERROR")
    }
}
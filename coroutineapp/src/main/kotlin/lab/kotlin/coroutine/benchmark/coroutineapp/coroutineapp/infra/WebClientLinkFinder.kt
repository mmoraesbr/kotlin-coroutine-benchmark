package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.infra

import lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain.LinkFinder
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.netty.http.client.HttpClient

@Component
class WebClientLinkFinder : LinkFinder {

    private val externalLinkPattern = "\"(http(s)?://.*?)\""

    private val regex = externalLinkPattern.toRegex()

    private val webClientBuilder = WebClient.builder().clientConnector(object : ReactorClientHttpConnector(
            HttpClient.create().followRedirect(true)) {
    }).codecs {
        it.defaultCodecs().maxInMemorySize(1024 * 1024 * 10)
    }

    private val webClient = webClientBuilder.build()

    override suspend fun find(url: String): Set<String> = try {
        val content = webClient
                .get().uri(url).retrieve().awaitBody<String>()

        regex.findAll(content).map {
            it.groups[1]?.value ?: ""
        }.toSet()
    } catch (e: Exception) {
        e.printStackTrace()
        setOf("ERROR")
    }
}
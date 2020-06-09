package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.infra

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration


@Configuration
class WebClientConfig {

    @Value("\${web-client.max-connections}")
    private val maxConnections = 0

    @Value("\${web-client.acquire-max-count}")
    private val acquireMaxCount = 0

    @Value("\${web-client.acquire-timeout}")
    private val acquireTimeout = 0L

    @Bean
    fun webClientBuilder(): WebClient.Builder {
        val cp = ConnectionProvider
                .builder("webClient")
                .maxConnections(maxConnections)
                .pendingAcquireMaxCount(acquireMaxCount)
                .pendingAcquireTimeout(Duration.ofSeconds(acquireTimeout)).build()

        val httpClient: HttpClient = HttpClient.create(cp)

        return WebClient.builder()
                .clientConnector(ReactorClientHttpConnector(httpClient))
    }

}
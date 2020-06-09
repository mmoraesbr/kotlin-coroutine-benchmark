package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class ExternalService {

    @Value("\${external.service.url}")
    private lateinit var externalServiceUrl: String

    private val webClient = WebClient.builder().build()

    suspend fun callA() = webClient
            .get()
            .uri(externalServiceUrl)
            .retrieve().awaitBody<ResultA>()

    suspend fun callB() = webClient
            .get()
            .uri(externalServiceUrl)
            .retrieve().awaitBody<ResultB>()

    suspend fun callC() = webClient
            .get()
            .uri(externalServiceUrl)
            .retrieve().awaitBody<ResultC>()

}
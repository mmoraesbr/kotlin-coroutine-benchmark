package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.service

import lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain.ResultA
import lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain.ResultB
import lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain.ResultC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody


@Component
class ExternalService {

    @Value("\${external.service.url}")
    private lateinit var externalServiceUrl: String

    @Autowired
    private lateinit var webClientBuilder: WebClient.Builder

    private val webClient by lazy {
        webClientBuilder.build()
    }

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

    fun callAWithReactor() = webClient
            .get()
            .uri(externalServiceUrl)
            .retrieve().bodyToMono(ResultA::class.java)

    fun callBWithReactor() = webClient
            .get()
            .uri(externalServiceUrl)
            .retrieve().bodyToMono(ResultB::class.java)

    fun callCWithReactor() = webClient
            .get()
            .uri(externalServiceUrl)
            .retrieve().bodyToMono(ResultC::class.java)

}
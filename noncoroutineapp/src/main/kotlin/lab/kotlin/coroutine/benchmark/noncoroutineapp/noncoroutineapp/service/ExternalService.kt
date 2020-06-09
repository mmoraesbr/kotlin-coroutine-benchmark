package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.service

import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain.ResultA
import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain.ResultB
import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain.ResultC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.web.client.AsyncRestTemplate
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

@Component
class ExternalService {

    @Value("\${external.service.url}")
    private lateinit var externalServiceUrl: String

    @Autowired
    private lateinit var restTemplate: RestTemplate

//    private val webClient = WebClient.builder().build()

    private lateinit var asyncRestTemplate: AsyncRestTemplate

    fun callA() =
            restTemplate.getForObject(externalServiceUrl, ResultA::class.java)!!

    fun callB() =
            restTemplate.getForObject(externalServiceUrl, ResultB::class.java)!!

    fun callC() =
            restTemplate.getForObject(externalServiceUrl, ResultC::class.java)!!


    @Async
    fun callAAsync(): CompletableFuture<ResultA> {
        val result = restTemplate.getForObject(externalServiceUrl, ResultA::class.java)!!
        return CompletableFuture.completedFuture(result)
    }

    @Async
    fun callBAsync(): CompletableFuture<ResultB> {
        val result = restTemplate.getForObject(externalServiceUrl, ResultB::class.java)!!
        return CompletableFuture.completedFuture(result)
    }

    @Async
    fun callCAsync(): CompletableFuture<ResultC> {
        val result = restTemplate.getForObject(externalServiceUrl, ResultC::class.java)!!
        return CompletableFuture.completedFuture(result)
    }
}
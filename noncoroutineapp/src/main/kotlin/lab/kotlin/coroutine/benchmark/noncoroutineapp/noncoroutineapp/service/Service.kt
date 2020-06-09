package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.AsyncRestTemplate

@Service
class Service {

    @Autowired
    private lateinit var externalService: ExternalService

    @Autowired
    private lateinit var resultRepository: ResultRepository

    private val asyncRestTemplate = AsyncRestTemplate()

    fun execute(): Result {
        val result = Result(
                resultA = externalService.callA(),
                resultB = externalService.callB(),
                resultC = externalService.callC()
        )

        resultRepository.save(result)

        return result
    }

    fun executeAsync(): Result {
        val serviceA = externalService.callAAsync()
        val serviceB = externalService.callBAsync()
        val serviceC = externalService.callCAsync()

        val result = Result(
                resultA = serviceA.get(),
                resultB = serviceB.get(),
                resultC = serviceC.get()
        )

        resultRepository.save(result)

        return result
    }
}
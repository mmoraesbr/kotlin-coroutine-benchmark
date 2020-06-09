package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.service

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitFirst
import lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain.Result
import lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain.ResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class Service {

    @Autowired
    private lateinit var externalService: ExternalService

    @Autowired
    private lateinit var resultRepository: ResultRepository

    suspend fun execute(): Result {
        val result = Result(
                resultA = externalService.callA(),
                resultB = externalService.callB(),
                resultC = externalService.callC()
        )

        resultRepository.save(result).awaitFirst()

        return result
    }

    suspend fun executeAsync() = coroutineScope {
        val serviceA = async { externalService.callA() }
        val serviceB = async { externalService.callB() }
        val serviceC = async { externalService.callC() }

        val result = Result(
                resultA = serviceA.await(),
                resultB = serviceB.await(),
                resultC = serviceC.await()
        )

        resultRepository.save(result).awaitFirst()

        result
    }

}
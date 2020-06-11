package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.service

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitFirst
import lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class Service {

    @Autowired
    private lateinit var externalService: ExternalService

    @Autowired
    private lateinit var resultRepository: ResultRepository

    /**
     * ******************
     * * DO NOT DO IT   *
     * ******************
     *
     * Any blocking code (ex: IO, Thread.sleep, etc) will block the shared thread.
     *
     */
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

    fun executeAsyncWithReactor(): Mono<Result> {
        val callA = externalService.callAWithReactor().subscribeOn(Schedulers.elastic())
        val callB = externalService.callBWithReactor().subscribeOn(Schedulers.elastic())
        val callC = externalService.callCWithReactor().subscribeOn(Schedulers.elastic())

        return Mono.zip(listOf(callA, callB, callC)) {
            Result(
                    resultA = it[0] as ResultA,
                    resultB = it[1] as ResultB,
                    resultC = it[2] as ResultC
            )
        }.flatMap {
            resultRepository.save(it)
        }
    }
}
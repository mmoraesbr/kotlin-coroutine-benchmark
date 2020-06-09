package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.application

import lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.service.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SomeResource {

    @Autowired
    private lateinit var service: Service

    @GetMapping("/execute")
    suspend fun execute() = service.execute()

    @GetMapping("/executeAsync")
    suspend fun executeAsync() = service.executeAsync()
}
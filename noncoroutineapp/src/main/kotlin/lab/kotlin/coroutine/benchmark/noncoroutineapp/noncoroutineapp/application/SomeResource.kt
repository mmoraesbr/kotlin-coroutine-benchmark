package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.application

import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.service.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SomeResource {

    @Autowired
    private lateinit var service: Service

    @GetMapping("/execute")
    fun execute() = service.execute()

    @GetMapping("/executeAsync")
    fun executeAsync() = service.executeAsync()
}
package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.application

import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain.LinkExplorerService
import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain.LinkTree
import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain.TargetHost
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/explore")
class LinkExplorerResource {

    @Autowired
    private lateinit var service: LinkExplorerService

    @PostMapping
    fun execute(@RequestBody target: TargetHost): LinkTree {
        return service.execute(target)
    }
}

package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.application

import lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain.LinkExplorerService
import lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain.LinkTree
import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain.TargetHost
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient

@RequestMapping("/explore")
@RestController
class LinkExplorerResources {

    @Autowired
    private lateinit var service: LinkExplorerService

    private val webClientBuilder = WebClient.builder().clientConnector(object : ReactorClientHttpConnector(
            HttpClient.create().followRedirect(true)) {
    })

    @PostMapping
    suspend fun execute(@RequestBody target: TargetHost): LinkTree {
        return service.execute(target)
    }

}
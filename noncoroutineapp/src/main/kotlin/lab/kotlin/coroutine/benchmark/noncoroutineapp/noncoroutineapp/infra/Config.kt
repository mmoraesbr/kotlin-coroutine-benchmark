package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.infra

import org.apache.http.client.HttpClient
import org.apache.http.conn.HttpClientConnectionManager
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.client.RestTemplate
import java.util.concurrent.Executor


@Configuration
class Config {
    @Bean
    fun httpClient(): HttpClient {
        val poolingConnManager: HttpClientConnectionManager = PoolingHttpClientConnectionManager()
        return HttpClients.custom().setConnectionManager(poolingConnManager)
                .build()
    }

    @Bean
    fun restTemplate() = RestTemplate()

    @Bean
    fun asyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 5
        executor.maxPoolSize = 30
        executor.setQueueCapacity(50)
        executor.setThreadNamePrefix("AsyncExecutor-")
        executor.initialize()
        return executor
    }
}
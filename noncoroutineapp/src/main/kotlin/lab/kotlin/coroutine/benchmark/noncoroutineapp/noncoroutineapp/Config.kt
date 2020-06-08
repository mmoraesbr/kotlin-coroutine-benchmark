package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp

import org.apache.http.client.HttpClient
import org.apache.http.conn.HttpClientConnectionManager
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class Config {
    @Bean
    fun httpClient(): HttpClient {
        val poolingConnManager: HttpClientConnectionManager = PoolingHttpClientConnectionManager()
        return HttpClients.custom().setConnectionManager(poolingConnManager)
                .build()
    }
}
package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.infra

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration


@Configuration
class MongoDBConfig : AbstractMongoClientConfiguration() {

    @Bean
    override fun mongoClient(): MongoClient {
        return MongoClients.create()
    }

    override fun getDatabaseName(): String {
        return "noncoroutineapp"
    }
}
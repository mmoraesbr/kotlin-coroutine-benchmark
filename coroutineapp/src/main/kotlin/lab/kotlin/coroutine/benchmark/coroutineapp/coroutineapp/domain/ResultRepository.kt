package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ResultRepository : ReactiveCrudRepository<Result, String>
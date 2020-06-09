package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.service

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ResultRepository : ReactiveCrudRepository<Result, String>
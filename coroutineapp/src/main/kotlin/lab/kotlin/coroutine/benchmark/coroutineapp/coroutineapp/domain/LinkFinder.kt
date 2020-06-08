package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain

interface LinkFinder {
    suspend fun find(url: String): Set<String>
}

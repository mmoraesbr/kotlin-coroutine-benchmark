package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain

interface LinkFinder {
    fun find(url: String): Set<String>
}

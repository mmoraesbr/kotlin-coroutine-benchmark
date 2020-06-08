package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain

data class TargetHost(
        val url: String,
        val maxDepth: Int,
        val maxChilds: Int)
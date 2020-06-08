package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain

data class LinkTree(
        val url: String,
        val childs: List<LinkTree> = emptyList()
)
package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain

data class LinkTree(
        val url: String,
        val childs: List<LinkTree> = emptyList()
)
package lab.kotlin.coroutine.benchmark.coroutineapp.coroutineapp.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.withContext
import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain.TargetHost
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.min

@Service
class LinkExplorerService {

    @Autowired
    protected lateinit var linkTreeDocumentRepository: LinkTreeDocumentRepository

    @Autowired
    private lateinit var linkFinder: LinkFinder

    suspend fun execute(target: TargetHost): LinkTree {
        return withContext(Dispatchers.IO) {
            explore(target) ?: LinkTree(url = "ERROR")
        }
    }

    private suspend fun explore(target: TargetHost, currentDepth: Int = 0, explored: MutableSet<String> = HashSet()): LinkTree? {

        if (explored.contains(target.url)) {
            return null
        }

        save(target.url)

        val links = linkFinder.find(target.url).toList()

        val childs = ArrayList<LinkTree>(links.size)

        val root = LinkTree(
                url = target.url,
                childs = childs
        )

        val size = if (target.maxChilds < 0) {
            links.size
        } else {
            min(target.maxChilds, links.size)
        }

        for (index in 0 until size) {
            val childUrl = links[index]
            if (currentDepth < target.maxDepth) {
                explore(target.copy(url = childUrl), currentDepth + 1, explored)?.let {
                    childs.add(it)
                    explored.add(childUrl)
                }
            } else {
                childs.add(LinkTree(childUrl))
            }
        }

        return root
    }

    private suspend fun save(url: String) {
        var entity = linkTreeDocumentRepository.save(LinkTreeDocument(url)).awaitFirstOrNull()

        if (entity == null) {
            entity = LinkTreeDocument(url)
        }

        entity.visited()
        linkTreeDocumentRepository.save(entity)
    }
}

private fun LinkTree.toDocument() = LinkTreeDocument(
        url = this.url
)

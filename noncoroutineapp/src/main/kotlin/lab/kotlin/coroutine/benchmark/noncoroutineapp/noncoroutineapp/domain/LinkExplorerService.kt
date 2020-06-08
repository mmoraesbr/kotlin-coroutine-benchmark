package lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.domain

import lab.kotlin.coroutine.benchmark.noncoroutineapp.noncoroutineapp.infra.HttpLinkFinder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.min

@Service
class LinkExplorerService {

    @Autowired
    private lateinit var linkFinder: LinkFinder

    fun execute(target: TargetHost): LinkTree {
        return explore(target) ?: LinkTree(url = "ERROR")
    }

    private fun explore(target: TargetHost, currentDepth: Int = 0, explored: MutableSet<String> = HashSet()): LinkTree? {

        if (explored.contains(target.url)) {
            return null
        }

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
}
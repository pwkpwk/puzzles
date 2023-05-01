package com.example.puzzles

object LinkTree {

    class Node(val value: Int, val left: Node?, val right: Node?) {
        var next: Node? = null
    }

    fun linkTree(root: Node) {
        val queue = ArrayDeque<Node?>()

        queue.addLast(root)
        queue.addLast(null)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()

            node?.let {
                if (queue.size > 0 && queue[0] != null) {
                    it.next = queue[0]
                }

                if (it.left != null) {
                    queue.addLast(it.left)
                }

                if (it.right != null) {
                    queue.addLast(it.right)
                }

                if (it.next == null) {
                    queue.addLast(null)
                }
            }
        }
    }
}

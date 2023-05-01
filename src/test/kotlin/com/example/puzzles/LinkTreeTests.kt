package com.example.puzzles

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LinkTreeTests {
    @Test
    fun plop() {
        /*
              1
            /  \
           2    3
          / \  / \
         4  5 6  7
         */

        val root = LinkTree.Node(1,
            LinkTree.Node(2,
                LinkTree.Node(4, null, null),
                LinkTree.Node(5, null, null)
            ),
            LinkTree.Node(3,
                LinkTree.Node(6, null, null),
                LinkTree.Node(7, null, null)
            ))

        LinkTree.linkTree(root)

        assertNull(root.next)
        assertEquals(3, root.left?.next?.value)
        assertNull(root.left?.next?.next)
        assertEquals(5, root.left?.left?.next?.value)
        assertEquals(6, root.left?.left?.next?.next?.value)
        assertEquals(7, root.left?.left?.next?.next?.next?.value)
        assertNull(root.left?.left?.next?.next?.next?.next?.next)
    }
}

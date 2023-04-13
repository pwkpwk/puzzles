package com.example.puzzles

import java.security.MessageDigest
import java.util.*
import kotlin.Comparator

object RendevousHash {

    /**
     * Select new resources for the object after one of the resources will have been removed
     * from the resource set
     */
    fun removeResourceAndRelocate(
        objectKey: String,
        resourceKeys: List<String>,
        removedResourceKey: String,
        selectionSize: Int
    ): List<String> =
        selectResources(
            objectKey,
            LinkedList<String>().apply {
                for (rk in resourceKeys) {
                    if (!rk.contentEquals(removedResourceKey)) {
                        add(rk)
                    }
                }
            },
            selectionSize
        )

    fun addResourcesAndRelocate(
        objectKey: String,
        resourceKeys: Collection<String>,
        newResourceKeys: Collection<String>,
        oldSelectionSize: Int,
        selectionSize: Int
    ): ObjectRelocation =
        ObjectRelocationData(
            objectKey,
            selectResources(objectKey, resourceKeys, oldSelectionSize),
            selectResources(
                objectKey,
                LinkedList(resourceKeys).apply {
                    addAll(newResourceKeys)
                },
                selectionSize
            )
        )

    /**
     * Select the specified number of resources from a collection for placing the specified object
     *
     * @param objectKey Stable key of the object to be placed on the selected resources
     * @param resourceKeys Collection of stable keys of the resources that will accept the object
     * @param selectionSize Size of the requested selection; must be less than the number of resources
     * @return Collection of selected resource identifiers
     */
    fun selectResources(objectKey: String, resourceKeys: Collection<String>, selectionSize: Int): List<String> =
        mutableListOf<String>().apply {
            val pq = PriorityQueue(resourceKeys.size, KeyedHashComparator())

            for (rk in resourceKeys) {
                pq.add(KeyedHash(rk, calculateHash(objectKey, rk)))
            }

            for (i in 0 until selectionSize) {
                add(pq.remove().resourceKey)
            }
        }

    interface ObjectRelocation {
        val objectKey: String
        val removeFrom: Collection<String>
        val addTo: Collection<String>
    }

    private fun calculateHash(objectKey: String, resourceKey: String): ByteArray =
        MessageDigest.getInstance("MD5").apply {
            update(objectKey.toByteArray(Charsets.UTF_8))
            update(resourceKey.toByteArray(Charsets.UTF_8))
        }.digest()

    private class KeyedHash(val resourceKey: String, val hash: ByteArray)

    private class KeyedHashComparator : Comparator<KeyedHash> {
        override fun compare(o1: KeyedHash?, o2: KeyedHash?): Int =
            if (o1 == null) {
                if (o2 == null) 0 else -1
            } else if (o2 == null) {
                1 // o1 is present, o2 is null
            } else {
                Arrays.compare(o1.hash, o2.hash)
            }
    }

    private class ObjectRelocationData : ObjectRelocation {

        private val ok: String
        private val rf: MutableCollection<String>
        private val at: MutableCollection<String>

        constructor(
            objectKey: String,
            oldResourceKeys: Collection<String>,
            newResourceKeys: Collection<String>
        ) {
            ok = objectKey

            val setOld = mutableSetOf<String>().apply { addAll(oldResourceKeys) }
            val setNew = mutableSetOf<String>().apply { addAll(newResourceKeys) }

            rf = mutableListOf<String>().apply {
                for (rk in oldResourceKeys) {
                    if (!setNew.contains(rk)) {
                        add(rk)
                    }
                }
            }
            at = mutableListOf<String>().apply {
                for (rk in newResourceKeys) {
                    if (!setOld.contains(rk)) {
                        add(rk)
                    }
                }
            }
        }

        override val objectKey get() = ok
        override val removeFrom: Collection<String> get() = rf

        override val addTo: Collection<String> get() = at
    }
}

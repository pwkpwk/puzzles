import com.example.puzzles.RendevousHash

fun main() {
    /*
    RendevousHash in action: figure out how to relocate one resident after
    new resources have been added to the set.
     */
    val set = listOf("server1", "server2", "server3", "server4")
    val addition = listOf("server5", "server6")
    val resident = "Giraffe"

    val residence = RendevousHash.selectResources(resident, set, 2)
    println("${resident} resided at ${residence}")
    RendevousHash.addResourcesAndRelocate(resident, set, addition, 2, 2).apply {
        println("Relocate ${objectKey} from ${removeFrom} to ${addTo}")
    }
    val newResidence = RendevousHash.selectResources(resident, set.plus(addition), 2)
    println("${resident} resides at ${newResidence}")
}

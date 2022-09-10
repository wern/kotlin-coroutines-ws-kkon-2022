package coroutines.kotlin.coroutines.async

// Import needed!
import kotlinx.coroutines.*

fun main() = countParticipants()

fun countParticipants() = runBlocking {
    val audience = async { countAudience() }
    val presenter = async { countPresenter() }
    println("${audience.await()} Zuhörer und ${presenter.await()} Sprecher anwesend.")
}

suspend fun countAudience() : Int{
    delay(1000L)
    return 14
}

suspend fun countPresenter() : Int{
    delay(500L)
    return 1
}
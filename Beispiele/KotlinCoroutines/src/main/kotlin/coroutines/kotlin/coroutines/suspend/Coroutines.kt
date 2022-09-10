package coroutines.kotlin.coroutines.suspend

// Import needed!
import kotlinx.coroutines.*

fun main() = helloWithFun()

fun hello() = runBlocking {
    launch {
        delay(1000L)
        println("KKON!")
    }
    println("Willkommen zur")
}

fun helloWithFun() = runBlocking {
    launch {
        delayedKKONNewJobs()
    }
    println("Willkommen zur")
}

fun helloWithFunSeq() = runBlocking {
    delayedKKON()
    println("Willkommen zur")
}

fun helloWithTimeout() = runBlocking {
    withTimeout(50) {
        delayedKKON()
    }
    println("Willkommen zur")
}

suspend fun delayedKKON() {
    try{
        delay(1000L)
        println("KKON!")
    }catch(e : CancellationException){
        println("Abbruch KKON :(")
    }
}

suspend fun delayedKKONNewJobs() = coroutineScope {
    launch {
        delay(2000L)
        println("2022")
    }

    launch {
        delay(1000L)
        println("digital")
    }

    println("KKON")
}

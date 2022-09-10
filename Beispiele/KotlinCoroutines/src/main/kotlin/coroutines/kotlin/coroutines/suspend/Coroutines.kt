package coroutines.kotlin.coroutines.suspend

// Import needed!
import kotlinx.coroutines.*

fun main() = helloWithFun()

fun hello() = runBlocking {
    launch {
        delay(1000L)
        println("ETKA!")
    }
    println("Willkommen zum")
}

fun helloWithFun() = runBlocking {
    launch {
        delayedETKANewJobs()
    }
    println("Willkommen zum")
}

fun helloWithFunSeq() = runBlocking {
    delayedETKA()
    println("Willkommen zum")
}

fun helloWithTimeout() = runBlocking {
    withTimeout(50) {
        delayedETKA()
    }
    println("Willkommen zum")
}

suspend fun delayedETKA() {
    try{
        delay(1000L)
        println("ETKA!")
    }catch(e : CancellationException){
        println("Abbruch ETKA :(")
    }
}

suspend fun delayedETKANewJobs() = coroutineScope {
    launch {
        delay(2000L)
        println("2022")
    }

    launch {
        delay(1000L)
        println("Karlsruhe")
    }

    println("Entwicklertag")
}

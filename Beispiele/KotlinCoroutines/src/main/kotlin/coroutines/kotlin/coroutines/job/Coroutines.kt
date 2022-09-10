package coroutines.kotlin.coroutines.job

// Import needed!
import kotlinx.coroutines.*
import java.lang.RuntimeException

fun main() = helloWithJob()

fun helloWithJob () = runBlocking {
    val job = launch {
        delayedETKA()
    }
    println("Willkommen zum")
    job.join()
    println("2022")
}

fun helloWithJobCancelation () = runBlocking {
    val job = launch (start = CoroutineStart.LAZY ){
        delayedETKAWithSubjobs()
    }
    println("Willkommen zum")
    delay(10)
    job.cancel()
    println("und Tschüss... ;))")
}

suspend fun delayedETKAWithSubjobs () = coroutineScope {
        launch {
            delayedKarlsruhe()
        }
        launch {
            delayedBrokenEntwicklertag()
        }
}

suspend fun delayedETKA() {
    try{
        delay(1000L)
        println("ETKA!")
    }catch(e : CancellationException){
        println("Abbruch!!! :(")
    }
}

suspend fun delayedKarlsruhe() {
    try{
        delay(2000L)
        println("Karlsruhe")
    }catch(e : CancellationException){
        println("Abbruch Karlsruhe :(")
    }
}

suspend fun delayedEntwicklertag() {
    try{
        delay(1000L)
        println("Entwicklertag")
    }catch(e : CancellationException){
        println("Abbruch Entwicklertag :(")
    }
}

suspend fun delayedBrokenEntwicklertag() {
    try{
        delay(1000L)
        println("Entwicklertag")
        throw RuntimeException("Bang!")
    }catch(e : CancellationException){
        println("Abbruch Karlsruhe :(")
    }
}

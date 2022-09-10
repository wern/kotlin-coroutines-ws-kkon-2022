package coroutines.kotlin.coroutines.job

// Import needed!
import kotlinx.coroutines.*
import java.lang.RuntimeException

fun main() = helloWithJob()


fun helloWithJob () = runBlocking {
    val job = launch {
        delayedKKON()
    }
    println("Willkommen zur")
    job.join()
    println("2022")
}

fun helloWithJobCancelation () = runBlocking {
    val job = launch {
        delayedKKONWithSubjobs()
    }
    println("Willkommen zur")
    delay(10)
    job.cancel()
    println("und Tschüss... ;))")
}

suspend fun delayedKKONWithSubjobs () = coroutineScope {
        launch {
            delayedDigital()
        }
        launch {
            delayedBrokenKKON()
        }
}

suspend fun delayedKKON() {
    try{
        delay(1000L)
        println("KKON!")
    }catch(e : CancellationException){
        println("Abbruch KKON :(")
    }
}

suspend fun delayedDigital() {
    try{
        delay(2000L)
        println("digital")
    }catch(e : CancellationException){
        println("Abbruch Karlsruhe :(")
    }
}

suspend fun delayedBrokenKKON() {
    try{
        delay(1000L)
        println("digital")
        throw RuntimeException("Bang!")
    }catch(e : CancellationException){
        println("Abbruch KKON :(")
    }
}


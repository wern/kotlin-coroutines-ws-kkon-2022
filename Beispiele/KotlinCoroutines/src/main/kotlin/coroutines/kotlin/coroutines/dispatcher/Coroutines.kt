package coroutines.kotlin.coroutines.dispatcher

// Import needed!
import coroutines.printlnWithThreadInfo
import kotlinx.coroutines.*

fun main() = helloWithDefaultDispatcher()

fun hello() = runBlocking {
    launch {
        delay(1000L)
        printlnWithThreadInfo("ETKA!")
    }
    printlnWithThreadInfo("Willkommen zum")
}

fun helloWithDefaultDispatcher() = runBlocking {
    launch(Dispatchers.Default) {
        delay(1000L)
        printlnWithThreadInfo("ETKA!")
    }
    printlnWithThreadInfo("Willkommen zum")
}

fun helloWithDefaultDispatcherAndContextSwitch() = runBlocking {
    val outerContext = coroutineContext
    launch(Dispatchers.Default) {
        delay(1000L)
        printlnWithThreadInfo("Doing my work...")
        withContext(outerContext) {
            printlnWithThreadInfo("ETKA!")
        }
    }
    printlnWithThreadInfo("Willkommen zum")
}

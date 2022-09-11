package coroutines.kotlin.coroutines.dispatcher

// Import needed!
import coroutines.printlnWithThreadInfo
import kotlinx.coroutines.*

fun main() = helloWithDefaultDispatcher()

fun hello() = runBlocking {
    launch {
        delay(1000L)
        printlnWithThreadInfo("KKON!")
    }
    printlnWithThreadInfo("Willkommen zur")
}

fun helloWithDefaultDispatcher() = runBlocking {
    launch(Dispatchers.Default) {
        delay(1000L)
        printlnWithThreadInfo("KKON!")
    }
    printlnWithThreadInfo("Willkommen zur")
}

fun helloWithDefaultDispatcherAndContextSwitch() = runBlocking {
    val outerContext = coroutineContext
    launch(Dispatchers.Default) {
        delay(1000L)
        printlnWithThreadInfo("Doing my work...")
        withContext(outerContext) {
            printlnWithThreadInfo("KKON!")
        }
    }
    printlnWithThreadInfo("Willkommen zur")
}

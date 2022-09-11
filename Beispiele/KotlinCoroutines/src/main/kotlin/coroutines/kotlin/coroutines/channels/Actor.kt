package coroutines.kotlin.coroutines.channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor

fun main() = countSafe()

var counter = 0

fun countUnsafe() = runBlocking {
    withContext(Dispatchers.Default) {
        startCoroutines(100,1000) {
            counter++
        }
    }
    println("Counter = $counter")
}

fun countSafe() = runBlocking<Unit> {
    val counter = counterActor()
    withContext(Dispatchers.Default) {
        startCoroutines(100,1000) {
            counter.send(IncrementCounter)
        }
    }

    val response = CompletableDeferred<Int>()
    counter.send(GetCounterValue(response))
    println("Counter = ${response.await()}")
    counter.close()
}

sealed class CounterMsg
object IncrementCounter : CounterMsg()
class GetCounterValue(val counterValue: CompletableDeferred<Int>) : CounterMsg()

fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter = 0
    for (msg in channel) {
        when (msg) {
            is IncrementCounter -> counter++
            is GetCounterValue -> msg.counterValue.complete(counter)
        }
    }
}

suspend fun startCoroutines(number : Int, repititions: Int, action: suspend () -> Unit) {
   coroutineScope {
       repeat(number) {
           launch {
               repeat(repititions) { action() }
           }
       }
   }
}
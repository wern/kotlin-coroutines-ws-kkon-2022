package coroutines.kotlin.coroutines.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.text.StringBuilder

val chars = "Hello KKON digital 2022!".toCharArray()

fun main () = simpelKKONCharacterChannel()

fun simpelKKONCharacterChannel() = runBlocking {
    val channel = Channel<Char>()
    launch {
        chars.forEach {
            // Komplizierte und daher parallel ausgefuehrte Berechnung
            delay(400L)
            channel.send(it)
        }
    }
    repeat(chars.size) { print(channel.receive()) }
    println("\nFertig!")
}

fun closingKKONCharacterChannel() = runBlocking {
    val channel = Channel<Char>()
    launch {
        chars.forEach {
            // Komplizierte und daher parallel ausgefuehrte Berechnung
            delay(400L)
            channel.send(it)
        }
        channel.close()
    }
    for(c in channel) { print(c) }
    println("\nFertig!")
}

fun producedKKONCharacters() = runBlocking {
    val charProducer = produce {
        chars.forEach {
            delay(400L)
            send(it)
        }
    }
    charProducer.consumeEach { print(it) }
    println("\nFertig!")
}

fun pipelinedKKONCharacters () = runBlocking {
    val charProducer = produce {
        chars.forEach {
            delay(400L)
            send(it)
        }
    }

    val wordProducer = produceWords(charProducer)
    wordProducer.consumeEach { println(it) }
    println("Fertig!")
}

fun pipelinedKKONCharactersWithMultipleConsumers() = runBlocking {
    val charProducer = produce {
        chars.forEach {
            delay(400L)
            send(it)
        }
    }

    val wordProducer = produceWords(charProducer)
    val consumerA = launchWordConsumer(1, wordProducer)
    val consumerB = launchWordConsumer(2, wordProducer)

    consumerA.join()
    consumerB.join()
    println("Fertig!")
}

fun CoroutineScope.produceWords(charProducer : ReceiveChannel<Char>) : ReceiveChannel<String> = produce {
    val wordBuffer = StringBuilder()
    charProducer.consumeEach {
        if(it == ' ') {
                send(wordBuffer.toString())
                wordBuffer.clear()
        } else{
                wordBuffer.append(it)
        }
    }
    if(!wordBuffer.isEmpty()) {
        send(wordBuffer.toString())
    }
}

fun CoroutineScope.launchWordConsumer(idx: Int, wordProducer: ReceiveChannel<String>) = launch {
    for (word in wordProducer) {
        println("$word ($idx. Consumer)")
    }
}
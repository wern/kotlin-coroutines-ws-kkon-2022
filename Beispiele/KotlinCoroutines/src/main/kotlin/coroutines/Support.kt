package coroutines

fun printlnWithThreadInfo(text : String){
    println("${Thread.currentThread().name} : $text")
}
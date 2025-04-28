import java.net.ServerSocket
import java.util.concurrent.Executors

fun main() {
    val port = readLine()!!.toInt()
    val serverSocket = ServerSocket(port)
    val threadPool = Executors.newVirtualThreadPerTaskExecutor()
    println("Server started on port $port")
    while (true) {
        val socket = serverSocket.accept()
        threadPool.submit {
            socket.use {
                Handler().handle(socket)
            }
        }
    }
}
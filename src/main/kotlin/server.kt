import Model.dataset
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.jetbrains.exposed.sql.ResultRow
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    val server = ServerSocket(8034)
    println("Server is running on port ${server.localPort}")

    while (true) {
        val client = server.accept()
        println("Client connected: ${client.inetAddress.hostAddress}")

        val inStream = ObjectInputStream(client.getInputStream())

        val data = inStream.readObject() as dataset
//        println(String(data))
//        val receivedData = Gson().fromJson<ArrayList<dataset>>(String(data), ArrayList::class.java)
        println("receive data")
        println(data)

        thread { convert(data) }

//        thread { convert(recievedData) }
//        receivedData.forEach {
////            convert(it)
////            println(it as dataset)
//        }
    }
}
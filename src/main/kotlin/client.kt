import Model.dataset
import Tables.*
import com.mysql.jdbc.Connection
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.util.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    createTables()

    val rawData = ArrayList<ResultRow>()

    transaction(Connection.TRANSACTION_SERIALIZABLE, 2, Db.db_bad) {
        val query = HeroesTrashCan.selectAll()
        rawData.addAll(query)
    }

    val list = ArrayList<dataset>()

    rawData.forEach {
        val client = Socket("localhost",8034)
        val outStream = ObjectOutputStream(client.getOutputStream())
        val data = dataset(
                it[HeroesTrashCan.personId],
                it[HeroesTrashCan.name],
                it[HeroesTrashCan.age],
                it[HeroesTrashCan.secondName],
                it[HeroesTrashCan.middleName],
                it[HeroesTrashCan.resume],
                it[HeroesTrashCan.workPlace],
                it[HeroesTrashCan.startDate],
                it[HeroesTrashCan.finishDate],
                it[HeroesTrashCan.duty],
                it[HeroesTrashCan.superPower]
        )

        val json = Gson().toJson(data)

        outStream.writeObject(data)
    }
//    val send = Gson().toJson(list).toByteArray()
//    outStream.writeObject(send)

//    client.close()
}
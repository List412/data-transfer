import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection

object Db {
    val db_bad by lazy {
        val conn = Database.connect("jdbc:sqlite:C:/Sqlite/bad_data.db", "org.sqlite.JDBC")
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
        return@lazy conn
    }
    val db by lazy {
        Database.connect("jdbc:mysql://localhost:3306/some_data", "com.mysql.jdbc.Driver",
        user = "root", password = ",bkb,ehlf")
    }
}
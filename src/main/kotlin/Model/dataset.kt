package Model

import org.joda.time.DateTime
import java.io.Serializable

data class dataset(
    val personId: Int,
    val name: String,
    val age : Int?,
    val secondName : String?,
    val middleName : String?,
    val resume : String?,
    val workPlace : String?,
    val startDate : DateTime?,
    val finishDate : DateTime?,
    val duty : String?,
    val superPower : String?
) : Serializable
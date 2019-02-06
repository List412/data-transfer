import Model.Person
import Tables.Persons
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.FileOutputStream
import kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue

fun main(args: Array<String>) {

    var persons: ArrayList<Person> = ArrayList()
    transaction(Db.db) {
        var s = Person.all()
        persons.addAll(s)
    }

    var book = HSSFWorkbook()
    var sheet = book.createSheet("Люди")

    var rowLabel = sheet.createRow(0)
    var nameLabel = rowLabel.createCell(0)
    nameLabel.setCellValue("Имя")
    var secondNameLabel = rowLabel.createCell(1)
    secondNameLabel.setCellValue("Фамилия")
    var middleNameLabel = rowLabel.createCell(2)
    middleNameLabel.setCellValue("Отчество")
    var ageLabel = rowLabel.createCell(3)
    ageLabel.setCellValue("Возраст")
    var nameStyle = book.createCellStyle()

    var i = 1
    var j = 0
    persons.forEach {
        var row = sheet.createRow(i)
        var name = row.createCell(0)
        name.setCellValue(it.name)
        var secondName = row.createCell(1)
        if (it.secondName != null) secondName.setCellValue(it.secondName)
        var middleName = row.createCell(2)
        if (it.middleName != null) middleName.setCellValue(it.middleName)
        var age = row.createCell(3)
        if (it.age != null) age.setCellValue( it.age!!.toDouble() )
        i++

    }
    for (a in 0..3) {
        sheet.autoSizeColumn(a)
    }




    var file = "persons.xls"
    book.write(FileOutputStream(file))
    book.close()

}
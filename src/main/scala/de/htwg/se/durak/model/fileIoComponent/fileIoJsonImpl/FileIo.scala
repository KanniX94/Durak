import java.io.{BufferedWriter, File, FileWriter}

import com.google.gson.Gson
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.Field
import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.model.fileIoComponent.FileIoInterface

import scala.io.Source

class FileIo extends FileIoInterface {
  def save(filename: String, field: FieldInterface): Unit = {
    val file = new File(filename + ".json")
    val bufferedWriter = new BufferedWriter(new FileWriter(file))

    val gson = new Gson
    bufferedWriter.write(gson.toJson(field))
    bufferedWriter.close()
  }

  def load(filename: String, field: FieldInterface): Unit = {
    val source = Source.fromFile(filename + ".json")
    val lines = try source.mkString finally source.close()

    val gson = new Gson
    val jsonField = gson.fromJson(lines, classOf[Field])

    field.deck = jsonField.deck
    field.playerCardOnHand = jsonField.playerCardOnHand
    field.enemyCardOnHand = jsonField.enemyCardOnHand
    field.cardOnField = jsonField.cardOnField
  }
}
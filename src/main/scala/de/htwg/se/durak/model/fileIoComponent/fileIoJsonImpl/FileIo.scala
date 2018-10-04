package de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.durak.durakGameModule
import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.model.fileIoComponent.FileIoInterface
import de.htwg.se.durak.model.FieldComponent.PlayerInterface
import de.htwg.se.durak.model.FieldComponent.DeckInterface
import play.api.libs.json._

import scala.util.{Failure, Success, Try}
import scala.io.Source

class FileIo extends FileIoInterface {

  final val FILE_NAME: String = "field.json"

  override def load: Try[Option[FieldInterface]] = {
    var gridOption: Option[FieldInterface] = None

    Try {
      val source: String = Source.fromFile(FILE_NAME).getLines.mkString

      val json: JsValue = Json.parse(source)
      val size = (json \ "field" \ "size").get.toString.toInt
      val injector = Guice.createInjector(new durakGameModule)
      size match {
        case 1 =>
          fieldOption =
            Some(injector.instance[FieldInterface](Names.named("tiny")))
        case 4 =>
          fieldOption =
            Some(injector.instance[FieldInterface](Names.named("small")))
        case 9 =>
          fieldOption =
            Some(injector.instance[FieldInterface](Names.named("normal")))
        case _ =>
      }
      gridOption match {
        case Some(grid) => {
          var _grid = grid
          for (index <- 0 until size * size) {
            val row = (json \\ "row") (index).as[Int]
            val col = (json \\ "col") (index).as[Int]
            val cell = (json \\ "cell") (index)
            val value = (cell \ "value").as[Int]
            _grid = _grid.set(row, col, value)
            val given = (cell \ "given").as[Boolean]
            val showCandidates = (cell \ "showCandidates").as[Boolean]
            if (given) _grid = _grid.setGiven(row, col, value)
            if (showCandidates) _grid = _grid.setShowCandidates(row, col)
          }
          gridOption = Some(_grid)
        }
        case None =>
      }
      gridOption
    }
  }

  override def save(field: FieldInterface): Try[Unit] = {
    import java.io._

    Try {
      val pw = new PrintWriter(new File(FILE_NAME))
      pw.write(Json.prettyPrint(fieldToJson(field)))
      pw.close
    }
  }

  def fieldToJson(field: FieldInterface) = field.toJson

}
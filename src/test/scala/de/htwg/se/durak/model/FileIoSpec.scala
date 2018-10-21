package de.htwg.se.durak.model

import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner
import de.htwg.se.durak.model.fileIoComponent._
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl._

@RunWith(classOf[JUnitRunner])
class FileIoSpec extends WordSpec with Matchers {
  "FileIo" when {
    "new" should {
      val fileIoJson = new fileIoJsonImpl.FileIo
      val fileIoXml = new fileIoXmlImpl.FileIo

      val field = new Field()
      field.playerInGame(0) = Player("Marcel")
      field.playerInGame(1) = Player("Christoph")
      field.actualPlayer = field.playerInGame(0)
      field.playerCardOnHand += Card("7 Piek", 7 , "P")
      field.enemyCardOnHand += Card("7 Herz", 7 , "H")
      field.deck += Card("7 Caro", 7, "C")
      "have a save (json) " in {
        field.win = true
        fileIoJson.save("test.durak", field)
      }
      "have a load (json) method" in {
        field.win = false
        fileIoJson.load("test.durak", field)
        field.win should be(false)
      }
      /*"have a save (xml) " in {
        field.win = true
        fileIoXml.save("test.durak", field)
      }
      "have a load (xml) method" in {
        field.win = false
        fileIoXml.load("test.durak", field)
        field.win should be(false)
      }*/
    }
  }
}

package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl._

import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class FieldSpec extends WordSpec with Matchers {
  "A Field" when {
    val field = new Field
    field.playerInGame(0) = Player("Marcel")
    field.playerInGame(1) = Player("Christoph")
    field.actualPlayer = field.playerInGame(0)
    field.playerCardOnHand += Card("7 Piek", 7 , "P")
    field.enemyCardOnHand += Card("7 Herz", 7 , "H")
    field.deck += Card("7 Caro", 7, "C")
    "new" should {
      "have a serializePlayer method" in {
        field.serializePlayer(field.playerInGame) should be("Marcel,Christoph")
      }
      "have a serializeCards method" in {
        field.serializeCards(field.playerCardOnHand) should be("7 Piek")
      }
      "have a deserializePlayer method" in {
        val playerInGame = new Array[Player](2)
        playerInGame(0) = Player("Marcel")
        playerInGame(1) = Player("Christoph")
        field.deserializePlayer("Marcel,Christoph") should be(playerInGame)
      }
      "have a deserializeCards method" in {
        val cardOnHand = new ArrayBuffer[Card]
        cardOnHand += Card("7 Piek", 7 , "P")
        field.deserializeCards("7 Piek") should be(cardOnHand)
      }
    }
  }
}
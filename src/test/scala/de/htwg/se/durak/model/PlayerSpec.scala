package de.htwg.se.durak.model

import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.{Card, Deck, Player}
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    val player = Player("Marcel")
    "new" should {
      "have a name" in {
        player.name should be("Marcel")
      }
    }
    "push all cards on field" when {
      var deck = Deck()
      val cardOnField: ArrayBuffer[Card] = ArrayBuffer(Card("7 Herz", 7, "H"), Card("7 Piek", 7, "P"))
      "the cards have the same value" in {
        val cardOnHand = FieldBaseImpl.Card("7 Kreuz", 7, "K")
        player.pushCard(cardOnHand)
        //deck.canPushCard(cardOnField, cardOnHand) should be(true)
      }
    }
    /*"beat a card" should {
      val deck = exactImpl.Deck()
      "have a higher value or trump" in {
        val cardOnField = Card("7 Herz", 7, "H")
        val cardOnHand = Card("8 Herz", 8, "H")
        deck.canBeatCard(cardOnField, cardOnHand) should be(true)
      }
    }*/
  }
}
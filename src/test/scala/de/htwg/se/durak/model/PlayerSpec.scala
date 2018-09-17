package de.htwg.se.durak.model

import de.htwg.se.durak.model.exactImpl.Card
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = exactImpl.Player("Marcel")
      "have a name" in {
        player.name should be("Marcel")
      }
    }
    "push all cards on field" when {
      var deck = exactImpl.Deck()
      val cardOnField: ArrayBuffer[Card] = ArrayBuffer(Card("7 Herz", 7, "H"), Card("7 Piek", 7, "P"))
      "the cards have the same value" in {
        val cardOnHand = exactImpl.Card("7 Kreuz", 7, "K")
        deck.canPushCard(cardOnField, cardOnHand) should be(true)
      }
      "the card have not an other value" in {
        val cardOnHand2 = exactImpl.Card("9 Kreuz", 9, "K")
        deck.canPushCard(cardOnField, cardOnHand2) should be(false)
      }
    }
  }
}
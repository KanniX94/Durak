package de.htwg.se.durak.model

import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.{Card, Deck}
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DeckSpec extends WordSpec with Matchers {
  "A deck" when {
    var deck = Deck.instance()
    "new" should {
      "have 32 Cards in it" in {
        deck.deck.clear()
        deck.deck.size should be(0)
      }
    }
    "filled with all cards" should {
      deck.init()
      "be have 32 cards" in {
        deck.cards.size should be(32)
      }

    }
    deck.deck.clear()
    "when is empty" in {
      deck.deck.size should be(0)
    }
    "is empty when all cards left" in {
      deck.deck.clear()
      deck.deck.isEmpty should be(true)
    }
    "when cards left" when {
      val card = new Card("7 Piek", 7, "P")
      "can dealout a card" in {
        deck.deck.clear()
        deck.deck += card
        val tmpCard = deck.dealOut()
        tmpCard should be(card)
      }
      "cards must be more than 0" in {
        deck.deck += card
        deck.length should not be(0)
      }
      "deck is not empty" in {
        deck.isEmpty should be (false)
      }
    }

  }
}


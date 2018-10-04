package de.htwg.se.durak.model

import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.Deck
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DeckSpec extends WordSpec with Matchers {
  "A deck" when {
    var deck = Deck()
    "new" should {
      "be empty" in {
        deck.size should be(0)
      }
    }
    "filled with all cards" should {
      deck.fillDeck()
      "be have 32 cards" in {
        deck.cards.size should be(32)
      }
    }
  }
}

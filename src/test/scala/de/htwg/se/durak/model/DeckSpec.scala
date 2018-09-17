package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DeckSpec extends WordSpec with Matchers {
  "A deck" when {
    var deck = exactImpl.Deck()
    "new" should {
      "be empty" in {
        deck.size should be(deck.isEmpty())
      }
    }
    "filled with all cards" should {
      deck.fillDeck()
      "be have 32 cards" in {
        deck.cards.size should be(32)
      }
    }
    "mix it" should {
      "not influence the deck" in {
        deck.mixDeck()
        deck.cards.size should be(32)
      }
      "change the order of the cards" in {
        val oldDeck = deck.cards
        deck.mixDeck()
        oldDeck should not be equals(deck.cards)
      }
    }
  }
}

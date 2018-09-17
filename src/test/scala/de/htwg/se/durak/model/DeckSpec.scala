package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DeckSpec extends WordSpec with Matchers {
  "A deck" when {
    "new" should {
      var deck = exactImpl.Deck()
      "be empty" in {
        deck.size should be(deck.isEmpty())
      }
    }
    "filled with all cards" should {
      var deck = exactImpl.Deck()
      deck.fillDeck()
      "be have 32 cards" in {
        deck.cards.size should be(32)
      }
    }
  }
}

package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class deckSpec extends WordSpec with Matchers {
  "A deck" can {
    var deck = exactImpl.Deck()
    "have at least 32 cards" in {
      deck.cards.isEmpty should be(false)
    }
  }
}

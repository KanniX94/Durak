package de.htwg.se.durak.model

import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.Deck
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DeckSpec extends WordSpec with Matchers {
  "A deck" when {
    var deck = Deck.instance()
    "new" should {
      "have 32 Cards in it" in {
        deck.deck.length should be(32)
      }
    }
    "filled with all cards" should {
      deck.init()
      "be have 32 cards" in {
        deck.cards.size should be(32)
      }
    }
  }
}

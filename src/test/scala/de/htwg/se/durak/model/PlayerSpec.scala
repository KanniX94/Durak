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
    "have a toString method" in {
      player.toString should be("Marcel")
    }
    "new" should {
      "have a name" in {
        player.name should be("Marcel")
      }
      "have no cards on hand" in {
        player.cardOnHand should be(empty)
      }
    }
  }
}
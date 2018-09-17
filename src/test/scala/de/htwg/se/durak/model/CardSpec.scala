package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CardSpec extends WordSpec with Matchers {
  "A Card" when {
    "new" should {
      val card = exactImpl.Card("7 Piek", 7, "P")
      "have a name" in {
        card.name should be("7 Piek")
      }
      "have a value" in {
        card.value should be(7)
      }
      "have a symbol" in {
        card.symbol should be("P")
      }
    }
  }
}

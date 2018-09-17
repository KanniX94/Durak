package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = exactImpl.Player("Marcel")
      "have a name" in {
        player.name should be("Marcel")
      }
    }
  }
}

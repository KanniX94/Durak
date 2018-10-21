package de.htwg.se.durak.util

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UtilsSpec extends WordSpec with Matchers {
  "A Util" when {
    "used" should {
      "have a processKey method" in {
        Utils.processKey(87, 'w') should be("schlagen")
        Utils.processKey(83, 's') should be("schieben")
        Utils.processKey(68, 'd') should be("rechts")
        Utils.processKey(65, 'a') should be("links")
        Utils.processKey(81, 'q') should be("beenden")
        Utils.processKey(82, 'r') should be("redo")
        Utils.processKey(90, 'z') should be("speichern")
        Utils.processKey(76, 'l') should be("laden")
        Utils.processKey(85, 'u') should be("undo")
        Utils.processKey(78, 'n') should be("non")
        Utils.processKey(80, 'p') should be("schlucken")
        Utils.processKey(13, 'b') should be("angreifen")
      }
    }
  }
}
package de.htwg.se.durak.model

import com.google.inject.Guice
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.durak.durakGameModule
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl._

import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    "new" should {
      val c = new Controller
      "have a canBeat method" in {
        val c1 = Card("7 Piek", 7, "P")
        val c2 = Card("8 Piek", 8, "P")
        c.canBeatCard(c1, c2) should be(true)
      }
    }
  }
}
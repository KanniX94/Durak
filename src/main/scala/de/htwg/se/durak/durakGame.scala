package de.htwg.se.durak

import com.google.inject.Guice
import de.htwg.se.durak.aview.Tui
import de.htwg.se.durak.aview.gui
import de.htwg.se.durak.aview.gui.Gui
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.Deck
import de.htwg.se.durak.model.FieldComponent.FieldInterface
import play.api.libs.json.JsValue

import scala.io.StdIn.readLine
import scala.swing.{Dimension, Label, MainFrame}

object durakGame {
  //val injector = Guice.createInjector(new durakGameModule)
  //val c = injector.getInstance(classOf[ControllerInterface])
  val c = new Controller
  val tui = new Tui(c)


  def main(args: Array[String]): Unit = {
    while (tui.interpret(readLine())) {}
    print("Das Spiel wird beendet!")
    // Controller zum Objekt machen...statt class
    // Controller.initialize()
  }
}
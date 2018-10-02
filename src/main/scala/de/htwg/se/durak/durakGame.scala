package de.htwg.se.durak

import com.google.inject.Guice
import de.htwg.se.durak.aview.Tui
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface

import scala.io.StdIn.readLine

object durakGame {
  val injector = Guice.createInjector(new durakGameModule)
  val c = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(c)
  c.createNewField

  def main(args: Array[String]): Unit = {
    while (tui.interpret(readLine())) {}
  }
}

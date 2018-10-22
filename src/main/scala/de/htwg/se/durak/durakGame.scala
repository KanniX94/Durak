package de.htwg.se.durak
import de.htwg.se.durak.aview.Tui
import de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl.Controller
import scala.io.StdIn.readLine

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
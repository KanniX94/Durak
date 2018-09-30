package de.htwg.se.durak

import de.htwg.se.durak.aview.tui.Tui
import de.htwg.se.durak.controller.Controller
import com.google.inject.Guice
import scala.io.StdIn._

object durakGame {
  val injector = Guice.createInjector(new DurakModule)
  val c = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(c)

  def main(args: Array[String]): Unit = {
    while (tui.interpret(readLine())) {}
  }
}

package de.htwg.se.durak

import de.htwg.se.durak.aview.tui.Tui
import de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl.Controller
import com.google.inject.Guice
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.model.exactImpl.Card

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn._
import scala.swing.{Reactions, RefSet}
import scala.swing.event.Event

object durakGame {
  val injector = Guice.createInjector(new DurakModule)
  val c = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(c)

  def main(args: Array[String]): Unit = {
    while (tui.interpret(readLine())) {}
  }
}

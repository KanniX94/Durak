package de.htwg.se.durak.util

trait Command {

  def doStep: Unit

  def undoStep: Unit

  def redoStep: Unit
}

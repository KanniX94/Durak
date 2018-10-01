package de.htwg.se.durak.controller.controllerComponent

import de.htwg.se.durak.controller.controllerComponent.GameStatus.GameStatus
import play.api.libs.json.JsValue

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  def initialize(amountOfPlayer: Int)
  def undo: Unit
  def redo: Unit
  def save: Unit
  def load: Unit
  def gameStatus: GameStatus
  def toJson: JsValue

}

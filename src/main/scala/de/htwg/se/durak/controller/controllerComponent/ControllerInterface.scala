package de.htwg.se.durak.controller.controllerComponent

import de.htwg.se.durak.controller.controllerComponent.GameStatus.GameStatus
import de.htwg.se.durak.model.FieldComponent.FieldInterface
import play.api.libs.json.JsValue

import scala.swing.Publisher
import scala.swing.event.Event

trait ControllerInterface extends Publisher {

  def initialize()

  def doGameAction(field: FieldInterface, key: String): Unit

  def doAction(field: FieldInterface, key: String): Unit

  def createEmptyField: Unit

  def createNewField: Unit

  def undo: Unit

  def redo: Unit

  def save: Unit

  def load: Unit

  def gameStatus: GameStatus

  def fieldToString: String

  def toJson: JsValue

}

class FieldChanged extends Event
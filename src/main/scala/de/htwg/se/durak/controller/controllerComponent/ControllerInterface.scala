package de.htwg.se.durak.controller.controllerComponent

import de.htwg.se.durak.controller.controllerComponent.GameStatus.GameStatus
import de.htwg.se.durak.model.FieldComponent.FieldInterface
import play.api.libs.json.JsValue

import scala.swing.event.Event

trait ControllerInterface {

  def initialize()

  def doGameAction(field: FieldInterface, key: String): Unit

  def doAction(field: FieldInterface, key: String): Unit

  def toJson: JsValue

}

class FieldChanged extends Event
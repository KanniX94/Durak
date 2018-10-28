package de.htwg.se.durak.controller.controllerComponent

trait ControllerInterface {
  def saveGame(): Unit


  def initialize()

  //def doGameAction(field: FieldInterface, key: String): Unit

  //def doAction(field: FieldInterface, key: String): Unit

  //def toJson: JsValue

}
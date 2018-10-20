package de.htwg.se.durak.model.FieldComponent.FieldBaseImpl

case class Card(name: String, value: Integer, symbol: String) {
  def isSet: Boolean = value != 0
  override def toString: String = name
}

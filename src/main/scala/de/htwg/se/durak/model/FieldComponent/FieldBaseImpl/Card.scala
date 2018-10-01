package de.htwg.se.durak.model.FieldComponent.FieldBaseImpl

import de.htwg.se.durak.model.CardInterface

case class Card(name: String, value: Integer, symbol: String) {
  def isSet: Boolean = value != 0

  override def toString: String = name
}

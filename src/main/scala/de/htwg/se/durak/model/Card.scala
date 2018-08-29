package de.htwg.se.durak.model.CardInterface
import de.htwg.se.durak.model.{CardInterface, Item}

case class Card(name: String, v: Integer, s: String) extends CardInterface {
  value = v
  symbol = s
}

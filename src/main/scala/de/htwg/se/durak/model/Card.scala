package de.htwg.se.durak.model.CardInterface
import de.htwg.se.durak.model.{CardInterface, Item}

case class Card(name: String, v: Integer, s: String) extends CardInterface {
  value = v // Wert der Karte (von 7 bis 14)
  symbol = s // Piek, Kreuz, Caro, Herz
}

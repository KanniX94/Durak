package de.htwg.se.durak.model.CardInterface
import de.htwg.se.durak.model.{CardInterface, Item}

case class Card(name: String, v: Integer, s: String) extends CardInterface {
  value = v
  symbol = s

  def takeCard(item: Item): Item = {
    //Karte ziehen, bis alle Spieler 6 Karten auf der Hand haben
    // Oder lieber vom Player aus steuern?
    // ...
    return null
  }

  def dropCard(item: Item): Item = {
    return null
  }
}

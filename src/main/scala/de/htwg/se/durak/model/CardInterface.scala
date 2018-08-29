package de.htwg.se.durak.model

trait CardInterface extends Item {
  var value: Int = 0
  var symbol: String = ""

  def takeCard(item: Item): Item
  def dropCard(item: Item): Item
}

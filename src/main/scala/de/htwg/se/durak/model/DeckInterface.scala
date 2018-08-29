package de.htwg.se.durak.model

trait DeckInterface[Item] {
  def dealOut(): Item
}
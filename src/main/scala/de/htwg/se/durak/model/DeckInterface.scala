package de.htwg.se.durak.model

trait DeckInterface[Card] {
  // Karten an Spieler austeilen (am Anfang eines Spiels)
  def dealOut(): Item
  // Karten werden vom Spieler aufgezogen (bis 6 Stueck)
  // --> Evtl auslagern in Player, da Spieler Karten aufzieht
  def takeCard(item: Item): Item
  // Karte wird beim Austeilen an Spieler aus dem Deck entfernt
  def dropCard(item: Item): Item
}
package de.htwg.se.durak.controller

import de.htwg.se.durak.model.exactImpl._
import de.htwg.se.durak.model._

import scala.collection.mutable.ArrayBuffer
import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  var playerInGame: Array[PlayerInterface] = null
  var playerName: Array[String] = Array("Marcel", "Christoph", "Bernd", "Simone")
  var amountOfPlayer = 0
  var actualPlayer: PlayerInterface = null

  var deck: exactImpl.Deck = null
  var trumpCard: Card
  var cardOnField: ArrayBuffer[Card] = null
  var beatenCard: ArrayBuffer[Card] = null

  var difficulty = 0
  var cardsLeft = 0

  def initialize(amountOfPlayer: Int)
  def setDifficulty(dif: Int)

  // Sind alle Karten auf dem Spielfeld gleich?
  def canPushCard(cardOnField: ArrayBuffer[Card], cardFromHand: Card): Boolean

  // Kann aktuelle Karte geschlagen werden?
  def canBeatCard(cardFromField: Card, cardFromHand: Card): Boolean

  // Kann ein Gegner seine Karte dazu legen?
  def canLayCard(cardOnField: ArrayBuffer[Card], cardFromHand: Card): Boolean


}

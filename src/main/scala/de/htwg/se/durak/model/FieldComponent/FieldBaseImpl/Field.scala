package de.htwg.se.durak.model.FieldComponent.FieldBaseImpl

import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.model.PlayerInterface

import scala.collection.mutable.ArrayBuffer
import scala.xml.{Elem, Node}

class Field extends FieldInterface {
  def toXml: Elem = {
    return <Field>
      <playerInGame>
        <player>
          {serializePlayer(this.playerInGame)}
        </player>
      </playerInGame>
      <actualPlayer>
        {this.actualPlayer}
      </actualPlayer>
      <playerCardOnHand>
        <card>
          {serializeCards(this.playerCardOnHand)}
        </card>
      </playerCardOnHand>
      <enemyCardOnHand>
        <card>
          {serializeCards(this.enemyCardOnHand)}
        </card>
      </enemyCardOnHand>
      <deck>
        <card>
          {serializeCards(this.deck)}
        </card>
      </deck>
      <cardOnField>
        <card>
          {serializeCards(this.cardOnField)}
        </card>
      </cardOnField>
    </Field>
  }

  def serializePlayer(playerInGame: Array[Player]): String = {
    val stringBuilder = new StringBuilder

    for (i <- 0 until playerInGame.length) {
      stringBuilder.append(playerInGame(i).toString)
      if (i != (playerInGame.length - 1)) {
        stringBuilder.append(",")
      }
    }
    stringBuilder.toString()
  }

  def serializeCards(cards: ArrayBuffer[Card]): String = {
    val stringBuilder = new StringBuilder

    for (i <- 0 until cards.length) {
      stringBuilder.append(cards(i).toString)
      if (i != (cards.length - 1)) {
        stringBuilder.append(",")
      }
    }
    stringBuilder.toString()
  }

  def fromXml(node: Node): Unit = {
    this.playerCardOnHand = new ArrayBuffer[Card]()
    this.playerCardOnHand = deserializeCards((node \ "playerCardOnHand").text)

    this.enemyCardOnHand = new ArrayBuffer[Card]()
    this.enemyCardOnHand = deserializeCards((node \ "enemyCardOnHand").text)

    this.deck = new ArrayBuffer[Card]()
    this.deck = deserializeCards((node \ "deck").text)

    this.cardOnField = new ArrayBuffer[Card]()
    this.cardOnField = deserializeCards((node \ "cardOnField").text)

    this.playerInGame = new Array[Player](2)
    this.playerInGame = deserializePlayer((node \ "playerInGame").text)

    this.actualPlayer = Player((node \ "actualPlayer").text)
  }

  def deserializeCards(cards: String): ArrayBuffer[Card] = {
    val splitCards = cards.split(",")
    val splitValues = splitCards(0).split(" ")

    val tmpCards = new ArrayBuffer[Card](splitCards.length)
    for (i <- 0 until splitCards.length) {
      val card = Card(splitValues(0) + " " + splitValues(1), splitValues(0).toInt, splitValues(1).apply(0).toString)
      tmpCards += card
    }
    tmpCards
  }

  def deserializePlayer(player: String): Array[Player] = {
    val splitPlayer = player.split(",")

    val tmpPlayer = new Array[Player](splitPlayer.length)
    for (i <- 0 until splitPlayer.length) {
      tmpPlayer(i) = Player(splitPlayer(i))
    }
    tmpPlayer
  }

  override var playerCardOnHand: ArrayBuffer[Card] = ArrayBuffer.empty[Card]
  override var enemyCardOnHand: ArrayBuffer[Card] = ArrayBuffer.empty[Card]
  override var deck: ArrayBuffer[Card] = ArrayBuffer.empty[Card]
  override var cardOnField: ArrayBuffer[Card] = ArrayBuffer.empty[Card]
  override var actualPlayer: Player = _
  override var playerInGame = new Array[Player](2)
  override var win: Boolean = _
  override var lose: Boolean = _
}

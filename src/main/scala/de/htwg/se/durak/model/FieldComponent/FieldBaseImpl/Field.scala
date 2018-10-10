package de.htwg.se.durak.model.FieldComponent.FieldBaseImpl

import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.model.PlayerInterface

import scala.collection.mutable.ArrayBuffer
import scala.xml.{Elem, Node}

class Field extends FieldInterface {
  playerCardOnHand = ArrayBuffer[Card]()
  enemyCardOnHand = ArrayBuffer[Card]()
  deck = ArrayBuffer[Card]()
  cardOnField = ArrayBuffer[Card]()
  playerInGame = Array[PlayerInterface]()
  actualPlayer

  def createNewGame: Unit = {
    playerCardOnHand = ArrayBuffer[Card]()
    enemyCardOnHand = ArrayBuffer[Card]()
    deck = ArrayBuffer[Card]()
    cardOnField = ArrayBuffer[Card]()
  }

  def toXml: Elem = {
    <Field>
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

  def serializePlayer(playerInGame: Array[PlayerInterface]): String = {
    val stringBuilder = new StringBuilder

    for (i <- playerInGame.indices) {
      stringBuilder.append(playerInGame(i).toString)
      if (i != (playerInGame.length - 1)) {
        stringBuilder.append(",")
      }
    }
    stringBuilder.toString()
  }

  def serializeCards(cards: ArrayBuffer[Card]): String = {
    val stringBuilder = new StringBuilder

    for (i <- cards.indices) {
      stringBuilder.append(cards(i).toString())
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

    this.playerInGame = new Array[PlayerInterface](2)
    this.playerInGame = deserializePlayer((node \ "playerInGame").text)

    this.actualPlayer = new Player((node \ "actualPlayer").text)
  }

  def deserializeCards(cards: String): ArrayBuffer[Card] = {
    val splitCards = cards.split(",")
    val splitValues = splitCards.toString.split(" ")

    val tmpCards = new ArrayBuffer[Card](splitCards.length)
    for (i <- splitCards.indices) {
      tmpCards(i) = Card(splitValues(0) + " " + splitValues(1), splitValues(0).toInt, splitValues(1))
    }
    tmpCards
  }

  def deserializePlayer(player: String): Array[PlayerInterface] = {
    val splitPlayer = player.split(",")

    val tmpPlayer = new Array[PlayerInterface](splitPlayer.length)
    for (i <- splitPlayer.indices) {
      tmpPlayer(i) = Player(tmpPlayer(i).toString)
    }
    tmpPlayer
  }
}

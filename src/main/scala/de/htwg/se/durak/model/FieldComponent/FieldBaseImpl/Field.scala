package de.htwg.se.durak.model.FieldComponent.FieldBaseImpl

import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.model.PlayerInterface

import scala.collection.mutable.ArrayBuffer
import scala.xml.{Elem, Node}

class Field(pH: ArrayBuffer[Card],eH: ArrayBuffer[Card], d: ArrayBuffer[Card], cF: ArrayBuffer[Card], pG: Array[Player], pA: Player) extends FieldInterface {

  def toXml: Elem = {
    <Field>
      <playerInGame>
        <player>
          {serializePlayer(pG)}
        </player>
      </playerInGame>
      <actualPlayer>
        {pA}
      </actualPlayer>
      <playerCardOnHand>
        <card>
          {serializeCards(pH)}
        </card>
      </playerCardOnHand>
      <enemyCardOnHand>
        <card>
          {serializeCards(eH)}
        </card>
      </enemyCardOnHand>
      <deck>
        <card>
          {serializeCards(d)}
        </card>
      </deck>
      <cardOnField>
        <card>
          {serializeCards(cF)}
        </card>
      </cardOnField>
    </Field>
  }

  def serializePlayer(playerInGame: Array[Player]): String = {
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

    this.playerInGame = new Array[Player](2)
    this.playerInGame = deserializePlayer((node \ "playerInGame").text)

    this.actualPlayer = new Player((node \ "actualPlayer").text)
    returnToController()
  }

  def returnToController(): Unit = {

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

  def deserializePlayer(player: String): Array[Player] = {
    val splitPlayer = player.split(",")

    val tmpPlayer = new Array[Player](splitPlayer.length)
    for (i <- splitPlayer.indices) {
      tmpPlayer(i) = Player(tmpPlayer(i).toString)
    }
    tmpPlayer
  }

  override var win: Boolean = _
  override var lose: Boolean = _
  override var playerCardOnHand: ArrayBuffer[Card] = _
  override var enemyCardOnHand: ArrayBuffer[Card] = _
  override var deck: ArrayBuffer[Card] = _
  override var cardOnField: ArrayBuffer[Card] = _
  override var actualPlayer: Player = _
  override var playerInGame: Array[Player] = _
}

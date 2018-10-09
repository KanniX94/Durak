package de.htwg.se.durak.model.FieldComponent.FieldBaseImpl

import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.model.FieldComponent.PlayerInterface

import scala.collection.mutable.ArrayBuffer
import scala.xml.{Elem, Node}

class Field extends FieldInterface {
  amountOfPlayer = 2
  players = ArrayBuffer[PlayerInterface]()
  field = new Field

  def createNewGame: Unit = {
    amountOfPlayer = 2
    players = ArrayBuffer[PlayerInterface]()
    field = new Field
  }

  def toXml: Elem = {
    return <game>
    <field>{serializeTiles(this.field.tiles)}</field>
    </game>
  }

  def serializeTiles(tiles: Array[Tile]): String = {
    val stringBuilder = new StringBuilder

    for (i <- tiles.indices) {
      stringBuilder.append(tiles(i).value)
      if (i != (tiles.length - 1)) {
        stringBuilder.append(",")
      }
    }
    stringBuilder.toString()
  }

  def fromXml(node: Node): Unit = {
    val playerCardOnHand = new ArrayBuffer[Card]()
    playerCardOnHand = (node).text.toString

    val enemyCardOnHand = new ArrayBuffer[Card]()
    enemyCardOnHand = (node).text.toString

    val deck = new ArrayBuffer[Card]()
    deck = (node).text.toString

  }
}

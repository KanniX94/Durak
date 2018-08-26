package de.htwg.se.durak.model
import scala.collection.mutable.ArrayBuffer
import de.htwg.se.durak.model.{ PlayerInterface, Card, Player}

case class Player(name: String) extends PlayerInterface {
   override def toString:String = name

   cardOnHand = ArrayBuffer[Card]()
   def win(): String = {
      return s"$name hat gewonnen und verlaesst das Spiel!"
   }

   def loose(): String = {
      return s"$name ist ein Durak!"
   }
}


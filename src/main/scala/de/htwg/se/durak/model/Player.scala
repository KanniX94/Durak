package de.htwg.se.durak.model
import scala.collection.mutable.ArrayBuffer
import de.htwg.se.durak.model.{ PlayerInterface, Item, Player}

case class Player(name: String) extends PlayerInterface {
   override def toString:String = name

   cardOnHand = ArrayBuffer[Item]()
   def win(): String = {
      return s"$name hat gewonnen und verlaesst das Spiel!"
   }

   def loose(): String = {
      return s"$name ist ein Durak!"
   }

   def pushCard(card: Card): Card = {
      //Was passiert, wenn Karte geschoben wurde?
      // ...
     for (i <- 0 to cardOnHand.length - 1) {
       if (cardOnHand(i).name == card.name) {

       }
     }

      dropCard(card)
      return null
   }

   def beatCard(card: Card): Card = {
      //Was passiert, wenn Karte geschlagen wurde?
      // ...

      dropCard(card)
      return null
   }

   def dropCard(card: Card): Card = {
      for (i <- 0 to cardOnHand.length - 1) {
         if (cardOnHand(i).name == card.name) {
            val tmpCard = cardOnHand(i)
            cardOnHand.remove(i)
            return tmpCard
         }
      }
      return null
   }
}


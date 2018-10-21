package de.htwg.se.durak.aview.gui

import java.awt.{BorderLayout, GridLayout}
import java.awt.event.{WindowAdapter, WindowEvent}

import de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.Card
import javax.swing._

import scala.collection.mutable.ArrayBuffer
import scala.swing.Dimension

class Gui() extends JFrame {

  val defaultSize: (Int, Int) = (1024, 768)

  // Layout
  val panel = new JPanel()
  val label = new JLabel("Hallo")
  val enemy = new EnemyFieldGui()
  val deck = new DeckFieldGui()
  val player = new PlayerFieldGui
  var counter = 1

  panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS))
  panel.add(enemy)
  panel.add(deck)
  panel.add(player)

  setContentPane(panel)

  setTitle("Durak")
  setMinimumSize(new Dimension(defaultSize._1, defaultSize._2))
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)


  pack()
  setVisible(true)

  def displayHand(cards: ArrayBuffer[Card], enemyCards: ArrayBuffer[Card]): Unit = {
    for(card <- cards) {
      player.labelArray(counter - 1).setIcon(new ImageIcon("C:/Users/chris/IdeaProjects/Durak/media/" + card.name + ".png"))
      counter += 1
    }
    counter = 1
    for(card <- enemyCards) {
      enemy.labelArray(counter - 1).setIcon(new ImageIcon("C:/Users/chris/IdeaProjects/Durak/media/" + card.name + ".png"))
      counter += 1
    }
    counter = 1
  }


  // Methods
  def close(): Unit = {
    setVisible(false)
    dispose()
  }

}

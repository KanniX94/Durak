package de.htwg.se.durak.aview.gui

import javax.swing._

import scala.collection.mutable.ArrayBuffer

class PlayerFieldGui extends JPanel{
  val playerPanel = new JPanel()

  var label1 = new JLabel()
  var label2 = new JLabel()
  var label3 = new JLabel()
  var label5 = new JLabel()
  var label4 = new JLabel()
  var label6 = new JLabel()
  var label7 = new JLabel()
  var label8 = new JLabel()
  var label9 = new JLabel()
  var label10 = new JLabel()

  var labelArray: ArrayBuffer[JLabel] = new ArrayBuffer[JLabel]()

  fillLabel()

  def fillLabel(): Unit = {
    labelArray += label1
    labelArray += label2
    labelArray += label3
    labelArray += label4
    labelArray += label5
    labelArray += label6
    labelArray += label7
    labelArray += label8
    labelArray += label9
    labelArray += label10
  }
  playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS))
  playerPanel.setBorder(BorderFactory.createEtchedBorder())
  playerPanel.add(label1)
  playerPanel.add(label2)
  playerPanel.add(label3)
  playerPanel.add(label4)
  playerPanel.add(label5)
  playerPanel.add(label6)
  playerPanel.add(label7)
  playerPanel.add(label8)
  playerPanel.add(label9)
  playerPanel.add(label10)

  add(playerPanel)
}

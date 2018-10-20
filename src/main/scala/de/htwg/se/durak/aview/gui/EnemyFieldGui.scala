package de.htwg.se.durak.aview.gui

import javax.swing._

import scala.collection.mutable.ArrayBuffer

class EnemyFieldGui extends JPanel{
  val enemyPanel = new JPanel()
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

  enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.X_AXIS))
  enemyPanel.setBorder(BorderFactory.createEtchedBorder())
  enemyPanel.add(label1)
  enemyPanel.add(label2)
  enemyPanel.add(label3)
  enemyPanel.add(label4)
  enemyPanel.add(label5)
  enemyPanel.add(label6)
  enemyPanel.add(label7)
  enemyPanel.add(label8)
  enemyPanel.add(label9)
  enemyPanel.add(label10)
  enemyPanel.setVisible(true)

  add(enemyPanel)
}

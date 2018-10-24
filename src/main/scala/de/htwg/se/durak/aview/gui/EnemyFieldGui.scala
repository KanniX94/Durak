package de.htwg.se.durak.aview.gui

import javax.swing._

class EnemyFieldGui extends JPanel with FieldGuiLabel{
  val enemyPanel = new JPanel()

  //var labelArray: ArrayBuffer[JLabel] = new ArrayBuffer[JLabel]()

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
  enemyPanel.add(label11)
  enemyPanel.add(label12)
  enemyPanel.add(label13)
  enemyPanel.add(label14)
  enemyPanel.add(label15)
  enemyPanel.add(label16)
  enemyPanel.add(label17)
  enemyPanel.add(label18)
  enemyPanel.add(label19)
  enemyPanel.add(label20)
  enemyPanel.add(label21)
  enemyPanel.add(label22)
  enemyPanel.add(label23)
  enemyPanel.add(label24)
  enemyPanel.add(label25)
  enemyPanel.add(label26)
  enemyPanel.add(label27)
  enemyPanel.add(label28)
  enemyPanel.add(label29)
  enemyPanel.add(label30)
  enemyPanel.add(label31)
  enemyPanel.add(label32)
  enemyPanel.setVisible(true)

  add(enemyPanel)
}

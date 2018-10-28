package de.htwg.se.durak.aview.gui

import java.awt.GridLayout
import javax.swing._

class DeckFieldGui extends JPanel with FieldGuiLabel {

  val deckPanel = new JPanel()
  deckPanel.setLayout(new GridLayout(1, 4))
  setBorder(BorderFactory.createEtchedBorder())

  var deckLabel = new JLabel("Deck: ")
  var deckBackLabel = new JLabel(new ImageIcon("C:/Users/marce/durakGame/media/back.png"))
  var trumpLabel = new JLabel(new ImageIcon("C:/Users/marce/durakGame/media/back.png"))
  deckPanel.add(deckLabel)
  deckPanel.add(deckBackLabel)
  deckPanel.add(trumpLabel)
  add(deckPanel)
}

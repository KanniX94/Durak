package de.htwg.se.durak.aview.gui

import java.awt.GridLayout
import javax.swing.{BorderFactory, JPanel}

class DeckFieldGui extends JPanel{
  val deckPanel = new JPanel()
  setLayout(new GridLayout(1, 3))
  setBorder(BorderFactory.createEtchedBorder())

  add(deckPanel)
}

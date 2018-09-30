package de.htwg.se.durak.controller.controllerComponent

class GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, NEW, UNDO, REDO, LOADED, COULD_NOT_LOAD, SAVED,
  COULT_NOT_SAVE, DIFFICULTY, PLAYER, ATTACK, PUSH, PULL, BEAT = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    NEW -> "Ein neues Spiel wird gestartet",
    UNDO -> "Einen Schritt zurueck",
    REDO -> "Einen Schritt vor",
    LOADED -> "Ein Spielstand wurde geladen",
    COULD_NOT_LOAD -> "Die Datei konnte nicht geladen werden",
    SAVED -> "Das Spiel wurde gespeichert",
    COULT_NOT_SAVE -> "Das Spiel konnte nicht gespeichert werden",
    ATTACK -> "Spieler wird angegriffen",
    PUSH -> "Karten werden weiter geschoben",
    PULL -> "Karten werden aufgenommen",
    DIFFICULTY -> "Schwierigkeitsgrad wurde festgelegt",
    PLAYER -> "Spieleranzahl wurde festgelegt"
  )

  def message(gameStatus: GameStatus): Unit = {
    map(gameStatus)
  }
}

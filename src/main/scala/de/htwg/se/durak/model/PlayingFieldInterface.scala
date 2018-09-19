package de.htwg.se.durak.model

trait PlayingFieldInterface {
  val x: Int = 0
  val y: Int = 0
  val length: Int
  val width: Int
  var line: Array[Array[FieldInterface]] = null
}

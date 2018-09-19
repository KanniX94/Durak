package de.htwg.se.durak.model.exactImpl

import de.htwg.se.durak.model.{FieldInterface, PlayingFieldInterface}

case class PlayingField(length: Int, width: Int) extends PlayingFieldInterface {
  line = Array.ofDim[FieldInterface](length, width)
  val field: Field = Field(Position(0, 0))
  val step: Int = field.length

  for (i <- 0 to length -1) {
    for (j <- 0 to width -1) {
      line(j)(i) = Field(Position(x + step * j, y + step * i))
    }
  }
}

package de.htwg.se.durak.model.fileIoComponent

import de.htwg.se.durak.model.FieldComponent.FieldInterface

import scala.util.Try

trait FileIoInterface {
  def load: Try[Option[FieldInterface]]

  def save(field: FieldInterface): Try[Unit]
}

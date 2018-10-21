package de.htwg.se.durak.model.fileIoComponent

import de.htwg.se.durak.model.FieldComponent.FieldInterface

trait FileIoInterface {
  def save(filename: String, field: FieldInterface)

  def load(filename: String, field: FieldInterface)}

package de.htwg.se.durak.model.fileIoComponent.fileIoXmlImpl

import java.io.{BufferedWriter, File, FileWriter}

import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.model.fileIoComponent.FileIoInterface

import scala.io.Source
import scala.xml.XML

class FileIo extends FileIoInterface {
  def save(filename: String, field: FieldInterface): Unit = {
    val file = new File(filename + ".xml")
    val bufferdWriter = new BufferedWriter(new FileWriter(file))
    bufferdWriter.write(field.toXml.toString)
    bufferdWriter.close()
  }

  def load(filename: String, field: FieldInterface): Unit = {
    val source = Source.fromFile(filename + ".xml")
    val lines = try source.mkString finally source.close()

    field.fromXml(XML.loadString(lines))
  }

}

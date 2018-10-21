package de.htwg.se.durak

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.durak.controller.controllerComponent._
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.{Deck, Field}
import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.model.fileIoComponent.{FileIoInterface,  fileIoXmlImpl}
//import de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl.FileIo
//import de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl

class durakGameModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[FieldInterface].to[Field]
   // bind[FieldInterface].annotatedWithName("Feld").toInstance(new Field(new Deck))
    //bind[FileIoInterface].to[fileIoJsonImpl.FileIo]
  }
}

package de.htwg.se.durak

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.Field
import de.htwg.se.durak.model.FieldComponent.FieldInterface

class durakGameModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[FieldInterface].to[Field]
    // bind[FieldInterface].annotatedWithName("Feld").toInstance(new Field(new Deck))
    //bind[FileIoInterface].to[fileIoJsonImpl.FileIo]
  }
}

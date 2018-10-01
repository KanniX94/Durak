package de.htwg.se.durak

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.durak.controller.controllerComponent._
import de.htwg.se.durak.model.fileIoComponent._

class durakGameModule extends AbstractModule with ScalaModule {
  def configure(): Unit = {
    
  }

}

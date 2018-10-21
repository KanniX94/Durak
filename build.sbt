name := "durak"
organization := "de.htwg.se"
version := "0.0.1"
scalaVersion := "2.12.1"

//libraryDependencies += "org.scala-lang" % "scala-swing" % "2.12.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

libraryDependencies += "junit" % "junit" % "4.8" % "test"

// https://mvnrepository.com/artifact/com.google.inject/guice
//libraryDependencies += "com.google.inject" % "guice" % "4.2.1"

// https://mvnrepository.com/artifact/net.codingwell/scala-guice
//libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.1"

// https://mvnrepository.com/artifact/org.scala-lang.modules/scala-xml
//libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.6"

// https://mvnrepository.com/artifact/com.typesafe.play/play-json
//libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.0-M1"

// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

// https://mvnrepository.com/artifact/com.typesafe.scala-logging/scala-logging-slf4j
//libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"


//*******************************************************************************//
//Libraries that we will use in later lectures compatible with this scala version
// uncomment to use!!

libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.1"

libraryDependencies += "com.google.inject" % "guice" % "4.1.0"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.5"

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.5")
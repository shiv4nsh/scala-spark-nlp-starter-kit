name := "scala-spark-nlp-starter-kit"


val spark = "org.apache.spark" %% "spark-core" % "2.0.0"
val spark_sql = "org.apache.spark" %% "spark-sql" % "2.0.0"
val spark_mllib = "org.apache.spark" %% "spark-mllib" % "2.0.0"
val pdfbox = "org.apache.pdfbox" % "pdfbox" % "2.0.2"
val coreNlp = "edu.stanford.nlp" % "stanford-corenlp" % "3.6.0"

val protobufjava = "com.google.protobuf" % "protobuf-java" % "2.6.1"
val corenlpmodel = "edu.stanford.nlp" % "stanford-corenlp" % "3.6.0" classifier "models"


lazy val commonSettings = Seq(
  organization := "com.knoldus",
  version := "0.1.0",
  scalaVersion := "2.11.8",
  resolvers += Resolver.mavenLocal
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(spark, spark_sql, pdfbox, coreNlp, spark_mllib,corenlpmodel,protobufjava)
  )

assembleArtifact in assemblyPackageScala := false // We don't need the Scala library, Spark already includes it

assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$") => MergeStrategy.discard
  case "reference.conf" => MergeStrategy.concat
  case _ => MergeStrategy.first
}

spName := "knoldus/scala-spark-nlp-starter-kit"

sparkVersion := "2.0.0"

sparkComponents += "sql"

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

fork in run := true
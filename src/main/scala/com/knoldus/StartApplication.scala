package com.knoldus

import edu.stanford.nlp.simple.{Document, Sentence}
import edu.stanford.nlp.util.Quadruple
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.functions.udf

import scala.collection.JavaConverters._


private case class OpenIE(subject: String, relation: String, target: String, confidence: Double) {
  def this(quadruple: Quadruple[String, String, String, java.lang.Double]) =
    this(quadruple.first, quadruple.second, quadruple.third, quadruple.fourth)
}

object StartApplication extends App{

  val spark = SparkSession.builder().appName("spark-nlp-starter").master("local[*]").getOrCreate()
  val sc = spark.sparkContext
  val readPdfFile: Dataset[String] = spark.read.textFile("test")
  readPdfFile.show(false)

  def openie = udf { sentence: String =>
    new Sentence(sentence).openie().asScala.map(q => new OpenIE(q)).toSeq
  }

  val res = readPdfFile.select(openie(readPdfFile("value")))
  res.show(false)
}

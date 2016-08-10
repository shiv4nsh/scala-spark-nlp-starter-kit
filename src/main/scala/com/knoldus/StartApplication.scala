package com.knoldus

import java.lang.Double
import java.util

import edu.stanford.nlp.naturalli.OpenIE
import edu.stanford.nlp.simple.Sentence
import edu.stanford.nlp.util.Quadruple
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.functions._
import scala.collection.JavaConverters._

/**
  * Created by shivansh on 9/8/16.
  */
object StartApplication {

  val spark = SparkSession.builder().appName("spark-nlp-starter").getOrCreate()
  val sc = spark.sparkContext
  val readPdfFile: Dataset[String] = spark.read.textFile("LICENSE")
  readPdfFile.show()

  val stringToTriples = (sentence: String) => {
    val sentences = new Sentence(sentence).openie().asScala.toList
    sentences.map(q => new OpenIE(q)).toSeq
  }

  def openie = udf(stringToTriples _)
}

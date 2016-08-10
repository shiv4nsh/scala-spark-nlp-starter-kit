package com.knoldus

import org.apache.spark.sql.SparkSession

/**
  * Created by shivansh on 9/8/16.
  */
object StartApplication {

  val spark = SparkSession.builder().appName("spark-nlp-starter").getOrCreate()
  val sc = spark.sparkContext
  val readPdfFile = spark.read.textFile("/home/shivansh/Documents/Elsevier_Preparation/1608.02193.pdf")
  readPdfFile.show()

}

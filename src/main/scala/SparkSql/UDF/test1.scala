package SparkSql.UDF

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object test1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("test1")
      .setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder()
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()
    spark.udf.register("addName",(x:String) => "Name:"+x)
    val df1: DataFrame = spark.read.json("E:\\test1.json")
    df1.show()
    df1.createOrReplaceTempView("df1")
    spark.sql(
      s"""
         |select addName(username) as name,age
         |from df1
         |""".stripMargin).show()

  }

}

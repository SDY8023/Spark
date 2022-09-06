package SparkCore

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val conf = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    // 创建Spark上下文环境对象(连接对象)
    val spark = new SparkContext(conf)
    // 读取文件数据
    val fileRDD: RDD[String] = spark.textFile("D:\\study\\studyFile\\test1.txt")
    // 将文件中的数据分词
    val wordRDD: RDD[String] = fileRDD.flatMap(_.split(" "))
    wordRDD.map(x => (x,1)).reduceByKey(_+_).foreach(println)
    spark.stop()
  }

}

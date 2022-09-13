package SparkCore.RDD

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName: transfunctionRDD
 * @Auther: SDY
 * @Description: 转换算子
 * @Date: 2022/9/13 22:36
 * */
object TransFunctionRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("createRDD")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
//    testMap(sc)
    testMapPartitions(sc)

  }

  def testMap(sc:SparkContext): Unit ={
    // map
    val mapRDD = sc.makeRDD(List(1, 3, 4, 5))
    val mapRDD2 = mapRDD.map(_ * 2)
    mapRDD2.foreach(println)
    val mapRDD3 = mapRDD.map(" " + _)
    mapRDD3.foreach(println)
  }

  def testMapPartitions(sc:SparkContext): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),3)
    rdd1.mapPartitions( x => {
      val max = x.max
      Array(max).toIterator
    }).foreach(println)
    rdd1.mapPartitions(_.filter(_ == 2)).foreach(println)
  }

}

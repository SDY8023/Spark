package SparkCore.RDD

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName: ActionFuntionRDD
 * @Auther: SDY
 * @Description:
 * @Date: 2022/9/25 16:29
 * */
object ActionFunctionRDD {
  var sc: SparkContext = null
  def testReduce(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4))
    val result = rdd1.reduce(_ + _)
    println(result)
  }

  def testCollect(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))
    rdd1.collect().foreach(println)
    println(rdd1.collect().toList)
  }

  def testTakeOrdered(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 8,3, 9,4, 5,6))
    val result: Array[Int] = rdd1.takeOrdered(3)
    println(result.toList)
  }

  def testAggregate(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 8,3, 9,4, 5,6),5)
    // 先分区内的数据和初始值相加，在分区间的数据和初始值相加
    val r1 = rdd1.aggregate(0)(_ + _, _ + _)
    val r2 = rdd1.aggregate(10)(_ + _, _ + _)
    println(r1,r2)
  }

  def testFold(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 8,3, 9,4, 5,6),5)
    val result: Int = rdd1.fold(5)(_ + _)
    println(result)
  }

  def testCountByKey(): Unit ={
    val rdd1 = sc.makeRDD(List((1, "a"), (1, "b"), (1, "c"), (2, "d"), (2, "e"), (3, "a"), (3, "b")))
    val result: collection.Map[Int, Long] = rdd1.countByKey()
    println(result)
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
      .setAppName("transFunctionRDD")
    sc = new SparkContext(conf)
    //testReduce()
    //testCollect()
    //testTakeOrdered()
    //testAggregate()
    //testFold()
    testCountByKey()
  }

}

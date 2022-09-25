package SparkCore.RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * @ClassName: transfunctionRDD
 * @Auther: SDY
 * @Description: 转换算子
 * @Date: 2022/9/13 22:36
 * */
object TransFunctionRDD {
  var sc: SparkContext = null

  def testMap(): Unit ={
    // map
    val mapRDD = sc.makeRDD(List(1, 3, 4, 5))
    val mapRDD2 = mapRDD.map(_ * 2)
    mapRDD2.foreach(println)
    val mapRDD3 = mapRDD.map(" " + _)
    mapRDD3.foreach(println)
  }

  def testMapPartitions(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),3)
    rdd1.mapPartitions( x => {
      val max = x.max
      Array(max).toIterator
    }).foreach(println)
    rdd1.mapPartitions(_.filter(_ == 2)).foreach(println)
  }

  def testMapPartitionsWithIndex(sc:SparkContext): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),3)

    rdd1.mapPartitionsWithIndex((index,data) =>{
      data.map((index,_))
    }).foreach(println)
    println("===========获取第二个分区的数据=========")
    rdd1.mapPartitionsWithIndex((index,data) => {
      if(index == 1){
        data
      }else{
        Nil.iterator
      }
    }).foreach(println)
  }

  def testFlatMap(): Unit ={
    val rdd1 = sc.makeRDD(List(List(1, 2, 3),List(4, 5, 6, 7, 8), 9, 10),3)

    /**
     * 思路：因为该RDD中的数据类型是Any,所以需要判度每个数据的类型，然后将他们转为统一类型
     */
    rdd1.foreach(println)
    rdd1.flatMap(x => {
      if(x.isInstanceOf[List[Int]]){
        x.asInstanceOf[List[Int]]
      }else{
        List(x)
      }
    }).foreach(println)

  }

  def testGlom(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4,5,6,7,8,9,10),3)

    println(rdd1.glom().map(x => x.max).collect().sum)

    println(rdd1.aggregate(0 )(
      (x,y) => math.max(x,y), // 分区内计算规则
      (a,b) => a + b // 分区间计算规则
    ))
  }

  def testGroupBy(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 1)
    rdd1.groupBy(_%2).mapPartitionsWithIndex((i,d) => d.map((i,_))).foreach(println)

    val rdd2 = sc.makeRDD(List("Hello", "hive", "hbase", "Hadoop"))
    rdd2.groupBy(_.take(1)).foreach(println)
  }

  def testFilter(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4))
    rdd1.filter(_ % 2 == 0).foreach(println)
  }

  def testSample(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 1)
    // 抽取数据不放回（伯努利算法）
    // 伯努利算法：又叫 0、1 分布。例如扔硬币，要么正面，要么反面。
    // 具体实现：根据种子和随机算法算出一个数和第二个参数设置几率比较，小于第二个参数要，大于不要
    // 第一个参数：抽取的数据是否放回，false：不放回
    // 第二个参数：抽取的几率，范围在[0,1]之间,0：全不取；1：全取；
    // 第三个参数：随机数种子
    rdd1.sample(false,0.5).foreach(println)
    println("====================================================")
    // 抽取数据放回（泊松算法）
    // 第一个参数：抽取的数据是否放回，true：放回；false：不放回
    // 第二个参数：重复数据的几率，范围大于等于 0.表示每一个元素被期望抽取到的次数
    // 第三个参数：随机数种子
    rdd1.sample(true,2).foreach(println)
  }

  def testDistinct(): Unit ={
    val rdd1 = sc.makeRDD(List(1,3, 2,2, 3, 4, 5, 6, 7, 8, 9, 10), 1)
//    rdd1.distinct(12).foreach(println)
    rdd1.map(x => (x,1))
      .reduceByKey((x,y) => x,2).foreach(println)
      //.map(_._1).foreach(println)
  }

  def testCoalesce(): Unit ={
    val rdd1 = sc.makeRDD(List(1,3, 2,2, 3, 4, 5, 6, 7, 8, 9, 10), 5)
    rdd1.repartition(10).mapPartitionsWithIndex((p,x) => {
      x.map(v => (v,p))
    }).foreach(println)
  }

  def testSortBy(): Unit ={
    val rdd1 = sc.makeRDD(List(1,3, 2,2, 3, 4, 5, 6, 7, 8, 9, 10), 5)
    rdd1.sortBy(x => x,false,2)
      .mapPartitionsWithIndex((p,x) => {
        x.map((_,p))
      })
      .foreach(println)
  }

  def testIntersection(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))
    val rdd2 = sc.makeRDD(List(2, 3, 7, 8, 9))
    val rdd3 = sc.makeRDD(List[String]("2", "3", "7","8", "9"))
    rdd1.intersection(rdd2).foreach(println)
//    rdd1.intersection(rdd3).foreach(println)
  }

  def testUnion(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))
    val rdd2 = sc.makeRDD(List(2, 3, 7, 8, 9))
    val rdd3 = sc.makeRDD(List[String]("2", "3", "7","8", "9"))
    rdd1.union(rdd2).foreach(println)
    //rdd1.union(rdd3)
  }

  def testSubtract(): Unit ={
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))
    val rdd2 = sc.makeRDD(List(2, 3, 7, 8, 9))
    rdd1.subtract(rdd2).foreach(println)
  }

  def testZip(): Unit ={
    // 分区一致,元素类型一致,元素数量一致
    println("===分区一致,元素类型一致,元素数量一致===")
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))
    val rdd2 = sc.makeRDD(List(2, 3, 7, 8, 9))
    rdd1.zip(rdd2).foreach(println)
    // 分区不一致,元素类型一致,元素数量一致
    println("===分区不一致,元素类型一致,元素数量一致===")
    val rdd3 = sc.makeRDD(List(1, 2, 3, 4, 5),2)
    val rdd4 = sc.makeRDD(List(2, 3, 7, 8, 9),4)
    rdd3.zip(rdd4).foreach(println)
    // 分区一致,元素类型一致,元素数量不一致
    println("===分区一致,元素类型一致,元素数量不一致===")
    val rdd5 = sc.makeRDD(List(1, 2, 3, 4, 5,6),4)
    val rdd6 = sc.makeRDD(List(2, 3, 7, 8, 9),4)
    rdd5.zip(rdd6).foreach(println)
  }

  def testPartitionBy(): Unit ={
    import org.apache.spark._
    val rdd1 = sc.makeRDD(Array((1, "aa"), (2, "bb"), (3, "cc"), (4, "dd"),(4, "dd")))
    // 使用hashPartitioner
    val rdd2: RDD[(Int, String)] = rdd1.partitionBy(new HashPartitioner(2))
    rdd2.repartition(3).mapPartitionsWithIndex((p,x) => {
      x.map((_,p))
    }).foreach(println)
    println("===RangePartitioner===")
    rdd1.repartition(3).partitionBy(new RangePartitioner(4, rdd1)).mapPartitionsWithIndex((p,x) => {
      x.map((_,p))
    }).foreach(println)
    println("===customPartitioner===")
    rdd1.partitionBy(new Partitioner {
      override def numPartitions: Int = 3 // 指定分区个数

      override def getPartition(key: Any): Int = {
        if(key.asInstanceOf[Int] % 2 == 0){
          0
        }else{
          1
        }
      }
    }).mapPartitionsWithIndex((p,x) => {
      x.map((_,p))
    }).foreach(println)
  }

  def testReduceByKey(): Unit ={
    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 1), ("c", 2), ("d", 3),("a", 3),("b", 3)))
    rdd1.reduceByKey(_+_).foreach(println)
    println("========================")
    rdd1.reduceByKey(_+_,3).foreach(println)
  }

  def testGroupByKey(): Unit ={
    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3), ("a", 2),("b",3)))
    val rdd2 = rdd1.groupByKey()
    rdd2.foreach(println)
    val rdd3 = rdd1.groupByKey(5) // 默认的分区器是hashPartitioner
    rdd3.foreach(println)
    val rdd4 = rdd1.groupByKey(new HashPartitioner(3))
    rdd4.foreach(println)

  }

  def testAggregateByKey(): Unit ={
    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 4), ("c", 3),("a", 2),("c",5),("c",6)),2)
    val rdd2 = rdd1.aggregateByKey(0)(_ + _, _ + _)
    rdd2.foreach(println)
    println("=================================================================")
    // 求分区内相同key的最大值,分区间将最大值求和
    rdd1.aggregateByKey(0)(
      (x,y) => math.max(x,y),
      (x,y) => x + y
    ).foreach(println)
  }

  def testFoldByKey(): Unit ={
    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 4), ("c", 3),("a", 2),("c",5),("c",6)),2)
    val rdd2 = rdd1.foldByKey(0)(_ + _)
    rdd2.foreach(println)
  }

  def testCombineByKey(): Unit ={
    val rdd1 = sc.makeRDD(List[(String, Int)](("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98)),2)
    rdd1.combineByKey(
      (_,1),
      (acc:(Int,Int),v) => (acc._1 + v,acc._2 + 1),
      (acc1:(Int,Int),acc2:(Int,Int)) => (acc1._1 + acc2._1,acc1._2 + acc2._2)
    ).foreach(println)
  }

  def testSortByKey(): Unit ={
    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 4), ("c", 3),("a", 2),("c",5),("c",6)),2)
    rdd1.sortByKey(true).foreach(println)
  }

  def testJoin(): Unit ={
    val rdd1 = sc.makeRDD(Array((1, "a"), (2, "b"), (3, "c"),(9,"d")))
    val rdd2 = sc.makeRDD(Array((1, 4), (2, 5), (3, 6),(4,7)))
    rdd1.join(rdd2).foreach(println)
  }

  def testLeftOuterJoin(): Unit ={
    val rdd1 = sc.makeRDD(Array((1, "a"), (2, "b"), (3, "c"),(9,"d")))
    val rdd2 = sc.makeRDD(Array((1, 4), (2, 5), (3, 6),(4,7)))
    rdd1.leftOuterJoin(rdd2).foreach(println)
  }

  def testCogroup(): Unit ={
    val rdd1 = sc.makeRDD(List(("a", 1), ("a", 2), ("c", 3)))
    val rdd2 = sc.makeRDD(List(("a", 3), ("a", 4), ("c", 5)))
    rdd1.cogroup(rdd2).foreach(println)
  }

  def testProject(): Unit ={
    val dataRDD1: RDD[String] = sc.textFile("D:\\study\\code\\Spark\\src\\main\\resources\\agent.log")
    dataRDD1.map(_.split(" "))
      .map(x => ((x(1),x(4)),x(0),x(2),x(3)))
      .groupBy(_._1)
      .map(x => (x._1,x._2.size))
      .sortBy(_._2,false)
      .take(3)
      .foreach(println)

  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("createRDD")
      .setMaster("local[*]")
    sc = new SparkContext(conf)
    //    testMap(sc)
    //    testMapPartitions(sc)
    //    testMapPartitionsWithIndex(sc)
    //    testFlatMap()
    //    testGlom()
    //    testGroupBy()
    //    testFilter()
    //    testSample()
//    testDistinct()
//    testCoalesce()
    //testSortBy()
//    testIntersection()
    //testUnion()
    //testSubtract()
    //testZip()
    //testPartitionBy()
    //testReduceByKey()
    //testGroupByKey()
    //testAggregateByKey()
    //testFoldByKey()
    //testCombineByKey()
    //testSortByKey()
    //testJoin()
    //testLeftOuterJoin()
    //testCogroup()
    testProject()
  }

}

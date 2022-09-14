package SparkCore.RDD

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName: transfunctionRDD
 * @Auther: SDY
 * @Description: 转换算子
 * @Date: 2022/9/13 22:36
 * */
object TransFunctionRDD {
  var sc: SparkContext = null
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
    testSample()


  }

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

}

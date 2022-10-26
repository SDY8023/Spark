package SparkCore.RDDCase

import org.apache.commons.lang3.StringUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.util.LongAccumulator

import scala.collection.mutable

case class UserVisitAction(
                            date: String,//用户点击行为的日期
                            user_id: Long,//用户的 ID
                            session_id: String,//Session 的 ID
                            page_id: Long,//某个页面的 ID
                            action_time: String,//动作的时间点
                            search_keyword: String,//用户搜索的关键词
                            click_category_id: Long,//某一个商品品类的 ID
                            click_product_id: Long,//某一个商品的 ID
                            order_category_ids: String,//一次订单中所有品类的 ID 集合
                            order_product_ids: String,//一次订单中所有商品的 ID 集合
                            pay_category_ids: String,//一次支付中所有品类的 ID 集合
                            pay_product_ids: String,//一次支付中所有商品的 ID 集合
                            city_id: Long
                          )//城市 id
object Case1 {
  var spark: SparkContext = null
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test1")
    spark = new SparkContext(conf)
    val data: RDD[UserVisitAction] = readData()
//    val result = test1(data)
//    println("====点击次数====")
//    result._1.foreach(println)
//    println("====下单次数====")
//    result._2.foreach(println)
//    println("====支付次数====")
//    result._3.foreach(println)
//    println("====test2====")
//    test2(data)
    test3(data)

  }

  def readData(): RDD[UserVisitAction] ={
    val fileRdd = spark.textFile("D:\\study\\code\\Spark\\src\\main\\resources\\user_visit_action.csv")
    fileRdd.map(x => {
      val datas = x.split(",")
      UserVisitAction(datas(0),datas(1).toLong,datas(2),datas(3).toLong,datas(4),datas(5),datas(6).toLong,datas(7).toLong,datas(8),datas(9),datas(10),
        datas(11),datas(12).toLong)
    })
  }

  /**
   * 分别统计每个品类点击的次数，下单的次数和支付的次数：
   */
  def test1(data:RDD[UserVisitAction]): (RDD[(Long,Long)],RDD[(String,Long)],RDD[(String,Long)]) ={
    // 每个品类点击的次数
    val clickRDD: RDD[(Long, Long)] = data.filter(x => (x.click_category_id != -1 && x.click_product_id != -1))
      .groupBy(_.click_category_id).map(x => {
      var click_count: Long = 0
      x._2.foreach(d => {
        if(d.click_category_id != -1 && d.click_product_id != -1) {
          click_count += 1
        }
      })
      (x._1, click_count)
    })

    // 每个品类下单次数
    val orderRDD: RDD[(String, Long)] = data.filter(x => StringUtils.isNotEmpty(x.order_category_ids) && StringUtils.isNotEmpty(x.order_category_ids))
      .groupBy(_.order_category_ids)
      .map(x => {
        var count: Long = 0
        x._2.foreach(d => {
          if (d.order_category_ids != null && d.order_product_ids != null) {
            count += 1
          }
        })
        (x._1, count)
      })

    // 每个品类的支付次数
    val payRDD: RDD[(String, Long)] = data.filter(x => StringUtils.isNotEmpty(x.pay_category_ids) && StringUtils.isNotEmpty(x.pay_product_ids))
      .groupBy(_.pay_category_ids)
      .map(x => {
        var count: Long = 0
        x._2.foreach(d => {
          if (d.pay_category_ids != null && d.pay_product_ids != null) {
            count += 1
          }
        })
        (x._1, count)
      })
    (clickRDD,orderRDD,payRDD)
  }

  /**
   * 一次性统计每个品类点击的次数，下单的次数和支付的次数：
   * @param data
   */
  def test2(data:RDD[UserVisitAction]): Unit ={
    val categoryIdMap:mutable.Map[String,(Long,Long,Long)] = mutable.Map[String,(Long,Long,Long)]()
    val value = data.map(x => {

      if (x.click_category_id != -1) {
        val click: (Long, Long, Long) = categoryIdMap.getOrElseUpdate(x.click_category_id.toString, (0, 0, 0))
        categoryIdMap.update(x.click_category_id.toString, (click._1 + 1, click._2, click._3))
      } else if (StringUtils.isNotEmpty(x.order_category_ids)) {
        val order: (Long, Long, Long) = categoryIdMap.getOrElseUpdate(x.order_category_ids, (0, 0, 0))
        categoryIdMap.update(x.order_category_ids, (order._1, order._2 + 1, order._3))
      } else if (StringUtils.isNotEmpty(x.pay_category_ids)) {
        val pay: (Long, Long, Long) = categoryIdMap.getOrElseUpdate(x.pay_category_ids, (0, 0, 0))
        categoryIdMap.update(x.pay_category_ids, (pay._1, pay._2, pay._3 + 1))
      }
      categoryIdMap
    })
    value.foreach(println)
  }

  /**
   * 使用累加器的方式聚合数据
   */
  def test3(data:RDD[UserVisitAction]): Unit ={
    // 定义系统累加器
    val clickCount: LongAccumulator = spark.longAccumulator("clickCount")
    val orderCount: LongAccumulator = spark.longAccumulator("orderCount")
    val payCount: LongAccumulator = spark.longAccumulator("payCount")
    // 点击行为次数
    println("=====点击行为=====")
    data.filter(_.click_category_id != -1)
      .groupBy(_.click_category_id)
      .map(x => {
        x._2.foreach(y => {
          clickCount.add(1L)
        })
        (x._1,clickCount.value)
      }).foreach(println)
    // 订单次数

    println("=====订单次数=====")
    data.filter(x => StringUtils.isNotEmpty(x.order_category_ids))
      .groupBy(_.order_category_ids)
      .map(x => {
        orderCount.reset()
        x._2.foreach(y => {
          orderCount.add(1L)
        })
        (x._1,orderCount.value)
      }).foreach(println)
    // 支付次数
    println("=====支付次数=====")
    data.filter(x => StringUtils.isNotEmpty(x.pay_category_ids))
      .groupBy(_.pay_category_ids)
      .map(x => {
        payCount.reset()
        x._2.foreach(y =>{
          payCount.add(1L)
        })
        (x._1,payCount.value)
      }).foreach(println)


  }

}

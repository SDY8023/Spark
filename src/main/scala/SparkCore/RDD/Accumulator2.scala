package SparkCore.RDD

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * @ClassName: Accumulator2
 * @Auther: SDY
 * @Description:
 * @Date: 2022/9/25 21:01
 * */
object Accumulator2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test")
    val sc = new SparkContext(conf)
  }

}
class WordCountAccumulator extends AccumulatorV2[String,mutable.Map[String,Long]]{
  var map: mutable.Map[String,Long] = mutable.Map()
  // 判度累加器是否是初始化状态
  override def isZero: Boolean = {
    map.isEmpty
  }

  // 复制累加器
  override def copy(): AccumulatorV2[String, mutable.Map[String, Long]] = {
    new WordCountAccumulator
  }

  // 重置累加器
  override def reset(): Unit = {
    map.clear()
  }

  // 向累加器中增加数据
  override def add(v: String): Unit = {
    // 查询map中是否存在相同的单词
    // 若有相同的单词，那么单词的数量加1
    // 如果没有相同的单词，那么在map中增加这个单词
    map(v) = map.getOrElse(v,0L) + 1L
  }

  // 合并累加器
  override def merge(other: AccumulatorV2[String, mutable.Map[String, Long]]): Unit = {
    val map1 = map
    val map2 = other.value

    // 两个map的合并
    map1.foldLeft(map2)(
      (x,y) => {
        x(y._1) = x.getOrElse(y._1,0L) + y._2
        x
      }
    )
  }

  // 返回累加器的结果
  override def value: mutable.Map[String, Long] = map
}

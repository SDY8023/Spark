package HWJS

import scala.io.StdIn
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
object test5 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    val priceAndPackge = input.split(" ")
    val totalMoney = priceAndPackge(0).toInt
    val count = priceAndPackge(1).toInt
    // 创建物品集合(物品id,价格,满意度,是否为主属性)
    val resultList: ListBuffer[(Int,Int, Int, Int)] = mutable.ListBuffer[(Int,Int, Int, Int)]()
    val result:Int = 0
    for(i <- 0 until(count)){
      val data: String = StdIn.readLine()
      val ds: Array[String] = data.split(" ")
      resultList.append((i + 1,ds(0).toInt,ds(1).toInt,ds(2).toInt))
    }
    // 统计每个主属性物品->附属物品
    val resultMap = mutable.Map[Int,mutable.ListBuffer[(Int,Int, Int, Int)]]()
    // 主属性物品统计进来
    resultList.map(x => {
      if(x._4 == 0) {
        resultMap += (x._1 -> mutable.ListBuffer(x))
      }
    })
    // 附属物品加入进来
    resultList.map(x => {
      if(x._4 != 0){
        val tuples: ListBuffer[(Int, Int, Int, Int)] = resultMap.getOrElseUpdate(x._4, mutable.ListBuffer())
        tuples.append(x)
        resultMap.update(x._4,tuples)
      }
    })
    // 满意度集合
    val ints: ListBuffer[Int] = mutable.ListBuffer[Int]()
    var t:Int = 0
    var m:Int = 0
    resultMap.foreach(x => {
      x._2.map(y => {
        t += y._2 * y._3
        m += y._2
      })
      if(m <= totalMoney){
        ints.append(t)
      }
    })
    print(ints.max)
  }

}

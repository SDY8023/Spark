package HWJS

import scala.io.StdIn
import scala.collection.mutable
object test24 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun2(input)

  }

  def fun1(input:String): Unit ={
    val n = input.toInt
    val resultList = mutable.ListBuffer[Int]()
    for(i <- 0 until(n)){
      var beautifulValue = 26
      val line = StdIn.readLine()
      val strMap = mutable.Map[String,Int]() // 记录每个字母出现的次数
      val strMapValue = mutable.Map[String,Int]() // 记录每个字母对应的美丽值
      val strList = mutable.ListBuffer[String]()
      var resultValue:Int = 0
      for(j <- 0 until(line.length)){
        val key = line.substring(j,j+1)
        val v = strMap.getOrElse(key, 0)
        strMap.put(key,v+1)
        strList.append(key)
      }
      val tuples: List[(String, Int)] = strMap.toList.sortWith((x, y) => x._2 > y._2)
      tuples.foreach(x => {
        strMapValue.put(x._1,beautifulValue)
        beautifulValue -= 1
      })
      strList.foreach(x => {
        resultValue += strMapValue(x)
      })
      resultList.append(resultValue)
    }
    resultList.foreach(println)
  }

  def fun2(input:String): Unit ={
    val str = StdIn.readLine()
    println(input.substring(0,str.toInt))
  }

}

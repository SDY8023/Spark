package HWJS

import scala.io.StdIn
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
object test13 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun1(input)
  }

  def fun1(input:String): Unit ={
    val table = Map(
      ("A",1), ("B",2), ("C",3), ("D",4), ("E",5), ("F",6), ("G",7), ("H",8), ("I",9), ("J",10), ("K",11), ("L",12), ("M",13),
      ("N",14), ("O",15), ("P",16), ("Q",17), ("R",18), ("S",19), ("T",20), ("U",21), ("V",22), ("W",23), ("X",24), ("Y",25),
      ("Z",26)
    )
    // 字母组合
    val strList = mutable.ListBuffer[(String,(Int,Int))]()
    // 非字母组合
    val noStrList = mutable.ListBuffer[(String,Int)]()
    for(i <- 0 until(input.length)){
      val str = input.substring(i, i + 1)
      if(table.contains(str.toUpperCase)){
        strList.append((str,(table(str.toUpperCase),i)))
      }else{
        noStrList.append((str,i))
      }
    }
    val resultArray: Array[String] = new Array[String](input.length)
    // 将非字母的元素放置对应位置
    noStrList.foreach(x => {
      resultArray(x._2) = x._1
    })
    // 对字母元素进行排序
    val strArray: Array[(String, (Int, Int))] = strList.toArray


    for(j <- strArray.indices){
      var tmp = strArray(0)
      for(i <- 1 until(strArray.length-j)){
        if(strArray(i)._2._1 >= tmp._2._1){
          tmp = strArray(i)
        }else if(strArray(i)._2._1 < tmp._2._1){
          tmp = strArray(i-1)
          strArray(i-1) = strArray(i)
          strArray(i) = tmp
        }
      }
    }
    for(i <- strArray.indices){
      if(resultArray(i) == null){
        resultArray(i) = strArray(i)._1
      }else{
        var j = i
        while(resultArray(j) != null){
          j += 1
        }
        resultArray(j) = strArray(i)._1
      }
    }
    println(resultArray.mkString(""))



  }

}

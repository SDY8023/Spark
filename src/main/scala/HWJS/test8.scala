package HWJS

import scala.io.StdIn
import scala.collection.mutable
object test8 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun1(input)
  }

  def fun1(input:String): Unit ={
    val dataMatch1: Map[String, String] = Map[String, String](("1", "1"), ("abc", "2"), ("def", "3"), ("ghi", "4"), ("jkl", "5"), ("mno", "6"), ("pqrs", "7"), ("tuv", "8"),
      ("wxyz", "9"), ("0", "0"))
    val dataMatch2: Map[String, String] = Map[String, String](
      ("A", "b"),
      ("B", "c"),
      ("C", "d"),
      ("D", "e"),
      ("E", "f"),
      ("F", "g"),
      ("G", "h"),
      ("H", "i"),
      ("I", "j"),
      ("J", "k"),
      ("K", "l"),
      ("L", "m"),
      ("M", "n"),
      ("N", "o"),
      ("O","p"),
      ("P","q"),
      ("Q","r"),
      ("R","s"),
      ("S","t"),
      ("T","u"),
      ("U","v"),
      ("V","w"),
      ("W","x"),
      ("X","y"),
      ("Y","z"),
      ("Z","a")
    )
    var result = mutable.ListBuffer[String]()
    for(i <- 0 until(input.length)){
      val str = input.substring(i, i + 1)
      var flag = 0
      if(dataMatch2.contains(str)){
        result.append(dataMatch2(str))
        flag = 1
      }else{
        dataMatch1.foreach(x => {
          if(x._1.contains(str)){
            result.append(x._2)
            flag = 1
          }
        })
      }
      if(flag == 0) result.append(str)
    }
    println(result.mkString(""))
  }

}

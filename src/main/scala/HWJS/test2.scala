package HWJS

import scala.io.StdIn

object test2 {
  def main(args: Array[String]): Unit = {
    val input: String = StdIn.readLine()
    val datas: Array[String] = input.split(" ")
    if(datas.length == 1){
      println(input)
    }else{
      datas.reverse.foreach(x => print(x+" "))
    }
  }

}

package HWJS

import scala.io.StdIn

object test20 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun2(input)

  }

  def fun1(input:String): Unit ={
    val testArray1 = new Array[String](input.length)
    for(i <- 0 until(input.length)){
      testArray1(i) = input.substring(i,i+1)
    }
    val sortedArray: Array[String] = testArray1.sorted
    sortedArray.foreach(print)
  }

  def fun2(input:String): Unit ={
    val data = input.toInt
    // 外循环控制行数
    var init = data
    var tmp = 0 // 初始间隔数
    var tmpInit = 1 // 每行第一个初始值
    var tmp1 = 0 // 每行第一个数字之间的间隔值
    for(i <- 0 until  data){
      tmp = i + 2
      // 内循环控制列数
      init = data - i
      tmpInit = tmpInit + tmp1
      var tmpInit1 = tmpInit
      for(j <- 0 until(init)){
        if(j == 0){
          print(tmpInit1+" ")
        }else{
          tmpInit1 += tmp
          print(tmpInit1+" ")
          tmp += 1
        }
      }
      tmp1 += 1
      println()
    }
  }

}

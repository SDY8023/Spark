package HWJS

import scala.io.StdIn
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
object test9 {
  def main(args: Array[String]): Unit = {
    var i:Int = 0
    val resultList: ListBuffer[Int] = mutable.ListBuffer[Int]()
    while(i < 10){
      val input: String = StdIn.readLine()
      if(!input.equals("0")){
        resultList.append(fun1(input))
        i += 1
      }else{
        i = 10
      }
    }
    resultList.foreach(println)

  }

  def fun1(input:String): Int ={
    var data = input.toInt // 输入的空瓶数
    var flag:Boolean = true // 循环结束标志
    var result:Int = 0
    while(flag){
      if(data <= 1){
        flag = false
      }else if(data == 2){
        // 可以向老板再借一个空瓶子,再喝一瓶
        result += 1
        flag = false
      }else{
        // 空瓶数大于2的计算方式
        // 手中的空瓶数可以喝多少瓶
        val i = data / 3
        result += i
        // 换完后还剩几个空瓶子
        val y = data % 3
        // 喝完后手中还有几个空瓶子
        data = i + y
      }
    }
    result

  }

}

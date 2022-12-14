package HWJS
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.StdIn
object test11 {
  def main(args: Array[String]): Unit = {
    val line = StdIn.readLine()
    fun1(line)
  }

  def fun1(input:String): Unit ={
    val data = StdIn.readLine()
    val len = input.toInt
    val strings = data.split(" ")
    val dataArray = strings.map(_.toInt)
    val leftMax: Array[Int] = new Array[Int](len)
    val rightMax: Array[Int] = new Array[Int](len)
    // 初始化边界值
    for(i <- leftMax.indices){
      leftMax(i) = 1
      rightMax(i) = 1
    }
    val resultArray: Array[Int] = new Array[Int](len)
    // 求出左边的最长单调递增子序列
    for(i <- dataArray.indices){
      // 计算以dataArray(i)元素为结尾数据时，最长的单调递增子序列
      for(j <- 0 until i){
        if(dataArray(i) > dataArray(j) && leftMax(j)+1 > leftMax(i)){
          leftMax(i) = leftMax(j) + 1
        }
      }
    }
    // 求出右边的最长单调递增子序列
    var m = len-1
    while(m >= 0){
      var n = len-1
      while(n >= m){
        if(dataArray(n) < dataArray(m) && rightMax(n)+1 > rightMax(m)){
          rightMax(m) = rightMax(n)+1
        }
        n -= 1
      }
      m -=1
    }
    for(i <- 0 until(len)){
      resultArray(i) = leftMax(i) + rightMax(i)-1
    }
    println(len - resultArray.max)

  }

}

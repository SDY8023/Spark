package HWJS
import scala.collection.mutable
import scala.io.StdIn
object test19 {
  def main(args: Array[String]): Unit = {

    val input1 = StdIn.readLine()
    val input2 = StdIn.readLine()
    fun1(input1, input2)
  }

  def fun1(input1:String,input2:String): Unit ={
    val data1: Array[String] = input1.split("\\.")
    var r1 = ""
    var r2 = ""
    data1.foreach(x => {
      var t1 = x.toInt.toBinaryString
      var ts1 = ""
      for(i <- 0 until(8-t1.length)){
        ts1 += "0"
      }
      t1 = ts1 + t1
      r1 += t1
    })
    var result1:Double = 0
    val reverse = r1.reverse
    for(i <- 0 until(reverse.length)){
      val tt1  = reverse.substring(i,i+1).toDouble
      result1 += tt1 * Math.pow(2,i)
    }

    var d2 = input2.toLong.toBinaryString
    var tmp = ""
    if(d2.length < 32){
      for(i <- 0 until(32-d2.length)){
        tmp += "0"
      }
    }
    d2 = tmp + d2
    val dataList = mutable.ListBuffer[String]()
    var tmp2:Int = 0
    val a1 = d2.substring(0, 8)
    val a2 = d2.substring(8,16)
    val a3 = d2.substring(16,24)
    val a4 = d2.substring(24,32)
    r2 = s"${Integer.parseInt(a1,2)}.${Integer.parseInt(a2,2)}.${Integer.parseInt(a3,2)}.${Integer.parseInt(a4,2)}"

    println(result1.toLong)
    println(r2)
  }

}

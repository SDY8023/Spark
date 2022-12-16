package HWJS
import scala.collection.mutable
import scala.io.StdIn

object test16 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun1(input)

  }
  def fun1(input:String): Unit ={
    // 1 合并
    val strings = input.split(" ")
    val data = strings(0) + strings(1)
    // 2 排序
    val dataArray1: Array[String] = new Array[String](data.length)
    var o = mutable.ListBuffer[String]()
    var j = mutable.ListBuffer[String]()
    for(i <- 0 until  data.length){
      if(i%2==0){
        o.append(data.substring(i,i+1))
      }else{
        j.append(data.substring(i,i+1))
      }
    }
    o = o.sorted
    j = j.sorted
    var m = 0
    var n = 0
    for(i <- 0 until(data.length)){
      if(i % 2==0){
        dataArray1(i) = o(m)
        m += 1
      }else{
        dataArray1(i) = j(n)
        n += 1
      }
    }

    // 3 转换 1111
    val matchMap: Map[String, String] = Map[String, String](
      ("A", "5"), ("B", "D"), ("C", "3"), ("D", "B"), ("E", "7"), ("F", "F"),
      ("0","0"),("1","8"),("2","4"),("3","C"),("4","2"),("5","A"),("6","6"),("7","E"),
      ("8","1"),("9","9")
    )
    var result:String = ""
    dataArray1.foreach(x => {
      if(matchMap.contains(x.toUpperCase)){
        result += matchMap(x.toUpperCase)
      }else{
        result += x
      }
    })
    println(result)


  }

}

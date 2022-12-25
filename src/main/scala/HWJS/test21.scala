package HWJS
import java.io
import scala.collection.mutable
import scala.io.StdIn
object test21 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun3(input)

  }

  def fun1(input:String): Unit ={
    val line: String = StdIn.readLine()
    // 先将输入字符串中的重复字符串保留第一个
    val inputList = mutable.ListBuffer[String]()
    val dataList = mutable.ListBuffer[String]()
    for(i <- 0 until(input.length)){
      inputList.append(input.substring(i,i+1))
    }
    for(i <- inputList.indices){
      if(!dataList.contains(inputList(i))){
        dataList.append(inputList(i))
      }
    }
    val fieldTable = mutable.ListBuffer[String]("A","B","C","D","E","F","G","H","I","J","K",
      "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
    val fieldTable2 = mutable.ListBuffer[String]("a","b","c","d","e","f","g","h","i","j","k",
      "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")
    val fieldTable3 = mutable.ListBuffer[String]("A","B","C","D","E","F","G","H","I","J","K",
      "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
    dataList.foreach(x => {
      if(fieldTable.contains(x.toUpperCase)){
        fieldTable.-=(x.toUpperCase)
      }
    })
    val resultTable = dataList.toList ::: fieldTable.toList
    println(resultTable)
    println(fieldTable3)
    val resultMap = mutable.Map[String, String]()
    for(i <- resultTable.indices){
      resultMap(fieldTable3(i)) = resultTable(i)
    }
    var resultStr:String = ""
    for(i <- 0 until(line.length)){
      val a = line.substring(i, i + 1)
      var str = resultMap.getOrElse(a.toUpperCase, "")
      if(fieldTable2.contains(a)){
        str = str.toLowerCase
      }
      resultStr += str
    }
    println(resultStr)

  }

  def fun2(input:String): Unit ={
    val data:Int = input.toInt
    // 初始化只有一只兔子
    val months = new Array[Int](data+1)
    months(1) = 1
    months(2) = 1
    var tmp = 1
    for(i <- 3 until(data+1)){
      months(i) = months(i-1)+months(i-2)
    }
    println(months(data))
  }

  def fun3(input:String): Unit ={
    val h = input.toInt
    // 反弹高度
    var tmp:Double = h
    // 第几次落地
    var n:Int = 0
    var totalH:Double = 0
    var result:Double = 0
    while(tmp != 0 && n < 5){
      n += 1
      if(n == 1){
        totalH += tmp
      }else{
        totalH += 2*tmp
      }
      tmp = tmp / 2
      if(n == 5){
        result = tmp
      }
    }
    println(totalH)
    println(result)

  }

}

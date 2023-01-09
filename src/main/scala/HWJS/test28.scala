package HWJS

import javax.script._
import scala.io.StdIn
import scala.collection.mutable
/**
 * @ClassName: test28
 * @Auther: SDY
 * @Description:
 * @Date: 2023/1/8 20:30
 * */
object test28 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun6(input)
  }

  def fun1(input:String): Unit ={
    val len = input.toInt
    val resultList = mutable.ListBuffer[String]()
    val datas = StdIn.readLine().split(" ")
    for(i <- 0 until(len)){
      resultList.append(datas(i))
    }
    val k = StdIn.readLine()
    println(datas(len-k.toInt))
  }

  def fun2(input:String): Unit ={
    val data1 = input
    val data2 = StdIn.readLine()
    // 记录操作次数
    var count:Int = 0
    val dataList1 = mutable.ListBuffer[String]()
    val dataList2 = mutable.ListBuffer[String]()
    for(i <- 0 until(data1.length)){
      dataList1.append(data1.substring(i,i+1))
    }
    for(i <- 0 until(data2.length)){
      dataList2.append(data2.substring(i,i+1))
    }
    if(dataList1.length > dataList2.length){
      // 差值
      val d = dataList1.length - dataList2.length
      for(i <- 0 until(dataList2.length)){
        if(!dataList1(i).equals(dataList2(i))){
          count += 1
        }
      }
      count = count + d
    }else if(dataList1.length < dataList2.length){
      // 差值
      val d = dataList2.length - dataList1.length
      for(i <- 0 until(data1.length)){
        if(!dataList1(i).equals(dataList2(i))){
          count += 1
        }
      }
      count = count + d
    }else{
      // 两者长度相等
      var len = dataList1.length
      for(i <- 0 until(len)){
        if(!dataList1(i).equalsIgnoreCase(dataList2(i))){
          count += 1
        }
      }
    }
    println(count)

  }

  def fun3(input:String): Unit ={
    val input2 = StdIn.readLine()
    val len2 = input2.length
    val dp = new Array[Array[Int]](input.length+1)
    var data1:String = ""
    var data2:String = ""
    // 外循环控制行数 处理 第0行，0列数据
    for(i <- 0 until(input.length+1)){
      val datas = new Array[Int](len2 + 1) // 该行的数据
      // 内循环控制列数
      for(j <- 0 until(len2+1)){
        // 单独处理第0行数据:代表空字符串的填充
        if(i == 0){ // 按照列字符串计算
          datas(j) = j
        }else if(j == 0){
          datas(j) = i
        }
      }
      dp(i) = datas
    }

    // 从1，1处开始遍历
    for(i <- 1 until(input.length+1)){
      data1 = input.substring(i-1,i)
      for(j <- 1 until(len2+1)){
        data2 = input2.substring(j-1,j)
        if(data1.equals(data2)){
          dp(i)(j) = dp(i-1)(j-1)
        }else{
          val d1 = dp(i-1)(j-1)+1
          val d2 = dp(i-1)(j)+1
          val d3 = dp(i)(j-1)+1
          dp(i)(j) = Math.min(Math.min(d1,d2),d3)
        }
      }
    }
    println(dp(input.length)(len2))

  }

  def fun4(input:String): Unit ={
    val n = input.toInt
    if(n <= 2){
      println("-1")
    }else if( n > 2 && n % 2 != 0){
      println("2")
    }else{
      println("3")
    }
  }

  def fun5(input:String): Unit ={
    val engine = new ScriptEngineManager().getEngineByName("nashorn")
    println(engine.eval(input))
  }

  def fun6(input:String): Unit ={
    val n = input.toInt
    var count = 0
    for( i <- 1 to n){
      if(i >= 7 ){
        if(i % 7 == 0){
          count += 1
        }else{
          var j = 0
          while(j < i.toString.length){
            if(i.toString.substring(j,j+1).equals("7")){
              count += 1
              j = i.toString.length
            }else{
              j += 1
            }
          }
        }
      }
    }
    println(count)
  }

}

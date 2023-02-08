package HWJS
import scala.collection.mutable
import scala.io.StdIn

object test29 {
  def main(args: Array[String]): Unit = {
    fun1()

  }

  /**
   * 24点游戏
   */
  def fun1(): Unit ={
    val n: String = StdIn.readLine()
    var dataList = mutable.ListBuffer[(Int, Int)]() // 收集对应的矩阵
    for(i <- 0 until(n.toInt)){
      val d = StdIn.readLine()
      val strings = d.split(" ")
      dataList.append((strings(0).toInt,strings(1).toInt))
    }
    val str = StdIn.readLine() // 输入计算公式
    var resultCount:Long = 0 // 最终计算结果次数
    val strList = mutable.ListBuffer[String]() // 收集计算公式
    var caluateList = mutable.ListBuffer[String]()
    val len = str.length
    var m:Int = 0
    for(i <- 0 until(len)){
      val s1 = str.substring(i,i+1)
      if(!s1.equalsIgnoreCase(")")){
        // 只要不是右括号，都进栈
        if(s1.equalsIgnoreCase("(")){
          strList.append(s1) // 左括号进栈
        }else{
          // 不是左括号，进入下标
          strList.append(m.toString)
          m += 1
        }
      }else{
        // 遇到右括号,开始出栈
        var flag:Boolean = true
        var j = strList.length-1
        while(flag && j >= 0){
          if(strList(j).equalsIgnoreCase("(")){
            strList.remove(j)
            flag = false
          }else{
            caluateList.append(strList(j))
            strList.remove(j)
            j -= 1
          }
        }
        // 计算已经出栈的值
        caluateList = caluateList.reverse
        val tmp1 = caluate(dataList, caluateList)
        resultCount += tmp1._1
        dataList = tmp1._2
        strList.append(caluateList.head)
        m = caluateList.head.toInt + 1
        caluateList.clear()
      }
    }
    println(resultCount)
  }

  def caluate(dataList:mutable.ListBuffer[(Int,Int)],caluateList:mutable.ListBuffer[String]): (Long,mutable.ListBuffer[(Int,Int)],(Int,Int)) ={
    var count:Long = 0
    var tmp:(Int,Int) = (0,0)
    var tmpDataList = dataList // 新的data矩阵
    for(i <- caluateList.indices){
      if(i == 0){
        // 第一次计算
        val j1 = dataList(caluateList(i).toInt)
        val j2 = dataList(caluateList(i+1).toInt)
        val rs: (Long, (Int, Int)) = test1(j1, j2)
        tmp = rs._2
        count += rs._1
      }else if(i == 1){
        // 什么都不做
      }else{
        val j3 = dataList(caluateList(i).toInt)
        val rs = test1(tmp, j3)
        tmp = rs._2
        count += rs._1
      }
    }
    for(i <- caluateList.indices){
      if(i == 0){
        tmpDataList(caluateList(i).toInt) = tmp
      }else{
        tmpDataList.remove(caluateList(i).toInt)
      }
    }
    (count,tmpDataList,tmp)
  }

  def test1(tuple1:(Int,Int),tuple2:(Int,Int)): (Long,(Int,Int)) ={
    (tuple1._1 * tuple1._2 * tuple2._2,(tuple1._1,tuple2._2))
  }

}

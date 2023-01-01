package HWJS

import scala.io.StdIn
import scala.collection.mutable
object test22 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun4(input)
  }

  def fun1(input:String): Unit ={
    val s1 = "[A-Za-z]".r
    val s2 = "[ ]".r
    val s3 = "[0-9]".r
    var c1 = 0
    var c2 = 0
    var c3 = 0
    var c4 = 0
    for(i <- 0 until(input.length)){
      val str = input.substring(i, i + 1)
      if(s1.findAllIn(str).nonEmpty){
        c1 += 1
      }else if(s2.findAllIn(str).nonEmpty){
        c2 += 1
      }else if(s3.findAllIn(str).nonEmpty){
        c3 += 1
      }else{
        c4 += 1
      }
    }
    println(c1)
    println(c2)
    println(c3)
    println(c4)
  }

  def fun2(input:String): Unit ={
    val input2 = StdIn.readLine() // 砝码的重量
    val input3 = StdIn.readLine() // 砝码的数量
    val f2: Array[Int] = input2.split(" ").map(_.toInt) // 重量
    val f3: Array[Int] = input3.split(" ").map(_.toInt) // 数量
    var resultSet = mutable.Set[Int]()
    resultSet.add(0)
    // 将所有砝码加入到此集合中: 1 1 2 2
    val dataList = mutable.ListBuffer[Int]()
    for(i <- 0 until(input.toInt)){
      for(j <- 0 until(f3(i))){
        dataList.append(f2(i))
      }
    }
    dataList.foreach(x => {
      resultSet = resultSet ++ resultSet.map(_ + x)
      resultSet.add(x)
    })
    println(resultSet.size)

  }

  def fun3(input:String): Unit ={
    val strings = input.split(" ")
    val x = strings(0).toInt // 行数
    val y = strings(1).toInt // 列数
    val array: Array[Array[Int]] = new Array[Array[Int]](x)
    for(i <- 0 until(x)){
      val str = StdIn.readLine()
      val strings1 = str.split(" ")
      val dataYArray = new Array[Int](y)
      for(j <- 0 until(y)){
        dataYArray(j) = strings1(j).toInt
      }
      array(i) = dataYArray
    }
    var m:Int = 0
    var n:Int = 0
    val resultList = mutable.ListBuffer[(Int,Int)]()
    var tmp:Int = 0 // 记录该行最后一个出现0的位置
    while(m < x){
      val lines: Array[Int] = array(m) // 行数据
      if(m != x-1){
        val tmpList = mutable.ListBuffer[Int]()
        val lines2: Array[Int]  = array(m+1)// 下一行数据
        var tmp1:Int = 0
        while(n < y){
          val d1 = lines(n)
          val d2 = lines2(n)
          if(d1 == 0 && d2 == 0){
            tmpList.append(n)
            resultList.append((m,n))
            n += 1
          }else if(d1 == 0 && d2 != 0){
            resultList.append((m,n))
            n += 1
          }else if(d1 != 0){
            n = y
          }
        }
        if(tmpList.nonEmpty){
          tmp = tmpList.max
          n = tmpList.max
        }else{
          // 若往下找不到元素，则往上找
          n = tmp
          n += 1
          val lines3 = array(m - 1)
          while(n < y){
            val d1 = lines3(n)
            val d2 = lines(n)
            if(d1 == 0 && d2 ==0){
              m = m - 2
              tmpList.append(n)
              n = y
            }else if(d1 != 0 && d2 == 0){
              n += 1
            }else if(d2 != 0){
              n = y
            }

          }
          n = tmpList.max
        }
      }else{
        // 最后一行数据单独处理
        while(n < y){
          val d = lines(n)
          if(d == 0){
            resultList.append((m,n))
            n += 1
          }
        }
      }
      m += 1
    }
    resultList.foreach(println)
  }

  def fun4(input:String): Unit ={
    val strings = input.split(" ")
    val x = strings(0).toInt // 行数
    val y = strings(1).toInt // 列数
    val array: Array[Array[Int]] = new Array[Array[Int]](x)
    for(i <- 0 until(x)){
      val str = StdIn.readLine()
      val strings1 = str.split(" ")
      val dataYArray = new Array[Int](y)
      for(j <- 0 until(y)){
        dataYArray(j) = strings1(j).toInt
      }
      array(i) = dataYArray
    }
    val resultList = mutable.ListBuffer[(Int,Int)]()
    resultList.append((0,0))

    def walk(i:Int,j:Int,resultList:mutable.ListBuffer[(Int,Int)]): Unit ={
      if(j + 1 < y && array(i)(j+1) == 0 && (i != x-1 || j != y-1)){
        // 向右走
        if(!resultList.contains((i,j+1))){
          resultList.append((i,j+1))
          walk(i,j+1,resultList)
        }
      }
      if( j - 1 >= 0 && array(i)(j-1) == 0 && (i != x-1 || j != y-1)){
        // 向左走
        if(!resultList.contains((i,j-1))){
          resultList.append((i,j-1))
          walk(i,j-1,resultList)
        }
      }
      if(i+1 < x && array(i+1)(j) == 0 && (i != x-1 || j != y-1)){
        // 向下走
        if(!resultList.contains((i+1,j))){
          resultList.append((i+1,j))
          walk(i+1,j, resultList)
        }
      }
      if(i-1 >= 0 && array(i-1)(j) == 0 && (i != x-1 || j != y-1)){
        // 向上走
        if(!resultList.contains((i-1,j))){
          resultList.append((i-1,j))
          walk(i-1,j,resultList)
        }
      }
      if(i == x-1 && j == y -1){
        return
      }
    }
    walk(0,0,resultList)
    resultList.foreach(println)
  }

}

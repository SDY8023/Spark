package HWJS

import java.util.Scanner
import scala.io.StdIn

/**
 * @ClassName: test6
 * @Auther: SDY
 * @Description:
 * @Date: 2022/12/11 15:49
 * */
object test6 {
  def main(args: Array[String]): Unit = {
    val sc = StdIn.readLine()
    val strings = sc.split(" ")
    val money = strings(0).toInt / 10
    val n = strings(1).toInt
    if (n <= 0 || money <= 0) System.out.println(0)
    val Gs = new Array[good](n + 1)
    for (i <- 1 to n) {
      val sc1 = StdIn.readLine()
      val strings1 = sc1.split(" ")
      val v = strings1(0).toInt / 10
      val p = strings1(1).toInt
      val q = strings1(2).toInt
      Gs(i) = new good(v, p, q)
    }
    // 添加附件
    for (i <- 1 to n) {
      if (Gs(i).q > 0) {
        if (Gs(Gs(i).q).a1 == 0) Gs(Gs(i).q).a1 = i else Gs(Gs(i).q).a2 = i
      }
    }

    val dp = Array.ofDim[Int](n + 1, money + 1)
    val sit = Array
    for (i <- 1 to n) {
      var v = 0
      var v1 = 0
      var v2 = 0
      var v3 = 0
      var tempdp = 0
      var tempdp1 = 0
      var tempdp2 = 0
      var tempdp3 = 0
      v = Gs(i).v
      tempdp = Gs(i).p * v //只有主件

      if (Gs(i).a1 != 0) { //主件加附件1
        v1 = Gs(Gs(i).a1).v + v
        tempdp1 = tempdp + Gs(Gs(i).a1).v * Gs(Gs(i).a1).p
      }

      if (Gs(i).a2 != 0) { //主件加附件2
        v2 = Gs(Gs(i).a2).v + v
        tempdp2 = tempdp + Gs(Gs(i).a2).v * Gs(Gs(i).a2).p
      }

      if (Gs(i).a1 != 0 && Gs(i).a2 != 0) { //主件加两个附件
        v3 = Gs(Gs(i).a1).v + Gs(Gs(i).a2).v + v
        tempdp3 = tempdp + Gs(Gs(i).a1).v * Gs(Gs(i).a1).p + Gs(Gs(i).a2).v * Gs(Gs(i).a2).p
      }
      for (j <- 1 to money) {
        if (Gs(i).q > 0) { //当物品i是附件时,相当于跳过
          dp(i)(j) = dp(i - 1)(j)
        }
        else {
          dp(i)(j) = dp(i - 1)(j)
          if (j >= v) dp(i)(j) = Math.max(dp(i)(j), dp(i - 1)(j - v) + tempdp)
          if (j >= v1) dp(i)(j) = Math.max(dp(i)(j), dp(i - 1)(j - v1) + tempdp1)
          if (j >= v2) dp(i)(j) = Math.max(dp(i)(j), dp(i - 1)(j - v2) + tempdp2)
          if (j >= v3) dp(i)(j) = Math.max(dp(i)(j), dp(i - 1)(j - v3) + tempdp3)
        }
      }
    }

    System.out.println(dp(n)(money) * 10)
  }

  class good(var v: Int, var p: Int, var q: Int) {
    var a1 = 0 //附件1ID
    var a2 = 0 //附件2ID

    override def toString: String = {
      return "a1:"+a1+" a2:"+a2+" v:"+v+" p:"+p+" q:"+q
    }
  }
}

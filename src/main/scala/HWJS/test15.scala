package HWJS

import scala.io.StdIn
import scala.util.matching.Regex

object test15 {
  def main(args: Array[String]): Unit = {
    val input1 = StdIn.readLine()
    val input2 = StdIn.readLine()
    fun1(input1)
    fun2(input2)
  }

  /**
   * 加密
   * @param input
   */
  def fun1(input:String): Unit ={
    val matchMap = Map[String,String](
      ("a","B"),("b","C"),("c","D"),("d","E"),("e","F"),("f","G"),("g","H"),("h","I"),("i","J"),("j","K"),("k","L"),
      ("l","M"),("m","N"),("n","O"),("o","P"),("p","Q"),("q","R"),("r","S"),("s","T"),("t","U"),("u","V"),("v","W"),
      ("w","X"),("x","Y"),("y","Z"),("z","A"),
      ("A","b"),("B","c"),("C","d"),("D","e"),("E","f"),("F","g"),("G","h"),("H","i"),("I","j"),("J","k"),("K","l"),
      ("L","m"),("M","n"),("N","o"),("O","p"),("P","q"),("Q","r"),("R","s"),("S","t"),("T","u"),("U","v"),("V","w"),
      ("W","x"),("X","y"),("Y","z"),("Z","a"),
      ("0","1"),("1","2"),("2","3"),("3","4"),("4","5"),("5","6"),("6","7"),("7","8"),("8","9"),("9","0")
    )
    val regex = "[A-Za-z0-9]+"
    if(input.matches(regex)){
      var result = ""
      for(i <- 0 until(input.length)){
        result += matchMap(input.substring(i,i+1))
      }
      println(result)
    }
  }

  /**
   * 解密
   * @param input
   */
  def fun2(input:String): Unit ={
    val matchMap = Map[String,String](
      ("a","Z"),("b","A"),("c","B"),("d","C"),("e","D"),("f","E"),("g","F"),("h","G"),("i","H"),("j","I"),("k","J"),
      ("l","K"),("m","L"),("n","M"),("o","N"),("p","O"),("q","P"),("r","Q"),("s","R"),("t","S"),("u","T"),("v","U"),
      ("w","V"),("x","W"),("y","X"),("z","Y"),
      ("A","z"),("B","a"),("C","b"),("D","c"),("E","d"),("F","e"),("G","f"),("H","g"),("I","h"),("J","i"),("K","j"),
      ("L","k"),("M","l"),("N","m"),("O","n"),("P","o"),("Q","p"),("R","q"),("S","r"),("T","s"),("U","t"),("V","u"),
      ("W","v"),("X","w"),("Y","x"),("Z","y"),
      ("0","9"),("1","0"),("2","1"),("3","2"),("4","3"),("5","4"),("6","5"),("7","6"),("8","7"),("9","8")
    )
    val regex = "[A-Za-z0-9]+"
    if(input.matches(regex)){
      var result = ""
      for(i <- 0 until(input.length)){
        result += matchMap(input.substring(i,i+1))
      }
      println(result)
    }
  }
}

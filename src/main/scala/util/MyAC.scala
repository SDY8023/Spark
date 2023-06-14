package util
import org.apache.spark.util.AccumulatorV2
class MyAC extends AccumulatorV2[Int,Int]{
  var sum:Int = 0
  var count:Int  = 0
  //
  override def isZero: Boolean = {
    return sum ==0 && count == 0
  }

  override def copy(): AccumulatorV2[Int, Int] = {
    val myAC = new MyAC
    myAC.sum = this.sum
    myAC.count = this.count
    myAC
  }

  override def reset(): Unit = {
    sum = 0
    count = 0
  }

  override def add(v: Int): Unit = {
    sum += v
    count += 1
  }

  override def merge(other: AccumulatorV2[Int, Int]): Unit = {
    other match {
      case o:MyAC => {
        sum += o.sum
        count += o.count
      }
      case _ =>
    }
  }

  override def value: Int = sum/count
}

package util

import org.apache.spark.sql.{Encoder, Encoders}
import org.apache.spark.sql.expressions.Aggregator
import util.caseClass.{AgeBuffer, User01}

class MyAveragUDAF2 extends Aggregator[User01,AgeBuffer,Double]{
  // 初始化缓存值
  override def zero: AgeBuffer = {
    AgeBuffer(0,0)
  }

  // 聚合
  override def reduce(b: AgeBuffer, a: User01): AgeBuffer = {
    b.sum = b.sum + a.age
    b.count += 1
    b
  }

  // 缓存之间合并
  override def merge(b1: AgeBuffer, b2: AgeBuffer): AgeBuffer = {
    b1.sum += b2.sum
    b1.count += b2.count
    b1
  }

  // 最终结果
  override def finish(reduction: AgeBuffer): Double = reduction.sum/reduction.count

  //DataSet 默认额编解码器，用于序列化，固定写法
  //自定义类型就是 product 自带类型根据类型选择
  override def bufferEncoder: Encoder[AgeBuffer] = Encoders.product

  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble

}

package util
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, IntegerType, LongType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
class MyAveragUDAF extends UserDefinedAggregateFunction{
  // 定义聚合函数输入的参数类型
  override def inputSchema: StructType = {
    StructType(Array(StructField("age",DoubleType)))
  }

  // 聚合函数缓冲区中值的数据类型(age,count)
  override def bufferSchema: StructType = {
    StructType(Array(StructField("sum",DoubleType),StructField("count",DoubleType)))
  }

  // 函数返回值的数据类型
  override def dataType: DataType = DoubleType

  // 稳定性：对于相同的输入是否一直返回相同的输出。
  override def deterministic: Boolean = true

  // 函数缓冲区初始化
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    // 存年龄的总和
    buffer(0) = 0.toDouble
    // 存年龄的个数
    buffer(1) = 0.toDouble
  }

  // 更新缓冲区中的数据
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    if(!input.isNullAt(0)){
      val sum = buffer.getDouble(0)
      val count = buffer.getDouble(1)
      buffer(0) = sum + input.getDouble(0)
      buffer(1) = count + 1
    }

  }

  // 合并缓冲区
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getDouble(0) + buffer2.getDouble(0)
    buffer1(1) = buffer1.getDouble(1) + buffer2.getDouble(1)
  }

  // 计算最终结果
  override def evaluate(buffer: Row): Any = buffer.getDouble(0)/buffer.getDouble(1)
}

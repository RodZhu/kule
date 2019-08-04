package com.spark.app.mllib.literm

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.{SparkConf, SparkContext, sql}

/**
  * 线性回归 模型训练
  * 线性回归终归是底层实现：
  *
  *
  *
  *
  *
  *
  *
  *
  */
object Driver {

  def main(args: Array[String]): Unit = {
    var conf = new SparkConf().setMaster("local").setAppName("iterm")
    var sc = new SparkContext(conf)

    var sqc = new sql.SparkSession.Builder().getOrCreate()
    var data = List("100|10 1000", "1010|10 10100", "200|100 1000", "150|10 800", "60|10 500")


    var datamodel = sc.makeRDD(data).map(f = x => {
      var arr = x.split("\\|")
      var arr1 = arr(1).split(" ")
      // 返回三元tuple。主要满足 dataframe数据格式
      (arr1(0).toDouble, arr1(1).toDouble, arr(0).toDouble)
    })

    // 装成df并设置列名
    var df = sqc.createDataFrame(datamodel).toDF("X1", "X2", "Y")

    // 转换成的df必须转成向量。
    //特征向量
    var features = Array("X1", "X2")
    // 获取向量转换器，并指定输入的特征列，特征列指所有的自变量
    var ass = new VectorAssembler().setInputCols(features).setOutputCol("features")

    // 把df转换成向量类型满足建模
    var dfvector = ass.transform(df)


    val model = new LinearRegression().setFeaturesCol("features")
      .setLabelCol("Y")
      .setFitIntercept(true) // true表示计算常量值
      .fit(dfvector) // 传入向量开始训练

    // Y = β1X1 + β2X2 + β0
    println(model.coefficients) // 打印β1 β2
    println(model.intercept) // 打印β0

    // 回带结果集
    val result = model.transform(dfvector)
    // round函数: 保留两位小数
    var selectResult = result.selectExpr("features","Y", "round(prediction, 2)")

    selectResult.foreach(println(_))


    //-----------------------------------------------------------------------------------------
    // 预测模型
    // 我们可以提供一些数据集，进行预测，预测之前必须将数据转换成向量
    // 将如最新的输入数据为 X1=10， X2= 111， 预测Y变量.因为y值不进行预测，因此我们不需要输入y值，这里用0代替
    var testData = sc.makeRDD(List((10.0, 111.0, 0.0)))
    var testdf = sqc.createDataFrame(testData).toDF("X1", "X2", "Y")
    var testDfvector = ass.transform(testdf)

    // 基于model的预测
    var testresult = model.transform(testDfvector).selectExpr("features","Y", "round(prediction, 2)")
    testresult.foreach(println(_))
  }
}

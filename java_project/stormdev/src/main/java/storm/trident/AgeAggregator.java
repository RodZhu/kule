package storm.trident;

import org.apache.storm.trident.operation.BaseAggregator;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Values;

/**
 * Created by rod on 2019/4/9.
 */

/**
 * BaseAggregator 最通用的aggregator可以发射多个Field，CombinerAggretor和ReducerAggregator只能发送的单个field
 */
public class AgeAggregator extends BaseAggregator<Integer> {

    private Integer sum = 0;

    public Integer init(Object o, TridentCollector tridentCollector) {
        return 0;
    }

    public void aggregate(Integer integer, TridentTuple tridentTuple, TridentCollector tridentCollector) {
        sum = tridentTuple.getIntegerByField("age") + sum;
        tridentCollector.emit(new Values("aaaa",sum));
    }

    public void complete(Integer integer, TridentCollector tridentCollector) {
        System.out.println("complete method: " + integer);
    }
}

package storm.trident.wordCount;

import org.apache.storm.trident.operation.BaseAggregator;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;

/**
 * Created by rod on 2019/4/10.
 */
public class PrintAggregator extends BaseAggregator<String> {



    public String init(Object batchId, TridentCollector collector) {
        return null;
    }

    public void aggregate(String val, TridentTuple tuple, TridentCollector collector) {

        System.out.println(tuple.getStringByField("wordStr") + ": " + tuple.getIntegerByField("count"));

    }

    public void complete(String val, TridentCollector collector) {

    }
}

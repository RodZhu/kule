package storm.trident.wordCount;

import org.apache.storm.trident.operation.BaseAggregator;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Values;

public class SplitAggregator extends BaseAggregator<String> {


    public String init(Object batchId, TridentCollector collector) {

        return null;
    }

    public void aggregate(String val, TridentTuple tuple, TridentCollector collector) {
        String line = tuple.getStringByField("line");
        String[] split = line.split(" ");
        for (String str : split) {
            collector.emit(new Values(str));
        }
    }

    public void complete(String val, TridentCollector collector) {

    }
}

package storm.trident.wordCount;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CountFunction extends BaseFunction {

    Map<String, Integer> countMap = new HashMap();

    public void execute(TridentTuple tuple, TridentCollector collector) {

        Fields fields = tuple.getFields();
        Iterator<String> it = fields.iterator();

        while(it.hasNext()) {
            String key = it.next();
            Object value = tuple.getValueByField(key);

            Integer count = countMap.get(value);
            if (count == null || count.equals(0)) {
                countMap.put(value.toString(), 1);
            } else {
                countMap.put(value.toString(), count + 1);
            }

            collector.emit(new Values(value, countMap.get(value)));

        }
    }
}

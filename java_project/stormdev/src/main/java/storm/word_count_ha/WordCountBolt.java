package storm.word_count_ha;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rod on 2019/3/19.
 */
public class WordCountBolt extends BaseRichBolt {

    private OutputCollector collector;

    private Map<String, Integer> countMap = new HashMap<String, Integer>();

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {

        try{
            String word = input.getStringByField("word");
            if (countMap.containsKey(word)) {
                countMap.put(word, countMap.get(word) + 1);
            } else {
                countMap.put(word, 1);
            }
            collector.emit(input, new Values(word, countMap.get(word)));
            int a = 1/0;
            collector.ack(input);
        }catch (Exception e) {
            collector.fail(input);
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "count"));
    }
}

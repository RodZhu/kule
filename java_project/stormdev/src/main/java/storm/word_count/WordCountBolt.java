package storm.word_count;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

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
        String word = input.getStringByField("word");

        if (countMap.containsKey(word)) {
            countMap.put(word, countMap.get(word) + 1);
        } else {
            countMap.put(word, 1);
        }

        collector.emit(new Values(word, countMap.get(word)));

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "count"));
    }
}

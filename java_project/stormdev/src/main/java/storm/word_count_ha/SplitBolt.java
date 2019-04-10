package storm.word_count_ha;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by rod on 2019/3/19.
 */
public class SplitBolt extends BaseRichBolt {

    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {
        try{
            String line = input.getStringByField("line");
            String[] lines = line.split(" ");
            for(String str : lines) {
                collector.emit(input, new Values(str));
            }
            collector.ack(input);
        }catch(Exception e) {
            System.out.println(e);
            collector.fail(input);
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }


}

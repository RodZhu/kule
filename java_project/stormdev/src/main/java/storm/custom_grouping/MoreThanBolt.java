package storm.custom_grouping;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import java.util.Map;

public class MoreThanBolt extends BaseRichBolt {

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

    }

    public void execute(Tuple input) {
        Integer number = input.getIntegerByField("number");
        System.out.println("More: " + number);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}

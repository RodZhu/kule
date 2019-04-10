package storm.custom_grouping;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
public class NumberTopology {

    public static void main(String[] args) {
        Config conf = new Config();

        RandomSpout rs = new RandomSpout();

        LessThanBolt ltb = new LessThanBolt();

        MoreThanBolt mtb = new MoreThanBolt();

        TopologyBuilder tb = new TopologyBuilder();

        tb.setSpout("spout", rs);
        // 必须要指定 stream id，和spout的对应。
        tb.setBolt("lessThanBolt", ltb).shuffleGrouping("spout", "lessThan");
        tb.setBolt("moreThanBolt", mtb).shuffleGrouping("spout", "moreThan");

        StormTopology topology = tb.createTopology();

        LocalCluster lc = new LocalCluster();
        lc.submitTopology("custom", conf, topology);
    }
}

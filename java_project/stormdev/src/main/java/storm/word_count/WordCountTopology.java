package storm.word_count;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

/**
 * Created by rod on 2019/3/19.
 */
public class WordCountTopology {

    public static void main(String[] args) {
        Config config = new Config();
        WordCountSpout wcs = new WordCountSpout();
        SplitBolt sb = new SplitBolt();
        WordCountBolt wcb = new WordCountBolt();
        PrintBolt pb = new PrintBolt();

        TopologyBuilder tb = new TopologyBuilder();
        tb.setSpout("spout", wcs);

        tb.setBolt("splitbolt", sb).globalGrouping("spout");

        tb.setBolt("wordcount", wcb).globalGrouping("splitbolt");

        tb.setBolt("printbolt", pb).globalGrouping("wordcount");

        StormTopology topology = tb.createTopology();

        LocalCluster lc = new LocalCluster();

        lc.submitTopology("wordcounttopology", config, topology);
    }
}

package storm.word_count_ha;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
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
        // tb.setBolt("splitbolt", sb).fieldsGrouping("spout",new Fields("dddd"));
        tb.setBolt("wordcount", wcb).globalGrouping("splitbolt");

        tb.setBolt("printbolt", pb).globalGrouping("wordcount");

        StormTopology topology = tb.createTopology();

        LocalCluster lc = new LocalCluster();

        lc.submitTopology("wordcounttopology", config, topology);
    }
}

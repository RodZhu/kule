package storm.trident;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.testing.FixedBatchSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * Created by rod on 2019/4/9.
 */
public class InfoTopogloy {
    public static void main(String[] args) {
        Config config = new Config();

        // -- 利用trident提供数据源，可以批量发送tuple
        // -- 参数：1 tuple的key字段，2 每批发送最大条数， 3 数据
        FixedBatchSpout spout = new FixedBatchSpout(new Fields("name", "age"), 2,
                new Values("tom", 23),
                new Values("rose", 25),
                new Values("jary", 28),
                new Values("jim", 35));

        // -- 模拟循环发送数据。
        spout.setCycle(true);
        TridentTopology topology = new TridentTopology();

        // -- 绑定数据源
        topology.newStream("spout", spout).
                // filter 组件可以根据条件过滤字段，有选择性的向下游传递tuple
                each(new Fields("name", "age"), new NameFilter()).
                // function组建可以将上游接过来的tuple基础上，追加新的组建
                each(new Fields("name", "age"), new GenderFunction(), new Fields("gender")).
                // 新加的组建必须也要在下游写出来的。
                each(new Fields("name", "age", "gender"), new PrintFilter());


        LocalCluster lc = new LocalCluster();
        lc.submitTopology("info_topology", config, topology.build());
    }
}

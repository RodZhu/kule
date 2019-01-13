package relation.reduce;

import model.RelationDemo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.awt.*;
import java.io.IOException;

/**
 * Created by rod on 2019/1/10.
 */
public class RelationReduce extends Reducer<Text, RelationDemo, Text, Text>{

    @Override
    protected void reduce(Text key, Iterable<RelationDemo> values, Context context) throws IOException, InterruptedException {
        String grandson = "";
        String grandfather = "";

        String grandson_1 = "";
        String grandfather_1 = "";
        /**
         * 福临 皇太极
         福全 福临
         玄烨 福临
         */
        for(RelationDemo demo: values) {
            System.out.println("ddddd" + demo.getFather() + " - " + demo.getSon());
            if (demo.getFather().equals("皇太极")) {
                grandson = grandson + "" +demo.getSon();
            }

            if (demo.getSon().equals("皇太极")) {
                grandfather = demo.getFather();
            }

            if (demo.getSon().equals("福临")) {
                grandfather_1 = demo.getFather();
            }

            if (demo.getFather().equals("福临")) {
                grandson_1 = grandson_1 + demo.getSon();
            }
        }
        context.write(new Text(grandfather + ": " + grandson), new Text(grandfather_1 +": " + grandson_1));
    }
}

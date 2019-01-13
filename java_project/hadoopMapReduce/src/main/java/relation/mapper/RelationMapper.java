package relation.mapper;

import model.RelationDemo;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by rod on 2019/1/10.
 */
public class RelationMapper extends Mapper<LongWritable, Text, Text,RelationDemo> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] ss = value.toString().split(" ");
        RelationDemo demp = new RelationDemo();
        demp.setSon(ss[0]);
        demp.setFather(ss[1]);
        context.write(new Text(ss[1]), demp);
    }
}

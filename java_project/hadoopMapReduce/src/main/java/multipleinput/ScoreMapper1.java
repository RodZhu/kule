package multipleinput;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 此Mapper用于处理下面的格式文件：
 * jim	math 45 english 68
   rose	math 75 english 100
   …………
 * @author ysq
 *
 */
public class ScoreMapper1 extends Mapper<LongWritable,Text,Text,Text>{

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		String name=line.split("\t")[0];
		String score=line.split("\t")[1];
		
		context.write(new Text(name),new Text(score));
	}
}

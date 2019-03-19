package output;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DFSOutputStream;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 自定义格式输出组件，
 * 此组件决定最后结果文件的格式
 * @author ysq
 *
 * @param <K>
 * @param <V>
 */
public class AuthOutputFormat<K,V> extends FileOutputFormat<K, V>{

	@Override
	public RecordWriter<K, V> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
		//--获取输出结果文件路径
		Path path=super.getDefaultWorkFile(job,"");
		Configuration conf=job.getConfiguration();
		FileSystem system=path.getFileSystem(conf);
		
		//--获取输出流
		FSDataOutputStream out=system.create(path);
		
		return new AuthRecordWriter<K,V>(out);
	}

}

package output;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class AuthRecordWriter<K,V> extends RecordWriter<K, V>{
	
	private FSDataOutputStream out;

	public AuthRecordWriter(FSDataOutputStream out) {
		this.out=out;
	}

	@Override
	public void write(K key, V value) throws IOException, InterruptedException {
		//--将输出key写到文件里。如果只有Mapper组件，则是Mapper的输出key
		//--如果既有Mapper又有Reducer，则是Reducer的输出key
		out.write(key.toString().getBytes());
		//--输出 kv分隔符，默认是Tab制表符
		out.write("|".getBytes());
		//--将输出value写到文件中
		out.write(value.toString().getBytes());
		//--输出行与行之间的分隔符
		out.write("\n".getBytes());
		
		
		
	}

	@Override
	public void close(TaskAttemptContext context) throws IOException, InterruptedException {
		out.close();
		
	}

}

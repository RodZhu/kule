package model;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by rod on 2019/1/10.
 */
public class FlowDemo implements Writable {

    private String phone;

    private String name;

    private String addr;

    // hadoop 尽量不要使用包装类，如Integer等，如果要使用则要进行非空判断，否则在执行任务的时候可能出现空指针异常。
    private int flow;

    /**
     *  序列发和反序列发顺序一致
     * @param var1
     * @throws IOException
     */
    // 序列化
    public void write(DataOutput var1) throws IOException {
        var1.writeUTF(phone);
        var1.writeUTF(name);
        var1.writeUTF(addr);
        var1.writeInt(flow);
    }

    // 反序列发
    public void readFields(DataInput var1) throws IOException {
        this.phone = var1.readUTF();
        this.name = var1.readUTF();
        this.addr = var1.readUTF();
        this.flow = var1.readInt();
    };

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    @Override
    public String toString() {
        return "sum: " + flow;
    }
}

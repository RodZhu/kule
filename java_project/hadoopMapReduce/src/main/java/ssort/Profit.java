package ssort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Profit implements WritableComparable<Profit>{
	
	private int month;
	private String name;
	private int profit;


    public void write(DataOutput out) throws IOException {
        out.writeInt(month);
        out.writeUTF(name);
        out.writeInt(profit);
    }

    public void readFields(DataInput in) throws IOException {
		this.month=in.readInt();
		this.name=in.readUTF();
		this.profit=in.readInt();
		
	}

	public int compareTo(Profit o) {
		//--按月份做升序
		int result=this.month-o.month;
		if(result==0){
			//--按利润做降序
			return o.profit-this.profit;
		}else{
			return result;
		}
		
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "Profit [month=" + month + ", name=" + name + ", profit=" + profit + "]";
	}
	

}

package storm.trident;

import org.apache.storm.trident.operation.CombinerAggregator;
import org.apache.storm.trident.tuple.TridentTuple;

/**
 * Created by rod on 2019/4/9.
 */
public class AgeCombinerAggregator implements CombinerAggregator<Integer> {

    /**
     *  该组建第一个被调用的方法，返回一个初始值。
     *  value值会传递给combiner 方法
     * @return
     */
    public Integer zero() {
        return 0;
    }

    /**
     *  该组建第二个被调用的方法。会接收上游的tuple，
     *  此方法返回val2，传递给combine。
     * @param tridentTuple
     * @return
     */
    public Integer init(TridentTuple tridentTuple) {
        Integer age = tridentTuple.getIntegerByField("age");
        return age;
    }


    // 统计所有人员的年龄和，其工作原理

    /**
     * combine 方法返回的值，会作为下一次combine的输入参数。也就是说当第二次执行combine方法。val1 = val1(第一次)+val2(第一次)。
     * @param val1
     * @param val2
     * @return
     */
    public Integer combine(Integer val1, Integer val2) {
        return val1 + val2;
    }
}

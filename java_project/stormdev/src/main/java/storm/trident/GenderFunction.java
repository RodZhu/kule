package storm.trident;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Values;

/**
 * Created by rod on 2019/4/9.
 */

// Funciton组建可以在原有的基础上追加新的字段。
public class GenderFunction extends BaseFunction {

    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        tridentCollector.emit(new Values("male"));
    }
}

package hadoop;


import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.apache.htrace.fasterxml.jackson.databind.JsonNode;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rod on 2019/7/5.
 */
public class JsonToYaml {



    public static void main(String[] args) throws Exception {

        User father = new User();
        father.setName("father");
        father.setAge("50");
        Map<String,String> map= new HashMap();
        map.put("home_addr", "ShangHai");
        map.put("hometown", "AnHui");
        User son = new User();
        son.setName("son");
        son.setAge("20");
        son.setInfo(map);

        father.setInfo(map);
        father.setSon(son);



        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        options.setAllowReadOnlyProperties(true);
        Yaml yaml = new Yaml(options);
        String s = new ObjectMapper().writeValueAsString(father);

        String dump = yaml.dumpAsMap(s);
//        yaml.
//
//   System.out.println(dump);
         System.out.println(dump);

    }

    public static class User {
        private String name;
        private String age;
        private Map<String,String> info;
        private User son;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Map<String, String> getInfo() {
            return info;
        }

        public void setInfo(Map<String, String> info) {
            this.info = info;
        }

        public User getSon() {
            return son;
        }

        public void setSon(User son) {
            this.son = son;
        }
    }
}

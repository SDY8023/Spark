package Kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @ClassName MyPartitioner
 * @Description
 * 1. 实现接口Partitioner
 * 2. 实现3个方法：partition,close,configure
 * 3. 编写partition方法，返回分区号
 * @Author SDY
 * @Date 2022/11/20 21:00
 **/
public class MyPartitioner implements Partitioner {
    /**
     *
     * @param topic
     * @param key
     * @param keyBytes
     * @param value
     * @param valueBytes
     * @param cluster
     * @return
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 获取消息
        String msgValue = value.toString();
        // 创建partition
        int partition;
        // 判断消息是否包含sdy
        if(msgValue.contains("sdy")){
            partition = 0;
        }else{
            partition = 1;
        }
        return partition;
    }

    // 关闭资源
    @Override
    public void close() {

    }

    // 配置方法
    @Override
    public void configure(Map<String, ?> configs) {

    }
}

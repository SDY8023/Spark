package Kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2022/11/20 19:20
 **/
public class Test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        // 添加kafka配置信息
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"bigdata01:9092");
        // k,v序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        // 创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // 调用send方法，发送消息
        for (int i = 0;i < 10;i ++){
            kafkaProducer.send(new ProducerRecord<>("test2","sdy"+i)).get();
        }
        kafkaProducer.close();
    }
}






















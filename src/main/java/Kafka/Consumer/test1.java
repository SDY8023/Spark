package Kafka.Consumer;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @ClassName test1
 * @Description 独立消费者
 * @Author SDY
 * @Date 2022/11/27 21:27
 **/
public class test1 {
    public static void main(String[] args) {
        // 1 创建消费者的配置对象
        Properties properties = new Properties();
        // 2 给消费者配置对象添加参数
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"bigdata01:9092");
        // 3 配置序列化 必须有
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        // 4 配置消费者组，必须
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"sdyTest1");

        // 5 创建消费者对象
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // 6 注册要消费的主题(可以消费多个主题)
        ArrayList<String> topics = new ArrayList<>();
        topics.add("test5");
        kafkaConsumer.subscribe(topics);

        // 7 拉取数据打印
        while (true){
            // 设置消费时间间隔
            ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.ofSeconds(1));
            // 打印消费到的数据
            for(ConsumerRecord<String,String> consumerRecord : poll){
                System.out.println(consumerRecord);
            }
        }


    }
}

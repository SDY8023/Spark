package Kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.protocol.types.Field;
import org.codehaus.jackson.map.ser.std.StringSerializer;

import java.util.Properties;

/**
 * @ClassName Test3
 * @Description
 * @Author SDY
 * @Date 2022/11/20 20:32
 **/
public class Test3 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"bigdata01:9092");

        // 序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        for(int i=0;i<10;i++){
            kafkaProducer.send(new ProducerRecord<>("test2", 1, "", "sdy" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception == null){
                        System.out.println("主题："+metadata.topic()+"->"+"分区："+metadata.partition());
                    }else{
                        exception.printStackTrace();
                    }
                }
            });
        }
        kafkaProducer.close();
    }
}

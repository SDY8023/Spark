package Kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @ClassName test5
 * @Description
 * @Author SDY
 * @Date 2022/11/20 21:06
 **/
public class test5 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"bigdata01:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        // 添加自定义分区
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"Kafka.MyPartitioner");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        for (int i = 0; i < 10; i++) {
            String message;
            if(i%2==0){
                message = "sdy";
            }else{
                message = "abc";
            }
            kafkaProducer.send(new ProducerRecord<>("test2", message + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception == null){
                        System.out.println("主题:"+metadata.topic()+"->分区->"+metadata.partition());
                    }else{
                        exception.printStackTrace();
                    }
                }
            });
        }
        kafkaProducer.close();

    }
}

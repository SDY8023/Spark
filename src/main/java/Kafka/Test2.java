package Kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @ClassName Test2
 * @Description 带回调函数的API代码
 * @Author SDY
 * @Date 2022/11/20 19:25
 **/
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"bigdata01:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        // 创建生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        // 调用send方法,发送消息
        for(int i = 0;i < 10; i++){
            kafkaProducer.send(new ProducerRecord<>("test2", "sdy" + i), new Callback() {
                // 该方法在Producer收到ack调用，为异步调用
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(e == null){
                        System.out.println("主题:"+recordMetadata.topic() + "->" + "分区:"+recordMetadata.partition());
                    }else{
                        // 出现异常打印
                        e.printStackTrace();
                    }
                }
            });
            Thread.sleep(2);
        }
        // 关闭资源
        kafkaProducer.close();

    }
}

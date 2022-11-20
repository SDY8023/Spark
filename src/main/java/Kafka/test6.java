package Kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @ClassName test6
 * @Description
 * @Author SDY
 * @Date 2022/11/20 21:17
 **/
public class test6 {
    public static void main(String[] args) {
        // 增加kafka配置文件
        Properties properties = new Properties();

        // 指定kafka地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"bigdata01:9092,bigdata02:9092,bigdata03:9092");
        // 指定k,v序列化类
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        // batch.size:批次大小，默认16k
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        // linger.ms: 等待时间,默认0
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1000000);
        // 缓冲区大小，默认32M
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        // 压缩格式：默认none,可配置gazip、snappy、lz4、zstd
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");

        // 生成kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        // 循环发送数据
        for(int i=0;i < 10; i++){
            kafkaProducer.send(new ProducerRecord<String, String>("test2", "f", "sdy" + i), new Callback() {
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

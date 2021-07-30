import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.Collections;
import java.util.Properties;

public class Consumer extends ShutdownableThread {
	private final String topic;
	private final KafkaConsumer<Integer, String> consumer;

	public Consumer(String topic) {
		super("KafkaConsumerExample", false);
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		consumer = new KafkaConsumer<>(props);
		this.topic = topic;
	}

	@Override
	public void doWork() {
		consumer.subscribe(Collections.singletonList(this.topic));
		ConsumerRecords<Integer, String> records = consumer.poll(1000);
		for (ConsumerRecord<Integer, String> record : records) {
//			System.out.println(new StringBuilder("Consumer: message N").append(record.key())
//					.append(" has been received at offset N").append(record.offset()));
			System.out.println(record.value());
		}
	}

//	@Override
//	public String name() {
//		return null;
//	}
//
//	@Override
//	public boolean isInterruptible() {
//		return false;
//	}
}
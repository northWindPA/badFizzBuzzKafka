import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer extends Thread {
	private final KafkaProducer<Integer, String> producer;
	private final String topic;
	private final Boolean isAsync;

	public Producer(String topic, Boolean isAsync) {
		Properties props = new Properties();
		props.put("bootstrap.servers", KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
		props.put("client.id", "DemoProducer");
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
		this.topic = topic;
		this.isAsync = isAsync;
	}

	public void run() {
		int range = 1_000_000_000;
		int messageNo = 1;
		int i = 1;
		for (; i < range - 15; i += 15) {
			StringBuilder messageStr = new StringBuilder();
			messageStr.append(i).append("\n").append(i + 1).append("\n").append("Fizz").append("\n")
				.append(i + 3).append("\n").append("Buzz").append("\n").append("Fizz").append("\n")
				.append(i + 6).append("\n").append(i + 7).append("\n").append("Fizz").append("\n")
				.append("Buzz").append("\n").append(i + 10).append("\n").append("Fizz").append("\n")
				.append(i + 12).append("\n").append(i + 13).append("\n").append("FizzBuzz").append("\n");
			try {
				producer.send(new ProducerRecord<>(topic, messageNo, messageStr.toString())).get();
//				System.out.println(new StringBuilder("Producer: sent message N").append(messageNo));
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			++messageNo;
		}
	}
}

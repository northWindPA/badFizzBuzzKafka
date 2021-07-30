//public class FizzBuzz {
//	public void FizzBuzzMethod(int range) {
//		long startTime = System.currentTimeMillis();
//		int i = 1;
//		for (; i < range - 15; i += 15) {
//			StringBuilder sb = new StringBuilder();
//			sb.append(i).append("\n").append(i + 1).append("\n").append("Fizz").append("\n")
//			.append(i + 3).append("\n").append("Buzz").append("\n").append("Fizz").append("\n")
//			.append(i + 6).append("\n").append(i + 7).append("\n").append("Fizz").append("\n")
//			.append("Buzz").append("\n").append(i + 10).append("\n").append("Fizz").append("\n")
//			.append(i + 12).append("\n").append(i + 13).append("\n").append("FizzBuzz").append("\n");
//			System.out.print(sb);
//		}
//		while (i <= range) {
//			if (i % 3 == 0) {
//				System.out.println("Fizz");
//			} else if (i % 5 == 0) {
//				System.out.println("Buzz");
//			} else {
//				System.out.println(i);
//			}
//			i++;
//		}
//		long endTime = System.currentTimeMillis();
//		System.out.println("Total execution time: " + (endTime - startTime) + " ms");
//	}
//}

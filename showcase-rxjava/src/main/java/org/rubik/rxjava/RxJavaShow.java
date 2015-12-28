package org.rubik.rxjava;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import rx.Observable;

public class RxJavaShow {
	private static String[] personArray = new String[]{"张晓明", "张鹏", "王义军", "陈洪斌", "李如钢",
			"刘颖刚", "李萍", "黎明", "何劲帆", "梅雪飞", "王义军", "王义军", "沈进军", "陈洪斌"};
	private static List<String> personList = Arrays.asList("张晓明", "张鹏", "李如钢",
			"刘颖刚", "李萍", "黎明", "何劲帆", "梅雪飞", "王义军", "沈进军", "宋崇峰", "陈洪斌", "王展明",
			"王一帆", "陈牡蓉", "徐扬", "华永兵", "聂耿");

	public static void main(String[] args) throws IOException {
		// 处理，过滤，跳过，遍历
		mapFilterSkipForEach();
		
		// 统计字符串出现次数
		filterCount("王义军");
		
		// 发布
		subscribe();
		
		// 计算所有字符的总长度
		mapReduce();
		
		// top K - 词频
		topK();
	}
	

	private static void mapFilterSkipForEach() {
		// JDK8
		personList.stream().map(person -> person + " - 高三(二)班").filter(person -> person.startsWith("王")).skip(2).forEach(System.out::println);
		
		// RxJava
		Observable.from(personList).map(person -> person + " - 高三(二)班").filter(person -> person.startsWith("王")).skip(2).forEach(System.out::println);;

		// 求和
		Integer[] numbers = new Integer[]{1, 2, 3, 4, 5};
		Observable.from(numbers).map(c -> c + 1).reduce((a, b) -> a + b).forEach(System.out::println);
		
//		Observable.from(numbers).groupBy(i -> i).map(s -> s.getKey()).subscribe(System.out::println);;
	}

	private static void filterCount(String searchStr) {
		System.out.printf("[JDK8] - %s count: %d %n", searchStr, personList.stream().filter(person -> person.equals(searchStr)).count());
		Observable.from(personArray).filter(person -> person.equals(searchStr)).count().subscribe(count -> System.out.printf("[RxJava] - %s count: %d %n", searchStr, count));
	}
	
	private static void subscribe() {
		Observable.from(personArray).subscribe(person -> {if(person.equals("王义军")) {System.out.println(person);}});
	}

	private static void mapReduce() {
		Observable.from(personArray).map(person -> person.length()).reduce((a, b) -> a + b).subscribe(result -> System.out.printf("字符串总长度:%d %n", result));
	}
	
	private static void topK() {
		Observable.from(personArray).groupBy(word -> word).map(s -> {
			return s.count().map(count -> s.getKey() + ":" + count);
		}).toBlocking().forEach(item -> System.out.println(item));

//		Observable.from(personArray).groupBy(word -> word).subscribe(item -> System.out.println(item));
	}
}
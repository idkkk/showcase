package org.rubik.sandbox.jdk8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import static java.util.stream.Collectors.*;

public class CollectionApp {
	private static List<String> persons = Arrays.asList("张晓明", "张鹏", "李如钢",
			"刘颖刚", "李萍", "黎明", "何劲帆", "梅雪飞", "王义军", "沈进军", "宋崇峰", "陈洪斌", "王展明",
			"王一帆", "陈牡蓉", "徐扬", "华永兵", "聂耿");

	private static List<String> letters = Arrays.asList("ZhangXiaoMing",
			"ZhangPeng", "LiRuGang", "LiuYingGang", "LiPing", "LiMing",
			"HeJinFan", "MeiXueFei", "WangYiJun", "ShenJinJun",
			"SongChongFeng", "ChenHongBin", "WangZhanMing", "WangYiFan",
			"ChenMuRong", "XuYang", "HuaYongBin", "WangYiJun", "NieGeng");

	public static void forEach() {
		System.out.println("==========  1 ============");
		for (int i = 0; i < persons.size(); i++) {
			System.out.println(persons.get(i));
		}

		System.out.println("==========  2 ============");
		for (String item : persons) {
			System.out.println(item);
		}

		System.out.println("==========  3 ============");
		persons.forEach(new Consumer<String>() {
			public void accept(String item) {
				System.out.println(item);
			}
		});

		System.out.println("==========  4 ============");
		persons.forEach(item -> System.out.println(item));

		System.out.println("==========  5 ============");
		persons.forEach(System.out::println);
	}

	public static void toUpper() {
		System.out.println("==========  toUpperCase ============");
		letters.stream().map(item -> item.toUpperCase())
				.forEach(item -> System.out.println(item));
	}

	public static void counter() {
		System.out.println("==========  Counter ============");
		Map<String, Long> result = letters.stream().collect(groupingBy(word -> word, counting()));
		System.out.println(result);
	}

	public static List<String> toFilter(List<String> source) {
		System.out.println("==========  filter ============");
		return source.stream().filter(item -> item.startsWith("Wang"))
				.collect(toList());
	}

	public static void dry() {
		System.out.println("==========  dry ============");
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
		final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");
		final Predicate<String> startsWithN = name -> name.startsWith("N");
		final long countFriendsStartN = friends.stream().filter(startsWithN).count();
		final long countComradesStartN = comrades.stream().filter(startsWithN).count();
		final long countEditorsStartN = editors.stream().filter(startsWithN).count();

		System.out.println(String.format(
				"Friends: %d, Comrades: %d, Comrades: %d", countFriendsStartN,
				countComradesStartN, countEditorsStartN));
	}

	public static void main(String[] args) throws IOException {
		forEach();
		toUpper();

		toFilter(letters).stream().forEach(System.out::println);
		counter();
		
		Files.list(Paths.get("/Users/xiajinxin/ABC")) .forEach(System.out::println);
		
		for (String dirElement: new File("/Users/xiajinxin/ABC").list()) {
			System.out.println(dirElement);
		}

		dry();
	}
}

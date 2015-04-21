package org.rubik.sandbox.jdk8;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import org.rubik.sandbox.jdk8.model.Student;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class StudentApp {
	private static final int LENGTH = 1000;
	private static List<Student> students = Lists.newArrayList();
	private static int[] scores = new int[LENGTH];
	private static int[] ages = new int[LENGTH];

	static {
		initAges();
		initScores();
	}

	public static void init() {
		for (int i = 0; i < LENGTH; i++) {
			Student student = new Student();
			student.setId(Strings.padStart(String.valueOf(i), 4, '0'));
			student.setName("学生" + i);
			student.setAge(ages[i]);
			student.setScore(scores[i]);
			students.add(student);
		}
	}

	private static int[] initAges() {
		Random random = new Random();
		IntStream intStream = random.ints(15, 25);
		ages = intStream.limit(LENGTH).toArray();
		return ages;
	}

	private static int[] initScores() {
		Random random = new Random();
		IntStream intStream = random.ints(260, 750);
		scores = intStream.limit(LENGTH).toArray();
		return scores;
	}

	public static void main(String[] args) {
		init();

		Optional<Student> maxScoreStudent = students.stream().max((s1, s2) -> s1.getScore() - s2.getScore());
		System.out.println(maxScoreStudent.get().toString());
	}
}

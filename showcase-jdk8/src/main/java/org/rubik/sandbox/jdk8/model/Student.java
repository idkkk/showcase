package org.rubik.sandbox.jdk8.model;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;


public class Student {
	private String id;
	private String name;
	private int age;
	private int score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int compareTo(Student s) {
		return ComparisonChain.start()
				.compare(this.id, s.getId())
				.compare(this.getName(), s.getName())
				.compare(this.age, s.getAge())
				.compare(this.score, s.getScore())
				.result();
	}

	public String toString() {
		return Objects.toStringHelper(this)
				.omitNullValues()
				.add("id", id)
				.add("name", name)
				.add("age", age)
				.add("score", score)
				.toString();
	}
}

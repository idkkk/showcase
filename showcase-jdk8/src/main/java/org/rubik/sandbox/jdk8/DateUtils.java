package org.rubik.sandbox.jdk8;

import java.time.LocalDate;

public abstract class DateUtils {

	public static LocalDate getTheBeforeDay(LocalDate theDay) {
		return theDay.minusDays(1);
	}

	public static LocalDate getTheNextDay(LocalDate theDay) {
		return theDay.plusDays(1);
	}
}

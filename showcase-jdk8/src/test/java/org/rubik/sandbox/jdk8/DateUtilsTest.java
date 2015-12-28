package org.rubik.sandbox.jdk8;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testGetTheBeforeDay() {
		String theBeforeDay = DateUtils.getTheBeforeDay(LocalDate.now()).format(DateTimeFormatter.ISO_DATE);
		assertEquals("2015-12-22", theBeforeDay);
	}

	@Test
	public void testGetTheNextDay() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate theDay = LocalDate.parse("20150430", dateTimeFormatter);
		String theNextDay = DateUtils.getTheNextDay(theDay).format(dateTimeFormatter);
		assertEquals("20150501", theNextDay);
	}
}

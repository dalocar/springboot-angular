package com.belike.core.util.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class DateUtil {

	public static final String FORMAT_DATE = "yyyyMMdd";
	public static final String FORMAT_TIME = "HHmmss";
	public static final String FORMAT_DATE_TIME = FORMAT_DATE + FORMAT_TIME;

	public static Long nowInLong() {
		return parseLocalDateTimeToLong(nowInLocalDateTime());
	}

	public static LocalDateTime nowInLocalDateTime() {
		return LocalDateTime.now();
	}

	public static LocalDateTime getLocalDateTime(final Long dateTime) {
		return getLocalDateTime(dateTime, FORMAT_DATE_TIME);
	}

	public static LocalDateTime getLocalDateTime(final Long dateTime, final String format) {
		if (dateTime == null) {
			return null;
		}
		return getLocalDateTime(dateTime.toString(), format);
	}

	public static LocalDateTime getLocalDateTime(final String dateTime, final String format) {
		LocalDateTime result;
		if (dateTime == null || dateTime.isEmpty()) {
			result = null;
		} else {
			try {
				result = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
			} catch (Exception e) {
				throw e;
			}
		}
		return result;
	}

	public static Long parseLocalDateTimeToLong(final LocalDateTime localDateTime) {
		return parseLocalDateTimeToLong(localDateTime, FORMAT_DATE_TIME);
	}

	public static Long parseLocalDateTimeToLong(final LocalDateTime localDateTime, final String format) {
		Long result;
		if (localDateTime == null) {
			result = null;
		} else {
			try {
				result = Long.parseLong(parseLocalDate(localDateTime, format));
			} catch (Exception e) {
				throw e;
			}
		}
		return result;
	}

	public static LocalDate getLocalDate(final Integer date) {
		return getLocalDate(date, FORMAT_DATE);
	}

	public static LocalDate getLocalDate(final Integer date, final String format) {
		if (date == null) {
			return null;
		}
		return getLocalDate(date.toString(), format);
	}

	public static LocalDate getLocalDate(final String date, final String format) {
		LocalDate result;
		if (date == null || date.isEmpty()) {
			result = null;
		} else {
			try {
				result = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
			} catch (Exception e) {
				throw e;
			}
		}
		return result;
	}

	public static Integer parseLocalDateToInteger(final LocalDate localDate) {
		return parseLocalDateToInteger(localDate, FORMAT_DATE);
	}

	public static Integer parseLocalDateToInteger(final LocalDate localDate, final String format) {
		Integer result;
		if (localDate == null) {
			result = null;
		} else {
			try {
				result = Integer.parseInt(parseLocalDate(localDate, format));
			} catch (Exception e) {
				throw e;
			}
		}
		return result;
	}

	public static String parseLocalDate(final TemporalAccessor date, final String format) {
		return DateTimeFormatter.ofPattern(format).format(date);
	}

	public static LocalTime getLocalTime(final String time) {
		return getLocalTime(time, FORMAT_TIME);
	}

	public static LocalTime getLocalTime(final String time, final String format) {
		LocalTime result;
		if (time == null || time.isEmpty()) {
			result = null;
		} else {
			try {
				result = LocalTime.parse(time, DateTimeFormatter.ofPattern(format));
			} catch (Exception e) {
				throw e;
			}
		}
		return result;
	}

	public static String parseLocalTimeToString(final LocalTime localTime) {
		return parseLocalTimeToString(localTime, FORMAT_TIME);
	}

	public static String parseLocalTimeToString(final LocalTime localTime, final String format) {
		String result;
		if (localTime == null) {
			result = null;
		} else {
			try {
				result = DateTimeFormatter.ofPattern(format).format(localTime);
			} catch (Exception e) {
				throw e;
			}
		}
		return result;
	}

	public static int workingDaysBetween(final LocalDate start, final LocalDate end) {
		int count = 0;
		LocalDate curr = start.plusDays(0);
		while (curr.isBefore(end)) {
			if (!DayOfWeek.SATURDAY.equals(curr.getDayOfWeek()) && !DayOfWeek.SUNDAY.equals(curr.getDayOfWeek())) {
				count++;
			}
			curr = curr.plusDays(1);
		}
		return count;
	}

	public static Date getDate(final LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static Date getDate(final LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
package com.carrascolimited.springboot.dozer.converters;

import java.time.LocalDateTime;

import org.dozer.DozerConverter;
import org.springframework.stereotype.Component;

import com.belike.core.util.date.DateUtil;

@Component
public class LocalDateTimeDozerConverter extends DozerConverter<Long, LocalDateTime> {

	public LocalDateTimeDozerConverter() {
		super(Long.class, LocalDateTime.class);
	}

	@Override
	public LocalDateTime convertTo(final Long source, final LocalDateTime destination) {
		return DateUtil.getLocalDateTime(source);
	}

	@Override
	public Long convertFrom(final LocalDateTime source, final Long destination) {
		return DateUtil.parseLocalDateTimeToLong(source);
	}

}
package com.carrascolimited.springboot.dozer.converters;

import java.time.LocalDate;

import org.dozer.DozerConverter;
import org.springframework.stereotype.Component;

import com.belike.core.util.date.DateUtil;

@Component
public class LocalDateDozerConverter extends DozerConverter<Integer, LocalDate> {

	public LocalDateDozerConverter() {
		super(Integer.class, LocalDate.class);
	}

	@Override
	public LocalDate convertTo(final Integer source, final LocalDate destination) {
		return DateUtil.getLocalDate(source);
	}

	@Override
	public Integer convertFrom(final LocalDate source, final Integer destination) {
		return DateUtil.parseLocalDateToInteger(source);
	}

}
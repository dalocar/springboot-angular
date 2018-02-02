package com.carrascolimited.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class DozerService {

	@Autowired
	private Mapper mapper;

	@SuppressWarnings("unchecked")
	public <T, U> U map(final T source, final Class<U> destType) {
		U dest = null;
		if (source != null) {
			if (destType.isInstance(source)) {
				dest = (U) source;
			} else {
				dest = mapper.map(source, destType);
			}
		}
		return dest;
	}

	public <T, U> void map(final T source, final U dest) {
		if (source != null) {
			mapper.map(source, dest);
		}
	}

	@SuppressWarnings("unchecked")
	public <T, U> List<U> mapList(final List<T> source, final Class<U> destType) {
		List<U> dest = new ArrayList<>();
		for (T element : source) {

			if (destType.isInstance(element)) {
				dest.add((U) element);
			} else {
				dest.add(mapper.map(element, destType));
			}
		}
		return dest;
	}
}

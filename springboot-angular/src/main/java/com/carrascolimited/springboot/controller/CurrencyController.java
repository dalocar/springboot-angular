package com.carrascolimited.springboot.controller;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carrascolimited.springboot.domain.Exchange;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/exchange")
@Slf4j
public class CurrencyController {

	private final static String EXCHANGE_URL = "https://www.bitstamp.net/api/v2/ticker/";

	@RequestMapping(value = "/{currency}", method = RequestMethod.GET)
	public String getCurrency(@PathVariable("currency") String currency) {
		try {
			log.info("Inside currency value: " + currency);
			Reader reader = new InputStreamReader(new URL(EXCHANGE_URL + currency).openStream());
			return String.valueOf(new GsonBuilder().create().fromJson(reader, Exchange.class).getLast());

		} catch (Exception e) {
		}
		return null;
	}

}
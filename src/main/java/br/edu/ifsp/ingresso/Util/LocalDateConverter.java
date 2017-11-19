package br.edu.ifsp.ingresso.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

import br.com.caelum.vraptor.Convert;

import br.com.caelum.vraptor.converter.Converter;

@Convert(Date.class)
@ApplicationScoped
public class LocalDateConverter implements Converter<Date> {
	public Date convert(String value, Class<? extends Date> type) {
		if (value == null || value.equals("")) {
			return null;
		}

		try {
			return (Date) new SimpleDateFormat("dd/mm/aaaa").parse(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}

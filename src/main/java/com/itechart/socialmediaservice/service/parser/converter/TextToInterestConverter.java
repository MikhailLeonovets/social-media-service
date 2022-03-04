package com.itechart.socialmediaservice.service.parser.converter;

import com.itechart.socialmediaservice.service.model.Interest;
import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class TextToInterestConverter extends AbstractCsvConverter {

	@Override
	public Object convertToRead(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		return new Interest(s);
	}
}

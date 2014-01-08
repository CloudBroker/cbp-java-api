package com.cloudbroker.platform.api.converter;

import com.thoughtworks.xstream.converters.basic.DateConverter;

public class CBDateConverter extends DateConverter {
	public CBDateConverter(String string, String[] acceptableFormats) {
		super(string, acceptableFormats);
	}

	public Object fromString(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		return super.fromString(str);
	}
}

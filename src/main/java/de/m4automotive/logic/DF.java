package de.m4automotive.logic;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DF {

	private static final Logger LOG = LogManager.getLogger(DF.class);

	public DecimalFormat getDecimalFormat(int decimalPlaces) {

		LOG.debug("getDecimalFormat called for decimalPlaces: {}", decimalPlaces);

		String pattern;
		switch (decimalPlaces) {
		case 0:
			pattern = "0";
			break;
		case 1:
			pattern = "0.0";
			break;
		case 2:
			pattern = "0.00";
			break;
		case 3:
			pattern = "0.000";
			break;
		case 4:
			pattern = "0.0000";
			break;
		case 5:
			pattern = "0.00000";
			break;
		case 6:
			pattern = "0.000000";
			break;
		case 7:
			pattern = "0.0000000";
			break;
		case 8:
			pattern = "0.00000000";
			break;
		case 9:
			pattern = "0.000000000";
			break;
		case 10:
			pattern = "0.0000000000";
			break;
		default:
			pattern = "0.00";
			break;
		}

		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
		DecimalFormat df = new DecimalFormat(pattern, symbols);

		LOG.debug("Created DecimalFormat with pattern '{}' using German symbols", pattern);
		return df;
	}
}
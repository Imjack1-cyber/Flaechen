package de.m4automotive.model;

import java.text.DecimalFormat;

public class Octagon {

	private double o_seite;
	private double o_radiusInkreis;
	private double o_radiusUmkreis;
	private double o_flaecheninhalt;
	private double o_umfang;

	private DecimalFormat df;

	public Octagon() {

	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out.println(
				"Octagon Bean: DecimalFormat set in Octagon object: " + (df != null ? df.toPattern() : "null")); // Debug
	}

	private String formatValue(double value) {
		// 1. Handle non-finite numbers which DecimalFormat might struggle with
		if (!Double.isFinite(value)) {
			return String.valueOf(value); // Return "NaN", "Infinity", "-Infinity" directly
		}
		// 2. Check if the formatter instance variable (df) has been set
		if (this.df == null) {
			System.err.println(
					"WARNING: Kreis.formatValue - DecimalFormat (df) is null. Returning unformatted value: " + value); // Log
																														// warning
			// Fallback: return unformatted double converted to a string
			return String.valueOf(value);
		}
		// 3. Use the formatter if it exists and the number is finite
		try {
			return this.df.format(value);
		} catch (Exception e) {
			// Catch potential formatting errors (though less common with standard patterns)
			System.err.println("ERROR: Kreis.formatValue - Error formatting value " + value + " with pattern '"
					+ this.df.toPattern() + "': " + e.getMessage());
			return String.valueOf(value); // Fallback to unformatted on error
		}
	}

	public String getO_seite() {
		return formatValue(this.o_seite);
	}

	public String getO_radiusInkreis() {
		return formatValue(this.o_radiusInkreis);
	}

	public String getO_radiusUmkreis() {
		return formatValue(this.o_radiusUmkreis);
	}

	public String getO_flaecheninhalt() {
		return formatValue(this.o_flaecheninhalt);
	}

	public String getO_umfang() {
		return formatValue(this.o_umfang);
	}

	public void setO_seite(double o_seite) {
		this.o_seite = o_seite;
	}

	public void setO_radiusInkreis(double o_radiusInkreis) {
		this.o_radiusInkreis = o_radiusInkreis;
	}

	public void setO_radiusUmkreis(double o_radiusUmkreis) {
		this.o_radiusUmkreis = o_radiusUmkreis;
	}

	public void setO_flaecheninhalt(double o_flaecheninhalt) {
		this.o_flaecheninhalt = o_flaecheninhalt;
	}

	public void setO_umfang(double o_umfang) {
		this.o_umfang = o_umfang;
	}
}

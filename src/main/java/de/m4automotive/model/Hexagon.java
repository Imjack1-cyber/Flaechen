package de.m4automotive.model;

import java.text.DecimalFormat;

public class Hexagon {

	private double h_seite;
	private double h_radiusInkreis;
	private double h_radiusUmkreis;
	private double h_flaecheninhalt;
	private double h_umfang;

	private DecimalFormat df;

	public Hexagon() {

	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out.println(
				"Hexagon Bean: DecimalFormat set in Hexagon object: " + (df != null ? df.toPattern() : "null")); // Debug
	}

	private String formatValue(double value) {
		// 1. Handle non-finite numbers which DecimalFormat might struggle with
		if (!Double.isFinite(value)) {
			return String.valueOf(value); // Return "NaN", "Infinity", "-Infinity" directly
		}
		// 2. Check if the formatter instance variable (df) has been set
		if (this.df == null) {
			System.err.println(
					"WARNING: Hexagon.formatValue - DecimalFormat (df) is null. Returning unformatted value: " + value); // Log
																															// warning
			// Fallback: return unformatted double converted to a string
			return String.valueOf(value);
		}
		// 3. Use the formatter if it exists and the number is finite
		try {
			return this.df.format(value);
		} catch (Exception e) {
			// Catch potential formatting errors (though less common with standard patterns)
			System.err.println("ERROR: Hexagon.formatValue - Error formatting value " + value + " with pattern '"
					+ this.df.toPattern() + "': " + e.getMessage());
			return String.valueOf(value); // Fallback to unformatted on error
		}
	}

	public String getH_seite() {
		return formatValue(this.h_seite);
	}

	public String getH_radiusInkreis() {
		return formatValue(this.h_radiusInkreis);
	}

	public String getH_radiusUmkreis() {
		return formatValue(this.h_radiusUmkreis);
	}

	public String getH_flaecheninhalt() {
		return formatValue(this.h_flaecheninhalt);
	}

	public String getH_umfang() {
		return formatValue(this.h_umfang);
	}

	public void setH_seite(double h_seite) {
		this.h_seite = h_seite;
	}

	public void setH_radiusInkreis(double h_radiusInkreis) {
		this.h_radiusInkreis = h_radiusInkreis;
	}

	public void setH_radiusUmkreis(double h_radiusUmkreis) {
		this.h_radiusUmkreis = h_radiusUmkreis;
	}

	public void setH_flaecheninhalt(double h_flaecheninhalt) {
		this.h_flaecheninhalt = h_flaecheninhalt;
	}

	public void setH_umfang(double h_umfang) {
		this.h_umfang = h_umfang;
	}
}

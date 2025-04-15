package de.m4automotive.model;

import java.text.DecimalFormat;

public class Pentagon {

	private double pe_seite;
	private double pe_radiusInkreis;
	private double pe_radiusUmkreis;
	private double pe_flaecheninhalt;
	private double pe_umfang;

	private DecimalFormat df;

	public Pentagon() {

	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out.println(
				"Pentagon Bean: DecimalFormat set in Pentagon object: " + (df != null ? df.toPattern() : "null")); // Debug
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

	public String getPe_seite() {
		return formatValue(this.pe_seite);
	}

	public String getPe_radiusInkreis() {
		return formatValue(this.pe_radiusInkreis);
	}

	public String getPe_radiusUmkreis() {
		return formatValue(this.pe_radiusUmkreis);
	}

	public String getPe_flaecheninhalt() {
		return formatValue(this.pe_flaecheninhalt);
	}

	public String getPe_umfang() {
		return formatValue(this.pe_umfang);
	}

	public void setPe_seite(double pe_seite) {
		this.pe_seite = pe_seite;
	}

	public void setPe_radiusInkreis(double pe_radiusInkreis) {
		this.pe_radiusInkreis = pe_radiusInkreis;
	}

	public void setPe_radiusUmkreis(double pe_radiusUmkreis) {
		this.pe_radiusUmkreis = pe_radiusUmkreis;
	}

	public void setPe_flaecheninhalt(double pe_flaecheninhalt) {
		this.pe_flaecheninhalt = pe_flaecheninhalt;
	}

	public void setPe_umfang(double pe_umfang) {
		this.pe_umfang = pe_umfang;
	}
}

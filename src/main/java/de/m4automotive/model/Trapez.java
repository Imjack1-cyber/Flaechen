package de.m4automotive.model;

import java.text.DecimalFormat;

public class Trapez {

	private double t_seiteA;
	private double t_seiteB;
	private double t_seiteC;
	private double t_seiteD;
	private double t_flaecheninhalt;
	private double t_umfang;
	private double t_diagonale;
	private double t_hoehe;

	private DecimalFormat df;

	public Trapez() {

	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out
				.println("Trapez Bean: DecimalFormat set in Trapez object: " + (df != null ? df.toPattern() : "null")); // Debug
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

	public String getT_seiteA() {
		return formatValue(this.t_seiteA);
	}

	public String getT_seiteB() {
		return formatValue(this.t_seiteB);
	}

	public String getT_seiteC() {
		return formatValue(this.t_seiteC);
	}

	public String getT_seiteD() {
		return formatValue(this.t_seiteD);
	}

	public String getT_flaecheninhalt() {
		return formatValue(this.t_flaecheninhalt);
	}

	public String getT_umfang() {
		return formatValue(this.t_umfang);
	}

	public String getT_diagonale() {
		return formatValue(this.t_diagonale);
	}

	public String getT_hoehe() {
		return formatValue(this.t_hoehe);
	}

	public void setT_seiteA(double r_seiteA) {
		this.t_seiteA = r_seiteA;
	}

	public void setT_seiteB(double r_seiteB) {
		this.t_seiteB = r_seiteB;
	}

	public void setT_seiteC(double r_seiteC) {
		this.t_seiteC = r_seiteC;
	}

	public void setT_seiteD(double r_seiteD) {
		this.t_seiteD = r_seiteD;
	}

	public void setT_flaecheninhalt(double r_flaecheninhalt) {
		this.t_flaecheninhalt = r_flaecheninhalt;
	}

	public void setT_umfang(double r_umfang) {
		this.t_umfang = r_umfang;
	}

	public void setT_diagonale(double r_diagonale) {
		this.t_diagonale = r_diagonale;
	}

	public void setT_hoehe(double r_hoehe) {
		this.t_hoehe = r_hoehe;
	}
}
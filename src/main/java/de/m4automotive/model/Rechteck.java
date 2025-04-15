package de.m4automotive.model;

import java.text.DecimalFormat;

public class Rechteck {

	private double r_seiteA;
	private double r_seiteB;
	private double r_seiteC;
	private double r_flaecheninhalt;
	private double r_umfang;
	private double r_diagonale;

	private DecimalFormat df;

	public Rechteck() {

	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out.println(
				"Rechteck Bean: DecimalFormat set in Rechteck object: " + (df != null ? df.toPattern() : "null")); // Debug
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

	public String getR_seiteA() {
		return formatValue(this.r_seiteA);
	}

	public String getR_seiteB() {
		return formatValue(this.r_seiteB);
	}

	public String getR_seiteC() {
		return formatValue(this.r_seiteC);
	}

	public String getR_flaecheninhalt() {
		return formatValue(this.r_flaecheninhalt);
	}

	public String getR_umfang() {
		return formatValue(this.r_umfang);
	}

	public String getR_diagonale() {
		return formatValue(this.r_diagonale);
	}

	public void setR_seiteA(double r_seiteA) {
		this.r_seiteA = r_seiteA;
	}

	public void setR_seiteB(double r_seiteB) {
		this.r_seiteB = r_seiteB;
	}

	public void setR_seiteC(double r_seiteC) {
		this.r_seiteC = r_seiteC;
	}

	public void setR_flaecheninhalt(double r_flaecheninhalt) {
		this.r_flaecheninhalt = r_flaecheninhalt;
	}

	public void setR_umfang(double r_umfang) {
		this.r_umfang = r_umfang;
	}

	public void setR_diagonale(double r_diagonale) {
		this.r_diagonale = r_diagonale;
	}
}
package de.m4automotive.model;

import java.text.DecimalFormat;

public class Quader {

	private double q_kanteA;
	private double q_kanteB;
	private double q_kanteC;
	private double q_volumen;
	private double q_grundflaeche;
	private double q_mantelflaeche;
	private double q_oberflaeche;
	private double q_raumdiagonale;

	private DecimalFormat df;

	public Quader() {

	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out
				.println("Quader Bean: DecimalFormat set in Quader object: " + (df != null ? df.toPattern() : "null")); // Debug
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

	public String getQ_kanteA() {
		return formatValue(this.q_kanteA);
	}

	public String getQ_kanteB() {
		return formatValue(this.q_kanteB);
	}

	public String getQ_kanteC() {
		return formatValue(this.q_kanteC);
	}

	public String getQ_volumen() {
		return formatValue(this.q_volumen);
	}

	public String getQ_grundflaeche() {
		return formatValue(this.q_grundflaeche);
	}

	public String getQ_mantelflaeche() {
		return formatValue(this.q_mantelflaeche);
	}

	public String getQ_oberflaeche() {
		return formatValue(this.q_oberflaeche);
	}

	public String getQ_raumdiagonale() {
		return formatValue(this.q_raumdiagonale);
	}

	public void setQ_kanteA(double q_kanteA) {
		this.q_kanteA = q_kanteA;
	}

	public void setQ_kanteB(double q_kanteB) {
		this.q_kanteB = q_kanteB;
	}

	public void setQ_kanteC(double q_kanteC) {
		this.q_kanteC = q_kanteC;
	}

	public void setQ_volumen(double q_volumen) {
		this.q_volumen = q_volumen;
	}

	public void setQ_grundflaeche(double q_grundflaeche) {
		this.q_grundflaeche = q_grundflaeche;
	}

	public void setQ_mantelflaeche(double q_mantelflaeche) {
		this.q_mantelflaeche = q_mantelflaeche;
	}

	public void setQ_oberflaeche(double q_oberflaeche) {
		this.q_oberflaeche = q_oberflaeche;
	}

	public void setQ_raumdiagonale(double q_raumdiagonale) {
		this.q_raumdiagonale = q_raumdiagonale;
	}
}
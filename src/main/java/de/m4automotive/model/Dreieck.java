package de.m4automotive.model;

import java.text.DecimalFormat;

public class Dreieck {

	private double a;
	private double b;
	private double c;
	private double umfangDreieck;
	private double flaecheninhaltDreieck;
	private double hoeheA;
	private double hoeheB;
	private double hoeheC;
	private double alpha;
	private double beta;
	private double gamma;

	private DecimalFormat df;

	public Dreieck() {
	}

	public Dreieck(double a, double b, double c, double umfangDreieck, double flaecheninhaltDreieck, double hoeheA,
			double hoeheB, double hoeheC, double alpha, double beta, double gamma) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.umfangDreieck = umfangDreieck;
		this.flaecheninhaltDreieck = flaecheninhaltDreieck;
		this.hoeheA = hoeheA;
		this.hoeheB = hoeheB;
		this.hoeheC = hoeheC;
		this.alpha = alpha;
		this.beta = beta;
		this.gamma = gamma;
	}

	public void setDecimalFormat(DecimalFormat df) {
		System.out.println("Kreis Bean: DecimalFormat set in Kreis object: " + (df != null ? df.toPattern() : "null")); // Debug
																														// log
		this.df = df;
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

	public String getA() {
		return formatValue(this.a);
	}

	public void setA(double a) {
		this.a = a;
	}

	public String getB() {
		return formatValue(this.b);
	}

	public void setB(double b) {
		this.b = b;
	}

	public String getC() {
		return formatValue(this.c);
	}

	public void setC(double c) {
		this.c = c;
	}

	public String getUmfangDreieck() {
		return formatValue(this.umfangDreieck);
	}

	public void setUmfangDreieck(double umfangDreieck) {
		this.umfangDreieck = umfangDreieck;
	}

	public String getFlaecheninhaltDreieck() {
		return formatValue(this.flaecheninhaltDreieck);
	}

	public void setFlaecheninhaltDreieck(double flaecheninhaltDreieck) {
		this.flaecheninhaltDreieck = flaecheninhaltDreieck;
	}

	public String getHoeheA() {
		return formatValue(this.hoeheA);
	}

	public void setHoeheA(double hoeheA) {
		this.hoeheA = hoeheA;
	}

	public String getHoeheB() {
		return formatValue(this.hoeheB);
	}

	public void setHoeheB(double hoeheB) {
		this.hoeheB = hoeheB;
	}

	public String getHoeheC() {
		return formatValue(this.hoeheC);
	}

	public void setHoeheC(double hoeheC) {
		this.hoeheC = hoeheC;
	}

	public String getAlpha() {
		return formatValue(this.alpha);
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public String getBeta() {
		return formatValue(this.beta);
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public String getGamma() {
		return formatValue(this.gamma);
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	@Override
	public String toString() {
		return "Dreieck [a=" + a + ", b=" + b + ", c=" + c + ", umfangDreieck=" + umfangDreieck
				+ ", flaecheninhaltDreieck=" + flaecheninhaltDreieck + ", hoeheA=" + hoeheA + ", hoeheB=" + hoeheB
				+ ", hoeheC=" + hoeheC + ", alpha=" + alpha + ", beta=" + beta + ", gamma=" + gamma + "]";
	}
}
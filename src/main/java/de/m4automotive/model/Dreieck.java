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

	private double p_seiteA;
	private double p_hoehe;
	private double p_hoeheSeite;
	private double p_seitenkante;
	private double p_grundflaecheDiagonale;
	private double p_grundflaeche;
	private double p_mantelflaeche;
	private double p_oberflaeche;
	private double p_volumen;

	private DecimalFormat df;

	public Dreieck() {
	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out.println(
				"Dreieck Bean: DecimalFormat set in Dreieck object: " + (df != null ? df.toPattern() : "null")); // Debug
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

	public String getSeiteA() {
		return formatValue(this.p_seiteA);
	}

	public String getHoehe() {
		return formatValue(this.p_hoehe);
	}

	public String getHoeheSeite() {
		return formatValue(this.p_hoeheSeite);
	}

	public String getSeitenkante() {
		return formatValue(this.p_seitenkante);
	}

	public String getGrundflaecheDiagonale() {
		return formatValue(this.p_grundflaecheDiagonale);
	}

	public String getGrundflaechePyramide() {
		return formatValue(this.p_grundflaeche);
	}

	public String getMantelflaechePyramide() {
		return formatValue(this.p_mantelflaeche);
	}

	public String getOberflaechePyramide() {
		return formatValue(this.p_oberflaeche);
	}

	public String getVolumenPyramide() {
		return formatValue(this.p_volumen);
	}

	public void setSeiteA(double seiteA) {
		this.p_seiteA = seiteA;
	}

	public void setHoehe(double hoehe) {
		this.p_hoehe = hoehe;
	}

	public void setHoeheSeite(double hoeheSeite) {
		this.p_hoeheSeite = hoeheSeite;
	}

	public void setSeitenkante(double seitenkante) {
		this.p_seitenkante = seitenkante;
	}

	public void setGrundflaecheDiagonale(double grundflaecheDiagonale) {
		this.p_grundflaecheDiagonale = grundflaecheDiagonale;
	}

	public void setGrundflaechePyramide(double grundflaechePyramide) {
		this.p_grundflaeche = grundflaechePyramide;
	}

	public void setMantelflaechePyramide(double mantelflaechePyramide) {
		this.p_mantelflaeche = mantelflaechePyramide;
	}

	public void setOberflaechePyramide(double oberflaechePyramide) {
		this.p_oberflaeche = oberflaechePyramide;
	}

	public void setVolumenPyramide(double volumenPyramide) {
		this.p_volumen = volumenPyramide;
	}

	@Override
	public String toString() {
		return "Dreieck [a=" + a + ", b=" + b + ", c=" + c + ", umfangDreieck=" + umfangDreieck
				+ ", flaecheninhaltDreieck=" + flaecheninhaltDreieck + ", hoeheA=" + hoeheA + ", hoeheB=" + hoeheB
				+ ", hoeheC=" + hoeheC + ", alpha=" + alpha + ", beta=" + beta + ", gamma=" + gamma + "]";
	}
}
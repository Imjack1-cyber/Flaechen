package de.m4automotive.model;

import java.text.DecimalFormat;

public class Pyramide {

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

	public Pyramide() {

	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out.println(
				"Pyramide Bean: DecimalFormat set in Pyramide object: " + (df != null ? df.toPattern() : "null")); // Debug
	}

	private String formatValue(double value) {
		// 1. Handle non-finite numbers which DecimalFormat might struggle with
		if (!Double.isFinite(value)) {
			return String.valueOf(value); // Return "NaN", "Infinity", "-Infinity" directly
		}
		// 2. Check if the formatter instance variable (df) has been set
		if (this.df == null) {
			System.err
					.println("WARNING: Pyramide.formatValue - DecimalFormat (df) is null. Returning unformatted value: "
							+ value); // Log
										// warning
			// Fallback: return unformatted double converted to a string
			return String.valueOf(value);
		}
		// 3. Use the formatter if it exists and the number is finite
		try {
			return this.df.format(value);
		} catch (Exception e) {
			// Catch potential formatting errors (though less common with standard patterns)
			System.err.println("ERROR: Pyramide.formatValue - Error formatting value " + value + " with pattern '"
					+ this.df.toPattern() + "': " + e.getMessage());
			return String.valueOf(value); // Fallback to unformatted on error
		}
	}

	public String getP_seiteA() {
		return formatValue(this.p_seiteA);
	}

	public String getP_hoehe() {
		return formatValue(this.p_hoehe);
	}

	public String getP_hoeheSeite() {
		return formatValue(this.p_hoeheSeite);
	}

	public String getP_seitenkante() {
		return formatValue(this.p_seitenkante);
	}

	public String getP_grundflaecheDiagonale() {
		return formatValue(this.p_grundflaecheDiagonale);
	}

	public String getP_grundflaeche() {
		return formatValue(this.p_grundflaeche);
	}

	public String getP_mantelflaeche() {
		return formatValue(this.p_mantelflaeche);
	}

	public String getP_oberflaeche() {
		return formatValue(this.p_oberflaeche);
	}

	public String getP_volumen() {
		return formatValue(this.p_volumen);
	}

	public void setP_seiteA(double p_seiteA) {
		this.p_seiteA = p_seiteA;
	}

	public void setP_hoehe(double p_hoehe) {
		this.p_hoehe = p_hoehe;
	}

	public void setP_hoeheSeite(double p_hoeheSeite) {
		this.p_hoeheSeite = p_hoeheSeite;
	}

	public void setP_seitenkante(double p_seitenkante) {
		this.p_seitenkante = p_seitenkante;
	}

	public void setP_grundflaecheDiagonale(double p_grundflaecheDiagonale) {
		this.p_grundflaecheDiagonale = p_grundflaecheDiagonale;
	}

	public void setP_grundflaeche(double p_grundflaeche) {
		this.p_grundflaeche = p_grundflaeche;
	}

	public void setP_mantelflaeche(double p_mantelflaeche) {
		this.p_mantelflaeche = p_mantelflaeche;
	}

	public void setP_oberflaeche(double p_oberflaeche) {
		this.p_oberflaeche = p_oberflaeche;
	}

	public void setP_volumen(double p_volumen) {
		this.p_volumen = p_volumen;
	}
}
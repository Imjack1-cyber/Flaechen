package de.m4automotive.model;

import java.text.DecimalFormat;

public class Wuerfel {

	private double w_kante;
	private double w_volumen;
	private double w_oberflaeche;
	private double w_raumdiagonale;
	private double w_grundflaeche;
	private double w_mantelflaeche;
	private double w_umfang_seitenflaeche;
	private double w_summe_aller_kanten;

	private DecimalFormat df;

	public Wuerfel() {

	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out.println(
				"Wuerfel Bean: DecimalFormat set in Wuerfel object: " + (df != null ? df.toPattern() : "null")); // Debug
	}

	private String formatValue(double value) {
		// 1. Handle non-finite numbers which DecimalFormat might struggle with
		if (!Double.isFinite(value)) {
			return String.valueOf(value); // Return "NaN", "Infinity", "-Infinity" directly
		}
		// 2. Check if the formatter instance variable (df) has been set
		if (this.df == null) {
			System.err.println(
					"WARNING: Wuerfel.formatValue - DecimalFormat (df) is null. Returning unformatted value: " + value); // Log
																															// warning
			// Fallback: return unformatted double converted to a string
			return String.valueOf(value);
		}
		// 3. Use the formatter if it exists and the number is finite
		try {
			return this.df.format(value);
		} catch (Exception e) {
			// Catch potential formatting errors (though less common with standard patterns)
			System.err.println("ERROR: Wuerfel.formatValue - Error formatting value " + value + " with pattern '"
					+ this.df.toPattern() + "': " + e.getMessage());
			return String.valueOf(value); // Fallback to unformatted on error
		}
	}

	public String getW_kante() {
		return formatValue(this.w_kante);
	}

	public String getW_volumen() {
		return formatValue(this.w_volumen);
	}

	public String getW_oberflaeche() {
		return formatValue(this.w_oberflaeche);
	}

	public String getW_raumdiagonale() {
		return formatValue(this.w_raumdiagonale);
	}

	public String getW_grundflaeche() {
		return formatValue(this.w_grundflaeche);
	}

	public String getW_mantelflaeche() {
		return formatValue(this.w_mantelflaeche);
	}

	public String getW_umfang_seitenflaeche() {
		return formatValue(this.w_umfang_seitenflaeche);
	}

	public String getW_summe_aller_kanten() {
		return formatValue(this.w_summe_aller_kanten);
	}

	public void setW_kante(double w_kante) {
		this.w_kante = w_kante;
	}

	public void setW_volumen(double w_volumen) {
		this.w_volumen = w_volumen;
	}

	public void setW_oberflaeche(double w_oberflaeche) {
		this.w_oberflaeche = w_oberflaeche;
	}

	public void setW_raumdiagonale(double w_raumdiagonale) {
		this.w_raumdiagonale = w_raumdiagonale;
	}

	public void setW_grundflaeche(double w_grundflaeche) {
		this.w_grundflaeche = w_grundflaeche;
	}

	public void setW_mantelflaeche(double w_mantelflaeche) {
		this.w_mantelflaeche = w_mantelflaeche;
	}

	public void setW_umfang_seitenflaeche(double w_umfang_seitenflaeche) {
		this.w_umfang_seitenflaeche = w_umfang_seitenflaeche;
	}

	public void setW_summe_aller_kanten(double w_summe_aller_kanten) {
		this.w_summe_aller_kanten = w_summe_aller_kanten;
	}
}
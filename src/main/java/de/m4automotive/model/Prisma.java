package de.m4automotive.model;

import java.text.DecimalFormat;

public class Prisma {

	private double pr_grundflaeche;
	private double pr_mantelflaeche;
	private double pr_oberflaeche;
	private double pr_volumen;
	private double pr_hoehe;
	private double pr_umfang;

	private DecimalFormat df;

	public Prisma() {

	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out
				.println("Prisma Bean: DecimalFormat set in Prisma object: " + (df != null ? df.toPattern() : "null")); // Debug
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

	public String getPr_grundflaeche() {
		return formatValue(this.pr_grundflaeche);
	}

	public String getPr_mantelflaeche() {
		return formatValue(this.pr_mantelflaeche);
	}

	public String getPr_oberflaeche() {
		return formatValue(this.pr_oberflaeche);
	}

	public String getPr_volumen() {
		return formatValue(this.pr_volumen);
	}

	public String getPr_hoehe() {
		return formatValue(this.pr_hoehe);
	}

	public String getPr_umfang() {
		return formatValue(this.pr_umfang);
	}

	public void setPr_grundflaeche(double pr_grundflaeche) {
		this.pr_grundflaeche = pr_grundflaeche;
	}

	public void setPr_mantelflaeche(double pr_mantelflaeche) {
		this.pr_mantelflaeche = pr_mantelflaeche;
	}

	public void setPr_oberflaeche(double pr_oberflaeche) {
		this.pr_oberflaeche = pr_oberflaeche;
	}

	public void setPr_volumen(double pr_volumen) {
		this.pr_volumen = pr_volumen;
	}

	public void setPr_hoehe(double pr_hoehe) {
		this.pr_hoehe = pr_hoehe;
	}

	public void setPr_umfang(double pr_umfang) {
		this.pr_umfang = pr_umfang;
	}
}
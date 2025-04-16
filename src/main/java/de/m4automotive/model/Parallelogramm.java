package de.m4automotive.model;

import java.text.DecimalFormat;

public class Parallelogramm {

	private double pa_seiteA;
	private double pa_seiteB;
	private double pa_hoeheA;
	private double pa_flaecheninhalt;
	private double pa_umfang;

	private DecimalFormat df;

	public Parallelogramm() {

	}

	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out.println("Parallelogramm Bean: DecimalFormat set in Parallelogramm object: "
				+ (df != null ? df.toPattern() : "null")); // Debug
	}

	private String formatValue(double value) {
		// 1. Handle non-finite numbers which DecimalFormat might struggle with
		if (!Double.isFinite(value)) {
			return String.valueOf(value); // Return "NaN", "Infinity", "-Infinity" directly
		}
		// 2. Check if the formatter instance variable (df) has been set
		if (this.df == null) {
			System.err.println(
					"WARNING: Parallelogramm.formatValue - DecimalFormat (df) is null. Returning unformatted value: "
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
			System.err.println("ERROR: Parallelogramm.formatValue - Error formatting value " + value + " with pattern '"
					+ this.df.toPattern() + "': " + e.getMessage());
			return String.valueOf(value); // Fallback to unformatted on error
		}
	}

	public String getPa_seiteA() {
		return formatValue(this.pa_seiteA);
	}

	public String getPa_seiteB() {
		return formatValue(this.pa_seiteB);
	}

	public String getPa_hoeheA() {
		return formatValue(this.pa_hoeheA);
	}

	public String getPa_flaecheninhalt() {
		return formatValue(this.pa_flaecheninhalt);
	}

	public String getPa_umfang() {
		return formatValue(this.pa_umfang);
	}

	public void setPa_seiteA(double pa_seiteA) {
		this.pa_seiteA = pa_seiteA;
	}

	public void setPa_seiteB(double pa_seiteB) {
		this.pa_seiteB = pa_seiteB;
	}

	public void setPa_hoeheA(double pa_hoeheA) {
		this.pa_hoeheA = pa_hoeheA;
	}

	public void setPa_flaecheninhalt(double pa_flaecheninhalt) {
		this.pa_flaecheninhalt = pa_flaecheninhalt;
	}

	public void setPa_umfang(double pa_umfang) {
		this.pa_umfang = pa_umfang;
	}
}

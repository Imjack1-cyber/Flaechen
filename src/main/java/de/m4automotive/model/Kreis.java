package de.m4automotive.model;

import java.text.DecimalFormat;
// Consider importing NumberFormat if you need locale-specific formatting later
// import java.text.NumberFormat; 

/**
 * Represents geometric calculations related to circles, cylinders, cones, and
 * spheres. This class holds calculated double values and provides getter
 * methods that return String representations formatted according to a
 * DecimalFormat object set externally (usually by a servlet).
 */
public class Kreis {

	// --- Numeric Fields (Store raw calculation results) ---
	// Kreis
	private double radius1;
	private double radius2;
	private double durchmesser1;
	private double durchmesser2;
	private double umfang1;
	private double umfang2;
	private double flaecheninhalt1;
	private double flaecheninhalt2;
	private double flaecheninhaltGesamt;

	// Kreisteile
	private double kreisbogen1;
	private double kreisbogen2;
	private double kreisausschnitt1;
	private double kreisausschnitt2;
	private double kreisausschnittGesamt;
	private double alpha;

	// Zylinder
	private double hoehe1;
	private double hoehe2;
	private double grundflaecheZylinder1;
	private double grundflaecheZylinder2;
	private double grundflaecheZylinderGesamt;
	private double mantelflaecheZylinder1;
	private double mantelflaecheZylinder2;
	private double mantelflaecheZylinderGesamt;
	private double oberflaecheZylinder1;
	private double oberflaecheZylinder2;
	private double oberflaecheZylinderGesamt;
	private double volumenZylinder1;
	private double volumenZylinder2;
	private double volumenZylinderGesamt;

	// Kegel
	private double grundflaecheKegel1;
	private double grundflaecheKegel2;
	private double grundflaecheKegelGesamt;
	private double mantelflaecheKegel1;
	private double mantelflaecheKegel2;
	private double mantelflaecheKegelGesamt;
	private double oberflaecheKegel1;
	private double oberflaecheKegel2;
	private double oberflaecheKegelGesamt;
	private double volumenKegel1;
	private double volumenKegel2;
	private double volumenKegelGesamt;
	private double seitenhoehe1;
	private double seitenhoehe2;

	// Kugel
	private double oberflaecheKugel1;
	private double oberflaecheKugel2;
	private double oberflaecheKugelGesamt;
	private double volumenKugel1;
	private double volumenKugel2;
	private double volumenKugelGesamt;

	// --- Formatting ---
	private DecimalFormat df; // The formatter instance, injected by the servlet

	// --- Comparison Flags ---
	private boolean grundflaechenGleich;
	private boolean zylinderVoluminaGleich;
	private boolean zylinderGrundflaechenGleich;
	private boolean kegelVoluminaGleich;
	private boolean kegelGrundflaechenGleich;
	private boolean kugelVoluminaGleich;
	private boolean kreisausschnitteGleich;

	// --- Constructors ---
	/**
	 * Default constructor.
	 */
	public Kreis() {
		// Fields will be initialized to default values (0.0 for double, false for
		// boolean, null for df)
	}

	// --- Setter for DecimalFormat ---
	/**
	 * Sets the DecimalFormat object used for formatting numeric output in getters.
	 * This is typically called by the controlling servlet after calculations.
	 * 
	 * @param df The DecimalFormat instance to use.
	 */
	public void setDecimalFormat(DecimalFormat df) {
		this.df = df;
		System.out.println("Kreis Bean: DecimalFormat set in Kreis object: " + (df != null ? df.toPattern() : "null")); // Debug
	}

	// --- Formatting Helper Method ---
	/**
	 * Safely formats a double value using the instance's DecimalFormat (df).
	 * Handles null formatter and non-finite values (NaN, Infinity).
	 * 
	 * @param value The double value to format.
	 * @return A formatted String representation of the value, or the unformatted
	 *         value as a String if formatting fails or isn't possible.
	 */
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

	// --- GETTERS (Return Formatted Strings for JSP Display) ---

	// Kreis Getters
	public String getRadius1() {
		return formatValue(this.radius1);
	}

	public String getRadius2() {
		return formatValue(this.radius2);
	}

	public String getDurchmesser1() {
		return formatValue(this.durchmesser1);
	}

	public String getDurchmesser2() {
		return formatValue(this.durchmesser2);
	}

	public String getUmfang1() {
		return formatValue(this.umfang1);
	}

	public String getUmfang2() {
		return formatValue(this.umfang2);
	}

	public String getFlaecheninhalt1() {
		return formatValue(this.flaecheninhalt1);
	}

	public String getFlaecheninhalt2() {
		return formatValue(this.flaecheninhalt2);
	}

	public String getFlaecheninhaltGesamt() {
		return formatValue(this.flaecheninhaltGesamt);
	}

	// Kreisteile Getters
	public String getKreisbogen1() {
		return formatValue(this.kreisbogen1);
	}

	public String getKreisbogen2() {
		return formatValue(this.kreisbogen2);
	}

	public String getKreisausschnitt1() {
		return formatValue(this.kreisausschnitt1);
	}

	public String getKreisausschnitt2() {
		return formatValue(this.kreisausschnitt2);
	}

	public String getKreisausschnittGesamt() {
		return formatValue(this.kreisausschnittGesamt);
	}

	public String getAlpha() {
		return formatValue(this.alpha);
	}

	// Zylinder Getters
	public String getHoehe1() {
		return formatValue(this.hoehe1);
	}

	public String getHoehe2() {
		return formatValue(this.hoehe2);
	}

	public String getGrundflaecheZylinder1() {
		return formatValue(this.grundflaecheZylinder1);
	}

	public String getGrundflaecheZylinder2() {
		return formatValue(this.grundflaecheZylinder2);
	}

	public String getGrundflaecheZylinderGesamt() {
		return formatValue(this.grundflaecheZylinderGesamt);
	}

	public String getMantelflaecheZylinder1() {
		return formatValue(this.mantelflaecheZylinder1);
	}

	public String getMantelflaecheZylinder2() {
		return formatValue(this.mantelflaecheZylinder2);
	}

	public String getMantelflaecheZylinderGesamt() {
		return formatValue(this.mantelflaecheZylinderGesamt);
	}

	public String getOberflaecheZylinder1() {
		return formatValue(this.oberflaecheZylinder1);
	}

	public String getOberflaecheZylinder2() {
		return formatValue(this.oberflaecheZylinder2);
	}

	public String getOberflaecheZylinderGesamt() {
		return formatValue(this.oberflaecheZylinderGesamt);
	}

	public String getVolumenZylinder1() {
		return formatValue(this.volumenZylinder1);
	}

	public String getVolumenZylinder2() {
		return formatValue(this.volumenZylinder2);
	}

	public String getVolumenZylinderGesamt() {
		return formatValue(this.volumenZylinderGesamt);
	}

	// Kegel Getters
	public String getGrundflaecheKegel1() {
		return formatValue(this.grundflaecheKegel1);
	}

	public String getGrundflaecheKegel2() {
		return formatValue(this.grundflaecheKegel2);
	}

	public String getGrundflaecheKegelGesamt() {
		return formatValue(this.grundflaecheKegelGesamt);
	}

	public String getMantelflaecheKegel1() {
		return formatValue(this.mantelflaecheKegel1);
	}

	public String getMantelflaecheKegel2() {
		return formatValue(this.mantelflaecheKegel2);
	}

	public String getMantelflaecheKegelGesamt() {
		return formatValue(this.mantelflaecheKegelGesamt);
	}

	public String getOberflaecheKegel1() {
		return formatValue(this.oberflaecheKegel1);
	}

	public String getOberflaecheKegel2() {
		return formatValue(this.oberflaecheKegel2);
	}

	public String getOberflaecheKegelGesamt() {
		return formatValue(this.oberflaecheKegelGesamt);
	}

	public String getVolumenKegel1() {
		return formatValue(this.volumenKegel1);
	}

	public String getVolumenKegel2() {
		return formatValue(this.volumenKegel2);
	}

	public String getVolumenKegelGesamt() {
		return formatValue(this.volumenKegelGesamt);
	}

	public String getSeitenhoehe1() {
		return formatValue(this.seitenhoehe1);
	}

	public String getSeitenhoehe2() {
		return formatValue(this.seitenhoehe2);
	}

	// Kugel Getters
	public String getOberflaecheKugel1() {
		return formatValue(this.oberflaecheKugel1);
	}

	public String getOberflaecheKugel2() {
		return formatValue(this.oberflaecheKugel2);
	}

	public String getOberflaecheKugelGesamt() {
		return formatValue(this.oberflaecheKugelGesamt);
	}

	public String getVolumenKugel1() {
		return formatValue(this.volumenKugel1);
	}

	public String getVolumenKugel2() {
		return formatValue(this.volumenKugel2);
	}

	public String getVolumenKugelGesamt() {
		return formatValue(this.volumenKugelGesamt);
	}

	// --- Getters for boolean flags ---
	public boolean isGrundflaechenGleich() {
		return grundflaechenGleich;
	}

	public boolean isZylinderVoluminaGleich() {
		return zylinderVoluminaGleich;
	}

	public boolean isZylinderGrundflaechenGleich() {
		return zylinderGrundflaechenGleich;
	}

	public boolean isKegelVoluminaGleich() {
		return kegelVoluminaGleich;
	}

	public boolean isKegelGrundflaechenGleich() {
		return kegelGrundflaechenGleich;
	}

	public boolean isKugelVoluminaGleich() {
		return kugelVoluminaGleich;
	}

	public boolean isKreisausschnitteGleich() {
		return kreisausschnitteGleich;
	}

	// --- SETTERS (Standard setters for raw double values) ---

	// Kreis Setters
	public void setRadius1(double radius1) {
		this.radius1 = radius1;
	}

	public void setRadius2(double radius2) {
		this.radius2 = radius2;
	}

	public void setDurchmesser1(double durchmesser1) {
		this.durchmesser1 = durchmesser1;
	}

	public void setDurchmesser2(double durchmesser2) {
		this.durchmesser2 = durchmesser2;
	}

	public void setUmfang1(double umfang1) {
		this.umfang1 = umfang1;
	}

	public void setUmfang2(double umfang2) {
		this.umfang2 = umfang2;
	}

	public void setFlaecheninhalt1(double flaecheninhalt1) {
		this.flaecheninhalt1 = flaecheninhalt1;
	}

	public void setFlaecheninhalt2(double flaecheninhalt2) {
		this.flaecheninhalt2 = flaecheninhalt2;
	}

	public void setFlaecheninhaltGesamt(double flaecheninhaltGesamt) {
		this.flaecheninhaltGesamt = flaecheninhaltGesamt;
	}

	// Kreisteile Setters
	public void setKreisbogen1(double kreisbogen1) {
		this.kreisbogen1 = kreisbogen1;
	}

	public void setKreisbogen2(double kreisbogen2) {
		this.kreisbogen2 = kreisbogen2;
	}

	public void setKreisausschnitt1(double kreisausschnitt1) {
		this.kreisausschnitt1 = kreisausschnitt1;
	}

	public void setKreisausschnitt2(double kreisausschnitt2) {
		this.kreisausschnitt2 = kreisausschnitt2;
	}

	public void setKreisausschnittGesamt(double kreisausschnittGesamt) {
		this.kreisausschnittGesamt = kreisausschnittGesamt;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	// Zylinder Setters
	public void setHoehe1(double hoehe1) {
		this.hoehe1 = hoehe1;
	}

	public void setHoehe2(double hoehe2) {
		this.hoehe2 = hoehe2;
	}

	public void setGrundflaecheZylinder1(double grundflaecheZylinder1) {
		this.grundflaecheZylinder1 = grundflaecheZylinder1;
	}

	public void setGrundflaecheZylinder2(double grundflaecheZylinder2) {
		this.grundflaecheZylinder2 = grundflaecheZylinder2;
	}

	public void setGrundflaecheZylinderGesamt(double grundflaecheZylinderGesamt) {
		this.grundflaecheZylinderGesamt = grundflaecheZylinderGesamt;
	}

	public void setMantelflaecheZylinder1(double mantelflaecheZylinder1) {
		this.mantelflaecheZylinder1 = mantelflaecheZylinder1;
	}

	public void setMantelflaecheZylinder2(double mantelflaecheZylinder2) {
		this.mantelflaecheZylinder2 = mantelflaecheZylinder2;
	}

	public void setMantelflaecheZylinderGesamt(double mantelflaecheZylinderGesamt) {
		this.mantelflaecheZylinderGesamt = mantelflaecheZylinderGesamt;
	}

	public void setOberflaecheZylinder1(double oberflaecheZylinder1) {
		this.oberflaecheZylinder1 = oberflaecheZylinder1;
	}

	public void setOberflaecheZylinder2(double oberflaecheZylinder2) {
		this.oberflaecheZylinder2 = oberflaecheZylinder2;
	}

	public void setOberflaecheZylinderGesamt(double oberflaecheZylinderGesamt) {
		this.oberflaecheZylinderGesamt = oberflaecheZylinderGesamt;
	}

	public void setVolumenZylinder1(double volumenZylinder1) {
		this.volumenZylinder1 = volumenZylinder1;
	}

	public void setVolumenZylinder2(double volumenZylinder2) {
		this.volumenZylinder2 = volumenZylinder2;
	}

	public void setVolumenZylinderGesamt(double volumenZylinderGesamt) {
		this.volumenZylinderGesamt = volumenZylinderGesamt;
	}

	// Kegel Setters
	public void setGrundflaecheKegel1(double grundflaecheKegel1) {
		this.grundflaecheKegel1 = grundflaecheKegel1;
	}

	public void setGrundflaecheKegel2(double grundflaecheKegel2) {
		this.grundflaecheKegel2 = grundflaecheKegel2;
	}

	public void setGrundflaecheKegelGesamt(double grundflaecheKegelGesamt) {
		this.grundflaecheKegelGesamt = grundflaecheKegelGesamt;
	}

	public void setMantelflaecheKegel1(double mantelflaecheKegel1) {
		this.mantelflaecheKegel1 = mantelflaecheKegel1;
	}

	public void setMantelflaecheKegel2(double mantelflaecheKegel2) {
		this.mantelflaecheKegel2 = mantelflaecheKegel2;
	}

	public void setMantelflaecheKegelGesamt(double mantelflaecheKegelGesamt) {
		this.mantelflaecheKegelGesamt = mantelflaecheKegelGesamt;
	}

	public void setOberflaecheKegel1(double oberflaecheKegel1) {
		this.oberflaecheKegel1 = oberflaecheKegel1;
	}

	public void setOberflaecheKegel2(double oberflaecheKegel2) {
		this.oberflaecheKegel2 = oberflaecheKegel2;
	}

	public void setOberflaecheKegelGesamt(double oberflaecheKegelGesamt) {
		this.oberflaecheKegelGesamt = oberflaecheKegelGesamt;
	}

	public void setVolumenKegel1(double volumenKegel1) {
		this.volumenKegel1 = volumenKegel1;
	}

	public void setVolumenKegel2(double volumenKegel2) {
		this.volumenKegel2 = volumenKegel2;
	}

	public void setVolumenKegelGesamt(double volumenKegelGesamt) {
		this.volumenKegelGesamt = volumenKegelGesamt;
	}

	public void setSeitenhoehe1(double seitenhoehe1) {
		this.seitenhoehe1 = seitenhoehe1;
	}

	public void setSeitenhoehe2(double seitenhoehe2) {
		this.seitenhoehe2 = seitenhoehe2;
	}

	// Kugel Setters
	public void setOberflaecheKugel1(double oberflaecheKugel1) {
		this.oberflaecheKugel1 = oberflaecheKugel1;
	}

	public void setOberflaecheKugel2(double oberflaecheKugel2) {
		this.oberflaecheKugel2 = oberflaecheKugel2;
	}

	public void setOberflaecheKugelGesamt(double oberflaecheKugelGesamt) {
		this.oberflaecheKugelGesamt = oberflaecheKugelGesamt;
	}

	public void setVolumenKugel1(double volumenKugel1) {
		this.volumenKugel1 = volumenKugel1;
	}

	public void setVolumenKugel2(double volumenKugel2) {
		this.volumenKugel2 = volumenKugel2;
	}

	public void setVolumenKugelGesamt(double volumenKugelGesamt) {
		this.volumenKugelGesamt = volumenKugelGesamt;
	}

	// Setters for boolean flags
	public void setGrundflaechenGleich(boolean grundflaechenGleich) {
		this.grundflaechenGleich = grundflaechenGleich;
	}

	public void setZylinderVoluminaGleich(boolean zylinderVoluminaGleich) {
		this.zylinderVoluminaGleich = zylinderVoluminaGleich;
	}

	public void setZylinderGrundflaechenGleich(boolean zylinderGrundflaechenGleich) {
		this.zylinderGrundflaechenGleich = zylinderGrundflaechenGleich;
	}

	public void setKegelVoluminaGleich(boolean kegelVoluminaGleich) {
		this.kegelVoluminaGleich = kegelVoluminaGleich;
	}

	public void setKegelGrundflaechenGleich(boolean kegelGrundflaechenGleich) {
		this.kegelGrundflaechenGleich = kegelGrundflaechenGleich;
	}

	public void setKugelVoluminaGleich(boolean kugelVoluminaGleich) {
		this.kugelVoluminaGleich = kugelVoluminaGleich;
	}

	public void setKreisausschnitteGleich(boolean kreisausschnitteGleich) {
		this.kreisausschnitteGleich = kreisausschnitteGleich;
	}

	// --- toString() Method (for debugging - shows raw values) ---
	@Override
	public String toString() {
		// This shows the internal double values, useful for debugging.
		// It does NOT use the DecimalFormat.
		return "Kreis [radius1=" + radius1 + ", radius2=" + radius2 + ", durchmesser1=" + durchmesser1
				+ ", durchmesser2=" + durchmesser2 + ", umfang1=" + umfang1 + ", umfang2=" + umfang2
				+ ", flaecheninhalt1=" + flaecheninhalt1 + ", flaecheninhalt2=" + flaecheninhalt2
				+ ", flaecheninhaltGesamt=" + flaecheninhaltGesamt + ", kreisbogen1=" + kreisbogen1 + ", kreisbogen2="
				+ kreisbogen2 + ", kreisausschnitt1=" + kreisausschnitt1 + ", kreisauschnitt2=" + kreisausschnitt2
				+ ", kreisausschnittGesamt=" + kreisausschnittGesamt + ", alpha=" + alpha + ", hoehe1=" + hoehe1
				+ ", hoehe2=" + hoehe2 + ", grundflaecheZylinder1=" + grundflaecheZylinder1 + ", grundflaecheZylinder2="
				+ grundflaecheZylinder2 + ", grundflaecheZylinderGesamt=" + grundflaecheZylinderGesamt
				+ ", mantelflaecheZylinder1=" + mantelflaecheZylinder1 + ", mantelflaecheZylinder2="
				+ mantelflaecheZylinder2 + ", mantelflaecheZylinderGesamt=" + mantelflaecheZylinderGesamt
				+ ", oberflaecheZylinder1=" + oberflaecheZylinder1 + ", oberflaecheZylinder2=" + oberflaecheZylinder2
				+ ", oberflaecheZylinderGesamt=" + oberflaecheZylinderGesamt + ", volumenZylinder1=" + volumenZylinder1
				+ ", volumenZylinder2=" + volumenZylinder2 + ", volumenZylinderGesamt=" + volumenZylinderGesamt
				+ ", grundflaecheKegel1=" + grundflaecheKegel1 + ", grundflaecheKegel2=" + grundflaecheKegel2
				+ ", grundflaecheKegelGesamt=" + grundflaecheKegelGesamt + ", mantelflaecheKegel1="
				+ mantelflaecheKegel1 + ", mantelflaecheKegel2=" + mantelflaecheKegel2 + ", mantelflaecheKegelGesamt="
				+ mantelflaecheKegelGesamt + ", oberflaecheKegel1=" + oberflaecheKegel1 + ", oberflaecheKegel2="
				+ oberflaecheKegel2 + ", oberflaecheKegelGesamt=" + oberflaecheKegelGesamt + ", volumenKegel1="
				+ volumenKegel1 + ", volumenKegel2=" + volumenKegel2 + ", volumenKegelGesamt=" + volumenKegelGesamt
				+ ", seitenhoehe1=" + seitenhoehe1 + ", seitenhoehe2=" + seitenhoehe2 + ", oberflaecheKugel1="
				+ oberflaecheKugel1 + ", oberflaecheKugel2=" + oberflaecheKugel2 + ", oberflaecheKugelGesamt="
				+ oberflaecheKugelGesamt + ", volumenKugel1=" + volumenKugel1 + ", volumenKugel2=" + volumenKugel2
				+ ", volumenKugelGesamt=" + volumenKugelGesamt + ", df pattern="
				+ (df != null ? df.toPattern() : "null") // Show formatter pattern if set
				+ ", grundflaechenGleich=" + grundflaechenGleich + ", zylinderVoluminaGleich=" + zylinderVoluminaGleich
				+ ", zylinderGrundflaechenGleich=" + zylinderGrundflaechenGleich + ", kegelVoluminaGleich="
				+ kegelVoluminaGleich + ", kegelGrundflaechenGleich=" + kegelGrundflaechenGleich
				+ ", kugelVoluminaGleich=" + kugelVoluminaGleich + ", kreisausschnitteGleich=" + kreisausschnitteGleich
				+ "]";
	}
}
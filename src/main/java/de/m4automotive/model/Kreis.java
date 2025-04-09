package de.m4automotive.model;

public class Kreis {
	// Vorraussetzungen
	private int decimalPlaces;
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

	double kreisbogen1;
	double kreisbogen2;
	double kreisausschnitt1;
	double kreisausschnitt2;
	double kreisausschnittGesamt;
	double alpha;

	// Zylinder

	double hoehe;
	double grundflaecheZylinder1;
	double grundflaecheZylinder2;
	double grundflaecheZylinderGesamt;
	double mantelflaecheZylinder1;
	double mantelflaecheZylinder2;
	double mantelflaecheZylinderGesamt;
	double oberflaecheZylinder1;
	double oberflaecheZylinder2;
	double oberflaecheZylinderGesamt;
	double volumenZylinder1;
	double volumenZylinder2;
	double volumenZylinderGesamt;

	// Kegel

	double grundflaecheKegel1;
	double grundflaecheKegel2;
	double grundflaecheKegelGesamt;
	double mantelflaecheKegel1;
	double mantelflaecheKegel2;
	double mantelflaecheKegelGesamt;
	double oberflaecheKegel1;
	double oberflaecheKegel2;
	double oberflaecheKegelGesamt;
	double volumenKegel1;
	double volumenKegel2;
	double volumenKegelGesamt;
	double seitenhoehe1;
	double seitenhoehe2;

	// Kugel

	double oberflaecheKugel1;
	double oberflaecheKugel2;
	double oberflaecheKugelGesamt;
	double volumenKugel1;
	double volumenKugel2;
	double volumenKugelGesamt;

	public Kreis() {
	}

	public Kreis(int decimalPlaces, double radius1, double radius2, double durchmesser1, double durchmesser2,
			double umfang1, double umfang2, double flaecheninhalt1, double flaecheninhalt2, double flaecheninhaltGesamt,
			double kreisbogen1, double kreisbogen2, double kreisauschnitt1, double kreisauschnitt2,
			double kreisauschnittGesamt, double alpha, double hoehe, double grundflaecheZylinder1,
			double grundflaecheZylinder2, double grundflaecheZylinderGesamt, double mantelflaecheZylinder1,
			double mantelflaecheZylinder2, double mantelflaecheZylinderGesamt, double oberflaecheZylinder1,
			double oberflaecheZylinder2, double oberflaecheZylinderGesamt, double volumenZylinder1,
			double volumenZylinder2, double volumenZylinderGesamt, double grundflaecheKegel1, double grundflaecheKegel2,
			double grundflaecheKegelGesamt, double mantelflaecheKegel1, double mantelflaecheKegel2,
			double mantelflaecheKegelGesamt, double oberflaecheKegel1, double oberflaecheKegel2,
			double oberflaecheKegelGesamt, double volumenKegel1, double volumenKegel2, double volumenKegelGesamt,
			double seitenhoehe1, double seitenhoehe2, double oberflaecheKugel1, double oberflaecheKugel2,
			double oberflaecheKugelGesamt, double volumenKugel1, double volumenKugel2, double volumenKugelGesamt) {
		// Vorraussetzungen
		this.decimalPlaces = decimalPlaces;
		this.radius1 = radius1;
		this.radius2 = radius2;
		this.durchmesser1 = durchmesser1;
		this.durchmesser2 = durchmesser2;
		this.umfang1 = umfang1;
		this.umfang2 = umfang2;
		this.flaecheninhalt1 = flaecheninhalt1;
		this.flaecheninhalt2 = flaecheninhalt2;
		this.flaecheninhaltGesamt = flaecheninhaltGesamt;

		// Kreisteile
		this.kreisbogen1 = kreisbogen1;
		this.kreisbogen2 = kreisbogen2;
		this.kreisausschnitt1 = kreisauschnitt1;
		this.kreisausschnitt2 = kreisauschnitt2;
		this.kreisausschnittGesamt = kreisauschnittGesamt;
		this.alpha = alpha;

		// Zylinder
		this.hoehe = hoehe;
		this.grundflaecheZylinder1 = grundflaecheZylinder1;
		this.grundflaecheZylinder2 = grundflaecheZylinder2;
		this.grundflaecheZylinderGesamt = grundflaecheZylinderGesamt;
		this.mantelflaecheZylinder1 = mantelflaecheZylinder1;
		this.mantelflaecheZylinder2 = mantelflaecheZylinder2;
		this.mantelflaecheZylinderGesamt = mantelflaecheZylinderGesamt;
		this.oberflaecheZylinder1 = oberflaecheZylinder1;
		this.oberflaecheZylinder2 = oberflaecheZylinder2;
		this.oberflaecheZylinderGesamt = oberflaecheZylinderGesamt;
		this.volumenZylinder1 = volumenZylinder1;
		this.volumenZylinder2 = volumenZylinder2;
		this.volumenZylinderGesamt = volumenZylinderGesamt;

		// Kegel
		this.grundflaecheKegel1 = grundflaecheKegel1;
		this.grundflaecheKegel2 = grundflaecheKegel2;
		this.grundflaecheKegelGesamt = grundflaecheKegelGesamt;
		this.mantelflaecheKegel1 = mantelflaecheKegel1;
		this.mantelflaecheKegel2 = mantelflaecheKegel2;
		this.mantelflaecheKegelGesamt = mantelflaecheKegelGesamt;
		this.oberflaecheKegel1 = oberflaecheKegel1;
		this.oberflaecheKegel2 = oberflaecheKegel2;
		this.oberflaecheKegelGesamt = oberflaecheKegelGesamt;
		this.volumenKegel1 = volumenKegel1;
		this.volumenKegel2 = volumenKegel2;
		this.volumenKegelGesamt = volumenKegelGesamt;
		this.seitenhoehe1 = seitenhoehe1;
		this.seitenhoehe2 = seitenhoehe2;

		// Kugel
		this.oberflaecheKugel1 = oberflaecheKugel1;
		this.oberflaecheKugel2 = oberflaecheKugel2;
		this.oberflaecheKugelGesamt = oberflaecheKugelGesamt;
		this.volumenKugel1 = volumenKugel1;
		this.volumenKugel2 = volumenKugel2;
		this.volumenKugelGesamt = volumenKugelGesamt;
	}

	// Getter und Setter

	// Vorraussetzungen

	public int getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public double getRadius1() {
		return radius1;
	}

	public void setRadius1(double radius1) {
		this.radius1 = radius1;
	}

	public double getRadius2() {
		return radius2;
	}

	public void setRadius2(double radius2) {
		this.radius2 = radius2;
	}

	public double getDurchmesser1() {
		return durchmesser1;
	}

	public void setDurchmesser1(double durchmesser1) {
		this.durchmesser1 = durchmesser1;
	}

	public double getDurchmesser2() {
		return durchmesser2;
	}

	public void setDurchmesser2(double durchmesser2) {
		this.durchmesser2 = durchmesser2;
	}

	public double getUmfang1() {
		return umfang1;
	}

	public void setUmfang1(double umfang1) {
		this.umfang1 = umfang1;
	}

	public double getUmfang2() {
		return umfang2;
	}

	public void setUmfang2(double umfang2) {
		this.umfang2 = umfang2;
	}

	public double getFlaecheninhalt1() {
		return flaecheninhalt1;
	}

	public void setFlaecheninhalt1(double flaecheninhalt1) {
		this.flaecheninhalt1 = flaecheninhalt1;
	}

	public double getFlaecheninhalt2() {
		return flaecheninhalt2;
	}

	public void setFlaecheninhalt2(double flaecheninhalt2) {
		this.flaecheninhalt2 = flaecheninhalt2;
	}

	public double getFlaecheninhaltGesamt() {
		return flaecheninhaltGesamt;
	}

	public void setFlaecheninhaltGesamt(double flaecheninhaltGesamt) {
		this.flaecheninhaltGesamt = flaecheninhaltGesamt;
	}

	// Kreisteile

	public double getKreisbogen1() {
		return kreisbogen1;
	}

	public void setKreisbogen1(double kreisbogen1) {
		this.kreisbogen1 = kreisbogen1;
	}

	public double getKreisbogen2() {
		return kreisbogen2;
	}

	public void setKreisbogen2(double kreisbogen2) {
		this.kreisbogen2 = kreisbogen2;
	}

	public double getKreisausschnitt1() {
		return kreisausschnitt1;
	}

	public void setKreisausschnitt1(double kreisausschnitt1) {
		this.kreisausschnitt1 = kreisausschnitt1;
	}

	public double getKreisausschnitt2() {
		return kreisausschnitt2;
	}

	public void setKreisausschnitt2(double kreisausschnitt2) {
		this.kreisausschnitt2 = kreisausschnitt2;
	}

	public double getKreisausschnittGesamt() {
		return kreisausschnittGesamt;
	}

	public void setKreisausschnittGesamt(double kreisausschnittGesamt) {
		this.kreisausschnittGesamt = kreisausschnittGesamt;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	// Zylinder

	public double getHoehe() {
		return hoehe;
	}

	public void setHoehe(double hoehe) {
		this.hoehe = hoehe;
	}

	public double getGrundflaecheZylinder1() {
		return grundflaecheZylinder1;
	}

	public void setGrundflaecheZylinder1(double grundflaecheZylinder1) {
		this.grundflaecheZylinder1 = grundflaecheZylinder1;
	}

	public double getGrundflaecheZylinder2() {
		return grundflaecheZylinder2;
	}

	public void setGrundflaecheZylinder2(double grundflaecheZylinder2) {
		this.grundflaecheZylinder2 = grundflaecheZylinder2;
	}

	public double getGrundflaecheZylinderGesamt() {
		return grundflaecheZylinderGesamt;
	}

	public void setGrundflaecheZylinderGesamt(double grundflaecheZylinderGesamt) {
		this.grundflaecheZylinderGesamt = grundflaecheZylinderGesamt;
	}

	public double getMantelflaecheZylinder1() {
		return mantelflaecheZylinder1;
	}

	public void setMantelflaecheZylinder1(double mantelflaecheZylinder1) {
		this.mantelflaecheZylinder1 = mantelflaecheZylinder1;
	}

	public double getMantelflaecheZylinder2() {
		return mantelflaecheZylinder2;
	}

	public void setMantelflaecheZylinder2(double mantelflaecheZylinder2) {
		this.mantelflaecheZylinder2 = mantelflaecheZylinder2;
	}

	public double getMantelflaecheZylinderGesamt() {
		return mantelflaecheZylinderGesamt;
	}

	public void setMantelflaecheZylinderGesamt(double mantelflaecheZylinderGesamt) {
		this.mantelflaecheZylinderGesamt = mantelflaecheZylinderGesamt;
	}

	public double getOberflaecheZylinder1() {
		return oberflaecheZylinder1;
	}

	public void setOberflaecheZylinder1(double oberflaecheZylinder1) {
		this.oberflaecheZylinder1 = oberflaecheZylinder1;
	}

	public double getOberflaecheZylinder2() {
		return oberflaecheZylinder2;
	}

	public void setOberflaecheZylinder2(double oberflaecheZylinder2) {
		this.oberflaecheZylinder2 = oberflaecheZylinder2;
	}

	public double getOberflaecheZylinderGesamt() {
		return oberflaecheZylinderGesamt;
	}

	public void setOberflaecheZylinderGesamt(double oberflaecheZylinderGesamt) {
		this.oberflaecheZylinderGesamt = oberflaecheZylinderGesamt;
	}

	public double getVolumenZylinder1() {
		return volumenZylinder1;
	}

	public void setVolumenZylinder1(double volumenZylinder1) {
		this.volumenZylinder1 = volumenZylinder1;
	}

	public double getVolumenZylinder2() {
		return volumenZylinder2;
	}

	public void setVolumenZylinder2(double volumenZylinder2) {
		this.volumenZylinder2 = volumenZylinder2;
	}

	public double getVolumenZylinderGesamt() {
		return volumenZylinderGesamt;
	}

	public void setVolumenZylinderGesamt(double volumenZylinderGesamt) {
		this.volumenZylinderGesamt = volumenZylinderGesamt;
	}

	// Kegel

	public double getGrundflaecheKegel1() {
		return grundflaecheKegel1;
	}

	public void setGrundflaecheKegel1(double grundflaecheKegel1) {
		this.grundflaecheKegel1 = grundflaecheKegel1;
	}

	public double getGrundflaecheKegel2() {
		return grundflaecheKegel2;
	}

	public void setGrundflaecheKegel2(double grundflaecheKegel2) {
		this.grundflaecheKegel2 = grundflaecheKegel2;
	}

	public double getGrundflaecheKegelGesamt() {
		return grundflaecheKegelGesamt;
	}

	public void setGrundflaecheKegelGesamt(double grundflaecheKegelGesamt) {
		this.grundflaecheKegelGesamt = grundflaecheKegelGesamt;
	}

	public double getMantelflaecheKegel1() {
		return mantelflaecheKegel1;
	}

	public void setMantelflaecheKegel1(double mantelflaecheKegel1) {
		this.mantelflaecheKegel1 = mantelflaecheKegel1;
	}

	public double getMantelflaecheKegel2() {
		return mantelflaecheKegel2;
	}

	public void setMantelflaecheKegel2(double mantelflaecheKegel2) {
		this.mantelflaecheKegel2 = mantelflaecheKegel2;
	}

	public double getMantelflaecheKegelGesamt() {
		return mantelflaecheKegelGesamt;
	}

	public void setMantelflaecheKegelGesamt(double mantelflaecheKegelGesamt) {
		this.mantelflaecheKegelGesamt = mantelflaecheKegelGesamt;
	}

	public double getOberflaecheKegel1() {
		return oberflaecheKegel1;
	}

	public void setOberflaecheKegel1(double oberflaecheKegel1) {
		this.oberflaecheKegel1 = oberflaecheKegel1;
	}

	public double getOberflaecheKegel2() {
		return oberflaecheKegel2;
	}

	public void setOberflaecheKegel2(double oberflaecheKegel2) {
		this.oberflaecheKegel2 = oberflaecheKegel2;
	}

	public double getOberflaecheKegelGesamt() {
		return oberflaecheKegelGesamt;
	}

	public void setOberflaecheKegelGesamt(double oberflaecheKegelGesamt) {
		this.oberflaecheKegelGesamt = oberflaecheKegelGesamt;
	}

	public double getVolumenKegel1() {
		return volumenKegel1;
	}

	public void setVolumenKegel1(double volumenKegel1) {
		this.volumenKegel1 = volumenKegel1;
	}

	public double getVolumenKegel2() {
		return volumenKegel2;
	}

	public void setVolumenKegel2(double volumenKegel2) {
		this.volumenKegel2 = volumenKegel2;
	}

	public double getVolumenKegelGesamt() {
		return volumenKegelGesamt;
	}

	public void setVolumenKegelGesamt(double volumenKegelGesamt) {
		this.volumenKegelGesamt = volumenKegelGesamt;
	}

	public double getSeitenhoehe1() {
		return seitenhoehe1;
	}

	public void setSeitenhoehe1(double seitenhoehe1) {
		this.seitenhoehe1 = seitenhoehe1;
	}

	public double getSeitenhoehe2() {
		return seitenhoehe2;
	}

	public void setSeitenhoehe2(double seitenhoehe2) {
		this.seitenhoehe2 = seitenhoehe2;
	}

	// Kugel

	public double getOberflaecheKugel1() {
		return oberflaecheKugel1;
	}

	public void setOberflaecheKugel1(double oberflaecheKugel1) {
		this.oberflaecheKugel1 = oberflaecheKugel1;
	}

	public double getOberflaecheKugel2() {
		return oberflaecheKugel2;
	}

	public void setOberflaecheKugel2(double oberflaecheKugel2) {
		this.oberflaecheKugel2 = oberflaecheKugel2;
	}

	public double getOberflaecheKugelGesamt() {
		return oberflaecheKugelGesamt;
	}

	public void setOberflaecheKugelGesamt(double oberflaecheKugelGesamt) {
		this.oberflaecheKugelGesamt = oberflaecheKugelGesamt;
	}

	public double getVolumenKugel1() {
		return volumenKugel1;
	}

	public void setVolumenKugel1(double volumenKugel1) {
		this.volumenKugel1 = volumenKugel1;
	}

	public double getVolumenKugel2() {
		return volumenKugel2;
	}

	public void setVolumenKugel2(double volumenKugel2) {
		this.volumenKugel2 = volumenKugel2;
	}

	public double getVolumenKugelGesamt() {
		return volumenKugelGesamt;
	}

	public void setVolumenKugelGesamt(double volumenKugelGesamt) {
		this.volumenKugelGesamt = volumenKugelGesamt;
	}

	// toString() Methode
	@Override
	public String toString() {
		return "Kreis [decimalPlaces=" + decimalPlaces + ", radius1=" + radius1 + ", radius2=" + radius2
				+ ", durchmesser1=" + durchmesser1 + ", durchmesser2=" + durchmesser2 + ", umfang1=" + umfang1
				+ ", umfang2=" + umfang2 + ", flaecheninhalt1=" + flaecheninhalt1 + ", flaecheninhalt2="
				+ flaecheninhalt2 + ", flaecheninhaltGesamt=" + flaecheninhaltGesamt + ", kreisbogen1=" + kreisbogen1
				+ ", kreisbogen2=" + kreisbogen2 + ", kreisausschnitt1=" + kreisausschnitt1 + ", kreisauschnitt2="
				+ kreisausschnitt2 + ", kreisausschnittGesamt=" + kreisausschnittGesamt + ", alpha=" + alpha
				+ ", hoehe=" + hoehe + ", grundflaecheZylinder1=" + grundflaecheZylinder1 + ", grundflaecheZylinder2="
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
				+ ", volumenKugelGesamt=" + volumenKugelGesamt + "]";
	}

}

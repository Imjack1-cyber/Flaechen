package de.m4automotive.model;

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

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getUmfangDreieck() {
		return umfangDreieck;
	}

	public void setUmfangDreieck(double umfangDreieck) {
		this.umfangDreieck = umfangDreieck;
	}

	public double getFlaecheninhaltDreieck() {
		return flaecheninhaltDreieck;
	}

	public void setFlaecheninhaltDreieck(double flaecheninhaltDreieck) {
		this.flaecheninhaltDreieck = flaecheninhaltDreieck;
	}

	public double getHoeheA() {
		return hoeheA;
	}

	public void setHoeheA(double hoeheA) {
		this.hoeheA = hoeheA;
	}

	public double getHoeheB() {
		return hoeheB;
	}

	public void setHoeheB(double hoeheB) {
		this.hoeheB = hoeheB;
	}

	public double getHoeheC() {
		return hoeheC;
	}

	public void setHoeheC(double hoeheC) {
		this.hoeheC = hoeheC;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getGamma() {
		return gamma;
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
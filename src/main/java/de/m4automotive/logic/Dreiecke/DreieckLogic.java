package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Dreieck;

public class DreieckLogic {

	private static final Logger LOG = LogManager.getLogger(DreieckLogic.class);

	public void berechneDreiecke(HttpServletRequest request, HttpServletResponse response, Dreieck dreieck)
			throws ServletException, IOException {

		LOG.debug("berechneDreiecke() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);
		double a = Double.parseDouble(request.getParameter("a"));
		double b = Double.parseDouble(request.getParameter("b"));
		double c = Double.parseDouble(request.getParameter("c"));
		double umfangDreieck = Double.parseDouble(request.getParameter("umfangDreieck"));
		double flaecheninhaltDreieck = Double.parseDouble(request.getParameter("flaecheninhaltDreieck"));
		double alpha = Double.parseDouble(request.getParameter("alpha"));
		double beta = Double.parseDouble(request.getParameter("beta"));
		double gamma = Double.parseDouble(request.getParameter("gamma"));
		double hoeheA = Double.parseDouble(request.getParameter("hoeheA"));
		double hoeheB = Double.parseDouble(request.getParameter("hoeheB"));
		double hoeheC = Double.parseDouble(request.getParameter("hoeheC"));

		if (a == 0) {
			if (b > 1E-9 && c > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9) {
				double cosAlpha = Math.cos(Math.toRadians(alpha));
				double valInsideSqrt = b * b + c * c - 2.0 * b * c * cosAlpha;
				if (valInsideSqrt > 1E-9) {
					double calculatedA = Math.sqrt(valInsideSqrt);
					if (calculatedA > 1E-9 && !Double.isNaN(calculatedA)) {
						if ((b + c > calculatedA) && (calculatedA + c > b) && (calculatedA + b > c)) {
							a = calculatedA;
						}
					}
				}
			}

			if (a == 0 && umfangDreieck > 1E-9 && b > 1E-9 && c > 1E-9) {
				double calculatedA = umfangDreieck - b - c;
				if (calculatedA > 1E-9) {
					if ((b + c > calculatedA) && (calculatedA + c > b) && (calculatedA + b > c)) {
						a = calculatedA;
					}
				}
			}

			if (a == 0 && b > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9) {
				double sinBeta = Math.sin(Math.toRadians(beta));
				if (Math.abs(sinBeta) > 1E-9) {
					double calculatedA = b * Math.sin(Math.toRadians(alpha)) / sinBeta;
					if (calculatedA > 1E-9 && !Double.isNaN(calculatedA)) {
						if (c <= 1E-9 || ((b + c > calculatedA) && (calculatedA + c > b) && (calculatedA + b > c))) {
							a = calculatedA;
						}
					}
				}
			}

			if (a == 0 && c > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9) {
				double sinGamma = Math.sin(Math.toRadians(gamma));
				if (Math.abs(sinGamma) > 1E-9) {
					double calculatedA = c * Math.sin(Math.toRadians(alpha)) / sinGamma;
					if (calculatedA > 1E-9 && !Double.isNaN(calculatedA)) {
						if (b <= 1E-9 || ((b + c > calculatedA) && (calculatedA + c > b) && (calculatedA + b > c))) {
							a = calculatedA;
						}
					}
				}
			}

			if (a == 0) {
				a = Double.NaN;
			}
		}

		if (b == 0) {
			if (a > 1E-9 && c > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9) {
				double cosBeta = Math.cos(Math.toRadians(beta));
				double valInsideSqrt = a * a + c * c - 2.0 * a * c * cosBeta;
				if (valInsideSqrt > 1E-9) {
					double calculatedB = Math.sqrt(valInsideSqrt);
					if (calculatedB > 1E-9 && !Double.isNaN(calculatedB)) {
						if ((a + c > calculatedB) && (calculatedB + c > a) && (calculatedB + a > c)) {
							b = calculatedB;
						}
					}
				}
			}

			if (b == 0 && umfangDreieck > 1E-9 && a > 1E-9 && c > 1E-9) {
				double calculatedB = umfangDreieck - a - c;
				if (calculatedB > 1E-9) {
					if ((a + c > calculatedB) && (calculatedB + c > a) && (calculatedB + a > c)) {
						b = calculatedB;
					}
				}
			}

			if (b == 0 && a > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9) {
				double sinAlpha = Math.sin(Math.toRadians(alpha));
				if (Math.abs(sinAlpha) > 1E-9) {
					double calculatedB = a * Math.sin(Math.toRadians(beta)) / sinAlpha;
					if (calculatedB > 1E-9 && !Double.isNaN(calculatedB)) {
						if (c <= 1E-9 || ((a + c > calculatedB) && (calculatedB + c > a) && (calculatedB + a > c))) {
							b = calculatedB;
						}
					}
				}
			}

			if (b == 0 && c > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9) {
				double sinGamma = Math.sin(Math.toRadians(gamma));
				if (Math.abs(sinGamma) > 1E-9) {
					double calculatedB = c * Math.sin(Math.toRadians(beta)) / sinGamma;
					if (calculatedB > 1E-9 && !Double.isNaN(calculatedB)) {
						if (a <= 1E-9 || ((a + c > calculatedB) && (calculatedB + c > a) && (calculatedB + a > c))) {
							b = calculatedB;
						}
					}
				}
			}

			if (b == 0) {
				b = Double.NaN;
			}
		}

		if (c == 0) {
			if (a > 1E-9 && b > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9) {
				double cosGamma = Math.cos(Math.toRadians(gamma));
				double valInsideSqrt = a * a + b * b - 2.0 * a * b * cosGamma;
				if (valInsideSqrt > 1E-9) {
					double calculatedC = Math.sqrt(valInsideSqrt);
					if (calculatedC > 1E-9 && !Double.isNaN(calculatedC)) {
						if ((a + b > calculatedC) && (calculatedC + b > a) && (calculatedC + a > b)) {
							c = calculatedC;
						}
					}
				}
			}

			if (c == 0 && umfangDreieck > 1E-9 && a > 1E-9 && b > 1E-9) {
				double calculatedC = umfangDreieck - a - b;
				if (calculatedC > 1E-9) {
					if ((a + b > calculatedC) && (calculatedC + b > a) && (calculatedC + a > b)) {
						c = calculatedC;
					}
				}
			}

			if (c == 0 && a > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9) {
				double sinAlpha = Math.sin(Math.toRadians(alpha));
				if (Math.abs(sinAlpha) > 1E-9) {
					double calculatedC = a * Math.sin(Math.toRadians(gamma)) / sinAlpha;
					if (calculatedC > 1E-9 && !Double.isNaN(calculatedC)) {
						if (b <= 1E-9 || ((a + b > calculatedC) && (calculatedC + b > a) && (calculatedC + a > b))) {
							c = calculatedC;
						}
					}
				}
			}

			if (c == 0 && b > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9) {
				double sinBeta = Math.sin(Math.toRadians(beta));
				if (Math.abs(sinBeta) > 1E-9) {
					double calculatedC = b * Math.sin(Math.toRadians(gamma)) / sinBeta;
					if (calculatedC > 1E-9 && !Double.isNaN(calculatedC)) {
						if (a <= 1E-9 || ((a + b > calculatedC) && (calculatedC + b > a) && (calculatedC + a > b))) {
							c = calculatedC;
						}
					}
				}
			}

			if (c == 0) {
				c = Double.NaN;
			}
		}

		if (umfangDreieck == 0) {
			if (a > 1E-9 && b > 1E-9 && c > 1E-9 && !Double.isNaN(a) && !Double.isNaN(b) && !Double.isNaN(c)) {
				umfangDreieck = a + b + c;
			} else {
				umfangDreieck = Double.NaN;
			}
		}

		if (alpha == 0) {
			if (b != 0 && c != 0 && (b + c > a) && (a + c > b) && (a + b > c)) {
				double cosAlphaArg = (b * b + c * c - a * a) / (2.0 * b * c);
				if (cosAlphaArg >= -1.0 && cosAlphaArg <= 1.0) {
					double calculatedAlpha = Math.toDegrees(Math.acos(cosAlphaArg));
					if (calculatedAlpha > 1E-9 && calculatedAlpha < 180.0 - 1E-9 && !Double.isNaN(calculatedAlpha)) {
						alpha = calculatedAlpha;
					}
				}
			}

			if (alpha == 0 && beta != 0 && gamma != 0 && (180.0 - beta - gamma) > 1E-9
					&& (180.0 - beta - gamma) < 180.0 - 1E-9) {
				alpha = 180.0 - beta - gamma;
			} else if (alpha == 0 && b != 0 && beta != 0 && a != 0 && Math.sin(Math.toRadians(beta)) != 0) {
				double sinAlphaArg = a * Math.sin(Math.toRadians(beta)) / b;
				if (sinAlphaArg >= -1.0 && sinAlphaArg <= 1.0) {
					double calculatedAlpha = Math.toDegrees(Math.asin(sinAlphaArg));
					if (calculatedAlpha > 1E-9 && calculatedAlpha < 180.0 - 1E-9 && !Double.isNaN(calculatedAlpha)) {
						alpha = calculatedAlpha;
					}
				}
			} else if (alpha == 0 && c != 0 && gamma != 0 && a != 0 && Math.sin(Math.toRadians(gamma)) != 0) {
				double sinAlphaArg = a * Math.sin(Math.toRadians(gamma)) / c;
				if (sinAlphaArg >= -1.0 && sinAlphaArg <= 1.0) {
					double calculatedAlpha = Math.toDegrees(Math.asin(sinAlphaArg));
					if (calculatedAlpha > 1E-9 && calculatedAlpha < 180.0 - 1E-9 && !Double.isNaN(calculatedAlpha)) {
						alpha = calculatedAlpha;
					}
				}
			} else if (alpha == 0 && gamma == 90 && b != 0) {
				double calculatedAlpha = Math.toDegrees(Math.atan(a / b));
				if (calculatedAlpha > 1E-9 && calculatedAlpha < 180.0 - 1E-9 && !Double.isNaN(calculatedAlpha)) {
					alpha = calculatedAlpha;
				}
			}

			if (alpha == 0) {
				alpha = Double.NaN;
			}
		}

		if (beta == 0) {

			if (a != 0 && c != 0 && (a + c > b) && (a + b > c) && (b + c > a)) {
				double cosBetaArg = (a * a + c * c - b * b) / (2.0 * a * c);

				if (cosBetaArg >= -1.0 && cosBetaArg <= 1.0) {
					double calculatedBeta = Math.toDegrees(Math.acos(cosBetaArg));
					if (calculatedBeta != 0 && !Double.isNaN(calculatedBeta)) {
						beta = calculatedBeta;
					}
				}
			}

			if (beta == 0 && alpha != 0 && gamma != 0 && (180.0 - alpha - gamma) > 1E-9) {
				beta = 180.0 - alpha - gamma;
			} else if (beta == 0 && a != 0 && alpha != 0 && b != 0 && Math.sin(Math.toRadians(alpha)) != 0) {
				double sinBetaArg = b * Math.sin(Math.toRadians(alpha)) / a;
				if (sinBetaArg >= -1.0 && sinBetaArg <= 1.0) {
					double calculatedBeta = Math.toDegrees(Math.asin(sinBetaArg));
					if (calculatedBeta != 0 && !Double.isNaN(calculatedBeta)) {
						beta = calculatedBeta;
					}
				}
			} else if (beta == 0 && c != 0 && gamma != 0 && b != 0 && Math.sin(Math.toRadians(gamma)) != 0) {
				double sinBetaArg = b * Math.sin(Math.toRadians(gamma)) / c;
				if (sinBetaArg >= -1.0 && sinBetaArg <= 1.0) {
					double calculatedBeta = Math.toDegrees(Math.asin(sinBetaArg));
					if (calculatedBeta != 0 && !Double.isNaN(calculatedBeta)) {
						beta = calculatedBeta;
					}
				}
			} else if (beta == 0 && gamma == 90 && a != 0) {
				double calculatedBeta = Math.toDegrees(Math.atan(b / a));
				if (calculatedBeta != 0 && !Double.isNaN(calculatedBeta)) {
					beta = calculatedBeta;
				}
			}

			if (beta == 0) {
				beta = Double.NaN;
			}
		}

		if (gamma == 0) {
			if (a != 0 && b != 0 && (a + b > c) && (a + c > b) && (b + c > a)) {
				double cosGammaArg = (a * a + b * b - c * c) / (2.0 * a * b);
				if (cosGammaArg >= -1.0 && cosGammaArg <= 1.0) {
					double calculatedGamma = Math.toDegrees(Math.acos(cosGammaArg));
					if (calculatedGamma > 1E-9 && calculatedGamma < 180.0 - 1E-9 && !Double.isNaN(calculatedGamma)) {
						gamma = calculatedGamma;
					}
				}
			}

			if (gamma == 0 && alpha != 0 && beta != 0 && (180.0 - alpha - beta) > 1E-9
					&& (180.0 - alpha - beta) < 180.0 - 1E-9) {
				gamma = 180.0 - alpha - beta;
			} else if (gamma == 0 && a != 0 && alpha != 0 && c != 0 && Math.sin(Math.toRadians(alpha)) != 0) {
				double sinGammaArg = c * Math.sin(Math.toRadians(alpha)) / a;
				if (sinGammaArg >= -1.0 && sinGammaArg <= 1.0) {
					double calculatedGamma = Math.toDegrees(Math.asin(sinGammaArg));
					if (calculatedGamma > 1E-9 && calculatedGamma < 180.0 - 1E-9 && !Double.isNaN(calculatedGamma)) {
						gamma = calculatedGamma;
					}
				}
			} else if (gamma == 0 && b != 0 && beta != 0 && c != 0 && Math.sin(Math.toRadians(beta)) != 0) {
				double sinGammaArg = c * Math.sin(Math.toRadians(beta)) / b;
				if (sinGammaArg >= -1.0 && sinGammaArg <= 1.0) {
					double calculatedGamma = Math.toDegrees(Math.asin(sinGammaArg));
					if (calculatedGamma > 1E-9 && calculatedGamma < 180.0 - 1E-9 && !Double.isNaN(calculatedGamma)) {
						gamma = calculatedGamma;
					}
				}
			}

			if (gamma == 0) {
				gamma = Double.NaN;
			}
		}

		if (hoeheA == 0) {
			if (b > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9 && !Double.isNaN(b) && !Double.isNaN(gamma)) {
				double calculatedHoehe = b * Math.sin(Math.toRadians(gamma));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheA = calculatedHoehe;
				}
			}

			if (hoeheA == 0 && c > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9 && !Double.isNaN(c)
					&& !Double.isNaN(beta)) {
				double calculatedHoehe = c * Math.sin(Math.toRadians(beta));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheA = calculatedHoehe;
				}
			}

			if (hoeheA == 0 && flaecheninhaltDreieck > 1E-9 && a > 1E-9 && !Double.isNaN(flaecheninhaltDreieck)
					&& !Double.isNaN(a)) {
				double calculatedHoehe = (2.0 * flaecheninhaltDreieck) / a;
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheA = calculatedHoehe;
				}
			}

			if (hoeheA == 0) {
				hoeheA = Double.NaN;
			}
		}

		if (hoeheB == 0) {
			if (a > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9 && !Double.isNaN(a) && !Double.isNaN(gamma)) {
				double calculatedHoehe = a * Math.sin(Math.toRadians(gamma));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheB = calculatedHoehe;
				}
			}
			if (hoeheB == 0 && c > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9 && !Double.isNaN(c)
					&& !Double.isNaN(alpha)) {
				double calculatedHoehe = c * Math.sin(Math.toRadians(alpha));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheB = calculatedHoehe;
				}
			}

			if (hoeheB == 0 && flaecheninhaltDreieck > 1E-9 && b > 1E-9 && !Double.isNaN(flaecheninhaltDreieck)
					&& !Double.isNaN(b)) {
				double calculatedHoehe = (2.0 * flaecheninhaltDreieck) / b;
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheB = calculatedHoehe;
				}
			}

			if (hoeheB == 0) {
				hoeheB = Double.NaN;
			}
		}

		if (hoeheC == 0) {
			if (a > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9 && !Double.isNaN(a) && !Double.isNaN(beta)) {
				double calculatedHoehe = a * Math.sin(Math.toRadians(beta));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheC = calculatedHoehe;
				}
			}

			if (hoeheC == 0 && b > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9 && !Double.isNaN(b)
					&& !Double.isNaN(alpha)) {
				double calculatedHoehe = b * Math.sin(Math.toRadians(alpha));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheC = calculatedHoehe;
				}
			}

			if (hoeheC == 0 && flaecheninhaltDreieck > 1E-9 && c > 1E-9 && !Double.isNaN(flaecheninhaltDreieck)
					&& !Double.isNaN(c)) {
				double calculatedHoehe = (2.0 * flaecheninhaltDreieck) / c;
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheC = calculatedHoehe;
				}
			}

			if (hoeheC == 0) {
				hoeheC = Double.NaN;
			}
		}

		if (flaecheninhaltDreieck == 0) {
			// Try Heron's formula first if a, b, c are known and valid
			if (a > 1E-9 && b > 1E-9 && c > 1E-9 && !Double.isNaN(a) && !Double.isNaN(b) && !Double.isNaN(c)) {
				double s = (a + b + c) / 2.0;
				double valInsideSqrt = s * (s - a) * (s - b) * (s - c);
				if (valInsideSqrt > 1E-12) {
					double calculatedArea = Math.sqrt(valInsideSqrt);
					if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
						flaecheninhaltDreieck = calculatedArea;
					}
				}
			}

			if (flaecheninhaltDreieck == 0 && a > 1E-9 && b > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9
					&& !Double.isNaN(a) && !Double.isNaN(b) && !Double.isNaN(gamma)) {
				double calculatedArea = 0.5 * a * b * Math.sin(Math.toRadians(gamma));
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			if (flaecheninhaltDreieck == 0 && b > 1E-9 && c > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9
					&& !Double.isNaN(b) && !Double.isNaN(c) && !Double.isNaN(alpha)) {
				double calculatedArea = 0.5 * b * c * Math.sin(Math.toRadians(alpha));
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			if (flaecheninhaltDreieck == 0 && a > 1E-9 && c > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9
					&& !Double.isNaN(a) && !Double.isNaN(c) && !Double.isNaN(beta)) {
				double calculatedArea = 0.5 * a * c * Math.sin(Math.toRadians(beta));
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			if (flaecheninhaltDreieck == 0 && a > 1E-9 && hoeheA > 1E-9 && !Double.isNaN(a) && !Double.isNaN(hoeheA)) {
				double calculatedArea = 0.5 * a * hoeheA;
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			if (flaecheninhaltDreieck == 0 && b > 1E-9 && hoeheB > 1E-9 && !Double.isNaN(b) && !Double.isNaN(hoeheB)) {
				double calculatedArea = 0.5 * b * hoeheB;
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			if (flaecheninhaltDreieck == 0 && c > 1E-9 && hoeheC > 1E-9 && !Double.isNaN(c) && !Double.isNaN(hoeheC)) {
				double calculatedArea = 0.5 * c * hoeheC;
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			if (flaecheninhaltDreieck == 0) {
				flaecheninhaltDreieck = Double.NaN;
			}
		}

		dreieck.setA(a);
		LOG.debug("a: " + a);
		dreieck.setB(b);
		LOG.debug("b: " + b);
		dreieck.setC(c);
		LOG.debug("c: " + c);
		dreieck.setUmfangDreieck(umfangDreieck);
		LOG.debug("umfangDreieck: " + umfangDreieck);
		dreieck.setFlaecheninhaltDreieck(flaecheninhaltDreieck);
		LOG.debug("flaecheninhaltDreieck: " + flaecheninhaltDreieck);
		dreieck.setHoeheA(hoeheA);
		LOG.debug("hoeheA: " + hoeheA);
		dreieck.setHoeheB(hoeheB);
		LOG.debug("hoeheB: " + hoeheB);
		dreieck.setHoeheC(hoeheC);
		LOG.debug("hoeheC: " + hoeheC);
		dreieck.setAlpha(alpha);
		LOG.debug("alpha: " + alpha);
		dreieck.setBeta(beta);
		LOG.debug("beta: " + beta);
		dreieck.setGamma(gamma);
		LOG.debug("gamma: " + gamma);

		LOG.debug("Setting DecimalFormat into Dreieck bean...");
		DF df = new DF();
		dreieck.setDecimalFormat(df.getDecimalFormat(decimalPlaces));
	}
}
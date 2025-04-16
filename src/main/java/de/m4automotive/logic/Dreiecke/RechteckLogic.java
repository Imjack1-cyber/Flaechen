package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Rechteck;

public class RechteckLogic {

	private static final Logger LOG = LogManager.getLogger(RechteckLogic.class);

	public void berechneRechteck(HttpServletRequest request, HttpServletResponse response, Rechteck rechteck)
			throws ServletException, IOException {
		LOG.debug("berechneRechteck() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Rechteck) Start =======>");
		double r_seiteA = Double.parseDouble(request.getParameter("r_seiteA").replace(',', '.'));
		LOG.debug("r_seiteA: " + r_seiteA);
		double r_seiteB = Double.parseDouble(request.getParameter("r_seiteB").replace(',', '.'));
		LOG.debug("r_seiteB: " + r_seiteB);
		double r_flaecheninhalt = Double.parseDouble(request.getParameter("r_flaecheninhalt").replace(',', '.'));
		LOG.debug("r_flaecheninhalt: " + r_flaecheninhalt);
		double r_umfang = Double.parseDouble(request.getParameter("r_umfang").replace(',', '.'));
		LOG.debug("r_umfang: " + r_umfang);
		double r_diagonale = Double.parseDouble(request.getParameter("r_diagonale").replace(',', '.'));
		LOG.debug("r_diagonale: " + r_diagonale);
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Rechteck) Ende =======>");
		LOG.debug(" ");

		LOG.debug("<======= Berechnung Rechteck Start =======>");

		if (r_seiteA == 0) {
			LOG.debug("Versuche Berechnung der r_seiteA");
			if (!Double.isNaN(r_flaecheninhalt) && !Double.isNaN(r_seiteB) && r_seiteB > 0
					&& (r_flaecheninhalt / r_seiteB) > 0) {
				r_seiteA = r_flaecheninhalt / r_seiteB;
				LOG.debug("r_seiteA berechnet mit: Fläche / B");
			} else if (!Double.isNaN(r_umfang) && !Double.isNaN(r_seiteB) && (r_umfang / 2.0 - r_seiteB) > 0) {
				r_seiteA = (r_umfang / 2.0) - r_seiteB;
				LOG.debug("r_seiteA berechnet mit: (Umfang / 2) - B");
			} else if (!Double.isNaN(r_diagonale) && !Double.isNaN(r_seiteB)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteB, 2)
					&& Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2)) > 0) {
				r_seiteA = Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2));
				LOG.debug("r_seiteA berechnet mit: sqrt(Diagonale^2 - B^2)");
			} else {
				r_seiteA = Double.NaN;
				LOG.debug("r_seiteA konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("r_seiteA war gegeben.");
		}

		if (r_seiteB == 0) {
			LOG.debug("Versuche Berechnung der r_seiteB");
			if (!Double.isNaN(r_flaecheninhalt) && !Double.isNaN(r_seiteA) && r_seiteA > 0
					&& (r_flaecheninhalt / r_seiteA) > 0) {
				r_seiteB = r_flaecheninhalt / r_seiteA;
				LOG.debug("r_seiteB berechnet mit: Fläche / A");
			} else if (!Double.isNaN(r_umfang) && !Double.isNaN(r_seiteA) && (r_umfang / 2.0 - r_seiteA) > 0) {
				r_seiteB = (r_umfang / 2.0) - r_seiteA;
				LOG.debug("r_seiteB berechnet mit: (Umfang / 2) - A");
			} else if (!Double.isNaN(r_diagonale) && !Double.isNaN(r_seiteA)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteA, 2)
					&& Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2)) > 0) {
				r_seiteB = Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2));
				LOG.debug("r_seiteB berechnet mit: sqrt(Diagonale^2 - A^2)");
			} else {
				r_seiteB = Double.NaN;
				LOG.debug("r_seiteB konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("r_seiteB war gegeben.");
		}

		if (r_flaecheninhalt == 0) {
			LOG.debug("Versuche Berechnung des r_flaecheninhalt");
			if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_seiteB) && (r_seiteA * r_seiteB) > 0) {
				r_flaecheninhalt = r_seiteA * r_seiteB;
				LOG.debug("r_flaecheninhalt berechnet mit: A * B");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_diagonale)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteA, 2)
					&& (r_seiteA * Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2))) > 0) {
				r_flaecheninhalt = r_seiteA * Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2));
				LOG.debug("r_flaecheninhalt berechnet aus A und Diagonale");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_diagonale)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteB, 2)
					&& (r_seiteB * Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2))) > 0) {
				r_flaecheninhalt = r_seiteB * Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2));
				LOG.debug("r_flaecheninhalt berechnet aus B und Diagonale");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_umfang)
					&& (r_seiteA * ((r_umfang / 2.0) - r_seiteA)) > 0) {
				r_flaecheninhalt = r_seiteA * ((r_umfang / 2.0) - r_seiteA);
				LOG.debug("r_flaecheninhalt berechnet aus A und Umfang");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_umfang)
					&& (r_seiteB * ((r_umfang / 2.0) - r_seiteB)) > 0) {
				r_flaecheninhalt = r_seiteB * ((r_umfang / 2.0) - r_seiteB);
				LOG.debug("r_flaecheninhalt berechnet aus B und Umfang");
			} else {
				r_flaecheninhalt = Double.NaN;
				LOG.debug("r_flaecheninhalt konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("r_flaecheninhalt war gegeben.");
		}

		if (r_umfang == 0) {
			LOG.debug("Versuche Berechnung des r_umfang");
			if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_seiteB) && (2 * (r_seiteA + r_seiteB)) > 0) {
				r_umfang = 2 * (r_seiteA + r_seiteB);
				LOG.debug("r_umfang berechnet mit: 2 * (A + B)");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_flaecheninhalt) && r_seiteA > 0
					&& (2 * (r_seiteA + r_flaecheninhalt / r_seiteA)) > 0) {
				r_umfang = 2 * (r_seiteA + r_flaecheninhalt / r_seiteA);
				LOG.debug("r_umfang berechnet aus A und Fläche");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_flaecheninhalt) && r_seiteB > 0
					&& (2 * (r_seiteB + r_flaecheninhalt / r_seiteB)) > 0) {
				r_umfang = 2 * (r_seiteB + r_flaecheninhalt / r_seiteB);
				LOG.debug("r_umfang berechnet aus B und Fläche");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_diagonale)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteA, 2)
					&& (2 * (r_seiteA + Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2)))) > 0) {
				r_umfang = 2 * (r_seiteA + Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2)));
				LOG.debug("r_umfang berechnet aus A und Diagonale");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_diagonale)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteB, 2)
					&& (2 * (r_seiteB + Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2)))) > 0) {
				r_umfang = 2 * (r_seiteB + Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2)));
				LOG.debug("r_umfang berechnet aus B und Diagonale");
			} else {
				r_umfang = Double.NaN;
				LOG.debug("r_umfang konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("r_umfang war gegeben.");
		}

		if (r_diagonale == 0) {
			LOG.debug("Versuche Berechnung der r_diagonale");
			if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_seiteB)
					&& Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow(r_seiteB, 2)) > 0) {
				r_diagonale = Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow(r_seiteB, 2));
				LOG.debug("r_diagonale berechnet mit: sqrt(A^2 + B^2)");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_flaecheninhalt) && r_seiteA > 0
					&& Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow(r_flaecheninhalt / r_seiteA, 2)) > 0) {
				r_diagonale = Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow(r_flaecheninhalt / r_seiteA, 2));
				LOG.debug("r_diagonale berechnet aus A und Fläche");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_flaecheninhalt) && r_seiteB > 0
					&& Math.sqrt(Math.pow(r_seiteB, 2) + Math.pow(r_flaecheninhalt / r_seiteB, 2)) > 0) {
				r_diagonale = Math.sqrt(Math.pow(r_seiteB, 2) + Math.pow(r_flaecheninhalt / r_seiteB, 2));
				LOG.debug("r_diagonale berechnet aus B und Fläche");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_umfang)
					&& Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow((r_umfang / 2.0) - r_seiteA, 2)) > 0) {
				r_diagonale = Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow((r_umfang / 2.0) - r_seiteA, 2));
				LOG.debug("r_diagonale berechnet aus A und Umfang");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_umfang)
					&& Math.sqrt(Math.pow(r_seiteB, 2) + Math.pow((r_umfang / 2.0) - r_seiteB, 2)) > 0) {
				r_diagonale = Math.sqrt(Math.pow(r_seiteB, 2) + Math.pow((r_umfang / 2.0) - r_seiteB, 2));
				LOG.debug("r_diagonale berechnet aus B und Umfang");
			} else {
				r_diagonale = Double.NaN;
				LOG.debug("r_diagonale konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("r_diagonale war gegeben.");
		}

		LOG.debug("<======= Berechnung Rechteck Ende =======>");
		LOG.debug("");

		LOG.debug("<======= Setze Finale Werte in RechteckBean Start =======>");
		rechteck.setR_seiteA(r_seiteA);
		rechteck.setR_seiteB(r_seiteB);
		rechteck.setR_flaecheninhalt(r_flaecheninhalt);
		rechteck.setR_umfang(r_umfang);
		rechteck.setR_diagonale(r_diagonale);

		LOG.debug("r_seiteA" + r_seiteA);
		LOG.debug("r_seiteB" + r_seiteB);
		LOG.debug("r_flaecheninhalt" + r_flaecheninhalt);
		LOG.debug("r_umfang" + r_umfang);
		LOG.debug("r_diagonale" + r_diagonale);
		LOG.debug("<======= Setze Finale Werte in RechteckBean Ende =======>");

		LOG.debug("Setting DecimalFormat into Rechteck bean...");
		DF df = new DF();
		rechteck.setDecimalFormat(df.getDecimalFormat(decimalPlaces));

		LOG.debug("berechneRechteck() method finished.");
	}
}
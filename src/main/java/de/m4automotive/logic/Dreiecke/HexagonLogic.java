package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Hexagon;

public class HexagonLogic {

	private static final Logger LOG = LogManager.getLogger(Hexagon.class);

	public void berechneHexagon(HttpServletRequest request, HttpServletResponse response, Hexagon hexagon)
			throws ServletException, IOException {
		LOG.debug("berechneHexagon() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Hexagon) Start =======>");
		double h_seite = Double.parseDouble(request.getParameter("h_seite").replace(',', '.'));
		LOG.debug("h_seite", h_seite);
		double h_umfang = Double.parseDouble(request.getParameter("h_umfang").replace(',', '.'));
		LOG.debug("h_umfang", h_umfang);
		double h_radiusInkreis = Double.parseDouble(request.getParameter("h_radiusInkreis").replace(',', '.'));
		LOG.debug("h_radiusInkreis", h_radiusInkreis);
		double h_radiusUmkreis = Double.parseDouble(request.getParameter("h_radiusUmkreis").replace(',', '.'));
		LOG.debug("h_radiusUmkreis", h_radiusUmkreis);
		double h_flaecheninhalt = Double.parseDouble(request.getParameter("h_flaecheninhalt").replace(',', '.'));
		LOG.debug("h_flaecheninhalt", h_flaecheninhalt);
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Hexagon) Ende =======>");
		LOG.debug(" ");

		LOG.debug("<======= Berechnung Hexagon Start =======>");
		final double SQRT3 = Math.sqrt(3.0);

		if (h_seite == 0) {
			LOG.debug("Versuche Berechnung der h_seite");
			if (!Double.isNaN(h_umfang) && (h_umfang / 6.0) > 0) {
				h_seite = h_umfang / 6.0;
				LOG.debug("h_seite berechnet mit: Umfang / 6");
			} else if (!Double.isNaN(h_flaecheninhalt) && h_flaecheninhalt > 0
					&& (Math.sqrt((2.0 * h_flaecheninhalt) / (3.0 * SQRT3))) > 0) {
				h_seite = Math.sqrt((2.0 * h_flaecheninhalt) / (3.0 * SQRT3));
				LOG.debug("h_seite berechnet aus Fläche");
			} else if (!Double.isNaN(h_radiusInkreis) && SQRT3 != 0 && ((2.0 * h_radiusInkreis) / SQRT3) > 0) {
				h_seite = (2.0 * h_radiusInkreis) / SQRT3;
				LOG.debug("h_seite berechnet aus Inkreisradius");
			} else if (!Double.isNaN(h_radiusUmkreis) && h_radiusUmkreis > 0) {
				h_seite = h_radiusUmkreis;
				LOG.debug("h_seite berechnet aus Umkreisradius");
			} else {
				h_seite = Double.NaN;
				LOG.debug("h_seite konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("h_seite war gegeben.");
		}

		if (h_umfang == 0) {
			LOG.debug("Versuche Berechnung des h_umfang");
			if (!Double.isNaN(h_seite) && (6 * h_seite) > 0) {
				h_umfang = 6 * h_seite;
				LOG.debug("h_umfang berechnet mit: 6 * Seite");
			} else if (!Double.isNaN(h_flaecheninhalt) && !Double.isNaN(h_radiusInkreis) && h_radiusInkreis > 0
					&& ((2.0 * h_flaecheninhalt) / h_radiusInkreis) > 0) {
				h_umfang = (2.0 * h_flaecheninhalt) / h_radiusInkreis;
				LOG.debug("h_umfang berechnet mit: 2 * Fläche / Inkreisradius");
			} else if (!Double.isNaN(h_radiusInkreis) && SQRT3 != 0 && (6 * ((2.0 * h_radiusInkreis) / SQRT3)) > 0) {
				h_umfang = 6 * ((2.0 * h_radiusInkreis) / SQRT3);
				LOG.debug("h_umfang berechnet aus Inkreisradius");
			} else if (!Double.isNaN(h_radiusUmkreis) && (6 * h_radiusUmkreis) > 0) {
				h_umfang = 6 * h_radiusUmkreis;
				LOG.debug("h_umfang berechnet aus Umkreisradius");
			} else {
				h_umfang = Double.NaN;
				LOG.debug("h_umfang konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("h_umfang war gegeben.");
		}

		if (h_radiusInkreis == 0) {
			LOG.debug("Versuche Berechnung des h_radiusInkreis");
			if (!Double.isNaN(h_seite) && (h_seite * SQRT3 / 2.0) > 0) {
				h_radiusInkreis = (h_seite * SQRT3) / 2.0;
				LOG.debug("h_radiusInkreis berechnet mit: Seite * sqrt(3) / 2");
			} else if (!Double.isNaN(h_umfang) && (((h_umfang / 6.0) * SQRT3) / 2.0) > 0) {
				h_radiusInkreis = ((h_umfang / 6.0) * SQRT3) / 2.0;
				LOG.debug("h_radiusInkreis berechnet aus Umfang");
			} else if (!Double.isNaN(h_flaecheninhalt) && !Double.isNaN(h_umfang) && h_umfang > 0
					&& ((2.0 * h_flaecheninhalt) / h_umfang) > 0) {
				h_radiusInkreis = (2.0 * h_flaecheninhalt) / h_umfang;
				LOG.debug("h_radiusInkreis berechnet mit: 2 * Fläche / Umfang");
			} else if (!Double.isNaN(h_radiusUmkreis) && (h_radiusUmkreis * SQRT3 / 2.0) > 0) {
				h_radiusInkreis = h_radiusUmkreis * SQRT3 / 2.0;
				LOG.debug("h_radiusInkreis berechnet aus Umkreisradius");
			} else {
				h_radiusInkreis = Double.NaN;
				LOG.debug("h_radiusInkreis konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("h_radiusInkreis war gegeben.");
		}

		if (h_radiusUmkreis == 0) {
			LOG.debug("Versuche Berechnung des h_radiusUmkreis");
			if (!Double.isNaN(h_seite) && h_seite > 0) {
				h_radiusUmkreis = h_seite;
				LOG.debug("h_radiusUmkreis berechnet mit: Seite");
			} else if (!Double.isNaN(h_umfang) && (h_umfang / 6.0) > 0) {
				h_radiusUmkreis = h_umfang / 6.0;
				LOG.debug("h_radiusUmkreis berechnet aus Umfang");
			} else if (!Double.isNaN(h_radiusInkreis) && SQRT3 != 0 && ((2.0 * h_radiusInkreis) / SQRT3) > 0) {
				h_radiusUmkreis = (2.0 * h_radiusInkreis) / SQRT3;
				LOG.debug("h_radiusUmkreis berechnet aus Inkreisradius");
			} else if (!Double.isNaN(h_flaecheninhalt) && h_flaecheninhalt > 0
					&& (Math.sqrt((2.0 * h_flaecheninhalt) / (3.0 * SQRT3))) > 0) {
				h_radiusUmkreis = Math.sqrt((2.0 * h_flaecheninhalt) / (3.0 * SQRT3));
				LOG.debug("h_radiusUmkreis berechnet aus Fläche");
			} else {
				h_radiusUmkreis = Double.NaN;
				LOG.debug("h_radiusUmkreis konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("h_radiusUmkreis war gegeben.");
		}

		if (h_flaecheninhalt == 0) {
			LOG.debug("Versuche Berechnung des h_flaecheninhalt");
			double factor = (3.0 * SQRT3 / 2.0);
			if (!Double.isNaN(h_seite) && h_seite >= 0 && (factor * Math.pow(h_seite, 2)) > 0) {
				h_flaecheninhalt = factor * Math.pow(h_seite, 2);
				LOG.debug("h_flaecheninhalt berechnet aus Seite");
			} else if (!Double.isNaN(h_umfang) && !Double.isNaN(h_radiusInkreis)
					&& (h_umfang * h_radiusInkreis / 2.0) > 0) {
				h_flaecheninhalt = (h_umfang * h_radiusInkreis) / 2.0;
				LOG.debug("h_flaecheninhalt berechnet mit: Umfang * Inkreisradius / 2");
			} else if (!Double.isNaN(h_radiusUmkreis) && h_radiusUmkreis >= 0
					&& (factor * Math.pow(h_radiusUmkreis, 2)) > 0) {
				h_flaecheninhalt = factor * Math.pow(h_radiusUmkreis, 2);
				LOG.debug("h_flaecheninhalt berechnet aus Umkreisradius");
			} else {
				h_flaecheninhalt = Double.NaN;
				LOG.debug("h_flaecheninhalt konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("h_flaecheninhalt war gegeben.");
		}

		LOG.debug("<======= Berechnung Hexagon Ende =======>");
		LOG.debug("");

		LOG.debug("<======= Setze Finale Werte in HexagonBean Start =======>");
		hexagon.setH_seite(h_seite);
		hexagon.setH_umfang(h_umfang);
		hexagon.setH_radiusInkreis(h_radiusInkreis);
		hexagon.setH_radiusUmkreis(h_radiusUmkreis);
		hexagon.setH_flaecheninhalt(h_flaecheninhalt);

		LOG.debug("h_seite" + h_seite);
		LOG.debug("h_umfang" + h_umfang);
		LOG.debug("h_radiusInkreis" + h_radiusInkreis);
		LOG.debug("h_radiusUmkreis" + h_radiusUmkreis);
		LOG.debug("h_flaecheninhalt" + h_flaecheninhalt);
		LOG.debug("<======= Setze Finale Werte in HexagonBean Ende =======>");

		LOG.debug("Setting DecimalFormat into Hexagon bean...");
		DF df = new DF();
		hexagon.setDecimalFormat(df.getDecimalFormat(decimalPlaces));

		LOG.debug("berechneHexagon() method finished.");
	}
}
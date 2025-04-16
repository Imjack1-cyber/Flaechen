package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Octagon;

public class OctagonLogic {

	private static final Logger LOG = LogManager.getLogger(OctagonLogic.class);

	public void berechneOctagon(HttpServletRequest request, HttpServletResponse response, Octagon octagon)
			throws ServletException, IOException {
		LOG.debug("berechneOctagon() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Octagon) Start =======>");
		double o_seite = Double.parseDouble(request.getParameter("o_seite").replace(',', '.'));
		LOG.debug("o_seite" + o_seite);
		double o_umfang = Double.parseDouble(request.getParameter("o_umfang").replace(',', '.'));
		LOG.debug("o_umfang" + o_umfang);
		double o_flaecheninhalt = Double.parseDouble(request.getParameter("o_flaecheninhalt").replace(',', '.'));
		LOG.debug("o_flaecheninhalt" + o_flaecheninhalt);
		double o_radiusInkreis = Double.parseDouble(request.getParameter("o_radiusInkreis").replace(',', '.'));
		LOG.debug("o_radiusInkreis" + o_radiusInkreis);
		double o_radiusUmkreis = Double.parseDouble(request.getParameter("o_radiusUmkreis").replace(',', '.'));
		LOG.debug("o_radiusUmkreis" + o_radiusUmkreis);
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Octagon) Ende =======>");
		LOG.debug(" ");

		LOG.debug("<======= Berechnung Octagon Start =======>");
		final double SQRT2 = Math.sqrt(2.0);
		final double TAN_PI_8 = Math.tan(Math.PI / 8.0);
		final double SIN_PI_8 = Math.sin(Math.PI / 8.0);
		final double COS_PI_8 = Math.cos(Math.PI / 8.0);

		if (o_seite == 0) {
			LOG.debug("Versuche Berechnung der o_seite");
			if (!Double.isNaN(o_umfang) && (o_umfang / 8.0) > 0) {
				o_seite = o_umfang / 8.0;
				LOG.debug("o_seite berechnet mit: Umfang / 8");
			} else if (!Double.isNaN(o_flaecheninhalt) && o_flaecheninhalt > 0
					&& (Math.sqrt(o_flaecheninhalt / (2.0 * (1.0 + SQRT2)))) > 0) {
				o_seite = Math.sqrt(o_flaecheninhalt / (2.0 * (1.0 + SQRT2)));
				LOG.debug("o_seite berechnet aus Fläche");
			} else if (!Double.isNaN(o_radiusInkreis) && TAN_PI_8 != 0 && (2.0 * o_radiusInkreis * TAN_PI_8) > 0) {
				o_seite = 2.0 * o_radiusInkreis * TAN_PI_8;
				LOG.debug("o_seite berechnet aus Inkreisradius");
			} else if (!Double.isNaN(o_radiusUmkreis) && SIN_PI_8 != 0 && (2.0 * o_radiusUmkreis * SIN_PI_8) > 0) {
				o_seite = 2.0 * o_radiusUmkreis * SIN_PI_8;
				LOG.debug("o_seite berechnet aus Umkreisradius");
			} else {
				o_seite = Double.NaN;
				LOG.debug("o_seite konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("o_seite war gegeben.");
		}

		if (o_umfang == 0) {
			LOG.debug("Versuche Berechnung des o_umfang");
			if (!Double.isNaN(o_seite) && (8 * o_seite) > 0) {
				o_umfang = 8 * o_seite;
				LOG.debug("o_umfang berechnet mit: 8 * Seite");
			} else if (!Double.isNaN(o_flaecheninhalt) && !Double.isNaN(o_radiusInkreis) && o_radiusInkreis > 0
					&& ((2.0 * o_flaecheninhalt) / o_radiusInkreis) > 0) {
				o_umfang = (2.0 * o_flaecheninhalt) / o_radiusInkreis;
				LOG.debug("o_umfang berechnet mit: 2 * Fläche / Inkreisradius");
			} else if (!Double.isNaN(o_radiusInkreis) && TAN_PI_8 != 0
					&& (8 * (2.0 * o_radiusInkreis * TAN_PI_8)) > 0) {
				o_umfang = 8 * (2.0 * o_radiusInkreis * TAN_PI_8);
				LOG.debug("o_umfang berechnet aus Inkreisradius");
			} else if (!Double.isNaN(o_radiusUmkreis) && SIN_PI_8 != 0
					&& (8 * (2.0 * o_radiusUmkreis * SIN_PI_8)) > 0) {
				o_umfang = 8 * (2.0 * o_radiusUmkreis * SIN_PI_8);
				LOG.debug("o_umfang berechnet aus Umkreisradius");
			} else {
				o_umfang = Double.NaN;
				LOG.debug("o_umfang konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("o_umfang war gegeben.");
		}

		if (o_radiusInkreis == 0) {
			LOG.debug("Versuche Berechnung des o_radiusInkreis");
			if (!Double.isNaN(o_seite) && TAN_PI_8 != 0 && (o_seite / (2.0 * TAN_PI_8)) > 0) {
				o_radiusInkreis = o_seite / (2.0 * TAN_PI_8);
				LOG.debug("o_radiusInkreis berechnet mit: Seite / (2 * tan(PI/8))");
			} else if (!Double.isNaN(o_umfang) && TAN_PI_8 != 0 && ((o_umfang / 8.0) / (2.0 * TAN_PI_8)) > 0) {
				o_radiusInkreis = (o_umfang / 8.0) / (2.0 * TAN_PI_8);
				LOG.debug("o_radiusInkreis berechnet aus Umfang");
			} else if (!Double.isNaN(o_flaecheninhalt) && !Double.isNaN(o_umfang) && o_umfang > 0
					&& ((2.0 * o_flaecheninhalt) / o_umfang) > 0) {
				o_radiusInkreis = (2.0 * o_flaecheninhalt) / o_umfang;
				LOG.debug("o_radiusInkreis berechnet mit: 2 * Fläche / Umfang");
			} else if (!Double.isNaN(o_radiusUmkreis) && o_radiusUmkreis > 0 && (o_radiusUmkreis * COS_PI_8) > 0) {
				o_radiusInkreis = o_radiusUmkreis * COS_PI_8;
				LOG.debug("o_radiusInkreis berechnet aus Umkreisradius");
			} else {
				o_radiusInkreis = Double.NaN;
				LOG.debug("o_radiusInkreis konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("o_radiusInkreis war gegeben.");
		}

		if (o_radiusUmkreis == 0) {
			LOG.debug("Versuche Berechnung des o_radiusUmkreis");
			if (!Double.isNaN(o_seite) && SIN_PI_8 != 0 && (o_seite / (2.0 * SIN_PI_8)) > 0) {
				o_radiusUmkreis = o_seite / (2.0 * SIN_PI_8);
				LOG.debug("o_radiusUmkreis berechnet mit: Seite / (2 * sin(PI/8))");
			} else if (!Double.isNaN(o_umfang) && SIN_PI_8 != 0 && ((o_umfang / 8.0) / (2.0 * SIN_PI_8)) > 0) {
				o_radiusUmkreis = (o_umfang / 8.0) / (2.0 * SIN_PI_8);
				LOG.debug("o_radiusUmkreis berechnet aus Umfang");
			} else if (!Double.isNaN(o_radiusInkreis) && o_radiusInkreis > 0 && COS_PI_8 != 0
					&& (o_radiusInkreis / COS_PI_8) > 0) {
				o_radiusUmkreis = o_radiusInkreis / COS_PI_8;
				LOG.debug("o_radiusUmkreis berechnet aus Inkreisradius");
			} else {
				o_radiusUmkreis = Double.NaN;
				LOG.debug("o_radiusUmkreis konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("o_radiusUmkreis war gegeben.");
		}

		if (o_flaecheninhalt == 0) {
			LOG.debug("Versuche Berechnung des o_flaecheninhalt");
			double factor = 2.0 * (1.0 + SQRT2);
			if (!Double.isNaN(o_seite) && o_seite >= 0 && (factor * Math.pow(o_seite, 2)) > 0) {
				o_flaecheninhalt = factor * Math.pow(o_seite, 2);
				LOG.debug("o_flaecheninhalt berechnet aus Seite");
			} else if (!Double.isNaN(o_umfang) && !Double.isNaN(o_radiusInkreis)
					&& (o_umfang * o_radiusInkreis / 2.0) > 0) {
				o_flaecheninhalt = (o_umfang * o_radiusInkreis) / 2.0;
				LOG.debug("o_flaecheninhalt berechnet mit: Umfang * Inkreisradius / 2");
			} else if (!Double.isNaN(o_radiusUmkreis) && o_radiusUmkreis >= 0
					&& (2.0 * SQRT2 * Math.pow(o_radiusUmkreis, 2)) > 0) {
				o_flaecheninhalt = 2.0 * SQRT2 * Math.pow(o_radiusUmkreis, 2);
				LOG.debug("o_flaecheninhalt berechnet aus Umkreisradius");
			} else {
				o_flaecheninhalt = Double.NaN;
				LOG.debug("o_flaecheninhalt konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("o_flaecheninhalt war gegeben.");
		}

		LOG.debug("<======= Berechnung Octagon Ende =======>");
		LOG.debug("");

		LOG.debug("<======= Setze Finale Werte in OctagonBean Start =======>");
		octagon.setO_seite(o_seite);
		octagon.setO_umfang(o_umfang);
		octagon.setO_flaecheninhalt(o_flaecheninhalt);
		octagon.setO_radiusInkreis(o_radiusInkreis);
		octagon.setO_radiusUmkreis(o_radiusUmkreis);

		LOG.debug("o_seite" + o_seite);
		LOG.debug("o_umfang" + o_umfang);
		LOG.debug("o_flaecheninhalt" + o_flaecheninhalt);
		LOG.debug("o_radiusInkreis" + o_radiusInkreis);
		LOG.debug("o_radiusUmkreis" + o_radiusUmkreis);
		LOG.debug("<======= Setze Finale Werte in OctagonBean Ende =======>");

		LOG.debug("Setting DecimalFormat into Octagon bean...");
		DF df = new DF();
		octagon.setDecimalFormat(df.getDecimalFormat(decimalPlaces));

		LOG.debug("berechneOctagon() method finished.");
	}
}
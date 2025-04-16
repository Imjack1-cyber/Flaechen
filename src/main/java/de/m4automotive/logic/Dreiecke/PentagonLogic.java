package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Pentagon;

public class PentagonLogic {

	private static final Logger LOG = LogManager.getLogger(PentagonLogic.class);

	public void berechnePentagon(HttpServletRequest request, HttpServletResponse response, Pentagon pentagon)
			throws ServletException, IOException {
		LOG.debug("berechnePentagon() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Pentagon) Start =======>");
		double pe_seite = Double.parseDouble(request.getParameter("pe_seite").replace(',', '.'));
		LOG.debug("pe_seite" + pe_seite);
		double pe_umfang = Double.parseDouble(request.getParameter("pe_umfang").replace(',', '.'));
		LOG.debug("pe_umfang" + pe_umfang);
		double pe_radiusInkreis = Double.parseDouble(request.getParameter("pe_radiusInkreis").replace(',', '.'));
		LOG.debug("pe_radiusInkreis" + pe_radiusInkreis);
		double pe_radiusUmkreis = Double.parseDouble(request.getParameter("pe_radiusUmkreis").replace(',', '.'));
		LOG.debug("pe_radiusUmkreis" + pe_radiusUmkreis);
		double pe_flaecheninhalt = Double.parseDouble(request.getParameter("pe_flaecheninhalt").replace(',', '.'));
		LOG.debug("pe_flaecheninhalt" + pe_flaecheninhalt);
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Pentagon) Ende =======>");
		LOG.debug(" ");

		LOG.debug("<======= Berechnung Pentagon Start =======>");

		if (pe_seite == 0) {
			LOG.debug("Versuche Berechnung der pe_seite");
			if (!Double.isNaN(pe_umfang) && (pe_umfang / 5.0) > 0) {
				pe_seite = pe_umfang / 5.0;
				LOG.debug("pe_seite berechnet mit: Umfang / 5");
			} else if (!Double.isNaN(pe_flaecheninhalt) && pe_flaecheninhalt > 0
					&& (Math.sqrt((4.0 * pe_flaecheninhalt) / Math.sqrt(25.0 + 10.0 * Math.sqrt(5.0)))) > 0) {
				pe_seite = Math.sqrt((4.0 * pe_flaecheninhalt) / Math.sqrt(25.0 + 10.0 * Math.sqrt(5.0)));
				LOG.debug("pe_seite berechnet aus Fläche");
			} else if (!Double.isNaN(pe_radiusInkreis) && pe_radiusInkreis > 0
					&& (2.0 * pe_radiusInkreis * Math.tan(Math.PI / 5.0)) > 0) {
				pe_seite = 2.0 * pe_radiusInkreis * Math.tan(Math.PI / 5.0);
				LOG.debug("pe_seite berechnet aus Inkreisradius");
			} else if (!Double.isNaN(pe_radiusUmkreis) && pe_radiusUmkreis > 0
					&& (2.0 * pe_radiusUmkreis * Math.sin(Math.PI / 5.0)) > 0) {
				pe_seite = 2.0 * pe_radiusUmkreis * Math.sin(Math.PI / 5.0);
				LOG.debug("pe_seite berechnet aus Umkreisradius");
			} else {
				pe_seite = Double.NaN;
				LOG.debug("pe_seite konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pe_seite war gegeben.");
		}

		if (pe_umfang == 0) {
			LOG.debug("Versuche Berechnung des pe_umfang");
			if (!Double.isNaN(pe_seite) && (5 * pe_seite) > 0) {
				pe_umfang = 5 * pe_seite;
				LOG.debug("pe_umfang berechnet mit: 5 * Seite");
			} else if (!Double.isNaN(pe_flaecheninhalt) && !Double.isNaN(pe_radiusInkreis) && pe_radiusInkreis > 0
					&& ((2.0 * pe_flaecheninhalt) / pe_radiusInkreis) > 0) {
				pe_umfang = (2.0 * pe_flaecheninhalt) / pe_radiusInkreis;
				LOG.debug("pe_umfang berechnet mit: 2 * Fläche / Inkreisradius");
			} else if (!Double.isNaN(pe_radiusInkreis) && pe_radiusInkreis > 0
					&& (5 * (2.0 * pe_radiusInkreis * Math.tan(Math.PI / 5.0))) > 0) {
				pe_umfang = 5 * (2.0 * pe_radiusInkreis * Math.tan(Math.PI / 5.0));
				LOG.debug("pe_umfang berechnet aus Inkreisradius");
			} else if (!Double.isNaN(pe_radiusUmkreis) && pe_radiusUmkreis > 0
					&& (5 * (2.0 * pe_radiusUmkreis * Math.sin(Math.PI / 5.0))) > 0) {
				pe_umfang = 5 * (2.0 * pe_radiusUmkreis * Math.sin(Math.PI / 5.0));
				LOG.debug("pe_umfang berechnet aus Umkreisradius");
			} else {
				pe_umfang = Double.NaN;
				LOG.debug("pe_umfang konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pe_umfang war gegeben.");
		}

		if (pe_radiusInkreis == 0) {
			LOG.debug("Versuche Berechnung des pe_radiusInkreis");
			double tanPi5 = Math.tan(Math.PI / 5.0);
			if (!Double.isNaN(pe_seite) && tanPi5 != 0 && (pe_seite / (2.0 * tanPi5)) > 0) {
				pe_radiusInkreis = pe_seite / (2.0 * tanPi5);
				LOG.debug("pe_radiusInkreis berechnet mit: Seite / (2 * tan(PI/5))");
			} else if (!Double.isNaN(pe_umfang) && tanPi5 != 0 && ((pe_umfang / 5.0) / (2.0 * tanPi5)) > 0) {
				pe_radiusInkreis = (pe_umfang / 5.0) / (2.0 * tanPi5);
				LOG.debug("pe_radiusInkreis berechnet aus Umfang");
			} else if (!Double.isNaN(pe_flaecheninhalt) && !Double.isNaN(pe_umfang) && pe_umfang > 0
					&& ((2.0 * pe_flaecheninhalt) / pe_umfang) > 0) {
				pe_radiusInkreis = (2.0 * pe_flaecheninhalt) / pe_umfang;
				LOG.debug("pe_radiusInkreis berechnet mit: 2 * Fläche / Umfang");
			} else if (!Double.isNaN(pe_radiusUmkreis) && pe_radiusUmkreis > 0
					&& (pe_radiusUmkreis * Math.cos(Math.PI / 5.0)) > 0) {
				pe_radiusInkreis = pe_radiusUmkreis * Math.cos(Math.PI / 5.0);
				LOG.debug("pe_radiusInkreis berechnet aus Umkreisradius");
			} else {
				pe_radiusInkreis = Double.NaN;
				LOG.debug("pe_radiusInkreis konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pe_radiusInkreis war gegeben.");
		}

		if (pe_radiusUmkreis == 0) {
			LOG.debug("Versuche Berechnung des pe_radiusUmkreis");
			double sinPi5 = Math.sin(Math.PI / 5.0);
			if (!Double.isNaN(pe_seite) && sinPi5 != 0 && (pe_seite / (2.0 * sinPi5)) > 0) {
				pe_radiusUmkreis = pe_seite / (2.0 * sinPi5);
				LOG.debug("pe_radiusUmkreis berechnet mit: Seite / (2 * sin(PI/5))");
			} else if (!Double.isNaN(pe_umfang) && sinPi5 != 0 && ((pe_umfang / 5.0) / (2.0 * sinPi5)) > 0) {
				pe_radiusUmkreis = (pe_umfang / 5.0) / (2.0 * sinPi5);
				LOG.debug("pe_radiusUmkreis berechnet aus Umfang");
			} else if (!Double.isNaN(pe_radiusInkreis) && pe_radiusInkreis > 0 && Math.cos(Math.PI / 5.0) != 0
					&& (pe_radiusInkreis / Math.cos(Math.PI / 5.0)) > 0) {
				pe_radiusUmkreis = pe_radiusInkreis / Math.cos(Math.PI / 5.0);
				LOG.debug("pe_radiusUmkreis berechnet aus Inkreisradius");
			} else {
				pe_radiusUmkreis = Double.NaN;
				LOG.debug("pe_radiusUmkreis konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pe_radiusUmkreis war gegeben.");
		}

		if (pe_flaecheninhalt == 0) {
			LOG.debug("Versuche Berechnung des pe_flaecheninhalt");
			if (!Double.isNaN(pe_seite) && pe_seite >= 0
					&& (Math.pow(pe_seite, 2) / 4.0) * Math.sqrt(25.0 + 10.0 * Math.sqrt(5.0)) > 0) {
				pe_flaecheninhalt = (Math.pow(pe_seite, 2) / 4.0) * Math.sqrt(25.0 + 10.0 * Math.sqrt(5.0));
				LOG.debug("pe_flaecheninhalt berechnet aus Seite");
			} else if (!Double.isNaN(pe_umfang) && !Double.isNaN(pe_radiusInkreis)
					&& (pe_umfang * pe_radiusInkreis / 2.0) > 0) {
				pe_flaecheninhalt = (pe_umfang * pe_radiusInkreis) / 2.0;
				LOG.debug("pe_flaecheninhalt berechnet mit: Umfang * Inkreisradius / 2");
			} else if (!Double.isNaN(pe_radiusUmkreis) && pe_radiusUmkreis >= 0
					&& (5.0 / 2.0) * Math.pow(pe_radiusUmkreis, 2) * Math.sin(2.0 * Math.PI / 5.0) > 0) {
				pe_flaecheninhalt = (5.0 / 2.0) * Math.pow(pe_radiusUmkreis, 2) * Math.sin(2.0 * Math.PI / 5.0);
				LOG.debug("pe_flaecheninhalt berechnet aus Umkreisradius");
			} else {
				pe_flaecheninhalt = Double.NaN;
				LOG.debug("pe_flaecheninhalt konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pe_flaecheninhalt war gegeben.");
		}

		LOG.debug("<======= Berechnung Pentagon Ende =======>");
		LOG.debug("");

		LOG.debug("<======= Setze Finale Werte in PentagonBean Start =======>");
		pentagon.setPe_seite(pe_seite);
		pentagon.setPe_umfang(pe_umfang);
		pentagon.setPe_radiusInkreis(pe_radiusInkreis);
		pentagon.setPe_radiusUmkreis(pe_radiusUmkreis);
		pentagon.setPe_flaecheninhalt(pe_flaecheninhalt);

		LOG.debug("pe_seite" + pe_seite);
		LOG.debug("pe_umfang" + pe_umfang);
		LOG.debug("pe_radiusInkreis" + pe_radiusInkreis);
		LOG.debug("pe_radiusUmkreis" + pe_radiusUmkreis);
		LOG.debug("pe_flaecheninhalt" + pe_flaecheninhalt);
		LOG.debug("<======= Setze Finale Werte in PentagonBean Ende =======>");

		LOG.debug("Setting DecimalFormat into Pentagon bean...");
		DF df = new DF();
		pentagon.setDecimalFormat(df.getDecimalFormat(decimalPlaces));

		LOG.debug("berechnePentagon() method finished.");
	}

}
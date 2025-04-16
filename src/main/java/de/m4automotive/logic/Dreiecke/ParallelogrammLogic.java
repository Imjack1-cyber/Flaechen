package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Dreieck.Parallelogramm;

public class ParallelogrammLogic {

	private static final Logger LOG = LogManager.getLogger(ParallelogrammLogic.class);

	public void berechneParallelogramm(HttpServletRequest request, HttpServletResponse response,
			Parallelogramm parallelogramm) throws ServletException, IOException {
		LOG.debug("berechneParallelogramm() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Parallelogramm) Start =======>");
		double pa_seiteA = Double.parseDouble(request.getParameter("pa_seiteA").replace(',', '.'));
		LOG.debug("pa_seiteA" + pa_seiteA);
		double pa_seiteB = Double.parseDouble(request.getParameter("pa_seiteB").replace(',', '.'));
		LOG.debug("pa_seiteB" + pa_seiteB);
		double pa_hoeheA = Double.parseDouble(request.getParameter("pa_hoeheA").replace(',', '.'));
		LOG.debug("pa_hoeheA" + pa_hoeheA);
		double pa_flaecheninhalt = Double.parseDouble(request.getParameter("pa_flaecheninhalt").replace(',', '.'));
		LOG.debug("pa_flaecheninhalt" + pa_flaecheninhalt);
		double pa_umfang = Double.parseDouble(request.getParameter("pa_umfang").replace(',', '.'));
		LOG.debug("pa_umfang" + pa_umfang);
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Parallelogramm) Ende =======>");
		LOG.debug(" ");

		LOG.debug("<======= Berechnung Parallelogramm Start =======>");

		if (pa_seiteA == 0) {
			LOG.debug("Versuche Berechnung der pa_seiteA");
			if (!Double.isNaN(pa_flaecheninhalt) && !Double.isNaN(pa_hoeheA) && pa_hoeheA > 0
					&& (pa_flaecheninhalt / pa_hoeheA) > 0) {
				pa_seiteA = pa_flaecheninhalt / pa_hoeheA;
				LOG.debug("pa_seiteA berechnet mit: Fläche / Höhe_A");
			} else if (!Double.isNaN(pa_umfang) && !Double.isNaN(pa_seiteB) && (pa_umfang / 2.0 - pa_seiteB) > 0) {
				pa_seiteA = (pa_umfang / 2.0) - pa_seiteB;
				LOG.debug("pa_seiteA berechnet mit: (Umfang / 2) - Seite_B");
			} else {
				pa_seiteA = Double.NaN;
				LOG.debug("pa_seiteA konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pa_seiteA war gegeben.");
		}

		if (pa_seiteB == 0) {
			LOG.debug("Versuche Berechnung der pa_seiteB");
			if (!Double.isNaN(pa_umfang) && !Double.isNaN(pa_seiteA) && (pa_umfang / 2.0 - pa_seiteA) > 0) {
				pa_seiteB = (pa_umfang / 2.0) - pa_seiteA;
				LOG.debug("pa_seiteB berechnet mit: (Umfang / 2) - Seite_A");
			} else {
				pa_seiteB = Double.NaN;
				LOG.debug("pa_seiteB konnte nicht aus Fläche/HöheA berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pa_seiteB war gegeben.");
		}

		if (pa_hoeheA == 0) {
			LOG.debug("Versuche Berechnung der pa_hoeheA");
			if (!Double.isNaN(pa_flaecheninhalt) && !Double.isNaN(pa_seiteA) && pa_seiteA > 0
					&& (pa_flaecheninhalt / pa_seiteA) > 0) {
				pa_hoeheA = pa_flaecheninhalt / pa_seiteA;
				LOG.debug("pa_hoeheA berechnet mit: Fläche / Seite_A");
			} else {
				pa_hoeheA = Double.NaN;
				LOG.debug("pa_hoeheA konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pa_hoeheA war gegeben.");
		}

		if (pa_flaecheninhalt == 0) {
			LOG.debug("Versuche Berechnung des pa_flaecheninhalt");
			if (!Double.isNaN(pa_seiteA) && !Double.isNaN(pa_hoeheA) && (pa_seiteA * pa_hoeheA) > 0) {
				pa_flaecheninhalt = pa_seiteA * pa_hoeheA;
				LOG.debug("pa_flaecheninhalt berechnet mit: Seite_A * Höhe_A");
			} else {
				pa_flaecheninhalt = Double.NaN;
				LOG.debug("pa_flaecheninhalt konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pa_flaecheninhalt war gegeben.");
		}

		if (pa_umfang == 0) {
			LOG.debug("Versuche Berechnung des pa_umfang");
			if (!Double.isNaN(pa_seiteA) && !Double.isNaN(pa_seiteB) && (2 * (pa_seiteA + pa_seiteB)) > 0) {
				pa_umfang = 2 * (pa_seiteA + pa_seiteB);
				LOG.debug("pa_umfang berechnet mit: 2 * (Seite_A + Seite_B)");
			} else if (!Double.isNaN(pa_flaecheninhalt) && !Double.isNaN(pa_hoeheA) && pa_hoeheA > 0
					&& !Double.isNaN(pa_seiteB) && (2 * (pa_flaecheninhalt / pa_hoeheA + pa_seiteB)) > 0) {
				pa_umfang = 2 * (pa_flaecheninhalt / pa_hoeheA + pa_seiteB);
				LOG.debug("pa_umfang berechnet mit: 2 * (Fläche/Höhe_A + Seite_B)");
			} else {
				pa_umfang = Double.NaN;
				LOG.debug("pa_umfang konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pa_umfang war gegeben.");
		}

		LOG.debug("<======= Berechnung Parallelogramm Ende =======>");
		LOG.debug("");

		LOG.debug("<======= Setze Finale Werte in ParallelogrammBean Start =======>");
		parallelogramm.setPa_seiteA(pa_seiteA);
		parallelogramm.setPa_seiteB(pa_seiteB);
		parallelogramm.setPa_hoeheA(pa_hoeheA);
		parallelogramm.setPa_flaecheninhalt(pa_flaecheninhalt);
		parallelogramm.setPa_umfang(pa_umfang);

		LOG.debug("pa_seiteA" + pa_seiteA);
		LOG.debug("pa_seiteB" + pa_seiteB);
		LOG.debug("pa_hoeheA" + pa_hoeheA);
		LOG.debug("pa_flaecheninhalt" + pa_flaecheninhalt);
		LOG.debug("pa_umfang" + pa_umfang);
		LOG.debug("<======= Setze Finale Werte in ParallelogrammBean Ende =======>");

		LOG.debug("Setting DecimalFormat into Parallelogramm bean...");
		DF df = new DF();
		parallelogramm.setDecimalFormat(df.getDecimalFormat(decimalPlaces));

		LOG.debug("berechneParallelogramm() method finished.");
	}
}
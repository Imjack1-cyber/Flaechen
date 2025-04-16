package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Dreieck.Trapez;

public class TrapezLogic {

	private static final Logger LOG = LogManager.getLogger(TrapezLogic.class);

	public void berechneTrapez(HttpServletRequest request, HttpServletResponse response, Trapez trapez)
			throws ServletException, IOException {
		LOG.debug("berechneTrapez() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Trapez) Start =======>");
		double t_seiteA = Double.parseDouble(request.getParameter("t_seiteA").replace(',', '.'));
		LOG.debug("t_seiteA" + t_seiteA);
		double t_seiteB = Double.parseDouble(request.getParameter("t_seiteB").replace(',', '.'));
		LOG.debug("t_seiteB" + t_seiteB);
		double t_seiteC = Double.parseDouble(request.getParameter("t_seiteC").replace(',', '.'));
		LOG.debug("t_seiteC" + t_seiteC);
		double t_seiteD = Double.parseDouble(request.getParameter("t_seiteD").replace(',', '.'));
		LOG.debug("t_seiteD" + t_seiteD);
		double t_hoehe = Double.parseDouble(request.getParameter("t_hoehe").replace(',', '.'));
		LOG.debug("t_hoehe" + t_hoehe);
		double t_flaecheninhalt = Double.parseDouble(request.getParameter("t_flaecheninhalt").replace(',', '.'));
		LOG.debug("t_flaecheninhalt" + t_flaecheninhalt);
		double t_umfang = Double.parseDouble(request.getParameter("t_umfang").replace(',', '.'));
		LOG.debug("t_umfang" + t_umfang);
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Trapez) Ende =======>");
		LOG.debug(" ");

		LOG.debug("<======= Berechnung Trapez Start =======>");

		if (t_seiteA == 0) {
			LOG.debug("Versuche Berechnung der t_seiteA");
			if (!Double.isNaN(t_flaecheninhalt) && !Double.isNaN(t_hoehe) && t_hoehe > 0 && !Double.isNaN(t_seiteC)
					&& (2.0 * t_flaecheninhalt / t_hoehe - t_seiteC) > 0) {
				t_seiteA = (2.0 * t_flaecheninhalt / t_hoehe) - t_seiteC;
				LOG.debug("t_seiteA berechnet mit: (2*Fläche/Höhe) - C");
			} else if (!Double.isNaN(t_umfang) && !Double.isNaN(t_seiteB) && !Double.isNaN(t_seiteC)
					&& !Double.isNaN(t_seiteD) && (t_umfang - t_seiteB - t_seiteC - t_seiteD) > 0) {
				t_seiteA = t_umfang - t_seiteB - t_seiteC - t_seiteD;
				LOG.debug("t_seiteA berechnet mit: Umfang - B - C - D");
			} else {
				t_seiteA = Double.NaN;
				LOG.debug("t_seiteA konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("t_seiteA war gegeben.");
		}

		if (t_seiteB == 0) {
			LOG.debug("Versuche Berechnung der t_seiteB");
			if (!Double.isNaN(t_umfang) && !Double.isNaN(t_seiteA) && !Double.isNaN(t_seiteC) && !Double.isNaN(t_seiteD)
					&& (t_umfang - t_seiteA - t_seiteC - t_seiteD) > 0) {
				t_seiteB = t_umfang - t_seiteA - t_seiteC - t_seiteD;
				LOG.debug("t_seiteB berechnet mit: Umfang - A - C - D");
			} else {
				t_seiteB = Double.NaN;
				LOG.debug("t_seiteB konnte nur aus Umfang berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("t_seiteB war gegeben.");
		}

		if (t_seiteC == 0) {
			LOG.debug("Versuche Berechnung der t_seiteC");
			if (!Double.isNaN(t_flaecheninhalt) && !Double.isNaN(t_hoehe) && t_hoehe > 0 && !Double.isNaN(t_seiteA)
					&& (2.0 * t_flaecheninhalt / t_hoehe - t_seiteA) > 0) {
				t_seiteC = (2.0 * t_flaecheninhalt / t_hoehe) - t_seiteA;
				LOG.debug("t_seiteC berechnet mit: (2*Fläche/Höhe) - A");
			} else if (!Double.isNaN(t_umfang) && !Double.isNaN(t_seiteA) && !Double.isNaN(t_seiteB)
					&& !Double.isNaN(t_seiteD) && (t_umfang - t_seiteA - t_seiteB - t_seiteD) > 0) {
				t_seiteC = t_umfang - t_seiteA - t_seiteB - t_seiteD;
				LOG.debug("t_seiteC berechnet mit: Umfang - A - B - D");
			} else {
				t_seiteC = Double.NaN;
				LOG.debug("t_seiteC konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("t_seiteC war gegeben.");
		}

		if (t_seiteD == 0) {
			LOG.debug("Versuche Berechnung der t_seiteD");
			if (!Double.isNaN(t_umfang) && !Double.isNaN(t_seiteA) && !Double.isNaN(t_seiteB) && !Double.isNaN(t_seiteC)
					&& (t_umfang - t_seiteA - t_seiteB - t_seiteC) > 0) {
				t_seiteD = t_umfang - t_seiteA - t_seiteB - t_seiteC;
				LOG.debug("t_seiteD berechnet mit: Umfang - A - B - C");
			} else {
				t_seiteD = Double.NaN;
				LOG.debug("t_seiteD konnte nur aus Umfang berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("t_seiteD war gegeben.");
		}

		if (t_hoehe == 0) {
			LOG.debug("Versuche Berechnung der t_hoehe");
			if (!Double.isNaN(t_flaecheninhalt) && !Double.isNaN(t_seiteA) && !Double.isNaN(t_seiteC)
					&& (t_seiteA + t_seiteC) != 0 && (2.0 * t_flaecheninhalt) / (t_seiteA + t_seiteC) > 0) {
				t_hoehe = (2.0 * t_flaecheninhalt) / (t_seiteA + t_seiteC);
				LOG.debug("t_hoehe berechnet mit: (2*Fläche) / (A + C)");
			} else {
				t_hoehe = Double.NaN;
				LOG.debug("t_hoehe konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("t_hoehe war gegeben.");
		}

		if (t_flaecheninhalt == 0) {
			LOG.debug("Versuche Berechnung des t_flaecheninhalt");
			if (!Double.isNaN(t_seiteA) && !Double.isNaN(t_seiteC) && !Double.isNaN(t_hoehe)
					&& ((t_seiteA + t_seiteC) / 2.0 * t_hoehe) > 0) {
				t_flaecheninhalt = (t_seiteA + t_seiteC) / 2.0 * t_hoehe;
				LOG.debug("t_flaecheninhalt berechnet mit: (A + C) / 2 * Höhe");
			} else {
				t_flaecheninhalt = Double.NaN;
				LOG.debug("t_flaecheninhalt konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("t_flaecheninhalt war gegeben.");
		}

		if (t_umfang == 0) {
			LOG.debug("Versuche Berechnung des t_umfang");
			if (!Double.isNaN(t_seiteA) && !Double.isNaN(t_seiteB) && !Double.isNaN(t_seiteC) && !Double.isNaN(t_seiteD)
					&& (t_seiteA + t_seiteB + t_seiteC + t_seiteD) > 0) {
				t_umfang = t_seiteA + t_seiteB + t_seiteC + t_seiteD;
				LOG.debug("t_umfang berechnet mit: A + B + C + D");
			} else {
				t_umfang = Double.NaN;
				LOG.debug("t_umfang konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("t_umfang war gegeben.");
		}

		LOG.debug("<======= Berechnung Trapez Ende =======>");
		LOG.debug("");

		LOG.debug("<======= Setze Finale Werte in TrapezBean Start =======>");
		trapez.setT_seiteA(t_seiteA);
		trapez.setT_seiteB(t_seiteB);
		trapez.setT_seiteC(t_seiteC);
		trapez.setT_seiteD(t_seiteD);
		trapez.setT_hoehe(t_hoehe);
		trapez.setT_flaecheninhalt(t_flaecheninhalt);
		trapez.setT_umfang(t_umfang);

		LOG.debug("t_seiteA" + t_seiteA);
		LOG.debug("t_seiteB" + t_seiteB);
		LOG.debug("t_seiteC" + t_seiteC);
		LOG.debug("t_seiteD" + t_seiteD);
		LOG.debug("t_hoehe" + t_hoehe);
		LOG.debug("t_flaecheninhalt" + t_flaecheninhalt);
		LOG.debug("t_umfang" + t_umfang);
		LOG.debug("<======= Setze Finale Werte in TrapezBean Ende =======>");

		LOG.debug("Setting DecimalFormat into Trapez bean...");
		DF df = new DF();
		trapez.setDecimalFormat(df.getDecimalFormat(decimalPlaces));

		LOG.debug("berechneTrapez() method finished.");
	}
}
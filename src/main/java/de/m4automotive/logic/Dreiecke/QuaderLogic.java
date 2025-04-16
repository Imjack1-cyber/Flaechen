package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Quader;

public class QuaderLogic {

	private static final Logger LOG = LogManager.getLogger(QuaderLogic.class);

	public void berechneQuader(HttpServletRequest request, HttpServletResponse response, Quader quader)
			throws ServletException, IOException {
		LOG.debug("berechneQuader() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Quader) Start =======>");
		double q_kanteA = Double.parseDouble(request.getParameter("q_kanteA").replace(',', '.'));
		LOG.debug("q_kanteA" + q_kanteA);
		double q_kanteB = Double.parseDouble(request.getParameter("q_kanteB").replace(',', '.'));
		LOG.debug("q_kanteB" + q_kanteB);
		double q_kanteC = Double.parseDouble(request.getParameter("q_kanteC").replace(',', '.'));
		LOG.debug("q_kanteC" + q_kanteC);
		double q_volumen = Double.parseDouble(request.getParameter("q_volumen").replace(',', '.'));
		LOG.debug("q_volumen" + q_volumen);
		double q_grundflaeche = Double.parseDouble(request.getParameter("q_grundflaeche").replace(',', '.'));
		LOG.debug("q_grundflaeche (AxB)" + q_grundflaeche);
		double q_mantelflaeche = Double.parseDouble(request.getParameter("q_mantelflaeche").replace(',', '.'));
		LOG.debug("q_mantelflaeche (H:C)" + q_mantelflaeche);
		double q_oberflaeche = Double.parseDouble(request.getParameter("q_oberflaeche").replace(',', '.'));
		LOG.debug("q_oberflaeche" + q_oberflaeche);
		double q_raumdiagonale = Double.parseDouble(request.getParameter("q_raumdiagonale").replace(',', '.'));
		LOG.debug("q_raumdiagonale" + q_raumdiagonale);
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Quader) Ende =======>");
		LOG.debug(" ");

		LOG.debug("<======= Berechnung Quader Start =======>");

		if (q_kanteA == 0) {
			LOG.debug("Versuche Berechnung der q_kanteA");
			if (!Double.isNaN(q_volumen) && !Double.isNaN(q_kanteB) && q_kanteB > 0 && !Double.isNaN(q_kanteC)
					&& q_kanteC > 0 && (q_volumen / (q_kanteB * q_kanteC)) > 0) {
				q_kanteA = q_volumen / (q_kanteB * q_kanteC);
				LOG.debug("q_kanteA berechnet mit: Volumen / (B * C)");
			} else if (!Double.isNaN(q_grundflaeche) && !Double.isNaN(q_kanteB) && q_kanteB > 0
					&& (q_grundflaeche / q_kanteB) > 0) {
				q_kanteA = q_grundflaeche / q_kanteB;
				LOG.debug("q_kanteA berechnet mit: Grundfläche / B");
			} else if (!Double.isNaN(q_mantelflaeche) && !Double.isNaN(q_kanteC) && q_kanteC > 0
					&& !Double.isNaN(q_kanteB) && (q_mantelflaeche / (2.0 * q_kanteC)) - q_kanteB > 0) {
				q_kanteA = (q_mantelflaeche / (2.0 * q_kanteC)) - q_kanteB;
				LOG.debug("q_kanteA berechnet mit: (Mantelfläche / (2*C)) - B");
			} else if (!Double.isNaN(q_raumdiagonale) && !Double.isNaN(q_kanteB) && !Double.isNaN(q_kanteC)
					&& Math.pow(q_raumdiagonale, 2) >= Math.pow(q_kanteB, 2) + Math.pow(q_kanteC, 2)
					&& Math.sqrt(Math.pow(q_raumdiagonale, 2) - Math.pow(q_kanteB, 2) - Math.pow(q_kanteC, 2)) > 0) {
				q_kanteA = Math.sqrt(Math.pow(q_raumdiagonale, 2) - Math.pow(q_kanteB, 2) - Math.pow(q_kanteC, 2));
				LOG.debug("q_kanteA berechnet aus Raumdiagonale");
			} else {
				q_kanteA = Double.NaN;
				LOG.debug("q_kanteA konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("q_kanteA war gegeben.");
		}

		if (q_kanteB == 0) {
			LOG.debug("Versuche Berechnung der q_kanteB");
			if (!Double.isNaN(q_volumen) && !Double.isNaN(q_kanteA) && q_kanteA > 0 && !Double.isNaN(q_kanteC)
					&& q_kanteC > 0 && (q_volumen / (q_kanteA * q_kanteC)) > 0) {
				q_kanteB = q_volumen / (q_kanteA * q_kanteC);
				LOG.debug("q_kanteB berechnet mit: Volumen / (A * C)");
			} else if (!Double.isNaN(q_grundflaeche) && !Double.isNaN(q_kanteA) && q_kanteA > 0
					&& (q_grundflaeche / q_kanteA) > 0) {
				q_kanteB = q_grundflaeche / q_kanteA;
				LOG.debug("q_kanteB berechnet mit: Grundfläche / A");
			} else if (!Double.isNaN(q_mantelflaeche) && !Double.isNaN(q_kanteC) && q_kanteC > 0
					&& !Double.isNaN(q_kanteA) && (q_mantelflaeche / (2.0 * q_kanteC)) - q_kanteA > 0) {
				q_kanteB = (q_mantelflaeche / (2.0 * q_kanteC)) - q_kanteA;
				LOG.debug("q_kanteB berechnet mit: (Mantelfläche / (2*C)) - A");
			} else if (!Double.isNaN(q_raumdiagonale) && !Double.isNaN(q_kanteA) && !Double.isNaN(q_kanteC)
					&& Math.pow(q_raumdiagonale, 2) >= Math.pow(q_kanteA, 2) + Math.pow(q_kanteC, 2)
					&& Math.sqrt(Math.pow(q_raumdiagonale, 2) - Math.pow(q_kanteA, 2) - Math.pow(q_kanteC, 2)) > 0) {
				q_kanteB = Math.sqrt(Math.pow(q_raumdiagonale, 2) - Math.pow(q_kanteA, 2) - Math.pow(q_kanteC, 2));
				LOG.debug("q_kanteB berechnet aus Raumdiagonale");
			} else {
				q_kanteB = Double.NaN;
				LOG.debug("q_kanteB konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("q_kanteB war gegeben.");
		}

		if (q_kanteC == 0) {
			LOG.debug("Versuche Berechnung der q_kanteC");
			if (!Double.isNaN(q_volumen) && !Double.isNaN(q_kanteA) && q_kanteA > 0 && !Double.isNaN(q_kanteB)
					&& q_kanteB > 0 && (q_volumen / (q_kanteA * q_kanteB)) > 0) {
				q_kanteC = q_volumen / (q_kanteA * q_kanteB);
				LOG.debug("q_kanteC berechnet mit: Volumen / (A * B)");
			} else if (!Double.isNaN(q_volumen) && !Double.isNaN(q_grundflaeche) && q_grundflaeche > 0
					&& (q_volumen / q_grundflaeche) > 0) {
				q_kanteC = q_volumen / q_grundflaeche;
				LOG.debug("q_kanteC berechnet mit: Volumen / Grundfläche");
			} else if (!Double.isNaN(q_mantelflaeche) && !Double.isNaN(q_kanteA) && !Double.isNaN(q_kanteB)
					&& 2.0 * (q_kanteA + q_kanteB) > 0 && (q_mantelflaeche / (2.0 * (q_kanteA + q_kanteB))) > 0) {
				q_kanteC = q_mantelflaeche / (2.0 * (q_kanteA + q_kanteB));
				LOG.debug("q_kanteC berechnet mit: Mantelfläche / (2*(A+B))");
			} else if (!Double.isNaN(q_raumdiagonale) && !Double.isNaN(q_kanteA) && !Double.isNaN(q_kanteB)
					&& Math.pow(q_raumdiagonale, 2) >= Math.pow(q_kanteA, 2) + Math.pow(q_kanteB, 2)
					&& Math.sqrt(Math.pow(q_raumdiagonale, 2) - Math.pow(q_kanteA, 2) - Math.pow(q_kanteB, 2)) > 0) {
				q_kanteC = Math.sqrt(Math.pow(q_raumdiagonale, 2) - Math.pow(q_kanteA, 2) - Math.pow(q_kanteB, 2));
				LOG.debug("q_kanteC berechnet aus Raumdiagonale");
			} else {
				q_kanteC = Double.NaN;
				LOG.debug("q_kanteC konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("q_kanteC war gegeben.");
		}

		if (q_volumen == 0) {
			LOG.debug("Versuche Berechnung des q_volumen");
			if (!Double.isNaN(q_kanteA) && !Double.isNaN(q_kanteB) && !Double.isNaN(q_kanteC)
					&& (q_kanteA * q_kanteB * q_kanteC) != 0) {
				q_volumen = q_kanteA * q_kanteB * q_kanteC;
				LOG.debug("q_volumen berechnet mit: A * B * C");
			} else if (!Double.isNaN(q_grundflaeche) && !Double.isNaN(q_kanteC) && (q_grundflaeche * q_kanteC) != 0) {
				q_volumen = q_grundflaeche * q_kanteC;
				LOG.debug("q_volumen berechnet mit: Grundfläche * C");
			} else {
				q_volumen = Double.NaN;
				LOG.debug("q_volumen konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("q_volumen war gegeben.");
		}

		if (q_grundflaeche == 0) {
			LOG.debug("Versuche Berechnung der q_grundflaeche (AxB)");
			if (!Double.isNaN(q_kanteA) && !Double.isNaN(q_kanteB) && (q_kanteA * q_kanteB) != 0) {
				q_grundflaeche = q_kanteA * q_kanteB;
				LOG.debug("q_grundflaeche berechnet mit: A * B");
			} else if (!Double.isNaN(q_volumen) && !Double.isNaN(q_kanteC) && q_kanteC != 0
					&& (q_volumen / q_kanteC) != 0) {
				q_grundflaeche = q_volumen / q_kanteC;
				LOG.debug("q_grundflaeche berechnet mit: Volumen / C");
			} else if (!Double.isNaN(q_oberflaeche) && !Double.isNaN(q_mantelflaeche)
					&& q_oberflaeche >= q_mantelflaeche && ((q_oberflaeche - q_mantelflaeche) / 2.0) != 0) {
				q_grundflaeche = (q_oberflaeche - q_mantelflaeche) / 2.0; // Erlaube 0
				LOG.debug("q_grundflaeche berechnet mit: (Oberfläche - Mantelfläche) / 2");
			} else {
				q_grundflaeche = Double.NaN;
				LOG.debug("q_grundflaeche konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("q_grundflaeche war gegeben.");
		}

		if (q_mantelflaeche == 0) {
			LOG.debug("Versuche Berechnung der q_mantelflaeche (H:C)");
			if (!Double.isNaN(q_kanteA) && !Double.isNaN(q_kanteB) && !Double.isNaN(q_kanteC)
					&& (2 * (q_kanteA * q_kanteC + q_kanteB * q_kanteC)) != 0) {
				q_mantelflaeche = 2 * (q_kanteA * q_kanteC + q_kanteB * q_kanteC); // Erlaube 0
				LOG.debug("q_mantelflaeche berechnet mit: 2*(A*C + B*C)");
			} else if (!Double.isNaN(q_oberflaeche) && !Double.isNaN(q_grundflaeche)
					&& q_oberflaeche >= 2 * q_grundflaeche && (q_oberflaeche - 2 * q_grundflaeche) != 0) {
				q_mantelflaeche = q_oberflaeche - 2 * q_grundflaeche; // Erlaube 0
				LOG.debug("q_mantelflaeche berechnet mit: Oberfläche - 2*Grundfläche");
			} else {
				q_mantelflaeche = Double.NaN;
				LOG.debug("q_mantelflaeche konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("q_mantelflaeche war gegeben.");
		}

		if (q_oberflaeche == 0) {
			LOG.debug("Versuche Berechnung der q_oberflaeche");
			if (!Double.isNaN(q_kanteA) && !Double.isNaN(q_kanteB) && !Double.isNaN(q_kanteC)
					&& (2 * (q_kanteA * q_kanteB + q_kanteA * q_kanteC + q_kanteB * q_kanteC)) != 0) {
				q_oberflaeche = 2 * (q_kanteA * q_kanteB + q_kanteA * q_kanteC + q_kanteB * q_kanteC); // Erlaube 0
				LOG.debug("q_oberflaeche berechnet mit: 2*(A*B + A*C + B*C)");
			} else if (!Double.isNaN(q_grundflaeche) && !Double.isNaN(q_mantelflaeche)
					&& (2 * q_grundflaeche + q_mantelflaeche) != 0) {
				q_oberflaeche = 2 * q_grundflaeche + q_mantelflaeche; // Kann 0 sein
				LOG.debug("q_oberflaeche berechnet mit: 2*Grundfläche + Mantelfläche");
			} else {
				q_oberflaeche = Double.NaN;
				LOG.debug("q_oberflaeche konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("q_oberflaeche war gegeben.");
		}

		if (q_raumdiagonale == 0) {
			LOG.debug("Versuche Berechnung der q_raumdiagonale");
			if (!Double.isNaN(q_kanteA) && !Double.isNaN(q_kanteB) && !Double.isNaN(q_kanteC)
					&& Math.sqrt(Math.pow(q_kanteA, 2) + Math.pow(q_kanteB, 2) + Math.pow(q_kanteC, 2)) > 0) {
				q_raumdiagonale = Math.sqrt(Math.pow(q_kanteA, 2) + Math.pow(q_kanteB, 2) + Math.pow(q_kanteC, 2));
				LOG.debug("q_raumdiagonale berechnet mit: sqrt(A^2 + B^2 + C^2)");
			} else {
				q_raumdiagonale = Double.NaN;
				LOG.debug("q_raumdiagonale konnte nicht berechnet werden (benötigt alle Kanten), bleibt NaN.");
			}
		} else {
			LOG.debug("q_raumdiagonale war gegeben.");
		}

		LOG.debug("<======= Berechnung Quader Ende =======>");
		LOG.debug("");

		LOG.debug("<======= Setze Finale Werte in QuaderBean Start =======>");
		quader.setQ_kanteA(q_kanteA);
		quader.setQ_kanteB(q_kanteB);
		quader.setQ_kanteC(q_kanteC);
		quader.setQ_volumen(q_volumen);
		quader.setQ_grundflaeche(q_grundflaeche);
		quader.setQ_mantelflaeche(q_mantelflaeche);
		quader.setQ_oberflaeche(q_oberflaeche);
		quader.setQ_raumdiagonale(q_raumdiagonale);

		LOG.debug("q_kanteA" + q_kanteA);
		LOG.debug("q_kanteB" + q_kanteB);
		LOG.debug("q_kanteC" + q_kanteC);
		LOG.debug("q_volumen" + q_volumen);
		LOG.debug("q_grundflaeche (AxB)" + q_grundflaeche);
		LOG.debug("q_mantelflaeche (H:C)" + q_mantelflaeche);
		LOG.debug("q_oberflaeche" + q_oberflaeche);
		LOG.debug("q_raumdiagonale" + q_raumdiagonale);
		LOG.debug("<======= Setze Finale Werte in QuaderBean Ende =======>");

		LOG.debug("Setting DecimalFormat into Quader bean...");
		DF df = new DF();
		quader.setDecimalFormat(df.getDecimalFormat(decimalPlaces));

		LOG.debug("berechneQuader() method finished.");
	}
}
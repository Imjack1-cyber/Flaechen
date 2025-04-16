package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Wuerfel;

public class WuerfelLogic {

	private static final Logger LOG = LogManager.getLogger(WuerfelLogic.class);

	public void berechneWuerfel(HttpServletRequest request, HttpServletResponse response, Wuerfel wuerfel)
			throws ServletException, IOException {
		LOG.debug("berechneWuerfel() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Würfel) Start =======>");
		double w_kante = Double.parseDouble(request.getParameter("w_kante").replace(',', '.'));
		LOG.debug("w_kante" + w_kante);
		double w_volumen = Double.parseDouble(request.getParameter("w_volumen").replace(',', '.'));
		LOG.debug("w_volumen" + w_volumen);
		double w_oberflaeche = Double.parseDouble(request.getParameter("w_oberflaeche").replace(',', '.'));
		LOG.debug("w_oberflaeche" + w_oberflaeche);
		double w_raumdiagonale = Double.parseDouble(request.getParameter("w_raumdiagonale").replace(',', '.'));
		LOG.debug("w_raumdiagonale" + w_raumdiagonale);
		double w_grundflaeche = Double.parseDouble(request.getParameter("w_grundflaeche").replace(',', '.'));
		LOG.debug("w_grundflaeche" + w_grundflaeche);
		double w_mantelflaeche = Double.parseDouble(request.getParameter("w_mantelflaeche").replace(',', '.'));
		LOG.debug("w_mantelflaeche" + w_mantelflaeche);
		double w_umfang_seitenflaeche = Double
				.parseDouble(request.getParameter("w_umfang_seitenflaeche").replace(',', '.'));
		LOG.debug("w_umfang_seitenflaeche" + w_umfang_seitenflaeche);
		double w_summe_aller_kanten = Double
				.parseDouble(request.getParameter("w_summe_aller_kanten").replace(',', '.'));
		LOG.debug("w_summe_aller_kanten" + w_summe_aller_kanten);
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Würfel) Ende =======>");
		LOG.debug(" ");

		LOG.debug("<======= Berechnung Würfel Start =======>");
		final double SQRT3 = Math.sqrt(3.0);
		final double epsilon = 1e-9;

		if (w_kante == 0) {
			LOG.debug("Versuche Berechnung der w_kante");
			if (!Double.isNaN(w_volumen) && w_volumen >= 0 && Math.cbrt(w_volumen) > 0) {
				w_kante = Math.cbrt(w_volumen);
				LOG.debug("w_kante berechnet mit: cbrt(Volumen)");
			} else if (!Double.isNaN(w_oberflaeche) && w_oberflaeche >= 0 && Math.sqrt(w_oberflaeche / 6.0) > 0) {
				w_kante = Math.sqrt(w_oberflaeche / 6.0);
				LOG.debug("w_kante berechnet mit: sqrt(Oberfläche/6)");
			} else if (!Double.isNaN(w_raumdiagonale) && (w_raumdiagonale / SQRT3) > 0) {
				w_kante = w_raumdiagonale / SQRT3;
				LOG.debug("w_kante berechnet mit: Raumdiagonale/sqrt(3)");
			} else if (!Double.isNaN(w_grundflaeche) && w_grundflaeche >= 0 && Math.sqrt(w_grundflaeche) > 0) {
				w_kante = Math.sqrt(w_grundflaeche);
				LOG.debug("w_kante berechnet mit: sqrt(Grundfläche)");
			} else if (!Double.isNaN(w_mantelflaeche) && w_mantelflaeche >= 0 && Math.sqrt(w_mantelflaeche / 4.0) > 0) {
				w_kante = Math.sqrt(w_mantelflaeche / 4.0);
				LOG.debug("w_kante berechnet mit: sqrt(Mantelfläche/4)");
			} else if (!Double.isNaN(w_umfang_seitenflaeche) && (w_umfang_seitenflaeche / 4.0) > 0) {
				w_kante = w_umfang_seitenflaeche / 4.0;
				LOG.debug("w_kante berechnet mit: UmfangSeitenfläche/4");
			} else if (!Double.isNaN(w_summe_aller_kanten) && (w_summe_aller_kanten / 12.0) > 0) {
				w_kante = w_summe_aller_kanten / 12.0;
				LOG.debug("w_kante berechnet mit: SummeKanten/12");
			} else {
				w_kante = Double.NaN;
				LOG.debug("w_kante konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("w_kante war gegeben.");
		}

		if (w_volumen == 0) {
			LOG.debug("Versuche Berechnung des w_volumen");
			if (!Double.isNaN(w_kante) && Math.pow(w_kante, 3) != 0) {
				w_volumen = Math.pow(w_kante, 3);
				LOG.debug("w_volumen berechnet mit: Kante^3");
			} else if (!Double.isNaN(w_oberflaeche) && w_oberflaeche >= 0
					&& !Double.isNaN(Math.sqrt(w_oberflaeche / 6.0))
					&& Math.pow(Math.sqrt(w_oberflaeche / 6.0), 3) != 0) {
				w_volumen = Math.pow(Math.sqrt(w_oberflaeche / 6.0), 3);
				LOG.debug("w_volumen berechnet aus Oberfläche");
			} else if (!Double.isNaN(w_raumdiagonale) && w_raumdiagonale > 0 && !Double.isNaN(w_raumdiagonale / SQRT3)
					&& Math.pow(w_raumdiagonale / SQRT3, 3) != 0) {
				w_volumen = Math.pow(w_raumdiagonale / SQRT3, 3);
				LOG.debug("w_volumen berechnet aus Raumdiagonale");
			} else if (!Double.isNaN(w_grundflaeche) && w_grundflaeche >= 0) {
				if (!Double.isNaN(Math.sqrt(w_grundflaeche)) && Math.pow(Math.sqrt(w_grundflaeche), 3) != 0) {
					w_volumen = Math.pow(Math.sqrt(w_grundflaeche), 3);
					LOG.debug("w_volumen berechnet aus Grundfläche");
				} else if (!Double.isNaN(w_mantelflaeche) && w_mantelflaeche >= 0
						&& !Double.isNaN(Math.sqrt(w_mantelflaeche / 4.0))
						&& Math.pow(Math.sqrt(w_mantelflaeche / 4.0), 3) != 0) {
					w_volumen = Math.pow(Math.sqrt(w_mantelflaeche / 4.0), 3);
					LOG.debug("w_volumen berechnet aus Mantelfläche");
				} else {
					w_volumen = Double.NaN;
					LOG.debug("w_volumen konnte nicht berechnet werden, bleibt NaN.");
				}
			} else {
				LOG.debug("w_volumen war gegeben.");
			}

			if (w_grundflaeche == 0) {
				LOG.debug("Versuche Berechnung der w_grundflaeche");
				if (!Double.isNaN(w_kante) && Math.pow(w_kante, 2) != 0) {
					w_grundflaeche = Math.pow(w_kante, 2);
					LOG.debug("w_grundflaeche berechnet mit: Kante^2");
				} else if (!Double.isNaN(w_volumen) && w_volumen >= 0 && !Double.isNaN(Math.cbrt(w_volumen))
						&& Math.pow(Math.cbrt(w_volumen), 2) != 0) {
					w_grundflaeche = Math.pow(Math.cbrt(w_volumen), 2);
					LOG.debug("w_grundflaeche berechnet aus Volumen");
				} else if (!Double.isNaN(w_oberflaeche) && (w_oberflaeche / 6.0) > epsilon) {
					w_grundflaeche = w_oberflaeche / 6.0;
					LOG.debug("w_grundflaeche berechnet mit: Oberfläche/6");
				} else if (!Double.isNaN(w_raumdiagonale) && w_raumdiagonale > 0
						&& !Double.isNaN(w_raumdiagonale / SQRT3) && Math.pow(w_raumdiagonale / SQRT3, 2) != 0) {
					w_grundflaeche = Math.pow(w_raumdiagonale / SQRT3, 2);
					LOG.debug("w_grundflaeche berechnet aus Raumdiagonale");
				} else if (!Double.isNaN(w_mantelflaeche) && (w_mantelflaeche / 4.0) > epsilon) {
					w_grundflaeche = w_mantelflaeche / 4.0;
					LOG.debug("w_grundflaeche berechnet mit: Mantelfläche/4");
				} else {
					w_grundflaeche = Double.NaN;
					LOG.debug("w_grundflaeche konnte nicht berechnet werden, bleibt NaN.");
				}
			} else {
				LOG.debug("w_grundflaeche war gegeben.");
			}

			if (w_mantelflaeche == 0) {
				LOG.debug("Versuche Berechnung der w_mantelflaeche");
				if (!Double.isNaN(w_kante) && (4 * Math.pow(w_kante, 2)) != 0) {
					w_mantelflaeche = 4 * Math.pow(w_kante, 2);
					LOG.debug("w_mantelflaeche berechnet mit: 4 * Kante^2");
				} else if (!Double.isNaN(w_volumen) && w_volumen >= 0 && !Double.isNaN(Math.cbrt(w_volumen))
						&& (4 * Math.pow(Math.cbrt(w_volumen), 2)) != 0) {
					w_mantelflaeche = 4 * Math.pow(Math.cbrt(w_volumen), 2);
					LOG.debug("w_mantelflaeche berechnet aus Volumen");
				} else if (!Double.isNaN(w_oberflaeche) && (4 * (w_oberflaeche / 6.0)) > epsilon) {
					w_mantelflaeche = 4 * (w_oberflaeche / 6.0);
					LOG.debug("w_mantelflaeche berechnet mit: 4 * (Oberfläche/6)");
				} else if (!Double.isNaN(w_raumdiagonale) && w_raumdiagonale > 0
						&& !Double.isNaN(w_raumdiagonale / SQRT3) && (4 * Math.pow(w_raumdiagonale / SQRT3, 2)) != 0) {
					w_mantelflaeche = 4 * Math.pow(w_raumdiagonale / SQRT3, 2);
					LOG.debug("w_mantelflaeche berechnet aus Raumdiagonale");
				} else if (!Double.isNaN(w_grundflaeche) && (4 * w_grundflaeche) > epsilon) {
					w_mantelflaeche = 4 * w_grundflaeche;
					LOG.debug("w_mantelflaeche berechnet mit: 4 * Grundfläche");
				} else {
					w_mantelflaeche = Double.NaN;
					LOG.debug("w_mantelflaeche konnte nicht berechnet werden, bleibt NaN.");
				}
			} else {
				LOG.debug("w_mantelflaeche war gegeben.");
			}

			if (w_oberflaeche == 0) {
				LOG.debug("Versuche Berechnung der w_oberflaeche");
				if (!Double.isNaN(w_kante) && (6 * Math.pow(w_kante, 2)) != 0) {
					w_oberflaeche = 6 * Math.pow(w_kante, 2);
					LOG.debug("w_oberflaeche berechnet mit: 6 * Kante^2");
				} else if (!Double.isNaN(w_volumen) && w_volumen >= 0 && !Double.isNaN(Math.cbrt(w_volumen))
						&& (6 * Math.pow(Math.cbrt(w_volumen), 2)) != 0) {
					w_oberflaeche = 6 * Math.pow(Math.cbrt(w_volumen), 2);
					LOG.debug("w_oberflaeche berechnet aus Volumen");
				} else if (!Double.isNaN(w_raumdiagonale) && w_raumdiagonale > 0
						&& !Double.isNaN(w_raumdiagonale / SQRT3) && (6 * Math.pow(w_raumdiagonale / SQRT3, 2)) != 0) {
					w_oberflaeche = 6 * Math.pow(w_raumdiagonale / SQRT3, 2);
					LOG.debug("w_oberflaeche berechnet aus Raumdiagonale");
				} else if (!Double.isNaN(w_grundflaeche) && (6 * w_grundflaeche) > epsilon) {
					w_oberflaeche = 6 * w_grundflaeche;
					LOG.debug("w_oberflaeche berechnet mit: 6 * Grundfläche");
				} else if (!Double.isNaN(w_mantelflaeche) && (6 * (w_mantelflaeche / 4.0)) > epsilon) {
					w_oberflaeche = 6 * (w_mantelflaeche / 4.0);
					LOG.debug("w_oberflaeche berechnet mit: 6 * (Mantelfläche/4)");
				} else {
					w_oberflaeche = Double.NaN;
					LOG.debug("w_oberflaeche konnte nicht berechnet werden, bleibt NaN.");
				}
			} else {
				LOG.debug("w_oberflaeche war gegeben.");
			}

			if (w_raumdiagonale == 0) {
				LOG.debug("Versuche Berechnung der w_raumdiagonale");
				if (!Double.isNaN(w_kante) && (w_kante * SQRT3) > 0) {
					w_raumdiagonale = w_kante * SQRT3;
					LOG.debug("w_raumdiagonale berechnet mit: Kante * sqrt(3)");
				} else if (!Double.isNaN(w_volumen) && w_volumen >= 0 && !Double.isNaN(Math.cbrt(w_volumen))
						&& (Math.cbrt(w_volumen) * SQRT3) > 0) {
					w_raumdiagonale = Math.cbrt(w_volumen) * SQRT3;
					LOG.debug("w_raumdiagonale berechnet aus Volumen");
				} else if (!Double.isNaN(w_oberflaeche) && w_oberflaeche >= 0
						&& !Double.isNaN(Math.sqrt(w_oberflaeche / 6.0))
						&& (Math.sqrt(w_oberflaeche / 6.0) * SQRT3) > 0) {
					w_raumdiagonale = Math.sqrt(w_oberflaeche / 6.0) * SQRT3;
					LOG.debug("w_raumdiagonale berechnet aus Oberfläche");
				} else if (!Double.isNaN(w_grundflaeche) && w_grundflaeche >= 0
						&& !Double.isNaN(Math.sqrt(w_grundflaeche)) && (Math.sqrt(w_grundflaeche) * SQRT3) > 0) {
					w_raumdiagonale = Math.sqrt(w_grundflaeche) * SQRT3;
					LOG.debug("w_raumdiagonale berechnet aus Grundfläche");
				} else if (!Double.isNaN(w_mantelflaeche) && w_mantelflaeche >= 0
						&& !Double.isNaN(Math.sqrt(w_mantelflaeche / 4.0))
						&& (Math.sqrt(w_mantelflaeche / 4.0) * SQRT3) > 0) {
					w_raumdiagonale = Math.sqrt(w_mantelflaeche / 4.0) * SQRT3;
					LOG.debug("w_raumdiagonale berechnet aus Mantelfläche");
				} else {
					w_raumdiagonale = Double.NaN;
					LOG.debug("w_raumdiagonale konnte nicht berechnet werden, bleibt NaN.");
				}
			} else {
				LOG.debug("w_raumdiagonale war gegeben.");
			}

			if (w_umfang_seitenflaeche == 0) {
				LOG.debug("Versuche Berechnung des w_umfang_seitenflaeche");
				if (!Double.isNaN(w_kante) && (4 * w_kante) > 0) {
					w_umfang_seitenflaeche = 4 * w_kante;
					LOG.debug("w_umfang_seitenflaeche berechnet mit: 4 * Kante");
				} else {
					w_umfang_seitenflaeche = Double.NaN;
					LOG.debug("w_umfang_seitenflaeche konnte nicht berechnet werden (benötigt Kante), bleibt NaN.");
				}
			} else {
				LOG.debug("w_umfang_seitenflaeche war gegeben.");
			}

			if (w_summe_aller_kanten == 0) {
				LOG.debug("Versuche Berechnung der w_summe_aller_kanten");
				if (!Double.isNaN(w_kante) && (12 * w_kante) > 0) {
					w_summe_aller_kanten = 12 * w_kante;
					LOG.debug("w_summe_aller_kanten berechnet mit: 12 * Kante");
				} else {
					w_summe_aller_kanten = Double.NaN;
					LOG.debug("w_summe_aller_kanten konnte nicht berechnet werden (benötigt Kante), bleibt NaN.");
				}
			} else {
				LOG.debug("w_summe_aller_kanten war gegeben.");
			}

			LOG.debug("<======= Berechnung Würfel Ende =======>");
			LOG.debug("");

			LOG.debug("<======= Setze Finale Werte in WuerfelBean Start =======>");
			wuerfel.setW_kante(w_kante);
			wuerfel.setW_volumen(w_volumen);
			wuerfel.setW_grundflaeche(w_grundflaeche);
			wuerfel.setW_mantelflaeche(w_mantelflaeche);
			wuerfel.setW_oberflaeche(w_oberflaeche);
			wuerfel.setW_raumdiagonale(w_raumdiagonale);
			wuerfel.setW_umfang_seitenflaeche(w_umfang_seitenflaeche);
			wuerfel.setW_summe_aller_kanten(w_summe_aller_kanten);

			LOG.debug("w_kante" + w_kante);
			LOG.debug("w_volumen" + w_volumen);
			LOG.debug("w_grundflaeche" + w_grundflaeche);
			LOG.debug("w_mantelflaeche" + w_mantelflaeche);
			LOG.debug("w_oberflaeche" + w_oberflaeche);
			LOG.debug("w_raumdiagonale" + w_raumdiagonale);
			LOG.debug("w_umfang_seitenflaeche" + w_umfang_seitenflaeche);
			LOG.debug("w_summe_aller_kanten" + w_summe_aller_kanten);
			LOG.debug("<======= Setze Finale Werte in WuerfelBean Ende =======>");

			LOG.debug("Setting DecimalFormat into Wuerfel bean...");
			DF df = new DF();
			wuerfel.setDecimalFormat(df.getDecimalFormat(decimalPlaces));

			LOG.debug("berechneWuerfel() method finished.");
		}
	}

}
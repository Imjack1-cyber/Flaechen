package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Dreieck.Pyramide;

public class PyramideLogic {

	private static final Logger LOG = LogManager.getLogger(DreieckLogic.class);

	public void berechnePyramide(HttpServletRequest request, HttpServletResponse response, Pyramide pyramide)
			throws ServletException, IOException {
		LOG.debug("berechnePyramide() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		double p_seiteA = Double.parseDouble(request.getParameter("p_seiteA"));
		double p_hoehe = Double.parseDouble(request.getParameter("p_hoehe"));
		double p_hoeheSeite = Double.parseDouble(request.getParameter("p_hoeheSeite"));
		double p_seitenkante = Double.parseDouble(request.getParameter("p_seitenkante"));
		double p_grundflaecheDiagonale = Double.parseDouble(request.getParameter("p_grundflaecheDiagonale"));
		double p_grundflaeche = Double.parseDouble(request.getParameter("p_grundflaeche"));
		double p_mantelflaeche = Double.parseDouble(request.getParameter("p_mantelflaeche"));
		double p_oberflaeche = Double.parseDouble(request.getParameter("p_oberflaeche"));
		double p_volumen = Double.parseDouble(request.getParameter("p_volumen"));

		LOG.debug("<======= Gegebene Werte Start =======>");
		LOG.debug("p_seiteA: " + p_seiteA);
		LOG.debug("p_hoehe: " + p_hoehe);
		LOG.debug("p_hoeheSeite: " + p_hoeheSeite);
		LOG.debug("v: " + p_seitenkante);
		LOG.debug("p_grundflaecheDiagonale: " + p_grundflaecheDiagonale);
		LOG.debug("p_grundflaeche: " + p_grundflaeche);
		LOG.debug("p_mantelflaeche: " + p_mantelflaeche);
		LOG.debug("p_oberflaeche: " + p_oberflaeche);
		LOG.debug("p_volumen: " + p_volumen);
		LOG.debug("<======= Gegebene Werte Ende =======>");

		LOG.debug(" ");

		LOG.debug("<======= Berechnung Start =======>");

		if (p_seiteA == 0) {
			LOG.debug("Berechnung der seiteA");
			if (!Double.isNaN(p_grundflaeche) && Math.sqrt(p_grundflaeche) != 0) {
				p_seiteA = Math.sqrt(p_grundflaeche);
				LOG.debug("Calculated seiteA with Math.sqrt(grundflaechePyramide)");
			} else if (!Double.isNaN(p_grundflaecheDiagonale) && p_grundflaecheDiagonale / Math.sqrt(2.0) != 0) {
				p_seiteA = p_grundflaecheDiagonale / Math.sqrt(2.0);
				LOG.debug("Calculated seiteA with grundflaecheDiagonale / Math.sqrt(2.0)");
			} else if (!Double.isNaN(p_volumen) && !Double.isNaN(p_hoehe)
					&& Math.sqrt(3.0 * p_volumen / p_hoehe) != 0) {
				p_seiteA = Math.sqrt(3.0 * p_volumen / p_hoehe);
				LOG.debug("Calculated seiteA with Math.sqrt(3.0 * volumenPyramide / hoehe)");
			} else if (!Double.isNaN(p_mantelflaeche) && !Double.isNaN(p_hoeheSeite)
					&& p_mantelflaeche / (2.0 * p_hoeheSeite) != 0) {
				p_seiteA = p_mantelflaeche / (2.0 * p_hoeheSeite);
				LOG.debug("Calculated seiteA with mantelflaechePyramide / (2.0 * hoeheSeite)");
			} else if (!Double.isNaN(p_hoeheSeite) && !Double.isNaN(p_hoehe) && p_hoeheSeite > p_hoehe
					&& 2.0 * Math.sqrt(Math.pow(p_hoeheSeite, 2.0) - Math.pow(p_hoehe, 2.0)) != 0) {
				p_seiteA = 2.0 * Math.sqrt(Math.pow(p_hoeheSeite, 2.0) - Math.pow(p_hoehe, 2.0));
				LOG.debug("Calculated seiteA with 2 * Math.sqrt(Math.pow(hoeheSeite, 2.0) - Math.pow(hoehe, 2.0))");
			} else if (!Double.isNaN(p_seitenkante) && !Double.isNaN(p_hoehe) && p_seitenkante > p_hoehe
					&& Math.sqrt(2.0 * (Math.pow(p_seitenkante, 2.0) - Math.pow(p_hoehe, 2.0))) != 0) {
				p_seiteA = Math.sqrt(2.0 * (Math.pow(p_seitenkante, 2.0) - Math.pow(p_hoehe, 2.0)));
				LOG.debug(
						"Calculated seiteA with Math.sqrt(2.0 * (Math.pow(seitenkante, 2.0) - Math.pow(hoehe, 2.0)))");
			} else if (!Double.isNaN(p_seitenkante) && !Double.isNaN(p_hoeheSeite) && p_seitenkante > p_hoeheSeite
					&& 2.0 * Math.sqrt(Math.pow(p_seitenkante, 2.0) - Math.pow(p_hoeheSeite, 2.0)) != 0) {
				p_seiteA = 2.0 * Math.sqrt(Math.pow(p_seitenkante, 2.0) - Math.pow(p_hoeheSeite, 2.0));
				LOG.debug(
						"Calculated seiteA with 2.0 * Math.sqrt(Math.pow(seitenkante, 2.0) - Math.pow(hoeheSeite, 2.0))");
			} else {
				p_seiteA = Double.NaN;
				LOG.debug("Calculation for seiteA was not possible, set to NaN");
			}
		}

		if (p_hoehe == 0) {
			LOG.debug("Berechnung der hoehe");
			if (!Double.isNaN(p_volumen) && !Double.isNaN(p_seiteA)
					&& (3.0 * p_volumen) / Math.pow(p_seiteA, 2.0) != 0) {
				p_hoehe = (3.0 * p_volumen) / Math.pow(p_seiteA, 2.0);
				LOG.debug("Calculated hoehe with (3.0 * volumenPyramide) / Math.pow(seiteA, 2.0)");
			} else if (!Double.isNaN(p_volumen) && !Double.isNaN(p_grundflaeche)
					&& (3.0 * p_volumen) / p_grundflaeche != 0) {
				p_hoehe = (3.0 * p_volumen) / p_grundflaeche;
				LOG.debug("Calculated hoehe with (3.0 * volumenPyramide) / grundflaechePyramide");
			} else if (!Double.isNaN(p_hoeheSeite) && !Double.isNaN(p_seiteA) && p_hoeheSeite > p_seiteA / 2.0
					&& Math.sqrt(Math.pow(p_hoeheSeite, 2.0) - Math.pow(p_seiteA / 2.0, 2.0)) != 0) {
				p_hoehe = Math.sqrt(Math.pow(p_hoeheSeite, 2.0) - Math.pow(p_seiteA / 2.0, 2.0));
				LOG.debug("Calculated hoehe with Math.sqrt(Math.pow(hoeheSeite, 2.0) - Math.pow(seiteA / 2.0, 2.0))");
			} else if (!Double.isNaN(p_seitenkante) && !Double.isNaN(p_seiteA)
					&& Math.pow(p_seitenkante, 2.0) > Math.pow(p_seiteA, 2.0) / 2.0
					&& Math.sqrt(Math.pow(p_seitenkante, 2.0) - Math.pow(p_seiteA, 2.0) / 2.0) != 0) {
				p_hoehe = Math.sqrt(Math.pow(p_seitenkante, 2.0) - Math.pow(p_seiteA, 2.0) / 2.0);
				LOG.debug("Calculated hoehe with Math.sqrt(Math.pow(seitenkante, 2.0) - Math.pow(seiteA, 2.0) / 2.0)");
			} else {
				p_hoehe = Double.NaN;
				LOG.debug("Calculation for hoehe was not possible, set to NaN");
			}
		}

		if (p_hoeheSeite == 0) {
			LOG.debug("Berechnung der hoeheSeite");
			if (!Double.isNaN(p_hoehe) && !Double.isNaN(p_seiteA)
					&& Math.sqrt(Math.pow(p_hoehe, 2.0) + Math.pow(p_seiteA / 2.0, 2.0)) != 0) {
				p_hoeheSeite = Math.sqrt(Math.pow(p_hoehe, 2.0) + Math.pow(p_seiteA / 2.0, 2.0));
				LOG.debug("Calculated hoeheSeite with Math.sqrt(Math.pow(hoehe, 2.0) + Math.pow(seiteA / 2.0, 2.0))");
			} else if (!Double.isNaN(p_mantelflaeche) && !Double.isNaN(p_seiteA)
					&& p_mantelflaeche / (2.0 * p_seiteA) != 0) {
				p_hoeheSeite = p_mantelflaeche / (2.0 * p_seiteA);
				LOG.debug("Calculated hoeheSeite with mantelflaechePyramide / (2.0 * seiteA)");
			} else if (!Double.isNaN(p_oberflaeche) && !Double.isNaN(p_seiteA)
					&& p_oberflaeche >= Math.pow(p_seiteA, 2.0)
					&& (p_oberflaeche - Math.pow(p_seiteA, 2.0)) / (2.0 * p_seiteA) != 0) {
				p_hoeheSeite = (p_oberflaeche - Math.pow(p_seiteA, 2.0)) / (2.0 * p_seiteA);
				LOG.debug("Calculated hoeheSeite with (oberflaechePyramide - Math.pow(seiteA, 2.0)) / (2.0 * seiteA)");
			} else if (!Double.isNaN(p_oberflaeche) && !Double.isNaN(p_grundflaeche) && !Double.isNaN(p_seiteA)
					&& p_oberflaeche >= p_grundflaeche && (p_oberflaeche - p_grundflaeche) / (2.0 * p_seiteA) != 0) {
				p_hoeheSeite = (p_oberflaeche - p_grundflaeche) / (2.0 * p_seiteA);
				LOG.debug("Calculated hoeheSeite with (oberflaechePyramide - grundflaechePyramide) / (2.0 * seiteA)");
			} else if (!Double.isNaN(p_seitenkante) && !Double.isNaN(p_seiteA) && p_seitenkante > p_seiteA / 2.0
					&& Math.sqrt(Math.pow(p_seitenkante, 2.0) - Math.pow(p_seiteA / 2.0, 2.0)) != 0) {
				p_hoeheSeite = Math.sqrt(Math.pow(p_seitenkante, 2.0) - Math.pow(p_seiteA / 2.0, 2.0));
				LOG.debug(
						"Calculated hoeheSeite with Math.sqrt(Math.pow(seitenkante, 2.0) - Math.pow(seiteA / 2.0, 2.0))");
			} else {
				p_hoeheSeite = Double.NaN;
				LOG.debug("Calculation for hoeheSeite was not possible, set to NaN");
			}
		}

		if (p_seitenkante == 0) {
			LOG.debug("Berechnung der seitenkante");
			if (!Double.isNaN(p_hoehe) && !Double.isNaN(p_seiteA)
					&& Math.sqrt(Math.pow(p_hoehe, 2.0) + Math.pow(p_seiteA, 2.0) / 2.0) != 0) {
				p_seitenkante = Math.sqrt(Math.pow(p_hoehe, 2.0) + Math.pow(p_seiteA, 2.0) / 2.0);
				LOG.debug("Calculated seitenkante with Math.sqrt(Math.pow(hoehe, 2.0) + Math.pow(seiteA, 2.0) / 2.0)");
			} else if (!Double.isNaN(p_hoeheSeite) && !Double.isNaN(p_seiteA)
					&& Math.sqrt(Math.pow(p_hoeheSeite, 2.0) + Math.pow(p_seiteA / 2.0, 2.0)) != 0) {
				p_seitenkante = Math.sqrt(Math.pow(p_hoeheSeite, 2.0) + Math.pow(p_seiteA / 2.0, 2.0));
				LOG.debug(
						"Calculated seitenkante with Math.sqrt(Math.pow(hoeheSeite, 2.0) + Math.pow(seiteA / 2.0, 2.0))");
			} else {
				p_seitenkante = Double.NaN;
				LOG.debug("Calculation for seitenkante was not possible, set to NaN");
			}
		}

		if (p_grundflaeche == 0) {
			LOG.debug("Berechnung der grundflaechePyramide");
			if (!Double.isNaN(p_seiteA) && Math.pow(p_seiteA, 2.0) != 0) {
				p_grundflaeche = Math.pow(p_seiteA, 2.0);
				LOG.debug("Calculated grundflaechePyramide with Math.pow(seiteA, 2.0)");
			} else if (!Double.isNaN(p_volumen) && !Double.isNaN(p_hoehe) && (3.0 * p_volumen) / p_hoehe != 0) {
				p_grundflaeche = (3.0 * p_volumen) / p_hoehe;
				LOG.debug("Calculated grundflaechePyramide with (3.0 * volumenPyramide) / hoehe");
			} else if (!Double.isNaN(p_oberflaeche) && !Double.isNaN(p_mantelflaeche)
					&& p_oberflaeche >= p_mantelflaeche && p_oberflaeche - p_mantelflaeche != 0) {
				p_grundflaeche = p_oberflaeche - p_mantelflaeche;
				LOG.debug("Calculated grundflaechePyramide with oberflaechePyramide - mantelflaechePyramide");
			} else {
				p_grundflaeche = Double.NaN;
				LOG.debug("Calculation for grundflaechePyramide was not possible, set to NaN");
			}
		}

		if (p_mantelflaeche == 0) {
			LOG.debug("Berechnung der mantelflaechePyramide");
			if (!Double.isNaN(p_seiteA) && !Double.isNaN(p_hoeheSeite) && 2.0 * p_seiteA * p_hoeheSeite != 0) {
				p_mantelflaeche = 2.0 * p_seiteA * p_hoeheSeite;
				LOG.debug("Calculated mantelflaechePyramide with 2.0 * seiteA * hoeheSeite");
			} else if (!Double.isNaN(p_oberflaeche) && !Double.isNaN(p_grundflaeche) && p_oberflaeche >= p_grundflaeche
					&& p_oberflaeche - p_grundflaeche != 0) {
				p_mantelflaeche = p_oberflaeche - p_grundflaeche;
				LOG.debug("Calculated mantelflaechePyramide with oberflaechePyramide - grundflaechePyramide");
			} else {
				p_mantelflaeche = Double.NaN;
				LOG.debug("Calculation for mantelflaechePyramide was not possible, set to NaN");
			}
		}

		if (p_oberflaeche == 0) {
			if (!Double.isNaN(p_grundflaeche) && !Double.isNaN(p_mantelflaeche)
					&& p_grundflaeche + p_mantelflaeche != 0) {
				p_oberflaeche = p_grundflaeche + p_mantelflaeche;
				LOG.debug("Calculated oberflaechePyramide with grundflaechePyramide + mantelflaechePyramide");
			} else if (!Double.isNaN(p_seiteA) && !Double.isNaN(p_hoeheSeite)
					&& Math.pow(p_seiteA, 2.0) + 2.0 * p_seiteA * p_hoeheSeite != 0) {
				p_oberflaeche = Math.pow(p_seiteA, 2.0) + 2.0 * p_seiteA * p_hoeheSeite;
				LOG.debug("Calculated oberflaechePyramide with Math.pow(seiteA, 2.0) + 2.0 * seiteA * hoeheSeite");
			} else {
				p_oberflaeche = Double.NaN;
				LOG.debug("Calculation for oberflaechePyramide was not possible, set to NaN");
			}
		}

		if (p_volumen == 0) {
			LOG.debug("Berechnung der volumenPyramide");
			if (!Double.isNaN(p_grundflaeche) && !Double.isNaN(p_hoehe)
					&& (1.0 / 3.0) * p_grundflaeche * p_hoehe != 0) {
				p_volumen = (1.0 / 3.0) * p_grundflaeche * p_hoehe;
				LOG.debug("Calculated volumenPyramide with (1.0 / 3.0) * grundflaechePyramide * hoehe");
			} else if (!Double.isNaN(p_seiteA) && !Double.isNaN(p_hoehe)
					&& (1.0 / 3.0) * Math.pow(p_seiteA, 2.0) * p_hoehe != 0) {
				p_volumen = (1.0 / 3.0) * Math.pow(p_seiteA, 2.0) * p_hoehe;
				LOG.debug("Calculated volumenPyramide with (1.0 / 3.0) * Math.pow(seiteA, 2.0) * hoehe");
			} else {
				p_volumen = Double.NaN;
				LOG.debug("Calculation for volumenPyramide was not possible, set to NaN");
			}
		}

		if (p_grundflaecheDiagonale == 0) {
			LOG.debug("Berechnung der grundflaecheDiagonale");
			if (!Double.isNaN(p_seiteA) && p_seiteA * Math.sqrt(2.0) != 0) {
				p_grundflaecheDiagonale = p_seiteA * Math.sqrt(2.0);
				LOG.debug("Calculated grundflaecheDiagonale with seiteA * Math.sqrt(2.0)");
			} else {
				p_grundflaecheDiagonale = Double.NaN;
				LOG.debug("Calculation for grundflaecheDiagonale was not possible, set to NaN");
			}
		}

		LOG.debug("<============== Berechnung Ende ==============>");
		LOG.debug("");
		LOG.debug("<============== Setze Werte in Dreieck Start ==============>");

		pyramide.setP_seiteA(p_seiteA);
		pyramide.setP_hoehe(p_hoehe);
		pyramide.setP_hoeheSeite(p_hoeheSeite);
		pyramide.setP_seitenkante(p_seitenkante);
		pyramide.setP_grundflaecheDiagonale(p_grundflaecheDiagonale);
		pyramide.setP_grundflaeche(p_grundflaeche);
		pyramide.setP_mantelflaeche(p_mantelflaeche);
		pyramide.setP_oberflaeche(p_oberflaeche);
		pyramide.setP_volumen(p_volumen);

		LOG.debug("p_seiteA: " + p_seiteA);
		LOG.debug("p_hoehe: " + p_hoehe);
		LOG.debug("p_hoeheSeite: " + p_hoeheSeite);
		LOG.debug("p_seitenkante: " + p_seitenkante);
		LOG.debug("p_grundflaecheDiagonale: " + p_grundflaecheDiagonale);
		LOG.debug("p_grundflaeche: " + p_grundflaeche);
		LOG.debug("p_mantelflaeche: " + p_mantelflaeche);
		LOG.debug("p_oberflaeche: " + p_oberflaeche);
		LOG.debug("p_volumen: " + p_volumen);

		LOG.debug("<============== Setze Werte in Dreieck Ende ==============>");

		LOG.debug("Setting DecimalFormat into Pyramide bean...");
		DF df = new DF();
		pyramide.setDecimalFormat(df.getDecimalFormat(decimalPlaces)); // Pass decimalPlaces

		LOG.debug("berechnePyramide() method finished.");
	}
}
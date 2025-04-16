package de.m4automotive.logic.Dreiecke;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.DF;
import de.m4automotive.model.Prisma;

public class PrismaLogic {

	private static final Logger LOG = LogManager.getLogger(PrismaLogic.class);

	public void berechnePrisma(HttpServletRequest request, HttpServletResponse response, Prisma prisma)
			throws ServletException, IOException {
		LOG.debug("berechnePrisma() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Prisma) Start =======>");
		double pr_grundflaeche = Double.parseDouble(request.getParameter("pr_grundflaeche").replace(',', '.'));
		LOG.debug("pr_grundflaeche" + pr_grundflaeche);
		double pr_mantelflaeche = Double.parseDouble(request.getParameter("pr_mantelflaeche").replace(',', '.'));
		LOG.debug("pr_mantelflaeche" + pr_mantelflaeche);
		double pr_oberflaeche = Double.parseDouble(request.getParameter("pr_oberflaeche").replace(',', '.'));
		LOG.debug("pr_oberflaeche" + pr_oberflaeche);
		double pr_volumen = Double.parseDouble(request.getParameter("pr_volumen").replace(',', '.'));
		LOG.debug("pr_volumen" + pr_volumen);
		double pr_hoehe = Double.parseDouble(request.getParameter("pr_hoehe").replace(',', '.'));
		LOG.debug("pr_hoehe" + pr_hoehe);
		double pr_umfang = Double.parseDouble(request.getParameter("pr_umfang").replace(',', '.'));
		LOG.debug("pr_umfang" + pr_umfang);
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Prisma) Ende =======>");
		LOG.debug(" ");

		// 2. Calculation Logic
		LOG.debug("<======= Berechnung Prisma Start =======>");

		if (pr_grundflaeche == 0) {
			LOG.debug("Versuche Berechnung der pr_grundflaeche");
			if (!Double.isNaN(pr_volumen) && !Double.isNaN(pr_hoehe) && pr_hoehe > 0 && (pr_volumen / pr_hoehe) > 0) {
				pr_grundflaeche = pr_volumen / pr_hoehe;
				LOG.debug("pr_grundflaeche berechnet mit: Volumen / Hoehe");
			} else if (!Double.isNaN(pr_oberflaeche) && !Double.isNaN(pr_mantelflaeche)
					&& pr_oberflaeche >= pr_mantelflaeche && ((pr_oberflaeche - pr_mantelflaeche) / 2.0) > 0) {
				pr_grundflaeche = (pr_oberflaeche - pr_mantelflaeche) / 2.0;
				LOG.debug("pr_grundflaeche berechnet mit: (Oberfläche - Mantelfläche) / 2");
			} else if (!Double.isNaN(pr_oberflaeche) && !Double.isNaN(pr_umfang) && pr_umfang > 0
					&& !Double.isNaN(pr_hoehe) && ((pr_oberflaeche - pr_umfang * pr_hoehe) / 2.0) > 0) {
				pr_grundflaeche = (pr_oberflaeche - pr_umfang * pr_hoehe) / 2.0;
				LOG.debug("pr_grundflaeche berechnet mit: (Oberfläche - Umfang*Höhe) / 2");
			} else {
				pr_grundflaeche = Double.NaN;
				LOG.debug("pr_grundflaeche konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pr_grundflaeche war gegeben.");
		}

		if (pr_umfang == 0) {
			LOG.debug("Versuche Berechnung des pr_umfang");
			if (!Double.isNaN(pr_mantelflaeche) && !Double.isNaN(pr_hoehe) && pr_hoehe > 0
					&& (pr_mantelflaeche / pr_hoehe) > 0) {
				pr_umfang = pr_mantelflaeche / pr_hoehe;
				LOG.debug("pr_umfang berechnet mit: Mantelfläche / Hoehe");
			} else {
				pr_umfang = Double.NaN;
				LOG.debug(
						"pr_umfang konnte nicht berechnet werden (benötigt Grundflächenform oder Mantelfl./Höhe), bleibt NaN.");
			}
		} else {
			LOG.debug("pr_umfang war gegeben.");
		}

		if (pr_hoehe == 0) {
			LOG.debug("Versuche Berechnung der pr_hoehe");
			if (!Double.isNaN(pr_volumen) && !Double.isNaN(pr_grundflaeche) && pr_grundflaeche > 0
					&& (pr_volumen / pr_grundflaeche) > 0) {
				pr_hoehe = pr_volumen / pr_grundflaeche;
				LOG.debug("pr_hoehe berechnet mit: Volumen / Grundfläche");
			} else if (!Double.isNaN(pr_mantelflaeche) && !Double.isNaN(pr_umfang) && pr_umfang > 0
					&& (pr_mantelflaeche / pr_umfang) > 0) {
				pr_hoehe = pr_mantelflaeche / pr_umfang;
				LOG.debug("pr_hoehe berechnet mit: Mantelfläche / Umfang");
			} else if (!Double.isNaN(pr_oberflaeche) && !Double.isNaN(pr_grundflaeche) && !Double.isNaN(pr_umfang)
					&& pr_umfang > 0 && pr_oberflaeche >= 2 * pr_grundflaeche
					&& ((pr_oberflaeche - 2 * pr_grundflaeche) / pr_umfang) > 0) {
				pr_hoehe = (pr_oberflaeche - 2 * pr_grundflaeche) / pr_umfang;
				LOG.debug("pr_hoehe berechnet mit: (Oberfläche - 2*Grundfläche) / Umfang");
			} else {
				pr_hoehe = Double.NaN;
				LOG.debug("pr_hoehe konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pr_hoehe war gegeben.");
		}

		if (pr_volumen == 0) {
			LOG.debug("Versuche Berechnung des pr_volumen");
			if (!Double.isNaN(pr_grundflaeche) && !Double.isNaN(pr_hoehe) && (pr_grundflaeche * pr_hoehe) != 0) {
				pr_volumen = pr_grundflaeche * pr_hoehe;
				LOG.debug("pr_volumen berechnet mit: Grundfläche * Höhe");
			} else if (!Double.isNaN(pr_grundflaeche) && !Double.isNaN(pr_mantelflaeche) && !Double.isNaN(pr_umfang)
					&& pr_umfang > 0 && (pr_grundflaeche * (pr_mantelflaeche / pr_umfang)) != 0) {
				pr_volumen = pr_grundflaeche * (pr_mantelflaeche / pr_umfang);
				LOG.debug("pr_volumen berechnet mit: Grundfläche * (Mantelfläche / Umfang)");
			} else {
				pr_volumen = Double.NaN;
				LOG.debug("pr_volumen konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pr_volumen war gegeben.");
		}

		if (pr_mantelflaeche == 0) {
			LOG.debug("Versuche Berechnung der pr_mantelflaeche");
			if (!Double.isNaN(pr_umfang) && !Double.isNaN(pr_hoehe) && (pr_umfang * pr_hoehe) != 0) {
				pr_mantelflaeche = pr_umfang * pr_hoehe;
				LOG.debug("pr_mantelflaeche berechnet mit: Umfang * Höhe");
			} else if (!Double.isNaN(pr_oberflaeche) && !Double.isNaN(pr_grundflaeche)
					&& pr_oberflaeche >= 2 * pr_grundflaeche && (pr_oberflaeche - 2 * pr_grundflaeche) != 0) {
				pr_mantelflaeche = pr_oberflaeche - 2 * pr_grundflaeche; // Erlaube 0
				LOG.debug("pr_mantelflaeche berechnet mit: Oberfläche - 2*Grundfläche");
			} else if (!Double.isNaN(pr_umfang) && !Double.isNaN(pr_volumen) && !Double.isNaN(pr_grundflaeche)
					&& pr_grundflaeche > 0 && (pr_umfang * (pr_volumen / pr_grundflaeche)) != 0) {
				pr_mantelflaeche = pr_umfang * (pr_volumen / pr_grundflaeche); // Mantel 0 möglich
				LOG.debug("pr_mantelflaeche berechnet mit: Umfang * (Volumen / Grundfläche)");
			} else {
				pr_mantelflaeche = Double.NaN;
				LOG.debug("pr_mantelflaeche konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pr_mantelflaeche war gegeben.");
		}

		if (pr_oberflaeche == 0) {
			LOG.debug("Versuche Berechnung der pr_oberflaeche");
			if (!Double.isNaN(pr_grundflaeche) && !Double.isNaN(pr_mantelflaeche)
					&& (2 * pr_grundflaeche + pr_mantelflaeche) != 0) {
				pr_oberflaeche = 2 * pr_grundflaeche + pr_mantelflaeche;
				LOG.debug("pr_oberflaeche berechnet mit: 2*Grundfläche + Mantelfläche");
			} else if (!Double.isNaN(pr_grundflaeche) && !Double.isNaN(pr_umfang) && !Double.isNaN(pr_hoehe)
					&& (2 * pr_grundflaeche + pr_umfang * pr_hoehe) != 0) {
				pr_oberflaeche = 2 * pr_grundflaeche + pr_umfang * pr_hoehe;
				LOG.debug("pr_oberflaeche berechnet mit: 2*Grundfläche + Umfang*Höhe");
			} else if (!Double.isNaN(pr_volumen) && !Double.isNaN(pr_hoehe) && pr_hoehe > 0
					&& !Double.isNaN(pr_mantelflaeche) && (2 * (pr_volumen / pr_hoehe) + pr_mantelflaeche) != 0) {
				pr_oberflaeche = 2 * (pr_volumen / pr_hoehe) + pr_mantelflaeche;
				LOG.debug("pr_oberflaeche berechnet mit: 2*(Volumen/Höhe) + Mantelfläche");
			} else {
				pr_oberflaeche = Double.NaN;
				LOG.debug("pr_oberflaeche konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("pr_oberflaeche war gegeben.");
		}

		LOG.debug("<======= Berechnung Prisma Ende =======>");
		LOG.debug("");

		LOG.debug("<======= Setze Finale Werte in PrismaBean Start =======>");
		prisma.setPr_grundflaeche(pr_grundflaeche);
		prisma.setPr_mantelflaeche(pr_mantelflaeche);
		prisma.setPr_oberflaeche(pr_oberflaeche);
		prisma.setPr_volumen(pr_volumen);
		prisma.setPr_hoehe(pr_hoehe);
		prisma.setPr_umfang(pr_umfang);

		LOG.debug("pr_grundflaeche" + pr_grundflaeche);
		LOG.debug("pr_mantelflaeche" + pr_mantelflaeche);
		LOG.debug("pr_oberflaeche" + pr_oberflaeche);
		LOG.debug("pr_volumen" + pr_volumen);
		LOG.debug("pr_hoehe" + pr_hoehe);
		LOG.debug("pr_umfang" + pr_umfang);
		LOG.debug("<======= Setze Finale Werte in PrismaBean Ende =======>");

		LOG.debug("Setting DecimalFormat into Prisma bean...");
		DF df = new DF();
		prisma.setDecimalFormat(df.getDecimalFormat(decimalPlaces));

		LOG.debug("berechnePrisma() method finished.");
	}
}
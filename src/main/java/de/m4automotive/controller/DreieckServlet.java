package de.m4automotive.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.logic.Dreiecke.DreieckLogic;
import de.m4automotive.logic.Dreiecke.HexagonLogic;
import de.m4automotive.logic.Dreiecke.OctagonLogic;
import de.m4automotive.logic.Dreiecke.ParallelogrammLogic;
import de.m4automotive.logic.Dreiecke.PentagonLogic;
import de.m4automotive.logic.Dreiecke.PrismaLogic;
import de.m4automotive.logic.Dreiecke.PyramideLogic;
import de.m4automotive.logic.Dreiecke.QuaderLogic;
import de.m4automotive.logic.Dreiecke.RechteckLogic;
import de.m4automotive.logic.Dreiecke.TrapezLogic;
import de.m4automotive.logic.Dreiecke.WuerfelLogic;
import de.m4automotive.model.Dreieck.Dreieck;
import de.m4automotive.model.Dreieck.Hexagon;
import de.m4automotive.model.Dreieck.Octagon;
import de.m4automotive.model.Dreieck.Parallelogramm;
import de.m4automotive.model.Dreieck.Pentagon;
import de.m4automotive.model.Dreieck.Prisma;
import de.m4automotive.model.Dreieck.Pyramide;
import de.m4automotive.model.Dreieck.Quader;
import de.m4automotive.model.Dreieck.Rechteck;
import de.m4automotive.model.Dreieck.Trapez;
import de.m4automotive.model.Dreieck.Wuerfel;

@WebServlet(name = "/DreieckServlet", urlPatterns = ("/dreieck"))
public class DreieckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger(DreieckServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("DreieckServlet doGet() called");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		LOG.debug("doGet() method called");
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/dreiecke.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException e) {
			LOG.error("Error parsing number: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
		} catch (Exception e) {
			LOG.error("Unexpected error in doGet: " + e.getMessage(), e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("DreieckServlet doPost() called");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {
			processRequest(request, response);
		} catch (NumberFormatException e) {
			LOG.error("Error parsing number: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
		} catch (Exception e) {
			LOG.error("Unexpected error in doPost: " + e.getMessage(), e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("processRequest() method called");

		String action = request.getParameter("action");

		LOG.debug("Action: {}", action);

		switch (action) {
		case "berechne":
			try {
				Rechteck rechteck = new Rechteck();
				Trapez trapez = new Trapez();
				Parallelogramm parallelogramm = new Parallelogramm();
				Pyramide pyramide = new Pyramide();
				Pentagon pentagon = new Pentagon();
				Hexagon hexagon = new Hexagon();
				Octagon octagon = new Octagon();
				Wuerfel wuerfel = new Wuerfel();
				Quader quader = new Quader();
				Prisma prisma = new Prisma();
				Dreieck dreieck = new Dreieck();

				request.setAttribute("dreieck", dreieck);
				request.setAttribute("rechteck", rechteck);
				request.setAttribute("trapez", trapez);
				request.setAttribute("parallelogramm", parallelogramm);
				request.setAttribute("pyramide", pyramide);
				request.setAttribute("pentagon", pentagon);
				request.setAttribute("hexagon", hexagon);
				request.setAttribute("octagon", octagon);
				request.setAttribute("wuerfel", wuerfel);
				request.setAttribute("quader", quader);
				request.setAttribute("prisma", prisma);

				DreieckLogic dreieckLogic = new DreieckLogic();
				dreieckLogic.berechneDreiecke(request, response, dreieck);
				RechteckLogic rechteckLogic = new RechteckLogic();
				rechteckLogic.berechneRechteck(request, response, rechteck);
				TrapezLogic trapezLogic = new TrapezLogic();
				trapezLogic.berechneTrapez(request, response, trapez);
				ParallelogrammLogic parallelogrammLogic = new ParallelogrammLogic();
				parallelogrammLogic.berechneParallelogramm(request, response, parallelogramm);
				PentagonLogic pentagonLogic = new PentagonLogic();
				pentagonLogic.berechnePentagon(request, response, pentagon);
				HexagonLogic hexagonLogic = new HexagonLogic();
				hexagonLogic.berechneHexagon(request, response, hexagon);
				OctagonLogic octagonLogic = new OctagonLogic();
				octagonLogic.berechneOctagon(request, response, octagon);
				PyramideLogic pyramideLogic = new PyramideLogic();
				pyramideLogic.berechnePyramide(request, response, pyramide);
				QuaderLogic quaderLogic = new QuaderLogic();
				quaderLogic.berechneQuader(request, response, quader);
				WuerfelLogic wuerfelLogic = new WuerfelLogic();
				wuerfelLogic.berechneWuerfel(request, response, wuerfel);
				PrismaLogic prismaLogic = new PrismaLogic();
				prismaLogic.berechnePrisma(request, response, prisma);

				forwardToJsp(request, response);
			} catch (NumberFormatException e) {
				LOG.error("Error parsing number during calculation (action 'berechne'): {}", e.getMessage());
				request.setAttribute("errorMessage", "Ungültiges Zahlenformat in der Eingabe.");
			} catch (IllegalArgumentException e) {
				LOG.error("Invalid argument during calculation (action 'berechne'): {}", e.getMessage());
				request.setAttribute("errorMessage", e.getMessage());
				forwardToJsp(request, response);
			} catch (Exception e) {
				LOG.error("Unexpected error during calculation (action 'berechne'): {}", e.getMessage(), e);
				request.setAttribute("errorMessage", "Ein unerwarteter Fehler ist bei der Berechnung aufgetreten.");
				forwardToJsp(request, response);
			}
			break;

		default:
			LOG.warn("Unknown action received: {}", action);
			request.setAttribute("errorMessage", "Unbekannte Aktion: " + action);
			forwardToJsp(request, response);
			break;
		}
	}

	private void forwardToJsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/dreiecke.jsp");
		dispatcher.forward(request, response);
	}

}
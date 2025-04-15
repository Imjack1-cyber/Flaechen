package de.m4automotive.controller;

import java.io.IOException; // Importing IOException to handle input/output errors.
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; // Importing ServletException for handling servlet-related errors.
import javax.servlet.annotation.WebServlet; // Importing WebServlet annotation for mapping URLs to this servlet.
import javax.servlet.http.HttpServlet; // Importing HttpServlet class to create an HTTP servlet.
import javax.servlet.http.HttpServletRequest; // Importing HttpServletRequest for handling HTTP requests.
import javax.servlet.http.HttpServletResponse; // Importing HttpServletResponse for handling HTTP responses.

import org.apache.logging.log4j.LogManager; // Importing LogManager for logging.
import org.apache.logging.log4j.Logger; // Importing Logger interface for logging messages.

import de.m4automotive.model.Dreieck;
import de.m4automotive.model.Hexagon;
import de.m4automotive.model.Octagon;
import de.m4automotive.model.Parallelogramm;
import de.m4automotive.model.Pentagon;
import de.m4automotive.model.Prisma;
import de.m4automotive.model.Pyramide;
import de.m4automotive.model.Quader;
import de.m4automotive.model.Rechteck;
import de.m4automotive.model.Trapez;
import de.m4automotive.model.Wuerfel;

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
		if (action == null || action.trim().isEmpty()) {
			action = "showForm"; // Standardaktion, wenn nichts übergeben wird
		}

		LOG.debug("Action: {}", action); // Parameterisiertes Logging ist besser

		Dreieck currentDreieck = new Dreieck(); // Create new bean for this request
		request.setAttribute("dreieck", currentDreieck); // Put it in request scope EARLY

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

				request.setAttribute("rechteckErgebnis", rechteck);
				request.setAttribute("trapezErgebnis", trapez);
				request.setAttribute("parallelogrammErgebnis", parallelogramm);
				request.setAttribute("pyramideErgebnis", pyramide);
				request.setAttribute("pentagonErgebnis", pentagon);
				request.setAttribute("hexagonErgebnis", hexagon);
				request.setAttribute("octagonErgebnis", octagon);
				request.setAttribute("wuerfelErgebnis", wuerfel);
				request.setAttribute("quaderErgebnis", quader);
				request.setAttribute("prismaErgebnis", prisma);

				berechneDreiecke(request, response, dreieck);
				berechnePyramide(request, response, pyramide);
				berechneRechteck(request, response, rechteck);
				berechneParallelogramm(request, response, parallelogramm);
				berechneTrapez(request, response, trapez);
				berechnePentagon(request, response, pentagon);
				berechneHexagon(request, response, hexagon);
				berechneOctagon(request, response, octagon);
				berechneWuerfel(request, response, wuerfel);
				berechnePrisma(request, response, prisma);
				berechneQuader(request, response, quader);

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
				forwardToJsp(request, response); // Zeige Formular mit Fehler
			}
			break; // Ende von case "berechne"

		case "showForm":
			forwardToJsp(request, response);
			break;

		default:
			// Behandelt unbekannte Aktionen
			LOG.warn("Unknown action received: {}", action);
			request.setAttribute("errorMessage", "Unbekannte Aktion: " + action);
			// request.setAttribute("geometrieDaten", this.geometrieDaten);
			forwardToJsp(request, response);
			break;
		}
	}

	private void berechneDreiecke(HttpServletRequest request, HttpServletResponse response, Dreieck dreieck)
			throws ServletException, IOException {

		LOG.debug("berechneDreiecke() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);
		double a = Double.parseDouble(request.getParameter("a"));
		double b = Double.parseDouble(request.getParameter("b"));
		double c = Double.parseDouble(request.getParameter("c"));
		double umfangDreieck = Double.parseDouble(request.getParameter("umfangDreieck"));
		double flaecheninhaltDreieck = Double.parseDouble(request.getParameter("flaecheninhaltDreieck"));
		double alpha = Double.parseDouble(request.getParameter("alpha"));
		double beta = Double.parseDouble(request.getParameter("beta"));
		double gamma = Double.parseDouble(request.getParameter("gamma"));
		double hoeheA = Double.parseDouble(request.getParameter("hoeheA"));
		double hoeheB = Double.parseDouble(request.getParameter("hoeheB"));
		double hoeheC = Double.parseDouble(request.getParameter("hoeheC"));

		if (a == 0) {
			// Try Law of Cosines first if b, c, alpha are known
			if (b > 1E-9 && c > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9) {
				double cosAlpha = Math.cos(Math.toRadians(alpha));
				double valInsideSqrt = b * b + c * c - 2.0 * b * c * cosAlpha;
				if (valInsideSqrt > 1E-9) { // Ensure positive value for sqrt and a > 0
					double calculatedA = Math.sqrt(valInsideSqrt);
					if (calculatedA > 1E-9 && !Double.isNaN(calculatedA)) {
						// Check triangle inequality potential (using calculated a)
						if ((b + c > calculatedA) && (calculatedA + c > b) && (calculatedA + b > c)) {
							a = calculatedA;
						}
					}
				}
			}

			// Try perimeter if umfang, b, c are known
			if (a == 0 && umfangDreieck > 1E-9 && b > 1E-9 && c > 1E-9) {
				double calculatedA = umfangDreieck - b - c;
				if (calculatedA > 1E-9) { // Ensure positive side length
					// Check triangle inequality potential (using calculated a)
					if ((b + c > calculatedA) && (calculatedA + c > b) && (calculatedA + b > c)) {
						a = calculatedA;
					}
				}
			}

			// Try Law of Sines (alpha/beta)
			if (a == 0 && b > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9) {
				double sinBeta = Math.sin(Math.toRadians(beta));
				if (Math.abs(sinBeta) > 1E-9) { // Avoid division by zero
					double calculatedA = b * Math.sin(Math.toRadians(alpha)) / sinBeta;
					if (calculatedA > 1E-9 && !Double.isNaN(calculatedA)) {
						// Check triangle inequality potential (using calculated a)
						// Requires c to be known or calculated already for full check
						if (c <= 1E-9 || ((b + c > calculatedA) && (calculatedA + c > b) && (calculatedA + b > c))) {
							a = calculatedA;
						}
					}
				}
			}

			// Try Law of Sines (alpha/gamma)
			if (a == 0 && c > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9) {
				double sinGamma = Math.sin(Math.toRadians(gamma));
				if (Math.abs(sinGamma) > 1E-9) { // Avoid division by zero
					double calculatedA = c * Math.sin(Math.toRadians(alpha)) / sinGamma;
					if (calculatedA > 1E-9 && !Double.isNaN(calculatedA)) {
						// Check triangle inequality potential (using calculated a)
						// Requires b to be known or calculated already for full check
						if (b <= 1E-9 || ((b + c > calculatedA) && (calculatedA + c > b) && (calculatedA + b > c))) {
							a = calculatedA;
						}
					}
				}
			}

			if (a == 0) {
				a = Double.NaN;
			}
		}

		if (b == 0) {
			// Try Law of Cosines first if a, c, beta are known
			if (a > 1E-9 && c > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9) {
				double cosBeta = Math.cos(Math.toRadians(beta));
				double valInsideSqrt = a * a + c * c - 2.0 * a * c * cosBeta;
				if (valInsideSqrt > 1E-9) {
					double calculatedB = Math.sqrt(valInsideSqrt);
					if (calculatedB > 1E-9 && !Double.isNaN(calculatedB)) {
						if ((a + c > calculatedB) && (calculatedB + c > a) && (calculatedB + a > c)) {
							b = calculatedB;
						}
					}
				}
			}

			// Try perimeter if umfang, a, c are known
			if (b == 0 && umfangDreieck > 1E-9 && a > 1E-9 && c > 1E-9) {
				double calculatedB = umfangDreieck - a - c;
				if (calculatedB > 1E-9) {
					if ((a + c > calculatedB) && (calculatedB + c > a) && (calculatedB + a > c)) {
						b = calculatedB;
					}
				}
			}

			// Try Law of Sines (beta/alpha)
			if (b == 0 && a > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9) {
				double sinAlpha = Math.sin(Math.toRadians(alpha));
				if (Math.abs(sinAlpha) > 1E-9) {
					double calculatedB = a * Math.sin(Math.toRadians(beta)) / sinAlpha;
					if (calculatedB > 1E-9 && !Double.isNaN(calculatedB)) {
						if (c <= 1E-9 || ((a + c > calculatedB) && (calculatedB + c > a) && (calculatedB + a > c))) {
							b = calculatedB;
						}
					}
				}
			}

			// Try Law of Sines (beta/gamma)
			if (b == 0 && c > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9) {
				double sinGamma = Math.sin(Math.toRadians(gamma));
				if (Math.abs(sinGamma) > 1E-9) {
					double calculatedB = c * Math.sin(Math.toRadians(beta)) / sinGamma;
					if (calculatedB > 1E-9 && !Double.isNaN(calculatedB)) {
						if (a <= 1E-9 || ((a + c > calculatedB) && (calculatedB + c > a) && (calculatedB + a > c))) {
							b = calculatedB;
						}
					}
				}
			}

			if (b == 0) {
				b = Double.NaN;
			}
		}

		if (c == 0) {
			// Try Law of Cosines first if a, b, gamma are known
			if (a > 1E-9 && b > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9) {
				double cosGamma = Math.cos(Math.toRadians(gamma));
				double valInsideSqrt = a * a + b * b - 2.0 * a * b * cosGamma;
				if (valInsideSqrt > 1E-9) {
					double calculatedC = Math.sqrt(valInsideSqrt);
					if (calculatedC > 1E-9 && !Double.isNaN(calculatedC)) {
						if ((a + b > calculatedC) && (calculatedC + b > a) && (calculatedC + a > b)) {
							c = calculatedC;
						}
					}
				}
			}

			// Try perimeter if umfang, a, b are known
			if (c == 0 && umfangDreieck > 1E-9 && a > 1E-9 && b > 1E-9) {
				double calculatedC = umfangDreieck - a - b;
				if (calculatedC > 1E-9) {
					if ((a + b > calculatedC) && (calculatedC + b > a) && (calculatedC + a > b)) {
						c = calculatedC;
					}
				}
			}

			// Try Law of Sines (gamma/alpha)
			if (c == 0 && a > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9) {
				double sinAlpha = Math.sin(Math.toRadians(alpha));
				if (Math.abs(sinAlpha) > 1E-9) {
					double calculatedC = a * Math.sin(Math.toRadians(gamma)) / sinAlpha;
					if (calculatedC > 1E-9 && !Double.isNaN(calculatedC)) {
						if (b <= 1E-9 || ((a + b > calculatedC) && (calculatedC + b > a) && (calculatedC + a > b))) {
							c = calculatedC;
						}
					}
				}
			}

			// Try Law of Sines (gamma/beta)
			if (c == 0 && b > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9) {
				double sinBeta = Math.sin(Math.toRadians(beta));
				if (Math.abs(sinBeta) > 1E-9) {
					double calculatedC = b * Math.sin(Math.toRadians(gamma)) / sinBeta;
					if (calculatedC > 1E-9 && !Double.isNaN(calculatedC)) {
						if (a <= 1E-9 || ((a + b > calculatedC) && (calculatedC + b > a) && (calculatedC + a > b))) {
							c = calculatedC;
						}
					}
				}
			}

			if (c == 0) {
				c = Double.NaN;
			}
		}

		if (umfangDreieck == 0) {
			// Calculate only if a, b, c are valid positive numbers
			if (a > 1E-9 && b > 1E-9 && c > 1E-9 && !Double.isNaN(a) && !Double.isNaN(b) && !Double.isNaN(c)) {
				// Also verify triangle inequality for robustness
				umfangDreieck = a + b + c;
			} else {
				umfangDreieck = Double.NaN; // Cannot calculate if sides are invalid/unknown
			}
		}

		if (alpha == 0) {
			if (b != 0 && c != 0 && (b + c > a) && (a + c > b) && (a + b > c)) {
				double cosAlphaArg = (b * b + c * c - a * a) / (2.0 * b * c);
				if (cosAlphaArg >= -1.0 && cosAlphaArg <= 1.0) {
					double calculatedAlpha = Math.toDegrees(Math.acos(cosAlphaArg));
					if (calculatedAlpha > 1E-9 && calculatedAlpha < 180.0 - 1E-9 && !Double.isNaN(calculatedAlpha)) {
						alpha = calculatedAlpha;
					}
				}
			}

			if (alpha == 0 && beta != 0 && gamma != 0 && (180.0 - beta - gamma) > 1E-9
					&& (180.0 - beta - gamma) < 180.0 - 1E-9) {
				alpha = 180.0 - beta - gamma;
			} else if (alpha == 0 && b != 0 && beta != 0 && a != 0 && Math.sin(Math.toRadians(beta)) != 0) {
				double sinAlphaArg = a * Math.sin(Math.toRadians(beta)) / b;
				if (sinAlphaArg >= -1.0 && sinAlphaArg <= 1.0) {
					double calculatedAlpha = Math.toDegrees(Math.asin(sinAlphaArg));
					if (calculatedAlpha > 1E-9 && calculatedAlpha < 180.0 - 1E-9 && !Double.isNaN(calculatedAlpha)) {
						alpha = calculatedAlpha;
					}
				}
			} else if (alpha == 0 && c != 0 && gamma != 0 && a != 0 && Math.sin(Math.toRadians(gamma)) != 0) {
				double sinAlphaArg = a * Math.sin(Math.toRadians(gamma)) / c;
				if (sinAlphaArg >= -1.0 && sinAlphaArg <= 1.0) {
					double calculatedAlpha = Math.toDegrees(Math.asin(sinAlphaArg));
					if (calculatedAlpha > 1E-9 && calculatedAlpha < 180.0 - 1E-9 && !Double.isNaN(calculatedAlpha)) {
						alpha = calculatedAlpha;
					}
				}
			} else if (alpha == 0 && gamma == 90 && b != 0) {
				double calculatedAlpha = Math.toDegrees(Math.atan(a / b));
				if (calculatedAlpha > 1E-9 && calculatedAlpha < 180.0 - 1E-9 && !Double.isNaN(calculatedAlpha)) {
					alpha = calculatedAlpha;
				}
			}

			if (alpha == 0) {
				alpha = Double.NaN;
			}
		}

		if (beta == 0) {

			if (a != 0 && c != 0 && (a + c > b) && (a + b > c) && (b + c > a)) {
				double cosBetaArg = (a * a + c * c - b * b) / (2.0 * a * c);

				if (cosBetaArg >= -1.0 && cosBetaArg <= 1.0) {
					double calculatedBeta = Math.toDegrees(Math.acos(cosBetaArg));
					if (calculatedBeta != 0 && !Double.isNaN(calculatedBeta)) {
						beta = calculatedBeta;
					}
				}
			}

			if (beta == 0 && alpha != 0 && gamma != 0 && (180.0 - alpha - gamma) > 1E-9) {
				beta = 180.0 - alpha - gamma;
			} else if (beta == 0 && a != 0 && alpha != 0 && b != 0 && Math.sin(Math.toRadians(alpha)) != 0) {
				double sinBetaArg = b * Math.sin(Math.toRadians(alpha)) / a;
				if (sinBetaArg >= -1.0 && sinBetaArg <= 1.0) {
					double calculatedBeta = Math.toDegrees(Math.asin(sinBetaArg));
					if (calculatedBeta != 0 && !Double.isNaN(calculatedBeta)) {
						beta = calculatedBeta;
					}
				}
			} else if (beta == 0 && c != 0 && gamma != 0 && b != 0 && Math.sin(Math.toRadians(gamma)) != 0) {
				double sinBetaArg = b * Math.sin(Math.toRadians(gamma)) / c;
				if (sinBetaArg >= -1.0 && sinBetaArg <= 1.0) {
					double calculatedBeta = Math.toDegrees(Math.asin(sinBetaArg));
					if (calculatedBeta != 0 && !Double.isNaN(calculatedBeta)) {
						beta = calculatedBeta;
					}
				}
			} else if (beta == 0 && gamma == 90 && a != 0) {
				double calculatedBeta = Math.toDegrees(Math.atan(b / a));
				if (calculatedBeta != 0 && !Double.isNaN(calculatedBeta)) {
					beta = calculatedBeta;
				}
			}

			if (beta == 0) {
				beta = Double.NaN;
			}
		}

		if (gamma == 0) {
			if (a != 0 && b != 0 && (a + b > c) && (a + c > b) && (b + c > a)) {
				double cosGammaArg = (a * a + b * b - c * c) / (2.0 * a * b);
				if (cosGammaArg >= -1.0 && cosGammaArg <= 1.0) {
					double calculatedGamma = Math.toDegrees(Math.acos(cosGammaArg));
					if (calculatedGamma > 1E-9 && calculatedGamma < 180.0 - 1E-9 && !Double.isNaN(calculatedGamma)) {
						gamma = calculatedGamma;
					}
				}
			}

			if (gamma == 0 && alpha != 0 && beta != 0 && (180.0 - alpha - beta) > 1E-9
					&& (180.0 - alpha - beta) < 180.0 - 1E-9) {
				gamma = 180.0 - alpha - beta;
			} else if (gamma == 0 && a != 0 && alpha != 0 && c != 0 && Math.sin(Math.toRadians(alpha)) != 0) {
				double sinGammaArg = c * Math.sin(Math.toRadians(alpha)) / a;
				if (sinGammaArg >= -1.0 && sinGammaArg <= 1.0) {
					double calculatedGamma = Math.toDegrees(Math.asin(sinGammaArg));
					if (calculatedGamma > 1E-9 && calculatedGamma < 180.0 - 1E-9 && !Double.isNaN(calculatedGamma)) {
						gamma = calculatedGamma;
					}
				}
			} else if (gamma == 0 && b != 0 && beta != 0 && c != 0 && Math.sin(Math.toRadians(beta)) != 0) {
				double sinGammaArg = c * Math.sin(Math.toRadians(beta)) / b;
				if (sinGammaArg >= -1.0 && sinGammaArg <= 1.0) {
					double calculatedGamma = Math.toDegrees(Math.asin(sinGammaArg));
					if (calculatedGamma > 1E-9 && calculatedGamma < 180.0 - 1E-9 && !Double.isNaN(calculatedGamma)) {
						gamma = calculatedGamma;
					}
				}
			}

			if (gamma == 0) {
				gamma = Double.NaN;
			}
		}

		if (hoeheA == 0) {
			// Try sine formula: hA = b * sin(gamma)
			if (b > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9 && !Double.isNaN(b) && !Double.isNaN(gamma)) {
				double calculatedHoehe = b * Math.sin(Math.toRadians(gamma));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheA = calculatedHoehe;
				}
			}

			// Try sine formula: hA = c * sin(beta)
			if (hoeheA == 0 && c > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9 && !Double.isNaN(c)
					&& !Double.isNaN(beta)) {
				double calculatedHoehe = c * Math.sin(Math.toRadians(beta));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheA = calculatedHoehe;
				}
			}

			// Try area formula: hA = 2 * Area / a
			if (hoeheA == 0 && flaecheninhaltDreieck > 1E-9 && a > 1E-9 && !Double.isNaN(flaecheninhaltDreieck)
					&& !Double.isNaN(a)) {
				double calculatedHoehe = (2.0 * flaecheninhaltDreieck) / a;
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheA = calculatedHoehe;
				}
			}

			if (hoeheA == 0) {
				hoeheA = Double.NaN;
			}
		}

		if (hoeheB == 0) {
			// Try sine formula: hB = a * sin(gamma)
			if (a > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9 && !Double.isNaN(a) && !Double.isNaN(gamma)) {
				double calculatedHoehe = a * Math.sin(Math.toRadians(gamma));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheB = calculatedHoehe;
				}
			}

			// Try sine formula: hB = c * sin(alpha)
			if (hoeheB == 0 && c > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9 && !Double.isNaN(c)
					&& !Double.isNaN(alpha)) {
				double calculatedHoehe = c * Math.sin(Math.toRadians(alpha));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheB = calculatedHoehe;
				}
			}

			// Try area formula: hB = 2 * Area / b
			if (hoeheB == 0 && flaecheninhaltDreieck > 1E-9 && b > 1E-9 && !Double.isNaN(flaecheninhaltDreieck)
					&& !Double.isNaN(b)) {
				double calculatedHoehe = (2.0 * flaecheninhaltDreieck) / b;
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheB = calculatedHoehe;
				}
			}

			if (hoeheB == 0) {
				hoeheB = Double.NaN;
			}
		}

		if (hoeheC == 0) {
			// Try sine formula: hC = a * sin(beta)
			if (a > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9 && !Double.isNaN(a) && !Double.isNaN(beta)) {
				double calculatedHoehe = a * Math.sin(Math.toRadians(beta));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheC = calculatedHoehe;
				}
			}

			// Try sine formula: hC = b * sin(alpha)
			if (hoeheC == 0 && b > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9 && !Double.isNaN(b)
					&& !Double.isNaN(alpha)) {
				double calculatedHoehe = b * Math.sin(Math.toRadians(alpha));
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheC = calculatedHoehe;
				}
			}

			// Try area formula: hC = 2 * Area / c
			if (hoeheC == 0 && flaecheninhaltDreieck > 1E-9 && c > 1E-9 && !Double.isNaN(flaecheninhaltDreieck)
					&& !Double.isNaN(c)) {
				double calculatedHoehe = (2.0 * flaecheninhaltDreieck) / c;
				if (calculatedHoehe > 1E-9 && !Double.isNaN(calculatedHoehe)) {
					hoeheC = calculatedHoehe;
				}
			}

			if (hoeheC == 0) {
				hoeheC = Double.NaN;
			}
		}

		if (flaecheninhaltDreieck == 0) {
			// Try Heron's formula first if a, b, c are known and valid
			if (a > 1E-9 && b > 1E-9 && c > 1E-9 && !Double.isNaN(a) && !Double.isNaN(b) && !Double.isNaN(c)) { // Check
																												// triangle
																												// inequality
				double s = (a + b + c) / 2.0;
				double valInsideSqrt = s * (s - a) * (s - b) * (s - c);
				if (valInsideSqrt > 1E-12) { // Use a smaller tolerance here as area can be small
					double calculatedArea = Math.sqrt(valInsideSqrt);
					if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
						flaecheninhaltDreieck = calculatedArea;
					}
				}
			}

			// Try sine formula: Area = 0.5 * a * b * sin(gamma)
			if (flaecheninhaltDreieck == 0 && a > 1E-9 && b > 1E-9 && gamma > 1E-9 && gamma < 180.0 - 1E-9
					&& !Double.isNaN(a) && !Double.isNaN(b) && !Double.isNaN(gamma)) {
				double calculatedArea = 0.5 * a * b * Math.sin(Math.toRadians(gamma));
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			// Try sine formula: Area = 0.5 * b * c * sin(alpha)
			if (flaecheninhaltDreieck == 0 && b > 1E-9 && c > 1E-9 && alpha > 1E-9 && alpha < 180.0 - 1E-9
					&& !Double.isNaN(b) && !Double.isNaN(c) && !Double.isNaN(alpha)) {
				double calculatedArea = 0.5 * b * c * Math.sin(Math.toRadians(alpha));
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			// Try sine formula: Area = 0.5 * a * c * sin(beta)
			if (flaecheninhaltDreieck == 0 && a > 1E-9 && c > 1E-9 && beta > 1E-9 && beta < 180.0 - 1E-9
					&& !Double.isNaN(a) && !Double.isNaN(c) && !Double.isNaN(beta)) {
				double calculatedArea = 0.5 * a * c * Math.sin(Math.toRadians(beta));
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			// Try base-height formula: Area = 0.5 * a * hoeheA
			if (flaecheninhaltDreieck == 0 && a > 1E-9 && hoeheA > 1E-9 && !Double.isNaN(a) && !Double.isNaN(hoeheA)) {
				double calculatedArea = 0.5 * a * hoeheA;
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			// Try base-height formula: Area = 0.5 * b * hoeheB
			if (flaecheninhaltDreieck == 0 && b > 1E-9 && hoeheB > 1E-9 && !Double.isNaN(b) && !Double.isNaN(hoeheB)) {
				double calculatedArea = 0.5 * b * hoeheB;
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			// Try base-height formula: Area = 0.5 * c * hoeheC
			if (flaecheninhaltDreieck == 0 && c > 1E-9 && hoeheC > 1E-9 && !Double.isNaN(c) && !Double.isNaN(hoeheC)) {
				double calculatedArea = 0.5 * c * hoeheC;
				if (calculatedArea > 1E-12 && !Double.isNaN(calculatedArea)) {
					flaecheninhaltDreieck = calculatedArea;
				}
			}

			if (flaecheninhaltDreieck == 0) {
				flaecheninhaltDreieck = Double.NaN;
			}
		}

		dreieck.setA(a);
		LOG.debug("a: " + a);
		dreieck.setB(b);
		LOG.debug("b: " + b);
		dreieck.setC(c);
		LOG.debug("c: " + c);
		dreieck.setUmfangDreieck(umfangDreieck);
		LOG.debug("umfangDreieck: " + umfangDreieck);
		dreieck.setFlaecheninhaltDreieck(flaecheninhaltDreieck);
		LOG.debug("flaecheninhaltDreieck: " + flaecheninhaltDreieck);
		dreieck.setHoeheA(hoeheA);
		LOG.debug("hoeheA: " + hoeheA);
		dreieck.setHoeheB(hoeheB);
		LOG.debug("hoeheB: " + hoeheB);
		dreieck.setHoeheC(hoeheC);
		LOG.debug("hoeheC: " + hoeheC);
		dreieck.setAlpha(alpha);
		LOG.debug("alpha: " + alpha);
		dreieck.setBeta(beta);
		LOG.debug("beta: " + beta);
		dreieck.setGamma(gamma);
		LOG.debug("gamma: " + gamma);

		// Set the formatter IN the bean
		LOG.debug("Setting DecimalFormat into Kreis bean...");
		dreieck.setDecimalFormat(getDecimalFormat(decimalPlaces)); // <<< Set formatter HERE

	}

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

		// Set the formatter IN the bean
		LOG.debug("Setting DecimalFormat into Kreis bean...");
		pyramide.setDecimalFormat(getDecimalFormat(decimalPlaces)); // Pass decimalPlaces

		LOG.debug("berechneDreieck() method finished.");
	}

	// --- Rechteck ---
	public void berechneRechteck(HttpServletRequest request, HttpServletResponse response, Rechteck rechteck)
			throws ServletException, IOException {
		LOG.debug("berechneRechteck() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces")); // Assuming this won't fail based
																						// on UI
		LOG.debug("Decimal places: " + decimalPlaces);

		// 1. Parse Inputs & Set Initial Bean Values (UNSAFE PARSING)
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Rechteck) Start =======>");
		double r_seiteA = Double.parseDouble(request.getParameter("r_seiteA").replace(',', '.'));
		LOG.debug("r_seiteA: " + r_seiteA);
		double r_seiteB = Double.parseDouble(request.getParameter("r_seiteB").replace(',', '.'));
		LOG.debug("r_seiteB: " + r_seiteB);
		double r_flaecheninhalt = Double.parseDouble(request.getParameter("r_flaecheninhalt").replace(',', '.'));
		LOG.debug("r_flaecheninhalt: " + r_flaecheninhalt);
		double r_umfang = Double.parseDouble(request.getParameter("r_umfang").replace(',', '.'));
		LOG.debug("r_umfang: " + r_umfang);
		double r_diagonale = Double.parseDouble(request.getParameter("r_diagonale").replace(',', '.'));
		LOG.debug("r_diagonale: " + r_diagonale);
		LOG.debug("<======= Parse Inputs & Set Initial Bean Values (Rechteck) Ende =======>");
		LOG.debug(" ");

		// 2. Calculation Logic
		LOG.debug("<======= Berechnung Rechteck Start =======>");

		// --- Berechne r_seiteA ---
		if (r_seiteA == 0) {
			LOG.debug("Versuche Berechnung der r_seiteA");
			if (!Double.isNaN(r_flaecheninhalt) && !Double.isNaN(r_seiteB) && r_seiteB > 0
					&& (r_flaecheninhalt / r_seiteB) > 0) {
				r_seiteA = r_flaecheninhalt / r_seiteB;
				LOG.debug("r_seiteA berechnet mit: Fläche / B");
			} else if (!Double.isNaN(r_umfang) && !Double.isNaN(r_seiteB) && (r_umfang / 2.0 - r_seiteB) > 0) {
				r_seiteA = (r_umfang / 2.0) - r_seiteB;
				LOG.debug("r_seiteA berechnet mit: (Umfang / 2) - B");
			} else if (!Double.isNaN(r_diagonale) && !Double.isNaN(r_seiteB)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteB, 2)
					&& Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2)) > 0) {
				r_seiteA = Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2));
				LOG.debug("r_seiteA berechnet mit: sqrt(Diagonale^2 - B^2)");
			} else {
				r_seiteA = Double.NaN;
				LOG.debug("r_seiteA konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("r_seiteA war gegeben.");
		}

		// --- Berechne r_seiteB ---
		if (r_seiteB == 0) {
			LOG.debug("Versuche Berechnung der r_seiteB");
			if (!Double.isNaN(r_flaecheninhalt) && !Double.isNaN(r_seiteA) && r_seiteA > 0
					&& (r_flaecheninhalt / r_seiteA) > 0) {
				r_seiteB = r_flaecheninhalt / r_seiteA;
				LOG.debug("r_seiteB berechnet mit: Fläche / A");
			} else if (!Double.isNaN(r_umfang) && !Double.isNaN(r_seiteA) && (r_umfang / 2.0 - r_seiteA) > 0) {
				r_seiteB = (r_umfang / 2.0) - r_seiteA;
				LOG.debug("r_seiteB berechnet mit: (Umfang / 2) - A");
			} else if (!Double.isNaN(r_diagonale) && !Double.isNaN(r_seiteA)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteA, 2)
					&& Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2)) > 0) {
				r_seiteB = Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2));
				LOG.debug("r_seiteB berechnet mit: sqrt(Diagonale^2 - A^2)");
			} else {
				r_seiteB = Double.NaN;
				LOG.debug("r_seiteB konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("r_seiteB war gegeben.");
		}

		// --- Berechne r_flaecheninhalt ---
		if (r_flaecheninhalt == 0) {
			LOG.debug("Versuche Berechnung des r_flaecheninhalt");
			if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_seiteB) && (r_seiteA * r_seiteB) > 0) {
				r_flaecheninhalt = r_seiteA * r_seiteB;
				LOG.debug("r_flaecheninhalt berechnet mit: A * B");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_diagonale)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteA, 2)
					&& (r_seiteA * Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2))) > 0) {
				r_flaecheninhalt = r_seiteA * Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2));
				LOG.debug("r_flaecheninhalt berechnet aus A und Diagonale");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_diagonale)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteB, 2)
					&& (r_seiteB * Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2))) > 0) {
				r_flaecheninhalt = r_seiteB * Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2));
				LOG.debug("r_flaecheninhalt berechnet aus B und Diagonale");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_umfang)
					&& (r_seiteA * ((r_umfang / 2.0) - r_seiteA)) > 0) {
				r_flaecheninhalt = r_seiteA * ((r_umfang / 2.0) - r_seiteA);
				LOG.debug("r_flaecheninhalt berechnet aus A und Umfang");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_umfang)
					&& (r_seiteB * ((r_umfang / 2.0) - r_seiteB)) > 0) {
				r_flaecheninhalt = r_seiteB * ((r_umfang / 2.0) - r_seiteB);
				LOG.debug("r_flaecheninhalt berechnet aus B und Umfang");
			} else {
				r_flaecheninhalt = Double.NaN;
				LOG.debug("r_flaecheninhalt konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("r_flaecheninhalt war gegeben.");
		}

		// --- Berechne r_umfang ---
		if (r_umfang == 0) {
			LOG.debug("Versuche Berechnung des r_umfang");
			if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_seiteB) && (2 * (r_seiteA + r_seiteB)) > 0) {
				r_umfang = 2 * (r_seiteA + r_seiteB);
				LOG.debug("r_umfang berechnet mit: 2 * (A + B)");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_flaecheninhalt) && r_seiteA > 0
					&& (2 * (r_seiteA + r_flaecheninhalt / r_seiteA)) > 0) {
				r_umfang = 2 * (r_seiteA + r_flaecheninhalt / r_seiteA);
				LOG.debug("r_umfang berechnet aus A und Fläche");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_flaecheninhalt) && r_seiteB > 0
					&& (2 * (r_seiteB + r_flaecheninhalt / r_seiteB)) > 0) {
				r_umfang = 2 * (r_seiteB + r_flaecheninhalt / r_seiteB);
				LOG.debug("r_umfang berechnet aus B und Fläche");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_diagonale)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteA, 2)
					&& (2 * (r_seiteA + Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2)))) > 0) {
				r_umfang = 2 * (r_seiteA + Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteA, 2)));
				LOG.debug("r_umfang berechnet aus A und Diagonale");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_diagonale)
					&& Math.pow(r_diagonale, 2) >= Math.pow(r_seiteB, 2)
					&& (2 * (r_seiteB + Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2)))) > 0) {
				r_umfang = 2 * (r_seiteB + Math.sqrt(Math.pow(r_diagonale, 2) - Math.pow(r_seiteB, 2)));
				LOG.debug("r_umfang berechnet aus B und Diagonale");
			} else {
				r_umfang = Double.NaN;
				LOG.debug("r_umfang konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("r_umfang war gegeben.");
		}

		// --- Berechne r_diagonale ---
		if (r_diagonale == 0) {
			LOG.debug("Versuche Berechnung der r_diagonale");
			if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_seiteB)
					&& Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow(r_seiteB, 2)) > 0) {
				r_diagonale = Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow(r_seiteB, 2));
				LOG.debug("r_diagonale berechnet mit: sqrt(A^2 + B^2)");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_flaecheninhalt) && r_seiteA > 0
					&& Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow(r_flaecheninhalt / r_seiteA, 2)) > 0) {
				r_diagonale = Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow(r_flaecheninhalt / r_seiteA, 2));
				LOG.debug("r_diagonale berechnet aus A und Fläche");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_flaecheninhalt) && r_seiteB > 0
					&& Math.sqrt(Math.pow(r_seiteB, 2) + Math.pow(r_flaecheninhalt / r_seiteB, 2)) > 0) {
				r_diagonale = Math.sqrt(Math.pow(r_seiteB, 2) + Math.pow(r_flaecheninhalt / r_seiteB, 2));
				LOG.debug("r_diagonale berechnet aus B und Fläche");
			} else if (!Double.isNaN(r_seiteA) && !Double.isNaN(r_umfang)
					&& Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow((r_umfang / 2.0) - r_seiteA, 2)) > 0) {
				r_diagonale = Math.sqrt(Math.pow(r_seiteA, 2) + Math.pow((r_umfang / 2.0) - r_seiteA, 2));
				LOG.debug("r_diagonale berechnet aus A und Umfang");
			} else if (!Double.isNaN(r_seiteB) && !Double.isNaN(r_umfang)
					&& Math.sqrt(Math.pow(r_seiteB, 2) + Math.pow((r_umfang / 2.0) - r_seiteB, 2)) > 0) {
				r_diagonale = Math.sqrt(Math.pow(r_seiteB, 2) + Math.pow((r_umfang / 2.0) - r_seiteB, 2));
				LOG.debug("r_diagonale berechnet aus B und Umfang");
			} else {
				r_diagonale = Double.NaN;
				LOG.debug("r_diagonale konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("r_diagonale war gegeben.");
		}

		LOG.debug("<======= Berechnung Rechteck Ende =======>");
		LOG.debug("");

		// 3. Set final values into Bean
		LOG.debug("<======= Setze Finale Werte in RechteckBean Start =======>");
		rechteck.setR_seiteA(r_seiteA);
		rechteck.setR_seiteB(r_seiteB);
		rechteck.setR_flaecheninhalt(r_flaecheninhalt);
		rechteck.setR_umfang(r_umfang);
		rechteck.setR_diagonale(r_diagonale);

		LOG.debug("r_seiteA" + r_seiteA);
		LOG.debug("r_seiteB" + r_seiteB);
		LOG.debug("r_flaecheninhalt" + r_flaecheninhalt);
		LOG.debug("r_umfang" + r_umfang);
		LOG.debug("r_diagonale" + r_diagonale);
		LOG.debug("<======= Setze Finale Werte in RechteckBean Ende =======>");

		// 4. Set Formatter
		LOG.debug("Setting DecimalFormat into Rechteck bean...");
		rechteck.setDecimalFormat(getDecimalFormat(decimalPlaces));

		LOG.debug("berechneRechteck() method finished.");
	}

	// --- Trapez ---
	public void berechneTrapez(HttpServletRequest request, HttpServletResponse response, Trapez trapez)
			throws ServletException, IOException {
		LOG.debug("berechneTrapez() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		// 1. Parse Inputs & Set Initial Bean Values (UNSAFE PARSING)
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

		// 2. Calculation Logic
		LOG.debug("<======= Berechnung Trapez Start =======>");

		// --- Berechne t_seiteA ---
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

		// --- Berechne t_seiteB ---
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

		// --- Berechne t_seiteC ---
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

		// --- Berechne t_seiteD ---
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

		// --- Berechne t_hoehe ---
		if (Double.isNaN(t_hoehe)) {
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

		// --- Berechne t_flaecheninhalt ---
		if (Double.isNaN(t_flaecheninhalt)) {
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

		// --- Berechne t_umfang ---
		if (Double.isNaN(t_umfang)) {
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

		// 3. Set final values into Bean
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

		// 4. Set Formatter
		LOG.debug("Setting DecimalFormat into Trapez bean...");
		trapez.setDecimalFormat(getDecimalFormat(decimalPlaces));

		LOG.debug("berechneTrapez() method finished.");
	}

	// --- Parallelogramm ---
	public void berechneParallelogramm(HttpServletRequest request, HttpServletResponse response,
			Parallelogramm parallelogramm) throws ServletException, IOException {
		LOG.debug("berechneParallelogramm() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		// 1. Parse Inputs & Set Initial Bean Values (UNSAFE PARSING)
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

		// 2. Calculation Logic
		LOG.debug("<======= Berechnung Parallelogramm Start =======>");

		// --- Berechne pa_seiteA ---
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

		// --- Berechne pa_seiteB ---
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

		// --- Berechne pa_hoeheA ---
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

		// --- Berechne pa_flaecheninhalt ---
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

		// --- Berechne pa_umfang ---
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

		// 3. Set final values into Bean
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

		// 4. Set Formatter
		LOG.debug("Setting DecimalFormat into Parallelogramm bean...");
		parallelogramm.setDecimalFormat(getDecimalFormat(decimalPlaces));

		LOG.debug("berechneParallelogramm() method finished.");
	}

	// --- Pentagon ---
	public void berechnePentagon(HttpServletRequest request, HttpServletResponse response, Pentagon pentagon)
			throws ServletException, IOException {
		LOG.debug("berechnePentagon() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		// 1. Parse Inputs & Set Initial Bean Values (UNSAFE PARSING)
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

		// 2. Calculation Logic
		LOG.debug("<======= Berechnung Pentagon Start =======>");

		// --- Berechne pe_seite ---
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

		// --- Berechne pe_umfang ---
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

		// --- Berechne pe_radiusInkreis ---
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

		// --- Berechne pe_radiusUmkreis ---
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

		// --- Berechne pe_flaecheninhalt ---
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

		// 3. Set final values into Bean
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

		// 4. Set Formatter
		LOG.debug("Setting DecimalFormat into Pentagon bean...");
		pentagon.setDecimalFormat(getDecimalFormat(decimalPlaces));

		LOG.debug("berechnePentagon() method finished.");
	}

	// --- Hexagon ---
	public void berechneHexagon(HttpServletRequest request, HttpServletResponse response, Hexagon hexagon)
			throws ServletException, IOException {
		LOG.debug("berechneHexagon() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		// 1. Parse Inputs & Set Initial Bean Values (UNSAFE PARSING)
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

		// 2. Calculation Logic
		LOG.debug("<======= Berechnung Hexagon Start =======>");
		final double SQRT3 = Math.sqrt(3.0);

		// --- Berechne h_seite ---
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

		// --- Berechne h_umfang ---
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

		// --- Berechne h_radiusInkreis ---
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

		// --- Berechne h_radiusUmkreis ---
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

		// --- Berechne h_flaecheninhalt ---
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

		// 3. Set final values into Bean
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

		// 4. Set Formatter
		LOG.debug("Setting DecimalFormat into Hexagon bean...");
		hexagon.setDecimalFormat(getDecimalFormat(decimalPlaces));

		LOG.debug("berechneHexagon() method finished.");
	}

	// --- Octagon ---
	public void berechneOctagon(HttpServletRequest request, HttpServletResponse response, Octagon octagon)
			throws ServletException, IOException {
		LOG.debug("berechneOctagon() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		// 1. Parse Inputs & Set Initial Bean Values (UNSAFE PARSING)
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

		// 2. Calculation Logic
		LOG.debug("<======= Berechnung Octagon Start =======>");
		final double SQRT2 = Math.sqrt(2.0);
		final double TAN_PI_8 = Math.tan(Math.PI / 8.0);
		final double SIN_PI_8 = Math.sin(Math.PI / 8.0);
		final double COS_PI_8 = Math.cos(Math.PI / 8.0);

		// --- Berechne o_seite ---
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

		// --- Berechne o_umfang ---
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

		// --- Berechne o_radiusInkreis ---
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

		// --- Berechne o_radiusUmkreis ---
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

		// --- Berechne o_flaecheninhalt ---
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

		// 3. Set final values into Bean
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

		// 4. Set Formatter
		LOG.debug("Setting DecimalFormat into Octagon bean...");
		octagon.setDecimalFormat(getDecimalFormat(decimalPlaces));

		LOG.debug("berechneOctagon() method finished.");
	}

	// --- Quader ---
	public void berechneQuader(HttpServletRequest request, HttpServletResponse response, Quader quader)
			throws ServletException, IOException {
		LOG.debug("berechneQuader() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		// 1. Parse Inputs & Set Initial Bean Values (UNSAFE PARSING)
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

		// 2. Calculation Logic
		LOG.debug("<======= Berechnung Quader Start =======>");

		// --- Berechne q_kanteA ---
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

		// --- Berechne q_kanteB ---
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

		// --- Berechne q_kanteC ---
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

		// --- Berechne q_volumen ---
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

		// --- Berechne q_grundflaeche (A*B) ---
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

		// --- Berechne q_mantelflaeche (Height C) ---
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

		// --- Berechne q_oberflaeche ---
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

		// --- Berechne q_raumdiagonale ---
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

		// 3. Set final values into Bean
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

		// 4. Set Formatter
		LOG.debug("Setting DecimalFormat into Quader bean...");
		quader.setDecimalFormat(getDecimalFormat(decimalPlaces));

		LOG.debug("berechneQuader() method finished.");
	}

	// --- Würfel (Cube) ---
	public void berechneWuerfel(HttpServletRequest request, HttpServletResponse response, Wuerfel wuerfel)
			throws ServletException, IOException {
		LOG.debug("berechneWuerfel() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		// 1. Parse Inputs & Set Initial Bean Values (UNSAFE PARSING)
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

		// 2. Calculation Logic
		LOG.debug("<======= Berechnung Würfel Start =======>");
		final double SQRT3 = Math.sqrt(3.0);

		// --- Berechne w_kante ---
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

		// --- Berechne w_volumen ---
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

			// --- Berechne w_grundflaeche ---
			if (w_grundflaeche == 0) {
				LOG.debug("Versuche Berechnung der w_grundflaeche");
				if (!Double.isNaN(w_kante) && Math.pow(w_kante, 2) != 0) {
					w_grundflaeche = Math.pow(w_kante, 2);
					LOG.debug("w_grundflaeche berechnet mit: Kante^2");
				} else if (!Double.isNaN(w_volumen) && w_volumen >= 0 && !Double.isNaN(Math.cbrt(w_volumen))
						&& Math.pow(Math.cbrt(w_volumen), 2) != 0) {
					w_grundflaeche = Math.pow(Math.cbrt(w_volumen), 2);
					LOG.debug("w_grundflaeche berechnet aus Volumen");
				} else if (!Double.isNaN(w_oberflaeche) && (w_oberflaeche / 6.0) >= 0) {
					w_grundflaeche = w_oberflaeche / 6.0;
					LOG.debug("w_grundflaeche berechnet mit: Oberfläche/6");
				} else if (!Double.isNaN(w_raumdiagonale) && w_raumdiagonale > 0
						&& !Double.isNaN(w_raumdiagonale / SQRT3) && Math.pow(w_raumdiagonale / SQRT3, 2) != 0) {
					w_grundflaeche = Math.pow(w_raumdiagonale / SQRT3, 2);
					LOG.debug("w_grundflaeche berechnet aus Raumdiagonale");
				} else if (!Double.isNaN(w_mantelflaeche) && (w_mantelflaeche / 4.0) >= 0) {
					w_grundflaeche = w_mantelflaeche / 4.0;
					LOG.debug("w_grundflaeche berechnet mit: Mantelfläche/4");
				} else {
					w_grundflaeche = Double.NaN;
					LOG.debug("w_grundflaeche konnte nicht berechnet werden, bleibt NaN.");
				}
			} else {
				LOG.debug("w_grundflaeche war gegeben.");
			}

			// --- Berechne w_mantelflaeche ---
			if (w_mantelflaeche == 0) {
				LOG.debug("Versuche Berechnung der w_mantelflaeche");
				if (!Double.isNaN(w_kante) && (4 * Math.pow(w_kante, 2)) != 0) {
					w_mantelflaeche = 4 * Math.pow(w_kante, 2);
					LOG.debug("w_mantelflaeche berechnet mit: 4 * Kante^2");
				} else if (!Double.isNaN(w_volumen) && w_volumen >= 0 && !Double.isNaN(Math.cbrt(w_volumen))
						&& (4 * Math.pow(Math.cbrt(w_volumen), 2)) != 0) {
					w_mantelflaeche = 4 * Math.pow(Math.cbrt(w_volumen), 2);
					LOG.debug("w_mantelflaeche berechnet aus Volumen");
				} else if (!Double.isNaN(w_oberflaeche) && (4 * (w_oberflaeche / 6.0)) >= 0) {
					w_mantelflaeche = 4 * (w_oberflaeche / 6.0);
					LOG.debug("w_mantelflaeche berechnet mit: 4 * (Oberfläche/6)");
				} else if (!Double.isNaN(w_raumdiagonale) && w_raumdiagonale > 0
						&& !Double.isNaN(w_raumdiagonale / SQRT3) && (4 * Math.pow(w_raumdiagonale / SQRT3, 2)) != 0) {
					w_mantelflaeche = 4 * Math.pow(w_raumdiagonale / SQRT3, 2);
					LOG.debug("w_mantelflaeche berechnet aus Raumdiagonale");
				}
			} else if (!Double.isNaN(w_grundflaeche) && (4 * w_grundflaeche) >= 0) {
				w_mantelflaeche = 4 * w_grundflaeche;
				LOG.debug("w_mantelflaeche berechnet mit: 4 * Grundfläche");
			} else {
				w_mantelflaeche = Double.NaN;
				LOG.debug("w_mantelflaeche konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("w_mantelflaeche war gegeben.");
		}

		// --- Berechne w_oberflaeche ---
		if (w_oberflaeche == 0) {
			LOG.debug("Versuche Berechnung der w_oberflaeche");
			if (!Double.isNaN(w_kante) && (6 * Math.pow(w_kante, 2)) != 0) {
				w_oberflaeche = 6 * Math.pow(w_kante, 2);
				LOG.debug("w_oberflaeche berechnet mit: 6 * Kante^2");
			} else if (!Double.isNaN(w_volumen) && w_volumen >= 0 && !Double.isNaN(Math.cbrt(w_volumen))
					&& (6 * Math.pow(Math.cbrt(w_volumen), 2)) != 0) {
				w_oberflaeche = 6 * Math.pow(Math.cbrt(w_volumen), 2);
				LOG.debug("w_oberflaeche berechnet aus Volumen");
			} else if (!Double.isNaN(w_raumdiagonale) && w_raumdiagonale > 0 && !Double.isNaN(w_raumdiagonale / SQRT3)
					&& (6 * Math.pow(w_raumdiagonale / SQRT3, 2)) != 0) {
				w_oberflaeche = 6 * Math.pow(w_raumdiagonale / SQRT3, 2);
				LOG.debug("w_oberflaeche berechnet aus Raumdiagonale");
			} else if (!Double.isNaN(w_grundflaeche) && (6 * w_grundflaeche) >= 0) {
				w_oberflaeche = 6 * w_grundflaeche;
				LOG.debug("w_oberflaeche berechnet mit: 6 * Grundfläche");
			} else if (!Double.isNaN(w_mantelflaeche) && (6 * (w_mantelflaeche / 4.0)) >= 0) {
				w_oberflaeche = 6 * (w_mantelflaeche / 4.0);
				LOG.debug("w_oberflaeche berechnet mit: 6 * (Mantelfläche/4)");
			} else {
				w_oberflaeche = Double.NaN;
				LOG.debug("w_oberflaeche konnte nicht berechnet werden, bleibt NaN.");
			}
		} else {
			LOG.debug("w_oberflaeche war gegeben.");
		}

		// --- Berechne w_raumdiagonale ---
		if (w_raumdiagonale == 0) {
			LOG.debug("Versuche Berechnung der w_raumdiagonale");
			if (!Double.isNaN(w_kante) && (w_kante * SQRT3) > 0) {
				w_raumdiagonale = w_kante * SQRT3;
				LOG.debug("w_raumdiagonale berechnet mit: Kante * sqrt(3)");
			} else if (!Double.isNaN(w_volumen) && w_volumen >= 0) {
				if (!Double.isNaN(Math.cbrt(w_volumen)) && (Math.cbrt(w_volumen) * SQRT3) > 0) {
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

			// --- Berechne w_umfang_seitenflaeche ---
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

			// --- Berechne w_summe_aller_kanten ---
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

			// 3. Set final values into Bean
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

			// 4. Set Formatter
			LOG.debug("Setting DecimalFormat into Wuerfel bean...");
			wuerfel.setDecimalFormat(getDecimalFormat(decimalPlaces));

			LOG.debug("berechneWuerfel() method finished.");
		}
	}

	// --- Prisma ---
	public void berechnePrisma(HttpServletRequest request, HttpServletResponse response, Prisma prisma)
			throws ServletException, IOException {
		LOG.debug("berechnePrisma() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("Decimal places: " + decimalPlaces);

		// 1. Parse Inputs & Set Initial Bean Values (UNSAFE PARSING)
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

		// --- Berechne pr_grundflaeche ---
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

		// --- Berechne pr_umfang ---
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

		// --- Berechne pr_hoehe ---
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

		// --- Berechne pr_volumen ---
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

		// --- Berechne pr_mantelflaeche ---
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

		// --- Berechne pr_oberflaeche ---
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

		// 3. Set final values into Bean
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

		// 4. Set Formatter
		LOG.debug("Setting DecimalFormat into Prisma bean...");
		prisma.setDecimalFormat(getDecimalFormat(decimalPlaces));

		LOG.debug("berechnePrisma() method finished.");
	}

	// --- Helper method to forward request ---
	private void forwardToJsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/dreiecke.jsp"); // Correct JSP name
		dispatcher.forward(request, response);
	}

	public DecimalFormat getDecimalFormat(int decimalPlaces) {

		LOG.debug("getDecimalFormat called for decimalPlaces: {}", decimalPlaces); // Use parameterized logging

		String pattern;
		switch (decimalPlaces) {
		case 0:
			pattern = "0";
			break; // Use "0" for integer display
		case 1:
			pattern = "0.0";
			break;
		case 2:
			pattern = "0.00";
			break;
		case 3:
			pattern = "0.000";
			break;
		case 4:
			pattern = "0.0000";
			break;
		case 5:
			pattern = "0.00000";
			break;
		case 6:
			pattern = "0.000000";
			break;
		case 7:
			pattern = "0.0000000";
			break;
		case 8:
			pattern = "0.00000000";
			break;
		case 9:
			pattern = "0.000000000";
			break;
		case 10:
			pattern = "0.0000000000";
			break;
		default:
			pattern = "0.00";
			break; // Default to 2 decimal places with leading zero
		}

		// Explicitly set symbols for German locale (comma decimal separator)
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
		DecimalFormat df = new DecimalFormat(pattern, symbols);

		LOG.debug("Created DecimalFormat with pattern '{}' using German symbols", pattern);
		return df;
	}

}
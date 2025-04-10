package de.m4automotive.controller;

import java.io.IOException; // Importing IOException to handle input/output errors.

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; // Importing ServletException for handling servlet-related errors.
import javax.servlet.annotation.WebServlet; // Importing WebServlet annotation for mapping URLs to this servlet.
import javax.servlet.http.HttpServlet; // Importing HttpServlet class to create an HTTP servlet.
import javax.servlet.http.HttpServletRequest; // Importing HttpServletRequest for handling HTTP requests.
import javax.servlet.http.HttpServletResponse; // Importing HttpServletResponse for handling HTTP responses.

import org.apache.logging.log4j.LogManager; // Importing LogManager for logging.
import org.apache.logging.log4j.Logger; // Importing Logger interface for logging messages.

import de.m4automotive.model.Dreieck;

@WebServlet(name = "/DreieckServlet", urlPatterns = ("/dreieck"))
public class DreieckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger(DreieckServlet.class);

	int num = 0; // Counter for loop

	Dreieck dreieck = new Dreieck();

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
			action = "showForm";
		}

		LOG.debug("Action: " + action);

		switch (action) {
		case "berechneDreiecke":
			num++;
			try {
				berechneDreiecke(request, response);
			} catch (NumberFormatException e) {
				LOG.error("Error parsing number during calculation: " + e.getMessage(), e);
				request.setAttribute("errorMessage", "Ungültiges Zahlenformat in der Eingabe.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/dreiecke.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				LOG.error("Error during calculation: " + e.getMessage(), e);
				request.setAttribute("errorMessage", "Ein unerwarteter Fehler ist bei der Berechnung aufgetreten.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/dreiecke.jsp");
				dispatcher.forward(request, response);
			}
			break;
		case "showForm":
			RequestDispatcher dispatcher = request.getRequestDispatcher("/dreiecke.jsp");
			dispatcher.forward(request, response);
			break;
		default:
			LOG.warn("Unknown action: " + action);
			request.setAttribute("errorMessage", "Unbekannte Aktion: " + action);
			RequestDispatcher errorDispatcher = request.getRequestDispatcher("/dreiecke.jsp");
			errorDispatcher.forward(request, response);
			break;
		}
	}

	private void berechneDreiecke(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.debug("berechneDreiecke() method called");

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));

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
				if ((a + b > c) && (a + c > b) && (b + c > a)) {
					umfangDreieck = a + b + c;
				} else {
					umfangDreieck = Double.NaN; // Not a valid triangle
				}
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
			if (a > 1E-9 && b > 1E-9 && c > 1E-9 && !Double.isNaN(a) && !Double.isNaN(b) && !Double.isNaN(c)
					&& (a + b > c) && (a + c > b) && (b + c > a)) { // Check triangle inequality
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

		dreieck.setA(Math.round(a * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("a: " + a);
		dreieck.setB(Math.round(b * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("b: " + b);
		dreieck.setC(Math.round(c * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("c: " + c);
		dreieck.setUmfangDreieck(Math.round(umfangDreieck * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("umfangDreieck: " + umfangDreieck);
		dreieck.setFlaecheninhaltDreieck(
				Math.round(flaecheninhaltDreieck * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("flaecheninhaltDreieck: " + flaecheninhaltDreieck);
		dreieck.setHoeheA(Math.round(hoeheA * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("hoeheA: " + hoeheA);
		dreieck.setHoeheB(Math.round(hoeheB * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("hoeheB: " + hoeheB);
		dreieck.setHoeheC(Math.round(hoeheC * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("hoeheC: " + hoeheC);
		dreieck.setAlpha(Math.round(alpha * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("alpha: " + alpha);
		dreieck.setBeta(Math.round(beta * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("beta: " + beta);
		dreieck.setGamma(Math.round(gamma * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("gamma: " + gamma);

		request.setAttribute("dreieck", dreieck);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/dreiecke.jsp");
		dispatcher.forward(request, response);

	}

}
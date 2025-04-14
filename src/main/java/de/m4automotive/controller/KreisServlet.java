package de.m4automotive.controller;

import java.io.IOException; // Importing IOException to handle input/output errors.
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; // Importing ServletException for handling servlet-related errors.
import javax.servlet.annotation.WebServlet; // Importing WebServlet annotation for mapping URLs to this servlet.
import javax.servlet.http.HttpServlet; // Importing HttpServlet class to create an HTTP servlet.
import javax.servlet.http.HttpServletRequest; // Importing HttpServletRequest for handling HTTP requests.
import javax.servlet.http.HttpServletResponse; // Importing HttpServletResponse for handling HTTP responses.

import org.apache.logging.log4j.LogManager; // Importing LogManager for logging.
import org.apache.logging.log4j.Logger; // Importing Logger interface for logging messages.

import de.m4automotive.model.Kreis;

@WebServlet(name = "/KreisServlet", urlPatterns = ("/kreis"))
public class KreisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger(KreisServlet.class);

	private int decimalPlaces = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("KreisServlet doGet() called");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		LOG.debug("doGet() method called"); // Log the GET request.
		try {
			processRequest(request, response);
		} catch (ParseException e) {
			LOG.error("Error parsing date: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
		} catch (NumberFormatException e) {
			LOG.error("Error parsing number: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
		} catch (Exception e) {
			LOG.error("Unexpected error: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("KreisServlet doPost() called");

		try {
			processRequest(request, response);
		} catch (ParseException e) {
			LOG.error("Error parsing date: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
		} catch (NumberFormatException e) {
			LOG.error("Error parsing number: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
		} catch (Exception e) {
			LOG.error("Unexpected error: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		LOG.debug("processRequest() method called");

		String action = request.getParameter("action");
		if (action == null) {
			action = "default"; // or handle it appropriately
		}

		LOG.debug("Action: " + action);

		switch (action) {
		case "berechneKreise":
			berechneKreise(request, response);
			break;
		case "default":
			LOG.warn("Unknown action: " + action);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
			break;
		default:
			LOG.warn("Unknown action: " + action);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
		}
	}

	private void berechneKreise(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("berechneKreise() method called");

		Kreis kreis = new Kreis();

		decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		LOG.debug("DecimalPlaces: " + decimalPlaces);
		double radius1 = Double.parseDouble(request.getParameter("radius1"));
		LOG.debug("Radius1: " + radius1);
		double radius2 = Double.parseDouble(request.getParameter("radius2"));
		LOG.debug("Radius2: " + radius2);
		double durchmesser1 = Double.parseDouble(request.getParameter("durchmesser1"));
		LOG.debug("Durchmesser1: " + durchmesser1);
		double durchmesser2 = Double.parseDouble(request.getParameter("durchmesser2"));
		LOG.debug("Durchmesser2: " + durchmesser2);
		double umfang1 = Double.parseDouble(request.getParameter("umfang1"));
		LOG.debug("Umfang1: " + umfang1);
		double umfang2 = Double.parseDouble(request.getParameter("umfang2"));
		LOG.debug("Umfang2: " + umfang2);
		double flaecheninhalt1 = Double.parseDouble(request.getParameter("flaecheninhalt1"));
		LOG.debug("Flaecheninhalt1: " + flaecheninhalt1);
		double flaecheninhalt2 = Double.parseDouble(request.getParameter("flaecheninhalt2"));
		LOG.debug("Flaecheninhalt2: " + flaecheninhalt2);

		double kreisbogen1 = Double.parseDouble(request.getParameter("kreisbogen1"));
		LOG.debug("Kreisbogen1: " + kreisbogen1);
		double kreisbogen2 = Double.parseDouble(request.getParameter("kreisbogen2"));
		LOG.debug("Kreisbogen2: " + kreisbogen2);
		double kreisauschnitt1 = Double.parseDouble(request.getParameter("kreisausschnitt1"));
		LOG.debug("Kreisausschnitt1: " + kreisauschnitt1);
		double kreisauschnitt2 = Double.parseDouble(request.getParameter("kreisausschnitt2"));
		LOG.debug("Kreisausschnitt2: " + kreisauschnitt2);
		double alpha = Double.parseDouble(request.getParameter("alpha"));
		LOG.debug("Alpha: " + alpha);

		double hoehe1 = Double.parseDouble(request.getParameter("hoehe1"));
		LOG.debug("Hoehe1: " + hoehe1);
		double hoehe2 = Double.parseDouble(request.getParameter("hoehe2"));
		LOG.debug("Hoehe2: " + hoehe2);
		double grundflaecheZylinder1 = Double.parseDouble(request.getParameter("grundflaecheZylinder1"));
		LOG.debug("GrundflaecheZylinder1: " + grundflaecheZylinder1);
		double grundflaecheZylinder2 = Double.parseDouble(request.getParameter("grundflaecheZylinder2"));
		LOG.debug("GrundflaecheZylinder2: " + grundflaecheZylinder2);
		double mantelflaecheZylinder1 = Double.parseDouble(request.getParameter("mantelflaecheZylinder1"));
		LOG.debug("MantelflaecheZylinder1: " + mantelflaecheZylinder1);
		double mantelflaecheZylinder2 = Double.parseDouble(request.getParameter("mantelflaecheZylinder2"));
		LOG.debug("MantelflaecheZylinder2: " + mantelflaecheZylinder2);
		double oberflaecheZylinder1 = Double.parseDouble(request.getParameter("oberflaecheZylinder1"));
		LOG.debug("OberflaecheZylinder1: " + oberflaecheZylinder1);
		double oberflaecheZylinder2 = Double.parseDouble(request.getParameter("oberflaecheZylinder2"));
		LOG.debug("OberflaecheZylinder2: " + oberflaecheZylinder2);
		double volumenZylinder1 = Double.parseDouble(request.getParameter("volumenZylinder1"));
		LOG.debug("VolumenZylinder1: " + volumenZylinder1);
		double volumenZylinder2 = Double.parseDouble(request.getParameter("volumenZylinder2"));
		LOG.debug("VolumenZylinder2: " + volumenZylinder2);

		double grundflaecheKegel1 = Double.parseDouble(request.getParameter("grundflaecheKegel1"));
		LOG.debug("GrundflaecheKegel1: " + grundflaecheKegel1);
		double grundflaecheKegel2 = Double.parseDouble(request.getParameter("grundflaecheKegel2"));
		LOG.debug("GrundflaecheKegel2: " + grundflaecheKegel2);
		double mantelflaecheKegel1 = Double.parseDouble(request.getParameter("mantelflaecheKegel1"));
		LOG.debug("MantelflaecheKegel1: " + mantelflaecheKegel1);
		double mantelflaecheKegel2 = Double.parseDouble(request.getParameter("mantelflaecheKegel2"));
		LOG.debug("MantelflaecheKegel2: " + mantelflaecheKegel2);
		double oberflaecheKegel1 = Double.parseDouble(request.getParameter("oberflaecheKegel1"));
		LOG.debug("OberflaecheKegel1: " + oberflaecheKegel1);
		double oberflaecheKegel2 = Double.parseDouble(request.getParameter("oberflaecheKegel2"));
		LOG.debug("OberflaecheKegel2: " + oberflaecheKegel2);
		double volumenKegel1 = Double.parseDouble(request.getParameter("volumenKegel1"));
		LOG.debug("VolumenKegel1: " + volumenKegel1);
		double volumenKegel2 = Double.parseDouble(request.getParameter("volumenKegel2"));
		LOG.debug("VolumenKegel2: " + volumenKegel2);
		double seitenhoehe1 = Double.parseDouble(request.getParameter("seitenhoehe1"));
		LOG.debug("Seitenhoehe1: " + seitenhoehe1);
		double seitenhoehe2 = Double.parseDouble(request.getParameter("seitenhoehe2"));
		LOG.debug("Seitenhoehe2: " + seitenhoehe2);

		double oberflaecheKugel1 = Double.parseDouble(request.getParameter("oberflaecheKugel1"));
		LOG.debug("OberflaecheKugel1: " + oberflaecheKugel1);
		double oberflaecheKugel2 = Double.parseDouble(request.getParameter("oberflaecheKugel2"));
		LOG.debug("OberflaecheKugel2: " + oberflaecheKugel2);
		double volumenKugel1 = Double.parseDouble(request.getParameter("volumenKugel1"));
		LOG.debug("VolumenKugel1: " + volumenKugel1);
		double volumenKugel2 = Double.parseDouble(request.getParameter("volumenKugel2"));
		LOG.debug("VolumenKugel2: " + volumenKugel2);

		double flaecheninhaltGesamt = 0;
		double grundflaecheKegelGesamt = 0;
		double grundflaecheZylinderGesamt = 0;
		double mantelflaecheKegelGesamt = 0;
		double mantelflaecheZylinderGesamt = 0;
		double oberflaecheKegelGesamt = 0;
		double oberflaecheZylinderGesamt = 0;
		double volumenKegelGesamt = 0;
		double volumenZylinderGesamt = 0;
		double oberflaecheKugelGesamt = 0;
		double volumenKugelGesamt = 0;
		double kreisausschnittGesamt = 0;

		if (radius1 == 0) {
			if ((durchmesser1 / 2.0) != 0) {
				radius1 = durchmesser1 / 2.0;
			} else if (Math.sqrt(flaecheninhalt1 / Math.PI) != 0) {
				radius1 = Math.sqrt(flaecheninhalt1 / Math.PI);
			} else if (umfang1 / (2.0 * Math.PI) != 0) {
				radius1 = umfang1 / (2.0 * Math.PI);
			} else if ((kreisbogen1 * 360.0) / (alpha * 2.0 * Math.PI) != 0) {
				radius1 = (kreisbogen1 * 360.0) / (alpha * 2.0 * Math.PI);
			} else if (Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI)) != 0) {
				radius1 = Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI));
			} else if ((2.0 * kreisauschnitt1 / kreisbogen1) != 0) {
				radius1 = 2.0 * kreisauschnitt1 / kreisbogen1;
			} else if (Math.sqrt(oberflaecheKugel1 / (4.0 * Math.PI)) != 0) {
				radius1 = Math.sqrt(oberflaecheKugel1 / (4.0 * Math.PI));
			} else if (Math.cbrt(3.0 * volumenKugel1 / (4.0 * Math.PI)) != 0) {
				radius1 = Math.cbrt(3.0 * volumenKugel1 / (4.0 * Math.PI));
			} else if (Math.sqrt(grundflaecheZylinder1 / Math.PI) != 0) {
				radius1 = Math.sqrt(grundflaecheZylinder1 / Math.PI);
			} else if (Math.sqrt(grundflaecheKegel1 / Math.PI) != 0) {
				radius1 = Math.sqrt(grundflaecheKegel1 / Math.PI);
			} else if (mantelflaecheZylinder1 / (2.0 * Math.PI * hoehe1) != 0) {
				radius1 = mantelflaecheZylinder1 / (2.0 * Math.PI * hoehe1);
			} else if (Math.sqrt(volumenZylinder1 / (Math.PI * hoehe1)) != 0) {
				radius1 = Math.sqrt(volumenZylinder1 / (Math.PI * hoehe1));
			} else if (mantelflaecheKegel1 / (Math.PI * seitenhoehe1) != 0) {
				radius1 = mantelflaecheKegel1 / (Math.PI * seitenhoehe1);
			} else if (Math.sqrt(Math.pow(seitenhoehe1, 2) - Math.pow(hoehe1, 2.0)) != 0) {
				radius1 = Math.sqrt(Math.pow(seitenhoehe1, 2) - Math.pow(hoehe1, 2.0));
			} else if (Math.sqrt(3.0 * volumenKegel1 / (Math.PI * hoehe1)) != 0) {
				radius1 = Math.sqrt(3.0 * volumenKegel1 / (Math.PI * hoehe1));
			} else if ((kreisbogen1 * 180.0) / (alpha * Math.PI) != 0) {
				radius1 = (kreisbogen1 * 180.0) / (alpha * Math.PI);
			} else if (Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI)) != 0) {
				radius1 = Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI));
			} else {
				radius1 = Double.NaN;
			}
		}

		if (radius2 == 0) {
			if ((durchmesser2 / 2.0) != 0) {
				radius2 = durchmesser2 / 2.0;
			} else if (Math.sqrt(flaecheninhalt2 / Math.PI) != 0) {
				radius2 = Math.sqrt(flaecheninhalt2 / Math.PI);
			} else if (umfang2 / (2.0 * Math.PI) != 0) {
				radius2 = umfang2 / (2.0 * Math.PI);
			} else if ((kreisbogen2 * 360.0) / (alpha * 2.0 * Math.PI) != 0) {
				radius2 = (kreisbogen2 * 360.0) / (alpha * 2.0 * Math.PI);
			} else if (Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI)) != 0) {
				radius2 = Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI));
			} else if ((2.0 * kreisauschnitt2 / kreisbogen2) != 0) {
				radius2 = 2.0 * kreisauschnitt2 / kreisbogen2;
			} else if (Math.sqrt(oberflaecheKugel2 / (4.0 * Math.PI)) != 0) {
				radius2 = Math.sqrt(oberflaecheKugel2 / (4.0 * Math.PI));
			} else if (Math.cbrt(3.0 * volumenKugel2 / (4.0 * Math.PI)) != 0) {
				radius2 = Math.cbrt(3.0 * volumenKugel2 / (4.0 * Math.PI));
			} else if (Math.sqrt(grundflaecheZylinder2 / Math.PI) != 0) {
				radius2 = Math.sqrt(grundflaecheZylinder2 / Math.PI);
			} else if (Math.sqrt(grundflaecheKegel2 / Math.PI) != 0) {
				radius2 = Math.sqrt(grundflaecheKegel2 / Math.PI);
			} else if (mantelflaecheZylinder2 / (2.0 * Math.PI * hoehe2) != 0) {
				radius2 = mantelflaecheZylinder2 / (2.0 * Math.PI * hoehe2);
			} else if (Math.sqrt(volumenZylinder2 / (Math.PI * hoehe2)) != 0) {
				radius2 = Math.sqrt(volumenZylinder2 / (Math.PI * hoehe2));
			} else if (mantelflaecheKegel2 / (Math.PI * seitenhoehe2) != 0) {
				radius2 = mantelflaecheKegel2 / (Math.PI * seitenhoehe2);
			} else if (Math.sqrt(Math.pow(seitenhoehe2, 2) - Math.pow(hoehe2, 2.0)) != 0) {
				radius2 = Math.sqrt(Math.pow(seitenhoehe2, 2) - Math.pow(hoehe2, 2.0));
			} else if (Math.sqrt(3.0 * volumenKegel2 / (Math.PI * hoehe2)) != 0) {
				radius2 = Math.sqrt(3.0 * volumenKegel2 / (Math.PI * hoehe2));
			} else if ((kreisbogen2 * 180.0) / (alpha * Math.PI) != 0) {
				radius2 = (kreisbogen2 * 180.0) / (alpha * Math.PI);
			} else if (Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI)) != 0) {
				radius2 = Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI));
			} else {
				radius2 = Double.NaN;
			}
		}

		if (durchmesser1 == 0) {
			if (2.0 * radius1 != 0) {
				durchmesser1 = 2.0 * radius1;
			} else if (2.0 * Math.sqrt(flaecheninhalt1 / Math.PI) != 0) {
				durchmesser1 = 2.0 * Math.sqrt(flaecheninhalt1 / Math.PI);
			} else if (umfang1 / Math.PI != 0) {
				durchmesser1 = umfang1 / Math.PI;
			} else if ((kreisbogen1 * 360.0) / (alpha * Math.PI) != 0) {
				durchmesser1 = (kreisbogen1 * 360.0) / (alpha * Math.PI);
			} else if (2.0 * Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI)) != 0) {
				durchmesser1 = 2.0 * Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI));
			} else if (4.0 * kreisauschnitt1 / kreisbogen1 != 0) {
				durchmesser1 = 4.0 * kreisauschnitt1 / kreisbogen1;
			} else if (2.0 * Math.sqrt(oberflaecheKugel1 / (4.0 * Math.PI)) != 0) {
				durchmesser1 = 2.0 * Math.sqrt(oberflaecheKugel1 / (4.0 * Math.PI));
			} else if (2.0 * Math.cbrt(3.0 * volumenKugel1 / (4.0 * Math.PI)) != 0) {
				durchmesser1 = 2.0 * Math.cbrt(3.0 * volumenKugel1 / (4.0 * Math.PI));
			} else {
				durchmesser1 = Double.NaN;
			}
		}

		if (durchmesser2 == 0) {
			if (2.0 * radius2 != 0) {
				durchmesser2 = 2.0 * radius2;
			} else if (2.0 * Math.sqrt(flaecheninhalt2 / Math.PI) != 0) {
				durchmesser2 = 2.0 * Math.sqrt(flaecheninhalt2 / Math.PI);
			} else if (umfang2 / Math.PI != 0) {
				durchmesser2 = umfang2 / Math.PI;
			} else if ((kreisbogen2 * 360.0) / (alpha * Math.PI) != 0) {
				durchmesser2 = (kreisbogen2 * 360.0) / (alpha * Math.PI);
			} else if (2.0 * Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI)) != 0) {
				durchmesser2 = 2.0 * Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI));
			} else if (4.0 * kreisauschnitt2 / kreisbogen2 != 0) {
				durchmesser2 = 4.0 * kreisauschnitt2 / kreisbogen2;
			} else if (2.0 * Math.sqrt(oberflaecheKugel2 / (4.0 * Math.PI)) != 0) {
				durchmesser2 = 2.0 * Math.sqrt(oberflaecheKugel2 / (4.0 * Math.PI));
			} else if (2.0 * Math.cbrt(3.0 * volumenKugel2 / (4.0 * Math.PI)) != 0) {
				durchmesser2 = 2.0 * Math.cbrt(3.0 * volumenKugel2 / (4.0 * Math.PI));
			} else {
				durchmesser2 = Double.NaN;
			}
		}

		if (umfang1 == 0) {
			if (2.0 * Math.PI * radius1 != 0) {
				umfang1 = 2.0 * Math.PI * radius1;
			} else if (Math.PI * durchmesser1 != 0) {
				umfang1 = Math.PI * durchmesser1;
			} else if (Math.sqrt(4.0 * Math.PI * flaecheninhalt1) != 0) {
				umfang1 = Math.sqrt(4.0 * Math.PI * flaecheninhalt1);
			} else if ((kreisbogen1 * 360.0) / alpha != 0) {
				umfang1 = (kreisbogen1 * 360.0) / alpha;
			} else if (mantelflaecheZylinder1 / hoehe1 != 0) {
				umfang1 = mantelflaecheZylinder1 / hoehe1;
			} else {
				umfang1 = Double.NaN;
			}
		}

		if (umfang2 == 0) {
			if (2.0 * Math.PI * radius2 != 0) {
				umfang2 = 2.0 * Math.PI * radius2;
			} else if (Math.PI * durchmesser2 != 0) {
				umfang2 = Math.PI * durchmesser2;
			} else if (Math.sqrt(4.0 * Math.PI * flaecheninhalt2) != 0) {
				umfang2 = Math.sqrt(4.0 * Math.PI * flaecheninhalt2);
			} else if ((kreisbogen2 * 360.0) / alpha != 0) {
				umfang2 = (kreisbogen2 * 360.0) / alpha;
			} else if (mantelflaecheZylinder2 / hoehe2 != 0) {
				umfang2 = mantelflaecheZylinder2 / hoehe2;
			} else {
				umfang2 = Double.NaN;
			}
		}

		if (flaecheninhalt1 == 0) {
			if (Math.PI * Math.pow(radius1, 2.0) != 0) {
				flaecheninhalt1 = Math.PI * Math.pow(radius1, 2.0);
			} else if (Math.PI * Math.pow(durchmesser1 / 2.0, 2.0) != 0) {
				flaecheninhalt1 = Math.PI * Math.pow(durchmesser1 / 2.0, 2);
			} else if (Math.PI / 4.0 * Math.pow(durchmesser1, 2.0) != 0) {
				flaecheninhalt1 = Math.PI / 4.0 * Math.pow(durchmesser1, 2);
			} else if ((umfang1 * radius1) / 2.0 != 0) {
				flaecheninhalt1 = (umfang1 * radius1) / 2.0;
			} else if (Math.pow(umfang1, 2.0) / (4.0 * Math.PI) != 0) {
				flaecheninhalt1 = Math.pow(umfang1, 2.0) / (4.0 * Math.PI);
			} else if (grundflaecheZylinder1 != 0) {
				flaecheninhalt1 = grundflaecheZylinder1;
			} else if (grundflaecheKegel1 != 0) {
				flaecheninhalt1 = grundflaecheKegel1;
			} else if ((kreisauschnitt1 * 360.0) / alpha != 0) {
				flaecheninhalt1 = (kreisauschnitt1 * 360.0) / alpha;
			} else {
				flaecheninhalt1 = Double.NaN;
			}
		}

		if (flaecheninhalt2 == 0) {
			if (Math.PI * Math.pow(radius2, 2) != 0) {
				flaecheninhalt2 = Math.PI * Math.pow(radius2, 2);
			} else if (Math.PI * Math.pow(durchmesser2 / 2.0, 2) != 0) {
				flaecheninhalt2 = Math.PI * Math.pow(durchmesser2 / 2.0, 2);
			} else if (Math.PI / 4.0 * Math.pow(durchmesser2, 2) != 0) {
				flaecheninhalt2 = Math.PI / 4.0 * Math.pow(durchmesser2, 2);
			} else if ((umfang2 * radius2) / 2.0 != 0) {
				flaecheninhalt2 = (umfang2 * radius2) / 2.0;
			} else if (Math.pow(umfang2, 2) / (4.0 * Math.PI) != 0) {
				flaecheninhalt2 = Math.pow(umfang2, 2) / (4.0 * Math.PI);
			} else if (grundflaecheZylinder2 != 0) {
				flaecheninhalt2 = grundflaecheZylinder2;
			} else if (grundflaecheKegel2 != 0) {
				flaecheninhalt2 = grundflaecheKegel2;
			} else if ((kreisauschnitt2 * 360.0) / alpha != 0) {
				flaecheninhalt2 = (kreisauschnitt2 * 360.0) / alpha;
			} else {
				flaecheninhalt2 = Double.NaN;
			}
		}

		if (flaecheninhaltGesamt == 0) {
			if ((flaecheninhalt2 - flaecheninhalt1) >= 0) {
				flaecheninhaltGesamt = flaecheninhalt2 - flaecheninhalt1;
			} else if ((flaecheninhalt1 - flaecheninhalt2) >= 0) {
				flaecheninhaltGesamt = flaecheninhalt1 - flaecheninhalt2;
			} else {
				flaecheninhaltGesamt = Double.NaN;
			}
		}

		// Kreisteile

		if (kreisbogen1 == 0) {
			if ((alpha / 360.0) * umfang1 != 0) {
				kreisbogen1 = (alpha / 360.0) * umfang1;
			} else if ((alpha / 360) * 2 * Math.PI * radius1 != 0) {
				kreisbogen1 = (alpha / 360) * 2 * Math.PI * radius1;
			} else if ((alpha / 360.0) * (Math.PI * durchmesser1) != 0) {
				kreisbogen1 = (alpha / 360.0) * (Math.PI * durchmesser1);
			} else if (2.0 * kreisauschnitt1 / radius1 != 0) {
				kreisbogen1 = 2.0 * kreisauschnitt1 / radius1;
			} else {
				kreisbogen1 = Double.NaN;
			}
		}

		if (kreisbogen2 == 0) {
			if ((alpha / 360.0) * umfang2 != 0) {
				kreisbogen2 = (alpha / 360.0) * umfang2;
			} else if ((alpha / 360) * 2 * Math.PI * radius2 != 0) {
				kreisbogen2 = (alpha / 360) * 2 * Math.PI * radius2;
			} else if ((alpha / 360.0) * (Math.PI * durchmesser2) != 0) {
				kreisbogen2 = (alpha / 360.0) * (Math.PI * durchmesser2);
			} else if (2.0 * kreisauschnitt2 / radius2 != 0) {
				kreisbogen2 = 2.0 * kreisauschnitt2 / radius2;
			} else {
				kreisbogen2 = Double.NaN;
			}
		}

		if (kreisauschnitt1 == 0) {
			if ((alpha / 360.0) * flaecheninhalt1 != 0) {
				kreisauschnitt1 = (alpha / 360.0) * flaecheninhalt1;
			} else if ((alpha / 360.0) * (Math.PI * Math.pow(radius1, 2)) != 0) {
				kreisauschnitt1 = (alpha / 360.0) * (Math.PI * Math.pow(radius1, 2));
			} else if ((kreisbogen1 * radius1) / 2.0 != 0) {
				kreisauschnitt1 = (kreisbogen1 * radius1) / 2.0;
			} else {
				kreisauschnitt1 = Double.NaN;
			}
		}

		if (kreisauschnitt2 == 0) {
			if ((alpha / 360.0) * flaecheninhalt2 != 0) {
				kreisauschnitt2 = (alpha / 360.0) * flaecheninhalt2;
			} else if ((alpha / 360.0) * (Math.PI * Math.pow(radius2, 2)) != 0) {
				kreisauschnitt2 = (alpha / 360.0) * (Math.PI * Math.pow(radius2, 2));
			} else if ((kreisbogen2 * radius2) / 2.0 != 0) {
				kreisauschnitt2 = (kreisbogen2 * radius2) / 2.0;
			} else {
				kreisauschnitt2 = Double.NaN;
			}
		}

		if (kreisausschnittGesamt == 0) {
			if ((kreisauschnitt2 - kreisauschnitt1) >= 0) {
				kreisausschnittGesamt = kreisauschnitt2 - kreisauschnitt1;
			} else if ((kreisauschnitt1 - kreisauschnitt2) >= 0) {
				kreisausschnittGesamt = kreisauschnitt1 - kreisauschnitt2;
			} else {
				kreisausschnittGesamt = Double.NaN;
			}
		}

		if (alpha == 0) {
			if ((kreisbogen1 / umfang1) * 360.0 != 0) {
				alpha = (kreisbogen1 / umfang1) * 360.0;
			} else if ((kreisauschnitt1 / flaecheninhalt1) * 360.0 != 0) {
				alpha = (kreisauschnitt1 / flaecheninhalt1) * 360.0;
			} else if ((kreisbogen2 / umfang2) * 360.0 != 0) {
				alpha = (kreisbogen2 / umfang2) * 360.0;
			} else if ((kreisauschnitt2 / flaecheninhalt2) * 360.0 != 0) {
				alpha = (kreisauschnitt2 / flaecheninhalt2) * 360.0;
			} else {
				alpha = Double.NaN;
			}
		}

		// Zylinder

		if (hoehe1 == 0) {
			if (mantelflaecheZylinder1 / (2.0 * Math.PI * radius1) != 0) {
				hoehe1 = mantelflaecheZylinder1 / (2.0 * Math.PI * radius1);
			} else if (volumenZylinder1 / grundflaecheZylinder1 != 0) {
				hoehe1 = volumenZylinder1 / grundflaecheZylinder1;
			} else if (volumenZylinder1 / (Math.PI * Math.pow(radius1, 2)) != 0) {
				hoehe1 = volumenZylinder1 / (Math.PI * Math.pow(radius1, 2));
			} else if ((oberflaecheZylinder1 / (2.0 * Math.PI * radius1)) - radius1 != 0) {
				hoehe1 = (oberflaecheZylinder1 / (2.0 * Math.PI * radius1)) - radius1;
			} else if (Math.sqrt(Math.pow(seitenhoehe1, 2) - Math.pow(radius1, 2)) != 0) {
				hoehe1 = Math.sqrt(Math.pow(seitenhoehe1, 2) - Math.pow(radius1, 2));
			} else if (3.0 * volumenKegel1 / grundflaecheKegel1 != 0) {
				hoehe1 = 3.0 * volumenKegel1 / grundflaecheKegel1;
			} else if (3.0 * volumenKegel1 / (Math.PI * Math.pow(radius1, 2)) != 0) {
				hoehe1 = 3.0 * volumenKegel1 / (Math.PI * Math.pow(radius1, 2));
			} else {
				hoehe1 = Double.NaN;
			}
		}

		if (hoehe2 == 0) {
			if (mantelflaecheZylinder2 / (2.0 * Math.PI * radius2) != 0) {
				hoehe2 = mantelflaecheZylinder2 / (2.0 * Math.PI * radius2);
			} else if (volumenZylinder2 / grundflaecheZylinder2 != 0) {
				hoehe2 = volumenZylinder2 / grundflaecheZylinder2;
			} else if (volumenZylinder2 / (Math.PI * Math.pow(radius2, 2)) != 0) {
				hoehe2 = volumenZylinder2 / (Math.PI * Math.pow(radius2, 2));
			} else if ((oberflaecheZylinder2 / (2.0 * Math.PI * radius2)) - radius2 != 0) {
				hoehe2 = (oberflaecheZylinder2 / (2.0 * Math.PI * radius2)) - radius2;
			} else if (Math.sqrt(Math.pow(seitenhoehe2, 2) - Math.pow(radius2, 2)) != 0) {
				hoehe2 = Math.sqrt(Math.pow(seitenhoehe2, 2) - Math.pow(radius2, 2));
			} else if (3.0 * volumenKegel2 / grundflaecheKegel2 != 0) {
				hoehe2 = 3.0 * volumenKegel2 / grundflaecheKegel2;
			} else if (3.0 * volumenKegel2 / (Math.PI * Math.pow(radius2, 2)) != 0) {
				hoehe2 = 3.0 * volumenKegel2 / (Math.PI * Math.pow(radius2, 2));
			} else {
				hoehe2 = Double.NaN;
			}
		}

		if (grundflaecheZylinder1 == 0) {
			if (Math.PI * Math.pow(radius1, 2) != 0) {
				grundflaecheZylinder1 = Math.PI * Math.pow(radius1, 2);
			} else if (flaecheninhalt1 != 0) {
				grundflaecheZylinder1 = flaecheninhalt1;
			} else if (volumenZylinder1 / hoehe1 != 0) {
				grundflaecheZylinder1 = volumenZylinder1 / hoehe1;
			} else if ((oberflaecheZylinder1 - mantelflaecheZylinder1) / 2.0 != 0) {
				grundflaecheZylinder1 = (oberflaecheZylinder1 - mantelflaecheZylinder1) / 2.0;
			} else {
				grundflaecheZylinder1 = Double.NaN;
			}
		}

		if (grundflaecheZylinder2 == 0) {
			if (Math.PI * Math.pow(radius2, 2) != 0) {
				grundflaecheZylinder2 = Math.PI * Math.pow(radius2, 2);
			} else if (flaecheninhalt2 != 0) {
				grundflaecheZylinder2 = flaecheninhalt2;
			} else if (volumenZylinder2 / hoehe2 != 0) {
				grundflaecheZylinder2 = volumenZylinder2 / hoehe2;
			} else if ((oberflaecheZylinder2 - mantelflaecheZylinder2) / 2.0 != 0) {
				grundflaecheZylinder2 = (oberflaecheZylinder2 - mantelflaecheZylinder2) / 2.0;
			} else {
				grundflaecheZylinder2 = Double.NaN;
			}
		}

		if (grundflaecheZylinderGesamt == 0) {
			if ((grundflaecheZylinder2 - grundflaecheZylinder1) >= 0) {
				grundflaecheZylinderGesamt = grundflaecheZylinder2 - grundflaecheZylinder1;
			} else if ((grundflaecheZylinder1 - grundflaecheZylinder2) >= 0) {
				grundflaecheZylinderGesamt = grundflaecheZylinder1 - grundflaecheZylinder2;
			} else {
				grundflaecheZylinderGesamt = Double.NaN;
			}
		}

		if (mantelflaecheZylinder1 == 0) {
			if (2.0 * Math.PI * radius1 * hoehe1 != 0) {
				mantelflaecheZylinder1 = 2.0 * Math.PI * radius1 * hoehe1;
			} else if (umfang1 * hoehe1 != 0) {
				mantelflaecheZylinder1 = umfang1 * hoehe1;
			} else if (oberflaecheZylinder1 - 2.0 * grundflaecheZylinder1 != 0) {
				mantelflaecheZylinder1 = oberflaecheZylinder1 - 2.0 * grundflaecheZylinder1;
			} else if (oberflaecheZylinder1 - 2.0 * (Math.PI * Math.pow(radius1, 2)) != 0) {
				mantelflaecheZylinder1 = oberflaecheZylinder1 - 2.0 * (Math.PI * Math.pow(radius1, 2));
			} else {
				mantelflaecheZylinder1 = Double.NaN;
			}
		}

		if (mantelflaecheZylinder2 == 0) {
			if (2.0 * Math.PI * radius2 * hoehe2 != 0) {
				mantelflaecheZylinder2 = 2.0 * Math.PI * radius2 * hoehe2;
			} else if (umfang2 * hoehe2 != 0) {
				mantelflaecheZylinder2 = umfang2 * hoehe2;
			} else if (oberflaecheZylinder2 - 2.0 * grundflaecheZylinder2 != 0) {
				mantelflaecheZylinder2 = oberflaecheZylinder2 - 2.0 * grundflaecheZylinder2;
			} else if (oberflaecheZylinder2 - 2.0 * (Math.PI * Math.pow(radius2, 2)) != 0) {
				mantelflaecheZylinder2 = oberflaecheZylinder2 - 2.0 * (Math.PI * Math.pow(radius2, 2));
			} else {
				mantelflaecheZylinder2 = Double.NaN;
			}
		}

		if (mantelflaecheZylinderGesamt == 0) {
			if ((mantelflaecheZylinder2 + mantelflaecheZylinder1) != 0) {
				mantelflaecheZylinderGesamt = mantelflaecheZylinder2 + mantelflaecheZylinder1;
			} else {
				mantelflaecheZylinderGesamt = Double.NaN;
			}
		}

		if (oberflaecheZylinder1 == 0) {
			if (2.0 * grundflaecheZylinder1 + mantelflaecheZylinder1 != 0) {
				oberflaecheZylinder1 = 2.0 * grundflaecheZylinder1 + mantelflaecheZylinder1;
			} else if (2.0 * (Math.PI * Math.pow(radius1, 2)) + (2.0 * Math.PI * radius1 * hoehe1) != 0) {
				oberflaecheZylinder1 = 2.0 * (Math.PI * Math.pow(radius1, 2)) + (2.0 * Math.PI * radius1 * hoehe1);
			} else if (2.0 * Math.PI * radius1 * (radius1 + hoehe1) != 0) {
				oberflaecheZylinder1 = 2.0 * Math.PI * radius1 * (radius1 + hoehe1);
			} else {
				oberflaecheZylinder1 = Double.NaN;
			}
		}

		if (oberflaecheZylinder2 == 0) {
			if (2.0 * grundflaecheZylinder2 + mantelflaecheZylinder2 != 0) {
				oberflaecheZylinder2 = 2.0 * grundflaecheZylinder2 + mantelflaecheZylinder2;
			} else if (2.0 * (Math.PI * Math.pow(radius2, 2)) + (2.0 * Math.PI * radius2 * hoehe2) != 0) {
				oberflaecheZylinder2 = 2.0 * (Math.PI * Math.pow(radius2, 2)) + (2.0 * Math.PI * radius2 * hoehe2);
			} else if (2.0 * Math.PI * radius2 * (radius2 + hoehe2) != 0) {
				oberflaecheZylinder2 = 2.0 * Math.PI * radius2 * (radius2 + hoehe2);
			} else {
				oberflaecheZylinder2 = Double.NaN;
			}
		}

		if (oberflaecheZylinderGesamt == 0) {
			if (mantelflaecheZylinderGesamt + 2.0 * grundflaecheZylinderGesamt != 0) {
				oberflaecheZylinderGesamt = mantelflaecheZylinderGesamt + 2.0 * grundflaecheZylinderGesamt;
			} else {
				oberflaecheZylinderGesamt = Double.NaN;
			}
		}

		if (volumenZylinder1 == 0) {
			if (grundflaecheZylinder1 * hoehe1 != 0) {
				volumenZylinder1 = grundflaecheZylinder1 * hoehe1;
			} else if (Math.PI * Math.pow(radius1, 2) * hoehe1 != 0) {
				volumenZylinder1 = Math.PI * Math.pow(radius1, 2) * hoehe1;
			} else {
				volumenZylinder1 = Double.NaN;
			}
		}

		if (volumenZylinder2 == 0) {
			if (grundflaecheZylinder2 * hoehe2 != 0) {
				volumenZylinder2 = grundflaecheZylinder2 * hoehe2;
			} else if (Math.PI * Math.pow(radius2, 2) * hoehe2 != 0) {
				volumenZylinder2 = Math.PI * Math.pow(radius2, 2) * hoehe2;
			} else {
				volumenZylinder2 = Double.NaN;
			}
		}

		if (volumenZylinderGesamt == 0) {
			if ((volumenZylinder2 - volumenZylinder1) >= 0) {
				volumenZylinderGesamt = volumenZylinder2 - volumenZylinder1;
			} else if ((volumenZylinder1 - volumenZylinder2) >= 0) {
				volumenZylinderGesamt = volumenZylinder1 - volumenZylinder2;
			} else {
				volumenZylinderGesamt = Double.NaN;
			}
		}

		// Kegel

		if (seitenhoehe1 == 0) {
			if (Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2)) != 0) {
				seitenhoehe1 = Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2));
			} else if (mantelflaecheKegel1 / (Math.PI * radius1) != 0) {
				seitenhoehe1 = mantelflaecheKegel1 / (Math.PI * radius1);
			} else if ((oberflaecheKegel1 / (Math.PI * radius1)) - radius1 != 0) {
				seitenhoehe1 = (oberflaecheKegel1 / (Math.PI * radius1)) - radius1;
			} else {
				seitenhoehe1 = Double.NaN;
			}
		}

		if (seitenhoehe2 == 0) {
			if (Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2)) != 0) {
				seitenhoehe2 = Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2));
			} else if (mantelflaecheKegel2 / (Math.PI * radius2) != 0) {
				seitenhoehe2 = mantelflaecheKegel2 / (Math.PI * radius2);
			} else if ((oberflaecheKegel2 / (Math.PI * radius2)) - radius2 != 0) {
				seitenhoehe2 = (oberflaecheKegel2 / (Math.PI * radius2)) - radius2;
			} else {
				seitenhoehe2 = Double.NaN;
			}
		}

		if (grundflaecheKegel1 == 0) {
			if (Math.PI * Math.pow(radius1, 2) != 0) {
				grundflaecheKegel1 = Math.PI * Math.pow(radius1, 2);
			} else if (flaecheninhalt1 != 0) {
				grundflaecheKegel1 = flaecheninhalt1;
			} else if (3.0 * volumenKegel1 / hoehe1 != 0) {
				grundflaecheKegel1 = 3.0 * volumenKegel1 / hoehe1;
			} else if (oberflaecheKegel1 - mantelflaecheKegel1 != 0) {
				grundflaecheKegel1 = oberflaecheKegel1 - mantelflaecheKegel1;
			} else {
				grundflaecheKegel1 = Double.NaN;
			}
		}

		if (grundflaecheKegel2 == 0) {
			if (Math.PI * Math.pow(radius2, 2) != 0) {
				grundflaecheKegel2 = Math.PI * Math.pow(radius2, 2);
			} else if (flaecheninhalt2 != 0) {
				grundflaecheKegel2 = flaecheninhalt2;
			} else if (3.0 * volumenKegel2 / hoehe2 != 0) {
				grundflaecheKegel2 = 3.0 * volumenKegel2 / hoehe2;
			} else if (oberflaecheKegel2 - mantelflaecheKegel2 != 0) {
				grundflaecheKegel2 = oberflaecheKegel2 - mantelflaecheKegel2;
			} else {
				grundflaecheKegel2 = Double.NaN;
			}
		}

		if (grundflaecheKegelGesamt == 0) {
			if ((grundflaecheKegel2 - grundflaecheKegel1) >= 0) {
				grundflaecheKegelGesamt = grundflaecheKegel2 - grundflaecheKegel1;
			} else if ((grundflaecheKegel1 - grundflaecheKegel2) >= 0) {
				grundflaecheKegelGesamt = grundflaecheKegel1 - grundflaecheKegel2;
			} else {
				grundflaecheKegelGesamt = Double.NaN;
			}
		}

		if (mantelflaecheKegel1 == 0) {
			if (Math.PI * radius1 * seitenhoehe1 != 0) {
				mantelflaecheKegel1 = Math.PI * radius1 * seitenhoehe1;
			} else if (Math.PI * radius1 * Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2)) != 0) {
				mantelflaecheKegel1 = Math.PI * radius1 * Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2));
			} else if (oberflaecheKegel1 - grundflaecheKegel1 != 0) {
				mantelflaecheKegel1 = oberflaecheKegel1 - grundflaecheKegel1;
			} else if (oberflaecheKegel1 - (Math.PI * Math.pow(radius1, 2)) != 0) {
				mantelflaecheKegel1 = oberflaecheKegel1 - (Math.PI * Math.pow(radius1, 2));
			} else {
				mantelflaecheKegel1 = Double.NaN;
			}
		}

		if (mantelflaecheKegel2 == 0) {
			if (Math.PI * radius2 * seitenhoehe2 != 0) {
				mantelflaecheKegel2 = Math.PI * radius2 * seitenhoehe2;
			} else if (Math.PI * radius2 * Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2)) != 0) {
				mantelflaecheKegel2 = Math.PI * radius2 * Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2));
			} else if (oberflaecheKegel2 - grundflaecheKegel2 != 0) {
				mantelflaecheKegel2 = oberflaecheKegel2 - grundflaecheKegel2;
			} else if (oberflaecheKegel2 - (Math.PI * Math.pow(radius2, 2)) != 0) {
				mantelflaecheKegel2 = oberflaecheKegel2 - (Math.PI * Math.pow(radius2, 2));
			} else {
				mantelflaecheKegel2 = Double.NaN;
			}
		}

		if (mantelflaecheKegelGesamt == 0) {
			if ((mantelflaecheKegel2 + mantelflaecheKegel1) != 0) {
				mantelflaecheKegelGesamt = mantelflaecheKegel2 + mantelflaecheKegel1;
			} else {
				mantelflaecheKegelGesamt = Double.NaN;
			}
		}

		if (oberflaecheKegel1 == 0) {
			if (grundflaecheKegel1 + mantelflaecheKegel1 != 0) {
				oberflaecheKegel1 = grundflaecheKegel1 + mantelflaecheKegel1;
			} else if (Math.PI * Math.pow(radius1, 2) + Math.PI * radius1 * seitenhoehe1 != 0) {
				oberflaecheKegel1 = Math.PI * Math.pow(radius1, 2) + Math.PI * radius1 * seitenhoehe1;
			} else if (Math.PI * radius1 * (radius1 + seitenhoehe1) != 0) {
				oberflaecheKegel1 = Math.PI * radius1 * (radius1 + seitenhoehe1);
			} else if (Math.PI * radius1 * (radius1 + Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2))) != 0) {
				oberflaecheKegel1 = Math.PI * radius1
						* (radius1 + Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2)));
			} else {
				oberflaecheKegel1 = Double.NaN;
			}
		}

		if (oberflaecheKegel2 == 0) {
			if (grundflaecheKegel2 + mantelflaecheKegel2 != 0) {
				oberflaecheKegel2 = grundflaecheKegel2 + mantelflaecheKegel2;
			} else if (Math.PI * Math.pow(radius2, 2) + Math.PI * radius2 * seitenhoehe2 != 0) {
				oberflaecheKegel2 = Math.PI * Math.pow(radius2, 2) + Math.PI * radius2 * seitenhoehe2;
			} else if (Math.PI * radius2 * (radius2 + seitenhoehe2) != 0) {
				oberflaecheKegel2 = Math.PI * radius2 * (radius2 + seitenhoehe2);
			} else if (Math.PI * radius2 * (radius2 + Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2))) != 0) {
				oberflaecheKegel2 = Math.PI * radius2
						* (radius2 + Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2)));
			} else {
				oberflaecheKegel2 = Double.NaN;
			}
		}

		if (oberflaecheKegelGesamt == 0) {
			if ((2 * grundflaecheKegelGesamt + mantelflaecheKegelGesamt) != 0) {
				oberflaecheKegelGesamt = 2 * grundflaecheKegelGesamt + mantelflaecheKegelGesamt;
			} else {
				oberflaecheKegelGesamt = Double.NaN;
			}
		}

		if (volumenKegel1 == 0) {
			if ((1.0 / 3.0) * grundflaecheKegel1 * hoehe1 != 0) {
				volumenKegel1 = (1.0 / 3.0) * grundflaecheKegel1 * hoehe1;
			} else if ((1.0 / 3.0) * Math.PI * Math.pow(radius1, 2) * hoehe1 != 0) {
				volumenKegel1 = (1.0 / 3.0) * Math.PI * Math.pow(radius1, 2) * hoehe1;
			} else if ((Math.PI / 3.0) * Math.pow(radius1, 2) * hoehe1 != 0) {
				volumenKegel1 = (Math.PI / 3.0) * Math.pow(radius1, 2) * hoehe1;
			} else {
				volumenKegel1 = Double.NaN;
			}
		}

		if (volumenKegel2 == 0) {
			if ((1.0 / 3.0) * grundflaecheKegel2 * hoehe2 != 0) {
				volumenKegel2 = (1.0 / 3.0) * grundflaecheKegel2 * hoehe2;
			} else if ((1.0 / 3.0) * Math.PI * Math.pow(radius2, 2) * hoehe2 != 0) {
				volumenKegel2 = (1.0 / 3.0) * Math.PI * Math.pow(radius2, 2) * hoehe2;
			} else if ((Math.PI / 3.0) * Math.pow(radius2, 2) * hoehe2 != 0) {
				volumenKegel2 = (Math.PI / 3.0) * Math.pow(radius2, 2) * hoehe2;
			} else {
				volumenKegel2 = Double.NaN;
			}
		}

		if (volumenKegelGesamt == 0) {
			if ((volumenKegel2 - volumenKegel1) >= 0) {
				volumenKegelGesamt = volumenKegel2 - volumenKegel1;
			} else if ((volumenKegel1 - volumenKegel2) >= 0) {
				volumenKegelGesamt = volumenKegel1 - volumenKegel2;
			} else {
				volumenKegelGesamt = Double.NaN;
			}
		}

		// Kugel

		if (oberflaecheKugel1 == 0) {
			if (4.0 * Math.PI * Math.pow(radius1, 2) != 0) {
				oberflaecheKugel1 = 4.0 * Math.PI * Math.pow(radius1, 2);
			} else if (Math.PI * Math.pow(durchmesser1, 2) != 0) {
				oberflaecheKugel1 = Math.PI * Math.pow(durchmesser1, 2);
			} else if (Math.pow(36.0 * Math.PI * Math.pow(volumenKugel1, 2), 1.0 / 3.0) != 0) {
				oberflaecheKugel1 = Math.pow(36.0 * Math.PI * Math.pow(volumenKugel1, 2), 1.0 / 3.0);
			} else if (Math.PI * Math.pow((6.0 * volumenKugel1 / Math.PI), 2.0 / 3.0) != 0) {
				oberflaecheKugel1 = Math.PI * Math.pow((6.0 * volumenKugel1 / Math.PI), 2.0 / 3.0);
			} else {
				oberflaecheKugel1 = Double.NaN;
			}
		}

		if (oberflaecheKugel2 == 0) {
			if (4.0 * Math.PI * Math.pow(radius2, 2) != 0) {
				oberflaecheKugel2 = 4.0 * Math.PI * Math.pow(radius2, 2);
			} else if (Math.PI * Math.pow(durchmesser2, 2) != 0) {
				oberflaecheKugel2 = Math.PI * Math.pow(durchmesser2, 2);
			} else if (Math.pow(36.0 * Math.PI * Math.pow(volumenKugel2, 2), 1.0 / 3.0) != 0) {
				oberflaecheKugel2 = Math.pow(36.0 * Math.PI * Math.pow(volumenKugel2, 2), 1.0 / 3.0);
			} else if (Math.PI * Math.pow((6.0 * volumenKugel2 / Math.PI), 2.0 / 3.0) != 0) {
				oberflaecheKugel2 = Math.PI * Math.pow((6.0 * volumenKugel2 / Math.PI), 2.0 / 3.0);
			} else {
				oberflaecheKugel2 = Double.NaN;
			}
		}

		if (oberflaecheKugelGesamt == 0) {
			if ((oberflaecheKugel2 + oberflaecheKugel1) != 0) {
				oberflaecheKugelGesamt = oberflaecheKugel2 + oberflaecheKugel1;
			} else {
				oberflaecheKugelGesamt = Double.NaN;
			}
		}

		if (volumenKugel1 == 0) {
			if ((4.0 / 3.0) * Math.PI * Math.pow(radius1, 3) != 0) {
				volumenKugel1 = (4.0 / 3.0) * Math.PI * Math.pow(radius1, 3);
			} else if ((1.0 / 6.0) * Math.PI * Math.pow(durchmesser1, 3) != 0) {
				volumenKugel1 = (1.0 / 6.0) * Math.PI * Math.pow(durchmesser1, 3);
			} else if ((1.0 / 6.0) * Math.sqrt(Math.pow(oberflaecheKugel1, 3) / Math.PI) != 0) {
				volumenKugel1 = (1.0 / 6.0) * Math.sqrt(Math.pow(oberflaecheKugel1, 3) / Math.PI);
			} else {
				volumenKugel1 = Double.NaN;
			}
		}

		if (volumenKugel2 == 0) {
			if ((4.0 / 3.0) * Math.PI * Math.pow(radius2, 3) != 0) {
				volumenKugel2 = (4.0 / 3.0) * Math.PI * Math.pow(radius2, 3);
			} else if ((1.0 / 6.0) * Math.PI * Math.pow(durchmesser2, 3) != 0) {
				volumenKugel2 = (1.0 / 6.0) * Math.PI * Math.pow(durchmesser2, 3);
			} else if ((1.0 / 6.0) * Math.sqrt(Math.pow(oberflaecheKugel2, 3) / Math.PI) != 0) {
				volumenKugel2 = (1.0 / 6.0) * Math.sqrt(Math.pow(oberflaecheKugel2, 3) / Math.PI);
			} else {
				volumenKugel2 = Double.NaN;
			}
		}

		if (volumenKugelGesamt == 0) {
			if ((volumenKugel2 - volumenKugel1) >= 0) {
				volumenKugelGesamt = volumenKugel2 - volumenKugel1;
			} else if ((volumenKugel1 - volumenKugel2) >= 0) {
				volumenKugelGesamt = volumenKugel1 - volumenKugel2;
			} else {
				volumenKugelGesamt = Double.NaN;
			}
		}

		// Vorraussetzungen

		final double epsilon = 1e-9; // Tolerance for double comparison

		// Helper function for validity check (non-error, non-NaN, non-Infinite)
		// Adjust this if your default/error values are different (e.g. -1)
		java.util.function.Predicate<Double> isValid = val -> !Double.isNaN(val) && !Double.isInfinite(val) && val >= 0;

		// Grundflächen (Kreisflächen)
		boolean grundflaechenGleich = isValid.test(flaecheninhalt1) && isValid.test(flaecheninhalt2)
				&& Math.abs(flaecheninhalt1 - flaecheninhalt2) < epsilon;
		if (grundflaechenGleich)
			LOG.debug("Grundflächen sind gleich.");

		// Zylinder Volumina
		boolean zylinderVoluminaGleich = isValid.test(volumenZylinder1) && isValid.test(volumenZylinder2)
				&& Math.abs(volumenZylinder1 - volumenZylinder2) < epsilon;
		if (zylinderVoluminaGleich)
			LOG.debug("Zylindervolumina sind gleich.");

		// Zylinder Grundflächen (Often same as Kreisflächen)
		boolean zylinderGrundflaechenGleich = isValid.test(grundflaecheZylinder1) && isValid.test(grundflaecheZylinder2)
				&& Math.abs(grundflaecheZylinder1 - grundflaecheZylinder2) < epsilon;
		if (zylinderGrundflaechenGleich && !grundflaechenGleich)
			LOG.debug("Zylindergrundflächen sind gleich (aber Kreisflächen nicht?)."); // Log potential inconsistency

		// Kegel Volumina
		boolean kegelVoluminaGleich = isValid.test(volumenKegel1) && isValid.test(volumenKegel2)
				&& Math.abs(volumenKegel1 - volumenKegel2) < epsilon;
		if (kegelVoluminaGleich)
			LOG.debug("Kegelvolumina sind gleich.");

		// Kegel Grundflächen (Often same as Kreisflächen)
		boolean kegelGrundflaechenGleich = isValid.test(grundflaecheKegel1) && isValid.test(grundflaecheKegel2)
				&& Math.abs(grundflaecheKegel1 - grundflaecheKegel2) < epsilon;
		if (kegelGrundflaechenGleich && !grundflaechenGleich)
			LOG.debug("Kegelgrundflächen sind gleich (aber Kreisflächen nicht?)."); // Log potential inconsistency

		// Kugel Volumina
		boolean kugelVoluminaGleich = isValid.test(volumenKugel1) && isValid.test(volumenKugel2)
				&& Math.abs(volumenKugel1 - volumenKugel2) < epsilon;
		if (kugelVoluminaGleich)
			LOG.debug("Kugelvolumina sind gleich.");

		// Kreisausschnitte
		boolean kreisausschnitteGleich = isValid.test(kreisauschnitt1) && isValid.test(kreisauschnitt2)
				&& Math.abs(kreisauschnitt1 - kreisauschnitt2) < epsilon;
		if (kreisausschnitteGleich)
			LOG.debug("Kreisausschnitte sind gleich.");

		kreis.setRadius1(radius1);
		kreis.setRadius2(radius2);
		kreis.setDurchmesser1(durchmesser1);
		kreis.setDurchmesser2(durchmesser2);
		kreis.setUmfang1(umfang1);
		kreis.setUmfang2(umfang2);
		kreis.setFlaecheninhalt1(flaecheninhalt1);
		kreis.setFlaecheninhalt2(flaecheninhalt2);
		kreis.setFlaecheninhaltGesamt(flaecheninhaltGesamt);

		// Kreisteile
		kreis.setKreisbogen1(kreisbogen1);
		kreis.setKreisbogen2(kreisbogen2);
		kreis.setKreisausschnitt1(kreisauschnitt1);
		kreis.setKreisausschnitt2(kreisauschnitt2);
		kreis.setKreisausschnittGesamt(kreisausschnittGesamt);
		kreis.setAlpha(alpha);

		// Zylinder
		kreis.setHoehe1(hoehe1);
		kreis.setHoehe2(hoehe2);
		kreis.setGrundflaecheZylinder1(grundflaecheZylinder1);
		kreis.setGrundflaecheZylinder2(grundflaecheZylinder2);
		kreis.setGrundflaecheZylinderGesamt(grundflaecheZylinderGesamt);
		kreis.setMantelflaecheZylinder1(mantelflaecheZylinder1);
		kreis.setMantelflaecheZylinder2(mantelflaecheZylinder2);
		kreis.setMantelflaecheZylinderGesamt(mantelflaecheZylinderGesamt);
		kreis.setOberflaecheZylinder1(oberflaecheZylinder1);
		kreis.setOberflaecheZylinder2(oberflaecheZylinder2);
		kreis.setOberflaecheZylinderGesamt(oberflaecheZylinderGesamt);
		kreis.setVolumenZylinder1(volumenZylinder1);
		kreis.setVolumenZylinder2(volumenZylinder2);
		kreis.setVolumenZylinderGesamt(volumenZylinderGesamt);

		// Kegel
		kreis.setSeitenhoehe1(seitenhoehe1);
		kreis.setSeitenhoehe2(seitenhoehe2);
		kreis.setGrundflaecheKegel1(grundflaecheKegel1);
		kreis.setGrundflaecheKegel2(grundflaecheKegel2);
		kreis.setGrundflaecheKegelGesamt(grundflaecheKegelGesamt);
		kreis.setMantelflaecheKegel1(mantelflaecheKegel1);
		kreis.setMantelflaecheKegel2(mantelflaecheKegel2);
		kreis.setMantelflaecheKegelGesamt(mantelflaecheKegelGesamt);
		kreis.setOberflaecheKegel1(oberflaecheKegel1);
		kreis.setOberflaecheKegel2(oberflaecheKegel2);
		kreis.setOberflaecheKegelGesamt(oberflaecheKegelGesamt);
		kreis.setVolumenKegel1(volumenKegel1);
		kreis.setVolumenKegel2(volumenKegel2);
		kreis.setVolumenKegelGesamt(volumenKegelGesamt);

		// Kugel
		kreis.setOberflaecheKugel1(oberflaecheKugel1);
		kreis.setOberflaecheKugel2(oberflaecheKugel2);
		kreis.setOberflaecheKugelGesamt(oberflaecheKugelGesamt);
		kreis.setVolumenKugel1(volumenKugel1);
		kreis.setVolumenKugel2(volumenKugel2);
		kreis.setVolumenKugelGesamt(volumenKugelGesamt);

		// Set boolean flags
		kreis.setGrundflaechenGleich(grundflaechenGleich);
		kreis.setZylinderVoluminaGleich(zylinderVoluminaGleich);
		kreis.setZylinderGrundflaechenGleich(zylinderGrundflaechenGleich);
		kreis.setKegelVoluminaGleich(kegelVoluminaGleich);
		kreis.setKegelGrundflaechenGleich(kegelGrundflaechenGleich);
		kreis.setKugelVoluminaGleich(kugelVoluminaGleich);
		kreis.setKreisausschnitteGleich(kreisausschnitteGleich);

		LOG.debug("radius1: " + radius1);
		LOG.debug("radius1: " + radius1);
		LOG.debug("durchmesser1: " + durchmesser1);
		LOG.debug("durchmesser2: " + durchmesser2);
		LOG.debug("umfang1: " + umfang1);
		LOG.debug("umfang2: " + umfang2);
		LOG.debug("flaecheninhalt1: " + flaecheninhalt1);
		LOG.debug("flaecheninhalt2: " + flaecheninhalt2);
		LOG.debug("kreisbogen1: " + kreisbogen1);
		LOG.debug("kreisbogen2: " + kreisbogen2);
		LOG.debug("kreisauschnitt1: " + kreisauschnitt1);
		LOG.debug("kreisauschnitt2: " + kreisauschnitt2);
		LOG.debug("alpha: " + alpha);
		LOG.debug("hoehe1: " + hoehe1);
		LOG.debug("hoehe2: " + hoehe2);
		LOG.debug("grundflaecheZylinder1: " + grundflaecheZylinder1);
		LOG.debug("grundflaecheZylinder2: " + grundflaecheZylinder2);
		LOG.debug("mantelflaecheZylinder1: " + mantelflaecheZylinder1);
		LOG.debug("mantelflaecheZylinder2: " + mantelflaecheZylinder2);
		LOG.debug("oberflaecheZylinder1: " + oberflaecheZylinder1);
		LOG.debug("oberflaecheZylinder2: " + oberflaecheZylinder2);
		LOG.debug("volumenZylinder1: " + volumenZylinder1);
		LOG.debug("volumenZylinder2: " + volumenZylinder2);
		LOG.debug("seitenhoehe1: " + seitenhoehe1);
		LOG.debug("seitenhoehe2: " + seitenhoehe2);
		LOG.debug("grundflaecheKegel1: " + grundflaecheKegel1);
		LOG.debug("grundflaecheKegel2: " + grundflaecheKegel2);
		LOG.debug("mantelflaecheKegel1: " + mantelflaecheKegel1);
		LOG.debug("mantelflaecheKegel2: " + mantelflaecheKegel2);
		LOG.debug("oberflaecheKegel1: " + oberflaecheKegel1);
		LOG.debug("oberflaecheKegel2: " + oberflaecheKegel2);
		LOG.debug("volumenKegel1: " + volumenKegel1);
		LOG.debug("volumenKegel2: " + volumenKegel2);
		LOG.debug("oberflaecheKugel1: " + oberflaecheKugel1);
		LOG.debug("oberflaecheKugel2: " + oberflaecheKugel2);
		LOG.debug("volumenKugel1: " + volumenKugel1);
		LOG.debug("volumenKugel2: " + volumenKugel2);

		// Set the formatter IN the bean
		LOG.debug("Setting DecimalFormat into Kreis bean...");
		kreis.setDecimalFormat(getDecimalFormat()); // <<< Set formatter HERE

		// Forward to JSP
		LOG.debug("Forwarding request to kreise.jsp...");
		request.setAttribute("kreis", kreis);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kreise.jsp");
		dispatcher.forward(request, response);

		LOG.debug("berechneKreise() method finished.");
	}

	public DecimalFormat getDecimalFormat() {
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
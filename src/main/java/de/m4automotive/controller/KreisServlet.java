package de.m4automotive.controller;

import java.io.IOException; // Importing IOException to handle input/output errors.
import java.text.ParseException;

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

	Kreis kreis = new Kreis();

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

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
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
		double flaecheninhaltGesamt = Double.parseDouble(request.getParameter("flaecheninhaltGesamt"));
		LOG.debug("FlaecheninhaltGesamt: " + flaecheninhaltGesamt);

		double kreisbogen1 = Double.parseDouble(request.getParameter("kreisbogen1"));
		LOG.debug("Kreisbogen1: " + kreisbogen1);
		double kreisbogen2 = Double.parseDouble(request.getParameter("kreisbogen2"));
		LOG.debug("Kreisbogen2: " + kreisbogen2);
		double kreisauschnitt1 = Double.parseDouble(request.getParameter("kreisausschnitt1"));
		LOG.debug("Kreisausschnitt1: " + kreisauschnitt1);
		double kreisauschnitt2 = Double.parseDouble(request.getParameter("kreisausschnitt2"));
		LOG.debug("Kreisausschnitt2: " + kreisauschnitt2);
		double kreisauschnittGesamt = Double.parseDouble(request.getParameter("kreisausschnittGesamt"));
		LOG.debug("KreisausschnittGesamt: " + kreisauschnittGesamt);
		double alpha = Double.parseDouble(request.getParameter("alpha"));
		LOG.debug("Alpha: " + alpha);

		double hoehe = Double.parseDouble(request.getParameter("hoehe"));
		LOG.debug("Hoehe: " + hoehe);
		double grundflaecheZylinder1 = Double.parseDouble(request.getParameter("grundflaecheZylinder1"));
		LOG.debug("GrundflaecheZylinder1: " + grundflaecheZylinder1);
		double grundflaecheZylinder2 = Double.parseDouble(request.getParameter("grundflaecheZylinder2"));
		LOG.debug("GrundflaecheZylinder2: " + grundflaecheZylinder2);
		double grundflaecheZylinderGesamt = Double.parseDouble(request.getParameter("grundflaecheZylinderGesamt"));
		LOG.debug("GrundflaecheZylinderGesamt: " + grundflaecheZylinderGesamt);
		double mantelflaecheZylinder1 = Double.parseDouble(request.getParameter("mantelflaecheZylinder1"));
		LOG.debug("MantelflaecheZylinder1: " + mantelflaecheZylinder1);
		double mantelflaecheZylinder2 = Double.parseDouble(request.getParameter("mantelflaecheZylinder2"));
		LOG.debug("MantelflaecheZylinder2: " + mantelflaecheZylinder2);
		double mantelflaecheZylinderGesamt = Double.parseDouble(request.getParameter("mantelflaecheZylinderGesamt"));
		LOG.debug("MantelflaecheZylinderGesamt: " + mantelflaecheZylinderGesamt);
		double oberflaecheZylinder1 = Double.parseDouble(request.getParameter("oberflaecheZylinder1"));
		LOG.debug("OberflaecheZylinder1: " + oberflaecheZylinder1);
		double oberflaecheZylinder2 = Double.parseDouble(request.getParameter("oberflaecheZylinder2"));
		LOG.debug("OberflaecheZylinder2: " + oberflaecheZylinder2);
		double oberflaecheZylinderGesamt = Double.parseDouble(request.getParameter("oberflaecheZylinderGesamt"));
		LOG.debug("OberflaecheZylinderGesamt: " + oberflaecheZylinderGesamt);
		double volumenZylinder1 = Double.parseDouble(request.getParameter("volumenZylinder1"));
		LOG.debug("VolumenZylinder1: " + volumenZylinder1);
		double volumenZylinder2 = Double.parseDouble(request.getParameter("volumenZylinder2"));
		LOG.debug("VolumenZylinder2: " + volumenZylinder2);
		double volumenZylinderGesamt = Double.parseDouble(request.getParameter("volumenZylinderGesamt"));
		LOG.debug("VolumenZylinderGesamt: " + volumenZylinderGesamt);

		double grundflaecheKegel1 = Double.parseDouble(request.getParameter("grundflaecheKegel1"));
		LOG.debug("GrundflaecheKegel1: " + grundflaecheKegel1);
		double grundflaecheKegel2 = Double.parseDouble(request.getParameter("grundflaecheKegel2"));
		LOG.debug("GrundflaecheKegel2: " + grundflaecheKegel2);
		double grundflaecheKegelGesamt = Double.parseDouble(request.getParameter("grundflaecheKegelGesamt"));
		LOG.debug("GrundflaecheKegelGesamt: " + grundflaecheKegelGesamt);
		double mantelflaecheKegel1 = Double.parseDouble(request.getParameter("mantelflaecheKegel1"));
		LOG.debug("MantelflaecheKegel1: " + mantelflaecheKegel1);
		double mantelflaecheKegel2 = Double.parseDouble(request.getParameter("mantelflaecheKegel2"));
		LOG.debug("MantelflaecheKegel2: " + mantelflaecheKegel2);
		double mantelflaecheKegelGesamt = Double.parseDouble(request.getParameter("mantelflaecheKegelGesamt"));
		LOG.debug("MantelflaecheKegelGesamt: " + mantelflaecheKegelGesamt);
		double oberflaecheKegel1 = Double.parseDouble(request.getParameter("oberflaecheKegel1"));
		LOG.debug("OberflaecheKegel1: " + oberflaecheKegel1);
		double oberflaecheKegel2 = Double.parseDouble(request.getParameter("oberflaecheKegel2"));
		LOG.debug("OberflaecheKegel2: " + oberflaecheKegel2);
		double oberflaecheKegelGesamt = Double.parseDouble(request.getParameter("oberflaecheKegelGesamt"));
		LOG.debug("OberflaecheKegelGesamt: " + oberflaecheKegelGesamt);
		double volumenKegel1 = Double.parseDouble(request.getParameter("volumenKegel1"));
		LOG.debug("VolumenKegel1: " + volumenKegel1);
		double volumenKegel2 = Double.parseDouble(request.getParameter("volumenKegel2"));
		LOG.debug("VolumenKegel2: " + volumenKegel2);
		double volumenKegelGesamt = Double.parseDouble(request.getParameter("volumenKegelGesamt"));
		LOG.debug("VolumenKegelGesamt: " + volumenKegelGesamt);
		double seitenhoehe1 = Double.parseDouble(request.getParameter("seitenhoehe1"));
		LOG.debug("Seitenhoehe1: " + seitenhoehe1);
		double seitenhoehe2 = Double.parseDouble(request.getParameter("seitenhoehe2"));
		LOG.debug("Seitenhoehe2: " + seitenhoehe2);

		double oberflaecheKugel1 = Double.parseDouble(request.getParameter("oberflaecheKugel1"));
		LOG.debug("OberflaecheKugel1: " + oberflaecheKugel1);
		double oberflaecheKugel2 = Double.parseDouble(request.getParameter("oberflaecheKugel2"));
		LOG.debug("OberflaecheKugel2: " + oberflaecheKugel2);
		double oberflaecheKugelGesamt = Double.parseDouble(request.getParameter("oberflaecheKugelGesamt"));
		LOG.debug("OberflaecheKugelGesamt: " + oberflaecheKugelGesamt);
		double volumenKugel1 = Double.parseDouble(request.getParameter("volumenKugel1"));
		LOG.debug("VolumenKugel1: " + volumenKugel1);
		double volumenKugel2 = Double.parseDouble(request.getParameter("volumenKugel2"));
		LOG.debug("VolumenKugel2: " + volumenKugel2);
		double volumenKugelGesamt = Double.parseDouble(request.getParameter("volumenKugelGesamt"));
		LOG.debug("VolumenKugelGesamt: " + volumenKugelGesamt);

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
		case "Berechne":
			calculateAll(request, response);
			break;
		default:
			LOG.warn("Unknown action: " + action);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
		}
	}

	private void calculateAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("calculateAll() method called");

		// Vorraussetzungen

		int decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
		double radius1 = Double.parseDouble(request.getParameter("radius1"));
		double radius2 = Double.parseDouble(request.getParameter("radius2"));
		double durchmesser1 = Double.parseDouble(request.getParameter("durchmesser1"));
		double durchmesser2 = Double.parseDouble(request.getParameter("durchmesser2"));
		double umfang1 = Double.parseDouble(request.getParameter("umfang1"));
		double umfang2 = Double.parseDouble(request.getParameter("umfang2"));
		double flaecheninhalt1 = Double.parseDouble(request.getParameter("flaecheninhalt1"));
		double flaecheninhalt2 = Double.parseDouble(request.getParameter("flaecheninhalt2"));
		double flaecheninhaltGesamt = Double.parseDouble(request.getParameter("flaecheninhaltGesamt"));

		// Kreisteile

		double kreisbogen1 = Double.parseDouble(request.getParameter("kreisbogen1"));
		double kreisbogen2 = Double.parseDouble(request.getParameter("kreisbogen2"));
		double kreisausschnitt1 = Double.parseDouble(request.getParameter("kreisausschnitt1"));
		double kreisausschnitt2 = Double.parseDouble(request.getParameter("kreisausschnitt2"));
		double kreisausschnittGesamt = Double.parseDouble(request.getParameter("kreisausschnittGesamt"));
		double alpha = Double.parseDouble(request.getParameter("alpha"));

		// Zylinder

		double hoehe = Double.parseDouble(request.getParameter("hoehe"));
		double grundflaecheZylinder1 = Double.parseDouble(request.getParameter("grundflaecheZylinder1"));
		double grundflaecheZylinder2 = Double.parseDouble(request.getParameter("grundflaecheZylinder2"));
		double grundflaecheZylinderGesamt = Double.parseDouble(request.getParameter("grundflaecheZylinderGesamt"));
		double mantelflaecheZylinder1 = Double.parseDouble(request.getParameter("mantelflaecheZylinder1"));
		double mantelflaecheZylinder2 = Double.parseDouble(request.getParameter("mantelflaecheZylinder2"));
		double mantelflaecheZylinderGesamt = Double.parseDouble(request.getParameter("mantelflaecheZylinderGesamt"));
		double oberflaecheZylinder1 = Double.parseDouble(request.getParameter("oberflaecheZylinder1"));
		double oberflaecheZylinder2 = Double.parseDouble(request.getParameter("oberflaecheZylinder2"));
		double oberflaecheZylinderGesamt = Double.parseDouble(request.getParameter("oberflaecheZylinderGesamt"));
		double volumenZylinder1 = Double.parseDouble(request.getParameter("volumenZylinder1"));
		double volumenZylinder2 = Double.parseDouble(request.getParameter("volumenZylinder2"));
		double volumenZylinderGesamt = Double.parseDouble(request.getParameter("volumenZylinderGesamt"));

		// Kegel

		double grundflaecheKegel1 = Double.parseDouble(request.getParameter("grundflaecheKegel1"));
		double grundflaecheKegel2 = Double.parseDouble(request.getParameter("grundflaecheKegel2"));
		double grundflaecheKegelGesamt = Double.parseDouble(request.getParameter("grundflaecheKegelGesamt"));
		double mantelflaecheKegel1 = Double.parseDouble(request.getParameter("mantelflaecheKegel1"));
		double mantelflaecheKegel2 = Double.parseDouble(request.getParameter("mantelflaecheKegel2"));
		double mantelflaecheKegelGesamt = Double.parseDouble(request.getParameter("mantelflaecheKegelGesamt"));
		double oberflaecheKegel1 = Double.parseDouble(request.getParameter("oberflaecheKegel1"));
		double oberflaecheKegel2 = Double.parseDouble(request.getParameter("oberflaecheKegel2"));
		double oberflaecheKegelGesamt = Double.parseDouble(request.getParameter("oberflaecheKegelGesamt"));
		double volumenKegel1 = Double.parseDouble(request.getParameter("volumenKegel1"));
		double volumenKegel2 = Double.parseDouble(request.getParameter("volumenKegel2"));
		double volumenKegelGesamt = Double.parseDouble(request.getParameter("volumenKegelGesamt"));
		double seitenhoehe1 = Double.parseDouble(request.getParameter("seitenhoehe1"));
		double seitenhoehe2 = Double.parseDouble(request.getParameter("seitenhoehe2"));

		// Kugel

		double oberflaecheKugel1 = Double.parseDouble(request.getParameter("oberflaecheKugel1"));
		double oberflaecheKugel2 = Double.parseDouble(request.getParameter("oberflaecheKugel2"));
		double oberflaecheKugelGesamt = Double.parseDouble(request.getParameter("oberflaecheKugelGesamt"));
		double volumenKugel1 = Double.parseDouble(request.getParameter("volumenKugel1"));
		double volumenKugel2 = Double.parseDouble(request.getParameter("volumenKugel2"));
		double volumenKugelGesamt = Double.parseDouble(request.getParameter("volumenKugelGesamt"));

		if (radius1 == 0) {
			if ((durchmesser1 / 2) != 0) {
				radius1 = durchmesser1 / 2;
			} else if (umfang1 / (2 * Math.PI) != 0) {
				radius1 = umfang1 / (2 * Math.PI);
			} else if (flaecheninhalt1 / (Math.PI) != 0) {
				radius1 = Math.sqrt(flaecheninhalt1 / Math.PI);
			} else if (kreisbogen1 / (2 * Math.PI) != 0) {
				radius1 = kreisbogen1 / (2 * Math.PI);
			} else if (kreisausschnitt1 / (Math.PI) != 0) {
				radius1 = Math.sqrt(kreisausschnitt1 / Math.PI);
			} else if (grundflaecheZylinder1 / (Math.PI) != 0) {
				radius1 = Math.sqrt(grundflaecheZylinder1 / Math.PI);
			} else if (mantelflaecheZylinder1 / (2 * Math.PI) != 0) {
				radius1 = mantelflaecheZylinder1 / (2 * Math.PI);
			} else if (oberflaecheZylinder1 / (4 * Math.PI) != 0) {
				radius1 = Math.sqrt(oberflaecheZylinder1 / (4 * Math.PI));
			} else if (volumenZylinder1 / (Math.PI) != 0) {
				radius1 = Math.cbrt(volumenZylinder1 / Math.PI);
			} else if (grundflaecheKegel1 / (Math.PI) != 0) {
				radius1 = Math.sqrt(grundflaecheKegel1 / Math.PI);
			} else if (mantelflaecheKegel1 / (2 * Math.PI) != 0) {
				radius1 = mantelflaecheKegel1 / (2 * Math.PI);
			} else if (oberflaecheKegel1 / (3 * Math.PI) != 0) {
				radius1 = Math.sqrt(oberflaecheKegel1 / (3 * Math.PI));
			} else if (volumenKegel1 / (Math.PI) != 0) {
				radius1 = Math.cbrt(volumenKegel1 / Math.PI);
			} else if (oberflaecheKugel1 / (4 * Math.PI) != 0) {
				radius1 = Math.sqrt(oberflaecheKugel1 / (4 * Math.PI));
			} else if (volumenKugel1 / (Math.PI) != 0) {
				radius1 = Math.cbrt(volumenKugel1 / Math.PI);
			} else {
				radius1 = -1;
			}
		}

		if (radius2 == 0) {
			if ((durchmesser2 / 2) != 0) {
				radius2 = durchmesser2 / 2;
			} else if (umfang2 / (2 * Math.PI) != 0) {
				radius2 = umfang2 / (2 * Math.PI);
			} else if (flaecheninhalt2 / (Math.PI) != 0) {
				radius2 = Math.sqrt(flaecheninhalt2 / Math.PI);
			} else if (kreisbogen2 / (2 * Math.PI) != 0) {
				radius2 = kreisbogen2 / (2 * Math.PI);
			} else if (kreisausschnitt2 / (Math.PI) != 0) {
				radius2 = Math.sqrt(kreisausschnitt2 / Math.PI);
			} else if (grundflaecheZylinder2 / (Math.PI) != 0) {
				radius2 = Math.sqrt(grundflaecheZylinder2 / Math.PI);
			} else if (mantelflaecheZylinder2 / (2 * Math.PI) != 0) {
				radius2 = mantelflaecheZylinder2 / (2 * Math.PI);
			} else if (oberflaecheZylinder2 / (4 * Math.PI) != 0) {
				radius2 = Math.sqrt(oberflaecheZylinder2 / (4 * Math.PI));
			} else if (volumenZylinder2 / (Math.PI) != 0) {
				radius2 = Math.cbrt(volumenZylinder2 / Math.PI);
			} else if (grundflaecheKegel2 / (Math.PI) != 0) {
				radius2 = Math.sqrt(grundflaecheKegel2 / Math.PI);
			} else if (mantelflaecheKegel2 / (3 * Math.PI) != 0) {
				radius2 = mantelflaecheKegel2 / (3 * Math.PI);
			} else if (oberflaecheKegel2 / (3 * Math.PI) != 0) {
				radius2 = Math.sqrt(oberflaecheKegel2 / (3 * Math.PI));
			} else if (volumenKegel2 / (Math.PI) != 0) {
				radius2 = Math.cbrt(volumenKegel2 / Math.PI);
			} else if (oberflaecheKugel2 / (4 * Math.PI) != 0) {
				radius2 = Math.sqrt(oberflaecheKugel2 / (4 * Math.PI));
			} else if (volumenKugel2 / (Math.PI) != 0) {
				radius2 = Math.cbrt(volumenKugel2 / Math.PI);
			} else {
				radius2 = -1;
			}
		}

		if (durchmesser1 == 0) {
			if ((2 * radius1) != 0) {
				durchmesser1 = 2 * radius1;
			} else if (umfang1 / (Math.PI) != 0) {
				durchmesser1 = umfang1 / (Math.PI);
			} else if (flaecheninhalt1 / (Math.PI) != 0) {
				durchmesser1 = Math.sqrt(flaecheninhalt1 / Math.PI);
			} else if (kreisbogen1 / (2 * Math.PI) != 0) {
				durchmesser1 = kreisbogen1 / (2 * Math.PI);
			} else if (kreisausschnitt1 / (Math.PI) != 0) {
				durchmesser1 = Math.sqrt(kreisausschnitt1 / Math.PI);
			} else if (grundflaecheZylinder1 / (Math.PI) != 0) {
				durchmesser1 = Math.sqrt(grundflaecheZylinder1 / Math.PI);
			} else if (mantelflaecheZylinder1 / (2 * Math.PI) != 0) {
				durchmesser1 = mantelflaecheZylinder1 / (2 * Math.PI);
			} else if (oberflaecheZylinder1 / (4 * Math.PI) != 0) {
				durchmesser1 = Math.sqrt(oberflaecheZylinder1 / (4 * Math.PI));
			} else if (volumenZylinder1 / (Math.PI) != 0) {
				durchmesser1 = Math.cbrt(volumenZylinder1 / Math.PI);
			} else if (grundflaecheKegel1 / (Math.PI) != 0) {
				durchmesser1 = Math.sqrt(grundflaecheKegel1 / Math.PI);
			} else if (mantelflaecheKegel1 / (2 * Math.PI) != 0) {
				durchmesser1 = mantelflaecheKegel1 / (2 * Math.PI);
			} else if (oberflaecheKegel1 / (3 * Math.PI) != 0) {
				durchmesser1 = Math.sqrt(oberflaecheKegel1 / (3 * Math.PI));
			} else if (volumenKegel1 / (Math.PI) != 0) {
				durchmesser1 = Math.cbrt(volumenKegel1 / Math.PI);
			} else if (oberflaecheKugel1 / (4 * Math.PI) != 0) {
				durchmesser1 = Math.sqrt(oberflaecheKugel1 / (4 * Math.PI));
			} else if (volumenKugel1 / (Math.PI) != 0) {
				durchmesser1 = Math.cbrt(volumenKugel1 / Math.PI);
			} else {
				durchmesser1 = -1;
			}
		}

		if (durchmesser2 == 0) {
			if ((2 * radius2) != 0) {
				durchmesser2 = 2 * radius2;
			} else if (umfang2 / (Math.PI) != 0) {
				durchmesser2 = umfang2 / (Math.PI);
			} else if (flaecheninhalt2 / (Math.PI) != 0) {
				durchmesser2 = Math.sqrt(flaecheninhalt2 / Math.PI);
			} else if (kreisbogen2 / (2 * Math.PI) != 0) {
				durchmesser2 = kreisbogen2 / (2 * Math.PI);
			} else if (kreisausschnitt2 / (Math.PI) != 0) {
				durchmesser2 = Math.sqrt(kreisausschnitt2 / Math.PI);
			} else if (grundflaecheZylinder2 / (Math.PI) != 0) {
				durchmesser2 = Math.sqrt(grundflaecheZylinder2 / Math.PI);
			} else if (mantelflaecheZylinder2 / (3 * Math.PI) != 0) {
				durchmesser2 = mantelflaecheZylinder2 / (3 * Math.PI);
			} else if (oberflaecheZylinder2 / (4 * Math.PI) != 0) {
				durchmesser2 = Math.sqrt(oberflaecheZylinder2 / (4 * Math.PI));
			} else if (volumenZylinder2 / (Math.PI) != 0) {
				durchmesser2 = Math.cbrt(volumenZylinder2 / Math.PI);
			} else if (grundflaecheKegel2 / (Math.PI) != 0) {
				durchmesser2 = Math.sqrt(grundflaecheKegel2 / Math.PI);
			} else if (mantelflaecheKegel2 / (3 * Math.PI) != 0) {
				durchmesser2 = mantelflaecheKegel2 / (3 * Math.PI);
			} else if (oberflaecheKegel2 / (3 * Math.PI) != 0) {
				durchmesser2 = Math.sqrt(oberflaecheKegel2 / (3 * Math.PI));
			} else if (volumenKegel2 / (Math.PI) != 0) {
				durchmesser2 = Math.cbrt(volumenKegel2 / Math.PI);
			} else if (oberflaecheKugel2 / (4 * Math.PI) != 0) {
				durchmesser2 = Math.sqrt(oberflaecheKugel2 / (4 * Math.PI));
			} else if (volumenKugel2 / (Math.PI) != 0) {
				durchmesser2 = Math.cbrt(volumenKugel2 / Math.PI);
			} else {
				durchmesser2 = -1;
			}
		}

		if (umfang1 == 0) {
			if ((2 * Math.PI * radius1) != 0) {
				umfang1 = 2 * Math.PI * radius1;
			} else if (durchmesser1 * Math.PI != 0) {
				umfang1 = durchmesser1 * Math.PI;
			} else if (flaecheninhalt1 / (Math.PI) != 0) {
				umfang1 = Math.sqrt(flaecheninhalt1 / Math.PI);
			} else if (kreisbogen1 / (2 * Math.PI) != 0) {
				umfang1 = kreisbogen1 / (2 * Math.PI);
			} else if (kreisausschnitt1 / (Math.PI) != 0) {
				umfang1 = Math.sqrt(kreisausschnitt1 / Math.PI);
			} else if (grundflaecheZylinder1 / (Math.PI) != 0) {
				umfang1 = Math.sqrt(grundflaecheZylinder1 / Math.PI);
			} else if (mantelflaecheZylinder1 / (2 * Math.PI) != 0) {
				umfang1 = mantelflaecheZylinder1 / (2 * Math.PI);
			} else if (oberflaecheZylinder1 / (4 * Math.PI) != 0) {
				umfang1 = Math.sqrt(oberflaecheZylinder1 / (4 * Math.PI));
			} else if (volumenZylinder1 / (Math.PI) != 0) {
				umfang1 = Math.cbrt(volumenZylinder1 / Math.PI);
			} else if (grundflaecheKegel1 / (Math.PI) != 0) {
				umfang1 = Math.sqrt(grundflaecheKegel1 / Math.PI);
			} else if (mantelflaecheKegel1 / (2 * Math.PI) != 0) {
				umfang1 = mantelflaecheKegel1 / (2 * Math.PI);
			} else if (oberflaecheKegel1 / (3 * Math.PI) != 0) {
				umfang1 = Math.sqrt(oberflaecheKegel1 / (3 * Math.PI));
			} else if (volumenKegel1 / (Math.PI) != 0) {
				umfang1 = Math.cbrt(volumenKegel1 / Math.PI);
			} else if (oberflaecheKugel1 / (4 * Math.PI) != 0) {
				umfang1 = Math.sqrt(oberflaecheKugel1 / (4 * Math.PI));
			} else if (volumenKugel1 / (Math.PI) != 0) {
				umfang1 = Math.cbrt(volumenKugel1 / Math.PI);
			} else {
				umfang1 = -1;
			}
		}

		if (umfang2 == 0) {
			if ((2 * Math.PI * radius2) != 0) {
				umfang2 = 2 * Math.PI * radius2;
			} else if (durchmesser2 * Math.PI != 0) {
				umfang2 = durchmesser2 * Math.PI;
			} else if (flaecheninhalt2 / (Math.PI) != 0) {
				umfang2 = Math.sqrt(flaecheninhalt2 / (Math.PI));
			} else if (kreisbogen2 / (2 * Math.PI) != 0) {
				umfang2 = kreisbogen2 / (2 * Math.PI);
			} else if (kreisausschnitt2 / (Math.PI) != 0) {
				umfang2 = Math.sqrt(kreisausschnitt2 / (Math.PI));
			} else if (grundflaecheZylinder2 / (Math.PI) != 0) {
				umfang2 = Math.sqrt(grundflaecheZylinder2 / (Math.PI));
			} else if (mantelflaecheZylinder2 / (3 * Math.PI) != 0) {
				umfang2 = mantelflaecheZylinder2 / (3 * Math.PI);
			} else if (oberflaecheZylinder2 / (4 * Math.PI) != 0) {
				umfang2 = Math.sqrt(oberflaecheZylinder2 / (4 * Math.PI));
			} else if (volumenZylinder2 / (Math.PI) != 0) {
				umfang2 = Math.cbrt(volumenZylinder2 / (Math.PI));
			} else if (grundflaecheKegel2 / (Math.PI) != 0) {
				umfang2 = Math.sqrt(grundflaecheKegel2 / (Math.PI));
			} else if (mantelflaecheKegel2 / (3 * Math.PI) != 0) {
				umfang2 = mantelflaecheKegel2 / (3 * Math.PI);
			} else if (oberflaecheKegel2 / (3 * Math.PI) != 0) {
				umfang2 = Math.sqrt(oberflaecheKegel2 / (3 * Math.PI));
			} else if (volumenKegel2 / (Math.PI) != 0) {
				umfang2 = Math.cbrt(volumenKegel2 / (Math.PI));
			} else if (oberflaecheKugel2 / (4 * Math.PI) != 0) {
				umfang2 = Math.sqrt(oberflaecheKugel2 / (4 * Math.PI));
			} else if (volumenKugel2 / (Math.PI) != 0) {
				umfang2 = Math.cbrt(volumenKugel2 / (Math.PI));
			} else {
				umfang2 = -1;
			}
		}

		if (flaecheninhalt1 == 0) {
			if ((Math.PI * Math.pow(radius1, 2)) != 0) {
				flaecheninhalt1 = Math.PI * Math.pow(radius1, 2);
			} else if (durchmesser1 * Math.PI != 0) {
				flaecheninhalt1 = durchmesser1 * Math.PI;
			} else if (umfang1 / (Math.PI) != 0) {
				flaecheninhalt1 = umfang1 / (Math.PI);
			} else if (kreisbogen1 / (2 * Math.PI) != 0) {
				flaecheninhalt1 = kreisbogen1 / (2 * Math.PI);
			} else if (kreisausschnitt1 / (Math.PI) != 0) {
				flaecheninhalt1 = Math.sqrt(kreisausschnitt1 / (Math.PI));
			} else if (grundflaecheZylinder1 / (Math.PI) != 0) {
				flaecheninhalt1 = Math.sqrt(grundflaecheZylinder1 / (Math.PI));
			} else if (mantelflaecheZylinder1 / (2 * Math.PI) != 0) {
				flaecheninhalt1 = mantelflaecheZylinder1 / (2 * Math.PI);
			} else if (oberflaecheZylinder1 / (4 * Math.PI) != 0) {
				flaecheninhalt1 = Math.sqrt(oberflaecheZylinder1 / (4 * Math.PI));
			} else if (volumenZylinder1 / (Math.PI) != 0) {
				flaecheninhalt1 = Math.cbrt(volumenZylinder1 / (Math.PI));
			} else if (grundflaecheKegel1 / (Math.PI) != 0) {
				flaecheninhalt1 = Math.sqrt(grundflaecheKegel1 / (Math.PI));
			} else if (mantelflaecheKegel1 / (2 * Math.PI) != 0) {
				flaecheninhalt1 = mantelflaecheKegel1 / (2 * Math.PI);
			} else if (oberflaecheKegel1 / (3 * Math.PI) != 0) {
				flaecheninhalt1 = Math.sqrt(oberflaecheKegel1 / (3 * Math.PI));
			} else if (volumenKegel1 / (Math.PI) != 0) {
				flaecheninhalt1 = Math.cbrt(volumenKegel1 / (Math.PI));
			} else if (oberflaecheKugel1 / (4 * Math.PI) != 0) {
				flaecheninhalt1 = Math.sqrt(oberflaecheKugel1 / (4 * Math.PI));
			} else if (volumenKugel1 / (Math.PI) != 0) {
				flaecheninhalt1 = Math.cbrt(volumenKugel1 / (Math.PI));
			} else {
				flaecheninhalt1 = -1;
			}
		}

		if (flaecheninhalt2 == 0) {
			if ((Math.PI * Math.pow(radius2, 2)) != 0) {
				flaecheninhalt2 = Math.PI * Math.pow(radius2, 2);
			} else if (durchmesser2 * Math.PI != 0) {
				flaecheninhalt2 = durchmesser2 * Math.PI;
			} else if (umfang2 / (Math.PI) != 0) {
				flaecheninhalt2 = umfang2 / (Math.PI);
			} else if (kreisbogen2 / (2 * Math.PI) != 0) {
				flaecheninhalt2 = kreisbogen2 / (2 * Math.PI);
			} else if (kreisausschnitt2 / (Math.PI) != 0) {
				flaecheninhalt2 = Math.sqrt(kreisausschnitt2 / (Math.PI));
			} else if (grundflaecheZylinder2 / (Math.PI) != 0) {
				flaecheninhalt2 = Math.sqrt(grundflaecheZylinder2 / (Math.PI));
			} else if (mantelflaecheZylinder2 / (3 * Math.PI) != 0) {
				flaecheninhalt2 = mantelflaecheZylinder2 / (3 * Math.PI);
			} else if (oberflaecheZylinder2 / (4 * Math.PI) != 0) {
				flaecheninhalt2 = Math.sqrt(oberflaecheZylinder2 / (4 * Math.PI));
			} else if (volumenZylinder2 / (Math.PI) != 0) {
				flaecheninhalt2 = Math.cbrt(volumenZylinder2 / (Math.PI));
			} else if (grundflaecheKegel2 / (Math.PI) != 0) {
				flaecheninhalt2 = Math.sqrt(grundflaecheKegel2 / (Math.PI));
			} else if (mantelflaecheKegel2 / (3 * Math.PI) != 0) {
				flaecheninhalt2 = mantelflaecheKegel2 / (3 * Math.PI);
			} else if (oberflaecheKegel2 / (3 * Math.PI) != 0) {
				flaecheninhalt2 = Math.sqrt(oberflaecheKegel2 / (3 * Math.PI));
			} else if (volumenKegel2 / (Math.PI) != 0) {
				flaecheninhalt2 = Math.cbrt(volumenKegel2 / (Math.PI));
			} else if (oberflaecheKugel2 / (4 * Math.PI) != 0) {
				flaecheninhalt2 = Math.sqrt(oberflaecheKugel2 / (4 * Math.PI));
			} else if (volumenKugel1 / (Math.PI) != 0) {
				flaecheninhalt2 = Math.cbrt(volumenKugel2 / (Math.PI));
			} else {
				flaecheninhalt2 = -1;
			}
		}

		if (flaecheninhaltGesamt == 0) {
			if ((flaecheninhalt2 - flaecheninhalt1) >= 0) {
				flaecheninhaltGesamt = flaecheninhalt2 - flaecheninhalt1;
			} else if ((flaecheninhalt1 - flaecheninhalt2) >= 0) {
				flaecheninhaltGesamt = flaecheninhalt1 - flaecheninhalt2;
			} else {
				flaecheninhaltGesamt = -1;
			}
		}

		// Kreisteile

		if (kreisbogen1 == 0) {
			if ((2 * Math.PI * radius1 * (alpha / 360)) != 0) {
				kreisbogen1 = 2 * Math.PI * radius1 * (alpha / 360);
			} else {
				kreisbogen1 = -1;
			}
		}

		if (kreisbogen2 == 0) {
			if ((2 * Math.PI * radius2 * (alpha / 360)) != 0) {
				kreisbogen2 = 2 * Math.PI * radius2 * (alpha / 360);
			} else {
				kreisbogen2 = -1;
			}
		}

		if (kreisausschnitt1 == 0) {
			if ((Math.PI * Math.pow(radius1, 2) * (alpha / 360)) != 0) {
				kreisausschnitt1 = Math.PI * Math.pow(radius1, 2) * (alpha / 360);
			} else {
				kreisausschnitt1 = -1;
			}
		}

		if (kreisausschnitt2 == 0) {
			if ((Math.PI * Math.pow(radius2, 2) * (alpha / 360)) != 0) {
				kreisausschnitt2 = Math.PI * Math.pow(radius2, 2) * (alpha / 360);
			} else {
				kreisausschnitt2 = -1;
			}
		}

		if (kreisausschnittGesamt == 0) {
			if ((kreisausschnitt2 - kreisausschnitt1) >= 0) {
				kreisausschnittGesamt = kreisausschnitt2 - kreisausschnitt1;
			} else if ((kreisausschnitt1 - kreisausschnitt2) >= 0) {
				kreisausschnittGesamt = kreisausschnitt1 - kreisausschnitt2;
			} else {
				kreisausschnittGesamt = -1;
			}
		}

		if (alpha == 0) {
			if ((kreisausschnitt1 / (Math.PI * Math.pow(radius1, 2))) != 0) {
				alpha = kreisausschnitt1 / (Math.PI * Math.pow(radius1, 2));
			} else if ((kreisausschnitt2 / (Math.PI * Math.pow(radius2, 2))) != 0) {
				alpha = kreisausschnitt2 / (Math.PI * Math.pow(radius2, 2));
			} else {
				alpha = -1;
			}
		}

		// Zylinder

		if (grundflaecheZylinder1 == 0) {
			if ((Math.PI * Math.pow(radius1, 2)) != 0) {
				grundflaecheZylinder1 = Math.PI * Math.pow(radius1, 2);
			} else {
				grundflaecheZylinder1 = -1;
			}
		}

		if (grundflaecheZylinder2 == 0) {
			if ((Math.PI * Math.pow(radius2, 2)) != 0) {
				grundflaecheZylinder2 = Math.PI * Math.pow(radius2, 2);
			} else {
				grundflaecheZylinder2 = -1;
			}
		}

		if (grundflaecheZylinderGesamt == 0) {
			if ((grundflaecheZylinder2 - grundflaecheZylinder1) >= 0) {
				grundflaecheZylinderGesamt = grundflaecheZylinder2 - grundflaecheZylinder1;
			} else if ((grundflaecheZylinder1 - grundflaecheZylinder2) >= 0) {
				grundflaecheZylinderGesamt = grundflaecheZylinder1 - grundflaecheZylinder2;
			} else {
				grundflaecheZylinderGesamt = -1;
			}
		}

		if (mantelflaecheZylinder1 == 0) {
			if ((2 * Math.PI * radius1 * hoehe) != 0) {
				mantelflaecheZylinder1 = 2 * Math.PI * radius1 * hoehe;
			} else if ((umfang1 * hoehe) != 0) {
				mantelflaecheZylinder1 = umfang1 * hoehe;
			} else {
				mantelflaecheZylinder1 = -1;
			}
		}

		if (mantelflaecheZylinder2 == 0) {
			if ((2 * Math.PI * radius2 * hoehe) != 0) {
				mantelflaecheZylinder2 = 2 * Math.PI * radius2 * hoehe;
			} else if ((umfang2 * hoehe) != 0) {
				mantelflaecheZylinder2 = umfang2 * hoehe;
			} else {
				mantelflaecheZylinder2 = -1;
			}
		}

		if (mantelflaecheZylinderGesamt == 0) {
			if ((mantelflaecheZylinder2 + mantelflaecheZylinder1) != 0) {
				mantelflaecheZylinderGesamt = mantelflaecheZylinder2 + mantelflaecheZylinder1;
			} else {
				mantelflaecheZylinderGesamt = -1;
			}
		}

		if (oberflaecheZylinder1 == 0) {
			if ((2 * grundflaecheZylinder1 + mantelflaecheZylinder1) != 0) {
				oberflaecheZylinder1 = 2 * grundflaecheZylinder1 + mantelflaecheZylinder1;
			} else {
				oberflaecheZylinder1 = -1;
			}
		}

		if (oberflaecheZylinder2 == 0) {
			if ((2 * grundflaecheZylinder2 + mantelflaecheZylinder2) != 0) {
				oberflaecheZylinder2 = 2 * grundflaecheZylinder2 + mantelflaecheZylinder2;
			} else {
				oberflaecheZylinder2 = -1;
			}
		}

		if (oberflaecheZylinderGesamt == 0) {
			if ((mantelflaecheZylinderGesamt + grundflaecheZylinderGesamt) != 0) {
				oberflaecheZylinderGesamt = mantelflaecheZylinderGesamt + grundflaecheZylinderGesamt;
			} else {
				oberflaecheZylinderGesamt = -1;
			}
		}

		if (volumenZylinder1 == 0) {
			if ((grundflaecheZylinder1 * hoehe) != 0) {
				volumenZylinder1 = grundflaecheZylinder1 * hoehe;
			} else if ((Math.PI * radius1 * radius1 * hoehe) != 0) {
				volumenZylinder1 = Math.PI * Math.pow(radius1, 2) * hoehe;
			} else {
				volumenZylinder1 = -1;
			}
		}

		if (volumenZylinder2 == 0) {
			if ((grundflaecheZylinder2 * hoehe) != 0) {
				volumenZylinder2 = grundflaecheZylinder2 * hoehe;
			} else if ((Math.PI * radius2 * radius2 * hoehe) != 0) {
				volumenZylinder2 = Math.PI * Math.pow(radius2, 2) * hoehe;
			} else {
				volumenZylinder2 = -1;
			}
		}

		if (volumenZylinderGesamt == 0) {
			if ((volumenZylinder2 - volumenZylinder1) != 0) {
				volumenZylinderGesamt = volumenZylinder2 - volumenZylinder1;
			} else {
				volumenZylinderGesamt = 0;
			}
		}

		// Kegel

		if (seitenhoehe1 == 0) {
			if ((Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe, 2))) != 0) {
				seitenhoehe1 = Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe, 2));
			} else if ((mantelflaecheKegel1 / (Math.PI * radius1)) != 0) {
				seitenhoehe1 = mantelflaecheKegel1 / (Math.PI * radius1);
			} else {
				seitenhoehe1 = -1;
			}
		}

		if (seitenhoehe2 == 0) {
			if ((Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe, 2))) != 0) {
				seitenhoehe2 = Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe, 2));
			} else if ((mantelflaecheKegel2 / (Math.PI * radius2)) != 0) {
				seitenhoehe2 = mantelflaecheKegel2 / (Math.PI * radius2);
			} else {
				seitenhoehe2 = -1;
			}
		}

		if (grundflaecheKegel1 == 0) {
			if ((Math.PI * Math.pow(radius1, 2)) != 0) {
				grundflaecheKegel1 = Math.PI * Math.pow(radius1, 2);
			} else if ((oberflaecheKegel1 - mantelflaecheKegel1) != 0) {
				grundflaecheKegel1 = oberflaecheKegel1 - mantelflaecheKegel1;
			} else if (((3 * volumenKegel1) / hoehe) != 0) {
				grundflaecheKegel1 = ((3 * volumenKegel1) / hoehe);
			} else {
				grundflaecheKegel1 = -1;
			}
		}

		if (grundflaecheKegel2 == 0) {
			if ((Math.PI * Math.pow(radius2, 2)) != 0) {
				grundflaecheKegel2 = Math.PI * Math.pow(radius2, 2);
			} else if ((oberflaecheKegel2 - mantelflaecheKegel2) != 0) {
				grundflaecheKegel2 = oberflaecheKegel2 - mantelflaecheKegel2;
			} else if (((3 * volumenKegel2) / hoehe) != 0) {
				grundflaecheKegel2 = ((3 * volumenKegel2) / hoehe);
			} else {
				grundflaecheKegel2 = -1;
			}
		}

		if (grundflaecheKegelGesamt == 0) {
			if ((grundflaecheKegel2 - grundflaecheKegel1) != 0) {
				grundflaecheKegelGesamt = grundflaecheKegel2 - grundflaecheKegel1;
			} else {
				grundflaecheKegelGesamt = -1;
			}
		}

		if (mantelflaecheKegel1 == 0) {
			if ((Math.PI * radius1 * seitenhoehe1) != 0) {
				mantelflaecheKegel1 = Math.PI * radius1 * seitenhoehe1;
			} else if ((oberflaecheKegel1 - grundflaecheKegel1) != 0) {
				mantelflaecheKegel1 = oberflaecheKegel1 - grundflaecheKegel1;
			} else {
				mantelflaecheKegel1 = -1;
			}
		}

		if (mantelflaecheKegel2 == 0) {
			if ((Math.PI * radius2 * seitenhoehe2) != 0) {
				mantelflaecheKegel2 = Math.PI * radius2 * seitenhoehe2;
			} else if ((oberflaecheKegel2 - grundflaecheKegel2) != 0) {
				mantelflaecheKegel2 = oberflaecheKegel2 - grundflaecheKegel2;
			} else {
				mantelflaecheKegel2 = -1;
			}
		}

		if (mantelflaecheKegelGesamt == 0) {
			if ((mantelflaecheKegel2 + mantelflaecheKegel1) != 0) {
				mantelflaecheKegelGesamt = mantelflaecheKegel2 + mantelflaecheKegel1;
			} else {
				mantelflaecheKegelGesamt = -1;
			}
		}

		if (oberflaecheKegel1 == 0) {
			if ((grundflaecheKegel1 + mantelflaecheKegel1) != 0) {
				oberflaecheKegel1 = grundflaecheKegel1 + mantelflaecheKegel1;
			} else {
				oberflaecheKegel1 = -1;
			}
		}

		if (oberflaecheKegel2 == 0) {
			if ((grundflaecheKegel2 + mantelflaecheKegel2) != 0) {
				oberflaecheKegel2 = grundflaecheKegel2 + mantelflaecheKegel2;
			} else {
				oberflaecheKegel2 = -1;
			}
		}

		if (oberflaecheKegelGesamt == 0) {
			if ((grundflaecheKegelGesamt + mantelflaecheKegelGesamt) != 0) {
				oberflaecheKegelGesamt = grundflaecheKegelGesamt + mantelflaecheKegelGesamt;
			} else {
				oberflaecheKegelGesamt = -1;
			}
		}

		if (volumenKegel1 == 0) {
			if (((1 / 3) * grundflaecheKegel1 * hoehe) != 0) {
				volumenKegel1 = ((1 / 3) * grundflaecheKegel1 * hoehe);
			} else if (((Math.PI / 3) * Math.pow(radius1, 2) * hoehe) != 0) {
				volumenKegel1 = ((Math.PI / 3) * Math.pow(radius1, 2) * hoehe);
			} else {
				volumenKegel1 = -1;
			}
		}

		if (volumenKegel2 == 0) {
			if (((1 / 3) * grundflaecheKegel2 * hoehe) != 0) {
				volumenKegel2 = ((1 / 3) * grundflaecheKegel2 * hoehe);
			} else if (((Math.PI / 3) * Math.pow(radius2, 2) * hoehe) != 0) {
				volumenKegel2 = ((Math.PI / 3) * Math.pow(radius2, 2) * hoehe);
			} else {
				volumenKegel2 = -1;
			}
		}

		if (volumenKegelGesamt == 0) {
			if ((volumenKegel2 - volumenKegel1) != 0) {
				volumenKegelGesamt = volumenKegel2 - volumenKegel1;
			} else {
				volumenKegelGesamt = -1;
			}
		}

		// Kugel

		if (oberflaecheKugel1 == 0) {
			if ((4 * Math.PI * Math.pow(radius1, 2)) != 0) {
				oberflaecheKugel1 = 4 * Math.PI * Math.pow(radius1, 2);
			} else {
				oberflaecheKugel1 = -1;
			}
		}

		if (oberflaecheKugel2 == 0) {
			if ((4 * Math.PI * Math.pow(radius2, 2)) != 0) {
				oberflaecheKugel2 = 4 * Math.PI * Math.pow(radius2, 2);
			} else {
				oberflaecheKugel2 = -1;
			}
		}

		if (oberflaecheKugelGesamt == 0) {
			if ((oberflaecheKugel2 + oberflaecheKugel1) != 0) {
				oberflaecheKugelGesamt = oberflaecheKugel2 + oberflaecheKugel1;
			} else {
				oberflaecheKugelGesamt = -1;
			}
		}

		if (volumenKugel1 == 0) {
			if (((4 / 3) * Math.PI * Math.pow(radius1, 3)) != 0) {
				volumenKugel1 = (4.0 / 3.0) * Math.PI * Math.pow(radius1, 3);
			} else {
				volumenKugel1 = -1;
			}
		}

		if (volumenKugel2 == 0) {
			if (((4 / 3) * Math.PI * Math.pow(radius2, 3)) != 0) {
				volumenKugel2 = (4.0 / 3.0) * Math.PI * Math.pow(radius2, 3);
			} else {
				volumenKugel2 = -1;
			}
		}

		if (volumenKugelGesamt == 0) {
			if ((volumenKugel2 - volumenKugel1) != 0) {
				volumenKugelGesamt = volumenKugel2 - volumenKugel1;
			} else if ((volumenKugel1 - volumenKugel2) != 0) {
				volumenKugelGesamt = volumenKugel1 - volumenKugel2;
			} else {
				volumenKugelGesamt = -1;
			}
		}

		// Vorraussetzungen

		kreis.setRadius1(Math.round(radius1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("radius1: " + radius1);
		kreis.setRadius2(Math.round(radius2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("radius2: " + radius2);
		kreis.setDurchmesser1(Math.round(durchmesser1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("durchmesser1: " + durchmesser1);
		kreis.setDurchmesser2(Math.round(durchmesser2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("durchmesser2: " + durchmesser2);
		kreis.setUmfang1(Math.round(umfang1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("umfang1: " + umfang1);
		kreis.setUmfang2(Math.round(umfang2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("umfang2: " + umfang2);
		kreis.setFlaecheninhalt1(
				Math.round(flaecheninhalt1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("flaecheninhalt1: " + flaecheninhalt1);
		kreis.setFlaecheninhalt2(
				Math.round(flaecheninhalt2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("flaecheninhalt2: " + flaecheninhalt2);
		kreis.setFlaecheninhaltGesamt(
				Math.round(flaecheninhaltGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("flaecheninhaltGesamt: " + flaecheninhaltGesamt);

		// Kreisteile

		kreis.setKreisbogen1(Math.round(kreisbogen1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("kreisbogen1: " + kreisbogen1);
		kreis.setKreisbogen2(Math.round(kreisbogen2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("kreisbogen2: " + kreisbogen2);
		kreis.setKreisausschnitt1(
				Math.round(kreisausschnitt1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("kreisausschnitt1: " + kreisausschnitt1);
		kreis.setKreisausschnitt2(
				Math.round(kreisausschnitt2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("kreisauschnitt2: " + kreisausschnitt2);
		kreis.setKreisausschnittGesamt(
				Math.round(kreisausschnittGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("kreisauschnittGesamt: " + kreisausschnittGesamt);
		kreis.setAlpha(Math.round(alpha * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("alpha: " + alpha);

		// Zylinder

		kreis.setHoehe(hoehe);
		LOG.debug("hoehe: " + hoehe);
		kreis.setGrundflaecheZylinder1(
				Math.round(grundflaecheZylinder1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("grundflaecheZylinder1: " + grundflaecheZylinder1);
		kreis.setGrundflaecheZylinder2(
				Math.round(grundflaecheZylinder2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("grundflaecheZylinder2: " + grundflaecheZylinder2);
		kreis.setGrundflaecheZylinderGesamt(
				Math.round(grundflaecheZylinderGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("grundflaecheZylinderGesamt: " + grundflaecheZylinderGesamt);
		kreis.setMantelflaecheZylinder1(
				Math.round(mantelflaecheZylinder1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("mantelflaecheZylinder1: " + mantelflaecheZylinder1);
		kreis.setMantelflaecheZylinder2(
				Math.round(mantelflaecheZylinder2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("mantelflaecheZylinder2: " + mantelflaecheZylinder2);
		kreis.setMantelflaecheZylinderGesamt(
				Math.round(mantelflaecheZylinderGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("mantelflaecheZylinderGesamt: " + mantelflaecheZylinderGesamt);
		kreis.setOberflaecheZylinder1(
				Math.round(oberflaecheZylinder1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("oberflaecheZylinder1: " + oberflaecheZylinder1);
		kreis.setOberflaecheZylinder2(
				Math.round(oberflaecheZylinder2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("oberflaecheZylinder2: " + oberflaecheZylinder2);
		kreis.setOberflaecheZylinderGesamt(
				Math.round(oberflaecheZylinderGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("oberflaecheZylinderGesamt: " + oberflaecheZylinderGesamt);
		kreis.setVolumenZylinder1(
				Math.round(volumenZylinder1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("volumenZylinder1: " + volumenZylinder1);
		kreis.setVolumenZylinder2(
				Math.round(volumenZylinder2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("volumenZylinder2: " + volumenZylinder2);
		kreis.setVolumenZylinderGesamt(
				Math.round(volumenZylinderGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("volumenZylinderGesamt: " + volumenZylinderGesamt);

		// Kegel

		kreis.setSeitenhoehe1(Math.round(seitenhoehe1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("seitenhoehe1: " + seitenhoehe1);
		kreis.setSeitenhoehe2(Math.round(seitenhoehe2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("seitenhoehe2: " + seitenhoehe2);
		kreis.setGrundflaecheKegel1(
				Math.round(grundflaecheKegel1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("grundflaecheKegel1: " + grundflaecheKegel1);
		kreis.setGrundflaecheKegel2(
				Math.round(grundflaecheKegel2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("grundflaecheKegel2: " + grundflaecheKegel2);
		kreis.setGrundflaecheKegelGesamt(
				Math.round(grundflaecheKegelGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("grundflaecheKegelGesamt: " + grundflaecheKegelGesamt);
		kreis.setMantelflaecheKegel1(
				Math.round(mantelflaecheKegel1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("mantelflaecheKegel1: " + mantelflaecheKegel1);
		kreis.setMantelflaecheKegel2(
				Math.round(mantelflaecheKegel2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("mantelflaecheKegel2: " + mantelflaecheKegel2);
		kreis.setMantelflaecheKegelGesamt(
				Math.round(mantelflaecheKegelGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("mantelflaecheKegelGesamt: " + mantelflaecheKegelGesamt);
		kreis.setOberflaecheKegel1(
				Math.round(oberflaecheKegel1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("oberflaecheKegel1: " + oberflaecheKegel1);
		kreis.setOberflaecheKegel2(
				Math.round(oberflaecheKegel2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("oberflaecheKegel2: " + oberflaecheKegel2);
		kreis.setOberflaecheKegelGesamt(
				Math.round(oberflaecheKegelGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("oberflaecheKegelGesamt: " + oberflaecheKegelGesamt);
		kreis.setVolumenKegel1(Math.round(volumenKegel1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("volumenKegel1: " + volumenKegel1);
		kreis.setVolumenKegel2(Math.round(volumenKegel2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("volumenKegel2: " + volumenKegel2);
		kreis.setVolumenKegelGesamt(
				Math.round(volumenKegelGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("volumenKegelGesamt: " + volumenKegelGesamt);

		// Kugel

		kreis.setOberflaecheKugel1(
				Math.round(oberflaecheKugel1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("oberflaecheKugel1: " + oberflaecheKugel1);
		kreis.setOberflaecheKugel2(
				Math.round(oberflaecheKugel2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("oberflaecheKugel2: " + oberflaecheKugel2);
		kreis.setOberflaecheKugelGesamt(
				Math.round(oberflaecheKugelGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("oberflaecheKugelGesamt: " + oberflaecheKugelGesamt);
		kreis.setVolumenKugel1(Math.round(volumenKugel1 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("volumenKugel1: " + volumenKugel1);
		kreis.setVolumenKugel2(Math.round(volumenKugel2 * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("volumenKugel2: " + volumenKugel2);
		kreis.setVolumenKugelGesamt(
				Math.round(volumenKugelGesamt * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces));
		LOG.debug("volumenKugelGesamt: " + volumenKugelGesamt);

		request.setAttribute("kreis", kreis);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}
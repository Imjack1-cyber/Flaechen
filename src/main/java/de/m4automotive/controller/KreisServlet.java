package de.m4automotive.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
// Removed unused ParseException import
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.m4automotive.model.Kreis;

// Assuming the original mapping was just /kreis, added KreisServlet for potential direct access
@WebServlet(name = "KreisServlet", urlPatterns = { "/kreis", "/KreisServlet" })
public class KreisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger(KreisServlet.class);

	// Removed instance variable decimalPlaces, get it from request each time
	// Removed instance variable Kreis kreis, create locally in berechneKreise

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("KreisServlet doGet() called");
		// Simply forward to the JSP for GET requests
		forwardToJsp(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("KreisServlet doPost() called");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");

		// Check if the action is specifically for calculating circles
		if ("berechneKreise".equals(action)) {
			try {
				berechneKreise(request, response);
			} catch (NumberFormatException e) {
				LOG.error("Error parsing number during calculation: {}", e.getMessage());
				request.setAttribute("errorMessage", "Ungültiges Zahlenformat in der Eingabe: " + e.getMessage());
				forwardToJsp(request, response); // Forward back with error
			} catch (Exception e) {
				LOG.error("Unexpected error during calculation", e); // Log full stack trace
				request.setAttribute("errorMessage", "Ein unerwarteter Fehler ist bei der Berechnung aufgetreten.");
				forwardToJsp(request, response); // Forward back with error
			}
		} else {
			// Handle other actions or default behavior (e.g., just show the form)
			LOG.warn("Unknown or missing action, showing form. Action: {}", action);
			request.removeAttribute("kreis"); // Clear previous results if any
			forwardToJsp(request, response);
		}
	}

	// Removed processRequest as it's simpler with only one action handled in doPost

	private void berechneKreise(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.debug("berechneKreise() method called");
		Kreis kreis = new Kreis(); // Create a new bean for each calculation request

		// --- 1. Get and Parse Inputs (Using original Double.parseDouble) ---
		int decimalPlaces = 2; // Default
		try {
			decimalPlaces = Integer.parseInt(request.getParameter("decimalPlaces"));
			if (decimalPlaces < 0 || decimalPlaces > 10) {
				LOG.warn("Invalid decimalPlaces value ({}), defaulting to 2.", decimalPlaces);
				decimalPlaces = 2;
			}
		} catch (NumberFormatException e) {
			LOG.warn("Could not parse decimalPlaces, defaulting to 2.", e);
			decimalPlaces = 2;
		}
		LOG.debug("Using decimal places: {}", decimalPlaces);

		// Original parsing - will throw NumberFormatException if invalid/empty
		double radius1 = Double.parseDouble(request.getParameter("radius1"));
		LOG.debug("Input Radius1: {}", radius1);
		double radius2 = Double.parseDouble(request.getParameter("radius2"));
		LOG.debug("Input Radius2: {}", radius2);
		double durchmesser1 = Double.parseDouble(request.getParameter("durchmesser1"));
		LOG.debug("Input Durchmesser1: {}", durchmesser1);
		double durchmesser2 = Double.parseDouble(request.getParameter("durchmesser2"));
		LOG.debug("Input Durchmesser2: {}", durchmesser2);
		double umfang1 = Double.parseDouble(request.getParameter("umfang1"));
		LOG.debug("Input Umfang1: {}", umfang1);
		double umfang2 = Double.parseDouble(request.getParameter("umfang2"));
		LOG.debug("Input Umfang2: {}", umfang2);
		double flaecheninhalt1 = Double.parseDouble(request.getParameter("flaecheninhalt1"));
		LOG.debug("Input Flaecheninhalt1: {}", flaecheninhalt1);
		double flaecheninhalt2 = Double.parseDouble(request.getParameter("flaecheninhalt2"));
		LOG.debug("Input Flaecheninhalt2: {}", flaecheninhalt2);

		double kreisbogen1 = Double.parseDouble(request.getParameter("kreisbogen1"));
		LOG.debug("Input Kreisbogen1: {}", kreisbogen1);
		double kreisbogen2 = Double.parseDouble(request.getParameter("kreisbogen2"));
		LOG.debug("Input Kreisbogen2: {}", kreisbogen2);
		double kreisauschnitt1 = Double.parseDouble(request.getParameter("kreisausschnitt1"));
		LOG.debug("Input Kreisausschnitt1: {}", kreisauschnitt1);
		double kreisauschnitt2 = Double.parseDouble(request.getParameter("kreisausschnitt2"));
		LOG.debug("Input Kreisausschnitt2: {}", kreisauschnitt2);
		// Assuming only one alpha is submitted due to JS validation
		double alpha = Double.parseDouble(request.getParameter("alpha"));
		LOG.debug("Input Alpha: {}", alpha);

		double hoehe1 = Double.parseDouble(request.getParameter("hoehe1"));
		LOG.debug("Input Hoehe1: {}", hoehe1);
		double hoehe2 = Double.parseDouble(request.getParameter("hoehe2"));
		LOG.debug("Input Hoehe2: {}", hoehe2);
		double grundflaecheZylinder1 = Double.parseDouble(request.getParameter("grundflaecheZylinder1"));
		LOG.debug("Input GrundflaecheZylinder1: {}", grundflaecheZylinder1);
		double grundflaecheZylinder2 = Double.parseDouble(request.getParameter("grundflaecheZylinder2"));
		LOG.debug("Input GrundflaecheZylinder2: {}", grundflaecheZylinder2);
		double mantelflaecheZylinder1 = Double.parseDouble(request.getParameter("mantelflaecheZylinder1"));
		LOG.debug("Input MantelflaecheZylinder1: {}", mantelflaecheZylinder1);
		double mantelflaecheZylinder2 = Double.parseDouble(request.getParameter("mantelflaecheZylinder2"));
		LOG.debug("Input MantelflaecheZylinder2: {}", mantelflaecheZylinder2);
		double oberflaecheZylinder1 = Double.parseDouble(request.getParameter("oberflaecheZylinder1"));
		LOG.debug("Input OberflaecheZylinder1: {}", oberflaecheZylinder1);
		double oberflaecheZylinder2 = Double.parseDouble(request.getParameter("oberflaecheZylinder2"));
		LOG.debug("Input OberflaecheZylinder2: {}", oberflaecheZylinder2);
		double volumenZylinder1 = Double.parseDouble(request.getParameter("volumenZylinder1"));
		LOG.debug("Input VolumenZylinder1: {}", volumenZylinder1);
		double volumenZylinder2 = Double.parseDouble(request.getParameter("volumenZylinder2"));
		LOG.debug("Input VolumenZylinder2: {}", volumenZylinder2);

		double grundflaecheKegel1 = Double.parseDouble(request.getParameter("grundflaecheKegel1"));
		LOG.debug("Input GrundflaecheKegel1: {}", grundflaecheKegel1);
		double grundflaecheKegel2 = Double.parseDouble(request.getParameter("grundflaecheKegel2"));
		LOG.debug("Input GrundflaecheKegel2: {}", grundflaecheKegel2);
		double mantelflaecheKegel1 = Double.parseDouble(request.getParameter("mantelflaecheKegel1"));
		LOG.debug("Input MantelflaecheKegel1: {}", mantelflaecheKegel1);
		double mantelflaecheKegel2 = Double.parseDouble(request.getParameter("mantelflaecheKegel2"));
		LOG.debug("Input MantelflaecheKegel2: {}", mantelflaecheKegel2);
		double oberflaecheKegel1 = Double.parseDouble(request.getParameter("oberflaecheKegel1"));
		LOG.debug("Input OberflaecheKegel1: {}", oberflaecheKegel1);
		double oberflaecheKegel2 = Double.parseDouble(request.getParameter("oberflaecheKegel2"));
		LOG.debug("Input OberflaecheKegel2: {}", oberflaecheKegel2);
		double volumenKegel1 = Double.parseDouble(request.getParameter("volumenKegel1"));
		LOG.debug("Input VolumenKegel1: {}", volumenKegel1);
		double volumenKegel2 = Double.parseDouble(request.getParameter("volumenKegel2"));
		LOG.debug("Input VolumenKegel2: {}", volumenKegel2);
		double seitenhoehe1 = Double.parseDouble(request.getParameter("seitenhoehe1"));
		LOG.debug("Input Seitenhoehe1: {}", seitenhoehe1);
		double seitenhoehe2 = Double.parseDouble(request.getParameter("seitenhoehe2"));
		LOG.debug("Input Seitenhoehe2: {}", seitenhoehe2);

		double oberflaecheKugel1 = Double.parseDouble(request.getParameter("oberflaecheKugel1"));
		LOG.debug("Input OberflaecheKugel1: {}", oberflaecheKugel1);
		double oberflaecheKugel2 = Double.parseDouble(request.getParameter("oberflaecheKugel2"));
		LOG.debug("Input OberflaecheKugel2: {}", oberflaecheKugel2);
		double volumenKugel1 = Double.parseDouble(request.getParameter("volumenKugel1"));
		LOG.debug("Input VolumenKugel1: {}", volumenKugel1);
		double volumenKugel2 = Double.parseDouble(request.getParameter("volumenKugel2"));
		LOG.debug("Input VolumenKugel2: {}", volumenKugel2);

		// Initialize Gesamt values
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

		LOG.debug("<============== Berechnung Start ==============>");

		// --- Calculations (Original logic + logging + NaN checks + NaN assignment) ---

		if (radius1 == 0) {
			LOG.debug("Calculating radius1");
			if (!Double.isNaN(durchmesser1) && durchmesser1 != 0 && (durchmesser1 / 2.0) != 0) { // ADDED NaN check
				radius1 = durchmesser1 / 2.0;
				LOG.debug("Calculated with: durchmesser1 / 2.0");
			} else if (!Double.isNaN(flaecheninhalt1) && flaecheninhalt1 != 0
					&& Math.sqrt(flaecheninhalt1 / Math.PI) != 0) { // ADDED NaN check
				radius1 = Math.sqrt(flaecheninhalt1 / Math.PI);
				LOG.debug("Calculated with: Math.sqrt(flaecheninhalt1 / Math.PI)");
			} else if (!Double.isNaN(umfang1) && umfang1 != 0 && umfang1 / (2.0 * Math.PI) != 0) { // ADDED NaN check
				radius1 = umfang1 / (2.0 * Math.PI);
				LOG.debug("Calculated with: umfang1 / (2.0 * Math.PI)");
			} else if (!Double.isNaN(kreisbogen1) && !Double.isNaN(alpha) && kreisbogen1 != 0 && alpha != 0
					&& (kreisbogen1 * 180.0) / (alpha * Math.PI) != 0) { // ADDED NaN checks
				radius1 = (kreisbogen1 * 180.0) / (alpha * Math.PI);
				LOG.debug("Calculated with: (kreisbogen1 * 180.0) / (alpha * Math.PI)");
			} else if (!Double.isNaN(kreisauschnitt1) && !Double.isNaN(alpha) && kreisauschnitt1 != 0 && alpha != 0
					&& Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI)) != 0) { // ADDED NaN checks
				radius1 = Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI));
				LOG.debug("Calculated with: Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI))");
			} else if (!Double.isNaN(kreisauschnitt1) && !Double.isNaN(kreisbogen1) && kreisauschnitt1 != 0
					&& kreisbogen1 != 0 && (2.0 * kreisauschnitt1 / kreisbogen1) != 0) { // ADDED NaN checks
				radius1 = 2.0 * kreisauschnitt1 / kreisbogen1;
				LOG.debug("Calculated with: 2.0 * kreisauschnitt1 / kreisbogen1");
			} else if (!Double.isNaN(oberflaecheKugel1) && oberflaecheKugel1 != 0
					&& Math.sqrt(oberflaecheKugel1 / (4.0 * Math.PI)) != 0) { // ADDED NaN check
				radius1 = Math.sqrt(oberflaecheKugel1 / (4.0 * Math.PI));
				LOG.debug("Calculated with: Math.sqrt(oberflaecheKugel1 / (4.0 * Math.PI))");
			} else if (!Double.isNaN(volumenKugel1) && volumenKugel1 != 0
					&& Math.cbrt(3.0 * volumenKugel1 / (4.0 * Math.PI)) != 0) { // ADDED NaN check
				radius1 = Math.cbrt(3.0 * volumenKugel1 / (4.0 * Math.PI));
				LOG.debug("Calculated with: Math.cbrt(3.0 * volumenKugel1 / (4.0 * Math.PI))");
			} else if (!Double.isNaN(grundflaecheZylinder1) && grundflaecheZylinder1 != 0
					&& Math.sqrt(grundflaecheZylinder1 / Math.PI) != 0) { // ADDED NaN check
				radius1 = Math.sqrt(grundflaecheZylinder1 / Math.PI);
				LOG.debug("Calculated with: Math.sqrt(grundflaecheZylinder1 / Math.PI)");
			} else if (!Double.isNaN(grundflaecheKegel1) && grundflaecheKegel1 != 0
					&& Math.sqrt(grundflaecheKegel1 / Math.PI) != 0) { // ADDED NaN check
				radius1 = Math.sqrt(grundflaecheKegel1 / Math.PI);
				LOG.debug("Calculated with: Math.sqrt(grundflaecheKegel1 / Math.PI)");
			} else if (!Double.isNaN(mantelflaecheZylinder1) && !Double.isNaN(hoehe1) && hoehe1 != 0
					&& mantelflaecheZylinder1 != 0 && mantelflaecheZylinder1 / (2.0 * Math.PI * hoehe1) != 0) { // ADDED
																												// NaN
																												// checks
				radius1 = mantelflaecheZylinder1 / (2.0 * Math.PI * hoehe1);
				LOG.debug("Calculated with: mantelflaecheZylinder1 / (2.0 * Math.PI * hoehe1)");
			} else if (!Double.isNaN(volumenZylinder1) && !Double.isNaN(hoehe1) && volumenZylinder1 != 0 && hoehe1 != 0
					&& Math.sqrt(volumenZylinder1 / (Math.PI * hoehe1)) != 0) { // ADDED NaN checks
				radius1 = Math.sqrt(volumenZylinder1 / (Math.PI * hoehe1));
				LOG.debug("Calculated with: Math.sqrt(volumenZylinder1 / (Math.PI * hoehe1))");
			} else if (!Double.isNaN(mantelflaecheKegel1) && !Double.isNaN(seitenhoehe1) && seitenhoehe1 != 0
					&& mantelflaecheKegel1 != 0 && mantelflaecheKegel1 / (Math.PI * seitenhoehe1) != 0) { // ADDED NaN
																											// checks
				radius1 = mantelflaecheKegel1 / (Math.PI * seitenhoehe1);
				LOG.debug("Calculated with: mantelflaecheKegel1 / (Math.PI * seitenhoehe1)");
			} else if (!Double.isNaN(seitenhoehe1) && !Double.isNaN(hoehe1) && seitenhoehe1 != 0 && hoehe1 != 0
					&& Math.sqrt(Math.pow(seitenhoehe1, 2) - Math.pow(hoehe1, 2.0)) != 0) { // ADDED NaN checks
				radius1 = Math.sqrt(Math.pow(seitenhoehe1, 2) - Math.pow(hoehe1, 2.0));
				LOG.debug("Calculated with: Math.sqrt(seitenhoehe1^2 - hoehe1^2)");
			} else if (!Double.isNaN(volumenKegel1) && !Double.isNaN(hoehe1) && volumenKegel1 != 0 && hoehe1 != 0
					&& Math.sqrt(3.0 * volumenKegel1 / (Math.PI * hoehe1)) != 0) { // ADDED NaN checks
				radius1 = Math.sqrt(3.0 * volumenKegel1 / (Math.PI * hoehe1));
				LOG.debug("Calculated with: Math.sqrt(3.0 * volumenKegel1 / (Math.PI * hoehe1))");
			} else {
				radius1 = Double.NaN;
				LOG.debug("Calculation for radius1 was not possible, set to NaN");
			}
		}

		if (radius2 == 0) {
			LOG.debug("Calculating radius2");
			if (!Double.isNaN(durchmesser2) && durchmesser2 != 0 && (durchmesser2 / 2.0) != 0) {
				radius2 = durchmesser2 / 2.0;
				LOG.debug("Calculated with: durchmesser2 / 2.0");
			} else if (!Double.isNaN(flaecheninhalt2) && flaecheninhalt2 != 0
					&& Math.sqrt(flaecheninhalt2 / Math.PI) != 0) {
				radius2 = Math.sqrt(flaecheninhalt2 / Math.PI);
				LOG.debug("Calculated with: Math.sqrt(flaecheninhalt2 / Math.PI)");
			} else if (!Double.isNaN(umfang2) && umfang2 != 0 && umfang2 / (2.0 * Math.PI) != 0) {
				radius2 = umfang2 / (2.0 * Math.PI);
				LOG.debug("Calculated with: umfang2 / (2.0 * Math.PI)");
			} else if (!Double.isNaN(kreisbogen2) && !Double.isNaN(alpha) && kreisbogen2 != 0 && alpha != 0
					&& (kreisbogen2 * 180.0) / (alpha * Math.PI) != 0) {
				radius2 = (kreisbogen2 * 180.0) / (alpha * Math.PI);
				LOG.debug("Calculated with: (kreisbogen2 * 180.0) / (alpha * Math.PI)");
			} else if (!Double.isNaN(kreisauschnitt2) && !Double.isNaN(alpha) && kreisauschnitt2 != 0 && alpha != 0
					&& Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI)) != 0) {
				radius2 = Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI));
				LOG.debug("Calculated with: Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI))");
			} else if (!Double.isNaN(kreisauschnitt2) && !Double.isNaN(kreisbogen2) && kreisauschnitt2 != 0
					&& kreisbogen2 != 0 && (2.0 * kreisauschnitt2 / kreisbogen2) != 0) {
				radius2 = 2.0 * kreisauschnitt2 / kreisbogen2;
				LOG.debug("Calculated with: 2.0 * kreisauschnitt2 / kreisbogen2");
			} else if (!Double.isNaN(oberflaecheKugel2) && oberflaecheKugel2 != 0
					&& Math.sqrt(oberflaecheKugel2 / (4.0 * Math.PI)) != 0) {
				radius2 = Math.sqrt(oberflaecheKugel2 / (4.0 * Math.PI));
				LOG.debug("Calculated with: Math.sqrt(oberflaecheKugel2 / (4.0 * Math.PI))");
			} else if (!Double.isNaN(volumenKugel2) && volumenKugel2 != 0
					&& Math.cbrt(3.0 * volumenKugel2 / (4.0 * Math.PI)) != 0) {
				radius2 = Math.cbrt(3.0 * volumenKugel2 / (4.0 * Math.PI));
				LOG.debug("Calculated with: Math.cbrt(3.0 * volumenKugel2 / (4.0 * Math.PI))");
			} else if (!Double.isNaN(grundflaecheZylinder2) && grundflaecheZylinder2 != 0
					&& Math.sqrt(grundflaecheZylinder2 / Math.PI) != 0) {
				radius2 = Math.sqrt(grundflaecheZylinder2 / Math.PI);
				LOG.debug("Calculated with: Math.sqrt(grundflaecheZylinder2 / Math.PI)");
			} else if (!Double.isNaN(grundflaecheKegel2) && grundflaecheKegel2 != 0
					&& Math.sqrt(grundflaecheKegel2 / Math.PI) != 0) {
				radius2 = Math.sqrt(grundflaecheKegel2 / Math.PI);
				LOG.debug("Calculated with: Math.sqrt(grundflaecheKegel2 / Math.PI)");
			} else if (!Double.isNaN(mantelflaecheZylinder2) && !Double.isNaN(hoehe2) && hoehe2 != 0
					&& mantelflaecheZylinder2 != 0 && mantelflaecheZylinder2 / (2.0 * Math.PI * hoehe2) != 0) {
				radius2 = mantelflaecheZylinder2 / (2.0 * Math.PI * hoehe2);
				LOG.debug("Calculated with: mantelflaecheZylinder2 / (2.0 * Math.PI * hoehe2)");
			} else if (!Double.isNaN(volumenZylinder2) && !Double.isNaN(hoehe2) && volumenZylinder2 != 0 && hoehe2 != 0
					&& Math.sqrt(volumenZylinder2 / (Math.PI * hoehe2)) != 0) {
				radius2 = Math.sqrt(volumenZylinder2 / (Math.PI * hoehe2));
				LOG.debug("Calculated with: Math.sqrt(volumenZylinder2 / (Math.PI * hoehe2))");
			} else if (!Double.isNaN(mantelflaecheKegel2) && !Double.isNaN(seitenhoehe2) && seitenhoehe2 != 0
					&& mantelflaecheKegel2 != 0 && mantelflaecheKegel2 / (Math.PI * seitenhoehe2) != 0) {
				radius2 = mantelflaecheKegel2 / (Math.PI * seitenhoehe2);
				LOG.debug("Calculated with: mantelflaecheKegel2 / (Math.PI * seitenhoehe2)");
			} else if (!Double.isNaN(seitenhoehe2) && !Double.isNaN(hoehe2) && seitenhoehe2 != 0 && hoehe2 != 0
					&& Math.sqrt(Math.pow(seitenhoehe2, 2) - Math.pow(hoehe2, 2.0)) != 0) {
				radius2 = Math.sqrt(Math.pow(seitenhoehe2, 2) - Math.pow(hoehe2, 2.0));
				LOG.debug("Calculated with: Math.sqrt(seitenhoehe2^2 - hoehe2^2)");
			} else if (!Double.isNaN(volumenKegel2) && !Double.isNaN(hoehe2) && volumenKegel2 != 0 && hoehe2 != 0
					&& Math.sqrt(3.0 * volumenKegel2 / (Math.PI * hoehe2)) != 0) {
				radius2 = Math.sqrt(3.0 * volumenKegel2 / (Math.PI * hoehe2));
				LOG.debug("Calculated with: Math.sqrt(3.0 * volumenKegel2 / (Math.PI * hoehe2))");
			} else {
				radius2 = Double.NaN;
				LOG.debug("Calculation for radius2 was not possible, set to NaN");
			}
		}

		if (durchmesser1 == 0) {
			LOG.debug("Calculating durchmesser1");
			if (!Double.isNaN(radius1) && radius1 != 0 && (2.0 * radius1) != 0) {
				durchmesser1 = 2.0 * radius1;
				LOG.debug("Calculated with: 2.0 * radius1");
			} else if (!Double.isNaN(flaecheninhalt1) && flaecheninhalt1 != 0
					&& (2.0 * Math.sqrt(flaecheninhalt1 / Math.PI)) != 0) {
				durchmesser1 = 2.0 * Math.sqrt(flaecheninhalt1 / Math.PI);
				LOG.debug("Calculated with: 2.0 * Math.sqrt(flaecheninhalt1 / Math.PI)");
			} else if (!Double.isNaN(umfang1) && umfang1 != 0 && (umfang1 / Math.PI) != 0) {
				durchmesser1 = umfang1 / Math.PI;
				LOG.debug("Calculated with: umfang1 / Math.PI");
			} else if (!Double.isNaN(kreisbogen1) && !Double.isNaN(alpha) && kreisbogen1 != 0 && alpha != 0
					&& ((kreisbogen1 * 360.0) / (alpha * Math.PI)) != 0) {
				durchmesser1 = (kreisbogen1 * 360.0) / (alpha * Math.PI);
				LOG.debug("Calculated with: (kreisbogen1 * 360.0) / (alpha * Math.PI)");
			} else if (!Double.isNaN(kreisauschnitt1) && !Double.isNaN(alpha) && kreisauschnitt1 != 0 && alpha != 0
					&& (2.0 * Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI))) != 0) {
				durchmesser1 = 2.0 * Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI));
				LOG.debug("Calculated with: 2.0 * Math.sqrt((kreisauschnitt1 * 360.0) / (alpha * Math.PI))");
			} else if (!Double.isNaN(kreisauschnitt1) && !Double.isNaN(kreisbogen1) && kreisauschnitt1 != 0
					&& kreisbogen1 != 0 && (4.0 * kreisauschnitt1 / kreisbogen1) != 0) {
				durchmesser1 = 4.0 * kreisauschnitt1 / kreisbogen1;
				LOG.debug("Calculated with: 4.0 * kreisauschnitt1 / kreisbogen1");
			} else if (!Double.isNaN(oberflaecheKugel1) && oberflaecheKugel1 != 0
					&& Math.sqrt(oberflaecheKugel1 / Math.PI) != 0) {
				durchmesser1 = Math.sqrt(oberflaecheKugel1 / Math.PI);
				LOG.debug("Calculated with: Math.sqrt(oberflaecheKugel1 / Math.PI)");
			} else if (!Double.isNaN(volumenKugel1) && volumenKugel1 != 0
					&& Math.cbrt(6.0 * volumenKugel1 / Math.PI) != 0) {
				durchmesser1 = Math.cbrt(6.0 * volumenKugel1 / Math.PI);
				LOG.debug("Calculated with: Math.cbrt(6.0 * volumenKugel1 / Math.PI)");
			} else {
				durchmesser1 = Double.NaN;
				LOG.debug("Calculation for durchmesser1 was not possible, set to NaN");
			}
		}

		if (durchmesser2 == 0) {
			LOG.debug("Calculating durchmesser2");
			if (!Double.isNaN(radius2) && radius2 != 0 && (2.0 * radius2) != 0) {
				durchmesser2 = 2.0 * radius2;
				LOG.debug("Calculated with: 2.0 * radius2");
			} else if (!Double.isNaN(flaecheninhalt2) && flaecheninhalt2 != 0
					&& (2.0 * Math.sqrt(flaecheninhalt2 / Math.PI)) != 0) {
				durchmesser2 = 2.0 * Math.sqrt(flaecheninhalt2 / Math.PI);
				LOG.debug("Calculated with: 2.0 * Math.sqrt(flaecheninhalt2 / Math.PI)");
			} else if (!Double.isNaN(umfang2) && umfang2 != 0 && (umfang2 / Math.PI) != 0) {
				durchmesser2 = umfang2 / Math.PI;
				LOG.debug("Calculated with: umfang2 / Math.PI");
			} else if (!Double.isNaN(kreisbogen2) && !Double.isNaN(alpha) && kreisbogen2 != 0 && alpha != 0
					&& ((kreisbogen2 * 360.0) / (alpha * Math.PI)) != 0) {
				durchmesser2 = (kreisbogen2 * 360.0) / (alpha * Math.PI);
				LOG.debug("Calculated with: (kreisbogen2 * 360.0) / (alpha * Math.PI)");
			} else if (!Double.isNaN(kreisauschnitt2) && !Double.isNaN(alpha) && kreisauschnitt2 != 0 && alpha != 0
					&& (2.0 * Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI))) != 0) {
				durchmesser2 = 2.0 * Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI));
				LOG.debug("Calculated with: 2.0 * Math.sqrt((kreisauschnitt2 * 360.0) / (alpha * Math.PI))");
			} else if (!Double.isNaN(kreisauschnitt2) && !Double.isNaN(kreisbogen2) && kreisauschnitt2 != 0
					&& kreisbogen2 != 0 && (4.0 * kreisauschnitt2 / kreisbogen2) != 0) {
				durchmesser2 = 4.0 * kreisauschnitt2 / kreisbogen2;
				LOG.debug("Calculated with: 4.0 * kreisauschnitt2 / kreisbogen2");
			} else if (!Double.isNaN(oberflaecheKugel2) && oberflaecheKugel2 != 0
					&& Math.sqrt(oberflaecheKugel2 / Math.PI) != 0) {
				durchmesser2 = Math.sqrt(oberflaecheKugel2 / Math.PI);
				LOG.debug("Calculated with: Math.sqrt(oberflaecheKugel2 / Math.PI)");
			} else if (!Double.isNaN(volumenKugel2) && volumenKugel2 != 0
					&& Math.cbrt(6.0 * volumenKugel2 / Math.PI) != 0) {
				durchmesser2 = Math.cbrt(6.0 * volumenKugel2 / Math.PI);
				LOG.debug("Calculated with: Math.cbrt(6.0 * volumenKugel2 / Math.PI)");
			} else {
				durchmesser2 = Double.NaN;
				LOG.debug("Calculation for durchmesser2 was not possible, set to NaN");
			}
		}

		if (umfang1 == 0) {
			LOG.debug("Calculating umfang1");
			if (!Double.isNaN(radius1) && radius1 != 0 && (2.0 * Math.PI * radius1) != 0) {
				umfang1 = 2.0 * Math.PI * radius1;
				LOG.debug("Calculated with: 2.0 * Math.PI * radius1");
			} else if (!Double.isNaN(durchmesser1) && durchmesser1 != 0 && (Math.PI * durchmesser1) != 0) {
				umfang1 = Math.PI * durchmesser1;
				LOG.debug("Calculated with: Math.PI * durchmesser1");
			} else if (!Double.isNaN(flaecheninhalt1) && flaecheninhalt1 != 0
					&& Math.sqrt(4.0 * Math.PI * flaecheninhalt1) != 0) {
				umfang1 = Math.sqrt(4.0 * Math.PI * flaecheninhalt1);
				LOG.debug("Calculated with: Math.sqrt(4.0 * Math.PI * flaecheninhalt1)");
			} else if (!Double.isNaN(kreisbogen1) && !Double.isNaN(alpha) && kreisbogen1 != 0 && alpha != 0
					&& ((kreisbogen1 * 360.0) / alpha) != 0) {
				umfang1 = (kreisbogen1 * 360.0) / alpha;
				LOG.debug("Calculated with: (kreisbogen1 * 360.0) / alpha");
			} else if (!Double.isNaN(mantelflaecheZylinder1) && !Double.isNaN(hoehe1) && mantelflaecheZylinder1 != 0
					&& hoehe1 != 0 && (mantelflaecheZylinder1 / hoehe1) != 0) {
				umfang1 = mantelflaecheZylinder1 / hoehe1;
				LOG.debug("Calculated with: mantelflaecheZylinder1 / hoehe1");
			} else {
				umfang1 = Double.NaN;
				LOG.debug("Calculation for umfang1 was not possible, set to NaN");
			}
		}

		if (umfang2 == 0) {
			LOG.debug("Calculating umfang2");
			if (!Double.isNaN(radius2) && radius2 != 0 && (2.0 * Math.PI * radius2) != 0) {
				umfang2 = 2.0 * Math.PI * radius2;
				LOG.debug("Calculated with: 2.0 * Math.PI * radius2");
			} else if (!Double.isNaN(durchmesser2) && durchmesser2 != 0 && (Math.PI * durchmesser2) != 0) {
				umfang2 = Math.PI * durchmesser2;
				LOG.debug("Calculated with: Math.PI * durchmesser2");
			} else if (!Double.isNaN(flaecheninhalt2) && flaecheninhalt2 != 0
					&& Math.sqrt(4.0 * Math.PI * flaecheninhalt2) != 0) {
				umfang2 = Math.sqrt(4.0 * Math.PI * flaecheninhalt2);
				LOG.debug("Calculated with: Math.sqrt(4.0 * Math.PI * flaecheninhalt2)");
			} else if (!Double.isNaN(kreisbogen2) && !Double.isNaN(alpha) && kreisbogen2 != 0 && alpha != 0
					&& ((kreisbogen2 * 360.0) / alpha) != 0) {
				umfang2 = (kreisbogen2 * 360.0) / alpha;
				LOG.debug("Calculated with: (kreisbogen2 * 360.0) / alpha");
			} else if (!Double.isNaN(mantelflaecheZylinder2) && !Double.isNaN(hoehe2) && mantelflaecheZylinder2 != 0
					&& hoehe2 != 0 && (mantelflaecheZylinder2 / hoehe2) != 0) {
				umfang2 = mantelflaecheZylinder2 / hoehe2;
				LOG.debug("Calculated with: mantelflaecheZylinder2 / hoehe2");
			} else {
				umfang2 = Double.NaN;
				LOG.debug("Calculation for umfang2 was not possible, set to NaN");
			}
		}

		if (flaecheninhalt1 == 0) {
			LOG.debug("Calculating flaecheninhalt1");
			if (!Double.isNaN(radius1) && radius1 != 0 && (Math.PI * Math.pow(radius1, 2.0)) != 0) {
				flaecheninhalt1 = Math.PI * Math.pow(radius1, 2.0);
				LOG.debug("Calculated with: Math.PI * radius1^2");
			} else if (!Double.isNaN(durchmesser1) && durchmesser1 != 0
					&& (Math.PI * Math.pow(durchmesser1 / 2.0, 2.0)) != 0) {
				flaecheninhalt1 = Math.PI * Math.pow(durchmesser1 / 2.0, 2);
				LOG.debug("Calculated with: Math.PI * (durchmesser1/2)^2");
			} else if (!Double.isNaN(umfang1) && umfang1 != 0 && (Math.pow(umfang1, 2.0) / (4.0 * Math.PI)) != 0) {
				flaecheninhalt1 = Math.pow(umfang1, 2.0) / (4.0 * Math.PI);
				LOG.debug("Calculated with: umfang1^2 / (4 * PI)");
			} else if (grundflaecheZylinder1 != 0) { // No NaN check needed
				flaecheninhalt1 = grundflaecheZylinder1;
				LOG.debug("Calculated with: grundflaecheZylinder1");
			} else if (grundflaecheKegel1 != 0) { // No NaN check needed
				flaecheninhalt1 = grundflaecheKegel1;
				LOG.debug("Calculated with: grundflaecheKegel1");
			} else if (!Double.isNaN(kreisauschnitt1) && !Double.isNaN(alpha) && kreisauschnitt1 != 0 && alpha != 0
					&& ((kreisauschnitt1 * 360.0) / alpha) != 0) {
				flaecheninhalt1 = (kreisauschnitt1 * 360.0) / alpha;
				LOG.debug("Calculated with: (kreisauschnitt1 * 360.0) / alpha");
			} else {
				flaecheninhalt1 = Double.NaN;
				LOG.debug("Calculation for flaecheninhalt1 was not possible, set to NaN");
			}
		}

		if (flaecheninhalt2 == 0) {
			LOG.debug("Calculating flaecheninhalt2");
			if (!Double.isNaN(radius2) && radius2 != 0 && (Math.PI * Math.pow(radius2, 2)) != 0) {
				flaecheninhalt2 = Math.PI * Math.pow(radius2, 2);
				LOG.debug("Calculated with: Math.PI * radius2^2");
			} else if (!Double.isNaN(durchmesser2) && durchmesser2 != 0
					&& (Math.PI * Math.pow(durchmesser2 / 2.0, 2)) != 0) {
				flaecheninhalt2 = Math.PI * Math.pow(durchmesser2 / 2.0, 2);
				LOG.debug("Calculated with: Math.PI * (durchmesser2/2)^2");
			} else if (!Double.isNaN(umfang2) && umfang2 != 0 && (Math.pow(umfang2, 2) / (4.0 * Math.PI)) != 0) {
				flaecheninhalt2 = Math.pow(umfang2, 2) / (4.0 * Math.PI);
				LOG.debug("Calculated with: umfang2^2 / (4 * PI)");
			} else if (grundflaecheZylinder2 != 0) {
				flaecheninhalt2 = grundflaecheZylinder2;
				LOG.debug("Calculated with: grundflaecheZylinder2");
			} else if (grundflaecheKegel2 != 0) {
				flaecheninhalt2 = grundflaecheKegel2;
				LOG.debug("Calculated with: grundflaecheKegel2");
			} else if (!Double.isNaN(kreisauschnitt2) && !Double.isNaN(alpha) && kreisauschnitt2 != 0 && alpha != 0
					&& ((kreisauschnitt2 * 360.0) / alpha) != 0) {
				flaecheninhalt2 = (kreisauschnitt2 * 360.0) / alpha;
				LOG.debug("Calculated with: (kreisauschnitt2 * 360.0) / alpha");
			} else {
				flaecheninhalt2 = Double.NaN;
				LOG.debug("Calculation for flaecheninhalt2 was not possible, set to NaN");
			}
		}

		if (flaecheninhaltGesamt == 0) {
			LOG.debug("Calculating flaecheninhaltGesamt");
			if (!Double.isNaN(flaecheninhalt1) && !Double.isNaN(flaecheninhalt2)
					&& (flaecheninhalt2 - flaecheninhalt1) >= 0) {
				flaecheninhaltGesamt = flaecheninhalt2 - flaecheninhalt1;
				LOG.debug("Calculated with: flaecheninhalt2 - flaecheninhalt1");
			} else if (!Double.isNaN(flaecheninhalt1) && !Double.isNaN(flaecheninhalt2)
					&& (flaecheninhalt1 - flaecheninhalt2) >= 0) {
				flaecheninhaltGesamt = flaecheninhalt1 - flaecheninhalt2;
				LOG.debug("Calculated with: flaecheninhalt1 - flaecheninhalt2");
			} else {
				flaecheninhaltGesamt = Double.NaN;
				LOG.debug("Calculation for flaecheninhaltGesamt was not possible, set to NaN");
			}
		}

		// --- Kreisteile ---
		if (kreisbogen1 == 0) {
			LOG.debug("Calculating kreisbogen1");
			if (!Double.isNaN(alpha) && !Double.isNaN(umfang1) && alpha != 0 && umfang1 != 0
					&& ((alpha / 360.0) * umfang1) != 0) {
				kreisbogen1 = (alpha / 360.0) * umfang1;
				LOG.debug("Calculated with: (alpha / 360.0) * umfang1");
			} else if (!Double.isNaN(alpha) && !Double.isNaN(radius1) && alpha != 0 && radius1 != 0
					&& ((alpha / 180.0) * Math.PI * radius1) != 0) {
				kreisbogen1 = (alpha / 180.0) * Math.PI * radius1;
				LOG.debug("Calculated with: (alpha / 180.0) * Math.PI * radius1");
			} else if (!Double.isNaN(alpha) && !Double.isNaN(durchmesser1) && alpha != 0 && durchmesser1 != 0
					&& ((alpha / 360.0) * (Math.PI * durchmesser1)) != 0) {
				kreisbogen1 = (alpha / 360.0) * (Math.PI * durchmesser1);
				LOG.debug("Calculated with: (alpha / 360.0) * (Math.PI * durchmesser1)");
			} else if (!Double.isNaN(kreisauschnitt1) && !Double.isNaN(radius1) && kreisauschnitt1 != 0 && radius1 != 0
					&& (2.0 * kreisauschnitt1 / radius1) != 0) {
				kreisbogen1 = 2.0 * kreisauschnitt1 / radius1;
				LOG.debug("Calculated with: 2.0 * kreisauschnitt1 / radius1");
			} else {
				kreisbogen1 = Double.NaN;
				LOG.debug("Calculation for kreisbogen1 was not possible, set to NaN");
			}
		}

		if (kreisbogen2 == 0) {
			LOG.debug("Calculating kreisbogen2");
			if (!Double.isNaN(alpha) && !Double.isNaN(umfang2) && alpha != 0 && umfang2 != 0
					&& ((alpha / 360.0) * umfang2) != 0) {
				kreisbogen2 = (alpha / 360.0) * umfang2;
				LOG.debug("Calculated with: (alpha / 360.0) * umfang2");
			} else if (!Double.isNaN(alpha) && !Double.isNaN(radius2) && alpha != 0 && radius2 != 0
					&& ((alpha / 180.0) * Math.PI * radius2) != 0) {
				kreisbogen2 = (alpha / 180.0) * Math.PI * radius2;
				LOG.debug("Calculated with: (alpha / 180.0) * Math.PI * radius2");
			} else if (!Double.isNaN(alpha) && !Double.isNaN(durchmesser2) && alpha != 0 && durchmesser2 != 0
					&& ((alpha / 360.0) * (Math.PI * durchmesser2)) != 0) {
				kreisbogen2 = (alpha / 360.0) * (Math.PI * durchmesser2);
				LOG.debug("Calculated with: (alpha / 360.0) * (Math.PI * durchmesser2)");
			} else if (!Double.isNaN(kreisauschnitt2) && !Double.isNaN(radius2) && kreisauschnitt2 != 0 && radius2 != 0
					&& (2.0 * kreisauschnitt2 / radius2) != 0) {
				kreisbogen2 = 2.0 * kreisauschnitt2 / radius2;
				LOG.debug("Calculated with: 2.0 * kreisauschnitt2 / radius2");
			} else {
				kreisbogen2 = Double.NaN;
				LOG.debug("Calculation for kreisbogen2 was not possible, set to NaN");
			}
		}

		if (kreisauschnitt1 == 0) {
			LOG.debug("Calculating kreisauschnitt1");
			if (!Double.isNaN(alpha) && !Double.isNaN(flaecheninhalt1) && alpha != 0 && flaecheninhalt1 != 0
					&& ((alpha / 360.0) * flaecheninhalt1) != 0) {
				kreisauschnitt1 = (alpha / 360.0) * flaecheninhalt1;
				LOG.debug("Calculated with: (alpha / 360.0) * flaecheninhalt1");
			} else if (!Double.isNaN(alpha) && !Double.isNaN(radius1) && alpha != 0 && radius1 != 0
					&& ((alpha / 360.0) * (Math.PI * Math.pow(radius1, 2))) != 0) {
				kreisauschnitt1 = (alpha / 360.0) * (Math.PI * Math.pow(radius1, 2));
				LOG.debug("Calculated with: (alpha / 360.0) * (PI * radius1^2)");
			} else if (!Double.isNaN(kreisbogen1) && !Double.isNaN(radius1) && kreisbogen1 != 0 && radius1 != 0
					&& ((kreisbogen1 * radius1) / 2.0) != 0) {
				kreisauschnitt1 = (kreisbogen1 * radius1) / 2.0;
				LOG.debug("Calculated with: (kreisbogen1 * radius1) / 2.0");
			} else {
				kreisauschnitt1 = Double.NaN;
				LOG.debug("Calculation for kreisauschnitt1 was not possible, set to NaN");
			}
		}

		if (kreisauschnitt2 == 0) {
			LOG.debug("Calculating kreisauschnitt2");
			if (!Double.isNaN(alpha) && !Double.isNaN(flaecheninhalt2) && alpha != 0 && flaecheninhalt2 != 0
					&& ((alpha / 360.0) * flaecheninhalt2) != 0) {
				kreisauschnitt2 = (alpha / 360.0) * flaecheninhalt2;
				LOG.debug("Calculated with: (alpha / 360.0) * flaecheninhalt2");
			} else if (!Double.isNaN(alpha) && !Double.isNaN(radius2) && alpha != 0 && radius2 != 0
					&& ((alpha / 360.0) * (Math.PI * Math.pow(radius2, 2))) != 0) {
				kreisauschnitt2 = (alpha / 360.0) * (Math.PI * Math.pow(radius2, 2));
				LOG.debug("Calculated with: (alpha / 360.0) * (PI * radius2^2)");
			} else if (!Double.isNaN(kreisbogen2) && !Double.isNaN(radius2) && kreisbogen2 != 0 && radius2 != 0
					&& ((kreisbogen2 * radius2) / 2.0) != 0) {
				kreisauschnitt2 = (kreisbogen2 * radius2) / 2.0;
				LOG.debug("Calculated with: (kreisbogen2 * radius2) / 2.0");
			} else {
				kreisauschnitt2 = Double.NaN;
				LOG.debug("Calculation for kreisauschnitt2 was not possible, set to NaN");
			}
		}

		if (kreisausschnittGesamt == 0) {
			LOG.debug("Calculating kreisausschnittGesamt");
			if (!Double.isNaN(kreisauschnitt1) && !Double.isNaN(kreisauschnitt2)
					&& (kreisauschnitt2 - kreisauschnitt1) >= 0) {
				kreisausschnittGesamt = kreisauschnitt2 - kreisauschnitt1;
				LOG.debug("Calculated with: kreisauschnitt2 - kreisauschnitt1");
			} else if (!Double.isNaN(kreisauschnitt1) && !Double.isNaN(kreisauschnitt2)
					&& (kreisauschnitt1 - kreisauschnitt2) >= 0) {
				kreisausschnittGesamt = kreisauschnitt1 - kreisauschnitt2;
				LOG.debug("Calculated with: kreisauschnitt1 - kreisauschnitt2");
			} else {
				kreisausschnittGesamt = Double.NaN;
				LOG.debug("Calculation for kreisausschnittGesamt was not possible, set to NaN");
			}
		}

		if (alpha == 0) {
			LOG.debug("Calculating alpha");
			if (!Double.isNaN(kreisbogen1) && !Double.isNaN(umfang1) && kreisbogen1 != 0 && umfang1 != 0
					&& ((kreisbogen1 / umfang1) * 360.0) != 0) {
				alpha = (kreisbogen1 / umfang1) * 360.0;
				LOG.debug("Calculated with: (kreisbogen1 / umfang1) * 360.0");
			} else if (!Double.isNaN(kreisauschnitt1) && !Double.isNaN(flaecheninhalt1) && kreisauschnitt1 != 0
					&& flaecheninhalt1 != 0 && ((kreisauschnitt1 / flaecheninhalt1) * 360.0) != 0) {
				alpha = (kreisauschnitt1 / flaecheninhalt1) * 360.0;
				LOG.debug("Calculated with: (kreisauschnitt1 / flaecheninhalt1) * 360.0");
			} else if (!Double.isNaN(kreisbogen2) && !Double.isNaN(umfang2) && kreisbogen2 != 0 && umfang2 != 0
					&& ((kreisbogen2 / umfang2) * 360.0) != 0) {
				alpha = (kreisbogen2 / umfang2) * 360.0;
				LOG.debug("Calculated with: (kreisbogen2 / umfang2) * 360.0");
			} else if (!Double.isNaN(kreisauschnitt2) && !Double.isNaN(flaecheninhalt2) && kreisauschnitt2 != 0
					&& flaecheninhalt2 != 0 && ((kreisauschnitt2 / flaecheninhalt2) * 360.0) != 0) {
				alpha = (kreisauschnitt2 / flaecheninhalt2) * 360.0;
				LOG.debug("Calculated with: (kreisauschnitt2 / flaecheninhalt2) * 360.0");
			} else if (!Double.isNaN(kreisbogen1) && !Double.isNaN(radius1) && kreisbogen1 != 0 && radius1 != 0
					&& ((kreisbogen1 * 180.0) / (Math.PI * radius1)) != 0) {
				alpha = (kreisbogen1 * 180.0) / (Math.PI * radius1);
				LOG.debug("Calculated with: (kreisbogen1 * 180.0) / (Math.PI * radius1)");
			} else if (!Double.isNaN(kreisbogen2) && !Double.isNaN(radius2) && kreisbogen2 != 0 && radius2 != 0
					&& ((kreisbogen2 * 180.0) / (Math.PI * radius2)) != 0) {
				alpha = (kreisbogen2 * 180.0) / (Math.PI * radius2);
				LOG.debug("Calculated with: (kreisbogen2 * 180.0) / (Math.PI * radius2)");
			} else if (!Double.isNaN(kreisauschnitt1) && !Double.isNaN(radius1) && kreisauschnitt1 != 0 && radius1 != 0
					&& ((kreisauschnitt1 * 360.0) / (Math.PI * Math.pow(radius1, 2))) != 0) {
				alpha = (kreisauschnitt1 * 360.0) / (Math.PI * Math.pow(radius1, 2));
				LOG.debug("Calculated with: (kreisauschnitt1 * 360.0) / (PI * radius1^2)");
			} else if (!Double.isNaN(kreisauschnitt2) && !Double.isNaN(radius2) && kreisauschnitt2 != 0 && radius2 != 0
					&& ((kreisauschnitt2 * 360.0) / (Math.PI * Math.pow(radius2, 2))) != 0) {
				alpha = (kreisauschnitt2 * 360.0) / (Math.PI * Math.pow(radius2, 2));
				LOG.debug("Calculated with: (kreisauschnitt2 * 360.0) / (PI * radius2^2)");
			} else {
				alpha = Double.NaN;
				LOG.debug("Calculation for alpha was not possible, set to NaN");
			}
		}

		// --- Zylinder ---
		if (hoehe1 == 0) {
			LOG.debug("Calculating hoehe1");
			if (!Double.isNaN(mantelflaecheZylinder1) && !Double.isNaN(radius1) && mantelflaecheZylinder1 != 0
					&& radius1 != 0 && (mantelflaecheZylinder1 / (2.0 * Math.PI * radius1)) != 0) {
				hoehe1 = mantelflaecheZylinder1 / (2.0 * Math.PI * radius1);
				LOG.debug("Calculated with: mantelflaecheZylinder1 / (2 * PI * radius1)");
			} else if (!Double.isNaN(volumenZylinder1) && !Double.isNaN(grundflaecheZylinder1) && volumenZylinder1 != 0
					&& grundflaecheZylinder1 != 0 && (volumenZylinder1 / grundflaecheZylinder1) != 0) {
				hoehe1 = volumenZylinder1 / grundflaecheZylinder1;
				LOG.debug("Calculated with: volumenZylinder1 / grundflaecheZylinder1");
			} else if (!Double.isNaN(volumenZylinder1) && !Double.isNaN(radius1) && volumenZylinder1 != 0
					&& radius1 != 0 && (volumenZylinder1 / (Math.PI * Math.pow(radius1, 2))) != 0) {
				hoehe1 = volumenZylinder1 / (Math.PI * Math.pow(radius1, 2));
				LOG.debug("Calculated with: volumenZylinder1 / (PI * radius1^2)");
			} else if (!Double.isNaN(oberflaecheZylinder1) && !Double.isNaN(radius1) && oberflaecheZylinder1 != 0
					&& radius1 != 0 && ((oberflaecheZylinder1 / (2.0 * Math.PI * radius1)) - radius1) != 0) {
				hoehe1 = (oberflaecheZylinder1 / (2.0 * Math.PI * radius1)) - radius1;
				LOG.debug("Calculated with: (oberflaecheZylinder1 / (2 * PI * radius1)) - radius1");
			} else if (!Double.isNaN(seitenhoehe1) && !Double.isNaN(radius1) && seitenhoehe1 != 0 && radius1 != 0
					&& Math.sqrt(Math.pow(seitenhoehe1, 2) - Math.pow(radius1, 2)) != 0) {
				hoehe1 = Math.sqrt(Math.pow(seitenhoehe1, 2) - Math.pow(radius1, 2));
				LOG.debug("Calculated with: Math.sqrt(seitenhoehe1^2 - radius1^2) (from cone)");
			} else if (!Double.isNaN(volumenKegel1) && !Double.isNaN(grundflaecheKegel1) && volumenKegel1 != 0
					&& grundflaecheKegel1 != 0 && (3.0 * volumenKegel1 / grundflaecheKegel1) != 0) {
				hoehe1 = 3.0 * volumenKegel1 / grundflaecheKegel1;
				LOG.debug("Calculated with: 3 * volumenKegel1 / grundflaecheKegel1");
			} else if (!Double.isNaN(volumenKegel1) && !Double.isNaN(radius1) && volumenKegel1 != 0 && radius1 != 0
					&& (3.0 * volumenKegel1 / (Math.PI * Math.pow(radius1, 2))) != 0) {
				hoehe1 = 3.0 * volumenKegel1 / (Math.PI * Math.pow(radius1, 2));
				LOG.debug("Calculated with: 3 * volumenKegel1 / (PI * radius1^2)");
			} else {
				hoehe1 = Double.NaN;
				LOG.debug("Calculation for hoehe1 was not possible, set to NaN");
			}
		}

		if (hoehe2 == 0) {
			LOG.debug("Calculating hoehe2");
			if (!Double.isNaN(mantelflaecheZylinder2) && !Double.isNaN(radius2) && mantelflaecheZylinder2 != 0
					&& radius2 != 0 && (mantelflaecheZylinder2 / (2.0 * Math.PI * radius2)) != 0) {
				hoehe2 = mantelflaecheZylinder2 / (2.0 * Math.PI * radius2);
				LOG.debug("Calculated with: mantelflaecheZylinder2 / (2 * PI * radius2)");
			} else if (!Double.isNaN(volumenZylinder2) && !Double.isNaN(grundflaecheZylinder2) && volumenZylinder2 != 0
					&& grundflaecheZylinder2 != 0 && (volumenZylinder2 / grundflaecheZylinder2) != 0) {
				hoehe2 = volumenZylinder2 / grundflaecheZylinder2;
				LOG.debug("Calculated with: volumenZylinder2 / grundflaecheZylinder2");
			} else if (!Double.isNaN(volumenZylinder2) && !Double.isNaN(radius2) && volumenZylinder2 != 0
					&& radius2 != 0 && (volumenZylinder2 / (Math.PI * Math.pow(radius2, 2))) != 0) {
				hoehe2 = volumenZylinder2 / (Math.PI * Math.pow(radius2, 2));
				LOG.debug("Calculated with: volumenZylinder2 / (PI * radius2^2)");
			} else if (!Double.isNaN(oberflaecheZylinder2) && !Double.isNaN(radius2) && oberflaecheZylinder2 != 0
					&& radius2 != 0 && ((oberflaecheZylinder2 / (2.0 * Math.PI * radius2)) - radius2) != 0) {
				hoehe2 = (oberflaecheZylinder2 / (2.0 * Math.PI * radius2)) - radius2;
				LOG.debug("Calculated with: (oberflaecheZylinder2 / (2 * PI * radius2)) - radius2");
			} else if (!Double.isNaN(seitenhoehe2) && !Double.isNaN(radius2) && seitenhoehe2 != 0 && radius2 != 0
					&& Math.sqrt(Math.pow(seitenhoehe2, 2) - Math.pow(radius2, 2)) != 0) {
				hoehe2 = Math.sqrt(Math.pow(seitenhoehe2, 2) - Math.pow(radius2, 2));
				LOG.debug("Calculated with: Math.sqrt(seitenhoehe2^2 - radius2^2) (from cone)");
			} else if (!Double.isNaN(volumenKegel2) && !Double.isNaN(grundflaecheKegel2) && volumenKegel2 != 0
					&& grundflaecheKegel2 != 0 && (3.0 * volumenKegel2 / grundflaecheKegel2) != 0) {
				hoehe2 = 3.0 * volumenKegel2 / grundflaecheKegel2;
				LOG.debug("Calculated with: 3 * volumenKegel2 / grundflaecheKegel2");
			} else if (!Double.isNaN(volumenKegel2) && !Double.isNaN(radius2) && volumenKegel2 != 0 && radius2 != 0
					&& (3.0 * volumenKegel2 / (Math.PI * Math.pow(radius2, 2))) != 0) {
				hoehe2 = 3.0 * volumenKegel2 / (Math.PI * Math.pow(radius2, 2));
				LOG.debug("Calculated with: 3 * volumenKegel2 / (PI * radius2^2)");
			} else {
				hoehe2 = Double.NaN;
				LOG.debug("Calculation for hoehe2 was not possible, set to NaN");
			}
		}

		if (grundflaecheZylinder1 == 0) {
			LOG.debug("Calculating grundflaecheZylinder1");
			if (!Double.isNaN(radius1) && radius1 != 0 && (Math.PI * Math.pow(radius1, 2)) != 0) {
				grundflaecheZylinder1 = Math.PI * Math.pow(radius1, 2);
				LOG.debug("Calculated with: PI * radius1^2");
			} else if (flaecheninhalt1 != 0) {
				grundflaecheZylinder1 = flaecheninhalt1;
				LOG.debug("Calculated with: flaecheninhalt1");
			} else if (!Double.isNaN(volumenZylinder1) && !Double.isNaN(hoehe1) && volumenZylinder1 != 0 && hoehe1 != 0
					&& (volumenZylinder1 / hoehe1) != 0) {
				grundflaecheZylinder1 = volumenZylinder1 / hoehe1;
				LOG.debug("Calculated with: volumenZylinder1 / hoehe1");
			} else if (!Double.isNaN(oberflaecheZylinder1) && !Double.isNaN(mantelflaecheZylinder1)
					&& oberflaecheZylinder1 != 0 && mantelflaecheZylinder1 != 0
					&& ((oberflaecheZylinder1 - mantelflaecheZylinder1) / 2.0) != 0) {
				grundflaecheZylinder1 = (oberflaecheZylinder1 - mantelflaecheZylinder1) / 2.0;
				LOG.debug("Calculated with: (oberflaecheZylinder1 - mantelflaecheZylinder1) / 2.0");
			} else {
				grundflaecheZylinder1 = Double.NaN;
				LOG.debug("Calculation for grundflaecheZylinder1 was not possible, set to NaN");
			}
		}

		if (grundflaecheZylinder2 == 0) {
			LOG.debug("Calculating grundflaecheZylinder2");
			if (!Double.isNaN(radius2) && radius2 != 0 && (Math.PI * Math.pow(radius2, 2)) != 0) {
				grundflaecheZylinder2 = Math.PI * Math.pow(radius2, 2);
				LOG.debug("Calculated with: PI * radius2^2");
			} else if (flaecheninhalt2 != 0) {
				grundflaecheZylinder2 = flaecheninhalt2;
				LOG.debug("Calculated with: flaecheninhalt2");
			} else if (!Double.isNaN(volumenZylinder2) && !Double.isNaN(hoehe2) && volumenZylinder2 != 0 && hoehe2 != 0
					&& (volumenZylinder2 / hoehe2) != 0) {
				grundflaecheZylinder2 = volumenZylinder2 / hoehe2;
				LOG.debug("Calculated with: volumenZylinder2 / hoehe2");
			} else if (!Double.isNaN(oberflaecheZylinder2) && !Double.isNaN(mantelflaecheZylinder2)
					&& oberflaecheZylinder2 != 0 && mantelflaecheZylinder2 != 0
					&& ((oberflaecheZylinder2 - mantelflaecheZylinder2) / 2.0) != 0) {
				grundflaecheZylinder2 = (oberflaecheZylinder2 - mantelflaecheZylinder2) / 2.0;
				LOG.debug("Calculated with: (oberflaecheZylinder2 - mantelflaecheZylinder2) / 2.0");
			} else {
				grundflaecheZylinder2 = Double.NaN;
				LOG.debug("Calculation for grundflaecheZylinder2 was not possible, set to NaN");
			}
		}

		if (grundflaecheZylinderGesamt == 0) {
			LOG.debug("Calculating grundflaecheZylinderGesamt");
			if (!Double.isNaN(grundflaecheZylinder1) && !Double.isNaN(grundflaecheZylinder2)
					&& (grundflaecheZylinder2 - grundflaecheZylinder1) >= 0) {
				grundflaecheZylinderGesamt = grundflaecheZylinder2 - grundflaecheZylinder1;
				LOG.debug("Calculated with: grundflaecheZylinder2 - grundflaecheZylinder1");
			} else if (!Double.isNaN(grundflaecheZylinder1) && !Double.isNaN(grundflaecheZylinder2)
					&& (grundflaecheZylinder1 - grundflaecheZylinder2) >= 0) {
				grundflaecheZylinderGesamt = grundflaecheZylinder1 - grundflaecheZylinder2;
				LOG.debug("Calculated with: grundflaecheZylinder1 - grundflaecheZylinder2");
			} else {
				grundflaecheZylinderGesamt = Double.NaN;
				LOG.debug("Calculation for grundflaecheZylinderGesamt was not possible, set to NaN");
			}
		}

		if (mantelflaecheZylinder1 == 0) {
			LOG.debug("Calculating mantelflaecheZylinder1");
			if (!Double.isNaN(radius1) && !Double.isNaN(hoehe1) && radius1 != 0 && hoehe1 != 0
					&& (2.0 * Math.PI * radius1 * hoehe1) != 0) {
				mantelflaecheZylinder1 = 2.0 * Math.PI * radius1 * hoehe1;
				LOG.debug("Calculated with: 2 * PI * radius1 * hoehe1");
			} else if (!Double.isNaN(umfang1) && !Double.isNaN(hoehe1) && umfang1 != 0 && hoehe1 != 0
					&& (umfang1 * hoehe1) != 0) {
				mantelflaecheZylinder1 = umfang1 * hoehe1;
				LOG.debug("Calculated with: umfang1 * hoehe1");
			} else if (!Double.isNaN(oberflaecheZylinder1) && !Double.isNaN(grundflaecheZylinder1)
					&& oberflaecheZylinder1 != 0 && grundflaecheZylinder1 != 0
					&& (oberflaecheZylinder1 - 2.0 * grundflaecheZylinder1) != 0) {
				mantelflaecheZylinder1 = oberflaecheZylinder1 - 2.0 * grundflaecheZylinder1;
				LOG.debug("Calculated with: oberflaecheZylinder1 - 2 * grundflaecheZylinder1");
			} else if (!Double.isNaN(oberflaecheZylinder1) && !Double.isNaN(radius1) && oberflaecheZylinder1 != 0
					&& radius1 != 0 && (oberflaecheZylinder1 - 2.0 * (Math.PI * Math.pow(radius1, 2))) != 0) {
				mantelflaecheZylinder1 = oberflaecheZylinder1 - 2.0 * (Math.PI * Math.pow(radius1, 2));
				LOG.debug("Calculated with: oberflaecheZylinder1 - 2 * (PI * radius1^2)");
			} else {
				mantelflaecheZylinder1 = Double.NaN;
				LOG.debug("Calculation for mantelflaecheZylinder1 was not possible, set to NaN");
			}
		}

		if (mantelflaecheZylinder2 == 0) {
			LOG.debug("Calculating mantelflaecheZylinder2");
			if (!Double.isNaN(radius2) && !Double.isNaN(hoehe2) && radius2 != 0 && hoehe2 != 0
					&& (2.0 * Math.PI * radius2 * hoehe2) != 0) {
				mantelflaecheZylinder2 = 2.0 * Math.PI * radius2 * hoehe2;
				LOG.debug("Calculated with: 2 * PI * radius2 * hoehe2");
			} else if (!Double.isNaN(umfang2) && !Double.isNaN(hoehe2) && umfang2 != 0 && hoehe2 != 0
					&& (umfang2 * hoehe2) != 0) {
				mantelflaecheZylinder2 = umfang2 * hoehe2;
				LOG.debug("Calculated with: umfang2 * hoehe2");
			} else if (!Double.isNaN(oberflaecheZylinder2) && !Double.isNaN(grundflaecheZylinder2)
					&& oberflaecheZylinder2 != 0 && grundflaecheZylinder2 != 0
					&& (oberflaecheZylinder2 - 2.0 * grundflaecheZylinder2) != 0) {
				mantelflaecheZylinder2 = oberflaecheZylinder2 - 2.0 * grundflaecheZylinder2;
				LOG.debug("Calculated with: oberflaecheZylinder2 - 2 * grundflaecheZylinder2");
			} else if (!Double.isNaN(oberflaecheZylinder2) && !Double.isNaN(radius2) && oberflaecheZylinder2 != 0
					&& radius2 != 0 && (oberflaecheZylinder2 - 2.0 * (Math.PI * Math.pow(radius2, 2))) != 0) {
				mantelflaecheZylinder2 = oberflaecheZylinder2 - 2.0 * (Math.PI * Math.pow(radius2, 2));
				LOG.debug("Calculated with: oberflaecheZylinder2 - 2 * (PI * radius2^2)");
			} else {
				mantelflaecheZylinder2 = Double.NaN;
				LOG.debug("Calculation for mantelflaecheZylinder2 was not possible, set to NaN");
			}
		}

		if (mantelflaecheZylinderGesamt == 0) {
			LOG.debug("Calculating mantelflaecheZylinderGesamt");
			if (!Double.isNaN(mantelflaecheZylinder1) && !Double.isNaN(mantelflaecheZylinder2)
					&& (mantelflaecheZylinder2 + mantelflaecheZylinder1) != 0) {
				mantelflaecheZylinderGesamt = mantelflaecheZylinder2 + mantelflaecheZylinder1;
				LOG.debug("Calculated with: mantelflaecheZylinder2 + mantelflaecheZylinder1");
			} else {
				mantelflaecheZylinderGesamt = Double.NaN;
				LOG.debug("Calculation for mantelflaecheZylinderGesamt was not possible, set to NaN");
			}
		}

		if (oberflaecheZylinder1 == 0) {
			LOG.debug("Calculating oberflaecheZylinder1");
			if (!Double.isNaN(grundflaecheZylinder1) && !Double.isNaN(mantelflaecheZylinder1)
					&& grundflaecheZylinder1 != 0 && mantelflaecheZylinder1 != 0
					&& (2.0 * grundflaecheZylinder1 + mantelflaecheZylinder1) != 0) {
				oberflaecheZylinder1 = 2.0 * grundflaecheZylinder1 + mantelflaecheZylinder1;
				LOG.debug("Calculated with: 2 * grundflaecheZylinder1 + mantelflaecheZylinder1");
			} else if (!Double.isNaN(radius1) && !Double.isNaN(hoehe1) && radius1 != 0 && hoehe1 != 0
					&& (2.0 * (Math.PI * Math.pow(radius1, 2)) + (2.0 * Math.PI * radius1 * hoehe1)) != 0) {
				oberflaecheZylinder1 = 2.0 * (Math.PI * Math.pow(radius1, 2)) + (2.0 * Math.PI * radius1 * hoehe1);
				LOG.debug("Calculated with: 2*(PI*r1^2) + 2*PI*r1*h1");
			} else if (!Double.isNaN(radius1) && !Double.isNaN(hoehe1) && radius1 != 0 && hoehe1 != 0
					&& (2.0 * Math.PI * radius1 * (radius1 + hoehe1)) != 0) {
				oberflaecheZylinder1 = 2.0 * Math.PI * radius1 * (radius1 + hoehe1);
				LOG.debug("Calculated with: 2 * PI * radius1 * (radius1 + hoehe1)");
			} else {
				oberflaecheZylinder1 = Double.NaN;
				LOG.debug("Calculation for oberflaecheZylinder1 was not possible, set to NaN");
			}
		}

		if (oberflaecheZylinder2 == 0) {
			LOG.debug("Calculating oberflaecheZylinder2");
			if (!Double.isNaN(grundflaecheZylinder2) && !Double.isNaN(mantelflaecheZylinder2)
					&& grundflaecheZylinder2 != 0 && mantelflaecheZylinder2 != 0
					&& (2.0 * grundflaecheZylinder2 + mantelflaecheZylinder2) != 0) {
				oberflaecheZylinder2 = 2.0 * grundflaecheZylinder2 + mantelflaecheZylinder2;
				LOG.debug("Calculated with: 2 * grundflaecheZylinder2 + mantelflaecheZylinder2");
			} else if (!Double.isNaN(radius2) && !Double.isNaN(hoehe2) && radius2 != 0 && hoehe2 != 0
					&& (2.0 * (Math.PI * Math.pow(radius2, 2)) + (2.0 * Math.PI * radius2 * hoehe2)) != 0) {
				oberflaecheZylinder2 = 2.0 * (Math.PI * Math.pow(radius2, 2)) + (2.0 * Math.PI * radius2 * hoehe2);
				LOG.debug("Calculated with: 2*(PI*r2^2) + 2*PI*r2*h2");
			} else if (!Double.isNaN(radius2) && !Double.isNaN(hoehe2) && radius2 != 0 && hoehe2 != 0
					&& (2.0 * Math.PI * radius2 * (radius2 + hoehe2)) != 0) {
				oberflaecheZylinder2 = 2.0 * Math.PI * radius2 * (radius2 + hoehe2);
				LOG.debug("Calculated with: 2 * PI * radius2 * (radius2 + hoehe2)");
			} else {
				oberflaecheZylinder2 = Double.NaN;
				LOG.debug("Calculation for oberflaecheZylinder2 was not possible, set to NaN");
			}
		}

		if (oberflaecheZylinderGesamt == 0) {
			LOG.debug("Calculating oberflaecheZylinderGesamt");
			if (!Double.isNaN(mantelflaecheZylinderGesamt) && !Double.isNaN(grundflaecheZylinderGesamt)
					&& mantelflaecheZylinderGesamt != 0 && grundflaecheZylinderGesamt != 0
					&& (mantelflaecheZylinderGesamt + 2.0 * grundflaecheZylinderGesamt) != 0) {
				oberflaecheZylinderGesamt = mantelflaecheZylinderGesamt + 2.0 * grundflaecheZylinderGesamt;
				LOG.debug("Calculated with: mantelflaecheZylinderGesamt + 2 * grundflaecheZylinderGesamt");
			} else {
				oberflaecheZylinderGesamt = Double.NaN;
				LOG.debug("Calculation for oberflaecheZylinderGesamt was not possible, set to NaN");
			}
		}

		if (volumenZylinder1 == 0) {
			LOG.debug("Calculating volumenZylinder1");
			if (!Double.isNaN(grundflaecheZylinder1) && !Double.isNaN(hoehe1) && grundflaecheZylinder1 != 0
					&& hoehe1 != 0 && (grundflaecheZylinder1 * hoehe1) != 0) {
				volumenZylinder1 = grundflaecheZylinder1 * hoehe1;
				LOG.debug("Calculated with: grundflaecheZylinder1 * hoehe1");
			} else if (!Double.isNaN(radius1) && !Double.isNaN(hoehe1) && radius1 != 0 && hoehe1 != 0
					&& (Math.PI * Math.pow(radius1, 2) * hoehe1) != 0) {
				volumenZylinder1 = Math.PI * Math.pow(radius1, 2) * hoehe1;
				LOG.debug("Calculated with: PI * radius1^2 * hoehe1");
			} else {
				volumenZylinder1 = Double.NaN;
				LOG.debug("Calculation for volumenZylinder1 was not possible, set to NaN");
			}
		}

		if (volumenZylinder2 == 0) {
			LOG.debug("Calculating volumenZylinder2");
			if (!Double.isNaN(grundflaecheZylinder2) && !Double.isNaN(hoehe2) && grundflaecheZylinder2 != 0
					&& hoehe2 != 0 && (grundflaecheZylinder2 * hoehe2) != 0) {
				volumenZylinder2 = grundflaecheZylinder2 * hoehe2;
				LOG.debug("Calculated with: grundflaecheZylinder2 * hoehe2");
			} else if (!Double.isNaN(radius2) && !Double.isNaN(hoehe2) && radius2 != 0 && hoehe2 != 0
					&& (Math.PI * Math.pow(radius2, 2) * hoehe2) != 0) {
				volumenZylinder2 = Math.PI * Math.pow(radius2, 2) * hoehe2;
				LOG.debug("Calculated with: PI * radius2^2 * hoehe2");
			} else {
				volumenZylinder2 = Double.NaN;
				LOG.debug("Calculation for volumenZylinder2 was not possible, set to NaN");
			}
		}

		if (volumenZylinderGesamt == 0) {
			LOG.debug("Calculating volumenZylinderGesamt");
			if (!Double.isNaN(volumenZylinder1) && !Double.isNaN(volumenZylinder2)
					&& (volumenZylinder2 - volumenZylinder1) >= 0) {
				volumenZylinderGesamt = volumenZylinder2 - volumenZylinder1;
				LOG.debug("Calculated with: volumenZylinder2 - volumenZylinder1");
			} else if (!Double.isNaN(volumenZylinder1) && !Double.isNaN(volumenZylinder2)
					&& (volumenZylinder1 - volumenZylinder2) >= 0) {
				volumenZylinderGesamt = volumenZylinder1 - volumenZylinder2;
				LOG.debug("Calculated with: volumenZylinder1 - volumenZylinder2");
			} else {
				volumenZylinderGesamt = Double.NaN;
				LOG.debug("Calculation for volumenZylinderGesamt was not possible, set to NaN");
			}
		}

		// --- Kegel ---
		if (seitenhoehe1 == 0) {
			LOG.debug("Calculating seitenhoehe1");
			if (!Double.isNaN(radius1) && !Double.isNaN(hoehe1) && radius1 != 0 && hoehe1 != 0
					&& Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2)) != 0) {
				seitenhoehe1 = Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2));
				LOG.debug("Calculated with: Math.sqrt(radius1^2 + hoehe1^2)");
			} else if (!Double.isNaN(mantelflaecheKegel1) && !Double.isNaN(radius1) && mantelflaecheKegel1 != 0
					&& radius1 != 0 && (mantelflaecheKegel1 / (Math.PI * radius1)) != 0) {
				seitenhoehe1 = mantelflaecheKegel1 / (Math.PI * radius1);
				LOG.debug("Calculated with: mantelflaecheKegel1 / (PI * radius1)");
			} else if (!Double.isNaN(oberflaecheKegel1) && !Double.isNaN(radius1) && oberflaecheKegel1 != 0
					&& radius1 != 0 && ((oberflaecheKegel1 / (Math.PI * radius1)) - radius1) != 0) {
				seitenhoehe1 = (oberflaecheKegel1 / (Math.PI * radius1)) - radius1;
				LOG.debug("Calculated with: (oberflaecheKegel1 / (PI * radius1)) - radius1");
			} else {
				seitenhoehe1 = Double.NaN;
				LOG.debug("Calculation for seitenhoehe1 was not possible, set to NaN");
			}
		}

		if (seitenhoehe2 == 0) {
			LOG.debug("Calculating seitenhoehe2");
			if (!Double.isNaN(radius2) && !Double.isNaN(hoehe2) && radius2 != 0 && hoehe2 != 0
					&& Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2)) != 0) {
				seitenhoehe2 = Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2));
				LOG.debug("Calculated with: Math.sqrt(radius2^2 + hoehe2^2)");
			} else if (!Double.isNaN(mantelflaecheKegel2) && !Double.isNaN(radius2) && mantelflaecheKegel2 != 0
					&& radius2 != 0 && (mantelflaecheKegel2 / (Math.PI * radius2)) != 0) {
				seitenhoehe2 = mantelflaecheKegel2 / (Math.PI * radius2);
				LOG.debug("Calculated with: mantelflaecheKegel2 / (PI * radius2)");
			} else if (!Double.isNaN(oberflaecheKegel2) && !Double.isNaN(radius2) && oberflaecheKegel2 != 0
					&& radius2 != 0 && ((oberflaecheKegel2 / (Math.PI * radius2)) - radius2) != 0) {
				seitenhoehe2 = (oberflaecheKegel2 / (Math.PI * radius2)) - radius2;
				LOG.debug("Calculated with: (oberflaecheKegel2 / (PI * radius2)) - radius2");
			} else {
				seitenhoehe2 = Double.NaN;
				LOG.debug("Calculation for seitenhoehe2 was not possible, set to NaN");
			}
		}

		if (grundflaecheKegel1 == 0) {
			LOG.debug("Calculating grundflaecheKegel1");
			if (!Double.isNaN(radius1) && radius1 != 0 && (Math.PI * Math.pow(radius1, 2)) != 0) {
				grundflaecheKegel1 = Math.PI * Math.pow(radius1, 2);
				LOG.debug("Calculated with: PI * radius1^2");
			} else if (flaecheninhalt1 != 0) {
				grundflaecheKegel1 = flaecheninhalt1;
				LOG.debug("Calculated with: flaecheninhalt1");
			} else if (!Double.isNaN(volumenKegel1) && !Double.isNaN(hoehe1) && volumenKegel1 != 0 && hoehe1 != 0
					&& (3.0 * volumenKegel1 / hoehe1) != 0) {
				grundflaecheKegel1 = 3.0 * volumenKegel1 / hoehe1;
				LOG.debug("Calculated with: 3 * volumenKegel1 / hoehe1");
			} else if (!Double.isNaN(oberflaecheKegel1) && !Double.isNaN(mantelflaecheKegel1) && oberflaecheKegel1 != 0
					&& mantelflaecheKegel1 != 0 && (oberflaecheKegel1 - mantelflaecheKegel1) != 0) {
				grundflaecheKegel1 = oberflaecheKegel1 - mantelflaecheKegel1;
				LOG.debug("Calculated with: oberflaecheKegel1 - mantelflaecheKegel1");
			} else {
				grundflaecheKegel1 = Double.NaN;
				LOG.debug("Calculation for grundflaecheKegel1 was not possible, set to NaN");
			}
		}

		if (grundflaecheKegel2 == 0) {
			LOG.debug("Calculating grundflaecheKegel2");
			if (!Double.isNaN(radius2) && radius2 != 0 && (Math.PI * Math.pow(radius2, 2)) != 0) {
				grundflaecheKegel2 = Math.PI * Math.pow(radius2, 2);
				LOG.debug("Calculated with: PI * radius2^2");
			} else if (flaecheninhalt2 != 0) {
				grundflaecheKegel2 = flaecheninhalt2;
				LOG.debug("Calculated with: flaecheninhalt2");
			} else if (!Double.isNaN(volumenKegel2) && !Double.isNaN(hoehe2) && volumenKegel2 != 0 && hoehe2 != 0
					&& (3.0 * volumenKegel2 / hoehe2) != 0) {
				grundflaecheKegel2 = 3.0 * volumenKegel2 / hoehe2;
				LOG.debug("Calculated with: 3 * volumenKegel2 / hoehe2");
			} else if (!Double.isNaN(oberflaecheKegel2) && !Double.isNaN(mantelflaecheKegel2) && oberflaecheKegel2 != 0
					&& mantelflaecheKegel2 != 0 && (oberflaecheKegel2 - mantelflaecheKegel2) != 0) {
				grundflaecheKegel2 = oberflaecheKegel2 - mantelflaecheKegel2;
				LOG.debug("Calculated with: oberflaecheKegel2 - mantelflaecheKegel2");
			} else {
				grundflaecheKegel2 = Double.NaN;
				LOG.debug("Calculation for grundflaecheKegel2 was not possible, set to NaN");
			}
		}

		if (grundflaecheKegelGesamt == 0) {
			LOG.debug("Calculating grundflaecheKegelGesamt");
			if (!Double.isNaN(grundflaecheKegel1) && !Double.isNaN(grundflaecheKegel2)
					&& (grundflaecheKegel2 - grundflaecheKegel1) >= 0) {
				grundflaecheKegelGesamt = grundflaecheKegel2 - grundflaecheKegel1;
				LOG.debug("Calculated with: grundflaecheKegel2 - grundflaecheKegel1");
			} else if (!Double.isNaN(grundflaecheKegel1) && !Double.isNaN(grundflaecheKegel2)
					&& (grundflaecheKegel1 - grundflaecheKegel2) >= 0) {
				grundflaecheKegelGesamt = grundflaecheKegel1 - grundflaecheKegel2;
				LOG.debug("Calculated with: grundflaecheKegel1 - grundflaecheKegel2");
			} else {
				grundflaecheKegelGesamt = Double.NaN;
				LOG.debug("Calculation for grundflaecheKegelGesamt was not possible, set to NaN");
			}
		}

		if (mantelflaecheKegel1 == 0) {
			LOG.debug("Calculating mantelflaecheKegel1");
			if (!Double.isNaN(radius1) && !Double.isNaN(seitenhoehe1) && radius1 != 0 && seitenhoehe1 != 0
					&& (Math.PI * radius1 * seitenhoehe1) != 0) {
				mantelflaecheKegel1 = Math.PI * radius1 * seitenhoehe1;
				LOG.debug("Calculated with: PI * radius1 * seitenhoehe1");
			} else if (!Double.isNaN(radius1) && !Double.isNaN(hoehe1) && radius1 != 0 && hoehe1 != 0
					&& (Math.PI * radius1 * Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2))) != 0) {
				mantelflaecheKegel1 = Math.PI * radius1 * Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2));
				LOG.debug("Calculated with: PI * radius1 * sqrt(radius1^2 + hoehe1^2)");
			} else if (!Double.isNaN(oberflaecheKegel1) && !Double.isNaN(grundflaecheKegel1) && oberflaecheKegel1 != 0
					&& grundflaecheKegel1 != 0 && (oberflaecheKegel1 - grundflaecheKegel1) != 0) {
				mantelflaecheKegel1 = oberflaecheKegel1 - grundflaecheKegel1;
				LOG.debug("Calculated with: oberflaecheKegel1 - grundflaecheKegel1");
			} else if (!Double.isNaN(oberflaecheKegel1) && !Double.isNaN(radius1) && oberflaecheKegel1 != 0
					&& radius1 != 0 && (oberflaecheKegel1 - (Math.PI * Math.pow(radius1, 2))) != 0) {
				mantelflaecheKegel1 = oberflaecheKegel1 - (Math.PI * Math.pow(radius1, 2));
				LOG.debug("Calculated with: oberflaecheKegel1 - (PI * radius1^2)");
			} else {
				mantelflaecheKegel1 = Double.NaN;
				LOG.debug("Calculation for mantelflaecheKegel1 was not possible, set to NaN");
			}
		}

		if (mantelflaecheKegel2 == 0) {
			LOG.debug("Calculating mantelflaecheKegel2");
			if (!Double.isNaN(radius2) && !Double.isNaN(seitenhoehe2) && radius2 != 0 && seitenhoehe2 != 0
					&& (Math.PI * radius2 * seitenhoehe2) != 0) {
				mantelflaecheKegel2 = Math.PI * radius2 * seitenhoehe2;
				LOG.debug("Calculated with: PI * radius2 * seitenhoehe2");
			} else if (!Double.isNaN(radius2) && !Double.isNaN(hoehe2) && radius2 != 0 && hoehe2 != 0
					&& (Math.PI * radius2 * Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2))) != 0) {
				mantelflaecheKegel2 = Math.PI * radius2 * Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2));
				LOG.debug("Calculated with: PI * radius2 * sqrt(radius2^2 + hoehe2^2)");
			} else if (!Double.isNaN(oberflaecheKegel2) && !Double.isNaN(grundflaecheKegel2) && oberflaecheKegel2 != 0
					&& grundflaecheKegel2 != 0 && (oberflaecheKegel2 - grundflaecheKegel2) != 0) {
				mantelflaecheKegel2 = oberflaecheKegel2 - grundflaecheKegel2;
				LOG.debug("Calculated with: oberflaecheKegel2 - grundflaecheKegel2");
			} else if (!Double.isNaN(oberflaecheKegel2) && !Double.isNaN(radius2) && oberflaecheKegel2 != 0
					&& radius2 != 0 && (oberflaecheKegel2 - (Math.PI * Math.pow(radius2, 2))) != 0) {
				mantelflaecheKegel2 = oberflaecheKegel2 - (Math.PI * Math.pow(radius2, 2));
				LOG.debug("Calculated with: oberflaecheKegel2 - (PI * radius2^2)");
			} else {
				mantelflaecheKegel2 = Double.NaN;
				LOG.debug("Calculation for mantelflaecheKegel2 was not possible, set to NaN");
			}
		}

		if (mantelflaecheKegelGesamt == 0) {
			LOG.debug("Calculating mantelflaecheKegelGesamt");
			if (!Double.isNaN(mantelflaecheKegel1) && !Double.isNaN(mantelflaecheKegel2)
					&& (mantelflaecheKegel2 + mantelflaecheKegel1) != 0) {
				mantelflaecheKegelGesamt = mantelflaecheKegel2 + mantelflaecheKegel1;
				LOG.debug("Calculated with: mantelflaecheKegel2 + mantelflaecheKegel1");
			} else {
				mantelflaecheKegelGesamt = Double.NaN;
				LOG.debug("Calculation for mantelflaecheKegelGesamt was not possible, set to NaN");
			}
		}

		if (oberflaecheKegel1 == 0) {
			LOG.debug("Calculating oberflaecheKegel1");
			if (!Double.isNaN(grundflaecheKegel1) && !Double.isNaN(mantelflaecheKegel1) && grundflaecheKegel1 != 0
					&& mantelflaecheKegel1 != 0 && (grundflaecheKegel1 + mantelflaecheKegel1) != 0) {
				oberflaecheKegel1 = grundflaecheKegel1 + mantelflaecheKegel1;
				LOG.debug("Calculated with: grundflaecheKegel1 + mantelflaecheKegel1");
			} else if (!Double.isNaN(radius1) && !Double.isNaN(seitenhoehe1) && radius1 != 0 && seitenhoehe1 != 0
					&& (Math.PI * radius1 * (radius1 + seitenhoehe1)) != 0) {
				oberflaecheKegel1 = Math.PI * radius1 * (radius1 + seitenhoehe1);
				LOG.debug("Calculated with: PI * radius1 * (radius1 + seitenhoehe1)");
			} else if (!Double.isNaN(radius1) && !Double.isNaN(hoehe1) && radius1 != 0 && hoehe1 != 0
					&& (Math.PI * radius1 * (radius1 + Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2)))) != 0) {
				oberflaecheKegel1 = Math.PI * radius1
						* (radius1 + Math.sqrt(Math.pow(radius1, 2) + Math.pow(hoehe1, 2)));
				LOG.debug("Calculated with: PI * radius1 * (radius1 + sqrt(radius1^2 + hoehe1^2))");
			} else {
				oberflaecheKegel1 = Double.NaN;
				LOG.debug("Calculation for oberflaecheKegel1 was not possible, set to NaN");
			}
		}

		if (oberflaecheKegel2 == 0) {
			LOG.debug("Calculating oberflaecheKegel2");
			if (!Double.isNaN(grundflaecheKegel2) && !Double.isNaN(mantelflaecheKegel2) && grundflaecheKegel2 != 0
					&& mantelflaecheKegel2 != 0 && (grundflaecheKegel2 + mantelflaecheKegel2) != 0) {
				oberflaecheKegel2 = grundflaecheKegel2 + mantelflaecheKegel2;
				LOG.debug("Calculated with: grundflaecheKegel2 + mantelflaecheKegel2");
			} else if (!Double.isNaN(radius2) && !Double.isNaN(seitenhoehe2) && radius2 != 0 && seitenhoehe2 != 0
					&& (Math.PI * radius2 * (radius2 + seitenhoehe2)) != 0) {
				oberflaecheKegel2 = Math.PI * radius2 * (radius2 + seitenhoehe2);
				LOG.debug("Calculated with: PI * radius2 * (radius2 + seitenhoehe2)");
			} else if (!Double.isNaN(radius2) && !Double.isNaN(hoehe2) && radius2 != 0 && hoehe2 != 0
					&& (Math.PI * radius2 * (radius2 + Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2)))) != 0) {
				oberflaecheKegel2 = Math.PI * radius2
						* (radius2 + Math.sqrt(Math.pow(radius2, 2) + Math.pow(hoehe2, 2)));
				LOG.debug("Calculated with: PI * radius2 * (radius2 + sqrt(radius2^2 + hoehe2^2))");
			} else {
				oberflaecheKegel2 = Double.NaN;
				LOG.debug("Calculation for oberflaecheKegel2 was not possible, set to NaN");
			}
		}

		if (oberflaecheKegelGesamt == 0) {
			LOG.debug("Calculating oberflaecheKegelGesamt");
			if (!Double.isNaN(mantelflaecheKegelGesamt) && !Double.isNaN(grundflaecheKegelGesamt)
					&& mantelflaecheKegelGesamt != 0 && grundflaecheKegelGesamt != 0
					&& (mantelflaecheKegelGesamt + 2.0 * grundflaecheKegelGesamt) != 0) {
				oberflaecheKegelGesamt = mantelflaecheKegelGesamt + 2.0 * grundflaecheKegelGesamt;
				LOG.debug("Calculated with: mantelflaecheKegelGesamt + 2 * grundflaecheKegelGesamt");
			} else if (!Double.isNaN(oberflaecheKegel1) && !Double.isNaN(oberflaecheKegel2) && oberflaecheKegel1 != 0
					&& oberflaecheKegel2 != 0) {
				oberflaecheKegelGesamt = oberflaecheKegel1 + oberflaecheKegel2;
				LOG.debug("Calculated with: oberflaecheKegel1 + oberflaecheKegel2");
			} else {
				oberflaecheKegelGesamt = Double.NaN;
				LOG.debug("Calculation for oberflaecheKegelGesamt was not possible, set to NaN");
			}
		}

		if (volumenKegel1 == 0) {
			LOG.debug("Calculating volumenKegel1");
			if (!Double.isNaN(grundflaecheKegel1) && !Double.isNaN(hoehe1) && grundflaecheKegel1 != 0 && hoehe1 != 0
					&& ((1.0 / 3.0) * grundflaecheKegel1 * hoehe1) != 0) {
				volumenKegel1 = (1.0 / 3.0) * grundflaecheKegel1 * hoehe1;
				LOG.debug("Calculated with: (1/3) * grundflaecheKegel1 * hoehe1");
			} else if (!Double.isNaN(radius1) && !Double.isNaN(hoehe1) && radius1 != 0 && hoehe1 != 0
					&& ((1.0 / 3.0) * Math.PI * Math.pow(radius1, 2) * hoehe1) != 0) {
				volumenKegel1 = (1.0 / 3.0) * Math.PI * Math.pow(radius1, 2) * hoehe1;
				LOG.debug("Calculated with: (1/3) * PI * radius1^2 * hoehe1");
			} else {
				volumenKegel1 = Double.NaN;
				LOG.debug("Calculation for volumenKegel1 was not possible, set to NaN");
			}
		}

		if (volumenKegel2 == 0) {
			LOG.debug("Calculating volumenKegel2");
			if (!Double.isNaN(grundflaecheKegel2) && !Double.isNaN(hoehe2) && grundflaecheKegel2 != 0 && hoehe2 != 0
					&& ((1.0 / 3.0) * grundflaecheKegel2 * hoehe2) != 0) {
				volumenKegel2 = (1.0 / 3.0) * grundflaecheKegel2 * hoehe2;
				LOG.debug("Calculated with: (1/3) * grundflaecheKegel2 * hoehe2");
			} else if (!Double.isNaN(radius2) && !Double.isNaN(hoehe2) && radius2 != 0 && hoehe2 != 0
					&& ((1.0 / 3.0) * Math.PI * Math.pow(radius2, 2) * hoehe2) != 0) {
				volumenKegel2 = (1.0 / 3.0) * Math.PI * Math.pow(radius2, 2) * hoehe2;
				LOG.debug("Calculated with: (1/3) * PI * radius2^2 * hoehe2");
			} else {
				volumenKegel2 = Double.NaN;
				LOG.debug("Calculation for volumenKegel2 was not possible, set to NaN");
			}
		}

		if (volumenKegelGesamt == 0) {
			LOG.debug("Calculating volumenKegelGesamt");
			if (!Double.isNaN(volumenKegel1) && !Double.isNaN(volumenKegel2) && (volumenKegel2 - volumenKegel1) >= 0) {
				volumenKegelGesamt = volumenKegel2 - volumenKegel1;
				LOG.debug("Calculated with: volumenKegel2 - volumenKegel1");
			} else if (!Double.isNaN(volumenKegel1) && !Double.isNaN(volumenKegel2)
					&& (volumenKegel1 - volumenKegel2) >= 0) {
				volumenKegelGesamt = volumenKegel1 - volumenKegel2;
				LOG.debug("Calculated with: volumenKegel1 - volumenKegel2");
			} else {
				volumenKegelGesamt = Double.NaN;
				LOG.debug("Calculation for volumenKegelGesamt was not possible, set to NaN");
			}
		}

		// --- Kugel ---
		if (oberflaecheKugel1 == 0) {
			LOG.debug("Calculating oberflaecheKugel1");
			if (!Double.isNaN(radius1) && radius1 != 0 && (4.0 * Math.PI * Math.pow(radius1, 2)) != 0) {
				oberflaecheKugel1 = 4.0 * Math.PI * Math.pow(radius1, 2);
				LOG.debug("Calculated with: 4 * PI * radius1^2");
			} else if (!Double.isNaN(durchmesser1) && durchmesser1 != 0 && (Math.PI * Math.pow(durchmesser1, 2)) != 0) {
				oberflaecheKugel1 = Math.PI * Math.pow(durchmesser1, 2);
				LOG.debug("Calculated with: PI * durchmesser1^2");
			} else if (!Double.isNaN(volumenKugel1) && volumenKugel1 != 0
					&& Math.cbrt(36.0 * Math.PI * Math.pow(volumenKugel1, 2)) != 0) {
				oberflaecheKugel1 = Math.cbrt(36.0 * Math.PI * Math.pow(volumenKugel1, 2));
				LOG.debug("Calculated with: cbrt(36 * PI * volumenKugel1^2)");
			} else {
				oberflaecheKugel1 = Double.NaN;
				LOG.debug("Calculation for oberflaecheKugel1 was not possible, set to NaN");
			}
		}

		if (oberflaecheKugel2 == 0) {
			LOG.debug("Calculating oberflaecheKugel2");
			if (!Double.isNaN(radius2) && radius2 != 0 && (4.0 * Math.PI * Math.pow(radius2, 2)) != 0) {
				oberflaecheKugel2 = 4.0 * Math.PI * Math.pow(radius2, 2);
				LOG.debug("Calculated with: 4 * PI * radius2^2");
			} else if (!Double.isNaN(durchmesser2) && durchmesser2 != 0 && (Math.PI * Math.pow(durchmesser2, 2)) != 0) {
				oberflaecheKugel2 = Math.PI * Math.pow(durchmesser2, 2);
				LOG.debug("Calculated with: PI * durchmesser2^2");
			} else if (!Double.isNaN(volumenKugel2) && volumenKugel2 != 0
					&& Math.cbrt(36.0 * Math.PI * Math.pow(volumenKugel2, 2)) != 0) {
				oberflaecheKugel2 = Math.cbrt(36.0 * Math.PI * Math.pow(volumenKugel2, 2));
				LOG.debug("Calculated with: cbrt(36 * PI * volumenKugel2^2)");
			} else {
				oberflaecheKugel2 = Double.NaN;
				LOG.debug("Calculation for oberflaecheKugel2 was not possible, set to NaN");
			}
		}

		if (oberflaecheKugelGesamt == 0) {
			LOG.debug("Calculating oberflaecheKugelGesamt");
			if (!Double.isNaN(oberflaecheKugel1) && !Double.isNaN(oberflaecheKugel2)
					&& (oberflaecheKugel2 + oberflaecheKugel1) != 0) {
				oberflaecheKugelGesamt = oberflaecheKugel2 + oberflaecheKugel1;
				LOG.debug("Calculated with: oberflaecheKugel2 + oberflaecheKugel1");
			} else {
				oberflaecheKugelGesamt = Double.NaN;
				LOG.debug("Calculation for oberflaecheKugelGesamt was not possible, set to NaN");
			}
		}

		if (volumenKugel1 == 0) {
			LOG.debug("Calculating volumenKugel1");
			if (!Double.isNaN(radius1) && radius1 != 0 && ((4.0 / 3.0) * Math.PI * Math.pow(radius1, 3)) != 0) {
				volumenKugel1 = (4.0 / 3.0) * Math.PI * Math.pow(radius1, 3);
				LOG.debug("Calculated with: (4/3) * PI * radius1^3");
			} else if (!Double.isNaN(durchmesser1) && durchmesser1 != 0
					&& ((1.0 / 6.0) * Math.PI * Math.pow(durchmesser1, 3)) != 0) {
				volumenKugel1 = (1.0 / 6.0) * Math.PI * Math.pow(durchmesser1, 3);
				LOG.debug("Calculated with: (1/6) * PI * durchmesser1^3");
			} else if (!Double.isNaN(oberflaecheKugel1) && oberflaecheKugel1 != 0
					&& ((1.0 / 6.0) * Math.sqrt(Math.pow(oberflaecheKugel1, 3) / Math.PI)) != 0) {
				volumenKugel1 = (1.0 / 6.0) * Math.sqrt(Math.pow(oberflaecheKugel1, 3) / Math.PI);
				LOG.debug("Calculated with: (1/6) * sqrt(oberflaecheKugel1^3 / PI)");
			} else {
				volumenKugel1 = Double.NaN;
				LOG.debug("Calculation for volumenKugel1 was not possible, set to NaN");
			}
		}

		if (volumenKugel2 == 0) {
			LOG.debug("Calculating volumenKugel2");
			if (!Double.isNaN(radius2) && radius2 != 0 && ((4.0 / 3.0) * Math.PI * Math.pow(radius2, 3)) != 0) {
				volumenKugel2 = (4.0 / 3.0) * Math.PI * Math.pow(radius2, 3);
				LOG.debug("Calculated with: (4/3) * PI * radius2^3");
			} else if (!Double.isNaN(durchmesser2) && durchmesser2 != 0
					&& ((1.0 / 6.0) * Math.PI * Math.pow(durchmesser2, 3)) != 0) {
				volumenKugel2 = (1.0 / 6.0) * Math.PI * Math.pow(durchmesser2, 3);
				LOG.debug("Calculated with: (1/6) * PI * durchmesser2^3");
			} else if (!Double.isNaN(oberflaecheKugel2) && oberflaecheKugel2 != 0
					&& ((1.0 / 6.0) * Math.sqrt(Math.pow(oberflaecheKugel2, 3) / Math.PI)) != 0) {
				volumenKugel2 = (1.0 / 6.0) * Math.sqrt(Math.pow(oberflaecheKugel2, 3) / Math.PI);
				LOG.debug("Calculated with: (1/6) * sqrt(oberflaecheKugel2^3 / PI)");
			} else {
				volumenKugel2 = Double.NaN;
				LOG.debug("Calculation for volumenKugel2 was not possible, set to NaN");
			}
		}

		if (volumenKugelGesamt == 0) {
			LOG.debug("Calculating volumenKugelGesamt");
			if (!Double.isNaN(volumenKugel1) && !Double.isNaN(volumenKugel2) && (volumenKugel2 - volumenKugel1) >= 0) {
				volumenKugelGesamt = volumenKugel2 - volumenKugel1;
				LOG.debug("Calculated with: volumenKugel2 - volumenKugel1");
			} else if (!Double.isNaN(volumenKugel1) && !Double.isNaN(volumenKugel2)
					&& (volumenKugel1 - volumenKugel2) >= 0) {
				volumenKugelGesamt = volumenKugel1 - volumenKugel2;
				LOG.debug("Calculated with: volumenKugel1 - volumenKugel2");
			} else {
				volumenKugelGesamt = Double.NaN;
				LOG.debug("Calculation for volumenKugelGesamt was not possible, set to NaN");
			}
		}

		LOG.debug("<============== Berechnung Ende ==============>");
		LOG.debug("");
		LOG.debug("<============== Vergleiche Start ==============>");

		// --- Comparisons (Original logic with added logging) ---
		final double comparisonEpsilon = 1e-9;
		java.util.function.Predicate<Double> isValidForCompare = val -> !Double.isNaN(val) && !Double.isInfinite(val);

		boolean grundflaechenGleich = isValidForCompare.test(flaecheninhalt1) && isValidForCompare.test(flaecheninhalt2)
				&& Math.abs(flaecheninhalt1 - flaecheninhalt2) < comparisonEpsilon;
		if (grundflaechenGleich)
			LOG.debug("Grundflächen sind gleich.");

		boolean zylinderVoluminaGleich = isValidForCompare.test(volumenZylinder1)
				&& isValidForCompare.test(volumenZylinder2)
				&& Math.abs(volumenZylinder1 - volumenZylinder2) < comparisonEpsilon;
		if (zylinderVoluminaGleich)
			LOG.debug("Zylindervolumina sind gleich.");

		boolean zylinderGrundflaechenGleich = isValidForCompare.test(grundflaecheZylinder1)
				&& isValidForCompare.test(grundflaecheZylinder2)
				&& Math.abs(grundflaecheZylinder1 - grundflaecheZylinder2) < comparisonEpsilon;
		if (zylinderGrundflaechenGleich && !grundflaechenGleich)
			LOG.debug("Zylindergrundflächen sind gleich (aber Kreisflächen nicht?).");

		boolean kegelVoluminaGleich = isValidForCompare.test(volumenKegel1) && isValidForCompare.test(volumenKegel2)
				&& Math.abs(volumenKegel1 - volumenKegel2) < comparisonEpsilon;
		if (kegelVoluminaGleich)
			LOG.debug("Kegelvolumina sind gleich.");

		boolean kegelGrundflaechenGleich = isValidForCompare.test(grundflaecheKegel1)
				&& isValidForCompare.test(grundflaecheKegel2)
				&& Math.abs(grundflaecheKegel1 - grundflaecheKegel2) < comparisonEpsilon;
		if (kegelGrundflaechenGleich && !grundflaechenGleich)
			LOG.debug("Kegelgrundflächen sind gleich (aber Kreisflächen nicht?).");

		boolean kugelVoluminaGleich = isValidForCompare.test(volumenKugel1) && isValidForCompare.test(volumenKugel2)
				&& Math.abs(volumenKugel1 - volumenKugel2) < comparisonEpsilon;
		if (kugelVoluminaGleich)
			LOG.debug("Kugelvolumina sind gleich.");

		boolean kreisausschnitteGleich = isValidForCompare.test(kreisauschnitt1)
				&& isValidForCompare.test(kreisauschnitt2)
				&& Math.abs(kreisauschnitt1 - kreisauschnitt2) < comparisonEpsilon;
		if (kreisausschnitteGleich)
			LOG.debug("Kreisausschnitte sind gleich.");

		LOG.debug("<============== Vergleiche Ende ==============>");
		LOG.debug("");
		LOG.debug("<============== Setze Werte in Kreise Start ==============>");

		// --- Set Values in Bean ---
		kreis.setRadius1(radius1);
		kreis.setRadius2(radius2);
		kreis.setDurchmesser1(durchmesser1);
		kreis.setDurchmesser2(durchmesser2);
		kreis.setUmfang1(umfang1);
		kreis.setUmfang2(umfang2);
		kreis.setFlaecheninhalt1(flaecheninhalt1);
		kreis.setFlaecheninhalt2(flaecheninhalt2);
		kreis.setFlaecheninhaltGesamt(flaecheninhaltGesamt);
		kreis.setKreisbogen1(kreisbogen1);
		kreis.setKreisbogen2(kreisbogen2);
		kreis.setKreisausschnitt1(kreisauschnitt1);
		kreis.setKreisausschnitt2(kreisauschnitt2);
		kreis.setKreisausschnittGesamt(kreisausschnittGesamt);
		kreis.setAlpha(alpha);
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
		kreis.setOberflaecheKugel1(oberflaecheKugel1);
		kreis.setOberflaecheKugel2(oberflaecheKugel2);
		kreis.setOberflaecheKugelGesamt(oberflaecheKugelGesamt);
		kreis.setVolumenKugel1(volumenKugel1);
		kreis.setVolumenKugel2(volumenKugel2);
		kreis.setVolumenKugelGesamt(volumenKugelGesamt);

		// Set boolean flags for JSP conditional styling
		kreis.setGrundflaechenGleich(grundflaechenGleich);
		kreis.setZylinderVoluminaGleich(zylinderVoluminaGleich);
		kreis.setZylinderGrundflaechenGleich(zylinderGrundflaechenGleich);
		kreis.setKegelVoluminaGleich(kegelVoluminaGleich);
		kreis.setKegelGrundflaechenGleich(kegelGrundflaechenGleich);
		kreis.setKugelVoluminaGleich(kugelVoluminaGleich);
		kreis.setKreisausschnitteGleich(kreisausschnitteGleich);

		LOG.debug("<============== Setze Werte in Kreise Ende ==============>");

		// Set the formatter IN the bean
		LOG.debug("Setting DecimalFormat into Kreis bean...");
		kreis.setDecimalFormat(getDecimalFormat(decimalPlaces)); // Pass decimalPlaces

		// --- 4. Forward to JSP ---
		LOG.debug("Forwarding request to kreise.jsp...");
		request.setAttribute("kreis", kreis);
		forwardToJsp(request, response); // Use helper method

		LOG.debug("berechneKreise() method finished.");
	}

	// --- Helper method to forward request ---
	private void forwardToJsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kreise.jsp"); // Correct JSP name
		dispatcher.forward(request, response);
	}

	// --- Decimal Formatting ---
	public DecimalFormat getDecimalFormat(int decimalPlaces) { // Accept decimalPlaces as parameter
		LOG.debug("getDecimalFormat called for decimalPlaces: {}", decimalPlaces);
		String pattern;
		switch (decimalPlaces) {
		case 0:
			pattern = "0";
			break;
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
			break;
		}
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
		DecimalFormat df = new DecimalFormat(pattern, symbols);
		LOG.debug("Created DecimalFormat with pattern '{}' using German symbols", pattern);
		return df;
	}
}
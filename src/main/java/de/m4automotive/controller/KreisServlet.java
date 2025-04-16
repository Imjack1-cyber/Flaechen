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

import de.m4automotive.logic.Kreise.KreisLogic;

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

		KreisLogic kreisLogic = new KreisLogic();

		String action = request.getParameter("action");

		// Check if the action is specifically for calculating circles
		if ("berechneKreise".equals(action)) {
			try {
				kreisLogic.berechneKreise(request, response);
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

	private void forwardToJsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kreise.jsp"); // Correct JSP name
		dispatcher.forward(request, response);
	}
}
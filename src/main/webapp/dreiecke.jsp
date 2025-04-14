<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%-- Link to external CSS --%>
<link rel="stylesheet" href="css/dreiecke.css">
<title>Dreiecke</title>
<%-- Changed Title --%>
</head>
<header>
	<nav>
		<a href="kreise.jsp" class="nav-link">Kreise</a> <a
			href="dreiecke.jsp" class="nav-link active">Dreiecke</a>
		<%-- Active class moved --%>
	</nav>
</header>
<body>
	<h1>Eingabe</h1>

	<form action="DreieckServlet" method="post">
		<%-- Changed Servlet Action --%>
		<div class="content">
			<!-- Dreieck Inputs -->
			<div class="Dreieck">
				<%-- Changed class name for consistency if needed, or keep if specific styling exists --%>
				<h3>Dreieck</h3>
				<%-- Changed H2 to H3 for consistency --%>
				<label for="a">Seite a:</label><input type="text" id="a" name="a"
					value="0"><br> <br>
				<%-- Removed step/required for now, relying on JS/backend --%>
				<label for="b">Seite b:</label><input type="text" id="b" name="b"
					value="0"><br> <br> <label for="c">Seite
					c:</label><input type="text" id="c" name="c" value="0"><br> <br>
				<label for="hoeheA">Höhe a (ha):</label><input type="text"
					id="hoeheA" name="hoeheA" value="0"><br> <br> <label
					for="hoeheB">Höhe b (hb):</label><input type="text" id="hoeheB"
					name="hoeheB" value="0"><br> <br> <label
					for="hoeheC">Höhe c (hc):</label><input type="text" id="hoeheC"
					name="hoeheC" value="0"><br> <br> <label
					for="umfangDreieck">Umfang (U):</label><input type="text"
					id="umfangDreieck" name="umfangDreieck" value="0"><br>
				<br> <label for="flaecheninhaltDreieck">Flächeninhalt
					(A):</label><input type="text" id="flaecheninhaltDreieck"
					name="flaecheninhaltDreieck" value="0"><br> <br>
				<label for="alpha">Winkel Alpha (α):</label><input type="text"
					id="alpha" name="alpha" value="0"><br> <br> <label
					for="beta">Winkel Beta (β):</label><input type="text" id="beta"
					name="beta" value="0"><br> <br> <label
					for="gamma">Winkel Gamma (γ):</label><input type="text" id="gamma"
					name="gamma" value="0"><br> <br>
			</div>
			<%-- Add other triangle-related sections here if needed (e.g., for specific triangle types) --%>
		</div>
		<!-- end .content -->

		<label for="decimalPlaces">Dezimalstellen:</label> <input
			type="number" id="decimalPlaces" name="decimalPlaces" value="2"
			min="0" max="10" required>
		<button type="submit" name="action" value="berechneDreiecke"
			class="button">Berechne</button>
		<%-- Changed button text --%>
	</form>

	<!-- ==================== Results Section ==================== -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<details class="results-details" ${not empty dreieck ? 'open' : ''}>
		<%-- Check for 'dreieck' object --%>
		<summary>
			Ergebnisse <span id="copy-feedback"
				style="margin-left: 10px; font-weight: normal; color: green; display: none;"></span>
		</summary>
		<c:if test="${not empty dreieck}">
			<%-- Check for 'dreieck' object --%>
			<div class="Results">
				<!-- DreieckResults -->
				<div class="DreieckResults">
					<%-- Changed class name --%>
					<h3>Dreieck</h3>
					<%-- Changed H2 to H3 --%>
					<p id="result-seite-a">
						<span class="result-label">Seite a:</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.a}</span>
						<button class="copy-button" data-copy-target-id="result-seite-a"
							title="Seite a kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<p id="result-seite-b">
						<span class="result-label">Seite b:</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.b}</span>
						<button class="copy-button" data-copy-target-id="result-seite-b"
							title="Seite b kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<p id="result-seite-c">
						<span class="result-label">Seite c:</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.c}</span>
						<button class="copy-button" data-copy-target-id="result-seite-c"
							title="Seite c kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<p id="result-hoehe-a">
						<span class="result-label">Höhe a (ha):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.hoeheA}</span>
						<button class="copy-button" data-copy-target-id="result-hoehe-a"
							title="Höhe a kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<p id="result-hoehe-b">
						<span class="result-label">Höhe b (hb):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.hoeheB}</span>
						<button class="copy-button" data-copy-target-id="result-hoehe-b"
							title="Höhe b kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<p id="result-hoehe-c">
						<span class="result-label">Höhe c (hc):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.hoeheC}</span>
						<button class="copy-button" data-copy-target-id="result-hoehe-c"
							title="Höhe c kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<p id="result-umfang-dreieck">
						<span class="result-label">Umfang (U):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.umfangDreieck}</span>
						<button class="copy-button"
							data-copy-target-id="result-umfang-dreieck"
							title="Umfang kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<p id="result-flaecheninhalt-dreieck">
						<span class="result-label">Flächeninhalt (A):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.flaecheninhaltDreieck}</span>
						<button class="copy-button"
							data-copy-target-id="result-flaecheninhalt-dreieck"
							title="Flächeninhalt kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<p id="result-winkel-alpha">
						<span class="result-label">Winkel Alpha (α):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.alpha}</span>
						<button class="copy-button"
							data-copy-target-id="result-winkel-alpha"
							title="Winkel Alpha kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<p id="result-winkel-beta">
						<span class="result-label">Winkel Beta (β):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.beta}</span>
						<button class="copy-button"
							data-copy-target-id="result-winkel-beta"
							title="Winkel Beta kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<p id="result-winkel-gamma">
						<span class="result-label">Winkel Gamma (γ):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.gamma}</span>
						<button class="copy-button"
							data-copy-target-id="result-winkel-gamma"
							title="Winkel Gamma kopieren">
							<%@ include file="jsp_pages/copyIcon.jsp"%>
						</button>
					</p>
					<%-- No "Gesamt" or equality checks expected for basic triangle properties --%>
				</div>
				<%-- Add other results sections here if needed --%>
			</div>
			<!-- End .Results -->
		</c:if>
		<!-- End check for not empty dreieck -->
	</details>
	<!-- End results-details -->

	<script src="js/dreiecke.js"></script>

</body>
<footer>
	<%@ include file="jsp_pages/footer.jsp"%>
</footer>
</html>
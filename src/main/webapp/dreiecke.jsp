<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%-- Link to external CSS --%>
<link rel="stylesheet" href="css/dreiecke.css">
<title>Dreiecke</title>
</head>
<header>
	<nav>
		<a href="kreise.jsp" class="nav-link">Kreise</a> <a
			href="dreiecke.jsp" class="nav-link active">Dreiecke</a>
	</nav>
</header>
<body>
	<h1>Eingabe</h1>

	<form action="DreieckServlet" method="post" id="dreieck-form">
		<div class="content">
			<!-- Dreieck Inputs -->
			<div class="Dreieck">
				<h3>Dreieck</h3>
				<%-- **** Correct Structure: form-row divs, NO <br> tags **** --%>
				<div class="form-row">
					<label for="a">Seite a:</label><input type="text" id="a" name="a"
						value="${param.a != null ? param.a : '0'}">
				</div>
				<div class="form-row">
					<label for="b">Seite b:</label><input type="text" id="b" name="b"
						value="${param.b != null ? param.b : '0'}">
				</div>
				<div class="form-row">
					<label for="c">Seite c:</label><input type="text" id="c" name="c"
						value="${param.c != null ? param.c : '0'}">
				</div>
				<div class="form-row">
					<label for="hoeheA">Höhe a (ha):</label><input type="text"
						id="hoeheA" name="hoeheA"
						value="${param.hoeheA != null ? param.hoeheA : '0'}">
				</div>
				<div class="form-row">
					<label for="hoeheB">Höhe b (hb):</label><input type="text"
						id="hoeheB" name="hoeheB"
						value="${param.hoeheB != null ? param.hoeheB : '0'}">
				</div>
				<div class="form-row">
					<label for="hoeheC">Höhe c (hc):</label><input type="text"
						id="hoeheC" name="hoeheC"
						value="${param.hoeheC != null ? param.hoeheC : '0'}">
				</div>
				<div class="form-row">
					<label for="umfangDreieck">Umfang (U):</label><input type="text"
						id="umfangDreieck" name="umfangDreieck"
						value="${param.umfangDreieck != null ? param.umfangDreieck : '0'}">
				</div>
				<div class="form-row">
					<label for="flaecheninhaltDreieck">Flächeninhalt (A):</label><input
						type="text" id="flaecheninhaltDreieck"
						name="flaecheninhaltDreieck"
						value="${param.flaecheninhaltDreieck != null ? param.flaecheninhaltDreieck : '0'}">
				</div>
				<div class="form-row">
					<label for="alpha">Winkel Alpha (α):</label><input type="text"
						id="alpha" name="alpha"
						value="${param.alpha != null ? param.alpha : '0'}">
				</div>
				<div class="form-row">
					<label for="beta">Winkel Beta (β):</label><input type="text"
						id="beta" name="beta"
						value="${param.beta != null ? param.beta : '0'}">
				</div>
				<div class="form-row">
					<label for="gamma">Winkel Gamma (γ):</label><input type="text"
						id="gamma" name="gamma"
						value="${param.gamma != null ? param.gamma : '0'}">
				</div>
			</div>
		</div>
		<!-- end .content -->

		<div class="controls-submit">
			<label for="decimalPlaces">Dezimalstellen:</label> <input
				type="number" id="decimalPlaces" name="decimalPlaces"
				value="${param.decimalPlaces != null ? param.decimalPlaces : '2'}"
				min="0" max="10" required>
			<button type="submit" name="action" value="berechneDreiecke"
				class="button submit-button">Berechne</button>
		</div>
	</form>

	<!-- ==================== Results Section ==================== -->
	<details class="results-details" ${not empty dreieck ? 'open' : ''}>
		<summary>
			Ergebnisse <span id="copy-feedback"
				style="margin-left: 10px; font-weight: normal; color: green; display: none;"></span>
		</summary>
		<c:if test="${not empty dreieck}">
			<div class="Results">
				<div class="DreieckResults">
					<h3>Dreieck</h3>
					<p id="result-seite-a">
						<span class="result-label">Seite a:</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.a}</span>
						<button class="copy-button" data-copy-target-id="result-seite-a"
							title="Seite a kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
					<p id="result-seite-b">
						<span class="result-label">Seite b:</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.b}</span>
						<button class="copy-button" data-copy-target-id="result-seite-b"
							title="Seite b kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
					<p id="result-seite-c">
						<span class="result-label">Seite c:</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.c}</span>
						<button class="copy-button" data-copy-target-id="result-seite-c"
							title="Seite c kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
					<p id="result-hoehe-a">
						<span class="result-label">Höhe a (ha):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.hoeheA}</span>
						<button class="copy-button" data-copy-target-id="result-hoehe-a"
							title="Höhe a kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
					<p id="result-hoehe-b">
						<span class="result-label">Höhe b (hb):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.hoeheB}</span>
						<button class="copy-button" data-copy-target-id="result-hoehe-b"
							title="Höhe b kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
					<p id="result-hoehe-c">
						<span class="result-label">Höhe c (hc):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.hoeheC}</span>
						<button class="copy-button" data-copy-target-id="result-hoehe-c"
							title="Höhe c kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
					<p id="result-umfang-dreieck">
						<span class="result-label">Umfang (U):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.umfangDreieck}</span>
						<button class="copy-button"
							data-copy-target-id="result-umfang-dreieck"
							title="Umfang kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
					<p id="result-flaecheninhalt-dreieck">
						<span class="result-label">Flächeninhalt (A):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.flaecheninhaltDreieck}</span>
						<button class="copy-button"
							data-copy-target-id="result-flaecheninhalt-dreieck"
							title="Flächeninhalt kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
					<p id="result-winkel-alpha">
						<span class="result-label">Winkel Alpha (α):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.alpha}</span>
						<button class="copy-button"
							data-copy-target-id="result-winkel-alpha"
							title="Winkel Alpha kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
					<p id="result-winkel-beta">
						<span class="result-label">Winkel Beta (β):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.beta}</span>
						<button class="copy-button"
							data-copy-target-id="result-winkel-beta"
							title="Winkel Beta kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
					<p id="result-winkel-gamma">
						<span class="result-label">Winkel Gamma (γ):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.gamma}</span>
						<button class="copy-button"
							data-copy-target-id="result-winkel-gamma"
							title="Winkel Gamma kopieren"><%@ include
								file="jsp_pages/copyIcon.jsp"%></button>
					</p>
				</div>
			</div>
		</c:if>
	</details>

	<script src="js/dreiecke.js"></script>

</body>
<footer>
	<%@ include file="jsp_pages/footer.jsp"%>
</footer>
</html>
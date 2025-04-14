<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%-- Link to external CSS --%>
<link rel="stylesheet" href="css/kreise.css">
<title>Kreise</title>
</head>
<header>
	<nav>
		<a href="kreise.jsp" class="nav-link active">Kreise</a> <a
			href="dreiecke.jsp" class="nav-link">Dreieck</a>
		<%-- Changed back based on your input --%>
	</nav>
</header>
<body>

	<h1>Eingabe</h1>

	<%-- **** Added ID to the form **** --%>
	<form action="KreisServlet" method="post" id="kreis-form">

		<%-- Tab Navigation for Inputs --%>
		<div class="tab-nav">
			<button type="button" class="tab-button active"
				onclick="showInputTab('input-tab-1', this)">Kreis 1</button>
			<button type="button" class="tab-button"
				onclick="showInputTab('input-tab-2', this)">Kreis 2</button>
			<button type="button" class="tab-button"
				onclick="showInputTab('input-tab-common', this)">Dezimal
				Stellen</button>
		</div>

		<%-- Tab Content for Inputs --%>
		<div class="tab-content-container content">
			<%-- Tab 1: Inputs for Kreis 1 --%>
			<div id="input-tab-1" class="tab-content active">
				<div class="input-section Kreis">
					<h3>Kreis</h3>
					<div class="form-row">
						<label for="radius1">Radius:</label><input type="text"
							id="radius1" name="radius1"
							value="${param.radius1 != null ? param.radius1 : '0'}">
					</div>
					<div class="form-row">
						<label for="durchmesser1">Durchmesser:</label><input type="text"
							id="durchmesser1" name="durchmesser1"
							value="${param.durchmesser1 != null ? param.durchmesser1 : '0'}">
					</div>
					<div class="form-row">
						<label for="umfang1">Umfang:</label><input type="text"
							id="umfang1" name="umfang1"
							value="${param.umfang1 != null ? param.umfang1 : '0'}">
					</div>
					<div class="form-row">
						<label for="flaecheninhalt1">Flächeninhalt:</label><input
							type="text" id="flaecheninhalt1" name="flaecheninhalt1"
							value="${param.flaecheninhalt1 != null ? param.flaecheninhalt1 : '0'}">
					</div>
				</div>
				<div class="input-section Kreisteile">
					<h3>Kreisteile</h3>
					<div class="form-row">
						<label for="kreisbogen1">Kreisbogen:</label><input type="text"
							id="kreisbogen1" name="kreisbogen1"
							value="${param.kreisbogen1 != null ? param.kreisbogen1 : '0'}">
					</div>
					<div class="form-row">
						<label for="kreisausschnitt1">Kreisausschnitt:</label><input
							type="text" id="kreisausschnitt1" name="kreisausschnitt1"
							value="${param.kreisausschnitt1 != null ? param.kreisausschnitt1 : '0'}">
					</div>
					<div class="form-row">
						<%-- **** ID changed to alpha1, name="alpha" kept **** --%>
						<label for="alpha1">Alpha (Grad):</label><input type="text"
							id="alpha1" name="alpha"
							value="${param.alpha != null ? param.alpha : '0'}">
					</div>
				</div>
				<div class="input-section Zylinder">
					<h3>Zylinder</h3>
					<div class="form-row">
						<label for="hoehe1">Höhe:</label><input type="text" id="hoehe1"
							name="hoehe1"
							value="${param.hoehe1 != null ? param.hoehe1 : '0'}">
					</div>
					<div class="form-row">
						<label for="grundflaecheZylinder1">Grundfläche Zylinder:</label><input
							type="text" id="grundflaecheZylinder1"
							name="grundflaecheZylinder1"
							value="${param.grundflaecheZylinder1 != null ? param.grundflaecheZylinder1 : '0'}">
					</div>
					<div class="form-row">
						<label for="mantelflaecheZylinder1">Mantelfläche Zylinder:</label><input
							type="text" id="mantelflaecheZylinder1"
							name="mantelflaecheZylinder1"
							value="${param.mantelflaecheZylinder1 != null ? param.mantelflaecheZylinder1 : '0'}">
					</div>
					<div class="form-row">
						<label for="oberflaecheZylinder1">Oberfläche Zylinder:</label><input
							type="text" id="oberflaecheZylinder1" name="oberflaecheZylinder1"
							value="${param.oberflaecheZylinder1 != null ? param.oberflaecheZylinder1 : '0'}">
					</div>
					<div class="form-row">
						<label for="volumenZylinder1">Volumen Zylinder:</label><input
							type="text" id="volumenZylinder1" name="volumenZylinder1"
							value="${param.volumenZylinder1 != null ? param.volumenZylinder1 : '0'}">
					</div>
				</div>
				<div class="input-section Kegel">
					<h3>Kegel</h3>
					<div class="form-row">
						<label for="seitenhoehe1">Seitenhöhe:</label><input type="text"
							id="seitenhoehe1" name="seitenhoehe1"
							value="${param.seitenhoehe1 != null ? param.seitenhoehe1 : '0'}">
					</div>
					<div class="form-row">
						<label for="grundflaecheKegel1">Grundfläche Kegel:</label><input
							type="text" id="grundflaecheKegel1" name="grundflaecheKegel1"
							value="${param.grundflaecheKegel1 != null ? param.grundflaecheKegel1 : '0'}">
					</div>
					<div class="form-row">
						<label for="mantelflaecheKegel1">Mantelfläche Kegel:</label><input
							type="text" id="mantelflaecheKegel1" name="mantelflaecheKegel1"
							value="${param.mantelflaecheKegel1 != null ? param.mantelflaecheKegel1 : '0'}">
					</div>
					<div class="form-row">
						<label for="oberflaecheKegel1">Oberfläche Kegel:</label><input
							type="text" id="oberflaecheKegel1" name="oberflaecheKegel1"
							value="${param.oberflaecheKegel1 != null ? param.oberflaecheKegel1 : '0'}">
					</div>
					<div class="form-row">
						<label for="volumenKegel1">Volumen Kegel:</label><input
							type="text" id="volumenKegel1" name="volumenKegel1"
							value="${param.volumenKegel1 != null ? param.volumenKegel1 : '0'}">
					</div>
				</div>
				<div class="input-section Kugel">
					<h3>Kugel</h3>
					<div class="form-row">
						<label for="oberflaecheKugel1">Oberfläche Kugel:</label><input
							type="text" id="oberflaecheKugel1" name="oberflaecheKugel1"
							value="${param.oberflaecheKugel1 != null ? param.oberflaecheKugel1 : '0'}">
					</div>
					<div class="form-row">
						<label for="volumenKugel1">Volumen Kugel:</label><input
							type="text" id="volumenKugel1" name="volumenKugel1"
							value="${param.volumenKugel1 != null ? param.volumenKugel1 : '0'}">
					</div>
				</div>
			</div>
			<%-- End Input Tab 1 --%>

			<%-- Tab 2: Inputs for Kreis 2 --%>
			<div id="input-tab-2" class="tab-content">
				<div class="input-section Kreis">
					<h3>Kreis</h3>
					<div class="form-row">
						<label for="radius2">Radius:</label><input type="text"
							id="radius2" name="radius2"
							value="${param.radius2 != null ? param.radius2 : '0'}">
					</div>
					<div class="form-row">
						<label for="durchmesser2">Durchmesser:</label><input type="text"
							id="durchmesser2" name="durchmesser2"
							value="${param.durchmesser2 != null ? param.durchmesser2 : '0'}">
					</div>
					<div class="form-row">
						<label for="umfang2">Umfang:</label><input type="text"
							id="umfang2" name="umfang2"
							value="${param.umfang2 != null ? param.umfang2 : '0'}">
					</div>
					<div class="form-row">
						<label for="flaecheninhalt2">Flächeninhalt:</label><input
							type="text" id="flaecheninhalt2" name="flaecheninhalt2"
							value="${param.flaecheninhalt2 != null ? param.flaecheninhalt2 : '0'}">
					</div>
				</div>
				<div class="input-section Kreisteile">
					<h3>Kreisteile</h3>
					<div class="form-row">
						<label for="kreisbogen2">Kreisbogen:</label><input type="text"
							id="kreisbogen2" name="kreisbogen2"
							value="${param.kreisbogen2 != null ? param.kreisbogen2 : '0'}">
					</div>
					<div class="form-row">
						<label for="kreisausschnitt2">Kreisausschnitt:</label><input
							type="text" id="kreisausschnitt2" name="kreisausschnitt2"
							value="${param.kreisausschnitt2 != null ? param.kreisausschnitt2 : '0'}">
					</div>
					<div class="form-row">
						<%-- **** ID changed to alpha2, name attribute REMOVED **** --%>
						<label for="alpha2">Alpha (Grad):</label><input type="text"
							id="alpha2" value="${param.alpha != null ? param.alpha : '0'}">
					</div>
				</div>
				<div class="input-section Zylinder">
					<h3>Zylinder</h3>
					<div class="form-row">
						<label for="hoehe2">Höhe:</label><input type="text" id="hoehe2"
							name="hoehe2"
							value="${param.hoehe2 != null ? param.hoehe2 : '0'}">
					</div>
					<div class="form-row">
						<label for="grundflaecheZylinder2">Grundfläche Zylinder:</label><input
							type="text" id="grundflaecheZylinder2"
							name="grundflaecheZylinder2"
							value="${param.grundflaecheZylinder2 != null ? param.grundflaecheZylinder2 : '0'}">
					</div>
					<div class="form-row">
						<label for="mantelflaecheZylinder2">Mantelfläche Zylinder:</label><input
							type="text" id="mantelflaecheZylinder2"
							name="mantelflaecheZylinder2"
							value="${param.mantelflaecheZylinder2 != null ? param.mantelflaecheZylinder2 : '0'}">
					</div>
					<div class="form-row">
						<label for="oberflaecheZylinder2">Oberfläche Zylinder:</label><input
							type="text" id="oberflaecheZylinder2" name="oberflaecheZylinder2"
							value="${param.oberflaecheZylinder2 != null ? param.oberflaecheZylinder2 : '0'}">
					</div>
					<div class="form-row">
						<label for="volumenZylinder2">Volumen Zylinder:</label><input
							type="text" id="volumenZylinder2" name="volumenZylinder2"
							value="${param.volumenZylinder2 != null ? param.volumenZylinder2 : '0'}">
					</div>
				</div>
				<div class="input-section Kegel">
					<h3>Kegel</h3>
					<div class="form-row">
						<label for="seitenhoehe2">Seitenhöhe:</label><input type="text"
							id="seitenhoehe2" name="seitenhoehe2"
							value="${param.seitenhoehe2 != null ? param.seitenhoehe2 : '0'}">
					</div>
					<div class="form-row">
						<label for="grundflaecheKegel2">Grundfläche Kegel:</label><input
							type="text" id="grundflaecheKegel2" name="grundflaecheKegel2"
							value="${param.grundflaecheKegel2 != null ? param.grundflaecheKegel2 : '0'}">
					</div>
					<div class="form-row">
						<label for="mantelflaecheKegel2">Mantelfläche Kegel:</label><input
							type="text" id="mantelflaecheKegel2" name="mantelflaecheKegel2"
							value="${param.mantelflaecheKegel2 != null ? param.mantelflaecheKegel2 : '0'}">
					</div>
					<div class="form-row">
						<label for="oberflaecheKegel2">Oberfläche Kegel:</label><input
							type="text" id="oberflaecheKegel2" name="oberflaecheKegel2"
							value="${param.oberflaecheKegel2 != null ? param.oberflaecheKegel2 : '0'}">
					</div>
					<div class="form-row">
						<label for="volumenKegel2">Volumen Kegel:</label><input
							type="text" id="volumenKegel2" name="volumenKegel2"
							value="${param.volumenKegel2 != null ? param.volumenKegel2 : '0'}">
					</div>
				</div>
				<div class="input-section Kugel">
					<h3>Kugel</h3>
					<div class="form-row">
						<label for="oberflaecheKugel2">Oberfläche Kugel:</label><input
							type="text" id="oberflaecheKugel2" name="oberflaecheKugel2"
							value="${param.oberflaecheKugel2 != null ? param.oberflaecheKugel2 : '0'}">
					</div>
					<div class="form-row">
						<label for="volumenKugel2">Volumen Kugel:</label><input
							type="text" id="volumenKugel2" name="volumenKugel2"
							value="${param.volumenKugel2 != null ? param.volumenKugel2 : '0'}">
					</div>
				</div>
			</div>
			<%-- End Input Tab 2 --%>

			<%-- Tab 3: Renamed Tab for Settings --%>
			<div id="input-tab-common" class="tab-content">
				<div class="input-section">
					<h3>Anzeigeoptionen</h3>
					<div class="form-row">
						<label for="decimalPlaces">Dezimalstellen:</label> <input
							type="number" id="decimalPlaces" name="decimalPlaces"
							value="${param.decimalPlaces != null ? param.decimalPlaces : '2'}"
							min="0" max="10" required>
					</div>
				</div>
			</div>
			<%-- End Input Tab 3 --%>
		</div>
		<%-- end .tab-content-container.content --%>

		<%-- **** Added div for Alpha error message **** --%>
		<div id="alpha-error-message" class="error-message"
			style="display: none;"></div>

		<button type="submit" name="action" value="berechneKreise"
			class="button submit-button">Berechne</button>
	</form>

	<!-- ==================== Results Section (Using Includes) ==================== -->
	<details class="results-details" ${not empty kreis ? 'open' : ''}>
		<summary>
			Ergebnisse <span id="copy-feedback"
				style="margin-left: 10px; font-weight: normal; color: green; display: none;"></span>
		</summary>
		<c:if test="${not empty kreis}">
			<%-- Tab Navigation for Results --%>
			<div class="tab-nav">
				<button type="button" class="tab-button active"
					onclick="showResultTab('result-tab-1', this)">Kreis 1</button>
				<button type="button" class="tab-button"
					onclick="showResultTab('result-tab-2', this)">Kreis 2</button>
				<button type="button" class="tab-button"
					onclick="showResultTab('result-tab-gesamt', this)">Insgesamt</button>
			</div>

			<%-- Tab Content for Results --%>
			<div class="tab-content-container results-container Results">
				<%-- Tab 1: Results for Kreis 1 --%>
				<div id="result-tab-1" class="tab-content active">
					<div class="result-section KreisResults">
						<h3>Kreis</h3>
						<p id="result-radius1">
							<span class="result-label">Radius:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.radius1}</span>
							<button class="copy-button" data-copy-target-id="result-radius1"
								title="Radius 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-durchmesser1">
							<span class="result-label">Durchmesser:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.durchmesser1}</span>
							<button class="copy-button"
								data-copy-target-id="result-durchmesser1"
								title="Durchmesser 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-umfang1">
							<span class="result-label">Umfang:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.umfang1}</span>
							<button class="copy-button" data-copy-target-id="result-umfang1"
								title="Umfang 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-flaecheninhalt1">
							<span class="result-label">Flächeninhalt:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.flaecheninhalt1}</span>
							<button class="copy-button"
								data-copy-target-id="result-flaecheninhalt1"
								title="Flächeninhalt 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section KreisteileResults">
						<h3>Kreisteile</h3>
						<p id="result-kreisbogen1">
							<span class="result-label">Kreisbogen:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.kreisbogen1}</span>
							<button class="copy-button"
								data-copy-target-id="result-kreisbogen1"
								title="Kreisbogen 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-kreisausschnitt1">
							<span class="result-label">Kreisausschnitt:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.kreisausschnitt1}</span>
							<button class="copy-button"
								data-copy-target-id="result-kreisausschnitt1"
								title="Kreisausschnitt 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-alpha">
							<span class="result-label">Alpha:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.alpha}</span>
							<button class="copy-button" data-copy-target-id="result-alpha"
								title="Alpha kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section ZylinderResults">
						<h3>Zylinder</h3>
						<p id="result-hoehe1">
							<span class="result-label">Höhe:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.hoehe1}</span>
							<button class="copy-button" data-copy-target-id="result-hoehe1"
								title="Höhe 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-grundflaecheZylinder1">
							<span class="result-label">Grundfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.grundflaecheZylinder1}</span>
							<button class="copy-button"
								data-copy-target-id="result-grundflaecheZylinder1"
								title="Grundfläche Zylinder 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-mantelflaecheZylinder1">
							<span class="result-label">Mantelfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheZylinder1}</span>
							<button class="copy-button"
								data-copy-target-id="result-mantelflaecheZylinder1"
								title="Mantelfläche Zylinder 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-oberflaecheZylinder1">
							<span class="result-label">Oberfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheZylinder1}</span>
							<button class="copy-button"
								data-copy-target-id="result-oberflaecheZylinder1"
								title="Oberfläche Zylinder 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-volumenZylinder1">
							<span class="result-label">Volumen:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.volumenZylinder1}</span>
							<button class="copy-button"
								data-copy-target-id="result-volumenZylinder1"
								title="Volumen Zylinder 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section KegelResults">
						<h3>Kegel</h3>
						<p id="result-seitenhoehe1">
							<span class="result-label">Seitenhöhe:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.seitenhoehe1}</span>
							<button class="copy-button"
								data-copy-target-id="result-seitenhoehe1"
								title="Seitenhöhe 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-grundflaecheKegel1">
							<span class="result-label">Grundfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.grundflaecheKegel1}</span>
							<button class="copy-button"
								data-copy-target-id="result-grundflaecheKegel1"
								title="Grundfläche Kegel 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-mantelflaecheKegel1">
							<span class="result-label">Mantelfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheKegel1}</span>
							<button class="copy-button"
								data-copy-target-id="result-mantelflaecheKegel1"
								title="Mantelfläche Kegel 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-oberflaecheKegel1">
							<span class="result-label">Oberfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKegel1}</span>
							<button class="copy-button"
								data-copy-target-id="result-oberflaecheKegel1"
								title="Oberfläche Kegel 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-volumenKegel1">
							<span class="result-label">Volumen:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.volumenKegel1}</span>
							<button class="copy-button"
								data-copy-target-id="result-volumenKegel1"
								title="Volumen Kegel 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section KugelResults">
						<h3>Kugel</h3>
						<p id="result-oberflaecheKugel1">
							<span class="result-label">Oberfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKugel1}</span>
							<button class="copy-button"
								data-copy-target-id="result-oberflaecheKugel1"
								title="Oberfläche Kugel 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-volumenKugel1">
							<span class="result-label">Volumen:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.volumenKugel1}</span>
							<button class="copy-button"
								data-copy-target-id="result-volumenKugel1"
								title="Volumen Kugel 1 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
				</div>
				<%-- End Tab 1 --%>

				<%-- Tab 2: Results for Kreis 2 --%>
				<div id="result-tab-2" class="tab-content">
					<div class="result-section KreisResults">
						<h3>Kreis</h3>
						<p id="result-radius2">
							<span class="result-label">Radius:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.radius2}</span>
							<button class="copy-button" data-copy-target-id="result-radius2"
								title="Radius 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-durchmesser2">
							<span class="result-label">Durchmesser:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.durchmesser2}</span>
							<button class="copy-button"
								data-copy-target-id="result-durchmesser2"
								title="Durchmesser 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-umfang2">
							<span class="result-label">Umfang:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.umfang2}</span>
							<button class="copy-button" data-copy-target-id="result-umfang2"
								title="Umfang 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-flaecheninhalt2">
							<span class="result-label">Flächeninhalt:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.flaecheninhalt2}</span>
							<button class="copy-button"
								data-copy-target-id="result-flaecheninhalt2"
								title="Flächeninhalt 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section KreisteileResults">
						<h3>Kreisteile</h3>
						<p id="result-kreisbogen2">
							<span class="result-label">Kreisbogen:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.kreisbogen2}</span>
							<button class="copy-button"
								data-copy-target-id="result-kreisbogen2"
								title="Kreisbogen 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-kreisausschnitt2">
							<span class="result-label">Kreisausschnitt:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.kreisausschnitt2}</span>
							<button class="copy-button"
								data-copy-target-id="result-kreisausschnitt2"
								title="Kreisausschnitt 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-alpha">
							<span class="result-label">Alpha:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.alpha}</span>
							<button class="copy-button" data-copy-target-id="result-alpha"
								title="Alpha kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section ZylinderResults">
						<h3>Zylinder</h3>
						<p id="result-hoehe2">
							<span class="result-label">Höhe:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.hoehe2}</span>
							<button class="copy-button" data-copy-target-id="result-hoehe2"
								title="Höhe 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-grundflaecheZylinder2">
							<span class="result-label">Grundfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.grundflaecheZylinder2}</span>
							<button class="copy-button"
								data-copy-target-id="result-grundflaecheZylinder2"
								title="Grundfläche Zylinder 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-mantelflaecheZylinder2">
							<span class="result-label">Mantelfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheZylinder2}</span>
							<button class="copy-button"
								data-copy-target-id="result-mantelflaecheZylinder2"
								title="Mantelfläche Zylinder 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-oberflaecheZylinder2">
							<span class="result-label">Oberfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheZylinder2}</span>
							<button class="copy-button"
								data-copy-target-id="result-oberflaecheZylinder2"
								title="Oberfläche Zylinder 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-volumenZylinder2">
							<span class="result-label">Volumen:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.volumenZylinder2}</span>
							<button class="copy-button"
								data-copy-target-id="result-volumenZylinder2"
								title="Volumen Zylinder 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section KegelResults">
						<h3>Kegel</h3>
						<p id="result-seitenhoehe2">
							<span class="result-label">Seitenhöhe:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.seitenhoehe2}</span>
							<button class="copy-button"
								data-copy-target-id="result-seitenhoehe2"
								title="Seitenhöhe 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-grundflaecheKegel2">
							<span class="result-label">Grundfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.grundflaecheKegel2}</span>
							<button class="copy-button"
								data-copy-target-id="result-grundflaecheKegel2"
								title="Grundfläche Kegel 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-mantelflaecheKegel2">
							<span class="result-label">Mantelfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheKegel2}</span>
							<button class="copy-button"
								data-copy-target-id="result-mantelflaecheKegel2"
								title="Mantelfläche Kegel 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-oberflaecheKegel2">
							<span class="result-label">Oberfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKegel2}</span>
							<button class="copy-button"
								data-copy-target-id="result-oberflaecheKegel2"
								title="Oberfläche Kegel 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-volumenKegel2">
							<span class="result-label">Volumen:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.volumenKegel2}</span>
							<button class="copy-button"
								data-copy-target-id="result-volumenKegel2"
								title="Volumen Kegel 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section KugelResults">
						<h3>Kugel</h3>
						<p id="result-oberflaecheKugel2">
							<span class="result-label">Oberfläche:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKugel2}</span>
							<button class="copy-button"
								data-copy-target-id="result-oberflaecheKugel2"
								title="Oberfläche Kugel 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-volumenKugel2">
							<span class="result-label">Volumen:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.volumenKugel2}</span>
							<button class="copy-button"
								data-copy-target-id="result-volumenKugel2"
								title="Volumen Kugel 2 kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
				</div>
				<%-- End Tab 2 --%>

				<%-- Tab 3: Gesamt Results --%>
				<div id="result-tab-gesamt" class="tab-content">
					<div class="result-section KreisResults">
						<h3>Kreis Gesamt</h3>
						<p id="result-flaecheninhaltGesamt"
							class="${kreis.grundflaechenGleich ? 'result-notice-equal' : ''}">
							<span class="result-label">Flächeninhalt Gesamt:</span> <span
								class="result-icon-container"> <c:if
									test="${kreis.grundflaechenGleich}">
									<span class="info-icon"
										title="Hinweis: Die Kreisflächen sind gleich groß!">ⓘ</span>
								</c:if>
							</span> <span class="result-value">${kreis.flaecheninhaltGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-flaecheninhaltGesamt"
								title="Flächeninhalt Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section KreisteileResults">
						<h3>Kreisteile Gesamt / Gemeinsam</h3>
						<p id="result-kreisausschnittGesamt"
							class="${kreis.kreisausschnitteGleich ? 'result-notice-equal' : ''}">
							<span class="result-label">Kreisausschnitt Gesamt:</span> <span
								class="result-icon-container"> <c:if
									test="${kreis.kreisausschnitteGleich}">
									<span class="info-icon"
										title="Hinweis: Die Kreisausschnitte sind gleich groß!">ⓘ</span>
								</c:if>
							</span> <span class="result-value">${kreis.kreisausschnittGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-kreisausschnittGesamt"
								title="Kreisausschnitt Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-alpha">
							<span class="result-label">Alpha:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.alpha}</span>
							<button class="copy-button" data-copy-target-id="result-alpha"
								title="Alpha kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section ZylinderResults">
						<h3>Zylinder Gesamt</h3>
						<p id="result-grundflaecheZylinderGesamt"
							class="${kreis.zylinderGrundflaechenGleich ? 'result-notice-equal' : ''}">
							<span class="result-label">Grundfläche Gesamt:</span> <span
								class="result-icon-container"> <c:if
									test="${kreis.zylinderGrundflaechenGleich}">
									<span class="info-icon"
										title="Hinweis: Die Zylinder-Grundflächen sind gleich groß!">ⓘ</span>
								</c:if>
							</span> <span class="result-value">${kreis.grundflaecheZylinderGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-grundflaecheZylinderGesamt"
								title="Grundfläche Zylinder Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-mantelflaecheZylinderGesamt">
							<span class="result-label">Mantelfläche Gesamt:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheZylinderGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-mantelflaecheZylinderGesamt"
								title="Mantelfläche Zylinder Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-oberflaecheZylinderGesamt">
							<span class="result-label">Oberfläche Gesamt:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheZylinderGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-oberflaecheZylinderGesamt"
								title="Oberfläche Zylinder Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-volumenZylinderGesamt"
							class="${kreis.zylinderVoluminaGleich ? 'result-notice-equal' : ''}">
							<span class="result-label">Volumen Gesamt:</span> <span
								class="result-icon-container"> <c:if
									test="${kreis.zylinderVoluminaGleich}">
									<span class="info-icon"
										title="Hinweis: Die Zylindervolumina sind gleich groß!">ⓘ</span>
								</c:if>
							</span> <span class="result-value">${kreis.volumenZylinderGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-volumenZylinderGesamt"
								title="Volumen Zylinder Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section KegelResults">
						<h3>Kegel Gesamt</h3>
						<p id="result-grundflaecheKegelGesamt"
							class="${kreis.kegelGrundflaechenGleich ? 'result-notice-equal' : ''}">
							<span class="result-label">Grundfläche Gesamt:</span> <span
								class="result-icon-container"> <c:if
									test="${kreis.kegelGrundflaechenGleich}">
									<span class="info-icon"
										title="Hinweis: Die Kegel-Grundflächen sind gleich groß!">ⓘ</span>
								</c:if>
							</span> <span class="result-value">${kreis.grundflaecheKegelGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-grundflaecheKegelGesamt"
								title="Grundfläche Kegel Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-mantelflaecheKegelGesamt">
							<span class="result-label">Mantelfläche Gesamt:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheKegelGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-mantelflaecheKegelGesamt"
								title="Mantelfläche Kegel Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-oberflaecheKegelGesamt">
							<span class="result-label">Oberfläche Gesamt:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKegelGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-oberflaecheKegelGesamt"
								title="Oberfläche Kegel Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-volumenKegelGesamt"
							class="${kreis.kegelVoluminaGleich ? 'result-notice-equal' : ''}">
							<span class="result-label">Volumen Gesamt:</span> <span
								class="result-icon-container"> <c:if
									test="${kreis.kegelVoluminaGleich}">
									<span class="info-icon"
										title="Hinweis: Die Kegelvolumina sind gleich groß!">ⓘ</span>
								</c:if>
							</span> <span class="result-value">${kreis.volumenKegelGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-volumenKegelGesamt"
								title="Volumen Kegel Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
					<div class="result-section KugelResults">
						<h3>Kugel Gesamt</h3>
						<p id="result-oberflaecheKugelGesamt">
							<span class="result-label">Oberfläche Gesamt:</span><span
								class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKugelGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-oberflaecheKugelGesamt"
								title="Oberfläche Kugel Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
						<p id="result-volumenKugelGesamt"
							class="${kreis.kugelVoluminaGleich ? 'result-notice-equal' : ''}">
							<span class="result-label">Volumen Gesamt:</span> <span
								class="result-icon-container"> <c:if
									test="${kreis.kugelVoluminaGleich}">
									<span class="info-icon"
										title="Hinweis: Die Kugelvolumina sind gleich groß!">ⓘ</span>
								</c:if>
							</span> <span class="result-value">${kreis.volumenKugelGesamt}</span>
							<button class="copy-button"
								data-copy-target-id="result-volumenKugelGesamt"
								title="Volumen Kugel Gesamt kopieren">
								<%@ include file="jsp_pages/copyIcon.jsp"%>
							</button>
						</p>
					</div>
				</div>
				<%-- End Tab 3 --%>
			</div>
			<%-- end .tab-content-container.results-container.Results --%>
		</c:if>
		<%-- End check for not empty kreis --%>
	</details>
	<%-- End results-details --%>

	<!-- ==================== START COMBINED SCRIPT ==================== -->
	<script src="js/kreise.js"></script>

</body>
<footer>
	<%@ include file="jsp_pages/footer.jsp"%>
</footer>
</html>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%-- Link to external CSS. If you prefer inline styles, remove this and use the <style> block below. --%>
<link rel="stylesheet" href="css/kreise.css">
<title>Kreise</title>
<%-- Optional: Inline styles if not using external kreise.css --%>
<%--
<style>
    /* Paste the entire content of kreise.css here if you want inline styles */
</style>
--%>
</head>
<header>
    <nav>
        <a href="kreise.jsp" class="nav-link active">Kreise</a>
        <a href="dreiecke.jsp" class="nav-link">Dreiecke</a> <%-- Assuming this link exists --%>
    </nav>
</header>
<body>
    <h1>Eingabe</h1>

    <form action="KreisServlet" method="post">
        <div class="content">
            <!-- Kreis Inputs -->
            <div class="Kreis">
                <h3>Kreis</h3>
                <label for="radius1">Radius 1:</label><input type="text" id="radius1" name="radius1" value="0"><br><br>
                <label for="radius2">Radius 2:</label><input type="text" id="radius2" name="radius2" value="0"><br><br>
                <label for="durchmesser1">Durchmesser 1:</label><input type="text" id="durchmesser1" name="durchmesser1" value="0"><br><br>
                <label for="durchmesser2">Durchmesser 2:</label><input type="text" id="durchmesser2" name="durchmesser2" value="0"><br><br>
                <label for="umfang1">Umfang 1:</label><input type="text" id="umfang1" name="umfang1" value="0"><br><br>
                <label for="umfang2">Umfang 2:</label><input type="text" id="umfang2" name="umfang2" value="0"><br><br>
                <label for="flaecheninhalt1">Flächeninhalt 1:</label><input type="text" id="flaecheninhalt1" name="flaecheninhalt1" value="0"><br><br>
                <label for="flaecheninhalt2">Flächeninhalt 2:</label><input type="text" id="flaecheninhalt2" name="flaecheninhalt2" value="0"><br><br>
            </div>
            <!-- Kreisteile Inputs -->
            <div class="Kreisteile">
                <h3>Kreisteile</h3>
                <label for="kreisbogen1">Kreisbogen 1:</label><input type="text" id="kreisbogen1" name="kreisbogen1" value="0"><br><br>
                <label for="kreisbogen2">Kreisbogen 2:</label><input type="text" id="kreisbogen2" name="kreisbogen2" value="0"><br><br>
                <label for="kreisausschnitt1">Kreisausschnitt 1:</label><input type="text" id="kreisausschnitt1" name="kreisausschnitt1" value="0"><br><br>
                <label for="kreisausschnitt2">Kreisausschnitt 2:</label><input type="text" id="kreisausschnitt2" name="kreisausschnitt2" value="0"><br><br>
                <label for="alpha">Alpha (Grad):</label><input type="text" id="alpha" name="alpha" value="0"><br><br>
            </div>
            <!-- Zylinder Inputs -->
            <div class="Zylinder">
                <h3>Zylinder</h3>
                <label for="hoehe1">Höhe 1:</label><input type="text" id="hoehe1" name="hoehe1" value="0"><br><br>
                <label for="hoehe2">Höhe 2:</label><input type="text" id="hoehe2" name="hoehe2" value="0"><br><br>
                <label for="grundflaecheZylinder1">Grundfläche Zylinder 1:</label><input type="text" id="grundflaecheZylinder1" name="grundflaecheZylinder1" value="0"><br><br>
                <label for="grundflaecheZylinder2">Grundfläche Zylinder 2:</label><input type="text" id="grundflaecheZylinder2" name="grundflaecheZylinder2" value="0"><br><br>
                <label for="mantelflaecheZylinder1">Mantelfläche Zylinder 1:</label><input type="text" id="mantelflaecheZylinder1" name="mantelflaecheZylinder1" value="0"><br><br>
                <label for="mantelflaecheZylinder2">Mantelfläche Zylinder 2:</label><input type="text" id="mantelflaecheZylinder2" name="mantelflaecheZylinder2" value="0"><br><br>
                <label for="oberflaecheZylinder1">Oberfläche Zylinder 1:</label><input type="text" id="oberflaecheZylinder1" name="oberflaecheZylinder1" value="0"><br><br>
                <label for="oberflaecheZylinder2">Oberfläche Zylinder 2:</label><input type="text" id="oberflaecheZylinder2" name="oberflaecheZylinder2" value="0"><br><br>
                <label for="volumenZylinder1">Volumen Zylinder 1:</label><input type="text" id="volumenZylinder1" name="volumenZylinder1" value="0"><br><br>
                <label for="volumenZylinder2">Volumen Zylinder 2:</label><input type="text" id="volumenZylinder2" name="volumenZylinder2" value="0"><br><br>
            </div>
            <!-- Kegel Inputs -->
            <div class="Kegel">
                 <h3>Kegel</h3>
                 <label for="seitenhoehe1">Seitenhöhe 1:</label><input type="text" id="seitenhoehe1" name="seitenhoehe1" value="0"><br><br>
                 <label for="seitenhoehe2">Seitenhöhe 2:</label><input type="text" id="seitenhoehe2" name="seitenhoehe2" value="0"><br><br>
                 <label for="grundflaecheKegel1">Grundfläche Kegel 1:</label><input type="text" id="grundflaecheKegel1" name="grundflaecheKegel1" value="0"><br><br>
                 <label for="grundflaecheKegel2">Grundfläche Kegel 2:</label><input type="text" id="grundflaecheKegel2" name="grundflaecheKegel2" value="0"><br><br>
                 <label for="mantelflaecheKegel1">Mantelfläche Kegel 1:</label><input type="text" id="mantelflaecheKegel1" name="mantelflaecheKegel1" value="0"><br><br>
                 <label for="mantelflaecheKegel2">Mantelfläche Kegel 2:</label><input type="text" id="mantelflaecheKegel2" name="mantelflaecheKegel2" value="0"><br><br>
                 <label for="oberflaecheKegel1">Oberfläche Kegel 1:</label><input type="text" id="oberflaecheKegel1" name="oberflaecheKegel1" value="0"><br><br>
                 <label for="oberflaecheKegel2">Oberfläche Kegel 2:</label><input type="text" id="oberflaecheKegel2" name="oberflaecheKegel2" value="0"><br><br>
                 <label for="volumenKegel1">Volumen Kegel 1:</label><input type="text" id="volumenKegel1" name="volumenKegel1" value="0"><br><br>
                 <label for="volumenKegel2">Volumen Kegel 2:</label><input type="text" id="volumenKegel2" name="volumenKegel2" value="0"><br><br>
            </div>
            <!-- Kugel Inputs -->
            <div class="Kugel">
                 <h3>Kugel</h3>
                 <label for="oberflaecheKugel1">Oberfläche Kugel 1:</label><input type="text" id="oberflaecheKugel1" name="oberflaecheKugel1" value="0"><br><br>
                 <label for="oberflaecheKugel2">Oberfläche Kugel 2:</label><input type="text" id="oberflaecheKugel2" name="oberflaecheKugel2" value="0"><br><br>
                 <label for="volumenKugel1">Volumen Kugel 1:</label><input type="text" id="volumenKugel1" name="volumenKugel1" value="0"><br><br>
                 <label for="volumenKugel2">Volumen Kugel 2:</label><input type="text" id="volumenKugel2" name="volumenKugel2" value="0"><br><br>
            </div>
        </div> <!-- end .content -->

        <label for="decimalPlaces">Dezimalstellen:</label>
        <input type="number" id="decimalPlaces" name="decimalPlaces" value="2" min="0" max="10" required>
        <button type="submit" name="action" value="berechneKreise" class="button">Berechne</button>
    </form>

    <!-- ==================== Results Section ==================== -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <details class="results-details" ${not empty kreis ? 'open' : ''}>
        <summary>
             Ergebnisse
        </summary>
         <c:if test="${not empty kreis}">
            <div class="Results">
                <!-- KreisResults -->
                <div class="KreisResults">
                    <h3>Kreis</h3>
                    <p id="result-radius1"><span class="result-label">Radius 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.radius1}</span></p>
                    <p id="result-radius2"><span class="result-label">Radius 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.radius2}</span></p>
                    <p id="result-durchmesser1"><span class="result-label">Durchmesser 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.durchmesser1}</span></p>
                    <p id="result-durchmesser2"><span class="result-label">Durchmesser 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.durchmesser2}</span></p>
                    <p id="result-umfang1"><span class="result-label">Umfang 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.umfang1}</span></p>
                    <p id="result-umfang2"><span class="result-label">Umfang 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.umfang2}</span></p>
                    <p id="result-flaecheninhalt1"><span class="result-label">Flächeninhalt 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.flaecheninhalt1}</span></p>
                    <p id="result-flaecheninhalt2"><span class="result-label">Flächeninhalt 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.flaecheninhalt2}</span></p>
                    <p id="result-flaecheninhaltGesamt" class="${kreis.grundflaechenGleich ? 'result-notice-equal' : ''}">
                        <span class="result-label">Flächeninhalt Gesamt:</span>
                        <span class="result-icon-container">
                            <c:if test="${kreis.grundflaechenGleich}">
                                <span class="info-icon" title="Hinweis: Die Kreisflächen sind gleich groß!">ⓘ</span>
                            </c:if>
                        </span>
                        <span class="result-value">${kreis.flaecheninhaltGesamt}</span>
                    </p>
                </div>

                <!-- KreisteileResults -->
                 <div class="KreisteileResults">
                    <h3>Kreisteile</h3>
                    <p id="result-kreisbogen1"><span class="result-label">Kreisbogen 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.kreisbogen1}</span></p>
                    <p id="result-kreisbogen2"><span class="result-label">Kreisbogen 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.kreisbogen2}</span></p>
                    <p id="result-kreisausschnitt1"><span class="result-label">Kreisausschnitt 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.kreisausschnitt1}</span></p>
                    <p id="result-kreisausschnitt2"><span class="result-label">Kreisausschnitt 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.kreisausschnitt2}</span></p>
                    <p id="result-kreisausschnittGesamt" class="${kreis.kreisausschnitteGleich ? 'result-notice-equal' : ''}">
                        <span class="result-label">Kreisausschnitt Gesamt:</span>
                        <span class="result-icon-container">
                             <c:if test="${kreis.kreisausschnitteGleich}">
                                <span class="info-icon" title="Hinweis: Die Kreisausschnitte sind gleich groß!">ⓘ</span>
                            </c:if>
                        </span>
                        <span class="result-value">${kreis.kreisausschnittGesamt}</span>
                    </p>
                    <p id="result-alpha"><span class="result-label">Alpha:</span><span class="result-icon-container"></span><span class="result-value">${kreis.alpha}</span></p>
                </div>

                <!-- ZylinderResults -->
                <div class="ZylinderResults">
                    <h3>Zylinder</h3>
                    <p id="result-hoehe1"><span class="result-label">Höhe 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.hoehe1}</span></p>
                    <p id="result-hoehe2"><span class="result-label">Höhe 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.hoehe2}</span></p>
                    <p id="result-grundflaecheZylinder1"><span class="result-label">Grundfläche 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.grundflaecheZylinder1}</span></p>
                    <p id="result-grundflaecheZylinder2"><span class="result-label">Grundfläche 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.grundflaecheZylinder2}</span></p>
                    <p id="result-grundflaecheZylinderGesamt" class="${kreis.zylinderGrundflaechenGleich ? 'result-notice-equal' : ''}">
                        <span class="result-label">Grundfläche Gesamt:</span>
                        <span class="result-icon-container">
                             <c:if test="${kreis.zylinderGrundflaechenGleich}">
                                <span class="info-icon" title="Hinweis: Die Zylinder-Grundflächen sind gleich groß!">ⓘ</span>
                            </c:if>
                        </span>
                        <span class="result-value">${kreis.grundflaecheZylinderGesamt}</span>
                    </p>
                    <p id="result-mantelflaecheZylinder1"><span class="result-label">Mantelfläche 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheZylinder1}</span></p>
                    <p id="result-mantelflaecheZylinder2"><span class="result-label">Mantelfläche 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheZylinder2}</span></p>
                    <p id="result-mantelflaecheZylinderGesamt"><span class="result-label">Mantelfläche Gesamt:</span><span class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheZylinderGesamt}</span></p>
                    <p id="result-oberflaecheZylinder1"><span class="result-label">Oberfläche 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheZylinder1}</span></p>
                    <p id="result-oberflaecheZylinder2"><span class="result-label">Oberfläche 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheZylinder2}</span></p>
                    <p id="result-oberflaecheZylinderGesamt"><span class="result-label">Oberfläche Gesamt:</span><span class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheZylinderGesamt}</span></p>
                    <p id="result-volumenZylinder1"><span class="result-label">Volumen 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.volumenZylinder1}</span></p>
                    <p id="result-volumenZylinder2"><span class="result-label">Volumen 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.volumenZylinder2}</span></p>
                    <p id="result-volumenZylinderGesamt" class="${kreis.zylinderVoluminaGleich ? 'result-notice-equal' : ''}">
                        <span class="result-label">Volumen Gesamt:</span>
                        <span class="result-icon-container">
                             <c:if test="${kreis.zylinderVoluminaGleich}">
                                <span class="info-icon" title="Hinweis: Die Zylindervolumina sind gleich groß!">ⓘ</span>
                            </c:if>
                        </span>
                        <span class="result-value">${kreis.volumenZylinderGesamt}</span>
                    </p>
                </div>

                <!-- KegelResults -->
                 <div class="KegelResults">
                    <h3>Kegel</h3>
                    <p id="result-seitenhoehe1"><span class="result-label">Seitenhöhe 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.seitenhoehe1}</span></p>
                    <p id="result-seitenhoehe2"><span class="result-label">Seitenhöhe 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.seitenhoehe2}</span></p>
                    <p id="result-grundflaecheKegel1"><span class="result-label">Grundfläche 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.grundflaecheKegel1}</span></p>
                    <p id="result-grundflaecheKegel2"><span class="result-label">Grundfläche 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.grundflaecheKegel2}</span></p>
                    <p id="result-grundflaecheKegelGesamt" class="${kreis.kegelGrundflaechenGleich ? 'result-notice-equal' : ''}">
                         <span class="result-label">Grundfläche Gesamt:</span>
                         <span class="result-icon-container">
                             <c:if test="${kreis.kegelGrundflaechenGleich}">
                                <span class="info-icon" title="Hinweis: Die Kegel-Grundflächen sind gleich groß!">ⓘ</span>
                            </c:if>
                         </span>
                         <span class="result-value">${kreis.grundflaecheKegelGesamt}</span>
                    </p>
                    <p id="result-mantelflaecheKegel1"><span class="result-label">Mantelfläche 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheKegel1}</span></p>
                    <p id="result-mantelflaecheKegel2"><span class="result-label">Mantelfläche 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheKegel2}</span></p>
                    <p id="result-mantelflaecheKegelGesamt"><span class="result-label">Mantelfläche Gesamt:</span><span class="result-icon-container"></span><span class="result-value">${kreis.mantelflaecheKegelGesamt}</span></p>
                    <p id="result-oberflaecheKegel1"><span class="result-label">Oberfläche 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKegel1}</span></p>
                    <p id="result-oberflaecheKegel2"><span class="result-label">Oberfläche 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKegel2}</span></p>
                    <p id="result-oberflaecheKegelGesamt"><span class="result-label">Oberfläche Gesamt:</span><span class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKegelGesamt}</span></p>
                    <p id="result-volumenKegel1"><span class="result-label">Volumen 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.volumenKegel1}</span></p>
                    <p id="result-volumenKegel2"><span class="result-label">Volumen 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.volumenKegel2}</span></p>
                    <p id="result-volumenKegelGesamt" class="${kreis.kegelVoluminaGleich ? 'result-notice-equal' : ''}">
                        <span class="result-label">Volumen Gesamt:</span>
                        <span class="result-icon-container">
                             <c:if test="${kreis.kegelVoluminaGleich}">
                                <span class="info-icon" title="Hinweis: Die Kegelvolumina sind gleich groß!">ⓘ</span>
                            </c:if>
                        </span>
                        <span class="result-value">${kreis.volumenKegelGesamt}</span>
                    </p>
                </div>

                <!-- KugelResults -->
                <div class="KugelResults">
                    <h3>Kugel</h3>
                    <p id="result-oberflaecheKugel1"><span class="result-label">Oberfläche 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKugel1}</span></p>
                    <p id="result-oberflaecheKugel2"><span class="result-label">Oberfläche 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKugel2}</span></p>
                    <p id="result-oberflaecheKugelGesamt"><span class="result-label">Oberfläche Gesamt:</span><span class="result-icon-container"></span><span class="result-value">${kreis.oberflaecheKugelGesamt}</span></p>
                    <p id="result-volumenKugel1"><span class="result-label">Volumen 1:</span><span class="result-icon-container"></span><span class="result-value">${kreis.volumenKugel1}</span></p>
                    <p id="result-volumenKugel2"><span class="result-label">Volumen 2:</span><span class="result-icon-container"></span><span class="result-value">${kreis.volumenKugel2}</span></p>
                    <p id="result-volumenKugelGesamt" class="${kreis.kugelVoluminaGleich ? 'result-notice-equal' : ''}">
                        <span class="result-label">Volumen Gesamt:</span>
                         <span class="result-icon-container">
                            <c:if test="${kreis.kugelVoluminaGleich}">
                                <span class="info-icon" title="Hinweis: Die Kugelvolumina sind gleich groß!">ⓘ</span>
                            </c:if>
                        </span>
                        <span class="result-value">${kreis.volumenKugelGesamt}</span>
                    </p>
                </div>
            </div> <!-- End .Results -->
        </c:if> <!-- End check for not empty kreis -->
    </details> <!-- End results-details -->

    <!-- ==================== START COMBINED SCRIPT ==================== -->
    <script>
        // --- Requirements Map ---
        const calculationRequirementsMap = { /* ... Paste your complete map here ... */
            // --- Kreis ---
            'radius1': [
                ['durchmesser1'], ['umfang1'], ['flaecheninhalt1'], ['kreisbogen1', 'alpha'], ['kreisausschnitt1', 'alpha'],
                ['kreisausschnitt1', 'kreisbogen1'], ['oberflaecheKugel1'], ['volumenKugel1'], ['grundflaecheZylinder1'],
                ['grundflaecheKegel1'], ['mantelflaecheZylinder1', 'hoehe1'], ['volumenZylinder1', 'hoehe1'],
                ['mantelflaecheKegel1', 'seitenhoehe1'], ['seitenhoehe1', 'hoehe1'], ['volumenKegel1', 'hoehe1'],
                ['oberflaecheZylinder1', 'hoehe1'], ['oberflaecheKegel1', 'seitenhoehe1'], ['oberflaecheKegel1', 'hoehe1']
            ],
            'durchmesser1': [ ['radius1'], ['umfang1'], ['flaecheninhalt1'], /* ... */ ],
            'umfang1': [ ['radius1'], ['durchmesser1'], ['flaecheninhalt1'], ['kreisbogen1', 'alpha'], ['mantelflaecheZylinder1', 'hoehe1'] ],
            'flaecheninhalt1': [ ['radius1'], ['durchmesser1'], ['umfang1'], ['grundflaecheZylinder1'], ['grundflaecheKegel1'], ['kreisausschnitt1', 'alpha'] ],
            'radius2': [
                ['durchmesser2'], ['umfang2'], ['flaecheninhalt2'], ['kreisbogen2', 'alpha'], ['kreisausschnitt2', 'alpha'],
                ['kreisausschnitt2', 'kreisbogen2'], ['oberflaecheKugel2'], ['volumenKugel2'], ['grundflaecheZylinder2'],
                ['grundflaecheKegel2'], ['mantelflaecheZylinder2', 'hoehe2'], ['volumenZylinder2', 'hoehe2'],
                ['mantelflaecheKegel2', 'seitenhoehe2'], ['seitenhoehe2', 'hoehe2'], ['volumenKegel2', 'hoehe2'],
                ['oberflaecheZylinder2', 'hoehe2'], ['oberflaecheKegel2', 'seitenhoehe2'], ['oberflaecheKegel2', 'hoehe2']
            ],
             'durchmesser2': [ ['radius2'], ['umfang2'], ['flaecheninhalt2'], /* ... */ ],
             'umfang2': [ ['radius2'], ['durchmesser2'], ['flaecheninhalt2'], ['kreisbogen2', 'alpha'], ['mantelflaecheZylinder2', 'hoehe2'] ],
             'flaecheninhalt2': [ ['radius2'], ['durchmesser2'], ['umfang2'], ['grundflaecheZylinder2'], ['grundflaecheKegel2'], ['kreisausschnitt2', 'alpha'] ],
            // --- Kreisteile ---
            'kreisbogen1': [ ['radius1', 'alpha'], ['umfang1', 'alpha'], ['durchmesser1', 'alpha'], ['kreisausschnitt1', 'radius1'] ],
            'kreisbogen2': [ ['radius2', 'alpha'], ['umfang2', 'alpha'], ['durchmesser2', 'alpha'], ['kreisausschnitt2', 'radius2'] ],
            'kreisausschnitt1': [ ['radius1', 'alpha'], ['flaecheninhalt1', 'alpha'], ['kreisbogen1', 'radius1'] ],
            'kreisausschnitt2': [ ['radius2', 'alpha'], ['flaecheninhalt2', 'alpha'], ['kreisbogen2', 'radius2'] ],
            'alpha': [ ['kreisbogen1', 'umfang1'], ['kreisausschnitt1', 'flaecheninhalt1'], ['kreisbogen2', 'umfang2'], ['kreisausschnitt2', 'flaecheninhalt2'] ],
            // --- Zylinder 1 ---
            'hoehe1': [ ['mantelflaecheZylinder1', 'radius1'], ['volumenZylinder1', 'grundflaecheZylinder1'], ['volumenZylinder1', 'radius1'], ['oberflaecheZylinder1', 'radius1'], ['seitenhoehe1', 'radius1'] , ['volumenKegel1', 'grundflaecheKegel1'], ['volumenKegel1', 'radius1'] ],
            'grundflaecheZylinder1': [ ['radius1'], ['flaecheninhalt1'], ['volumenZylinder1', 'hoehe1'], ['oberflaecheZylinder1', 'mantelflaecheZylinder1'] ],
            'mantelflaecheZylinder1': [ ['radius1', 'hoehe1'], ['umfang1', 'hoehe1'], ['oberflaecheZylinder1', 'grundflaecheZylinder1'], ['oberflaecheZylinder1', 'radius1'] ],
            'oberflaecheZylinder1': [ ['grundflaecheZylinder1', 'mantelflaecheZylinder1'], ['radius1', 'hoehe1'], ['radius1', 'mantelflaecheZylinder1'] ],
            'volumenZylinder1': [ ['grundflaecheZylinder1', 'hoehe1'], ['radius1', 'hoehe1'] ],
            // --- Zylinder 2 ---
             'hoehe2': [ ['mantelflaecheZylinder2', 'radius2'], ['volumenZylinder2', 'grundflaecheZylinder2'], ['volumenZylinder2', 'radius2'], ['oberflaecheZylinder2', 'radius2'], ['seitenhoehe2', 'radius2'] , ['volumenKegel2', 'grundflaecheKegel2'] , ['volumenKegel2', 'radius2'] ],
             'grundflaecheZylinder2': [ ['radius2'], ['flaecheninhalt2'], ['volumenZylinder2', 'hoehe2'], ['oberflaecheZylinder2', 'mantelflaecheZylinder2'] ],
             'mantelflaecheZylinder2': [ ['radius2', 'hoehe2'], ['umfang2', 'hoehe2'], ['oberflaecheZylinder2', 'grundflaecheZylinder2'], ['oberflaecheZylinder2', 'radius2'] ],
             'oberflaecheZylinder2': [ ['grundflaecheZylinder2', 'mantelflaecheZylinder2'], ['radius2', 'hoehe2'], ['radius2', 'mantelflaecheZylinder2'] ],
             'volumenZylinder2': [ ['grundflaecheZylinder2', 'hoehe2'], ['radius2', 'hoehe2'] ],
            // --- Kegel 1 ---
            'seitenhoehe1': [ ['radius1', 'hoehe1'], ['mantelflaecheKegel1', 'radius1'], ['oberflaecheKegel1', 'radius1'] ],
            'grundflaecheKegel1': [ ['radius1'], ['flaecheninhalt1'], ['volumenKegel1', 'hoehe1'], ['oberflaecheKegel1', 'mantelflaecheKegel1'] ],
            'mantelflaecheKegel1': [ ['radius1', 'seitenhoehe1'], ['radius1', 'hoehe1'], ['oberflaecheKegel1', 'grundflaecheKegel1'], ['oberflaecheKegel1', 'radius1'] ],
            'oberflaecheKegel1': [ ['grundflaecheKegel1', 'mantelflaecheKegel1'], ['radius1', 'seitenhoehe1'], ['radius1', 'hoehe1'], ['radius1', 'mantelflaecheKegel1'] ],
            'volumenKegel1': [ ['grundflaecheKegel1', 'hoehe1'], ['radius1', 'hoehe1'] ],
             // --- Kegel 2 ---
             'seitenhoehe2': [ ['radius2', 'hoehe2'], ['mantelflaecheKegel2', 'radius2'], ['oberflaecheKegel2', 'radius2'] ],
             'grundflaecheKegel2': [ ['radius2'], ['flaecheninhalt2'], ['volumenKegel2', 'hoehe2'], ['oberflaecheKegel2', 'mantelflaecheKegel2'] ],
             'mantelflaecheKegel2': [ ['radius2', 'seitenhoehe2'], ['radius2', 'hoehe2'], ['oberflaecheKegel2', 'grundflaecheKegel2'], ['oberflaecheKegel2', 'radius2'] ],
             'oberflaecheKegel2': [ ['grundflaecheKegel2', 'mantelflaecheKegel2'], ['radius2', 'seitenhoehe2'], ['radius2', 'hoehe2'], ['radius2', 'mantelflaecheKegel2'] ],
             'volumenKegel2': [ ['grundflaecheKegel2', 'hoehe2'], ['radius2', 'hoehe2'] ],
            // --- Kugel 1 ---
            'oberflaecheKugel1': [ ['radius1'], ['durchmesser1'], ['volumenKugel1'] ],
            'volumenKugel1': [ ['radius1'], ['durchmesser1'], ['oberflaecheKugel1'] ],
            // --- Kugel 2 ---
             'oberflaecheKugel2': [ ['radius2'], ['durchmesser2'], ['volumenKugel2'] ],
             'volumenKugel2': [ ['radius2'], ['durchmesser2'], ['oberflaecheKugel2'] ],
         };
        // --- DOM References ---
        const inputFields = document.querySelectorAll('.content input[type="text"]');

        // --- Helper Functions ---
        function isNonZeroNumeric(inputElement) {
            if (!inputElement) return false;
            const value = inputElement.value.trim();
            if (value === '') return false;
            const numValue = parseFloat(value);
            return !isNaN(numValue) && numValue !== 0;
        }

        function checkConditionMet(requiredInputIds) {
            return requiredInputIds.every(id => isNonZeroNumeric(document.getElementById(id)));
        }

        // --- Result Value Check Function ---
        function isResultValueValid(pElement) {
            // Check if the paragraph has the notice class first
             if (pElement.classList.contains('result-notice-equal')) {
                 // Even if notice is present, check if value is non-error like NaN
                 // But don't highlight green if it's zero
                 const valueSpan = pElement.querySelector('.result-value');
                 if (!valueSpan) return false;
                 const valueText = valueSpan.textContent.trim();
                 const errorValues = ['NaN', 'Infinity', '-Infinity', '-1', '-1.0', '']; // Values indicating calculation failed
                 const zeroValues = ['0', '0.0'];
                 return !errorValues.includes(valueText) && !zeroValues.includes(valueText);
            }
            // Regular check for non-notice paragraphs
            const valueSpan = pElement.querySelector('.result-value');
            if (!valueSpan) return false;
            const valueText = valueSpan.textContent.trim();
            const notCalculatedOrZeroValues = ['0', '0.0', '-1', '-1.0', 'NaN', 'Infinity', '-Infinity', ''];
            return !notCalculatedOrZeroValues.includes(valueText);
        }


        // --- Main Highlighting Logic ---
        function updateInputHighlighting() {
            inputFields.forEach(targetInput => {
                const targetId = targetInput.id;
                let isProvided = false;
                let isCalculable = false;
                if (isNonZeroNumeric(targetInput)) {
                    isProvided = true;
                } else {
                    const requirements = calculationRequirementsMap[targetId];
                    if (requirements) {
                        for (const condition of requirements) {
                            if (checkConditionMet(condition)) { isCalculable = true; break; }
                        }
                    }
                }
                if (isProvided) {
                    targetInput.classList.add('input-has-value');
                    targetInput.classList.remove('input-calculable');
                } else if (isCalculable) {
                    targetInput.classList.remove('input-has-value');
                    targetInput.classList.add('input-calculable');
                } else {
                    targetInput.classList.remove('input-has-value');
                    targetInput.classList.remove('input-calculable');
                }
            });
        }

        function updateResultHighlighting() {
             const resultParagraphs = document.querySelectorAll('.Results p');
             resultParagraphs.forEach(pElement => {
                // Apply green highlight ONLY if value is valid AND it's NOT a yellow notice paragraph
                if (isResultValueValid(pElement) && !pElement.classList.contains('result-notice-equal')) {
                    pElement.classList.add('result-calculated');
                } else {
                    // Remove green highlight if value is invalid/zero OR if it's a yellow notice
                    pElement.classList.remove('result-calculated');
                }
            });
        }

        // --- Event Listeners & Initial State ---
        document.addEventListener('DOMContentLoaded', () => {
            inputFields.forEach(input => {
                input.addEventListener('input', updateInputHighlighting);
                input.addEventListener('blur', updateInputHighlighting);
            });
            updateInputHighlighting(); // Set initial input state
            updateResultHighlighting(); // Set initial result state
        });

    </script>
    <!-- ==================== END COMBINED SCRIPT ==================== -->

</body>
<footer>
    <a href="http://localhost:8080/HelloWeb/" class="button" target="_blank">Hello Web</a>
	<a href="http://localhost:8080/EmployeeWebApp/" class="button" target="_blank">EmployeeWebApp</a>
	<a href="http://localhost:8080/Workhours/" class="button" target="_blank">Workhours</a>
</footer>
</html>
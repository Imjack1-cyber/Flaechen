<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/dreiecke.css">
<title>Geometrie Rechner</title>
</head>
<header>
	<nav>
		<a href="kreise.jsp" class="nav-link">Kreise</a>
		<a href="dreiecke.jsp" class="nav-link active">Flächen & Körper</a>
	</nav>
</header>
<body>
	<h1>Geometrische Berechnungen</h1>

	<c:if test="${not empty errorMessage}">
		<p class="error-message">${errorMessage}</p>
	</c:if>
    <c:if test="${not empty fatalError}">
		<p class="fatal-error">${fatalError}</p>
	</c:if>

	<form action="DreieckServlet" method="post" id="geometrie-form">
		<input type="hidden" name="action" value="berechne">

		<%-- Tab Navigation für Eingaben --%>
        <div class="tab-nav input-tabs">
            <button type="button" class="tab-button active" onclick="showInputTab('flaechen', this)">Flächen</button>
            <button type="button" class="tab-button" onclick="showInputTab('koerper', this)">Körper</button>
            <button type="button" class="tab-button" onclick="showInputTab('common', this)">Anzeige</button>
        </div>

		<%-- Tab Content Container für Eingaben --%>
        <div class="tab-content-container content input-container">

            <%-- Tab 1: Flächen --%>
            <div id="input-tab-flaechen" class="tab-content active">
                <div class="input-section-row">
                    <!-- Dreieck Inputs -->
                    <div class="input-section Dreieck">
                        <h3>Dreieck</h3>
                        <div class="form-row"><label for="a">Seite a:</label><input type="text" id="a" name="a" value="${not empty param.a ? param.a : (not empty dreieck.a and dreieck.a ne 'NaN' and dreieck.a ne 'Ungültig' ? dreieck.a : '0')}"></div>
                        <div class="form-row"><label for="b">Seite b:</label><input type="text" id="b" name="b" value="${not empty param.b ? param.b : (not empty dreieck.b and dreieck.b ne 'NaN' and dreieck.b ne 'Ungültig' ? dreieck.b : '0')}"></div>
                        <div class="form-row"><label for="c">Seite c:</label><input type="text" id="c" name="c" value="${not empty param.c ? param.c : (not empty dreieck.c and dreieck.c ne 'NaN' and dreieck.c ne 'Ungültig' ? dreieck.c : '0')}"></div>
                        <div class="form-row"><label for="hoeheA">Höhe a (ha):</label><input type="text" id="hoeheA" name="hoeheA" value="${not empty param.hoeheA ? param.hoeheA : (not empty dreieck.hoeheA and dreieck.hoeheA ne 'NaN' and dreieck.hoeheA ne 'Ungültig' ? dreieck.hoeheA : '0')}"></div>
                        <div class="form-row"><label for="hoeheB">Höhe b (hb):</label><input type="text" id="hoeheB" name="hoeheB" value="${not empty param.hoeheB ? param.hoeheB : (not empty dreieck.hoeheB and dreieck.hoeheB ne 'NaN' and dreieck.hoeheB ne 'Ungültig' ? dreieck.hoeheB : '0')}"></div>
                        <div class="form-row"><label for="hoeheC">Höhe c (hc):</label><input type="text" id="hoeheC" name="hoeheC" value="${not empty param.hoeheC ? param.hoeheC : (not empty dreieck.hoeheC and dreieck.hoeheC ne 'NaN' and dreieck.hoeheC ne 'Ungültig' ? dreieck.hoeheC : '0')}"></div>
                        <div class="form-row"><label for="umfangDreieck">Umfang (U):</label><input type="text" id="umfangDreieck" name="umfangDreieck" value="${not empty param.umfangDreieck ? param.umfangDreieck : (not empty dreieck.umfangDreieck and dreieck.umfangDreieck ne 'NaN' and dreieck.umfangDreieck ne 'Ungültig' ? dreieck.umfangDreieck : '0')}"></div>
                        <div class="form-row"><label for="flaecheninhaltDreieck">Flächeninhalt (A):</label><input type="text" id="flaecheninhaltDreieck" name="flaecheninhaltDreieck" value="${not empty param.flaecheninhaltDreieck ? param.flaecheninhaltDreieck : (not empty dreieck.flaecheninhaltDreieck and dreieck.flaecheninhaltDreieck ne 'NaN' and dreieck.flaecheninhaltDreieck ne 'Ungültig' ? dreieck.flaecheninhaltDreieck : '0')}"></div>
                        <div class="form-row"><label for="alpha">Winkel Alpha (α):</label><input type="text" id="alpha" name="alpha" value="${not empty param.alpha ? param.alpha : (not empty dreieck.alpha and dreieck.alpha ne 'NaN' and dreieck.alpha ne 'Ungültig' ? dreieck.alpha : '0')}"></div>
                        <div class="form-row"><label for="beta">Winkel Beta (β):</label><input type="text" id="beta" name="beta" value="${not empty param.beta ? param.beta : (not empty dreieck.beta and dreieck.beta ne 'NaN' and dreieck.beta ne 'Ungültig' ? dreieck.beta : '0')}"></div>
                        <div class="form-row"><label for="gamma">Winkel Gamma (γ):</label><input type="text" id="gamma" name="gamma" value="${not empty param.gamma ? param.gamma : (not empty dreieck.gamma and dreieck.gamma ne 'NaN' and dreieck.gamma ne 'Ungültig' ? dreieck.gamma : '0')}"></div>
                    </div>
                    <!-- Rechteck Inputs -->
                    <div class="input-section Rechteck">
                        <h3>Rechteck</h3>
                        <div class="form-row"><label for="r_seiteA">Seite a:</label><input type="text" id="r_seiteA" name="r_seiteA" value="${not empty param.r_seiteA ? param.r_seiteA : (not empty rechteck.r_seiteA and rechteck.r_seiteA ne 'NaN' ? rechteck.r_seiteA : '0')}"></div>
                        <div class="form-row"><label for="r_seiteB">Seite b:</label><input type="text" id="r_seiteB" name="r_seiteB" value="${not empty param.r_seiteB ? param.r_seiteB : (not empty rechteck.r_seiteB and rechteck.r_seiteB ne 'NaN' ? rechteck.r_seiteB : '0')}"></div>
                        <div class="form-row"><label for="r_flaecheninhalt">Flächeninhalt (A):</label><input type="text" id="r_flaecheninhalt" name="r_flaecheninhalt" value="${not empty param.r_flaecheninhalt ? param.r_flaecheninhalt : (not empty rechteck.r_flaecheninhalt and rechteck.r_flaecheninhalt ne 'NaN' ? rechteck.r_flaecheninhalt : '0')}"></div>
                        <div class="form-row"><label for="r_umfang">Umfang (U):</label><input type="text" id="r_umfang" name="r_umfang" value="${not empty param.r_umfang ? param.r_umfang : (not empty rechteck.r_umfang and rechteck.r_umfang ne 'NaN' ? rechteck.r_umfang : '0')}"></div>
                        <div class="form-row"><label for="r_diagonale">Diagonale (d):</label><input type="text" id="r_diagonale" name="r_diagonale" value="${not empty param.r_diagonale ? param.r_diagonale : (not empty rechteck.r_diagonale and rechteck.r_diagonale ne 'NaN' ? rechteck.r_diagonale : '0')}"></div>
                    </div>
                     <!-- Trapez Inputs -->
                    <div class="input-section Trapez">
                        <h3>Trapez</h3>
                        <div class="form-row"><label for="t_seiteA">Seite a (parallel):</label><input type="text" id="t_seiteA" name="t_seiteA" value="${not empty param.t_seiteA ? param.t_seiteA : (not empty trapez.t_seiteA and trapez.t_seiteA ne 'NaN' ? trapez.t_seiteA : '0')}"></div>
                        <div class="form-row"><label for="t_seiteB">Seite b (Schenkel):</label><input type="text" id="t_seiteB" name="t_seiteB" value="${not empty param.t_seiteB ? param.t_seiteB : (not empty trapez.t_seiteB and trapez.t_seiteB ne 'NaN' ? trapez.t_seiteB : '0')}"></div>
                        <div class="form-row"><label for="t_seiteC">Seite c (parallel):</label><input type="text" id="t_seiteC" name="t_seiteC" value="${not empty param.t_seiteC ? param.t_seiteC : (not empty trapez.t_seiteC and trapez.t_seiteC ne 'NaN' ? trapez.t_seiteC : '0')}"></div>
                        <div class="form-row"><label for="t_seiteD">Seite d (Schenkel):</label><input type="text" id="t_seiteD" name="t_seiteD" value="${not empty param.t_seiteD ? param.t_seiteD : (not empty trapez.t_seiteD and trapez.t_seiteD ne 'NaN' ? trapez.t_seiteD : '0')}"></div>
                        <div class="form-row"><label for="t_hoehe">Höhe (h):</label><input type="text" id="t_hoehe" name="t_hoehe" value="${not empty param.t_hoehe ? param.t_hoehe : (not empty trapez.t_hoehe and trapez.t_hoehe ne 'NaN' ? trapez.t_hoehe : '0')}"></div>
                        <div class="form-row"><label for="t_flaecheninhalt">Flächeninhalt (A):</label><input type="text" id="t_flaecheninhalt" name="t_flaecheninhalt" value="${not empty param.t_flaecheninhalt ? param.t_flaecheninhalt : (not empty trapez.t_flaecheninhalt and trapez.t_flaecheninhalt ne 'NaN' ? trapez.t_flaecheninhalt : '0')}"></div>
                        <div class="form-row"><label for="t_umfang">Umfang (U):</label><input type="text" id="t_umfang" name="t_umfang" value="${not empty param.t_umfang ? param.t_umfang : (not empty trapez.t_umfang and trapez.t_umfang ne 'NaN' ? trapez.t_umfang : '0')}"></div>
                    </div>
                     <!-- Parallelogramm Inputs -->
                    <div class="input-section Parallelogramm">
                        <h3>Parallelogramm</h3>
                        <div class="form-row"><label for="pa_seiteA">Seite a:</label><input type="text" id="pa_seiteA" name="pa_seiteA" value="${not empty param.pa_seiteA ? param.pa_seiteA : (not empty parallelogramm.pa_seiteA and parallelogramm.pa_seiteA ne 'NaN' ? parallelogramm.pa_seiteA : '0')}"></div>
                        <div class="form-row"><label for="pa_seiteB">Seite b:</label><input type="text" id="pa_seiteB" name="pa_seiteB" value="${not empty param.pa_seiteB ? param.pa_seiteB : (not empty parallelogramm.pa_seiteB and parallelogramm.pa_seiteB ne 'NaN' ? parallelogramm.pa_seiteB : '0')}"></div>
                        <div class="form-row"><label for="pa_hoeheA">Höhe auf a (ha):</label><input type="text" id="pa_hoeheA" name="pa_hoeheA" value="${not empty param.pa_hoeheA ? param.pa_hoeheA : (not empty parallelogramm.pa_hoeheA and parallelogramm.pa_hoeheA ne 'NaN' ? parallelogramm.pa_hoeheA : '0')}"></div>
                        <div class="form-row"><label for="pa_flaecheninhalt">Flächeninhalt (A):</label><input type="text" id="pa_flaecheninhalt" name="pa_flaecheninhalt" value="${not empty param.pa_flaecheninhalt ? param.pa_flaecheninhalt : (not empty parallelogramm.pa_flaecheninhalt and parallelogramm.pa_flaecheninhalt ne 'NaN' ? parallelogramm.pa_flaecheninhalt : '0')}"></div>
                        <div class="form-row"><label for="pa_umfang">Umfang (U):</label><input type="text" id="pa_umfang" name="pa_umfang" value="${not empty param.pa_umfang ? param.pa_umfang : (not empty parallelogramm.pa_umfang and parallelogramm.pa_umfang ne 'NaN' ? parallelogramm.pa_umfang : '0')}"></div>
                    </div>
                     <!-- Pentagon Inputs -->
                    <div class="input-section Pentagon">
                        <h3>Regelmäßiges Fünfeck</h3>
                        <div class="form-row"><label for="pe_seite">Seite (a):</label><input type="text" id="pe_seite" name="pe_seite" value="${not empty param.pe_seite ? param.pe_seite : (not empty pentagon.pe_seite and pentagon.pe_seite ne 'NaN' ? pentagon.pe_seite : '0')}"></div>
                        <div class="form-row"><label for="pe_umfang">Umfang (U):</label><input type="text" id="pe_umfang" name="pe_umfang" value="${not empty param.pe_umfang ? param.pe_umfang : (not empty pentagon.pe_umfang and pentagon.pe_umfang ne 'NaN' ? pentagon.pe_umfang : '0')}"></div>
                        <div class="form-row"><label for="pe_radiusInkreis">Inkreisradius (r):</label><input type="text" id="pe_radiusInkreis" name="pe_radiusInkreis" value="${not empty param.pe_radiusInkreis ? param.pe_radiusInkreis : (not empty pentagon.pe_radiusInkreis and pentagon.pe_radiusInkreis ne 'NaN' ? pentagon.pe_radiusInkreis : '0')}"></div>
                        <div class="form-row"><label for="pe_radiusUmkreis">Umkreisradius (R):</label><input type="text" id="pe_radiusUmkreis" name="pe_radiusUmkreis" value="${not empty param.pe_radiusUmkreis ? param.pe_radiusUmkreis : (not empty pentagon.pe_radiusUmkreis and pentagon.pe_radiusUmkreis ne 'NaN' ? pentagon.pe_radiusUmkreis : '0')}"></div>
                        <div class="form-row"><label for="pe_flaecheninhalt">Flächeninhalt (A):</label><input type="text" id="pe_flaecheninhalt" name="pe_flaecheninhalt" value="${not empty param.pe_flaecheninhalt ? param.pe_flaecheninhalt : (not empty pentagon.pe_flaecheninhalt and pentagon.pe_flaecheninhalt ne 'NaN' ? pentagon.pe_flaecheninhalt : '0')}"></div>
                    </div>
                    <!-- Hexagon Inputs -->
                    <div class="input-section Hexagon">
                        <h3>Regelmäßiges Sechseck</h3>
                        <div class="form-row"><label for="h_seite">Seite (a):</label><input type="text" id="h_seite" name="h_seite" value="${not empty param.h_seite ? param.h_seite : (not empty hexagon.h_seite and hexagon.h_seite ne 'NaN' ? hexagon.h_seite : '0')}"></div>
                        <div class="form-row"><label for="h_umfang">Umfang (U):</label><input type="text" id="h_umfang" name="h_umfang" value="${not empty param.h_umfang ? param.h_umfang : (not empty hexagon.h_umfang and hexagon.h_umfang ne 'NaN' ? hexagon.h_umfang : '0')}"></div>
                        <div class="form-row"><label for="h_radiusInkreis">Inkreisradius (r):</label><input type="text" id="h_radiusInkreis" name="h_radiusInkreis" value="${not empty param.h_radiusInkreis ? param.h_radiusInkreis : (not empty hexagon.h_radiusInkreis and hexagon.h_radiusInkreis ne 'NaN' ? hexagon.h_radiusInkreis : '0')}"></div>
                        <div class="form-row"><label for="h_radiusUmkreis">Umkreisradius (R):</label><input type="text" id="h_radiusUmkreis" name="h_radiusUmkreis" value="${not empty param.h_radiusUmkreis ? param.h_radiusUmkreis : (not empty hexagon.h_radiusUmkreis and hexagon.h_radiusUmkreis ne 'NaN' ? hexagon.h_radiusUmkreis : '0')}"></div>
                        <div class="form-row"><label for="h_flaecheninhalt">Flächeninhalt (A):</label><input type="text" id="h_flaecheninhalt" name="h_flaecheninhalt" value="${not empty param.h_flaecheninhalt ? param.h_flaecheninhalt : (not empty hexagon.h_flaecheninhalt and hexagon.h_flaecheninhalt ne 'NaN' ? hexagon.h_flaecheninhalt : '0')}"></div>
                    </div>
                    <!-- Octagon Inputs -->
                    <div class="input-section Octagon">
                        <h3>Regelmäßiges Achteck</h3>
                        <div class="form-row"><label for="o_seite">Seite (a):</label><input type="text" id="o_seite" name="o_seite" value="${not empty param.o_seite ? param.o_seite : (not empty octagon.o_seite and octagon.o_seite ne 'NaN' ? octagon.o_seite : '0')}"></div>
                        <div class="form-row"><label for="o_umfang">Umfang (U):</label><input type="text" id="o_umfang" name="o_umfang" value="${not empty param.o_umfang ? param.o_umfang : (not empty octagon.o_umfang and octagon.o_umfang ne 'NaN' ? octagon.o_umfang : '0')}"></div>
                        <div class="form-row"><label for="o_radiusInkreis">Inkreisradius (r):</label><input type="text" id="o_radiusInkreis" name="o_radiusInkreis" value="${not empty param.o_radiusInkreis ? param.o_radiusInkreis : (not empty octagon.o_radiusInkreis and octagon.o_radiusInkreis ne 'NaN' ? octagon.o_radiusInkreis : '0')}"></div>
                        <div class="form-row"><label for="o_radiusUmkreis">Umkreisradius (R):</label><input type="text" id="o_radiusUmkreis" name="o_radiusUmkreis" value="${not empty param.o_radiusUmkreis ? param.o_radiusUmkreis : (not empty octagon.o_radiusUmkreis and octagon.o_radiusUmkreis ne 'NaN' ? octagon.o_radiusUmkreis : '0')}"></div>
                        <div class="form-row"><label for="o_flaecheninhalt">Flächeninhalt (A):</label><input type="text" id="o_flaecheninhalt" name="o_flaecheninhalt" value="${not empty param.o_flaecheninhalt ? param.o_flaecheninhalt : (not empty octagon.o_flaecheninhalt and octagon.o_flaecheninhalt ne 'NaN' ? octagon.o_flaecheninhalt : '0')}"></div>
                    </div>
                </div> <%-- End .input-section-row --%>
            </div> <%-- End Tab 1: Flächen --%>

            <%-- Tab 2: Körper --%>
            <div id="input-tab-koerper" class="tab-content">
                 <div class="input-section-row">
                     <!-- Pyramide Inputs -->
                    <div class="input-section Pyramide">
                        <h3>Quadratische Pyramide</h3>
                        <div class="form-row"><label for="p_seiteA">Grundseite (a):</label><input type="text" id="p_seiteA" name="p_seiteA" value="${not empty param.p_seiteA ? param.p_seiteA : (not empty pyramide.p_seiteA and pyramide.p_seiteA ne 'NaN' ? pyramide.p_seiteA : '0')}"></div>
                        <div class="form-row"><label for="p_hoehe">Körperhöhe (h):</label><input type="text" id="p_hoehe" name="p_hoehe" value="${not empty param.p_hoehe ? param.p_hoehe : (not empty pyramide.p_hoehe and pyramide.p_hoehe ne 'NaN' ? pyramide.p_hoehe : '0')}"></div>
                        <div class="form-row"><label for="p_hoeheSeite">Seitenhöhe (hs):</label><input type="text" id="p_hoeheSeite" name="p_hoeheSeite" value="${not empty param.p_hoeheSeite ? param.p_hoeheSeite : (not empty pyramide.p_hoeheSeite and pyramide.p_hoeheSeite ne 'NaN' ? pyramide.p_hoeheSeite : '0')}"></div>
                        <div class="form-row"><label for="p_seitenkante">Seitenkante (s):</label><input type="text" id="p_seitenkante" name="p_seitenkante" value="${not empty param.p_seitenkante ? param.p_seitenkante : (not empty pyramide.p_seitenkante and pyramide.p_seitenkante ne 'NaN' ? pyramide.p_seitenkante : '0')}"></div>
                        <div class="form-row"><label for="p_grundflaeche">Grundfläche (G):</label><input type="text" id="p_grundflaeche" name="p_grundflaeche" value="${not empty param.p_grundflaeche ? param.p_grundflaeche : (not empty pyramide.p_grundflaeche and pyramide.p_grundflaeche ne 'NaN' ? pyramide.p_grundflaeche : '0')}"></div>
                        <div class="form-row"><label for="p_mantelflaeche">Mantelfläche (M):</label><input type="text" id="p_mantelflaeche" name="p_mantelflaeche" value="${not empty param.p_mantelflaeche ? param.p_mantelflaeche : (not empty pyramide.p_mantelflaeche and pyramide.p_mantelflaeche ne 'NaN' ? pyramide.p_mantelflaeche : '0')}"></div>
                        <div class="form-row"><label for="p_oberflaeche">Oberfläche (O):</label><input type="text" id="p_oberflaeche" name="p_oberflaeche" value="${not empty param.p_oberflaeche ? param.p_oberflaeche : (not empty pyramide.p_oberflaeche and pyramide.p_oberflaeche ne 'NaN' ? pyramide.p_oberflaeche : '0')}"></div>
                        <div class="form-row"><label for="p_volumen">Volumen (V):</label><input type="text" id="p_volumen" name="p_volumen" value="${not empty param.p_volumen ? param.p_volumen : (not empty pyramide.p_volumen and pyramide.p_volumen ne 'NaN' ? pyramide.p_volumen : '0')}"></div>
                        <div class="form-row"><label for="p_grundflaecheDiagonale">Grundfl.-Diagonale (d):</label><input type="text" id="p_grundflaecheDiagonale" name="p_grundflaecheDiagonale" value="${not empty param.p_grundflaecheDiagonale ? param.p_grundflaecheDiagonale : (not empty pyramide.p_grundflaecheDiagonale and pyramide.p_grundflaecheDiagonale ne 'NaN' ? pyramide.p_grundflaecheDiagonale : '0')}"></div>
                    </div>
                    <!-- Quader Inputs -->
                    <div class="input-section Quader">
                        <h3>Quader</h3>
                        <div class="form-row"><label for="q_kanteA">Kante a:</label><input type="text" id="q_kanteA" name="q_kanteA" value="${not empty param.q_kanteA ? param.q_kanteA : (not empty quader.q_kanteA and quader.q_kanteA ne 'NaN' ? quader.q_kanteA : '0')}"></div>
                        <div class="form-row"><label for="q_kanteB">Kante b:</label><input type="text" id="q_kanteB" name="q_kanteB" value="${not empty param.q_kanteB ? param.q_kanteB : (not empty quader.q_kanteB and quader.q_kanteB ne 'NaN' ? quader.q_kanteB : '0')}"></div>
                        <div class="form-row"><label for="q_kanteC">Kante c (Höhe):</label><input type="text" id="q_kanteC" name="q_kanteC" value="${not empty param.q_kanteC ? param.q_kanteC : (not empty quader.q_kanteC and quader.q_kanteC ne 'NaN' ? quader.q_kanteC : '0')}"></div>
                        <div class="form-row"><label for="q_volumen">Volumen (V):</label><input type="text" id="q_volumen" name="q_volumen" value="${not empty param.q_volumen ? param.q_volumen : (not empty quader.q_volumen and quader.q_volumen ne 'NaN' ? quader.q_volumen : '0')}"></div>
                        <div class="form-row"><label for="q_grundflaeche">Grundfläche (G = a*b):</label><input type="text" id="q_grundflaeche" name="q_grundflaeche" value="${not empty param.q_grundflaeche ? param.q_grundflaeche : (not empty quader.q_grundflaeche and quader.q_grundflaeche ne 'NaN' ? quader.q_grundflaeche : '0')}"></div>
                        <div class="form-row"><label for="q_mantelflaeche">Mantelfläche (M, H:c):</label><input type="text" id="q_mantelflaeche" name="q_mantelflaeche" value="${not empty param.q_mantelflaeche ? param.q_mantelflaeche : (not empty quader.q_mantelflaeche and quader.q_mantelflaeche ne 'NaN' ? quader.q_mantelflaeche : '0')}"></div>
                        <div class="form-row"><label for="q_oberflaeche">Oberfläche (O):</label><input type="text" id="q_oberflaeche" name="q_oberflaeche" value="${not empty param.q_oberflaeche ? param.q_oberflaeche : (not empty quader.q_oberflaeche and quader.q_oberflaeche ne 'NaN' ? quader.q_oberflaeche : '0')}"></div>
                        <div class="form-row"><label for="q_raumdiagonale">Raumdiagonale (d):</label><input type="text" id="q_raumdiagonale" name="q_raumdiagonale" value="${not empty param.q_raumdiagonale ? param.q_raumdiagonale : (not empty quader.q_raumdiagonale and quader.q_raumdiagonale ne 'NaN' ? quader.q_raumdiagonale : '0')}"></div>
                    </div>
                    <!-- Würfel Inputs -->
                    <div class="input-section Wuerfel">
                        <h3>Würfel</h3>
                        <div class="form-row"><label for="w_kante">Kante (a):</label><input type="text" id="w_kante" name="w_kante" value="${not empty param.w_kante ? param.w_kante : (not empty wuerfel.w_kante and wuerfel.w_kante ne 'NaN' ? wuerfel.w_kante : '0')}"></div>
                        <div class="form-row"><label for="w_volumen">Volumen (V):</label><input type="text" id="w_volumen" name="w_volumen" value="${not empty param.w_volumen ? param.w_volumen : (not empty wuerfel.w_volumen and wuerfel.w_volumen ne 'NaN' ? wuerfel.w_volumen : '0')}"></div>
                        <div class="form-row"><label for="w_grundflaeche">Grundfläche (G):</label><input type="text" id="w_grundflaeche" name="w_grundflaeche" value="${not empty param.w_grundflaeche ? param.w_grundflaeche : (not empty wuerfel.w_grundflaeche and wuerfel.w_grundflaeche ne 'NaN' ? wuerfel.w_grundflaeche : '0')}"></div>
                        <div class="form-row"><label for="w_mantelflaeche">Mantelfläche (M):</label><input type="text" id="w_mantelflaeche" name="w_mantelflaeche" value="${not empty param.w_mantelflaeche ? param.w_mantelflaeche : (not empty wuerfel.w_mantelflaeche and wuerfel.w_mantelflaeche ne 'NaN' ? wuerfel.w_mantelflaeche : '0')}"></div>
                        <div class="form-row"><label for="w_oberflaeche">Oberfläche (O):</label><input type="text" id="w_oberflaeche" name="w_oberflaeche" value="${not empty param.w_oberflaeche ? param.w_oberflaeche : (not empty wuerfel.w_oberflaeche and wuerfel.w_oberflaeche ne 'NaN' ? wuerfel.w_oberflaeche : '0')}"></div>
                        <div class="form-row"><label for="w_raumdiagonale">Raumdiagonale (d):</label><input type="text" id="w_raumdiagonale" name="w_raumdiagonale" value="${not empty param.w_raumdiagonale ? param.w_raumdiagonale : (not empty wuerfel.w_raumdiagonale and wuerfel.w_raumdiagonale ne 'NaN' ? wuerfel.w_raumdiagonale : '0')}"></div>
                        <div class="form-row"><label for="w_umfang_seitenflaeche">Umfang Seitenfläche:</label><input type="text" id="w_umfang_seitenflaeche" name="w_umfang_seitenflaeche" value="${not empty param.w_umfang_seitenflaeche ? param.w_umfang_seitenflaeche : (not empty wuerfel.w_umfang_seitenflaeche and wuerfel.w_umfang_seitenflaeche ne 'NaN' ? wuerfel.w_umfang_seitenflaeche : '0')}"></div>
                        <div class="form-row"><label for="w_summe_aller_kanten">Summe aller Kanten:</label><input type="text" id="w_summe_aller_kanten" name="w_summe_aller_kanten" value="${not empty param.w_summe_aller_kanten ? param.w_summe_aller_kanten : (not empty wuerfel.w_summe_aller_kanten and wuerfel.w_summe_aller_kanten ne 'NaN' ? wuerfel.w_summe_aller_kanten : '0')}"></div>
                    </div>
                     <!-- Prisma Inputs -->
                    <div class="input-section Prisma">
                        <h3>Gerades Prisma</h3>
                        <div class="form-row"><label for="pr_grundflaeche">Grundfläche (G):</label><input type="text" id="pr_grundflaeche" name="pr_grundflaeche" value="${not empty param.pr_grundflaeche ? param.pr_grundflaeche : (not empty prisma.pr_grundflaeche and prisma.pr_grundflaeche ne 'NaN' ? prisma.pr_grundflaeche : '0')}"></div>
                        <div class="form-row"><label for="pr_umfang">Umfang Grundfläche (Ug):</label><input type="text" id="pr_umfang" name="pr_umfang" value="${not empty param.pr_umfang ? param.pr_umfang : (not empty prisma.pr_umfang and prisma.pr_umfang ne 'NaN' ? prisma.pr_umfang : '0')}"></div>
                        <div class="form-row"><label for="pr_hoehe">Höhe (h):</label><input type="text" id="pr_hoehe" name="pr_hoehe" value="${not empty param.pr_hoehe ? param.pr_hoehe : (not empty prisma.pr_hoehe and prisma.pr_hoehe ne 'NaN' ? prisma.pr_hoehe : '0')}"></div>
                        <div class="form-row"><label for="pr_volumen">Volumen (V):</label><input type="text" id="pr_volumen" name="pr_volumen" value="${not empty param.pr_volumen ? param.pr_volumen : (not empty prisma.pr_volumen and prisma.pr_volumen ne 'NaN' ? prisma.pr_volumen : '0')}"></div>
                        <div class="form-row"><label for="pr_mantelflaeche">Mantelfläche (M):</label><input type="text" id="pr_mantelflaeche" name="pr_mantelflaeche" value="${not empty param.pr_mantelflaeche ? param.pr_mantelflaeche : (not empty prisma.pr_mantelflaeche and prisma.pr_mantelflaeche ne 'NaN' ? prisma.pr_mantelflaeche : '0')}"></div>
                        <div class="form-row"><label for="pr_oberflaeche">Oberfläche (O):</label><input type="text" id="pr_oberflaeche" name="pr_oberflaeche" value="${not empty param.pr_oberflaeche ? param.pr_oberflaeche : (not empty prisma.pr_oberflaeche and prisma.pr_oberflaeche ne 'NaN' ? prisma.pr_oberflaeche : '0')}"></div>
                        <p class="info-text">Hinweis: Grundfläche (G) und Umfang (Ug) müssen ggf. aus den spezifischen Maßen der Grundform (z.B. Dreieck, Sechseck) berechnet werden.</p>
                    </div>
                </div> <%-- End .input-section-row --%>
            </div> <%-- End Tab 2: Körper --%>

            <%-- Tab 3: Anzeigeoptionen --%>
            <div id="input-tab-common" class="tab-content">
                 <div class="input-section">
                    <h3>Anzeigeoptionen</h3>
                    <div class="form-row">
                        <label for="decimalPlaces">Dezimalstellen:</label>
                        <input type="number" id="decimalPlaces" name="decimalPlaces" value="${not empty param.decimalPlaces ? param.decimalPlaces : '2'}" min="0" max="10" required>
                    </div>
                </div>
            </div> <%-- End Tab 3: Anzeigeoptionen --%>

        </div> <%-- End Input Tab Container --%>

		<div class="controls-submit">
            <label for="decimalPlacesSubmit" style="display:none;">Dezimalstellen:</label> <!-- Hidden label for accessibility if needed -->
            <input type="number" id="decimalPlacesSubmit" name="decimalPlaces" value="${not empty param.decimalPlaces ? param.decimalPlaces : '2'}" min="0" max="10" required>
            <label for="decimalPlacesSubmit" class="visually-hidden">Dezimalstellen</label> <!-- Better accessibility -->
			<button type="submit" class="button submit-button">Berechne</button>
		</div>
	</form>

	<!-- ==================== Results Section ==================== -->
	<%-- Show results if *any* result bean is available in request scope --%>
    <c:if test="${not empty dreieck or not empty pyramideErgebnis or not empty rechteckErgebnis or not empty trapezErgebnis or not empty parallelogrammErgebnis or not empty pentagonErgebnis or not empty hexagonErgebnis or not empty octagonErgebnis or not empty quaderErgebnis or not empty wuerfelErgebnis or not empty prismaErgebnis}">
		<details class="results-details" open>
			<summary>
				Ergebnisse <span id="copy-feedback" style="margin-left: 10px; font-weight: normal; color: green; display: none;"></span>
			</summary>

			<%-- Tab Navigation für Ergebnisse --%>
			<div class="tab-nav">
				<button type="button" class="tab-button active" onclick="showResultTab('dreieck', this)">Dreieck</button>
				<button type="button" class="tab-button" onclick="showResultTab('pyramide', this)">Pyramide</button>
                <button type="button" class="tab-button" onclick="showResultTab('rechteck', this)">Rechteck</button>
                <button type="button" class="tab-button" onclick="showResultTab('trapez', this)">Trapez</button>
                <button type="button" class="tab-button" onclick="showResultTab('parallelogramm', this)">Parallelogramm</button>
                <button type="button" class="tab-button" onclick="showResultTab('pentagon', this)">Fünfeck</button>
                <button type="button" class="tab-button" onclick="showResultTab('hexagon', this)">Sechseck</button>
                <button type="button" class="tab-button" onclick="showResultTab('octagon', this)">Achteck</button>
                <button type="button" class="tab-button" onclick="showResultTab('quader', this)">Quader</button>
                <button type="button" class="tab-button" onclick="showResultTab('wuerfel', this)">Würfel</button>
                <button type="button" class="tab-button" onclick="showResultTab('prisma', this)">Prisma</button>
			</div>

			<%-- Tab Content Container für Ergebnisse --%>
			<div class="tab-content-container results-container Results">

				<%-- Tab 1: Dreieck Ergebnisse --%>
				<div id="result-tab-dreieck" class="tab-content active">
                    <div class="result-section DreieckResults">
						<h3>Dreieck</h3>
						<c:choose><c:when test="${not empty dreieck.a and dreieck.a ne 'NaN' and dreieck.a ne 'Ungültig'}">
                            <p id="result-dreieck-a"><span class="result-label">Seite a:</span> <span class="result-value">${dreieck.a}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-a"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-dreieck-b"><span class="result-label">Seite b:</span> <span class="result-value">${dreieck.b}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-b"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-dreieck-c"><span class="result-label">Seite c:</span> <span class="result-value">${dreieck.c}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-c"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-dreieck-hoeheA"><span class="result-label">Höhe a (ha):</span> <span class="result-value">${dreieck.hoeheA}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-hoeheA"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-dreieck-hoeheB"><span class="result-label">Höhe b (hb):</span> <span class="result-value">${dreieck.hoeheB}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-hoeheB"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-dreieck-hoeheC"><span class="result-label">Höhe c (hc):</span> <span class="result-value">${dreieck.hoeheC}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-hoeheC"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-dreieck-umfang"><span class="result-label">Umfang (U):</span> <span class="result-value">${dreieck.umfangDreieck}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-umfang"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-dreieck-flaeche"><span class="result-label">Flächeninhalt (A):</span> <span class="result-value">${dreieck.flaecheninhaltDreieck}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-flaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-dreieck-alpha"><span class="result-label">Winkel Alpha (α):</span> <span class="result-value">${dreieck.alpha}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-alpha"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-dreieck-beta"><span class="result-label">Winkel Beta (β):</span> <span class="result-value">${dreieck.beta}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-beta"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-dreieck-gamma"><span class="result-label">Winkel Gamma (γ):</span> <span class="result-value">${dreieck.gamma}</span><button type="button" class="copy-button" data-copy-target-id="result-dreieck-gamma"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
						</c:when><c:otherwise><p>Keine Dreiecksdaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
					</div>
				</div>

				<%-- Tab 2: Pyramide Ergebnisse --%>
				<div id="result-tab-pyramide" class="tab-content">
					<div class="result-section PyramideResults">
						<h3>Quadratische Pyramide</h3>
						<c:choose><c:when test="${not empty pyramideErgebnis.p_seiteA and pyramideErgebnis.p_seiteA ne 'NaN'}">
							<p id="result-pyramide-seiteA"><span class="result-label">Grundseite (a):</span> <span class="result-value">${pyramideErgebnis.p_seiteA}</span><button type="button" class="copy-button" data-copy-target-id="result-pyramide-seiteA"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
							<p id="result-pyramide-hoehe"><span class="result-label">Körperhöhe (h):</span> <span class="result-value">${pyramideErgebnis.p_hoehe}</span><button type="button" class="copy-button" data-copy-target-id="result-pyramide-hoehe"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
							<p id="result-pyramide-hoeheSeite"><span class="result-label">Seitenhöhe (hs):</span> <span class="result-value">${pyramideErgebnis.p_hoeheSeite}</span><button type="button" class="copy-button" data-copy-target-id="result-pyramide-hoeheSeite"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
							<p id="result-pyramide-seitenkante"><span class="result-label">Seitenkante (s):</span> <span class="result-value">${pyramideErgebnis.p_seitenkante}</span><button type="button" class="copy-button" data-copy-target-id="result-pyramide-seitenkante"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
							<p id="result-pyramide-grundflaeche"><span class="result-label">Grundfläche (G):</span> <span class="result-value">${pyramideErgebnis.p_grundflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-pyramide-grundflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
							<p id="result-pyramide-mantelflaeche"><span class="result-label">Mantelfläche (M):</span> <span class="result-value">${pyramideErgebnis.p_mantelflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-pyramide-mantelflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
							<p id="result-pyramide-oberflaeche"><span class="result-label">Oberfläche (O):</span> <span class="result-value">${pyramideErgebnis.p_oberflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-pyramide-oberflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
							<p id="result-pyramide-volumen"><span class="result-label">Volumen (V):</span> <span class="result-value">${pyramideErgebnis.p_volumen}</span><button type="button" class="copy-button" data-copy-target-id="result-pyramide-volumen"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
							<p id="result-pyramide-grundflaecheDiagonale"><span class="result-label">Grundfl.-Diagonale (d):</span> <span class="result-value">${pyramideErgebnis.p_grundflaecheDiagonale}</span><button type="button" class="copy-button" data-copy-target-id="result-pyramide-grundflaecheDiagonale"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
						</c:when><c:otherwise><p>Keine Pyramidendaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
					</div>
				</div>

                <%-- Tab 3: Rechteck Ergebnisse --%>
                <div id="result-tab-rechteck" class="tab-content">
                    <div class="result-section RechteckResults">
                        <h3>Rechteck</h3>
                        <c:choose><c:when test="${not empty rechteckErgebnis.r_seiteA and rechteckErgebnis.r_seiteA ne 'NaN'}">
                            <p id="result-rechteck-seiteA"><span class="result-label">Seite a:</span><span class="result-value">${rechteckErgebnis.r_seiteA}</span><button type="button" class="copy-button" data-copy-target-id="result-rechteck-seiteA"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-rechteck-seiteB"><span class="result-label">Seite b:</span><span class="result-value">${rechteckErgebnis.r_seiteB}</span><button type="button" class="copy-button" data-copy-target-id="result-rechteck-seiteB"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-rechteck-flaecheninhalt"><span class="result-label">Flächeninhalt (A):</span><span class="result-value">${rechteckErgebnis.r_flaecheninhalt}</span><button type="button" class="copy-button" data-copy-target-id="result-rechteck-flaecheninhalt"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-rechteck-umfang"><span class="result-label">Umfang (U):</span><span class="result-value">${rechteckErgebnis.r_umfang}</span><button type="button" class="copy-button" data-copy-target-id="result-rechteck-umfang"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-rechteck-diagonale"><span class="result-label">Diagonale (d):</span><span class="result-value">${rechteckErgebnis.r_diagonale}</span><button type="button" class="copy-button" data-copy-target-id="result-rechteck-diagonale"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                        </c:when><c:otherwise><p>Keine Rechteckdaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
                    </div>
                </div>

                <%-- Tab 4: Trapez Ergebnisse --%>
                <div id="result-tab-trapez" class="tab-content">
                     <div class="result-section TrapezResults">
                        <h3>Trapez</h3>
                         <c:choose><c:when test="${not empty trapezErgebnis.t_seiteA and trapezErgebnis.t_seiteA ne 'NaN'}">
                            <p id="result-trapez-seiteA"><span class="result-label">Seite a:</span><span class="result-value">${trapezErgebnis.t_seiteA}</span><button type="button" class="copy-button" data-copy-target-id="result-trapez-seiteA"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-trapez-seiteB"><span class="result-label">Seite b:</span><span class="result-value">${trapezErgebnis.t_seiteB}</span><button type="button" class="copy-button" data-copy-target-id="result-trapez-seiteB"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-trapez-seiteC"><span class="result-label">Seite c:</span><span class="result-value">${trapezErgebnis.t_seiteC}</span><button type="button" class="copy-button" data-copy-target-id="result-trapez-seiteC"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-trapez-seiteD"><span class="result-label">Seite d:</span><span class="result-value">${trapezErgebnis.t_seiteD}</span><button type="button" class="copy-button" data-copy-target-id="result-trapez-seiteD"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-trapez-hoehe"><span class="result-label">Höhe (h):</span><span class="result-value">${trapezErgebnis.t_hoehe}</span><button type="button" class="copy-button" data-copy-target-id="result-trapez-hoehe"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-trapez-flaecheninhalt"><span class="result-label">Flächeninhalt (A):</span><span class="result-value">${trapezErgebnis.t_flaecheninhalt}</span><button type="button" class="copy-button" data-copy-target-id="result-trapez-flaecheninhalt"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-trapez-umfang"><span class="result-label">Umfang (U):</span><span class="result-value">${trapezErgebnis.t_umfang}</span><button type="button" class="copy-button" data-copy-target-id="result-trapez-umfang"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                        </c:when><c:otherwise><p>Keine Trapezdaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
                    </div>
                </div>

                 <%-- Tab 5: Parallelogramm Ergebnisse --%>
                <div id="result-tab-parallelogramm" class="tab-content">
                     <div class="result-section ParallelogrammResults">
                        <h3>Parallelogramm</h3>
                         <c:choose><c:when test="${not empty parallelogrammErgebnis.pa_seiteA and parallelogrammErgebnis.pa_seiteA ne 'NaN'}">
                            <p id="result-parallelogramm-seiteA"><span class="result-label">Seite a:</span><span class="result-value">${parallelogrammErgebnis.pa_seiteA}</span><button type="button" class="copy-button" data-copy-target-id="result-parallelogramm-seiteA"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-parallelogramm-seiteB"><span class="result-label">Seite b:</span><span class="result-value">${parallelogrammErgebnis.pa_seiteB}</span><button type="button" class="copy-button" data-copy-target-id="result-parallelogramm-seiteB"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-parallelogramm-hoeheA"><span class="result-label">Höhe auf a (ha):</span><span class="result-value">${parallelogrammErgebnis.pa_hoeheA}</span><button type="button" class="copy-button" data-copy-target-id="result-parallelogramm-hoeheA"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-parallelogramm-flaecheninhalt"><span class="result-label">Flächeninhalt (A):</span><span class="result-value">${parallelogrammErgebnis.pa_flaecheninhalt}</span><button type="button" class="copy-button" data-copy-target-id="result-parallelogramm-flaecheninhalt"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-parallelogramm-umfang"><span class="result-label">Umfang (U):</span><span class="result-value">${parallelogrammErgebnis.pa_umfang}</span><button type="button" class="copy-button" data-copy-target-id="result-parallelogramm-umfang"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                        </c:when><c:otherwise><p>Keine Parallelogrammdaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
                    </div>
                </div>

                 <%-- Tab 6: Pentagon Ergebnisse --%>
                <div id="result-tab-pentagon" class="tab-content">
                     <div class="result-section PentagonResults">
                        <h3>Regelmäßiges Fünfeck</h3>
                         <c:choose><c:when test="${not empty pentagonErgebnis.pe_seite and pentagonErgebnis.pe_seite ne 'NaN'}">
                            <p id="result-pentagon-seite"><span class="result-label">Seite (a):</span><span class="result-value">${pentagonErgebnis.pe_seite}</span><button type="button" class="copy-button" data-copy-target-id="result-pentagon-seite"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-pentagon-umfang"><span class="result-label">Umfang (U):</span><span class="result-value">${pentagonErgebnis.pe_umfang}</span><button type="button" class="copy-button" data-copy-target-id="result-pentagon-umfang"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-pentagon-radiusInkreis"><span class="result-label">Inkreisradius (r):</span><span class="result-value">${pentagonErgebnis.pe_radiusInkreis}</span><button type="button" class="copy-button" data-copy-target-id="result-pentagon-radiusInkreis"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-pentagon-radiusUmkreis"><span class="result-label">Umkreisradius (R):</span><span class="result-value">${pentagonErgebnis.pe_radiusUmkreis}</span><button type="button" class="copy-button" data-copy-target-id="result-pentagon-radiusUmkreis"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-pentagon-flaecheninhalt"><span class="result-label">Flächeninhalt (A):</span><span class="result-value">${pentagonErgebnis.pe_flaecheninhalt}</span><button type="button" class="copy-button" data-copy-target-id="result-pentagon-flaecheninhalt"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                        </c:when><c:otherwise><p>Keine Fünfeckdaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
                    </div>
                </div>

                 <%-- Tab 7: Hexagon Ergebnisse --%>
                <div id="result-tab-hexagon" class="tab-content">
                     <div class="result-section HexagonResults">
                        <h3>Regelmäßiges Sechseck</h3>
                         <c:choose><c:when test="${not empty hexagonErgebnis.h_seite and hexagonErgebnis.h_seite ne 'NaN'}">
                            <p id="result-hexagon-seite"><span class="result-label">Seite (a):</span><span class="result-value">${hexagonErgebnis.h_seite}</span><button type="button" class="copy-button" data-copy-target-id="result-hexagon-seite"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-hexagon-umfang"><span class="result-label">Umfang (U):</span><span class="result-value">${hexagonErgebnis.h_umfang}</span><button type="button" class="copy-button" data-copy-target-id="result-hexagon-umfang"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-hexagon-radiusInkreis"><span class="result-label">Inkreisradius (r):</span><span class="result-value">${hexagonErgebnis.h_radiusInkreis}</span><button type="button" class="copy-button" data-copy-target-id="result-hexagon-radiusInkreis"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-hexagon-radiusUmkreis"><span class="result-label">Umkreisradius (R):</span><span class="result-value">${hexagonErgebnis.h_radiusUmkreis}</span><button type="button" class="copy-button" data-copy-target-id="result-hexagon-radiusUmkreis"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-hexagon-flaecheninhalt"><span class="result-label">Flächeninhalt (A):</span><span class="result-value">${hexagonErgebnis.h_flaecheninhalt}</span><button type="button" class="copy-button" data-copy-target-id="result-hexagon-flaecheninhalt"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                         </c:when><c:otherwise><p>Keine Sechseckdaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
                    </div>
                </div>

                <%-- Tab 8: Octagon Ergebnisse --%>
                <div id="result-tab-octagon" class="tab-content">
                     <div class="result-section OctagonResults">
                        <h3>Regelmäßiges Achteck</h3>
                        <c:choose><c:when test="${not empty octagonErgebnis.o_seite and octagonErgebnis.o_seite ne 'NaN'}">
                            <p id="result-octagon-seite"><span class="result-label">Seite (a):</span><span class="result-value">${octagonErgebnis.o_seite}</span><button type="button" class="copy-button" data-copy-target-id="result-octagon-seite"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-octagon-umfang"><span class="result-label">Umfang (U):</span><span class="result-value">${octagonErgebnis.o_umfang}</span><button type="button" class="copy-button" data-copy-target-id="result-octagon-umfang"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-octagon-radiusInkreis"><span class="result-label">Inkreisradius (r):</span><span class="result-value">${octagonErgebnis.o_radiusInkreis}</span><button type="button" class="copy-button" data-copy-target-id="result-octagon-radiusInkreis"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-octagon-radiusUmkreis"><span class="result-label">Umkreisradius (R):</span><span class="result-value">${octagonErgebnis.o_radiusUmkreis}</span><button type="button" class="copy-button" data-copy-target-id="result-octagon-radiusUmkreis"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-octagon-flaecheninhalt"><span class="result-label">Flächeninhalt (A):</span><span class="result-value">${octagonErgebnis.o_flaecheninhalt}</span><button type="button" class="copy-button" data-copy-target-id="result-octagon-flaecheninhalt"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                        </c:when><c:otherwise><p>Keine Achteckdaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
                    </div>
                </div>

                <%-- Tab 9: Quader Ergebnisse --%>
                <div id="result-tab-quader" class="tab-content">
                     <div class="result-section QuaderResults">
                        <h3>Quader</h3>
                         <c:choose><c:when test="${not empty quaderErgebnis.q_kanteA and quaderErgebnis.q_kanteA ne 'NaN'}">
                            <p id="result-quader-kanteA"><span class="result-label">Kante a:</span><span class="result-value">${quaderErgebnis.q_kanteA}</span><button type="button" class="copy-button" data-copy-target-id="result-quader-kanteA"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-quader-kanteB"><span class="result-label">Kante b:</span><span class="result-value">${quaderErgebnis.q_kanteB}</span><button type="button" class="copy-button" data-copy-target-id="result-quader-kanteB"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-quader-kanteC"><span class="result-label">Kante c (Höhe):</span><span class="result-value">${quaderErgebnis.q_kanteC}</span><button type="button" class="copy-button" data-copy-target-id="result-quader-kanteC"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-quader-volumen"><span class="result-label">Volumen (V):</span><span class="result-value">${quaderErgebnis.q_volumen}</span><button type="button" class="copy-button" data-copy-target-id="result-quader-volumen"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-quader-grundflaeche"><span class="result-label">Grundfläche (G):</span><span class="result-value">${quaderErgebnis.q_grundflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-quader-grundflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-quader-mantelflaeche"><span class="result-label">Mantelfläche (M):</span><span class="result-value">${quaderErgebnis.q_mantelflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-quader-mantelflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-quader-oberflaeche"><span class="result-label">Oberfläche (O):</span><span class="result-value">${quaderErgebnis.q_oberflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-quader-oberflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-quader-raumdiagonale"><span class="result-label">Raumdiagonale (d):</span><span class="result-value">${quaderErgebnis.q_raumdiagonale}</span><button type="button" class="copy-button" data-copy-target-id="result-quader-raumdiagonale"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                        </c:when><c:otherwise><p>Keine Quaderdaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
                    </div>
                </div>

                <%-- Tab 10: Würfel Ergebnisse --%>
                <div id="result-tab-wuerfel" class="tab-content">
                     <div class="result-section WuerfelResults">
                        <h3>Würfel</h3>
                         <c:choose><c:when test="${not empty wuerfelErgebnis.w_kante and wuerfelErgebnis.w_kante ne 'NaN'}">
                            <p id="result-wuerfel-kante"><span class="result-label">Kante (a):</span><span class="result-value">${wuerfelErgebnis.w_kante}</span><button type="button" class="copy-button" data-copy-target-id="result-wuerfel-kante"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-wuerfel-volumen"><span class="result-label">Volumen (V):</span><span class="result-value">${wuerfelErgebnis.w_volumen}</span><button type="button" class="copy-button" data-copy-target-id="result-wuerfel-volumen"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-wuerfel-grundflaeche"><span class="result-label">Grundfläche (G):</span><span class="result-value">${wuerfelErgebnis.w_grundflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-wuerfel-grundflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-wuerfel-mantelflaeche"><span class="result-label">Mantelfläche (M):</span><span class="result-value">${wuerfelErgebnis.w_mantelflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-wuerfel-mantelflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-wuerfel-oberflaeche"><span class="result-label">Oberfläche (O):</span><span class="result-value">${wuerfelErgebnis.w_oberflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-wuerfel-oberflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-wuerfel-raumdiagonale"><span class="result-label">Raumdiagonale (d):</span><span class="result-value">${wuerfelErgebnis.w_raumdiagonale}</span><button type="button" class="copy-button" data-copy-target-id="result-wuerfel-raumdiagonale"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-wuerfel-umfang_seitenflaeche"><span class="result-label">Umfang Seitenfläche:</span><span class="result-value">${wuerfelErgebnis.w_umfang_seitenflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-wuerfel-umfang_seitenflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-wuerfel-summe_aller_kanten"><span class="result-label">Summe aller Kanten:</span><span class="result-value">${wuerfelErgebnis.w_summe_aller_kanten}</span><button type="button" class="copy-button" data-copy-target-id="result-wuerfel-summe_aller_kanten"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                         </c:when><c:otherwise><p>Keine Würfeldaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
                    </div>
                </div>

                <%-- Tab 11: Prisma Ergebnisse --%>
                <div id="result-tab-prisma" class="tab-content">
                    <div class="result-section PrismaResults">
                        <h3>Gerades Prisma</h3>
                         <c:choose><c:when test="${not empty prismaErgebnis.pr_grundflaeche and prismaErgebnis.pr_grundflaeche ne 'NaN'}">
                            <p id="result-prisma-grundflaeche"><span class="result-label">Grundfläche (G):</span><span class="result-value">${prismaErgebnis.pr_grundflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-prisma-grundflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-prisma-umfang"><span class="result-label">Umfang Grundfläche (Ug):</span><span class="result-value">${prismaErgebnis.pr_umfang}</span><button type="button" class="copy-button" data-copy-target-id="result-prisma-umfang"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-prisma-hoehe"><span class="result-label">Höhe (h):</span><span class="result-value">${prismaErgebnis.pr_hoehe}</span><button type="button" class="copy-button" data-copy-target-id="result-prisma-hoehe"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-prisma-volumen"><span class="result-label">Volumen (V):</span><span class="result-value">${prismaErgebnis.pr_volumen}</span><button type="button" class="copy-button" data-copy-target-id="result-prisma-volumen"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-prisma-mantelflaeche"><span class="result-label">Mantelfläche (M):</span><span class="result-value">${prismaErgebnis.pr_mantelflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-prisma-mantelflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                            <p id="result-prisma-oberflaeche"><span class="result-label">Oberfläche (O):</span><span class="result-value">${prismaErgebnis.pr_oberflaeche}</span><button type="button" class="copy-button" data-copy-target-id="result-prisma-oberflaeche"><%@ include file="jsp_pages/copyIcon.jsp"%></button></p>
                        </c:when><c:otherwise><p>Keine Prismadaten berechnet oder Eingabe unzureichend.</p></c:otherwise></c:choose>
                    </div>
                </div>

			</div> <%-- Ende .tab-content-container --%>
		</details>
	</c:if> <%-- Ende Result Check --%>

	<script src="js/dreiecke.js"></script>

</body>
<footer>
	<%@ include file="jsp_pages/footer.jsp"%>
</footer>
</html>
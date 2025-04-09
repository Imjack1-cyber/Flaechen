<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="styles.css">
<title>Kreise</title>
</head>
<body>
    <h1>Eingabe</h1>
    
    <form action="KreisServlet" method="post" onsubmit="setDefaultValues()">
    	
    	
    	<div class="content">
    	<div class="Kreis">
    	<h3>Kreis</h3> 
	        <label for="radius1">Radius 1:</label>
	        <input type="text" step="0.01" id="radius1" name="radius1" value="0" required><br><br>
	    
	        <label for="radius2">Radius 2:</label>
	        <input type="text" step="0.01" id="radius2" name="radius2" value="0" required><br><br>
	        
	        <label for="durchmesser1">Durchmesser 1:</label>
	        <input type="text" step="0.01" id="durchmesser1" name="durchmesser1" value="0" required><br><br>
	        
	        <label for="durchmesser2">Durchmesser 2:</label>
	        <input type="text" step="0.01" id="durchmesser2" name="durchmesser2" value="0" required><br><br>
	        
	        <label for="umfang1">Umfang 1:</label>
	        <input type="text" step="0.01" id="umfang1" name="umfang1" value="0" required><br><br>
	        
	        <label for="umfang2">Umfang 2:</label>
	        <input type="text" step="0.01" id="umfang2" name="umfang2" value="0" required><br><br>
	        
	        <label for="flaecheninhalt1">Flächeninhalt 1:</label>
	        <input type="text" step="0.01" id="flaecheninhalt1" name="flaecheninhalt1" value="0" required><br><br>
	        
	        <label for="flaecheninhalt2">Flächeninhalt 2:</label>
	        <input type="text" step="0.01" id="flaecheninhalt2" name="flaecheninhalt2" value="0" required><br><br>
	        
	        <label for="flaecheninhaltGesamt">Flächeninhalt: </label>
	        <input type="text" step="0.01" id="flaecheninhaltGesamt" name="flaecheninhaltGesamt" value="0" required><br><br>
        </div>
       
	        <div class="Kreisteile">
	        <h3>Kreisteile</h3> 
	        <label for="kreisbogen1">Kreisbogen 1:</label>
	        <input type="text" step="0.01" id="kreisbogen1" name="kreisbogen1" value="0" required><br><br>
	        
	        <label for="kreisbogen2">Kreisbogen 2:</label>
	        <input type="text" step="0.01" id="kreisbogen2" name="kreisbogen2" value="0" required><br><br>
	        
	        <label for="kreisauschnitt1">Kreisausschnitt 1:</label>
	        <input type="text" step="0.01" id="kreisausschnitt1" name="kreisausschnitt1" value="0" required><br><br>
	        
	        <label for="kreisauschnitt2">Kreisausschnitt 2:</label>
	        <input type="text" step="0.01" id="kreisausschnitt2" name="kreisausschnitt2" value="0" required><br><br>
	        
	        <label for="kreisausschnittGesamt">Kreisausschnitt:</label>
	        <input type="text" step="0.01" id="kreisausschnittGesamt" name="kreisausschnittGesamt" value="0" required><br><br>
	        
	        <label for="alpha">Alpha:</label>
	        <input type="text" step="1" id="alpha" name="alpha" value="0" required><br><br>
        </div>
        
        <div class="Zylinder">
        <h3>Zylinder</h3>
        <label for="hoehe">Höhe:</label>
        <input type="text" step="0.01" id="hoehe" name="hoehe" value="0" required><br><br>
        
        <label for="grundflaecheZylinder1">Grundfläche Zylinder 1:</label>
        <input type="text" step="0.01" id="grundflaecheZylinder1" name="grundflaecheZylinder1" value="0" required><br><br>
        
        <label for="grundflaecheZylinder2">Grundfläche Zylinder 2:</label>
        <input type="text" step="0.01" id="grundflaecheZylinder2" name="grundflaecheZylinder2" value="0" required><br><br>
        
        <label for="grundflaecheZylinderGesamt">Grundfläche Zylinder:</label>
        <input type="text" step="0.01" id="grundflaecheZylinderGesamt" name="grundflaecheZylinderGesamt" value="0" required><br><br>
        
        <label for="mantelflaecheZylinder1">Mantelfläche Zylinder 1:</label>
        <input type="text" step="0.01" id="mantelflaecheZylinder1" name="mantelflaecheZylinder1" value="0" required><br><br>
        
        <label for="mantelflaecheZylinder2">Mantelfläche Zylinder 2:</label>
        <input type="text" step="0.01" id="mantelflaecheZylinder2" name="mantelflaecheZylinder2" value="0" required><br><br>
        
        <label for="mantelflaecheZylinderGesamt">Mantelfläche Zylinder:</label>
        <input type="text" step="0.01" id="mantelflaecheZylinderGesamt" name="mantelflaecheZylinderGesamt" value="0" required><br><br>
        
        <label for="oberflaecheZylinder1">Oberfläche Zylinder 1:</label>
        <input type="text" step="0.01" id="oberflaecheZylinder1" name="oberflaecheZylinder1" value="0" required><br><br>
        
        <label for="oberflaecheZylinder2">Oberfläche Zylinder 2:</label>
        <input type="text" step="0.01" id="oberflaecheZylinder2" name="oberflaecheZylinder2" value="0" required><br><br>
        
        <label for="oberflaecheZylinderGesamt">Oberfläche Zylinder:</label>
        <input type="text" step="0.01" id="oberflaecheZylinderGesamt" name="oberflaecheZylinderGesamt" value="0" required><br><br>
        
        <label for="volumenZylinder1">Volumen Zylinder 1:</label>
        <input type="text" step="0.01" id="volumenZylinder1" name="volumenZylinder1" value="0" required><br><br>
        
        <label for="volumenZylinder2">Volumen Zylinder 2:</label>
        <input type="text" step="0.01" id="volumenZylinder2" name="volumenZylinder2" value="0" required><br><br>
        
        <label for="volumenZylinderGesamt">Volumen Zylinder:</label>
        <input type="text" step="0.01" id="volumenZylinderGesamt" name="volumenZylinderGesamt" value="0" required><br><br>
        </div>
         
        <div class="Kegel">
        <h3>Kegel</h3>
        <label for="seitenhoehe1">Seitenhöhe 1:</label>
        <input type="text" step="0.01" id="seitenhoehe" name="seitenhoehe1" value="0" required><br><br>
        
        <label for="seitenhoehe2">Seitenhöhe 2:</label>
        <input type="text" step="0.01" id="seitenhoehe" name="seitenhoehe2" value="0" required><br><br>
        
        <label for="grundflaecheKegel1">Grundfläche Kegel 1:</label>
        <input type="text" step="0.01" id="grundflaecheKegel1" name="grundflaecheKegel1" value="0" required><br><br>
        
        <label for="grundflaecheKegel2">Grundfläche Kegel 2:</label>
        <input type="text" step="0.01" id="grundflaecheKegel2" name="grundflaecheKegel2" value="0" required><br><br>
        
        <label for="grundflaecheKegelGesamt">Grundfläche Kegel:</label>
        <input type="text" step="0.01" id="grundflaecheKegelGesamt" name="grundflaecheKegelGesamt" value="0" required><br><br>
        
        <label for="mantelflaecheKegel1">Mantelfläche Kegel 1:</label>
        <input type="text" step="0.01" id="mantelflaecheKegel1" name="mantelflaecheKegel1" value="0" required><br><br>
        
        <label for="mantelflaecheKegel2">Mantelfläche Kegel 2:</label>
        <input type="text" step="0.01" id="mantelflaecheKegel2" name="mantelflaecheKegel2" value="0" required><br><br>
        
        <label for="mantelflaecheKegelGesamt">Mantelfläche Kegel:</label>
        <input type="text" step="0.01" id="mantelflaecheKegelGesamt" name="mantelflaecheKegelGesamt" value="0" required><br><br>
        
        <label for="oberflaecheKegel1">Oberfläche Kegel 1:</label>
        <input type="text" step="0.01" id="oberflaecheKegel1" name="oberflaecheKegel1" value="0" required><br><br>
        
        <label for="oberflaecheKegel2">Oberfläche Kegel 2:</label>
        <input type="text" step="0.01" id="oberflaecheKegel2" name="oberflaecheKegel2" value="0" required><br><br>
        
        <label for="oberflaecheKegelGesamt">Oberfläche Kegel:</label>
        <input type="text" step="0.01" id="oberflaecheKegelGesamt" name="oberflaecheKegelGesamt" value="0" required><br><br>
        
        <label for="volumenKegel1">Volumen Kegel 1:</label>
        <input type="text" step="0.01" id="volumenKegel1" name="volumenKegel1" value="0" required><br><br>
        
        <label for="volumenKegel2">Volumen Kegel 2:</label>
        <input type="text" step="0.01" id="volumenKegel2" name="volumenKegel2" value="0" required><br><br>
        
        <label for="volumenKegelGesamt">Volumen Kegel:</label>
        <input type="text" step="0.01" id="volumenKegelGesamt" name="volumenKegelGesamt" value="0" required><br><br>
        </div>
        
        <div class="Kugel">
        <h3>Kugel</h3> 
        <label for="oberflaecheKugel1">Oberfläche Kugel 1:</label>
        <input type="text" step="0.01" id="oberflaecheKugel1" name="oberflaecheKugel1" value="0" required><br><br>
        
        <label for="oberflaecheKugel2">Oberfläche Kugel 2:</label>
        <input type="text" step="0.01" id="oberflaecheKugel2" name="oberflaecheKugel2" value="0" required><br><br>
        
        <label for="oberflaecheKugelGesamt">Oberfläche Kugel:</label>
        <input type="text" step="0.01" id="oberflaecheKugelGesamt" name="oberflaecheKugelGesamt" value="0" required><br><br>
        
        <label for="volumenKugel1">Volumen Kugel 1:</label>
        <input type="text" step="0.01" id="volumenKugel1" name="volumenKugel1" value="0" required><br><br>
        
        <label for="volumenKugel2">Volumen Kugel 2:</label>
        <input type="text" step="0.01" id="volumenKugel2" name="volumenKugel2" value="0" required><br><br>
        
        <label for="volumenKugelGesamt">Volumen Kugel:</label>
        <input type="text" step="0.01" id="volumenKugelGesamt" name="volumenKugelGesamt" value="0" required><br><br>
        </div>
    	</div>
    	<label for="decimalPlaces">Dezimalstellen:</label>
    	<input type="number" id="decimalPlaces" name="decimalPlaces" value="2" min="0" max="10" required>
        <input type="submit" name="action" value="Berechne" class="button" />
    </form>
    
    <h1>Ergebnisse</h1>
    <c:if test="${not empty kreis}">
    <div class="Results">
    	<div class="KreisResults">
	        <h3>Kreis</h3>
	        <p>Radius 1: ${kreis.radius1}</p>
	        <p>Radius 2: ${kreis.radius2}</p>
	        <p>Durchmesser 1: ${kreis.durchmesser1}</p>
	        <p>Durchmesser 2: ${kreis.durchmesser2}</p>
	        <p>Umfang 1: ${kreis.umfang1}</p>
	        <p>Umfang 2: ${kreis.umfang2}</p>
	        <p>Flächeninhalt 1: ${kreis.flaecheninhalt1}</p>
	        <p>Flächeninhalt 2: ${kreis.flaecheninhalt2}</p>
	        <p>Flächeninhalt Gesamt: ${kreis.flaecheninhaltGesamt}</p>
		</div>		
        <div class="KreisteileResults">
		    <h3>Kreisteile</h3>		    
	        <p>Kreisbogen 1: ${kreis.kreisbogen1}</p>
	        <p>Kreisbogen 2: ${kreis.kreisbogen2}</p>
	        <p>Kreisausschnitt 1: ${kreis.kreisausschnitt1}</p>
	        <p>Kreisausschnitt 2: ${kreis.kreisausschnitt2}</p>
	        <p>Kreisausschnitt Gesamt: ${kreis.kreisausschnittGesamt}</p>     
	        <p>Alpha: ${kreis.alpha}</p>
        </div>       
        <div class="ZylinderResults">
	        <h3>Zylinder</h3>        
	        <p>Höhe: ${kreis.hoehe}</p>
	        <p>Grundfläche Zylinder 1: ${kreis.grundflaecheZylinder1}</p>
	        <p>Grundfläche Zylinder 2: ${kreis.grundflaecheZylinder2}</p>
	        <p>Grundfläche Zylinder Gesamt: ${kreis.grundflaecheZylinderGesamt}</p>
	        <p>Mantelfläche Zylinder 1: ${kreis.mantelflaecheZylinder1}</p>
	        <p>Mantelfläche Zylinder 2: ${kreis.mantelflaecheZylinder2}</p>
	        <p>Mantelfläche Zylinder Gesamt: ${kreis.mantelflaecheZylinderGesamt}</p>
	        <p>Oberfläche Zylinder 1: ${kreis.oberflaecheZylinder1}</p>
	        <p>Oberfläche Zylinder 2: ${kreis.oberflaecheZylinder2}</p>
	        <p>Oberfläche Zylinder Gesamt: ${kreis.oberflaecheZylinderGesamt}</p>
	        <p>Volumen Zylinder 1: ${kreis.volumenZylinder1}</p>
	        <p>Volumen Zylinder 2: ${kreis.volumenZylinder2}</p>
	        <p>Volumen Zylinder Gesamt: ${kreis.volumenZylinderGesamt}</p>
        </div>    
        <div class="KegelResults">
	        <h3>Kegel</h3>     
	        <p>Seitenhöhe 1: ${kreis.seitenhoehe1}</p>
	        <p>Seitenhöhe 2: ${kreis.seitenhoehe2}</p>
	        <p>Grundfläche Kegel 1: ${kreis.grundflaecheKegel1}</p>
	        <p>Grundfläche Kegel 2: ${kreis.grundflaecheKegel2}</p>
	        <p>Grundfläche Kegel Gesamt: ${kreis.grundflaecheKegelGesamt}</p>
	        <p>Mantelfläche Kegel 1: ${kreis.mantelflaecheKegel1}</p>
	        <p>Mantelfläche Kegel 2: ${kreis.mantelflaecheKegel2}</p>
	        <p>Mantelfläche Kegel Gesamt: ${kreis.mantelflaecheKegelGesamt}</p>
	        <p>Oberfläche Kegel 1: ${kreis.oberflaecheKegel1}</p>
	        <p>Oberfläche Kegel 2: ${kreis.oberflaecheKegel2}</p>
	        <p>Oberfläche Kegel Gesamt: ${kreis.oberflaecheKegelGesamt}</p>
	        <p>Volumen Kegel 1: ${kreis.volumenKegel1}</p>
	        <p>Volumen Kegel 2: ${kreis.volumenKegel2}</p>
	        <p>Volumen Kegel Gesamt: ${kreis.volumenKegelGesamt}</p>
        </div>
        <div class="KugelResults">
	       	<h3>Kugel</h3>
	        <p>Oberfläche Kugel 1: ${kreis.oberflaecheKugel1}</p>
	        <p>Oberfläche Kugel 2: ${kreis.oberflaecheKugel2}</p>
	        <p>Oberfläche Kugel Gesamt: ${kreis.oberflaecheKugelGesamt}</p>
	        <p>Volumen Kugel 1: ${kreis.volumenKugel1}</p>
	        <p>Volumen Kugel 2: ${kreis.volumenKugel2}</p>
	        <p>Volumen Kugel Gesamt: ${kreis.volumenKugelGesamt}</p>
        </div>
    </div>
    </c:if>    
</body>
</html>

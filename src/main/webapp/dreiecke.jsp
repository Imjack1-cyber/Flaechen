<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/dreiecke.css">
<title>Dreieck Rechner</title>
</head>
<header>
	<nav>
		<a href="kreise.jsp" class="nav-link">Kreise</a> <a
			href="dreiecke.jsp" class="nav-link active">Dreiecke</a>
	</nav>
</header>
<body>
	<h1>Eingabe</h1>

	<form action="DreieckServlet" method="post">

		<div class="content">
			<div class="Dreieck">
				<h2>Dreieck</h2>
				<label for="a">Seite a:</label> <input type="text" step="0.01"
					id="a" name="a" value="0" required><br>
				<br> <label for="b">Seite b:</label> <input type="text"
					step="0.01" id="b" name="b" value="0" required><br>
				<br> <label for="c">Seite c:</label> <input type="text"
					step="0.01" id="c" name="c" value="0" required><br>
				<br> <label for="hoeheA">Höhe a:</label> <input type="text"
					step="0.01" id="hoeheA" name="hoeheA" value="0" required><br>
				<br> <label for="hoeheB">Höhe b:</label> <input type="text"
					step="0.01" id="hoeheB" name="hoeheB" value="0" required><br>
				<br> <label for="hoeheC">Höhe c:</label> <input type="text"
					step="0.01" id="hoeheC" name="hoeheC" value="0" required><br>
				<br> <label for="umfangDreieck">Umfang:</label> <input
					type="text" step="0.01" id="umfangDreieck" name="umfangDreieck"
					value="0" required><br>
				<br> <label for="flaecheninhaltDreieck">Flächeninhalt:</label>
				<input type="text" step="0.01" id="flaecheninhaltDreieck"
					name="flaecheninhaltDreieck" value="0" required><br>
				<br> <label for="alpha">Winkel alpha:</label> <input
					type="text" step="0.01" id="alpha" name="alpha" value="0" required><br>
				<br> <label for="beta">Winkel beta:</label> <input type="text"
					step="0.01" id="beta" name="beta" value="0" required><br>
				<br> <label for="gamma">Winkel gamma:</label> <input
					type="text" step="0.01" id="gamma" name="gamma" value="0" required><br>
				<br>
			</div>
		</div>

		<label for="decimalPlaces">Dezimalstellen:</label> <input
			type="number" id="decimalPlaces" name="decimalPlaces" value="2"
			min="0" max="10" required>
		<button type="submit" name="action" value="berechneDreiecke"
			class="button">Calculate</button>
	</form>

	<h1>Ergebnisse</h1>
	<div class="Results">
		<div class="DreieckResults">
			<h2>Dreieck Ergebnisse</h2>
			<p>Seite a: ${dreieck.a}</p>
			<p>Seite b: ${dreieck.b}</p>
			<p>Seite c: ${dreieck.c}</p>
			<p>Höhe a: ${dreieck.hoeheA}</p>
			<p>Höhe b: ${dreieck.hoeheB}</p>
			<p>Höhe c: ${dreieck.hoeheC}</p>
			<p>Umfang: ${dreieck.umfangDreieck}</p>
			<p>Flächeninhalt: ${dreieck.flaecheninhaltDreieck}</p>
			<p>Winkel alpha: ${dreieck.alpha}</p>
			<p>Winkel beta: ${dreieck.beta}</p>
			<p>Winkel gamma: ${dreieck.gamma}</p>
		</div>
	</div>
</body>
<footer>
	<a href="http://localhost:8080/HelloWeb/" class="button"
		target="_blank">Hello Web</a> <a
		href="http://localhost:8080/EmployeeWebApp/" class="button"
		target="_blank">EmployeeWebApp</a> <a
		href="http://localhost:8080/Workhours/" class="button" target="_blank">Workhours</a>
</footer>
</html>
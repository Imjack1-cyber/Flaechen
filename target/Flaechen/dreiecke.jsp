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
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
						</button>
					</p>
					<p id="result-seite-b">
						<span class="result-label">Seite b:</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.b}</span>
						<button class="copy-button" data-copy-target-id="result-seite-b"
							title="Seite b kopieren">
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
						</button>
					</p>
					<p id="result-seite-c">
						<span class="result-label">Seite c:</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.c}</span>
						<button class="copy-button" data-copy-target-id="result-seite-c"
							title="Seite c kopieren">
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
						</button>
					</p>
					<p id="result-hoehe-a">
						<span class="result-label">Höhe a (ha):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.hoeheA}</span>
						<button class="copy-button" data-copy-target-id="result-hoehe-a"
							title="Höhe a kopieren">
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
						</button>
					</p>
					<p id="result-hoehe-b">
						<span class="result-label">Höhe b (hb):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.hoeheB}</span>
						<button class="copy-button" data-copy-target-id="result-hoehe-b"
							title="Höhe b kopieren">
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
						</button>
					</p>
					<p id="result-hoehe-c">
						<span class="result-label">Höhe c (hc):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.hoeheC}</span>
						<button class="copy-button" data-copy-target-id="result-hoehe-c"
							title="Höhe c kopieren">
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
						</button>
					</p>
					<p id="result-umfang-dreieck">
						<span class="result-label">Umfang (U):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.umfangDreieck}</span>
						<button class="copy-button"
							data-copy-target-id="result-umfang-dreieck"
							title="Umfang kopieren">
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
						</button>
					</p>
					<p id="result-flaecheninhalt-dreieck">
						<span class="result-label">Flächeninhalt (A):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.flaecheninhaltDreieck}</span>
						<button class="copy-button"
							data-copy-target-id="result-flaecheninhalt-dreieck"
							title="Flächeninhalt kopieren">
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
						</button>
					</p>
					<p id="result-winkel-alpha">
						<span class="result-label">Winkel Alpha (α):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.alpha}</span>
						<button class="copy-button"
							data-copy-target-id="result-winkel-alpha"
							title="Winkel Alpha kopieren">
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
						</button>
					</p>
					<p id="result-winkel-beta">
						<span class="result-label">Winkel Beta (β):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.beta}</span>
						<button class="copy-button"
							data-copy-target-id="result-winkel-beta"
							title="Winkel Beta kopieren">
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
						</button>
					</p>
					<p id="result-winkel-gamma">
						<span class="result-label">Winkel Gamma (γ):</span><span
							class="result-icon-container"></span><span class="result-value">${dreieck.gamma}</span>
						<button class="copy-button"
							data-copy-target-id="result-winkel-gamma"
							title="Winkel Gamma kopieren">
							<svg class="copy-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
								<path
									d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
							<svg class="copied-icon" xmlns="http://www.w3.org/2000/svg"
								width="16" height="16" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round">
								<polyline points="20 6 9 17 4 12"></polyline></svg>
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

	<!-- ==================== START COMBINED SCRIPT ==================== -->
	<script>
        // --- Requirements Map (Triangle Version - NEEDS TO BE COMPLETED ACCURATELY) ---
        // This is a SIMPLIFIED example. Real triangle calculations have complex interdependencies.
        // You MUST fill this map based on the actual calculation logic in your DreieckServlet.
        const calculationRequirementsMap = {
            // --- Triangle Sides ---
            'a': [ ['b', 'c', 'alpha'], ['b', 'c', 'gamma'], ['b', 'beta', 'gamma'], /* ... more Laws of Sines/Cosines ... */ ['umfangDreieck', 'b', 'c'] ],
            'b': [ ['a', 'c', 'beta'], ['a', 'c', 'alpha'], ['a', 'alpha', 'gamma'], /* ... */ ['umfangDreieck', 'a', 'c'] ],
            'c': [ ['a', 'b', 'gamma'], ['a', 'b', 'beta'], ['b', 'beta', 'alpha'], /* ... */ ['umfangDreieck', 'a', 'b'] ],
            // --- Triangle Heights ---
            'hoeheA': [ ['flaecheninhaltDreieck', 'a'], ['b', 'gamma'], ['c', 'beta'] /* ... (using trigonometry) ... */ ],
            'hoeheB': [ ['flaecheninhaltDreieck', 'b'], ['a', 'gamma'], ['c', 'alpha'] /* ... */ ],
            'hoeheC': [ ['flaecheninhaltDreieck', 'c'], ['a', 'beta'], ['b', 'alpha'] /* ... */ ],
            // --- Triangle Perimeter & Area ---
            'umfangDreieck': [ ['a', 'b', 'c'] ],
            'flaecheninhaltDreieck': [
                ['a', 'hoeheA'], ['b', 'hoeheB'], ['c', 'hoeheC'], // Base-Height formulas
                ['a', 'b', 'gamma'], ['a', 'c', 'beta'], ['b', 'c', 'alpha'], // Trig Area formula (0.5 * ab * sin(C))
                ['a', 'b', 'c'] // Heron's formula (requires intermediate step for semi-perimeter)
            ],
            // --- Triangle Angles ---
            // Requires sides (Law of Cosines) or other angles (Sum=180) or sides+angle (Law of Sines)
            'alpha': [ ['beta', 'gamma'], ['a', 'b', 'c'], ['a', 'b', 'beta'], ['a', 'c', 'gamma'] /* ... */ ],
            'beta': [ ['alpha', 'gamma'], ['a', 'b', 'c'], ['b', 'a', 'alpha'], ['b', 'c', 'gamma'] /* ... */ ],
            'gamma': [ ['alpha', 'beta'], ['a', 'b', 'c'], ['c', 'a', 'alpha'], ['c', 'b', 'beta'] /* ... */ ],
         };

        // --- DOM References ---
        const inputFields = document.querySelectorAll('.content input[type="text"]');
        const resultsContainer = document.querySelector('.Results'); // Reference to results container
        const copyFeedbackSpan = document.getElementById('copy-feedback'); // Reference for global feedback

        // --- Helper Functions ---
        function isNonZeroNumeric(inputElement) {
            if (!inputElement) return false;
            const value = inputElement.value.trim();
            if (value === '') return false;
            // Allow comma as decimal separator for input parsing
            const numValue = parseFloat(value.replace(',', '.'));
            return !isNaN(numValue) && numValue !== 0;
        }

        function checkConditionMet(requiredInputIds) {
            return requiredInputIds.every(id => isNonZeroNumeric(document.getElementById(id)));
        }

        // --- Result Value Check Function ---
        function isResultValueValid(pElement) {
             // Triangles don't have the 'equal' notice concept like Kreise/Zylinder had
             // So we simplify this - just check if the value is valid and not a placeholder/error
             const valueSpan = pElement.querySelector('.result-value');
             if (!valueSpan) return false;
             const valueText = valueSpan.textContent.trim();
             // Check against typical non-calculated or error values from backend
             const errorValues = ['0', '0.0', '-1', '-1.0', 'NaN', 'Infinity', '-Infinity', '', null, 'undefined'];
             // Check if the backend might output "Nicht berechnet" or similar
             // const notCalculatedStrings = ['Nicht berechnet', 'Error'];
             // return !errorValues.includes(valueText) && !notCalculatedStrings.some(s => valueText.includes(s));
             return !errorValues.includes(valueText); // Simpler check
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
                             // Check if *all* inputs in the current condition group are met
                            if (checkConditionMet(condition)) {
                                isCalculable = true;
                                break; // Found a way to calculate it, no need to check other conditions
                             }
                        }
                    }
                }

                targetInput.classList.toggle('input-has-value', isProvided);
                // Only add 'input-calculable' if it's NOT provided but IS calculable
                targetInput.classList.toggle('input-calculable', !isProvided && isCalculable);
                // Ensure calculable is removed if it becomes provided
                if (isProvided) {
                    targetInput.classList.remove('input-calculable');
                }
            });
        }


        function updateResultHighlighting() {
             const resultParagraphs = document.querySelectorAll('.Results p');
             resultParagraphs.forEach(pElement => {
                const isValid = isResultValueValid(pElement);
                // No 'notice' concept for triangles, just check validity
                pElement.classList.toggle('result-calculated', isValid);
             });
        }

        // --- Copy Functionality (Identical to kreise.jsp) ---
         function handleCopyClick(event) {
            const button = event.target.closest('.copy-button');
            if (!button) return;

            const targetId = button.dataset.copyTargetId;
            const resultElement = document.getElementById(targetId);
            const valueSpan = resultElement?.querySelector('.result-value');
            const labelSpan = resultElement?.querySelector('.result-label');

            if (valueSpan && labelSpan) {
                const textToCopy = valueSpan.textContent.trim();
                const labelText = labelSpan.textContent.trim().replace(':', ''); // Get label text for feedback

                navigator.clipboard.writeText(textToCopy).then(() => {
                    // --- SUCCESS ---
                    button.classList.add('copied');

                    // Optional: Display feedback message (can be removed if icons are enough)
                    // if (copyFeedbackSpan) {
                    //    copyFeedbackSpan.textContent = `${labelText} kopiert!`;
                    //    copyFeedbackSpan.style.display = 'inline';
                    //    copyFeedbackSpan.style.color = 'green';
                    //}

                    setTimeout(() => {
                        // if (copyFeedbackSpan) {
                        //     copyFeedbackSpan.style.display = 'none';
                        //     copyFeedbackSpan.textContent = '';
                        //}
                         button.classList.remove('copied');
                    }, 1500);

                }).catch(err => {
                    // --- FAILURE ---
                    console.error('Fehler beim Kopieren: ', err);
                     if (copyFeedbackSpan) {
                         copyFeedbackSpan.textContent = `Kopieren fehlgeschlagen!`;
                         copyFeedbackSpan.style.color = 'red';
                         copyFeedbackSpan.style.display = 'inline';
                         setTimeout(() => {
                             copyFeedbackSpan.style.display = 'none';
                             copyFeedbackSpan.textContent = '';
                             copyFeedbackSpan.style.color = 'green'; // Reset color
                         }, 2000);
                     } else {
                        alert('Kopieren fehlgeschlagen.'); // Fallback alert
                     }
                     button.classList.remove('copied');
                });
            } else {
                 // --- Elements Not Found ---
                console.error('Konnte Wert zum Kopieren nicht finden für ID:', targetId);
                 if (copyFeedbackSpan) {
                    copyFeedbackSpan.textContent = `Fehler: Element nicht gefunden!`;
                    copyFeedbackSpan.style.color = 'red';
                    copyFeedbackSpan.style.display = 'inline';
                    setTimeout(() => {
                        copyFeedbackSpan.style.display = 'none';
                        copyFeedbackSpan.textContent = '';
                        copyFeedbackSpan.style.color = 'green'; // Reset color
                    }, 2000);
                 }
            }
        }


        // --- Event Listeners & Initial State ---
        document.addEventListener('DOMContentLoaded', () => {
            inputFields.forEach(input => {
                input.addEventListener('input', updateInputHighlighting);
                // Also trigger on blur in case user pastes value and clicks away
                input.addEventListener('blur', updateInputHighlighting);
            });

            // Add event listener for copy buttons using event delegation
            if (resultsContainer) {
                resultsContainer.addEventListener('click', handleCopyClick);
            }

            updateInputHighlighting(); // Set initial input state
            updateResultHighlighting(); // Set initial result state
        });

    </script>
	<!-- ==================== END COMBINED SCRIPT ==================== -->

</body>
<footer>
	<%-- Footer links remain the same --%>
	<a href="http://localhost:8080/HelloWeb/" class="button"
		target="_blank">Hello Web</a> <a
		href="http://localhost:8080/EmployeeWebApp/" class="button"
		target="_blank">EmployeeWebApp</a> <a
		href="http://localhost:8080/Workhours/" class="button" target="_blank">Workhours</a>
</footer>
</html>
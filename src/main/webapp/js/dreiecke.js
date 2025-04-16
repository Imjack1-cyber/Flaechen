// --- Requirements Map for Dreiecke (TRIANGLES) ---
// Confirmed based on berechneDreiecke() logic
const triangleCalculationRequirementsMap = {
	'a': [['b', 'c', 'alpha'], ['b', 'alpha', 'beta'], ['c', 'alpha', 'gamma'], ['flaecheninhaltDreieck', 'hoeheA'], ['umfangDreieck', 'b', 'c']],
	'b': [['a', 'c', 'beta'], ['a', 'alpha', 'beta'], ['c', 'beta', 'gamma'], ['flaecheninhaltDreieck', 'hoeheB'], ['umfangDreieck', 'a', 'c']],
	'c': [['a', 'b', 'gamma'], ['a', 'alpha', 'gamma'], ['b', 'beta', 'gamma'], ['flaecheninhaltDreieck', 'hoeheC'], ['umfangDreieck', 'a', 'b']],
	'hoeheA': [['flaecheninhaltDreieck', 'a'], ['b', 'gamma'], ['c', 'beta']],
	'hoeheB': [['flaecheninhaltDreieck', 'b'], ['a', 'gamma'], ['c', 'alpha']],
	'hoeheC': [['flaecheninhaltDreieck', 'c'], ['a', 'beta'], ['b', 'alpha']],
	'umfangDreieck': [['a', 'b', 'c']],
	'flaecheninhaltDreieck': [['a', 'hoeheA'], ['b', 'hoeheB'], ['c', 'hoeheC'], ['a', 'b', 'gamma'], ['b', 'c', 'alpha'], ['a', 'c', 'beta'], ['a', 'b', 'c']],
	'alpha': [['beta', 'gamma'], ['a', 'b', 'c'], ['a', 'b', 'beta'], ['a', 'c', 'gamma']],
	'beta': [['alpha', 'gamma'], ['a', 'b', 'c'], ['b', 'a', 'alpha'], ['b', 'c', 'gamma']],
	'gamma': [['alpha', 'beta'], ['a', 'b', 'c'], ['c', 'a', 'alpha'], ['c', 'b', 'beta']]
};

// --- Requirements Map for Quadratische Pyramide (PYRAMID) ---
// Confirmed based on berechnePyramide() logic
const pyramidCalculationRequirementsMap = {
	'p_seiteA': [['p_grundflaeche'], ['p_grundflaecheDiagonale'], ['p_volumen', 'p_hoehe'], ['p_mantelflaeche', 'p_hoeheSeite'], ['p_hoeheSeite', 'p_hoehe'], ['p_seitenkante', 'p_hoehe'], ['p_seitenkante', 'p_hoeheSeite']],
	'p_hoehe': [['p_volumen', 'p_seiteA'], ['p_volumen', 'p_grundflaeche'], ['p_hoeheSeite', 'p_seiteA'], ['p_seitenkante', 'p_seiteA']],
	'p_hoeheSeite': [['p_hoehe', 'p_seiteA'], ['p_mantelflaeche', 'p_seiteA'], ['p_oberflaeche', 'p_seiteA'], ['p_oberflaeche', 'p_grundflaeche'], /* Java uses 'p_seiteA' with this, implicitly G=a^2 */['p_seitenkante', 'p_seiteA']],
	'p_seitenkante': [['p_hoehe', 'p_seiteA'], ['p_hoeheSeite', 'p_seiteA']],
	'p_grundflaeche': [['p_seiteA'], ['p_volumen', 'p_hoehe'], ['p_oberflaeche', 'p_mantelflaeche']],
	'p_mantelflaeche': [['p_seiteA', 'p_hoeheSeite'], ['p_oberflaeche', 'p_grundflaeche']],
	'p_oberflaeche': [['p_grundflaeche', 'p_mantelflaeche'], ['p_seiteA', 'p_hoeheSeite']],
	'p_volumen': [['p_grundflaeche', 'p_hoehe'], ['p_seiteA', 'p_hoehe']],
	'p_grundflaecheDiagonale': [['p_seiteA']]
};

// --- Requirements Map for Rechteck (RECTANGLE) ---
// Confirmed based on berechneRechteck() logic
const rechteckCalculationRequirementsMap = {
	'r_seiteA': [['r_flaecheninhalt', 'r_seiteB'], ['r_umfang', 'r_seiteB'], ['r_diagonale', 'r_seiteB']],
	'r_seiteB': [['r_flaecheninhalt', 'r_seiteA'], ['r_umfang', 'r_seiteA'], ['r_diagonale', 'r_seiteA']],
	'r_flaecheninhalt': [['r_seiteA', 'r_seiteB'], ['r_seiteA', 'r_diagonale'], ['r_seiteB', 'r_diagonale'], ['r_seiteA', 'r_umfang'], ['r_seiteB', 'r_umfang']],
	'r_umfang': [['r_seiteA', 'r_seiteB'], ['r_seiteA', 'r_flaecheninhalt'], ['r_seiteB', 'r_flaecheninhalt'], ['r_seiteA', 'r_diagonale'], ['r_seiteB', 'r_diagonale']],
	'r_diagonale': [['r_seiteA', 'r_seiteB'], ['r_seiteA', 'r_flaecheninhalt'], ['r_seiteB', 'r_flaecheninhalt'], ['r_seiteA', 'r_umfang'], ['r_seiteB', 'r_umfang']]
};

// --- Requirements Map for Trapez (TRAPEZOID) ---
// Confirmed based on berechneTrapez() logic
const trapezCalculationRequirementsMap = {
	't_seiteA': [['t_flaecheninhalt', 't_hoehe', 't_seiteC'], ['t_umfang', 't_seiteB', 't_seiteC', 't_seiteD']],
	't_seiteB': [['t_umfang', 't_seiteA', 't_seiteC', 't_seiteD']], // Only calculable from umfang in Java code shown
	't_seiteC': [['t_flaecheninhalt', 't_hoehe', 't_seiteA'], ['t_umfang', 't_seiteA', 't_seiteB', 't_seiteD']],
	't_seiteD': [['t_umfang', 't_seiteA', 't_seiteB', 't_seiteC']], // Only calculable from umfang in Java code shown
	't_hoehe': [['t_flaecheninhalt', 't_seiteA', 't_seiteC']],
	't_flaecheninhalt': [['t_seiteA', 't_seiteC', 't_hoehe']],
	't_umfang': [['t_seiteA', 't_seiteB', 't_seiteC', 't_seiteD']]
};

// --- Requirements Map for Parallelogramm (PARALLELOGRAM) ---
// Confirmed based on berechneParallelogramm() logic
const parallelogrammCalculationRequirementsMap = {
	'pa_seiteA': [['pa_flaecheninhalt', 'pa_hoeheA'], ['pa_umfang', 'pa_seiteB']],
	'pa_seiteB': [['pa_umfang', 'pa_seiteA']], // Only calculable from umfang in Java code shown
	'pa_hoeheA': [['pa_flaecheninhalt', 'pa_seiteA']],
	'pa_flaecheninhalt': [['pa_seiteA', 'pa_hoeheA']],
	'pa_umfang': [['pa_seiteA', 'pa_seiteB'], ['pa_flaecheninhalt', 'pa_hoeheA', 'pa_seiteB']] // Java uses derived 'pa_seiteA' here
};

// --- Requirements Map for Pentagon (PENTAGON) ---
// Confirmed based on berechnePentagon() logic
const pentagonCalculationRequirementsMap = {
	'pe_seite': [['pe_umfang'], ['pe_flaecheninhalt'], ['pe_radiusInkreis'], ['pe_radiusUmkreis']],
	'pe_umfang': [['pe_seite'], ['pe_flaecheninhalt', 'pe_radiusInkreis'], ['pe_radiusInkreis'], ['pe_radiusUmkreis']],
	'pe_radiusInkreis': [['pe_seite'], ['pe_umfang'], ['pe_flaecheninhalt', 'pe_umfang'], ['pe_radiusUmkreis']],
	'pe_radiusUmkreis': [['pe_seite'], ['pe_umfang'], ['pe_radiusInkreis']], // Cannot calculate from area alone in Java code
	'pe_flaecheninhalt': [['pe_seite'], ['pe_umfang', 'pe_radiusInkreis'], ['pe_radiusUmkreis']]
};

// --- Requirements Map for Hexagon (HEXAGON) ---
// Confirmed based on berechneHexagon() logic
const hexagonCalculationRequirementsMap = {
	'h_seite': [['h_umfang'], ['h_flaecheninhalt'], ['h_radiusInkreis'], ['h_radiusUmkreis']],
	'h_umfang': [['h_seite'], ['h_flaecheninhalt', 'h_radiusInkreis'], ['h_radiusInkreis'], ['h_radiusUmkreis']],
	'h_radiusInkreis': [['h_seite'], ['h_umfang'], ['h_flaecheninhalt', 'h_umfang'], ['h_radiusUmkreis']],
	'h_radiusUmkreis': [['h_seite'], ['h_umfang'], ['h_radiusInkreis'], ['h_flaecheninhalt']],
	'h_flaecheninhalt': [['h_seite'], ['h_umfang', 'h_radiusInkreis'], ['h_radiusUmkreis']]
};

// --- Requirements Map for Octagon (OCTAGON) ---
// Confirmed based on berechneOctagon() logic
const octagonCalculationRequirementsMap = {
	'o_seite': [['o_umfang'], ['o_flaecheninhalt'], ['o_radiusInkreis'], ['o_radiusUmkreis']],
	'o_umfang': [['o_seite'], ['o_flaecheninhalt', 'o_radiusInkreis'], ['o_radiusInkreis'], ['o_radiusUmkreis']],
	'o_radiusInkreis': [['o_seite'], ['o_umfang'], ['o_flaecheninhalt', 'o_umfang'], ['o_radiusUmkreis']],
	'o_radiusUmkreis': [['o_seite'], ['o_umfang'], ['o_radiusInkreis']], // Cannot calculate from area alone in Java code
	'o_flaecheninhalt': [['o_seite'], ['o_umfang', 'o_radiusInkreis'], ['o_radiusUmkreis']]
};

// --- Requirements Map for Quader (CUBOID) ---
// Confirmed based on berechneQuader() logic
const quaderCalculationRequirementsMap = {
	'q_kanteA': [['q_volumen', 'q_kanteB', 'q_kanteC'], ['q_grundflaeche', 'q_kanteB'], ['q_mantelflaeche', 'q_kanteC', 'q_kanteB'], ['q_raumdiagonale', 'q_kanteB', 'q_kanteC']],
	'q_kanteB': [['q_volumen', 'q_kanteA', 'q_kanteC'], ['q_grundflaeche', 'q_kanteA'], ['q_mantelflaeche', 'q_kanteC', 'q_kanteA'], ['q_raumdiagonale', 'q_kanteA', 'q_kanteC']],
	'q_kanteC': [['q_volumen', 'q_kanteA', 'q_kanteB'], ['q_volumen', 'q_grundflaeche'], ['q_mantelflaeche', 'q_kanteA', 'q_kanteB'], ['q_raumdiagonale', 'q_kanteA', 'q_kanteB']],
	'q_volumen': [['q_kanteA', 'q_kanteB', 'q_kanteC'], ['q_grundflaeche', 'q_kanteC']],
	'q_grundflaeche': [['q_kanteA', 'q_kanteB'], ['q_volumen', 'q_kanteC'], ['q_oberflaeche', 'q_mantelflaeche']],
	'q_mantelflaeche': [['q_kanteA', 'q_kanteB', 'q_kanteC'], /* Simplified in Java as 2*(a*c+b*c) */['q_oberflaeche', 'q_grundflaeche']],
	'q_oberflaeche': [['q_kanteA', 'q_kanteB', 'q_kanteC'], ['q_grundflaeche', 'q_mantelflaeche']],
	'q_raumdiagonale': [['q_kanteA', 'q_kanteB', 'q_kanteC']]
};

// --- Requirements Map for Würfel (CUBE) ---
// Confirmed based on berechneWuerfel() logic
const wuerfelCalculationRequirementsMap = {
	'w_kante': [['w_volumen'], ['w_oberflaeche'], ['w_raumdiagonale'], ['w_grundflaeche'], ['w_mantelflaeche'], ['w_umfang_seitenflaeche'], ['w_summe_aller_kanten']],
	'w_volumen': [['w_kante'], ['w_oberflaeche'], ['w_raumdiagonale'], ['w_grundflaeche'], ['w_mantelflaeche']],
	'w_grundflaeche': [['w_kante'], ['w_volumen'], ['w_oberflaeche'], ['w_raumdiagonale'], ['w_mantelflaeche']],
	'w_mantelflaeche': [['w_kante'], ['w_volumen'], ['w_oberflaeche'], ['w_raumdiagonale'], ['w_grundflaeche']],
	'w_oberflaeche': [['w_kante'], ['w_volumen'], ['w_raumdiagonale'], ['w_grundflaeche'], ['w_mantelflaeche']],
	'w_raumdiagonale': [['w_kante'], ['w_volumen'], ['w_oberflaeche'], ['w_grundflaeche'], ['w_mantelflaeche']],
	'w_umfang_seitenflaeche': [['w_kante']],
	'w_summe_aller_kanten': [['w_kante']]
};

// --- Requirements Map for Prisma (PRISM) ---
// Confirmed based on berechnePrisma() logic
const prismaCalculationRequirementsMap = {
	'pr_grundflaeche': [['pr_volumen', 'pr_hoehe'], ['pr_oberflaeche', 'pr_mantelflaeche'], ['pr_oberflaeche', 'pr_umfang', 'pr_hoehe']],
	'pr_umfang': [['pr_mantelflaeche', 'pr_hoehe']], // Only this direct calculation in Java code
	'pr_hoehe': [['pr_volumen', 'pr_grundflaeche'], ['pr_mantelflaeche', 'pr_umfang'], ['pr_oberflaeche', 'pr_grundflaeche', 'pr_umfang']],
	'pr_volumen': [['pr_grundflaeche', 'pr_hoehe'], ['pr_grundflaeche', 'pr_mantelflaeche', 'pr_umfang']],
	'pr_mantelflaeche': [['pr_umfang', 'pr_hoehe'], ['pr_oberflaeche', 'pr_grundflaeche'], ['pr_umfang', 'pr_volumen', 'pr_grundflaeche']], // Java uses derived hoehe
	'pr_oberflaeche': [['pr_grundflaeche', 'pr_mantelflaeche'], ['pr_grundflaeche', 'pr_umfang', 'pr_hoehe'], ['pr_volumen', 'pr_hoehe', 'pr_mantelflaeche']] // Java uses derived grundflaeche
};


// --- DOM References ---
let allInputFields = null;
let resultsTabContainer = null;
let copyFeedbackSpan = null;
let geometrieForm = null;
let inputTabContainer = null; // Reference for input tabs

// --- Helper Functions ---
function getNumericValue(inputElement) {
	if (!inputElement) return NaN;
	// Trim, replace German comma with dot, check for empty string
	const value = inputElement.value.trim().replace(',', '.');
	if (value === '') return NaN;
	const num = parseFloat(value);
	return isNaN(num) ? NaN : num; // Return NaN if parsing failed
}

function isNonZeroNumeric(inputElement) {
	const numValue = getNumericValue(inputElement);
	// Use a small epsilon to account for potential floating point inaccuracies
	const epsilon = 1E-9;
	// Check if it's a number, not NaN, and its absolute value is greater than epsilon
	return !isNaN(numValue) && Math.abs(numValue) > epsilon;
}

// Checks if *all* required input fields have a non-zero numeric value
function checkConditionMet(requiredInputIds) {
	return requiredInputIds.every(id => {
		const element = document.getElementById(id);
		// Ensure the element exists and has a non-zero numeric value
		return element && isNonZeroNumeric(element);
	});
}

// --- Result Value Checking ---
function isResultInvalidNumericText(valueText) {
	const errorValues = ['NaN', 'Infinity', '-Infinity', 'Ungültig', 'Fehler', null, 'null', undefined];
	// Check if the trimmed text is empty or matches any error value
	return valueText == null || valueText.trim() === '' || errorValues.includes(valueText.trim());
}

function isResultZeroText(valueText) {
	if (valueText === null || valueText.trim() === '') return false;
	// Handle potential thousand separators (like '.') before replacing the decimal comma
	const cleanedValue = valueText.replace(/\./g, '').replace(',', '.');
	const numValue = parseFloat(cleanedValue);
	const epsilon = 1E-9;
	// Check if it's a number, not NaN, and its absolute value is less than epsilon
	return !isNaN(numValue) && Math.abs(numValue) < epsilon;
}


// --- Highlighting Logic (Uses Requirements Maps) ---
function updateInputHighlighting() {
	if (!allInputFields) return;

	allInputFields.forEach(targetInput => {
		const targetId = targetInput.id;
		// Skip the shared decimalPlaces inputs in both locations
		if (targetId === 'decimalPlaces' || targetId === 'decimalPlacesSubmit') {
			targetInput.classList.remove('input-has-value', 'input-calculable');
			return;
		}

		// Get the current state of the target input
		const numValue = getNumericValue(targetInput);
		const hasNonZeroValue = isNonZeroNumeric(targetInput); // True if value is numeric AND > epsilon
		// Note: A field with '0' has !hasNonZeroValue = true

		let isCalculable = false;
		let requirementsMap = null;
		let mapKey = targetId;

		// Determine the correct requirements map based on the input's section class
		const parentSection = targetInput.closest('.input-section');
		if (parentSection) {
			if (parentSection.classList.contains('Dreieck')) requirementsMap = triangleCalculationRequirementsMap;
			else if (parentSection.classList.contains('Pyramide')) requirementsMap = pyramidCalculationRequirementsMap;
			else if (parentSection.classList.contains('Rechteck')) requirementsMap = rechteckCalculationRequirementsMap;
			else if (parentSection.classList.contains('Trapez')) requirementsMap = trapezCalculationRequirementsMap;
			else if (parentSection.classList.contains('Parallelogramm')) requirementsMap = parallelogrammCalculationRequirementsMap;
			else if (parentSection.classList.contains('Pentagon')) requirementsMap = pentagonCalculationRequirementsMap;
			else if (parentSection.classList.contains('Hexagon')) requirementsMap = hexagonCalculationRequirementsMap;
			else if (parentSection.classList.contains('Octagon')) requirementsMap = octagonCalculationRequirementsMap;
			else if (parentSection.classList.contains('Quader')) requirementsMap = quaderCalculationRequirementsMap;
			else if (parentSection.classList.contains('Wuerfel')) requirementsMap = wuerfelCalculationRequirementsMap;
			else if (parentSection.classList.contains('Prisma')) requirementsMap = prismaCalculationRequirementsMap;
			// Add more shapes here if necessary
		}

		// *** Check if the target field *can* be calculated based on *other* inputs ***
		// This check is now independent of the target input's current value (numValue).
		if (requirementsMap && requirementsMap[mapKey]) {
			const requirements = requirementsMap[mapKey];
			if (requirements) {
				// Check if *any* of the requirement sets for this field are met by *other* non-zero inputs
				isCalculable = requirements.some(condition => checkConditionMet(condition));
			}
		}

		// --- Apply Classes based on the checks ---
		// GREEN: Apply 'input-has-value' only if the input explicitly has a non-zero numeric value.
		targetInput.classList.toggle('input-has-value', hasNonZeroValue);

		// YELLOW: Apply 'input-calculable' only if it does NOT have a non-zero value
		// (i.e., it's empty, 0, or invalid) AND it IS calculable from other inputs.
		targetInput.classList.toggle('input-calculable', !hasNonZeroValue && isCalculable);

	});
}


// --- Result Highlighting ---
function updateResultHighlighting() {
	const resultParagraphs = document.querySelectorAll('.result-section p[id^="result-"]');

	resultParagraphs.forEach(pElement => {
		// Reset classes first
		pElement.classList.remove('result-error', 'result-calculated', 'result-zero');

		const valueSpan = pElement.querySelector('.result-value');
		const valueText = valueSpan ? valueSpan.textContent : null;

		if (isResultInvalidNumericText(valueText)) {
			pElement.classList.add('result-error');
		} else if (isResultZeroText(valueText)) {
			// Style zero results differently if needed, e.g., less prominent than calculated
			pElement.classList.add('result-zero'); // Or keep it neutral like 'result-calculated'
		} else {
			// Non-zero, valid numeric result
			pElement.classList.add('result-calculated');
		}
	});
}

// --- Tab Switching Logic (Generic for Inputs and Results) ---
function showTab(containerSelector, contentBaseId, tabIdSuffix, clickedButton) {
	// Find the navigation menu closest to the clicked button
	const nav = clickedButton.closest('.tab-nav');
	if (!nav) { console.error("Tab nav not found for button", clickedButton); return; }

	// Find the content container associated with this navigation
	// It's usually the next sibling, but might be nested (e.g., inside <details>)
	let contentContainer = nav.nextElementSibling;
	if (!contentContainer || !contentContainer.classList.contains('tab-content-container')) {
		const detailsParent = nav.closest('details'); // Check if inside results details
		if (detailsParent) {
			contentContainer = detailsParent.querySelector('.tab-content-container');
		}
	}
	// Final check if container was found
	if (!contentContainer) { console.error("Tab content container not found relative to nav:", nav); return; }

	// Deactivate all buttons within this specific navigation menu
	nav.querySelectorAll('.tab-button').forEach(button => button.classList.remove('active'));

	// Deactivate all tab content panes within this specific container
	contentContainer.querySelectorAll('.tab-content').forEach(tab => {
		// Ensure we only hide tabs belonging to this group (based on ID prefix)
		if (tab.id.startsWith(contentBaseId)) {
			tab.classList.remove('active');
		}
	});

	// Construct the ID of the target tab content pane
	const targetTabId = contentBaseId + tabIdSuffix;
	const selectedTab = contentContainer.querySelector('#' + targetTabId);

	// Activate the target tab content pane
	if (selectedTab) {
		selectedTab.classList.add('active');
	} else {
		// Log a warning if the target tab content doesn't exist
		console.warn("Tab content not found for ID:", targetTabId, "within container", contentContainer);
	}
	// Activate the clicked button itself
	if (clickedButton) {
		clickedButton.classList.add('active');
	}
}

// --- Specific Wrappers for Input and Result Tabs ---
function showInputTab(tabIdSuffix, clickedButton) {
	// Target the container specifically for inputs using '.input-container'
	showTab('.input-container.tab-content-container', 'input-tab-', tabIdSuffix, clickedButton);
}

function showResultTab(tabIdSuffix, clickedButton) {
	// Target the container specifically for results inside '.results-details'
	showTab('.results-details .tab-content-container', 'result-tab-', tabIdSuffix, clickedButton);
}


// --- Copy Functionality ---
function handleCopyClick(event) {
	// Find the closest copy button that was clicked
	const button = event.target.closest('.copy-button');
	// Ignore clicks outside a button or not within the Results section
	if (!button || !button.closest('.Results')) return;

	// Get the ID of the result paragraph to copy from the button's data attribute
	const targetId = button.dataset.copyTargetId;
	if (!targetId) { console.warn("Copy button missing data-copy-target-id attribute."); return; }

	// Find the result paragraph element
	const resultElement = document.getElementById(targetId);
	if (!resultElement) { console.error('Copy target element not found:', targetId); return; }

	// Find the span containing the actual value within the paragraph
	const valueSpan = resultElement.querySelector('.result-value');
	if (!valueSpan) { console.error('Value span (.result-value) not found within:', targetId); return; }

	// Get the text content, trimmed of whitespace
	const textToCopy = valueSpan.textContent.trim();

	// Prevent copying if the value is considered invalid or empty
	if (isResultInvalidNumericText(textToCopy)) {
		console.log("Not copying invalid or empty value:", textToCopy);
		// Briefly indicate error visually on the button
		button.classList.add('error-copy'); // Requires CSS for .error-copy
		setTimeout(() => button.classList.remove('error-copy'), 800);
		return;
	}

	// Use the Clipboard API to copy the text
	navigator.clipboard.writeText(textToCopy).then(() => {
		// Success: Provide feedback
		button.classList.add('copied'); // Trigger CSS change (e.g., show checkmark)
		if (copyFeedbackSpan) {
			// Show feedback message near the results summary
			copyFeedbackSpan.textContent = 'Kopiert!';
			copyFeedbackSpan.style.color = 'green';
			copyFeedbackSpan.style.display = 'inline';
		}
		// Remove feedback after a short delay
		setTimeout(() => {
			button.classList.remove('copied');
			if (copyFeedbackSpan) copyFeedbackSpan.style.display = 'none';
		}, 1500);
	}).catch(err => {
		// Error handling: Log error and provide feedback
		console.error('Fehler beim Kopieren: ', err);
		if (copyFeedbackSpan) {
			copyFeedbackSpan.textContent = 'Fehler!';
			copyFeedbackSpan.style.color = 'red';
			copyFeedbackSpan.style.display = 'inline';
			// Hide error message after a slightly longer delay
			setTimeout(() => {
				if (copyFeedbackSpan) {
					copyFeedbackSpan.style.display = 'none';
					copyFeedbackSpan.style.color = 'green'; // Reset color for next success
				}
			}, 2000);
		}
		// Ensure the 'copied' class is removed on error too
		button.classList.remove('copied');
	});
}

// --- Event Listeners & Initial State ---
document.addEventListener('DOMContentLoaded', () => {
	// Get references to elements after the DOM is loaded
	allInputFields = document.querySelectorAll('.input-section input[type="text"], .input-section input[type="number"]');
	resultsTabContainer = document.querySelector('.results-details .tab-content-container');
	inputTabContainer = document.querySelector('.input-container.tab-content-container'); // Get input tab container
	copyFeedbackSpan = document.getElementById('copy-feedback'); // Feedback span in summary
	geometrieForm = document.getElementById('geometrie-form'); // The main form

	// Attach input event listeners for live highlighting
	if (allInputFields.length > 0) {
		allInputFields.forEach(input => {
			// Exclude the two decimalPlaces inputs from triggering the highlighting update on *their own* input event
			if (input.id !== 'decimalPlaces' && input.id !== 'decimalPlacesSubmit') {
				input.addEventListener('input', updateInputHighlighting);
			}
		});
	} else { console.warn("Keine Eingabefelder für Highlighting gefunden."); }

	// Set up event delegation for copy buttons within the results details section
	const resultsDetails = document.querySelector('.results-details');
	if (resultsDetails) {
		resultsDetails.addEventListener('click', handleCopyClick);
	} else {
		// Warn if the results container exists but the expected parent <details> is missing
		if (document.querySelector('.results-container')) {
			console.warn("Results container found, but <details class='results-details'> wrapper missing for copy button event delegation.");
		}
	}

	// --- Sync decimal places inputs ---
	const decimalPlacesInputTab = document.getElementById('decimalPlaces'); // Inside input tabs
	const decimalPlacesInputSubmit = document.getElementById('decimalPlacesSubmit'); // In submit area
	if (decimalPlacesInputTab && decimalPlacesInputSubmit) {
		// Sync from tab input to submit input
		decimalPlacesInputTab.addEventListener('input', () => {
			decimalPlacesInputSubmit.value = decimalPlacesInputTab.value;
		});
		// Sync from submit input to tab input
		decimalPlacesInputSubmit.addEventListener('input', () => {
			decimalPlacesInputTab.value = decimalPlacesInputSubmit.value;
		});
		// Initialize submit input from tab input on load
		decimalPlacesInputSubmit.value = decimalPlacesInputTab.value;
	}

	// --- Initial UI State ---
	updateInputHighlighting();  // Run highlighting on page load
	updateResultHighlighting(); // Run result styling on page load

	// --- Initialize first tabs (Inputs and Results) ---

	// Activate the first *input* tab
	const firstInputNav = document.querySelector('.input-tabs'); // Use specific class for input nav
	if (firstInputNav) {
		const firstInputButton = firstInputNav.querySelector('.tab-button'); // Get the first button
		if (firstInputButton) {
			// Extract the tab suffix (e.g., 'flaechen') from its onclick attribute
			const onclickAttr = firstInputButton.getAttribute('onclick');
			if (onclickAttr) {
				const match = onclickAttr.match(/showInputTab\('([^']+)'/); // Regex to find the suffix
				if (match && match[1]) {
					const targetTabIdSuffix = match[1];
					// Check if the button doesn't already have the active class before calling showInputTab
					if (!firstInputButton.classList.contains('active')) {
						showInputTab(targetTabIdSuffix, firstInputButton); // Activate the tab
					} else {
						// If it's already active (e.g. from HTML), ensure content is shown
						const targetTabId = 'input-tab-' + targetTabIdSuffix;
						const selectedTab = inputTabContainer.querySelector('#' + targetTabId);
						if (selectedTab && !selectedTab.classList.contains('active')) {
							// Deactivate others first if necessary (though showTab should handle this)
							inputTabContainer.querySelectorAll('.tab-content.active').forEach(tc => tc.classList.remove('active'));
							selectedTab.classList.add('active');
						}
					}
				} else {
					console.warn("Could not extract suffix from first input tab button onclick:", onclickAttr);
				}
			} else {
				console.warn("First input tab button has no onclick attribute.");
			}
		} else {
			console.warn("No input tab buttons found in .input-tabs.");
		}
	} else {
		console.warn("Input tab navigation (.input-tabs) not found.");
	}

	// Activate the first *result* tab (if results are present)
	const firstResultNav = document.querySelector('.results-details .tab-nav');
	if (firstResultNav) { // Only proceed if the results section exists
		const firstResultButton = firstResultNav.querySelector('.tab-button'); // Get the first button
		if (firstResultButton) {
			const onclickAttr = firstResultButton.getAttribute('onclick');
			if (onclickAttr) {
				const match = onclickAttr.match(/showResultTab\('([^']+)'/); // Regex to find the suffix
				if (match && match[1]) {
					const targetTabIdSuffix = match[1];
					// Check if the button doesn't already have the active class before calling showResultTab
					if (!firstResultButton.classList.contains('active')) {
						showResultTab(targetTabIdSuffix, firstResultButton); // Activate the tab
					} else {
						// If it's already active (e.g. from HTML), ensure content is shown
						const targetTabId = 'result-tab-' + targetTabIdSuffix;
						const selectedTab = resultsTabContainer.querySelector('#' + targetTabId);
						if (selectedTab && !selectedTab.classList.contains('active')) {
							// Deactivate others first if necessary (though showTab should handle this)
							resultsTabContainer.querySelectorAll('.tab-content.active').forEach(tc => tc.classList.remove('active'));
							selectedTab.classList.add('active');
						}
					}
				} else {
					console.warn("Could not extract suffix from first result tab button onclick:", onclickAttr);
				}
			} else {
				console.warn("First result tab button has no onclick attribute.");
			}
		} else {
			console.warn("No result tab buttons found in .results-details .tab-nav.");
		}
	} // No warning if no results nav exists (e.g., on initial load without results)

	console.log("Geometrie Rechner JS (Multi-Shape with Input Tabs) initialisiert.");
});
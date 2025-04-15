// --- Requirements Map for Dreiecke (TRIANGLES) ---
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
const pyramidCalculationRequirementsMap = {
	'p_seiteA': [ ['p_grundflaeche'], ['p_grundflaecheDiagonale'], ['p_volumen', 'p_hoehe'], ['p_mantelflaeche', 'p_hoeheSeite'], ['p_hoeheSeite', 'p_hoehe'], ['p_seitenkante', 'p_hoehe'], ['p_seitenkante', 'p_hoeheSeite'] ],
	'p_hoehe': [ ['p_volumen', 'p_seiteA'], ['p_volumen', 'p_grundflaeche'], ['p_hoeheSeite', 'p_seiteA'], ['p_seitenkante', 'p_seiteA'] ],
	'p_hoeheSeite': [ ['p_hoehe', 'p_seiteA'], ['p_mantelflaeche', 'p_seiteA'], ['p_oberflaeche', 'p_seiteA'], ['p_oberflaeche', 'p_grundflaeche', 'p_seiteA'], ['p_seitenkante', 'p_seiteA'] ],
	'p_seitenkante': [ ['p_hoehe', 'p_seiteA'], ['p_hoeheSeite', 'p_seiteA'] ],
	'p_grundflaeche': [ ['p_seiteA'], ['p_volumen', 'p_hoehe'], ['p_oberflaeche', 'p_mantelflaeche'] ],
	'p_mantelflaeche': [ ['p_seiteA', 'p_hoeheSeite'], ['p_oberflaeche', 'p_grundflaeche'] ],
	'p_oberflaeche': [ ['p_grundflaeche', 'p_mantelflaeche'], ['p_seiteA', 'p_hoeheSeite'] ],
	'p_volumen': [ ['p_grundflaeche', 'p_hoehe'], ['p_seiteA', 'p_hoehe'] ],
	'p_grundflaecheDiagonale': [ ['p_seiteA'] ]
};

// --- Requirements Map for Rechteck (RECTANGLE) ---
const rechteckCalculationRequirementsMap = {
    'r_seiteA': [['r_flaecheninhalt', 'r_seiteB'], ['r_umfang', 'r_seiteB'], ['r_diagonale', 'r_seiteB']],
    'r_seiteB': [['r_flaecheninhalt', 'r_seiteA'], ['r_umfang', 'r_seiteA'], ['r_diagonale', 'r_seiteA']],
    'r_flaecheninhalt': [['r_seiteA', 'r_seiteB'], ['r_seiteA', 'r_diagonale'], ['r_seiteB', 'r_diagonale'], ['r_seiteA', 'r_umfang'], ['r_seiteB', 'r_umfang']],
    'r_umfang': [['r_seiteA', 'r_seiteB'], ['r_seiteA', 'r_flaecheninhalt'], ['r_seiteB', 'r_flaecheninhalt'], ['r_seiteA', 'r_diagonale'], ['r_seiteB', 'r_diagonale']],
    'r_diagonale': [['r_seiteA', 'r_seiteB'], ['r_seiteA', 'r_flaecheninhalt'], ['r_seiteB', 'r_flaecheninhalt'], ['r_seiteA', 'r_umfang'], ['r_seiteB', 'r_umfang']]
};

// --- Requirements Map for Trapez (TRAPEZOID) ---
const trapezCalculationRequirementsMap = {
    't_seiteA': [['t_flaecheninhalt', 't_hoehe', 't_seiteC'], ['t_umfang', 't_seiteB', 't_seiteC', 't_seiteD']],
    't_seiteB': [['t_umfang', 't_seiteA', 't_seiteC', 't_seiteD']],
    't_seiteC': [['t_flaecheninhalt', 't_hoehe', 't_seiteA'], ['t_umfang', 't_seiteA', 't_seiteB', 't_seiteD']],
    't_seiteD': [['t_umfang', 't_seiteA', 't_seiteB', 't_seiteC']],
    't_hoehe': [['t_flaecheninhalt', 't_seiteA', 't_seiteC']],
    't_flaecheninhalt': [['t_seiteA', 't_seiteC', 't_hoehe']],
    't_umfang': [['t_seiteA', 't_seiteB', 't_seiteC', 't_seiteD']]
};

// --- Requirements Map for Parallelogramm (PARALLELOGRAM) ---
const parallelogrammCalculationRequirementsMap = {
    'pa_seiteA': [['pa_flaecheninhalt', 'pa_hoeheA'], ['pa_umfang', 'pa_seiteB']],
    'pa_seiteB': [['pa_umfang', 'pa_seiteA']],
    'pa_hoeheA': [['pa_flaecheninhalt', 'pa_seiteA']],
    'pa_flaecheninhalt': [['pa_seiteA', 'pa_hoeheA']],
    'pa_umfang': [['pa_seiteA', 'pa_seiteB'], ['pa_flaecheninhalt', 'pa_hoeheA', 'pa_seiteB']]
};

// --- Requirements Map for Pentagon (PENTAGON) ---
const pentagonCalculationRequirementsMap = {
    'pe_seite': [['pe_umfang'], ['pe_flaecheninhalt'], ['pe_radiusInkreis'], ['pe_radiusUmkreis']],
    'pe_umfang': [['pe_seite'], ['pe_flaecheninhalt', 'pe_radiusInkreis'], ['pe_radiusInkreis'], ['pe_radiusUmkreis']],
    'pe_radiusInkreis': [['pe_seite'], ['pe_umfang'], ['pe_flaecheninhalt', 'pe_umfang'], ['pe_radiusUmkreis']],
    'pe_radiusUmkreis': [['pe_seite'], ['pe_umfang'], ['pe_radiusInkreis']],
    'pe_flaecheninhalt': [['pe_seite'], ['pe_umfang', 'pe_radiusInkreis'], ['pe_radiusUmkreis']]
};

// --- Requirements Map for Hexagon (HEXAGON) ---
const hexagonCalculationRequirementsMap = {
    'h_seite': [['h_umfang'], ['h_flaecheninhalt'], ['h_radiusInkreis'], ['h_radiusUmkreis']],
    'h_umfang': [['h_seite'], ['h_flaecheninhalt', 'h_radiusInkreis'], ['h_radiusInkreis'], ['h_radiusUmkreis']],
    'h_radiusInkreis': [['h_seite'], ['h_umfang'], ['h_flaecheninhalt', 'h_umfang'], ['h_radiusUmkreis']],
    'h_radiusUmkreis': [['h_seite'], ['h_umfang'], ['h_radiusInkreis'], ['h_flaecheninhalt']],
    'h_flaecheninhalt': [['h_seite'], ['h_umfang', 'h_radiusInkreis'], ['h_radiusUmkreis']]
};

// --- Requirements Map for Octagon (OCTAGON) ---
const octagonCalculationRequirementsMap = {
    'o_seite': [['o_umfang'], ['o_flaecheninhalt'], ['o_radiusInkreis'], ['o_radiusUmkreis']],
    'o_umfang': [['o_seite'], ['o_flaecheninhalt', 'o_radiusInkreis'], ['o_radiusInkreis'], ['o_radiusUmkreis']],
    'o_radiusInkreis': [['o_seite'], ['o_umfang'], ['o_flaecheninhalt', 'o_umfang'], ['o_radiusUmkreis']],
    'o_radiusUmkreis': [['o_seite'], ['o_umfang'], ['o_radiusInkreis']],
    'o_flaecheninhalt': [['o_seite'], ['o_umfang', 'o_radiusInkreis'], ['o_radiusUmkreis']]
};

// --- Requirements Map for Quader (CUBOID) ---
const quaderCalculationRequirementsMap = {
    'q_kanteA': [['q_volumen', 'q_kanteB', 'q_kanteC'], ['q_grundflaeche', 'q_kanteB'], ['q_mantelflaeche', 'q_kanteC', 'q_kanteB'], ['q_raumdiagonale', 'q_kanteB', 'q_kanteC']],
    'q_kanteB': [['q_volumen', 'q_kanteA', 'q_kanteC'], ['q_grundflaeche', 'q_kanteA'], ['q_mantelflaeche', 'q_kanteC', 'q_kanteA'], ['q_raumdiagonale', 'q_kanteA', 'q_kanteC']],
    'q_kanteC': [['q_volumen', 'q_kanteA', 'q_kanteB'], ['q_volumen', 'q_grundflaeche'], ['q_mantelflaeche', 'q_kanteA', 'q_kanteB'], ['q_raumdiagonale', 'q_kanteA', 'q_kanteB']],
    'q_volumen': [['q_kanteA', 'q_kanteB', 'q_kanteC'], ['q_grundflaeche', 'q_kanteC']],
    'q_grundflaeche': [['q_kanteA', 'q_kanteB'], ['q_volumen', 'q_kanteC'], ['q_oberflaeche', 'q_mantelflaeche']],
    'q_mantelflaeche': [['q_kanteA', 'q_kanteB', 'q_kanteC'], ['q_oberflaeche', 'q_grundflaeche']],
    'q_oberflaeche': [['q_kanteA', 'q_kanteB', 'q_kanteC'], ['q_grundflaeche', 'q_mantelflaeche']],
    'q_raumdiagonale': [['q_kanteA', 'q_kanteB', 'q_kanteC']]
};

// --- Requirements Map for Würfel (CUBE) ---
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
const prismaCalculationRequirementsMap = {
    'pr_grundflaeche': [['pr_volumen', 'pr_hoehe'], ['pr_oberflaeche', 'pr_mantelflaeche'], ['pr_oberflaeche', 'pr_umfang', 'pr_hoehe']],
    'pr_umfang': [['pr_mantelflaeche', 'pr_hoehe']],
    'pr_hoehe': [['pr_volumen', 'pr_grundflaeche'], ['pr_mantelflaeche', 'pr_umfang'], ['pr_oberflaeche', 'pr_grundflaeche', 'pr_umfang']],
    'pr_volumen': [['pr_grundflaeche', 'pr_hoehe'], ['pr_grundflaeche', 'pr_mantelflaeche', 'pr_umfang']],
    'pr_mantelflaeche': [['pr_umfang', 'pr_hoehe'], ['pr_oberflaeche', 'pr_grundflaeche'], ['pr_umfang', 'pr_volumen', 'pr_grundflaeche']],
    'pr_oberflaeche': [['pr_grundflaeche', 'pr_mantelflaeche'], ['pr_grundflaeche', 'pr_umfang', 'pr_hoehe'], ['pr_volumen', 'pr_hoehe', 'pr_mantelflaeche']]
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
	const value = inputElement.value.trim().replace(',', '.');
	if (value === '') return NaN;
	const num = parseFloat(value);
	return isNaN(num) ? NaN : num;
}

function isNonZeroNumeric(inputElement) {
	const numValue = getNumericValue(inputElement);
	const epsilon = 1E-9;
	return !isNaN(numValue) && Math.abs(numValue) > epsilon;
}

function checkConditionMet(requiredInputIds) {
	return requiredInputIds.every(id => {
		const element = document.getElementById(id);
		return element && isNonZeroNumeric(element);
	});
}

// --- Result Value Checking ---
function isResultInvalidNumericText(valueText) {
	const errorValues = ['NaN', 'Infinity', '-Infinity', 'Ungültig', 'Fehler', null, 'null'];
	return errorValues.includes(valueText) || (valueText != null && valueText.trim() === '');
}

function isResultZeroText(valueText) {
	if (valueText === null || valueText.trim() === '') return false;
	// Use German locale for parsing potentially formatted output
	const numValue = parseFloat(valueText.replace('.', '').replace(',', '.')); // First remove thousand sep, then replace decimal comma
	const epsilon = 1E-9;
	return !isNaN(numValue) && Math.abs(numValue) < epsilon;
}


// --- Highlighting Logic ---
function updateInputHighlighting() {
    if (!allInputFields) return;

    allInputFields.forEach(targetInput => {
        const targetId = targetInput.id;
        // Skip the shared decimalPlaces input in the submit area AND the one in the tab
        if (targetId === 'decimalPlaces' || targetId === 'decimalPlacesSubmit') {
            targetInput.classList.remove('input-has-value', 'input-calculable');
            return;
        }

        const numValue = getNumericValue(targetInput);
        const hasNonZeroValue = isNonZeroNumeric(targetInput);
        const hasValue = !isNaN(numValue); // True if value is numeric (incl. 0)
        const isEmpty = targetInput.value.trim() === '';

        let isCalculable = false;
        let requirementsMap = null;
        let mapKey = targetId;

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
        }

        // Check if calculable ONLY if no value is present (NaN or empty string)
        if ((isNaN(numValue) || isEmpty) && requirementsMap && requirementsMap[mapKey]) {
            const requirements = requirementsMap[mapKey];
            if (requirements) {
                isCalculable = requirements.some(condition => checkConditionMet(condition));
            }
        }

        // --- Apply Classes ---
        targetInput.classList.toggle('input-has-value', hasNonZeroValue);
        targetInput.classList.toggle('input-calculable', (!hasValue || isEmpty) && isCalculable);
        if (!hasNonZeroValue) targetInput.classList.remove('input-has-value');
        if (hasValue || !isCalculable) targetInput.classList.remove('input-calculable');

    });
}


// --- Result Highlighting ---
function updateResultHighlighting() {
	const resultParagraphs = document.querySelectorAll('.result-section p[id^="result-"]');

	resultParagraphs.forEach(pElement => {
		pElement.classList.remove('result-error', 'result-calculated', 'result-zero');

		const valueSpan = pElement.querySelector('.result-value');
		const valueText = valueSpan ? valueSpan.textContent : null;

		if (isResultInvalidNumericText(valueText)) {
			pElement.classList.add('result-error');
		} else if (isResultZeroText(valueText)) {
			pElement.classList.add('result-zero');
		} else {
            pElement.classList.add('result-calculated');
        }
	});
}

// --- Tab Switching Logic (Generic for Inputs and Results) ---
function showTab(containerSelector, contentBaseId, tabIdSuffix, clickedButton) {
    const nav = clickedButton.closest('.tab-nav');
    if (!nav) { console.error("Tab nav not found for button", clickedButton); return; }

    // Find the correct content container relative to the nav
    // Input tabs nav is followed by the input tab container
    // Result tabs nav is inside details, followed by result tab container
    let contentContainer = nav.nextElementSibling;
    // Adjust if the direct sibling isn't the container (like in details)
    if (!contentContainer || !contentContainer.classList.contains('tab-content-container')) {
         const detailsParent = nav.closest('details');
         if(detailsParent) {
            contentContainer = detailsParent.querySelector('.tab-content-container');
         }
    }

     if (!contentContainer) { console.error("Tab content container not found relative to nav:", nav); return; }

    // Deactivate all buttons and content within this specific tab group
    nav.querySelectorAll('.tab-button').forEach(button => button.classList.remove('active'));
    contentContainer.querySelectorAll('.tab-content').forEach(tab => {
        // Check if the tab ID starts with the expected base ID for this group
        if (tab.id.startsWith(contentBaseId)) {
             tab.classList.remove('active');
        }
    });

    const targetTabId = contentBaseId + tabIdSuffix;
    const selectedTab = contentContainer.querySelector('#' + targetTabId);

    if (selectedTab) {
        selectedTab.classList.add('active');
    } else {
        console.warn("Tab content not found for ID:", targetTabId, "within container", contentContainer);
    }
    if (clickedButton) {
        clickedButton.classList.add('active');
    }
}

// Specific wrappers for input and result tabs
function showInputTab(tabIdSuffix, clickedButton) {
    // The container for input tabs has class 'input-container'
    showTab('.input-container.tab-content-container', 'input-tab-', tabIdSuffix, clickedButton);
}

function showResultTab(tabIdSuffix, clickedButton) {
    // The container for result tabs is inside '.results-details'
    showTab('.results-details .tab-content-container', 'result-tab-', tabIdSuffix, clickedButton);
}


// --- Copy Functionality ---
function handleCopyClick(event) {
	const button = event.target.closest('.copy-button');
	if (!button || !button.closest('.Results')) return;

	const targetId = button.dataset.copyTargetId;
	if (!targetId) { console.warn("Copy button missing data-copy-target-id"); return; }
	const resultElement = document.getElementById(targetId);
	if (!resultElement) { console.error('Copy target element not found:', targetId); return; }
	const valueSpan = resultElement.querySelector('.result-value');
	if (!valueSpan) { console.error('Value span not found within:', targetId); return; }

	const textToCopy = valueSpan.textContent.trim();

	if (isResultInvalidNumericText(textToCopy)) {
		console.log("Not copying invalid or empty value:", textToCopy);
		button.classList.add('error-copy');
		setTimeout(() => button.classList.remove('error-copy'), 800);
		return;
	}

	navigator.clipboard.writeText(textToCopy).then(() => {
		button.classList.add('copied');
		if (copyFeedbackSpan) {
			copyFeedbackSpan.textContent = 'Kopiert!';
            copyFeedbackSpan.style.color = 'green';
			copyFeedbackSpan.style.display = 'inline';
		}
		setTimeout(() => {
			button.classList.remove('copied');
			if (copyFeedbackSpan) copyFeedbackSpan.style.display = 'none';
		}, 1500);
	}).catch(err => {
		console.error('Fehler beim Kopieren: ', err);
        if (copyFeedbackSpan) {
			copyFeedbackSpan.textContent = 'Fehler!';
            copyFeedbackSpan.style.color = 'red';
			copyFeedbackSpan.style.display = 'inline';
            setTimeout(() => {
                if (copyFeedbackSpan) {
                     copyFeedbackSpan.style.display = 'none';
                     copyFeedbackSpan.style.color = 'green';
                }
            }, 2000);
        }
		button.classList.remove('copied');
	});
}

// --- Event Listeners & Initial State ---
document.addEventListener('DOMContentLoaded', () => {
	allInputFields = document.querySelectorAll('.input-section input[type="text"], .input-section input[type="number"]');
	resultsTabContainer = document.querySelector('.results-details .tab-content-container');
    inputTabContainer = document.querySelector('.input-container.tab-content-container'); // Correct selector
	copyFeedbackSpan = document.getElementById('copy-feedback');
	geometrieForm = document.getElementById('geometrie-form');

	if (allInputFields.length > 0) {
		allInputFields.forEach(input => {
			if (input.closest('.controls-submit') === null) { // Exclude submit area input
				input.addEventListener('input', updateInputHighlighting);
			}
		});
	} else { console.warn("Keine Eingabefelder für Highlighting gefunden."); }

	const resultsDetails = document.querySelector('.results-details');
	if (resultsDetails) {
		resultsDetails.addEventListener('click', handleCopyClick);
	} else { if (document.querySelector('.results-container')) { console.warn("Results container found, but details wrapper missing for copy delegation."); }}

    // Sync decimal places inputs
    const decimalPlacesInput1 = document.getElementById('decimalPlaces'); // Inside input tabs
    const decimalPlacesInput2 = document.getElementById('decimalPlacesSubmit'); // In submit area
    if (decimalPlacesInput1 && decimalPlacesInput2) {
        decimalPlacesInput1.addEventListener('input', () => {
            decimalPlacesInput2.value = decimalPlacesInput1.value;
        });
        decimalPlacesInput2.addEventListener('input', () => {
             decimalPlacesInput1.value = decimalPlacesInput2.value;
        });
    }

	updateInputHighlighting();
	updateResultHighlighting();

	// --- Initialisiere ersten Tab für EINGABEN und ERGEBNISSE ---
	const firstInputNav = document.querySelector('.input-tabs'); // Use specific class for input nav
    if (firstInputNav) {
        const firstInputButton = firstInputNav.querySelector('.tab-button');
        if (firstInputButton) {
            // Extract the suffix (e.g., 'flaechen') from onclick
            const onclickAttr = firstInputButton.getAttribute('onclick');
            if (onclickAttr) {
                 const match = onclickAttr.match(/showInputTab\('([^']+)'/);
                 if (match && match[1]) {
                     const targetTabIdSuffix = match[1];
                     showInputTab(targetTabIdSuffix, firstInputButton);
                 } else {
                     console.warn("Could not extract suffix from first input tab button onclick:", onclickAttr);
                 }
            } else {
                 console.warn("First input tab button has no onclick attribute.");
            }
        } else {
             console.warn("No input tab buttons found.");
        }
    } else {
         console.warn("Input tab navigation (.input-tabs) not found.");
    }

	const firstResultNav = document.querySelector('.results-details .tab-nav');
	if (firstResultNav) {
		const firstResultButton = firstResultNav.querySelector('.tab-button');
		 if (firstResultButton) {
             const onclickAttr = firstResultButton.getAttribute('onclick');
             if (onclickAttr) {
                 const match = onclickAttr.match(/showResultTab\('([^']+)'/);
                 if (match && match[1]) {
                     const targetTabIdSuffix = match[1];
                     showResultTab(targetTabIdSuffix, firstResultButton);
                 } else {
                     console.warn("Could not extract suffix from first result tab button onclick:", onclickAttr);
                 }
            } else {
                console.warn("First result tab button has no onclick attribute.");
            }
        } else {
            console.warn("No result tab buttons found.");
        }
	} // No warning if no results nav exists (e.g., on initial load)

	console.log("Geometrie Rechner JS (Multi-Shape with Input Tabs) initialisiert.");
});
// --- Requirements Map ---
const calculationRequirementsMap = {
	// --- Kreis ---
	'radius1': [
		['durchmesser1'], ['umfang1'], ['flaecheninhalt1'], ['kreisbogen1', 'alpha1'], ['kreisausschnitt1', 'alpha1'], // Use alpha1
		['kreisausschnitt1', 'kreisbogen1'], ['oberflaecheKugel1'], ['volumenKugel1'], ['grundflaecheZylinder1'],
		['grundflaecheKegel1'], ['mantelflaecheZylinder1', 'hoehe1'], ['volumenZylinder1', 'hoehe1'],
		['mantelflaecheKegel1', 'seitenhoehe1'], ['seitenhoehe1', 'hoehe1'], ['volumenKegel1', 'hoehe1'],
		['oberflaecheZylinder1', 'hoehe1'], ['oberflaecheKegel1', 'seitenhoehe1'], ['oberflaecheKegel1', 'hoehe1']
	],
	'durchmesser1': [['radius1'], ['umfang1'], ['flaecheninhalt1'], ['oberflaecheKugel1'], ['volumenKugel1']],
	'umfang1': [['radius1'], ['durchmesser1'], ['flaecheninhalt1'], ['kreisbogen1', 'alpha1'], ['mantelflaecheZylinder1', 'hoehe1']], // Use alpha1
	'flaecheninhalt1': [['radius1'], ['durchmesser1'], ['umfang1'], ['grundflaecheZylinder1'], ['grundflaecheKegel1'], ['kreisausschnitt1', 'alpha1']], // Use alpha1
	'radius2': [
		['durchmesser2'], ['umfang2'], ['flaecheninhalt2'], ['kreisbogen2', 'alpha2'], ['kreisausschnitt2', 'alpha2'], // Use alpha2
		['kreisausschnitt2', 'kreisbogen2'], ['oberflaecheKugel2'], ['volumenKugel2'], ['grundflaecheZylinder2'],
		['grundflaecheKegel2'], ['mantelflaecheZylinder2', 'hoehe2'], ['volumenZylinder2', 'hoehe2'],
		['mantelflaecheKegel2', 'seitenhoehe2'], ['seitenhoehe2', 'hoehe2'], ['volumenKegel2', 'hoehe2'],
		['oberflaecheZylinder2', 'hoehe2'], ['oberflaecheKegel2', 'seitenhoehe2'], ['oberflaecheKegel2', 'hoehe2']
	],
	'durchmesser2': [['radius2'], ['umfang2'], ['flaecheninhalt2'], ['oberflaecheKugel2'], ['volumenKugel2']],
	'umfang2': [['radius2'], ['durchmesser2'], ['flaecheninhalt2'], ['kreisbogen2', 'alpha2'], ['mantelflaecheZylinder2', 'hoehe2']], // Use alpha2
	'flaecheninhalt2': [['radius2'], ['durchmesser2'], ['umfang2'], ['grundflaecheZylinder2'], ['grundflaecheKegel2'], ['kreisausschnitt2', 'alpha2']], // Use alpha2
	// --- Kreisteile ---
	'kreisbogen1': [['radius1', 'alpha1'], ['umfang1', 'alpha1'], ['durchmesser1', 'alpha1'], ['kreisausschnitt1', 'radius1']], // Use alpha1
	'kreisbogen2': [['radius2', 'alpha2'], ['umfang2', 'alpha2'], ['durchmesser2', 'alpha2'], ['kreisausschnitt2', 'radius2']], // Use alpha2
	'kreisausschnitt1': [['radius1', 'alpha1'], ['flaecheninhalt1', 'alpha1'], ['kreisbogen1', 'radius1']], // Use alpha1
	'kreisausschnitt2': [['radius2', 'alpha2'], ['flaecheninhalt2', 'alpha2'], ['kreisbogen2', 'radius2']], // Use alpha2
	'alpha1': [ // Now alpha1 can be calculated
		['kreisbogen1', 'radius1'], ['kreisausschnitt1', 'radius1'], ['kreisbogen1', 'umfang1'],
		['kreisausschnitt1', 'flaecheninhalt1']
	],
	'alpha2': [ // Now alpha2 can be calculated
		['kreisbogen2', 'radius2'], ['kreisausschnitt2', 'radius2'], ['kreisbogen2', 'umfang2'],
		['kreisausschnitt2', 'flaecheninhalt2']
	],
	// --- Zylinder 1 ---
	'hoehe1': [
		['mantelflaecheZylinder1', 'radius1'], ['volumenZylinder1', 'grundflaecheZylinder1'], ['volumenZylinder1', 'radius1'],
		['oberflaecheZylinder1', 'radius1'], ['seitenhoehe1', 'radius1'], ['volumenKegel1', 'grundflaecheKegel1'],
		['volumenKegel1', 'radius1'], ['mantelflaecheZylinder1', 'umfang1']
	],
	'grundflaecheZylinder1': [['radius1'], ['flaecheninhalt1'], ['volumenZylinder1', 'hoehe1'], ['oberflaecheZylinder1', 'mantelflaecheZylinder1']],
	'mantelflaecheZylinder1': [['radius1', 'hoehe1'], ['umfang1', 'hoehe1'], ['oberflaecheZylinder1', 'grundflaecheZylinder1'], ['oberflaecheZylinder1', 'radius1']],
	'oberflaecheZylinder1': [['grundflaecheZylinder1', 'mantelflaecheZylinder1'], ['radius1', 'hoehe1'], ['radius1', 'mantelflaecheZylinder1']],
	'volumenZylinder1': [['grundflaecheZylinder1', 'hoehe1'], ['radius1', 'hoehe1']],
	// --- Zylinder 2 ---
	'hoehe2': [
		['mantelflaecheZylinder2', 'radius2'], ['volumenZylinder2', 'grundflaecheZylinder2'], ['volumenZylinder2', 'radius2'],
		['oberflaecheZylinder2', 'radius2'], ['seitenhoehe2', 'radius2'], ['volumenKegel2', 'grundflaecheKegel2'],
		['volumenKegel2', 'radius2'], ['mantelflaecheZylinder2', 'umfang2']
	],
	'grundflaecheZylinder2': [['radius2'], ['flaecheninhalt2'], ['volumenZylinder2', 'hoehe2'], ['oberflaecheZylinder2', 'mantelflaecheZylinder2']],
	'mantelflaecheZylinder2': [['radius2', 'hoehe2'], ['umfang2', 'hoehe2'], ['oberflaecheZylinder2', 'grundflaecheZylinder2'], ['oberflaecheZylinder2', 'radius2']],
	'oberflaecheZylinder2': [['grundflaecheZylinder2', 'mantelflaecheZylinder2'], ['radius2', 'hoehe2'], ['radius2', 'mantelflaecheZylinder2']],
	'volumenZylinder2': [['grundflaecheZylinder2', 'hoehe2'], ['radius2', 'hoehe2']],
	// --- Kegel 1 ---
	'seitenhoehe1': [['radius1', 'hoehe1'], ['mantelflaecheKegel1', 'radius1'], ['oberflaecheKegel1', 'radius1']],
	'grundflaecheKegel1': [['radius1'], ['flaecheninhalt1'], ['volumenKegel1', 'hoehe1'], ['oberflaecheKegel1', 'mantelflaecheKegel1']],
	'mantelflaecheKegel1': [['radius1', 'seitenhoehe1'], ['radius1', 'hoehe1'], ['oberflaecheKegel1', 'grundflaecheKegel1'], ['oberflaecheKegel1', 'radius1']],
	'oberflaecheKegel1': [['grundflaecheKegel1', 'mantelflaecheKegel1'], ['radius1', 'seitenhoehe1'], ['radius1', 'hoehe1'], ['radius1', 'mantelflaecheKegel1']],
	'volumenKegel1': [['grundflaecheKegel1', 'hoehe1'], ['radius1', 'hoehe1']],
	// --- Kegel 2 ---
	'seitenhoehe2': [['radius2', 'hoehe2'], ['mantelflaecheKegel2', 'radius2'], ['oberflaecheKegel2', 'radius2']],
	'grundflaecheKegel2': [['radius2'], ['flaecheninhalt2'], ['volumenKegel2', 'hoehe2'], ['oberflaecheKegel2', 'mantelflaecheKegel2']],
	'mantelflaecheKegel2': [['radius2', 'seitenhoehe2'], ['radius2', 'hoehe2'], ['oberflaecheKegel2', 'grundflaecheKegel2'], ['oberflaecheKegel2', 'radius2']],
	'oberflaecheKegel2': [['grundflaecheKegel2', 'mantelflaecheKegel2'], ['radius2', 'seitenhoehe2'], ['radius2', 'hoehe2'], ['radius2', 'mantelflaecheKegel2']],
	'volumenKegel2': [['grundflaecheKegel2', 'hoehe2'], ['radius2', 'hoehe2']],
	// --- Kugel 1 ---
	'oberflaecheKugel1': [['radius1'], ['durchmesser1'], ['volumenKugel1']],
	'volumenKugel1': [['radius1'], ['durchmesser1'], ['oberflaecheKugel1']],
	// --- Kugel 2 ---
	'oberflaecheKugel2': [['radius2'], ['durchmesser2'], ['volumenKugel2']],
	'volumenKugel2': [['radius2'], ['durchmesser2'], ['oberflaecheKugel2']],
};

// --- DOM References ---
let inputFields = null;
let resultsTabContainer = null;
let copyFeedbackSpan = null;
let kreisForm = null;
let alpha1Input = null;
let alpha2Input = null;
let alphaErrorMessage = null;

// --- Helper Functions ---
function isNonZeroNumeric(inputElement) {
	if (!inputElement) return false;
	const value = inputElement.value.trim();
	if (value === '') return false;
	const numValue = parseFloat(value.replace(',', '.'));
	return !isNaN(numValue) && numValue !== 0;
}

function checkConditionMet(requiredInputIds) {
	return requiredInputIds.every(id => {
		const element = document.getElementById(id);
		return element && isNonZeroNumeric(element);
	});
}

function isResultInvalidNumeric(pElement) {
	const valueSpan = pElement.querySelector('.result-value');
	if (!valueSpan) return false;
	const valueText = valueSpan.textContent.trim();
	const errorValues = ['NaN', 'Infinity', '-Infinity', 'Ungültig', 'Fehler'];
	return errorValues.includes(valueText);
}

function isResultValueValid(pElement) {
	if (pElement.classList.contains('result-notice-equal') || pElement.classList.contains('result-error')) {
		return false;
	}
	const valueSpan = pElement.querySelector('.result-value');
	if (!valueSpan) return false;
	const valueText = valueSpan.textContent.trim();
	const notCalculatedOrZeroValues = ['0', '0.0', '-1', '-1.0', '', 'N/A', '---'];
	return !notCalculatedOrZeroValues.includes(valueText);
}

// --- Highlighting Logic ---
function updateInputHighlighting() {
	if (!inputFields) return;
	inputFields.forEach(targetInput => {
		const targetId = targetInput.id;
		// Skip alpha inputs if they have error class (error style takes precedence)
		if ((targetId === 'alpha1' || targetId === 'alpha2') && targetInput.classList.contains('input-error')) {
			targetInput.classList.remove('input-has-value', 'input-calculable');
			return;
		}

		let isProvided = isNonZeroNumeric(targetInput);
		let isCalculable = false;
		if (!isProvided) {
			// Check if alpha1/alpha2 can be calculated based on Kreis 1 / Kreis 2 inputs respectively
			const requirements = calculationRequirementsMap[targetId];
			if (requirements) {
				isCalculable = requirements.some(condition => checkConditionMet(condition));
			}
		}
		targetInput.classList.toggle('input-has-value', isProvided);
		targetInput.classList.toggle('input-calculable', !isProvided && isCalculable);
		// Ensure classes are removed if conditions aren't met
		if (!isProvided) targetInput.classList.remove('input-has-value');
		if (isProvided || !isCalculable) targetInput.classList.remove('input-calculable');

	});
}

function updateResultHighlighting() {
	if (!resultsTabContainer) return;
	const resultParagraphs = resultsTabContainer.querySelectorAll('p[id^="result-"]');
	resultParagraphs.forEach(pElement => {
		pElement.classList.remove('result-error', 'result-calculated');
		if (isResultInvalidNumeric(pElement)) {
			pElement.classList.add('result-error');
		} else if (isResultValueValid(pElement) && !pElement.classList.contains('result-notice-equal')) {
			pElement.classList.add('result-calculated');
		}
	});
}

// --- Tab Switching Logic ---
function showTab(tabContentId, clickedButton) {
	const nav = clickedButton.closest('.tab-nav');
	if (!nav) { console.error("Tab nav not found"); return; }
	const contentContainer = nav.nextElementSibling;
	if (!contentContainer || !contentContainer.classList.contains('tab-content-container')) {
		console.error("Tab content container not found"); return;
	}
	nav.querySelectorAll('.tab-button').forEach(button => button.classList.remove('active'));
	contentContainer.querySelectorAll('.tab-content').forEach(tab => tab.classList.remove('active'));
	const selectedTab = contentContainer.querySelector('#' + tabContentId);
	if (selectedTab) { selectedTab.classList.add('active'); }
	else { console.error("Tab content not found:", tabContentId); }
	if (clickedButton) { clickedButton.classList.add('active'); }
}
function showInputTab(tabContentId, clickedButton) { showTab(tabContentId, clickedButton); }
function showResultTab(tabContentId, clickedButton) { showTab(tabContentId, clickedButton); }

// --- Copy Functionality ---
function handleCopyClick(event) {
	const button = event.target.closest('.copy-button');
	if (!button) return;
	const targetId = button.dataset.copyTargetId;
	if (!targetId) return;
	const resultElement = document.getElementById(targetId);
	if (!resultElement) { console.error('Copy target element not found:', targetId); return; }
	const valueSpan = resultElement.querySelector('.result-value');
	if (!valueSpan) { console.error('Value span not found within:', targetId); return; }
	const textToCopy = valueSpan.textContent.trim();
	navigator.clipboard.writeText(textToCopy).then(() => {
		button.classList.add('copied');
		setTimeout(() => button.classList.remove('copied'), 1500);
	}).catch(err => {
		console.error('Fehler beim Kopieren: ', err);
		alert('Kopieren fehlgeschlagen.');
		button.classList.remove('copied');
	});
}

// --- Alpha Validation Function ---
function validateAlphaValues(event) {
	if (!alpha1Input || !alpha2Input || !alphaErrorMessage) {
		console.error("Alpha input fields or error message div not found.");
		return true; // Allow submission if elements aren't found
	}

	// Use parseFloat to handle potential decimal commas and compare numbers
	const alpha1Num = parseFloat(alpha1Input.value.trim().replace(',', '.'));
	const alpha2Num = parseFloat(alpha2Input.value.trim().replace(',', '.'));

	// Check if values are actually numbers after potential parseFloat
	const alpha1IsNum = !isNaN(alpha1Num);
	const alpha2IsNum = !isNaN(alpha2Num);

	let isValid = true;
	let errorMessageText = "";

	// Case 1: Both have valid numbers, they must match
	if (alpha1IsNum && alpha2IsNum && alpha1Num !== alpha2Num) {
		isValid = false;
		errorMessageText = "Fehler: Die Alpha-Werte in Kreis 1 und Kreis 2 müssen übereinstimmen!";
	}
	// Case 2: One has a valid number, the other is empty or not a number
	// (Allowing one to be empty while the other has a value might be desired,
	// depending on how the backend handles it. This check prevents it.)
	// else if ((alpha1IsNum && !alpha2IsNum) || (!alpha1IsNum && alpha2IsNum)) {
	//    // If alpha1 has a value but alpha2 doesn't (or vice versa)
	//    if(alpha1Input.value.trim() !== '' || alpha2Input.value.trim() !== ''){
	//        isValid = false;
	//        errorMessageText = "Fehler: Bitte geben Sie den Alpha-Wert für beide Kreise ein oder lassen Sie beide leer/ungültig.";
	//    }
	//}
	// Simplified: Only fail if both have DIFFERENT valid numbers. Otherwise let it pass.
	// Backend should handle the case where only one alpha is provided if needed.

	alpha1Input.classList.toggle('input-error', !isValid);
	alpha2Input.classList.toggle('input-error', !isValid);

	if (!isValid) {
		alphaErrorMessage.textContent = errorMessageText;
		alphaErrorMessage.style.display = 'block';
		if (event) { // Prevent actual form submission if called from the event
			event.preventDefault();
		}
		// Switch to the tabs containing the errors if they are hidden
		if (!alpha1Input.closest('.tab-content.active')) {
			showInputTab('input-tab-1', document.querySelector('.tab-nav button[onclick*="input-tab-1"]'));
		} else if (!alpha2Input.closest('.tab-content.active')) {
			showInputTab('input-tab-2', document.querySelector('.tab-nav button[onclick*="input-tab-2"]'));
		}
		// Scroll to the error message
		alphaErrorMessage.scrollIntoView({ behavior: 'smooth', block: 'center' });
		return false; // Indicate validation failure
	} else {
		alphaErrorMessage.textContent = '';
		alphaErrorMessage.style.display = 'none';
		alpha1Input.classList.remove('input-error');
		alpha2Input.classList.remove('input-error');
		return true; // Indicate validation success
	}
}


// --- Event Listeners & Initial State ---
document.addEventListener('DOMContentLoaded', () => {
	// --- Initialize DOM references ---
	inputFields = document.querySelectorAll('.tab-content-container.content input[type="text"], .tab-content-container.content input[type="number"]');
	resultsTabContainer = document.querySelector('.tab-content-container.Results');
	copyFeedbackSpan = document.getElementById('copy-feedback');
	kreisForm = document.getElementById('kreis-form');
	alpha1Input = document.getElementById('alpha1');
	alpha2Input = document.getElementById('alpha2');
	alphaErrorMessage = document.getElementById('alpha-error-message');

	// --- Add Input Event Listeners ---
	if (inputFields.length > 0) {
		inputFields.forEach(input => {
			input.addEventListener('input', updateInputHighlighting);
			input.addEventListener('blur', updateInputHighlighting);

			// Add blur listener to alpha fields to clear errors early if they now match
			if (input.id === 'alpha1' || input.id === 'alpha2') {
				input.addEventListener('blur', () => {
					const alpha1Num = parseFloat(alpha1Input?.value.trim().replace(',', '.'));
					const alpha2Num = parseFloat(alpha2Input?.value.trim().replace(',', '.'));
					const alpha1IsNum = !isNaN(alpha1Num);
					const alpha2IsNum = !isNaN(alpha2Num);

					// Clear error if both are empty OR both are valid numbers and equal
					if ((alpha1Input?.value.trim() === '' && alpha2Input?.value.trim() === '') ||
						(alpha1IsNum && alpha2IsNum && alpha1Num === alpha2Num)) {
						alphaErrorMessage.style.display = 'none';
						alpha1Input?.classList.remove('input-error');
						alpha2Input?.classList.remove('input-error');
						updateInputHighlighting(); // Re-run highlight in case calculable state changed
					}
				});
			}
		});
	} else {
		console.warn("No input fields found for highlighting listeners.");
	}

	// --- Add Form Submit Listener for Alpha Validation ---
	if (kreisForm) {
		kreisForm.addEventListener('submit', validateAlphaValues);
	} else {
		console.error("Form with id 'kreis-form' not found.");
	}

	// --- Add Copy Button Listener ---
	if (resultsTabContainer) {
		resultsTabContainer.addEventListener('click', handleCopyClick);
	} else {
		if (document.querySelector('.results-details[open]')) {
			console.warn("Results tab container (.tab-content-container.Results) not found for copy listeners.");
		}
	}

	// --- Initial State Updates ---
	updateInputHighlighting();
	updateResultHighlighting();

	// --- Initialize default tabs ---
	const firstInputNav = document.querySelector('form .tab-nav');
	if (firstInputNav) {
		const firstInputButton = firstInputNav.querySelector('.tab-button');
		const firstInputContent = firstInputNav.nextElementSibling?.querySelector('.tab-content');
		if (firstInputButton && firstInputContent) {
			if (!firstInputButton.classList.contains('active') || !firstInputContent.classList.contains('active')) {
				firstInputNav.querySelectorAll('.tab-button').forEach(btn => btn.classList.remove('active'));
				firstInputNav.nextElementSibling?.querySelectorAll('.tab-content').forEach(tab => tab.classList.remove('active'));
				firstInputButton.classList.add('active');
				firstInputContent.classList.add('active');
			}
		}
	} else { console.warn("Input tab navigation not found."); }

	const resultsDetails = document.querySelector('.results-details');
	if (resultsDetails && resultsDetails.hasAttribute('open')) {
		const firstResultNav = resultsDetails.querySelector('.tab-nav');
		if (firstResultNav) {
			const firstResultButton = firstResultNav.querySelector('.tab-button');
			const firstResultContent = firstResultNav.nextElementSibling?.querySelector('.tab-content');
			if (firstResultButton && firstResultContent) {
				if (!firstResultButton.classList.contains('active') || !firstResultContent.classList.contains('active')) {
					firstResultNav.querySelectorAll('.tab-button').forEach(btn => btn.classList.remove('active'));
					firstResultNav.nextElementSibling?.querySelectorAll('.tab-content').forEach(tab => tab.classList.remove('active'));
					firstResultButton.classList.add('active');
					firstResultContent.classList.add('active');
				}
			}
		} else { console.warn("Result tab navigation not found."); }
	}

}); // End DOMContentLoaded
// --- Requirements Map for Dreiecke (TRIANGLES) ---
const calculationRequirementsMap = {
	'a': [
		['b', 'c', 'alpha'], ['b', 'alpha', 'beta'], ['c', 'alpha', 'gamma'],
		['flaecheninhaltDreieck', 'hoeheA'],
		['umfangDreieck', 'b', 'c'],
	],
	'b': [
		['a', 'c', 'beta'], ['a', 'alpha', 'beta'], ['c', 'beta', 'gamma'],
		['flaecheninhaltDreieck', 'hoeheB'],
		['umfangDreieck', 'a', 'c'],
	],
	'c': [
		['a', 'b', 'gamma'], ['a', 'alpha', 'gamma'], ['b', 'beta', 'gamma'],
		['flaecheninhaltDreieck', 'hoeheC'],
		['umfangDreieck', 'a', 'b'],
	],
	'hoeheA': [
		['flaecheninhaltDreieck', 'a'],
		['b', 'gamma'], ['c', 'beta'],
		['a', 'b', 'c'] // SSS -> Height (JS will add equality check)
	],
	'hoeheB': [
		['flaecheninhaltDreieck', 'b'],
		['a', 'gamma'], ['c', 'alpha'],
		['a', 'b', 'c'] // SSS -> Height (JS will add equality check)
	],
	'hoeheC': [
		['flaecheninhaltDreieck', 'c'],
		['a', 'beta'], ['b', 'alpha'],
		['a', 'b', 'c'] // SSS -> Height (JS will add equality check)
	],
	'umfangDreieck': [['a', 'b', 'c']],
	'flaecheninhaltDreieck': [
		['a', 'hoeheA'], ['b', 'hoeheB'], ['c', 'hoeheC'],
		['a', 'b', 'gamma'], ['b', 'c', 'alpha'], ['a', 'c', 'beta'],
		['a', 'b', 'c'], // Heron's
	],
	'alpha': [
		['beta', 'gamma'], ['a', 'b', 'c'],
		['a', 'b', 'beta'], ['a', 'c', 'gamma'],
	],
	'beta': [
		['alpha', 'gamma'], ['a', 'b', 'c'],
		['b', 'a', 'alpha'], ['b', 'c', 'gamma'],
	],
	'gamma': [
		['alpha', 'beta'], ['a', 'b', 'c'],
		['c', 'a', 'alpha'], ['c', 'b', 'beta'],
	],
};

// --- DOM References ---
let inputFields = null;
let resultsContainer = null;
let copyFeedbackSpan = null;
let dreieckForm = null;

// --- Helper Functions ---
function getNumericValue(inputElement) {
	if (!inputElement) return NaN;
	const value = inputElement.value.trim().replace(',', '.');
	if (value === '') return NaN;
	return parseFloat(value);
}

// Checks for valid, non-zero numbers (using tolerance)
function isNonZeroNumeric(inputElement) {
	const numValue = getNumericValue(inputElement);
	const epsilon = 1E-9; // Tolerance for floating point zero comparison
	return !isNaN(numValue) && Math.abs(numValue) > epsilon;
}

// Checks if all dependencies are valid non-zero numbers
function checkConditionMet(requiredInputIds) {
	return requiredInputIds.every(id => {
		const element = document.getElementById(id);
		return element && isNonZeroNumeric(element);
	});
}

// **** NEW: Helper function to check if values of specified inputs are equal ****
function checkValuesAreEqual(ids) {
	if (!ids || ids.length < 2) {
		return true; // Or false? Let's say true for 0 or 1 ID.
	}
	const firstValue = getNumericValue(document.getElementById(ids[0]));
	// If the first value isn't a number, they can't be equal in a meaningful way here
	if (isNaN(firstValue)) {
		return false;
	}
	const epsilon = 1E-9; // Tolerance for float comparison
	for (let i = 1; i < ids.length; i++) {
		const currentElement = document.getElementById(ids[i]);
		const currentValue = getNumericValue(currentElement);
		// Fail if any subsequent value is not a number or not equal to the first
		if (isNaN(currentValue) || Math.abs(firstValue - currentValue) > epsilon) {
			return false;
		}
	}
	return true; // All were numbers and equal within tolerance
}


// --- Result Value Checking ---
function isResultInvalidNumericText(valueText) {
	const errorValues = ['NaN', 'Infinity', '-Infinity', 'Ungültig', 'Fehler'];
	return errorValues.includes(valueText) || valueText.trim() === '';
}

function isResultZeroText(valueText) {
	const numValue = parseFloat(valueText.replace(',', '.'));
	const epsilon = 1E-9;
	return !isNaN(numValue) && Math.abs(numValue) < epsilon;
}

function isResultCalculatedGreen(pElement) {
	if (pElement.classList.contains('result-error') || pElement.classList.contains('result-zero')) {
		return false;
	}
	const valueSpan = pElement.querySelector('.result-value');
	if (!valueSpan) return false;
	const valueText = valueSpan.textContent.trim();
	const notCalculatedPlaceholders = ['N/A', '---', '-1', '-1.0'];
	return !isResultInvalidNumericText(valueText) && !isResultZeroText(valueText) && !notCalculatedPlaceholders.includes(valueText);
}

// --- Highlighting Logic (REVISED) ---
function updateInputHighlighting() {
	if (!inputFields) return;
	inputFields.forEach(targetInput => {
		const targetId = targetInput.id;
		let isProvided = isNonZeroNumeric(targetInput); // Green highlight requires non-zero
		let isCalculable = false;

		// Check calculability only if not provided (is zero or empty/invalid)
		if (!isProvided) {
			const requirements = calculationRequirementsMap[targetId];
			if (requirements) {
				// Check each possible condition set
				isCalculable = requirements.some(condition => {
					// Standard check: are all required fields present and non-zero?
					let conditionMetStandard = checkConditionMet(condition);

					// **** SPECIAL CHECK FOR SSS -> Height ONLY IF Equilateral ****
					// If the target is a height AND the current condition is ['a', 'b', 'c']
					if (conditionMetStandard &&
						(targetId === 'hoeheA' || targetId === 'hoeheB' || targetId === 'hoeheC') &&
						condition.length === 3 && condition.includes('a') && condition.includes('b') && condition.includes('c')) {
						// Perform the additional equality check
						conditionMetStandard = checkValuesAreEqual(['a', 'b', 'c']);
					}
					// **** END SPECIAL CHECK ****

					return conditionMetStandard; // Return true if this condition (with potential extra checks) is met
				});
			}
		}

		// Apply classes based on combined checks
		targetInput.classList.toggle('input-has-value', isProvided);
		targetInput.classList.toggle('input-calculable', !isProvided && isCalculable);

		// Ensure classes are removed correctly if state changes
		if (!isProvided) targetInput.classList.remove('input-has-value');
		if (isProvided || !isCalculable) targetInput.classList.remove('input-calculable');
	});
}


function updateResultHighlighting() {
	if (!resultsContainer) return;
	const resultParagraphs = resultsContainer.querySelectorAll('p[id^="result-"]');

	resultParagraphs.forEach(pElement => {
		pElement.classList.remove('result-error', 'result-calculated', 'result-zero');
		const valueSpan = pElement.querySelector('.result-value');
		const valueText = valueSpan ? valueSpan.textContent.trim() : '';

		if (isResultInvalidNumericText(valueText)) {
			pElement.classList.add('result-error');
		} else if (isResultZeroText(valueText)) {
			pElement.classList.add('result-zero');
		} else if (isResultCalculatedGreen(pElement)) {
			pElement.classList.add('result-calculated');
		}
	});
}

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

// --- Event Listeners & Initial State ---
document.addEventListener('DOMContentLoaded', () => {
	// --- Initialize DOM references ---
	inputFields = document.querySelectorAll('.Dreieck input[type="text"], .Dreieck input[type="number"]');
	resultsContainer = document.querySelector('.DreieckResults');
	copyFeedbackSpan = document.getElementById('copy-feedback');
	dreieckForm = document.getElementById('dreieck-form');

	// --- Add Input Event Listeners ---
	if (inputFields.length > 0) {
		inputFields.forEach(input => {
			if (input.id !== 'decimalPlaces') {
				input.addEventListener('input', updateInputHighlighting);
				input.addEventListener('blur', updateInputHighlighting);
			}
		});
	} else {
		console.warn("No input fields found for highlighting listeners.");
	}

	// --- (Optional) Add Form Submit Listener ---
	// if (dreieckForm) {
	//     dreieckForm.addEventListener('submit', (event) => { /* Validation? */ });
	// }

	// --- Add Copy Button Listener ---
	if (resultsContainer) {
		resultsContainer.addEventListener('click', handleCopyClick);
	} else {
		if (document.querySelector('.results-details[open]')) {
			console.warn("Results container (.DreieckResults) not found for copy listeners.");
		}
	}

	// --- Initial State Updates ---
	updateInputHighlighting();
	updateResultHighlighting();

}); // End DOMContentLoaded
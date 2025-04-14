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
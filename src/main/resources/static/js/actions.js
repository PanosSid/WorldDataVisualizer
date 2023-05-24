var numberOfPairs = 1;

$(document).ready(function() {
			
			$('.addPair').click(function() {
				
				var indicatorOptionsHtml = '<option value="" selected hidden>Indicator</option>'; 
				for (let indicator of indicatorsList)
					indicatorOptionsHtml += '<option value="'+indicator+'">'+indicator+'</option>';
						
				var countryOptionsHtml = '<option value="" selected hidden>Country</option>';
				for (let country of countriesList)
					countryOptionsHtml += '<option value="'+country+'">'+country+'</option>';

				var pairHtml = 
				'<div id="pairs">'+
					'<div class="form-row mb-2">'+
						'<div class="col">'+
							'<select class="form-control" id=countrySelect' + numberOfPairs + ' name="country">'+
							countryOptionsHtml+
							'</select>'+
						 '</div>' +
						'<div class="col">'+
		                	'<select class="form-control" id=indicatorSelect' + numberOfPairs + ' name="indicator">'+
		                    	indicatorOptionsHtml+
		                    '</select>'+
		                 '</div>'+
						'<div class="col-auto">' +
							'<button type="button" class="btn btn-danger removePair">&times;</button>' +
						'</div>' +
					'</div>' +
				'</div>';

				$('#pairs').append(pairHtml);
				
				numberOfPairs += 1;
			});

			$(document).on('click', '.removePair', function() {
				$(this).closest('.form-row').remove();
			});

			$('form').submit(function(event) {
				event.preventDefault();

				var chartType = $('#chartType').val();
				var minYear = $('input[name="number1"]').val();
				var maxYear = $('input[name="number2"]').val();
				var aggregate = $('select[name="selectChoice"]').val();

				var formData = {
						  "chartType": chartType,
						  "countries": [],
						  "indicators": [],
						  "minYear": minYear,
						  "maxYear": maxYear,
						  "aggregate": aggregate,
				};
				
				$('#pairs .form-row').each(function() {
					var country = $(this).find('select[name="country"]').val();
				    var indicator = $(this).find('select[name="indicator"]').val();
				    if (indicator && country){
				    	formData.countries.push(country);
				    	formData.indicators.push(indicator);
				    }
				});
				
				if (chartType == "scatter"){
					if (formData.countries.length % 2 != 0){
						alert("To generate a scatter plot, it is necessary to select two pairs of countries and indicators," +
								" where the countries chosen for comparison are the same.");
						return;
					}
					if (formData.countries[0] != formData.countries[1]){
						alert("To generate a Scatter Chart the selected countries should be the same!");
						return;
					}
					
				}
				
				fetch('/generateChart', {
					method: 'POST',
					headers: {'Content-Type': 'application/json'},
					body: JSON.stringify(formData)
				}).then(response => {
					if (!response.ok) {
						throw new Error('Network response was not ok');
					}
					window.location.href = response.url; // Redirect to the new URL
				}).catch(error => {
					console.error('There was an error:', error);
				});
				
			});
			
			/*for*/
			document.getElementById('countrySelect0').addEventListener('change', function() {
		        indicatorsList = countryIndicatorsMap[this.value];
				indicatorSelect = document.getElementById('indicatorSelect0');
		        while (indicatorSelect.firstChild) {
		            indicatorSelect.removeChild(indicatorSelect.firstChild);
		        } // Clear existing options
		        for (let i = 0; i < indicatorsList.length; i++) {
		            option = document.createElement('option');
		            option.value = indicatorsList[i];
		            option.text = indicatorsList[i];
		            indicatorSelect.appendChild(option);
		        } // Add options from the indicatorsList
		    });
			
		});
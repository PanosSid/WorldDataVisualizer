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
					'<select class="form-control" name="country">'+
					countryOptionsHtml+
					'</select>'+
				 '</div>' +
				'<div class="col">'+
                	'<select class="form-control" name="indicator">'+
                    	indicatorOptionsHtml+
                    '</select>'+
                 '</div>'+
				'<div class="col-auto">' +
					'<button type="button" class="btn btn-danger removePair">&times;</button>' +
				'</div>' +
			'</div>' +
		'</div>';

		$('#pairs').append(pairHtml);
	});

	$(document).on('click', '.removePair', function() {
		$(this).closest('.form-row').remove();
	});

	$('form').submit(function(event) {
		event.preventDefault();

		var chartType = $('#chartType').val();
		var pairsList = [];

		$('#pairs .form-row').each(function() {
			var country = $(this).find('select[name="country"]').val();
		    var indicator = $(this).find('select[name="indicator"]').val();
		    if (indicator && country)
		        pairsList.push([country, indicator]);
		});
		
		fetch('/generateChart', {
			  method: 'POST',
			  headers: {
			    'Content-Type': 'application/json'
			  },
			  body: JSON.stringify(pairsList)
			})
			.then(response => {
			  if (!response.ok) {
			    throw new Error('Network response was not ok');
			  }
			  return response.json();
			})
			.then(data => {
			  // handle the response data
			})
			.catch(error => {
			  console.error('There was an error:', error);
			});
		
		//alert('Chart type: ' + chartType + '\nPairs: ' + JSON.stringify(pairsList));
		
	});
});
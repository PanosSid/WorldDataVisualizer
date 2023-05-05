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
		    var indicator = $(this).find('select[name="indicator"]').val();
		    var country = $(this).find('select[name="country"]').val();
		    if (indicator && country)
		        pairsList.push([indicator, country]);
		});

//		for (var i = 0; i < pairsList.length; i++) {
//		    var pair = pairsList[i];
//		    var input1 = $('<input type="hidden" name="pairsList[' + i + '][0]" value="' + pair[0] + '">');
//		    var input2 = $('<input type="hidden" name="pairsList[' + i + '][1]" value="' + pair[1] + '">');
//		    $('form').append(input1);
//		    $('form').append(input2);
//		}
		
		fetch('/generateChart', {
			  method: 'POST',
			  headers: {
			    'Content-Type': 'application/json'
			  },
			  body: pairsList
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
		
		alert('Chart type: ' + chartType + '\nPairs: ' + JSON.stringify(pairsList));
		
	});
});
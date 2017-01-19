function getUsers() {
	
	$.get( "/mall/users")
	 .done(function(r) {
		var tbody = $('table > tbody').clone();
		tbody.find('tr').remove();
		var tr = $('table > tbody > tr');
		$('table > tbody').remove();
		
		var count = 0;
		r.forEach(function(user) {
			if(count%2 === 0){
				$(tr).css("background-color", "#bbf");
				count=count+1;
			}else{
				$(tr).css("background-color", "#ffc")
				count=count+1;
			}
			tr.clone().find('td').each(function(index, elem) {
				$(elem).text(user[['id', 'nickName', 'gender', 'email'][index]]);
			}).parent()
			  .click(function() {
				   window.location.href = '/mall/static/user.html?id=' + user.id;
				   window.location.reload;
			   })
			  .mouseenter(function() {
				  $(this).addClass('active');
			  })
			  .mouseleave(function() {
				  $(this).removeClass('active');
			  })
			   .appendTo(tbody);
			
			
		});
		$('table').append(tbody);
		
		
		
		
		
	 }) 	 
	.fail(function( xhr, status, errorThrown ){
		var message = xhr.status + ' ' + xhr.responseJSON.errorMessage;
		$("<div>")
		.text("message")
		.addClass("alert alert-danger")
		. appendTo(".container");
	});
	
	
}
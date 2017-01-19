function getUserId() {
	var searchStr = window.location.search;
	if(searchStr.length===0) {
		return -1;
	}
	var param = 
		searchStr.slice(1).split('&')
		.map(str => str.split('='))
		.find(v=> v[0] ==='id');
	if(!param) {
		return -1;
	}
	return param[1]-0;
}
function fetch(id) {
	$.get("/mall/users/"+id,function(r) {
		if(id!=r.id) {
			console.error("wrong")
		}		
		response = r;
		$('#user_id').val(r.id);
		$('#nick_name').text(r.nickName);
		$('#email').text(r.email);
		$('#gender').text(r.gender);
		if(r.defaultAddress==null){
			$('#address').text("")	;
			$('#telephone').text("");
		}else{
			$('#address').text(r.defaultAddress.address);
			$('#telephone').text(r.defaultAddress.telephone);			
		}
		$('.form-horizontal.hide').removeClass('hide');
	})	
.fail(function(xhr,status,errorThrown) {
		var message = xhr.status +' '+xhr.responseJSON.errorMessage;
		$('.form-horizontal').addClass('hide');
		$('.alert.alert-danger').text(message).removeClass('hide');
	});	
}
$("#confirm").click(function() {
	$.ajax({
		url:'/mall/users/'+getUserId(),
		type:"DELETE",
		data:{}	
	})
	window.location.href = '/mall/static/users.html?';
	
})
$('#myModal').on('show.bs.modal', function (e) {
		var text = $('#myModal .modal-body').text()
		.replace('xxx',$('#nick_name').text());
		$('#myModal .modal-body').text(text);	
	});
function getUser(){
	var id = getUserId();
	if(id>0) {
		fetch(id);
	}else{
		console.error('wrong user id:',id);
	}
}
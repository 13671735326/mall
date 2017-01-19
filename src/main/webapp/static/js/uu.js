function getUsers() {
	var page = getPage();
	var size= getSize();

	$.get("/mall/users/?currentPage="+page+"&pageSize="+size)
	 .done(function(r) {
			aaa(r)
			 var qwe=r[0].count
		 var bottonNum=Math.ceil(qwe/size);
			for(let i=1;i<=bottonNum;i++) {
				$('.btn-toolbar')
				.append($('#botton')
						.text(i)
						.clone().click(function() {
							window.location.href = '/mall/static/userss.html?currentPage='+i+'&pageSize='+size;
							window.location.reload;
						})
				)
			} 
			$('#botton').remove();
			$('#dq').text("当前页"+page+'/'+bottonNum)
		if(page<bottonNum){
		$('#btn3').click(function() {
			window.location.href =  '/mall/static/userss.html?currentPage='+(page+1)+'&pageSize='+size;
		})
		}
		$('#btn1').click(function(){
			window.location.href =  '/mall/static/userss.html?currentPage='+1+'&pageSize='+size;
		})
//		else{
//			$('#btn3').click(function() {
//				window.location.href =  '/mall/static/userss.html?currentPage='+page+'&pageSize='+size;
//			})
//		}
		
		if(page>1){
		$('#btn2').click(function() {
			window.location.href =  '/mall/static/userss.html?currentPage='+(page-1)+'&pageSize='+size;
		})
		}
		$('#btn4').click(function() {
			window.location.href =  '/mall/static/userss.html?currentPage='+bottonNum+'&pageSize='+size;
		})
		
		$('#btn5').click(function() {
			var a = document.getElementById("changePage").value;
			if(a>0&&a<bottonNum){
				window.location.href =  '/mall/static/userss.html?currentPage='+a+'&pageSize='+size;
			}else{
				confirm('请输入正确页面');


			}
		})
//		else{
//			$('#btn2').click(function() {
//				window.location.href =  '/mall/static/userss.html?currentPage='+page+'&pageSize='+size;
//		})
//		
//	 }
	 }) 	 
	.fail(function( xhr, status, errorThrown ){
		var message = xhr.status + ' ' + xhr.responseJSON.errorMessage;
		$("<div>")
		.text("message")
		.addClass("alert alert-danger")
		. appendTo(".container");
	});
	
  

}
function aaa(r) {
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
}
function getPage() {
	var searchStr = window.location.search;
	if(searchStr.length===0) {
		return -1;
	}
	var param = 
		searchStr.slice(1).split('&')
		.map(str => str.split('='))
		.find(v=> v[0] ==='currentPage');
	if(!param) {
		return -1;
	}
	return param[1]-0;
}
function getSize() {
	var searchStr = window.location.search;
	if(searchStr.length===0) {
		return -1;
	}
	var param = 
		searchStr.slice(1).split('&')
		.map(str => str.split('='))
		.find(v=> v[0] ==='pageSize');
	if(!param) {
		return -1;
	}
	return param[1]-0;
}


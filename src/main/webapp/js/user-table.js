/*$(document).ready(function() {
	$('.btn-xoa').click(function() {
		var id = $(this).attr('id-user')
		$.ajax({
			method: "GET",
			url: 'http://localhost:8080/crm06/api/user?id=${id}',
			//data: { name: "John", location: "Boston" }
		})
			.done(function(result) {
				if(result.data){
					alert("Xoa Thanh Cong")
				}else{
					alert("Xoa That Bai")
				}
			});
	}

	)
})*/

$(document).ready(function() {
	$('.btn-xoa').click(function() {
		var id = $(this).attr('id-user');
		var This = $(this);
		$.ajax({
			method: "GET",
			// gọi API
			url: 'http://localhost:8080/crm06/api/user?id=' + id,
			//data: { name: "John", location: "Boston" }
		})
		.done(function(result) {
			if(result.data){
				//alert("Xoa Thanh Cong");
				This.closest('tr').remove()
			}else{
				alert("Xoa That Bai");
			}
		});
	});
});

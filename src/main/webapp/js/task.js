$(document).ready(function() {
	$('.btn-xoa').click(function() {
		var id = $(this).attr('id-task');
		var This = $(this);
		$.ajax({
			method: "GET",
			// gọi API
			url: 'http://localhost:8080/crm06/api/task?idTask=' + id,
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
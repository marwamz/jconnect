$(document).ready(function(){
	//chercher user
	$(document).on("keyup","#search",function(){
		var searchUser=$(this).val();
		$.ajax({
			url:"/admin/user/searchUser?userSearch="+searchUser,
			success: function(data){
				
				//console.log(data);
					$("#bodyTab").empty();
					$(data).hide().appendTo("#bodyTab").fadeIn(500);
				
			
			}
			
		});
	});
	
	
	//chercher pilote
	$(document).on("keyup","#piloteSearch",function(){
		var searchUser=$(this).val();
		$.ajax({
			url:"/admin/pilote/searchPilote?piloteSearch="+searchUser,
			success: function(data){
				
				//console.log(data);
					$("#bodyTab").empty();
					$(data).hide().appendTo("#bodyTab").fadeIn(500);
				
			
			}
			
		});
	});
	
	$('.datepicker').datetimepicker(); 
	 
	 $(':checkbox').on('change',function(){
		 var th = $(this), name = th.prop('name'); 
		 if(th.is(':checked')){
		     $(':checkbox[name="'  + name + '"]').not($(this)).prop('checked',false);   
		  }
		});
	
});


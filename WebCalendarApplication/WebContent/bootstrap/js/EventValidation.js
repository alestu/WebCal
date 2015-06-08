$(document).ready(function()
{
	
});

function Validate()
{
	var error= false;
	$("#txtTitle").removeClass("eventError");
	$("#txtDescription").removeClass("eventError");
	$("#txtPlace").removeClass("eventError");
	
	if($("#txtTitle").val() == "")
	{
	
		$("#txtTitle").addClass("eventError");
	
		error= true;
	}
	if($("#txtDescription").val() == "")
	{
	
		$("#txtDescription").addClass("eventError");
		error= true;
	}
	if($("#txtPlace").val() == "")
	{
	
		$("#txtPlace").addClass("eventError");
		error= true;
	}
	
	if(error)
	{
		return true;
	}
	else
	{	
		return false;
	}
	

	
}
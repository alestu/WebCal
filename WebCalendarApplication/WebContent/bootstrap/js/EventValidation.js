$(document).ready(function()
{
	$("#txtTitle").change(function()
			{
		if($("#txtTitle").val() != "")
		{
			$("#txtTitle").removeClass("eventError");
		}
		else
		{
			$("#txtTitle").addClass("eventError");
		}
		
	});
	
	$("#txtDescription").change(function()
			{
		if($("#txtDescription").val() != "")
		{
			$("#txtDescription").removeClass("eventError");
		}
		else
		{
			$("#txtDescription").addClass("eventError");
		}
		
	});
	
	$("#txtPlace").change(function()
			{
		if($("#txtPlace").val() != "")
		{
			$("#txtPlace").removeClass("eventError");
		}
		else
		{
			$("#txtPlace").addClass("eventError");
		}
		
	});
	
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
		return false;
	}
	else
	{	
		return true;
	}
	

	
}
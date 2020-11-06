//global XML request variable
var xhr

function loadPost()
{
	xhr = GetXmlHttpObject();
	if (xhr == null)
	{
		alert("Your browser does not support XMLHTTP!");
		return;
	}
	
	// Backend handler 

}

function stateChanged() // On return, change the DOM with the response text
{
	if (xhr.readyState == 4)
	{
		document.getElementById("a_v1").innerHTML = xhr.responseText;
	}
}

function GetXmlHttpObject()
{
	//IE7+, Firefox, Chrome, Opera, Safari
	if (window.XMLHttpRequest)
	{
		return new XMLHttpRequest();
	}
	//IE6, IE5
	if (window.ActiveXObject)
	{
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	return null;
}

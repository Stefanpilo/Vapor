function loadVideogiocoSinglePageScript() {
	let IDVideogioco = new URLSearchParams(window.location.search).get('id');
	
	//pulisci i cookie

	let jsonToSend= {
		"query type" : "select by id",
		"DAO type" : "VideogiocoDAO",
		"ID" : IDVideogioco
	}

	let xhr = createXMLHTTPRequest();
	xhr.open("get", ClienteServlet + "?dati=" + encodeURIComponent(JSON.stringify(jsonToSend)), true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				let responseContent = JSON.parse(xhr.responseText);
				document.getElementById("immagine").src = responseContent.immagine;
				document.getElementById("titolo").innerHTML = responseContent.titolo;
				document.getElementById("prezzo").innerHTML = responseContent.prezzo;
				document.getElementById("sconto").innerHTML = responseContent.sconto;
				document.getElementById("descrizione").innerHTML = responseContent.descrizione;
				document.getElementById("categoria").innerHTML = responseContent.categoria;
				document.getElementsByTagName("title")[0].innerHTML = responseContent.titolo;
			}
		}
	}
	xhr.send();
}
document.addEventListener("DOMContentLoaded", loadVideogiocoSinglePageScript);


let ClienteServlet = "/Vapor/ClienteServlet";
function createXMLHTTPRequest() {
	let request;
	try {
		//Firefox 1+, Chrome 1+, Opera 8+, Safari 1.2+, Edge 12+, Internet Explorer 7+
		request = new XMLHttpRequest();
	}
	catch(e) {
		try {
			//past versions of Internet Explorer
			request = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch(e) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e) {
				alert("Il browser non supporta AJAX");
				return null;
			}
		}
	}
		
	return request;
}
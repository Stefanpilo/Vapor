document.addEventListener("DOMContentLoaded" , startScript);
			
function startScript(){
	let aggiungiVideogioco_form = document.getElementById("aggiungiVideogioco_form");
	let messageViewer = document.getElementById("messageViewer");
	document.getElementById("aggiungiVideogioco_button").addEventListener('click', () => {
		messageViewer.style.display = "none";
		document.getElementById("aggiungiVideogioco_form").style.display = "block";
	});		
	
	aggiungiVideogioco_form.submit_button.addEventListener('click', () => {

		let videogioco = {
			immagine : aggiungiVideogioco_form.immagine.value,
			titolo : aggiungiVideogioco_form.titolo.value,
			prezzo : aggiungiVideogioco_form.prezzo.value,
			sconto : aggiungiVideogioco_form.sconto.value,
			descrizione : aggiungiVideogioco_form.descrizione.value,
			categoria : aggiungiVideogioco_form.categoria.value
		}
		
		if (!videogioco.immagine.match(/^\/Vapor\/images\/[^/]+\.[a-zA-Z0-9]+$/)) {
			aggiungiVideogioco_form.immagine.setCustomValidity("Immagine deve essere della forma: /Vapor/images/...");
			aggiungiVideogioco_form.immagine.reportValidity();
			return;
		}
		else if (videogioco.titolo.length > 50 || videogioco.titolo.length == 0) {
			aggiungiVideogioco_form.titolo.setCustomValidity("Titolo deve avere da 0 a 50 caratteri");
			aggiungiVideogioco_form.titolo.reportValidity();
			return;
		}
		else if (!videogioco.prezzo.match(/^\d{1,10}(?:[.,]\d{1,2})?$/)) {
			aggiungiVideogioco_form.prezzo.setCustomValidity("Prezzo accetta massimo 10 cifre prima del punto/virgola, e massimo due cifre decimali");
			aggiungiVideogioco_form.prezzo.reportValidity();
			return;
		}
		else if (!videogioco.sconto.match(/^\d{1,5}(?:[.,]\d{1,2})?$/)) {
			aggiungiVideogioco_form.sconto.setCustomValidity("Sconto accetta massimo 5 cifre prima del punto/virgola, e massimo due cifre decimali");
			aggiungiVideogioco_form.sconto.reportValidity();
			return;
		}

		let xhr = createXMLHTTPRequest();
		if (!xhr)
			return;
		
		xhr.open(aggiungiVideogioco_form.method, aggiungiVideogioco_form.action, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				messageViewer.style.display = "block";
				messageViewer.innerHTML = "Videogioco inserito nel DB con successo";
			}
			else if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 400) {
				messageViewer.style.display = "block";
				messageViewer.innerHTML = "Errore con l'inserimento nel DB"
			}
		};
		let jsonToSend = {
			"query type" : "insert",
			"videogioco" : videogioco
		}
		xhr.send(JSON.stringify(jsonToSend));

	});	
}



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
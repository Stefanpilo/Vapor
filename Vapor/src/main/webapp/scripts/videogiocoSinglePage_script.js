function loadVideogiocoSinglePageScript() {
	let videogioco;
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
				videogioco = JSON.parse(xhr.responseText);
				document.getElementById("aggiungiAlCarrello").setAttribute("data-idvideogioco", IDVideogioco);
				document.getElementById("immagine").src = videogioco.immagine;
				document.getElementById("titolo").innerHTML = videogioco.titolo;
				if (videogioco.disponibile) {
						document.getElementById("prezzoSpan").innerHTML = "€ " + videogioco.prezzo;
						document.getElementById("prezzoInput").value = videogioco.prezzo;
						document.getElementById("scontoInput").value = videogioco.sconto;
					if (videogioco.sconto > 0) {
						let prezzo = (videogioco.prezzo/100*(100-videogioco.sconto)).toFixed(2);
						document.getElementById("prezzoSpan").innerHTML = "€ " + prezzo;
						document.getElementById("scontoSpan").innerHTML = videogioco.sconto + "% off →";
					}
					else
						document.getElementById("scontoSpan").style.display = "none";
				}
				else {
					document.getElementById("prezzoSpan").innerHTML = "Non disponibile";
					document.getElementById("aggiungiAlCarrello").style.display = "none";
					document.getElementById("rimuoviDalCatalogo_button").style.display = "none";
					document.getElementById("aggiungiAlCatalogo_button").style.display = "inline";
					document.getElementById("updateCommandsContainer").style.display = "none";
				}
				document.getElementById("descrizione").innerHTML = videogioco.descrizione;
				document.getElementById("categoria").innerHTML = videogioco.categoria;
				document.getElementsByTagName("title")[0].innerHTML = videogioco.titolo;

				if (document.getElementById("videogiocoInfoContainer").dataset.isadmin === "true") {
					document.getElementById("adminCommands").style.display = "inline";

				}
			}
		}
	};
	xhr.send();
	
	document.getElementById("aggiungiAlCarrello").addEventListener('click', () => {
		let jsonToSend = {
			"query type" : "insert product",
			"id" : event.target.dataset.idvideogioco
		};

		let xhr = createXMLHTTPRequest();
		xhr.open("post", "/Vapor/CarrelloServlet", true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					document.getElementById("aggiuntaAlCarrelloResult").innerHTML = xhr.responseText;
				}
			}
		};
		xhr.send(JSON.stringify(jsonToSend));
	});

	document.getElementById("rimuoviDalCatalogo_button").addEventListener('click', () => {
		let jsonToSend = {
			"query type" : "remove disponibile",
			"videogioco" : videogioco
		};

		let xhr = createXMLHTTPRequest();
		xhr.open("post", "/Vapor/AdminServlet", true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					location.reload(true);
				}
			}
		};
		xhr.send(JSON.stringify(jsonToSend));
	});

	document.getElementById("aggiungiAlCatalogo_button").addEventListener('click', () => {
		let jsonToSend = {
			"query type" : "add disponibile",
			"videogioco" : videogioco
		};

		let xhr = createXMLHTTPRequest();
		xhr.open("post", "/Vapor/AdminServlet", true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					location.reload(true);
				}
			}
		};
		xhr.send(JSON.stringify(jsonToSend));
	});

	document.getElementById("updatePrezzo").addEventListener('click', () => {
		let videogioco2 = Object.assign({}, videogioco);
		let prezzoInput = document.getElementById("prezzoInput");
		let scontoInput = document.getElementById("scontoInput");
		videogioco2.prezzo = prezzoInput.value;
		videogioco2.sconto = scontoInput.value;
		console.log(videogioco2.prezzo);
		console.log(videogioco2.sconto);

		if (videogioco2.prezzo <= 0) {
			prezzoInput.setCustomValidity("Il prezzo deve essere maggiore di 0");
			prezzoInput.reportValidity();
			return;
		}
		if(videogioco2.sconto <0) {
			scontoInput.setCustomValidity("Sconto deve essere maggiore o uguale a 0");
			scontoInput.reportValidity();
			return;
		}

		let jsonToSend = {
			"query type" : "update videogioco",
			"videogioco" : videogioco2
		};

		let xhr = createXMLHTTPRequest();
		xhr.open("post", "/Vapor/AdminServlet", true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					location.reload(true);
				}
			}
		};
		xhr.send(JSON.stringify(jsonToSend));
	});
}
document.addEventListener("DOMContentLoaded", loadVideogiocoSinglePageScript);


let IDVideogioco = new URLSearchParams(window.location.search).get('id');
let ClienteServlet = "/Vapor/ClienteServlet";
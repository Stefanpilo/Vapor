function loadCatalogoScript() {
	addListenerToGames();
	addListenerToRadioButtons();
}
document.addEventListener('DOMContentLoaded', loadCatalogoScript);

function addListenerToRadioButtons() {
	document.getElementById("filterContainer").querySelector("input").addEventListener('change', ()=> {
		document.querySelectorAll(".videogiocoContainer").forEach( (currentVideoContainer) => {
			currentVideoContainer.style.display = "block";
		});
	});

	document.getElementById("filterContainer").querySelectorAll("input:not(:first-child)").forEach( (radioBtn) => {
		radioBtn.addEventListener('change', () => {
			//nascondi tutti i container videogioco che non sono della categoria del radio button clickato
			document.querySelectorAll(".videogiocoContainer").forEach( (currentVideogiocoContainer) => {
				if (currentVideogiocoContainer.classList.contains(event.target.value))
					currentVideogiocoContainer.style.display = "block";
				else
					currentVideogiocoContainer.style.display = "none";
			});
		});
	});
}

function addListenerToGames() {
	document.querySelectorAll(".videogiocoContainer").forEach ( (currentVideoContainer) => {
		currentVideoContainer.addEventListener('click', () => {
			window.location.href = "/Vapor/jsp/videogiocoSinglePage.jsp?id=" + event.target.dataset.idvideogioco;
		});
	});
}
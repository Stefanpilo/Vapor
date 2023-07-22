function loadCatalogoScript() {
	addListenerToGames();
	addListenerToRadioButtons();
}
document.addEventListener('DOMContentLoaded', loadCatalogoScript);

function addListenerToRadioButtons() {
	document.getElementById("filterContainer").querySelectorAll("input").forEach( (radioBtn) => {
		radioBtn.addEventListener('change', () => {
			//nascondi tutti i container videogioco che non sono della categoria del radio button clickato
			document.querySelectorAll(".videogiocoContainer").forEach( (currentVideoContainer) => {
				if (currentVideoContainer.classList.contains(event.target.value))
					currentVideoContainer.style.display = "block";
				else
					currentVideoContainer.style.display = "none";
			})
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
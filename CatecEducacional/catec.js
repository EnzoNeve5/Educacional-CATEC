function aparecerPopup() {
  document.getElementById('meusegundoPopUp').style.display = 'block';
}

function desabrirPopup() {
  document.getElementById('meusegundoPopUp').style.display = 'none';
}

window.onclick = function(event) {
  var secondpopup = document.getElementById('meusegundoPopUp');
  if (event.target == secondpopup) {
    secondpopup.style.display = 'none';
  }
}
  
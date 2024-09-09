function mostrarPopup() {
  document.getElementById('meuPopUp').style.display = 'block';
}
  
function fecharPopup() {
  document.getElementById('meuPopUp').style.display = 'none';
}
  
window.onclick = function(event) {
  var popup = document.getElementById('meuPopUp');
  if (event.target == popup) {
    popup.style.display = 'none';
  }
}
  
function mostrarPopup() { // Declara uma função chamada mostrarPopup.
  document.getElementById('meuPopUp').style.display = 'block'; // Obtém o elemento com o ID 'meuPopUp' e define seu estilo de exibição como 'block' (tornando-o visível).
}
  
function fecharPopup() { // Declara uma função chamada fecharPopup.
  document.getElementById('meuPopUp').style.display = 'none'; // Obtém o elemento com o ID 'meuPopUp' e define seu estilo de exibição como 'none' (ocultando-o).
}
  
window.onclick = function(event) { // Define uma função a ser executada quando um clique ocorre em qualquer lugar da janela.
  var popup = document.getElementById('meuPopUp'); // Obtém o elemento com o ID 'meuPopUp'.
  if (event.target == popup) { // Verifica se o elemento clicado (event.target) é o próprio popup.
    popup.style.display = 'none'; // Se for, oculta o popup.
  }
}
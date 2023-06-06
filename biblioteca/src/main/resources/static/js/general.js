


$(function(){

$("figure").hover(
function () {
  $(this).css("z-index", "20");
 
  $("#tapar").css("opacity", "1");
  $("#tapar").css("display", "block");
},function () {
  $(this).css("z-index", "auto");
  $("#tapar").css("display", "none");
  $("#tapar").css("opacity", "0");
}
);

    $("#btnMenuMovil").click(function() {
        $("#menuMovil").toggleClass("active");
      });
      $("aside>img").click(function() {
        $("#menuMovil").toggleClass("active");
      });

})

// Obtener elementos del DOM
const main = document.querySelector('main');
const footer = document.querySelector('footer');
const body = document.querySelector('body');

// Función para comprobar la altura del contenido y ajustar el footer
function ajustarFooter() {
  if (main.offsetHeight + footer.offsetHeight < window.innerHeight) {
    footer.style.position = 'fixed';
    body.style.paddingBottom = `${footer.offsetHeight}px`;
  } else {
    footer.style.position = 'static';
    body.style.paddingBottom = 0;
  }
}

// Ajustar el footer al cargar la página
ajustarFooter();

// Ajustar el footer al redimensionar la ventana
window.addEventListener('resize', ajustarFooter);


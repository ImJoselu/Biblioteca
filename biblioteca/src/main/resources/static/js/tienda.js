var filtroAutor = document.getElementById("filtroAutor");
var filtroEditorial = document.getElementById("filtroEditorial");
var filtroGenero = document.getElementById("filtroGenero");
var filtros = document.getElementById("filtros");
var ordenar = document.getElementById("ordenar");

/*
filtros.addEventListener('change', (e) => {
    filtroAutor.setAttribute("hidden", "");
    filtroEditorial.setAttribute("hidden", "");
    filtroGenero.setAttribute("hidden", "");

    if (filtros.value == "autor") {
        filtroAutor.removeAttribute("hidden");
    }
    if (filtros.value == "editorial") {
        filtroEditorial.removeAttribute("hidden");
    }
    if (filtros.value == "genero") {
        filtroGenero.removeAttribute("hidden");
    }

});

*/
  var filtroInput = document.getElementById("filtro");
    var aplicarFiltroButton = document.getElementById("aplicarFiltro");

    aplicarFiltroButton.addEventListener("click", function() {
        var filtro = filtroInput.value;
        var url = "/tienda/filtrada?";

        if (filtro) {
            url += "filtro=" + filtro;
        }

        window.location.href = url;
    });
    
    
   var ordenSelector = document.getElementById("orden");

    ordenSelector.addEventListener("change", function() {
        var orden = this.value;
        var url = "/tienda/filtrada?";

        if (orden) {
            url += "orden=" + orden;
        }

        window.location.href = url;
    });
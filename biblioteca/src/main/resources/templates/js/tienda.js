var filtroAutor = document.getElementById("filtroAutor");
var filtroEditorial = document.getElementById("filtroEditorial");
var filtroGenero = document.getElementById("filtroGenero");
var filtros = document.getElementById("filtros");
var ordenar = document.getElementById("ordenar");


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

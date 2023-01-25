document.querySelector("form").addEventListener("submit", function (e) {

    e.preventDefault();
});
$(function(){

    $("main>div>span:nth-of-type(1)").click(function () {
        $("#seccionRegistro").css("display", "none");
        $("#seccionLogin").css("display", "flex");
    })
    $("main>div>span:nth-of-type(3)").click(function () {
        $("#seccionRegistro").css("display", "flex");
        $("#seccionLogin").css("display", "none");
    })

});
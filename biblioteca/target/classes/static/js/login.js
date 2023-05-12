

$(function(){


$("#seccionLogin").show();
  $("#seccionRegistro").hide();
  $("span:last").show();
  $("span:first").hide();

  $("span:first").click(function(){
    $("#seccionRegistro").toggle();
    $("#seccionLogin").toggle();
    $("span:first").toggle();
    $("span:last").toggle();
  });

  $("span:last").click(function(){
    $("#seccionRegistro").toggle();
    $("#seccionLogin").toggle();
    $("span:last").toggle();
    $("span:first").toggle();
  });

  $("#btnMenuMovil").click(function() {
    $("#menuMovil").toggleClass("active");
  });
  $("aside>img").click(function() {
    $("#menuMovil").toggleClass("active");
  });



})


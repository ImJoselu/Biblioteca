

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

 var toggleVisibility = document.getElementById('toggleVisibility');
  var registroPass = document.getElementById('registroPass');
  var registroPass2 = document.getElementById('registroPass2');

  toggleVisibility.addEventListener('click', function() {
    if (registroPass.type === 'password') {
      registroPass.type = 'text';
      registroPass2.type = 'text';
    } else {
      registroPass.type = 'password';
      registroPass2.type = 'password';
    }
  });
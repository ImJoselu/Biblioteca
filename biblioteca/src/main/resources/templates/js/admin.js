'use strict'


$(function () {

    $(".cambiarInput a").click(function () {
        $(this).css("display", "none");
        $(this).parent().children("input").css("display", "block");
    });

    $(".verTextarea a").click(function () {
        $(this).css("display", "none");
        $(this).parent().children("textarea").css("display", "block");
    });
    $("#btnMenuMovil").click(function() {
        $("#menuMovil").toggleClass("active");
      });
      $("aside>img").click(function() {
        $("#menuMovil").toggleClass("active");
      });

});


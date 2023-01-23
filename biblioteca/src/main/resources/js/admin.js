'use strict'


$(function () {

    $(".cambiarStock a").click(function () {
        $(this).parent().children("a").css("display", "none");
        $(this).parent().children("input").css("display", "block");
    }

    );

});
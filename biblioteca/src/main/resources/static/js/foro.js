$(document).ready(function() {
        $("#btn-comment-post").click(function() {
            $("#comment-form-container").slideToggle("fast");
        });
        
        
        
        $('.reply-link').click(function() {
    $(this).siblings('#comment-form-container').slideToggle("fast");
  });
        
        

    $("#mostrar-crear-post").click(function() {
      $("#form-crear-post").slideToggle("fast");
    });


        
        
    });
$(document).ready(function () {
   var $newsId = $('#news_id').val();
   $.ajax({
       url: "http://localhost:8081/api/home/news",
       type: "GET",
       data: {
           id: $newsId
       },
       success: function (item) {
           console.log(item);
           $.each(item, function (i, comment) {
               $('#user_comments').after('<div class="m-5"><div class="card-body"><div class="row">' +
                   '<div class="col-md-2"><img src="https://image.ibb.co/jw55Ex/def_face.jpg" class="img img-rounded img-fluid mb-2" alt=""><p class="text-secondary text-center">15 Minutes Ago</p></div>' +
                   '<div class="col-md-10"><p><strong>'+ comment.user.login + '</strong></p><p>' + comment.text +'</p><p><a class="float-right btn text-white btn-danger"> <i class="fas fa-exclamation-triangle"></i> Пожаловаться</a></p>' +
                   ' </div></div></div></div>');
           });
       }
   })
});

$(document).ready(function () {
   var $newsId = $('#news_id').val();
   var $btnSubmit = $('#submit_comment');
   var $newComment = $("#user_comment");
   $.ajax({
       url: "http://localhost:8081/api/home/news",
       type: "GET",
       data: {
           id: $newsId
       },
       success: function (item) {
          $.each(item, function (i, comment) {
               $('#user_comments').after('<div class="m-5"><div class="card-body"><div class="row">' +
                   '<div class="col-md-2"><img src="https://image.ibb.co/jw55Ex/def_face.jpg" class="img img-rounded img-fluid mb-2" alt=""><p class="text-secondary text-center">15 Minutes Ago</p></div>' +
                   '<div class="col-md-10"><p><strong>'+ item[i].user.login + '</strong></p><p>' + item[i].text +'</p><p><a class="float-right btn text-white btn-danger"> <i class="fas fa-exclamation-triangle"></i> Пожаловаться</a></p>' +
                   ' </div></div></div></div>');
           });
       }
   });

   $btnSubmit.on('click', function () {
       $.ajax({
           url: "http://localhost:8081/api/home/news?id=" + $newsId,
           type: "POST",
           contentType: 'application/json',
           data: JSON.stringify({
               text: $newComment.val()
           }),
           success: function () {
               location.reload();
           }
       })
   })
    /*$.ajax({
        url: "http://localhost:8081/admin/insert",
        type: "POST",
        data: JSON.stringify({
            email: $('#exampleInputEmail1').val(),
            name: $('#exampleInputName1').val(),
            login: $('#exampleInputLogin1').val(),
            password: $('#exampleInputPassword1').val(),
            roles: $roles
        }),
        contentType: 'application/json',
        success: function () {
            location.reload();
        },*/
});

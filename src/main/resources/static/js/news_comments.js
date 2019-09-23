$(document).ready(function () {
    var $currentUser;
    $.ajax({
        url: "http://localhost:8081/home/",
        type: "GET",
        success: function (user) {
            $currentUser = user;
        }
    });
    var $submitBtn = $('#submit_comment');
    var $comment = $('#user_comment');
    $submitBtn.on('click', function () {
       $.ajax({
            url: "http://localhost:8081/home/news/insert",
            type: "POST",
            data: JSON.stringify({
                text: $comment.val(),
                user_id: $currentUser
            }),
            contentType: 'application/json',
            success: function () {
                location.reload();
            }
        })
    })
});
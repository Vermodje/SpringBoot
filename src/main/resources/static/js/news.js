$(document).ready(function () {
    var $allNews = $('#all_news');
    $.ajax({
        url: "http://localhost:8081/api/home/news",
        type: "GET",
        success: function (data) {
            $.each(data, function (i, news){
                $allNews.append('<li class="nav-item"><a class="nav-link" href="/home/news/' + news.id + '">Новость№'+ news.id +'</a></li>');
            });
        }
    });
})
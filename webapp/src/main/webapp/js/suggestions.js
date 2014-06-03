var defaultValue = "Rate this movie";
var svalue = new Array();
var response = "";
const limitCount = 3;
svalue[0] = defaultValue;
svalue[1] = defaultValue;
svalue[2] = defaultValue;
$(".rateit").bind('rated', function () {
    $("#ratedvalue" + $(this).attr('number')).html($(this).rateit('value') + " of 10");
    svalue[parseInt($(this).attr('number')) - 1] = $(this).rateit('value') + " of 10";
    //TODO: call post rating api

    //TODO: call recommended api with limit 1, get info about film by imdbid and fill movie data
});

$(".rateit").bind('over', function (event, value) {
    if (value) {
        $("#ratedvalue" + $(this).attr('number')).html(value + " of 10");
    }
});

$(".rateit").mouseleave(function () {
    var tmp = $(this).attr('number');
    $("#ratedvalue" + tmp).fadeOut('fast', function () {
        $(this).html(svalue[parseInt(tmp) - 1]);
        $(this).fadeIn('fast');
    });
});

$(".poster").mouseover(function () {
    $(this).prev().fadeIn('fast');
    clearBlurEffect();
    var id = $(this).parent().attr("id");
    if (id == "movie1") {
        $("#movie1").removeAttr("highblur");
        $("#movie2").attr("highblur", true);
        $("#movie3").attr("highblur", true);
    }
    else if (id == "movie2") {
        $("#movie1").attr("highblur", true);
        $("#movie2").removeAttr("highblur");
        $("#movie3").attr("highblur", true);
    }
    else if (id == "movie3") {
        $("#movie1").attr("highblur", true);
        $("#movie2").attr("highblur", true);
        $("#movie3").removeAttr("highblur");
    }
});

$(".poster").mouseout(function () {
    $(this).prev().fadeOut('fast');
    clearBlurEffect();
});

function clearBlurEffect() {
    $("#movie1").removeAttr("highblur");
    $("#movie2").removeAttr("highblur");
    $("#movie3").removeAttr("highblur");
}

$(".next").click(function () {
    //TODO: call get recommended movies api
    //TODO: get imbd info for this movies
    //TODO: fill movies data

    //TODO: call this 3 actions at the beginning of the script

    $("#ratedvalue1").fadeOut('fast');
    $("#ratedvalue2").fadeOut('fast');
    $("#ratedvalue3").fadeOut('fast');

    $(".rateit").each(function () {
        $(this).fadeOut('fast');
    });

    $(".poster").each(function () {
        $(this).fadeOut('slow', function () {
            var id1 = getRandomInt(1, 7);
            fillMovieData(movies[id1], 1, id1);

            var id2 = getRandomInt(1, 7);
            fillMovieData(movies[id2], 2, id2);

            var id3 = getRandomInt(1, 7);
            fillMovieData(movies[id3], 3, id3);

            $("#ratedvalue1").html(defaultValue);
            $("#ratedvalue2").html(defaultValue);
            $("#ratedvalue3").html(defaultValue);
            $('.rateit').rateit('reset');
            svalue[0] = defaultValue;
            svalue[1] = defaultValue;
            svalue[2] = defaultValue;

            $(".poster").each(function () {
                $(this).fadeIn('fast');
            });

            $(".watched").each(function () {
                $(this).fadeIn('fast');
            });
        });
    });


})

function fillMovieData(movie, number, id) {
    $("#poster" + number).attr("src", "images/" + id + ".jpg");

    $("#title" + number).html(movie.title);
    $("#genre" + number).html(movie.genre);
    $("#description" + number).html(movie.description);
    $("#director" + number).html(movie.director);
    $("#year" + number).html(movie.year);
    $("#stars" + number).html(movie.stars);
}

$(".watched").click(function () {
    $(this).fadeOut('fast', function () {
        var num = $(this).attr("number");
        $("#ratedvalue" + $(this).attr("number")).fadeIn('fast');
        $(".rateit").each(function () {
            if ($(this).attr("number") == num)
                $(this).fadeIn('fast');
        });
    });
});

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}
function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : null;
}
function fillMovieData(movie,number) {
    $("#title"+number).html(movie.title);
    $("#year"+number).html("2025");
    $("#poster"+number).attr("src", movie.imdbPictureURL);
}
function handleNextMovie() {
    var googleId = getCookie("authToken");

    $.get("http://localhost:8080/api/movies/recommended", {googleid: googleId}, function (data) {
        response=JSON.stringify(data);
        var movieResponse=JSON.parse(response);
        console.log(movieResponse.toString());
        for (var i=1;i<=limitCount;i++)
        {
            fillMovieData(movieResponse[i-1],i);
        }

    });

}

$( document ).ready(function() {
    handleNextMovie();
});

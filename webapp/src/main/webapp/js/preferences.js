var defaultValue = "Rate this movie";
var svalue = defaultValue;
var currentMovie = {};
var ratedMovies = 0;
var rateLimit=10;
var moveUp=false;
var animationFinished = true;
var firstOne = true;
var url="";
var response = {};

$(".rateit").bind('rated', function () {
    $("#ratedvalue").html($(this).rateit('value') + " of 10");
    svalue = $(this).rateit('value');

    postRating(currentMovie.id, svalue);
    ratedMovies += 1;

    //check if number of films is greater than 10 - than redirect to suggestions.html
    if (ratedMovies >= rateLimit) {
        //TODO: use some bootstrap styled confirm dialog
        jQuery(function($) {
            $('form[data-form-confirm]').submit(function(e) {
                e.preventDefault();

                var form = $(this),
                    modal = $('#' + form.attr('data-form-confirm'))

                modal.modal({
                    show: true,
                    backdrop: true
                });

                modal.find('.btn-confirm').click(function() {
                    modal.modal('hide');
                    form.unbind('submit').submit();
                    window.location.replace("suggestions.html");
                });
                modal.find('.btn').click(function() {
                    modal.modal('hide');
                    form.unbind('submit').submit();

                });
            });
        });
    }
    else {
        handleNextMovie();
    }
});

$(".rateit").bind('over', function (event, value) {
    if (value) {
        $("#ratedvalue").html(value + " of 10");
    }
});

$(".rateit").mouseleave(function () {
    $("#ratedvalue").fadeOut('fast', function () {
        $(this).html(svalue);
        $(this).fadeIn('fast');
    });
});

function fillSomeData(movieData) {
    console.log(movieData);
}

$(".next").click(function () {
    handleNextMovie();
});

function setSize(){
    $('#poster2').width(220); // Units are assumed to be pixels
    $('#poster2').height(318);
    $('#poster').width(220); // Units are assumed to be pixels
    $('#poster').height(318);
}

function showMovieData(movie) {
    movie = JSON.parse(movie);

    if (animationFinished) {
        animationFinished = false;


        if (!moveUp) {
            $("#poster2").attr("src", movie.Poster);
            setSize();
            $("#poster").animate({
                marginTop: -335
            }, 1500, function () {
                moveUp = true;
                animationFinished = true;
                fillMovieData(movie);
                $("#info").fadeIn('fast');
            });
        }
        else {
            $("#poster").attr("src", movie.Poster);
            setSize();
            $("#poster").animate({
                marginTop: 0
            }, 1500, function () {
                moveUp = false;
                animationFinished = true;
                fillMovieData(movie);
                $("#info").fadeIn('fast');
            });
        }


        $("#info").fadeOut('fast');


        $("#ratedvalue").html(defaultValue);
        $('.rateit').rateit('reset');
        svalue = defaultValue;
    }
}


function fillMovieData(movie) {
    $("#title").html(movie.Title);
    $("#genre").html(movie.Genre);
    $("#description").html(movie.Plot);
    $("#director").html(movie.Director);
    $("#year").html(movie.Year);
    $("#stars").html(movie.Actors);
}

function handleNextMovie() {
    $.get("http://localhost:8080/api/movies/forRating", function (data) {
        currentMovie = data;
        console.log("forRating data: " + JSON.stringify(currentMovie));
        handleImdbInfo(currentMovie.imdbId, showMovieData);
    });
}
function postRating(idMovie, rating) {

    $.post("http://localhost:8080/api/movies/" + idMovie + "/rate", {rating: rating},{id: getCookie(authToken)});
}

// возвращает cookie с именем name, если есть, если нет, то undefined
function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : null;
}


function addUser(){
    var googleId = getCookie("authToken");
    if ( googleId != null){
        $.get("http://localhost:8080/api/user/picture", function (data) {


        console.log(data.toString());

            var elem = document.createElement("img");
            elem.src = data.toString();
            document.getElementById("user").appendChild(elem);

        });
    } else {
        alert("NOPE");
    }

}
//get first movie for rating
    addUser();
    handleNextMovie();

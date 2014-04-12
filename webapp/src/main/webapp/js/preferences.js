var defaultValue = "Rate this movie";
var svalue = defaultValue;
var currentMovie = {};
var ratedMovies = 0;
var moveUp = false;
var animationFinished = true;

$(".rateit").bind('rated', function () {
    $("#ratedvalue").html($(this).rateit('value') + " of 10");
    svalue = $(this).rateit('value');

    postRating(currentMovie.id, svalue);
    ratedMovies += 1;

    //check if number of films is greater than 10 - than redirect to suggestions.html
    if (ratedMovies >= 10) {
        //TODO: use some bootstrap styled confirm dialog
        alert("You are already rated 10 films");
        window.location.replace("suggestions.html");
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

function showMovieData(movie) {
    movie = JSON.parse(movie);

    if (animationFinished) {
        animationFinished = false;

        $("#poster").attr("src", movie.Poster);

        if (!moveUp) {
            $("#poster2").attr("src", movie.Poster);
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

    $.post("http://localhost:8080/api/movies/" + idMovie + "/rate", {rating: rating});
}

//get first movie for rating
handleNextMovie();
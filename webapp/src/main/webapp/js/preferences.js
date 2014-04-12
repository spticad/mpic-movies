


var defaultValue = "Rate this movie";
var svalue = defaultValue;
var movieId=1;
var ratedMovies=0;
$(".rateit").bind('rated', function () {
    $("#ratedvalue").html($(this).rateit('value') + " of 10");
   // svalue = $(this).rateit('value') + " of 10";
    svalue = $(this).rateit('value');
    //TODO: call post rating api
    postRating(movieId,svalue);
    ratedMovies+=1;

    //TODO: check if number of films is greater than 10 - than redirect to suggestions.html
    if (ratedMovies>=10){
       alert("You are already rated 10 films");
        window.location.replace("suggestions.html");
    }
    else{
        //TODO: call forRating api, get info about film by imdbid - maybe we need to call new film after click on the next button?
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


var moveUp = false;
var picUrl="";
var animationFinished = true;
$(".next").click(function () {
    //TODO: call forRating api, get info about film by imdbid

    if (animationFinished) {
        animationFinished = false;
        var id = getRandomInt(1, 7);
        var movie = movies[id];

        if (!moveUp) {
            getMovie();
           // $("#poster2").attr("src", "images/" + id + ".jpg");
            $("#poster2").attr("src", picUrl);
            $("#poster").animate({
                marginTop: -335
            }, 1500, function () {
                moveUp = true;
                animationFinished = true;
                //fillMovieData(movie);

                $("#info").fadeIn('fast');
            });
        }
        else {
            getMovie();
            //$("#poster").attr("src", "images/" + id + ".jpg");
            $("#poster").attr("src", picUrl);
            $("#poster").animate({
                marginTop: 0
            }, 1500, function () {
                moveUp = false;
                animationFinished = true;
              // fillMovieData(movie);

                $("#info").fadeIn('fast');
            });
        }

        $("#info").fadeOut('fast');


        $("#ratedvalue").html(defaultValue);
        $('.rateit').rateit('reset');
        svalue = defaultValue;
    }

});

function fillMovieData(movie) {

   // $("#title").html(movie.title);
    //$("#genre").html(movie.genre);
   // $("#description").html(movie.description);
   // $("#director").html(movie.director);
   // $("#year").html(movie.year);
   // $("#stars").html(movie.stars);
}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}


//TODO: call forRating api, get info about film by imdbid
function getMovie (){
    $.get("http://localhost:8080/api/movies/forRating", function (data) {

        $("#title").html(data.title);
        alert(data.imdb);
        picUrl=data.imdb_pic.toString();
    });
}
function postRating (idMovie,rating){

    $.post ("http://localhost:8080/api/"+idMovie+"/rate",
        {
            rating: rating
        }
    );
}
var movies = {
    '1': {
        'title': 'The Social Network',
        'genre': 'Biography · Drama',
        'year': '2010',
        'description': 'Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, but is later sued by two brothers who claimed he stole their idea, and the cofounder who was later squeezed out of the business.',
        'director': 'David Fincher',
        'stars': 'Jesse Eisenberg, Andrew Garfield, Justin Timberlake'
    },
    '2': {
        'title': 'The Lord of the Rings: The Fellowship of the Ring',
        'genre': ' Action · Adventure · Fantasy',
        'year': '2001',
        'description': 'A meek hobbit of The Shire and eight companions set out on a journey to Mount Doom to destroy the One Ring and the dark lord Sauron.',
        'director': 'Peter Jackson',
        'stars': 'Elijah Wood, Ian McKellen, Orlando Bloom'
    },
    '3': {
        'title': 'Inception',
        'genre': 'Action · Adventure · Mystery',
        'year': '2010',
        'description': 'A skilled extractor is offered a chance to regain his old life as payment for a task considered to be impossible.',
        'director': 'Christopher Nolan',
        'stars': ' Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page'
    },
    '4': {
        'title': 'The Matrix',
        'genre': ' Action · Adventure · Sci-Fi ',
        'year': '1999',
        'description': 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.',
        'director': 'The Wachowski Brothers',
        'stars': 'Keanu Reeves, Laurence Fishburne, Carrie-Anne '
    },
    '5': {
        'title': 'American Beauty',
        'genre': 'Drama',
        'year': '1999',
        'description': 'Lester Burnham, a depressed suburban father in a mid-life crisis, decides to turn his hectic life around after developing an infatuation for his daughters attractive friend.',
        'director': 'Sam Mendes',
        'stars': 'Kevin Spacey, Annette Bening, Thora Birch'
    },
    '6': {
        'title': 'The Truman Show',
        'genre': 'Comedy · Drama · Sci-Fi',
        'year': '1998',
        'description': 'An insurance salesman/adjuster discovers his entire life is actually a T.V. show.',
        'director': 'Peter Weir',
        'stars': 'Jim Carrey, Ed Harris, Laura Linney '
    },
    '7': {
        'title': 'Dallas Buyers Club',
        'genre': 'Biography · Drama · History',
        'year': '2013',
        'description': 'In 1985 Dallas, electrician and hustler Ron Woodroof works around ',
        'director': 'Peter Weir',
        'stars': 'Jim Carrey, Ed Harris, Laura Linney '
    }}
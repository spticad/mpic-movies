var defaultValue = "Rate this movie";
var svalue = new Array();
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
        'description': 'In 1985 Dallas, electrician and hustler Ron Woodroof works around the system to help AIDS patients get the medication they need after he is himself diagnosed with the disease.',
        'director': 'Jean-Marc Vallée',
        'stars': ' Matthew McConaughey, Jennifer Garner, Jared Leto'
    }
}

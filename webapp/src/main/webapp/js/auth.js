/**
 * Created by LeoMal on 17.05.2014.
 */
var url="";
var response = {};

$.get("http://localhost:8080/api/user/url", function (data) {

    response=data;
    if (response.urlToRedirect != null){

        function redirect(e) {
            e.preventDefault();
            $(location).attr('href', response.urlToRedirect);
            return false;
        }

        var loginBtn = $('<button/>')
            .text('Auth with Google')
            .click(redirect);
        $('#buttonPlace').append(loginBtn);
    }
    else {
        var user = JSON.parse(response.user);
        console.log(user.name);
        // TODO: Make something with user
    }
});
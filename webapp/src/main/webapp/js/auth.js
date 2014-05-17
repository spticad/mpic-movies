/**
 * Created by LeoMal on 17.05.2014.
 */
var url="";
var response = {};

$.get("http://localhost:8080/api/user/me", function (data) {

    response=data;
    if (response.urlToRedirect != null){
        var div = document.getElementById('buttonPlace');
        var button= document.createElement('input');
        button.setAttribute('type','button');
        button.setAttribute('name','authButton');
        button.setAttribute('value','Auth with Google');
        button.setAttribute('onclick','top.location=='+response.urlToRedirect);
        div.appendChild(button);
    }
    else{
        // TODO: Make something with user
    }
});
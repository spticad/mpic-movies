/**
 * Created by LeoMal on 01.06.2014.
 */
function insertText () {
    document.getElementById('text').innerHTML = "Hi "+ getName()+". Nice to see you again!";
}
function getName (){
    var googleId = getCookie("authToken");
    $.get("http://localhost:8080/api/user/name?googleID="+googleId, function (data) {
       console.log(data.toString());
       return data.toString();

    });

}
function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : null;
}
$( document ).ready(function() {
    insertText();
});
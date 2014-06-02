/**
 * Created by LeoMal on 01.06.2014.
 */
function insertText () {
    document.getElementById('text').innerHTML = "Hi "+ getName()+". Nice to see you again!";
}
function getName (){
    var googleId = getCookie("authToken");
    $.get("http://localhost:8080/api/user/name&googleID="+googleId, function (data) {
       console.log(data.toString());
       return data.toString();

    });
}
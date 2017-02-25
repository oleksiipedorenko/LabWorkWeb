$(document).ready(function(){

    $("#submitLogin").click(function () {
        var login = $("#login").val();
        var password = $("#password").val();

        $.ajax({
            type: "POST",
            url: "/auth",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify({login: login, password:password}),
            success: function (data) {
                console.log(data);
                if (data.status === "OK") {
                    window.location.href = "/pub";
                } else {

                }
            }
        });
    });

});


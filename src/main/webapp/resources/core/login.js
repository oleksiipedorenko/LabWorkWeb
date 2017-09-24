$(document).ready(function(){
    $("#submitLogin").click(function () {
        var login = $('#login').val();
        var password = $('#password').val();
        $.ajax({
            type: "POST",
            url: "/submitLogin",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"login": "' + login +'", "password": "' + password + '"}',
            success: function (data) {
                if (data.status === "OK") {
                    window.location.href = "/pub";
                } else {
                    alert("Incorrect credentials!!!!!!!!1111111")
                }
            }
        });
    });
});


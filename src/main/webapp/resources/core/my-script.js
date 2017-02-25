$(document).ready(function(){

    var switcher = function(name) {
        $(".tab-panel").addClass("hidden");
        $("#"+name+"-panel").removeClass("hidden");

        $("ul.nav-tabs li").removeClass("active");
        $("#"+name+"-tab").parent().addClass("active");
    };

    $("#status-tab").click(function () {
        switcher("status");
    });

    $("#order-tab").click(function () {
        switcher("order");
    });

    $("#contact-tab").click(function () {
        switcher("contact");
    });

    var updateBarStatus = function (barStatus) {
        $("#darkAmount").text(barStatus.glassesOfDark);
        $("#lightAmount").text(barStatus.glassesOfLight);
        $("#aleAmount").text(barStatus.glassesOfAle);
    };

    var displayClientOrderSuccess = function (message) {
      $("#client-order-response").text(message);
      $("#client-order-response").addClass("bg-success");
      $("#client-order-response").removeClass("bg-danger");
    };

    var displayClientOrderError = function (message) {
        var field = $("#client-order-response");
        field.text(message);
        field.addClass("bg-danger");
        field.removeClass("bg-success");
    };

    $("#submit-order").click(function () {
        var kind = $("#kindSelect option:selected").text();
        var count = $("#count").val();
        $.ajax({
            type: "POST",
            url: "/clientOrder",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify({kind: kind, count:count}),
            success: function (data) {
                console.log(data);
                if (data.status === "OK") {
                    updateBarStatus(data.barStatus);
                    displayClientOrderSuccess("Your order completed!")
                } else {
                    displayClientOrderError(data.errorMessage);
                }
            }
        });
    });

});


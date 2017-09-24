$(document).ready(function(){

    $('#status-tab').click(function () {
        hideTabs();
        displayTab("status-panel");
        disActiveControl();
        addActive("status-tab");
    });
    $('#order-tab').click(function () {
        hideTabs();
        displayTab("order-panel");
        disActiveControl();
        addActive("order-tab");
    });
    $('#contact-tab').click(function () {
        hideTabs();
        displayTab("contact-panel");
        disActiveControl();
        addActive("contact-tab");
    });

    $('#submit-order').click(function () {
        clearPlaceOrderInfo();

        var kind = $('#kindSelect option:selected').text();
        var amount = $('#count').val();
        if (amount < 1) {
            placeOrderError("Incorrect order amount!");
        } else {
            $.ajax({
                type: "POST",
                url: "/clientOrder",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: '{"kind": "' + kind +'", "count": "' + amount + '"}',
                success: function (data) {
                    if (data.status === "OK") {
                        // update status
                        placeOrderSuccess();
                    } else {
                        placeOrderError(data.errorMessage);
                    }
                }
            });
        }
    })
});

function clearPlaceOrderInfo() {
    var response = $('#client-order-response');
    response.removeClass("bg-success");
    response.removeClass("bg-danger");
    response.html("");
}

function placeOrderError(message) {
    var response = $('#client-order-response');
    response.addClass("bg-danger");
    response.html(message);
}

function placeOrderSuccess() {
    var response = $('#client-order-response');
    response.addClass("bg-success");
    response.html("Order executed successfully");
}

function hideTabs () {
    $(".content-panel").each(function (index, elem) {
        $(elem).addClass("hidden");
    });
}

function displayTab(id) {
    $("#"+id).removeClass("hidden");
}

function disActiveControl () {
    $(".content-tab").each(function (index, elem) {
        $(elem).removeClass("active");
    });
}

function addActive(id) {
    $("#"+id).parent().addClass("active");
}

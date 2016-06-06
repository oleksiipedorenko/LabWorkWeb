$(document).ready(function(){

    function deactivateTabs() {
        $("ul.nav-tabs li.switcher").removeClass("active");
    }

    function hideTabContent() {
        $("div.tab-content").addClass("hidden");

    }

    $("#status-tab").click(function() {
        deactivateTabs();
        hideTabContent();
        $("#status-tab").addClass("active");
        $("#status-panel").removeClass("hidden");
    });

    $("#order-tab").click(function() {
        deactivateTabs();
        hideTabContent();
        $("#order-tab").addClass("active");
        $("#order-panel").removeClass("hidden");
    });

    $("#contact-tab").click(function() {
        deactivateTabs();
        hideTabContent();
        $("#contact-tab").addClass("active");
        $("#contact-panel").removeClass("hidden");
    });

    function updateBarStatus(barStatus) {
        $("#darkAmount").text(barStatus.glassesOfDark);
        $("#lightAmount").text(barStatus.glassesOfLight);
        $("#aleAmount").text(barStatus.glassesOfAle);
    }

    function displayClientOrderSuccess() {
        var responseElem = $("#client-order-response");
        responseElem.addClass("bg-success");
        responseElem.removeClass("bg-danger");
        responseElem.text("Your order has been sent");
    }

    function displayError(errorMessage) {
        var responseElem = $("#client-order-response");
        responseElem.addClass("bg-danger");
        responseElem.text(errorMessage);
    }

    $("#submit-order").click(function () {
        var kind = $("#kindSelect option:selected").text();
        var count = $("#count").val();
        $.ajax({
            type: "POST",
            url: "/clientOrder",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"kind": "' + kind + '", "count": "' + count + '"}',
            success: function (data) {
                if (data.status === 'OK') {
                    updateBarStatus(data.barStatus);
                    displayClientOrderSuccess();
                } else {
                    displayError(data.errorMessage);
                }
            }
        });
    });
    $("#refill-bar").click(function () {
        var dark = $("#darkGlasses").val();
        var light = $("#lightGlasses").val();
        var ale = $("#aleGlasses").val();
        $.ajax({
            type: "POST",
            url: "/refillOrder",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"dark": "' + dark + '", "light": "' + light + '", "ale": "' + ale + '"}',
            success: function (data) {
                if (data.status === 'OK') {
                    updateBarStatus(data.barStatus);
                    $("#close-modal").click();
                }
            }
        });
    });
});


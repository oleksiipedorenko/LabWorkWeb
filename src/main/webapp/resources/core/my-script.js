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

});


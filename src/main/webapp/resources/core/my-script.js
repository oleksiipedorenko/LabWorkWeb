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
        $("#contact-tab").addClass("active")
        $("#contact-panel").removeClass("hidden");
    });
});


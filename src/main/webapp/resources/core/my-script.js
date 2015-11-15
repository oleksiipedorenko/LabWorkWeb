$(document).ready(function(){

    function clearNavSelection() {
        $('.nav-tabs li').removeClass("active");
        $('.tab-panel').addClass("hidden");
    };

    $("#order-tab").click(function(){
        clearNavSelection();
        $("#order-panel").removeClass("hidden");
        $("#order-tab").parent('li').addClass("active");
    });

    $("#status-tab").click(function(){
        clearNavSelection();
        $("#status-panel").removeClass("hidden");
        $("#status-tab").parent('li').addClass("active");
    });

    $("#contact-tab").click(function(){
        clearNavSelection();
        $("#contact-panel").removeClass("hidden");
        $("#contact-tab").parent('li').addClass("active");
    });

    function updateStatus(barStatus) {
        $('#darkAmount').text(barStatus.glassesOfDark);
        $('#lightAmount').text(barStatus.glassesOfLight);
        $('#aleAmount').text(barStatus.glassesOfAle);
    }
    function displayError(message) {
        $('#client-order-response').removeClass('bg-success');
        $('#client-order-response').addClass('bg-danger');
        $('#client-order-response').text(message);
    }

    function displayClientOrderSuccess() {
        $('#client-order-response').removeClass('bg-danger');
        $('#client-order-response').addClass('bg-success');
        $('#client-order-response').text('Thank you for you order! Just wait for a minute :)');
    }

    $('#refresh-status').click(function () {
        $.get("/barStatus", function(data){
            if (data.status === 'OK') {
                updateStatus(data.barStatus);
            } else {
                displayError(data.errorMessage);
            }
        });
    });

    $('#submit-order').click(function () {
        var kind = $('#kindSelect option:selected').text();
        var count = $('#count').val();
        $.ajax({
            type: "POST",
            url: '/clientOrder',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"kind": "' + kind + '", "count": "' + count + '"}',
            success: function(data) {
                if (data.status === 'OK') {
                    updateStatus(data.barStatus);
                    displayClientOrderSuccess();
                } else {
                    displayError(data.errorMessage);
                }
        }});
    });

    $('#refill-bar').click(function(){
        var darkCount = $('#darkGlasses').val();
        var lightCount = $('#lightGlasses').val();
        var aleCount = $('#aleGlasses').val();
        $.ajax({
            type: "POST",
            url: '/refillOrder',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"dark": "' + darkCount + '", "light": "' + lightCount + '", "ale": "' + aleCount+ '"}',
            success: function(data) {
                if (data.status === 'OK') {
                    updateStatus(data.barStatus);
                } else {
                    alert(data.errorMessage);
                }
            }
        });
        $('#requestModal').modal('toggle');
    });
});


var visible = false;

$(".menu-toggle").click(function(e) {

    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
    visible = !visible;
});

$("#page-body").click(function() {
    if (visible) {
        visible = false;
        $("#wrapper").toggleClass("toggled");
    }


});
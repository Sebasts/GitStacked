$(function(){

var $rows = $("tr");

$("#search").keyup(function() {
    var val = $.trim(this.value);
    if (val === "")
        $rows.show();
    else {
        $rows.hide();
        $rows.has("td:contains(" + val + ")").show();
    }
});
})
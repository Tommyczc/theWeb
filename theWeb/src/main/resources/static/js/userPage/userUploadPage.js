$(document).ready(function(){


    // var form1 = document.getElementById('userUploadForm');
    // form1.attr("target","iFrame");
    $("#userUploadInput").change(function () {
        $("#userUploadSelet").text(this.files.length + " file(s) selected");
    });
});
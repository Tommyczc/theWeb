$(document).ready(function () {

    selectPage();
    $('.navbar .dropdown-item').on('click', function (e) {
        var $el = $(this).children('.dropdown-toggle');
        var $parent = $el.offsetParent(".dropdown-menu");
        $(this).parent("li").toggleClass('open');

        if (!$parent.parent().hasClass('navbar-nav')) {
            if ($parent.hasClass('show')) {
                $parent.removeClass('show');
                $el.next().removeClass('show');
                $el.next().css({"top": -999, "left": -999});
            } else {
                $parent.parent().find('.show').removeClass('show');
                $parent.addClass('show');
                $el.next().addClass('show');
                //$el.next().css({"top": $el[0].offsetTop, "left": $parent.outerWidth() - 4});
            }
            e.preventDefault();
            e.stopPropagation();
        }
    });

    $('.navbar .dropdown').on('hidden.bs.dropdown', function () {
        $(this).find('li.dropdown').removeClass('show open');
        $(this).find('ul.dropdown-menu').removeClass('show open');
    });

});



//根据点击事件来展开相应内容
function selectPage() {
    hideAllPage();
    $('#home').show();

    $('#navHome').click(function(){
        hideAllPage();
        $('#home').show();
    });

    $('#viewPictureButton').click(function(){
        hideAllPage();
        updatePicture();
        $('#userViewPicture').show();
    });

    $('#uploadPictureButton').click(function(){
        hideAllPage();
        $('#userUploadPicture').show();
    });

    $('#adminUploadHistory').click(function (){
        hideAllPage();
        updateUploadHistory();
        $('#adminUserUpload').show();
    });

    $('#adminUserInformation').click(function (){
        hideAllPage();
        updateUserInformation();
        $('#adminUserInfo').show();
    });
}


//隐藏所有页面
function hideAllPage(){
    $('#home').hide();
    $('#userViewPicture').hide();
    $('#userUploadPicture').hide();
    $('#adminUserUpload').hide();
    $('#adminUserInfo').hide();
}


function updatePicture() {
    var options = {
        url: "/guest/userExportLogic",//json文件或者数据库查询后的地址
    }
    var body=$('#pictureViewTableBody');
    $("#pictureViewTable").grid(options,false,body);
}

function updateUploadHistory(){
    var options={
        url:"/admin/pictureUploadHistory",
    }
    var body=$('#uploadHistoryBody');
    $("#uploadHistoryTable").grid(options,true,body);
}

// window.alert = function() {
//     return false;
// }



$.fn.grid = function(options,UserName,body) {
    var $tbody = body;
    var url = options.url;
    var content = [];
    //ajax获取数据源后存入content数据中。
    $.ajax({
        type: "post",
        url: url,
        dataType: "json",
        async: false,
        contentType: "application/json;charset=utf8",
        success: function(data) {
            content = data;
        },

        error:function(e){
            alert("error: "+e.responseText);
        }
    });
    $tbody.html("");
    for(var c = 0; c < content.length; c++) {
        $tbody.append("<tr>");
        var originFile="";

        $.each(content[c],function(k,row){
            //alert(k);
            if(UserName==false) {
                if (k != "userName") {
                    if (k == "pictureUrl") {
                        originFile = row;
                        var frontFile = originFile.substring(13);
                        $tbody.append("<td>" + frontFile + "</td>");
                    } else {
                        $tbody.append("<td>" + row + "</td>");
                    }
                }
            }
            else{
                if (k == "pictureUrl") {
                    originFile = row;
                    var frontFile = originFile.substring(13);
                    $tbody.append("<td>" + frontFile + "</td>");
                } else {
                    $tbody.append("<td>" + row + "</td>");
                }
            }
        });

        var delButton = $('<a class="optLink" href="javascript:;">Delete</a>');//删除按钮
        delButton.attr("userName",content[c]["userName"].toString());//给按钮添加dataid属性
        delButton.attr("pictureUrl",content[c]["pictureUrl"].toString());//给按钮添加dataid属性
        delButton.attr("description",content[c]["description"].toString());
        delButton.attr('options',options.url);
        delButton.click(deletePictureRequest);//给按钮添加点击事件


        var viewButton = $('<a class="optLink" href="javascript:;">View</a>');//编辑按钮
        viewButton.attr("userName",content[c]["userName"].toString());
        viewButton.attr("pictureUrl",content[c]["pictureUrl"].toString());
        viewButton.click(showPictureDetail);

        var opt_td = $('<td></td>');
        opt_td.append(delButton);
        $tbody.append(opt_td);

        var opt_td = $('<td></td>');
        opt_td.append(viewButton);
        $tbody.append(opt_td);

        $tbody.append("</tr>");
        //alert($tbody);

    }
    //alert($tbody.html());
}


function deletePictureRequest(){
    var userName = $(this).attr("userName");
    var pictureUrl = $(this).attr("pictureUrl");
    var description = $(this).attr("description");
    var opt=$(this).attr("options");
    var formData=new FormData();
    //
    // var newElement = document.createElement("input");
    // newElement.setAttribute('userName',userName);
    // newElement.setAttribute('pictureUrl',pictureUrl);
    // newElement.setAttribute('description',description);
    //
    // formData.append(newElement);


    formData.append('userName',userName);
    formData.append('pictureUrl',pictureUrl);
    formData.append('description',description);
    //var data={userName:userName,pictureUrl:pictureUrl,description:description};
    $.ajax({
        type: "post",
        url: "/guest/pictuerDeleteLogic",
        dataType: "json",
        //async: false,
        data:formData,
        cache: false,
        processData : false,//用于对data参数进行序列化处理 这里必须false
        contentType: false,
        //contentType: "application/json;charset=utf8",
        success: function(data) {
            alert("success: "+data["response"]);

            //自动更新表格
            if(opt=="/guest/userExportLogic"){
                updatePicture();
            }
            else if(opt=="/admin/pictureUploadHistory"){
                updateUploadHistory();
            }

        },

        error:function(e){
            alert("error: "+e.responseText);
        }
    });

}


function showPictureDetail(){
    var userName = $(this).attr("userName");
    var pictureUrl = $(this).attr("pictureUrl");
    var src="/image/"+userName+"/"+pictureUrl+"?tempid=\"+Math.random()";

    var modal = document.getElementById('myModal');
    var modalImg = document.getElementById("img01");
    var captionText = document.getElementById("caption");

    modal.style.display = "block";
    modalImg.src = src.toString();
    captionText.innerHTML = "Upload by "+userName;

    var span = document.getElementsByClassName("close")[0];

    span.onclick = function() {
        modal.style.display = "none";
    }
}



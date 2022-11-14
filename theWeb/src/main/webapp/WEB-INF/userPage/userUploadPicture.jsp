<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




    <form id="userUploadForm" >
        <input id='userUploadInput' class="userUpload" name="files" accept="image/*" type="file" multiple>
        <p id='userUploadSelet' name='userUpload' class="userUpload">Drag your picturess here or click in this area.</p>
        <p id='userUploadDescript' name="userUploadDescript" class="userUploadDescript">Description: <input id="txtDescription" name='txtDescription' class="userUploadDescription" type='text'/>(optional)</p>
        <button type="submit" class="userUpload">Upload</button>
    </form>



<script>
    $(document).ready(function() {
        $('#userUploadForm').submit(function() {	//这次我们这么绑定
            // var files = $("#userUploadInput")[0];
            // var description = $("#txtDescription").val();
            // var params = {
            //     files: files,
            //     description: description
            //     //errmessage: errmessage
            // }

            var form = $('#userUploadForm')[0];

            var data = new FormData(form);

            if($("#userUploadInput").val()==""){alert("Please select at least one picture");return false};


            $.ajax({
                type:"post",
                //ansyv:true,
                data:data,
                //enctype: 'guest/userUploadLogic',
                //datatype:"json",
                cache : false,//上传文件无需缓存
                processData : false,//用于对data参数进行序列化处理 这里必须false
                contentType : false, //必须
                url:"/guest/userUploadLogic",
                success:function(reply){//本来用的mag   现在用:data
                    if (reply){
                        alert(reply);
                    }
                },
                error:function(e){
                    alert("error: "+e.responseText);
                }
            })
            return false;	//还是要return false, 跟上面一样的道理
        });
    });

</script>


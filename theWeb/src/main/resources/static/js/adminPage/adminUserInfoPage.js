function updateUserInformation() {
    var content=[];
    var body=$('#userInfoBody');

    $.ajax({
        type: "post",
        url: "/admin/userInformation",
        dataType: "json",
        async: false,
        contentType: "application/json;charset=utf8",
        success: function (data) {
            content=data;
            //alert(body);
        },

        error: function (e) {
            alert("error: " + e.responseText);
        }
    });

    body.html('');
    for(var c = 0; c < content.length; c++) {
        body.append("<tr>");

        $.each(content[c],function(k,row){
            body.append("<td>" + row + "</td>");
        });


        var updButton = $('<a class="optLink" href="javascript:;">Update</a>');//删除按钮
        updButton.attr('userId',content[c]['id'].toString());
        updButton.attr("userName",content[c]["username"].toString());//给按钮添加dataid属性
        updButton.click(updateUserInfoRequest);//给按钮添加点击事件

        var delButton = $('<a class="optLink" href="javascript:;">Delete</a>');//删除按钮
        delButton.attr('userId',content[c]['id'].toString());
        delButton.attr("userName",content[c]["username"].toString());//给按钮添加dataid属性
        delButton.click(deleteUserInfoRequest);//给按钮添加点击事件


        var opt_td = $('<td></td>');
        opt_td.append(updButton);
        body.append(opt_td);

        var opt_td = $('<td></td>');
        opt_td.append(delButton);
        body.append(opt_td);

        body.append("<tr>");
    }

}


function deleteUserInfoRequest(){
    var id=$(this).attr('userId');
    var userName = $(this).attr("userName");
    var formData=new FormData();

    formData.append('id',id);
    formData.append('userName',userName);

    $.ajax({
        type: "post",
        url: "/admin/deleteUserInformation",
        dataType: "json",
        //async: false,
        data:formData,
        cache: false,
        processData : false,//用于对data参数进行序列化处理 这里必须false
        contentType: false,
        //contentType: "application/json;charset=utf8",
        success: function(data) {
            alert(data['response']);
            updateUserInformation();
        },

        error: function (e){
            alert(e.responseText);

        }
    });


}

function updateUserInfoRequest(){
    var id=$(this).attr('userId');
    var userName = $(this).attr("userName");
    var model=document.getElementById('userUpdateModal');
    var inputName=document.getElementById('inputName');
    var inputId=document.getElementById('inputId');
    var formTitle=document.getElementById('formTitle');
    var confirmBtn=document.getElementById('submitButton');

    const success = $('#success_message');
    const warming = $('#warming_message');

    success.hide();
    warming.hide();

    inputId.disabled="true";
    inputName.disabled="true";
    confirmBtn.disabled="false";

    formTitle.innerText="Update User";
    inputName.value=userName;
    inputId.value=id;
    model.style.display = "block";
}

function insertUserInfoRequest(){
    var model=document.getElementById('userUpdateModal');
    var inputId=document.getElementById('inputId');
    var formTitle=document.getElementById('formTitle');
    var confirmBtn=document.getElementById('submitButton');

    const success = $('#success_message');
    const warming = $('#warming_message');

    success.hide();
    warming.hide();

    inputId.disabled="true";
    confirmBtn.disabled="false";

    formTitle.innerText="Insert User";
    inputId.value="It is default self-generated id, ignore it";
    model.style.display = "block";
}



$(document).ready(function() {

    const success = $('#success_message');
    const warming = $('#warming_message');

    success.hide();
    warming.hide();

    $('#contact_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            id: {
                validators: {
                    stringLength: {
                        //min: 2,
                    },
                    notEmpty: {
                        message: 'Please supply user Id'
                    }
                }
            },
            name: {
                validators: {
                    stringLength: {
                        //min: 2,
                    },
                    notEmpty: {
                        message: 'Please supply user name'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'Please supply user password'
                    },
                    // emailAddress: {
                    //     message: 'Please supply a valid email address'
                    // }
                }
            },
            age: {
                validators: {
                    // notEmpty: {
                    //     message: 'Please supply your phone number'
                    // },
                    // phone: {
                    //     country: 'US',
                    //     message: 'Please supply a vaild phone number with area code'
                    // }
                }
            },
            address: {
                validators: {
                    // stringLength: {
                    //     min: 8,
                    // },
                    // notEmpty: {
                    //     message: 'Please supply your street address'
                    // }
                }
            },
            // city: {
            //     validators: {
            //         stringLength: {
            //             min: 4,
            //         },
            //         notEmpty: {
            //             message: 'Please supply your city'
            //         }
            //     }
            // },
            // state: {
            //     validators: {
            //         notEmpty: {
            //             message: 'Please select your state'
            //         }
            //     }
            // },
            // zip: {
            //     validators: {
            //         notEmpty: {
            //             message: 'Please supply your zip code'
            //         },
            //         zipCode: {
            //             country: 'US',
            //             message: 'Please supply a vaild zip code'
            //         }
            //     }
            // },
            // comment: {
            //     validators: {
            //         stringLength: {
            //             min: 10,
            //             max: 200,
            //             message:'Please enter at least 10 characters and no more than 200'
            //         },
            //         notEmpty: {
            //             message: 'Please supply a description of your project'
            //         }
            //     }
            // }
        }
    })
        $('#submitButton').click(function(e) {
            $('#contact_form').data('bootstrapValidator').resetForm();
            // Prevent form submission
            //e.preventDefault();

            // Get the form instance
            var id=$('#inputId').val();
            var userName=$('#inputName').val();
            var age=$('#inputAge').val();
            var address=$('#inputAddress').val();
            var password=$('#inputPassword').val();
            var optionUser=document.getElementById('optionUser').checked;
            var optionAdmin=document.getElementById('optionAdmin').checked;
            var url;

            var UorA='user';
            if(optionAdmin){UorA='admin';}

            var formData=new FormData();
            formData.append('id',id);
            formData.append('userName',userName);
            formData.append('password',password);
            formData.append('age',age);
            formData.append('address',address);
            formData.append('UorA',UorA);

            var formTitle=document.getElementById('formTitle');
            if(formTitle.innerText=="Update User"){
                url="/admin/updateUserInformation";
            }
            else if(formTitle.innerText=="Insert User"){
                url="/admin/insertUserInformation";
            }

            $.ajax({
                type: "post",
                url: url,
                dataType: "json",
                //async: false,
                data:formData,
                cache: false,
                processData : false,//用于对data参数进行序列化处理 这里必须false
                contentType: false,
                //contentType: "application/json;charset=utf8",
                success: function(data) {
                    //alert(data['response']);
                    warming.hide();
                    success.html(data['response']);
                    success.slideDown({ opacity: "show" }, "slow"); // Do something ...
                    updateUserInformation();
                    $('#contact_form').data('bootstrapValidator').resetForm();
                    return false;
                },

                error: function (e){
                    alert(e.responseText);
                    success.hide();
                    warming.html(e.responseText);
                    warming.slideDown({ opacity: "show" }, "slow"); // Do something ...
                    $('#contact_form').data('bootstrapValidator').resetForm();
                    return false;
                }
            });

            return false;
            // Get the BootstrapValidator instance
            // var bv = $form.data('bootstrapValidator');
            //
            // // Use Ajax to submit form data
            // $.post($form.attr('action'), $form.serialize(), function(result) {
            //     console.log(result);
            // }, 'json');
        });
});


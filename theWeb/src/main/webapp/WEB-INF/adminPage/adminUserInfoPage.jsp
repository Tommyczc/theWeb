<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>User Information</h2>
<div class="table-wrapper">

    <button id="insertButton" class="btn btn-info"onclick="insertUserInfoRequest()">Insert New User</button>
    <br><br>
    <table class="fl-table">
        <thead>
        <tr>
            <th>Id</th>
            <th>UserName</th>
            <th>Password</th>
            <th>Age</th>
            <th>Address</th>
            <th>Role</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody id="userInfoBody">

        <tbody>
    </table>


    <div id="userUpdateModal" class="modal">
    <div class="container">
        <form class="well form-horizontal align-content-lg-center" action=" " method="post"  id="contact_form">
            <fieldset>

                <!-- Form Name -->
                <legend class="text-warning" id="formTitle"></legend>

                <!-- Text input-->

                <div class="form-group">
                    <label class="col-md-4 control-label text-info">User Id <code>(unable to change)</code></label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input name="id" id="inputId" placeholder="Id" class="form-control"  type="text">
                        </div>
                    </div>
                </div>

                <!-- Text input-->

                <div class="form-group">
                    <label class="col-md-4 control-label text-info" >Name <code>(unable to change)</code></label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input name="name" id="inputName" placeholder="Name" class="form-control"  type="text">
                        </div>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label text-info">Password <code>(Compulsory)</code></label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input name="password" id="inputPassword" placeholder="Password" class="form-control"  type="text">
                        </div>
                    </div>
                </div>


                <!-- Text input-->

                <div class="form-group">
                    <label class="col-md-4 control-label text-info">age <code>(optional)</code></label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                            <input name="age" id="inputAge" placeholder="age" class="form-control" type="text" pattern="^\d{1,3}$">
                        </div>
                    </div>
                </div>

                <!-- Text input-->

                <div class="form-group">
                    <label class="col-md-4 control-label text-info">Address <code>(optional)</code></label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon "><i class="glyphicon glyphicon-home"></i></span>
                            <input name="address" id="inputAddress" placeholder="Address" class="form-control" type="text">
                        </div>
                    </div>
                </div>

                <!-- Text input-->

<%--                <div class="form-group">--%>
<%--                    <label class="col-md-4 control-label">City</label>--%>
<%--                    <div class="col-md-4 inputGroupContainer">--%>
<%--                        <div class="input-group">--%>
<%--                            <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>--%>
<%--                            <input name="city" placeholder="city" class="form-control"  type="text">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <!-- Select Basic -->

<%--                <div class="form-group">--%>
<%--                    <label class="col-md-4 control-label">State</label>--%>
<%--                    <div class="col-md-4 selectContainer">--%>
<%--                        <div class="input-group">--%>
<%--                            <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>--%>
<%--                            <select name="state" class="form-control selectpicker" >--%>
<%--                                <option value=" " >Please select your state</option>--%>
<%--                                <option>Alabama</option>--%>
<%--                                <option>Alaska</option>--%>
<%--                                <option >Arizona</option>--%>
<%--                                <option >Arkansas</option>--%>
<%--                                <option>Wisconsin</option>--%>
<%--                                <option >Wyoming</option>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <!-- Text input-->

<%--                <div class="form-group">--%>
<%--                    <label class="col-md-4 control-label">Zip Code</label>--%>
<%--                    <div class="col-md-4 inputGroupContainer">--%>
<%--                        <div class="input-group">--%>
<%--                            <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>--%>
<%--                            <input name="zip" placeholder="Zip Code" class="form-control"  type="text">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <!-- Text input-->
<%--                <div class="form-group">--%>
<%--                    <label class="col-md-4 control-label">Website or domain name</label>--%>
<%--                    <div class="col-md-4 inputGroupContainer">--%>
<%--                        <div class="input-group">--%>
<%--                            <span class="input-group-addon"><i class="glyphicon glyphicon-globe"></i></span>--%>
<%--                            <input name="website" placeholder="Website or domain name" class="form-control" type="text">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <!-- radio checks -->
                <div class="form-group">
                    <label class="col-md-4 control-label text-info">Is user or admin? <code>(compulsory, default user)</code></label>
                    <div class="col-md-4">
                        <div class="radio text-warning">
                            <label>
                                <input type="radio" id="optionUser" name="hosting" value="user" /> User
                            </label>
                        </div>
                        <div class="radio text-warning">
                            <label>
                                <input type="radio" id="optionAdmin" name="hosting" value="admin"/> Admin
                            </label>
                        </div>
                    </div>
                </div>

                <!-- Text area -->

<%--                <div class="form-group">--%>
<%--                    <label class="col-md-4 control-label">Project Description</label>--%>
<%--                    <div class="col-md-4 inputGroupContainer">--%>
<%--                        <div class="input-group">--%>
<%--                            <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>--%>
<%--                            <textarea class="form-control" name="comment" placeholder="Project Description"></textarea>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <!-- Success message -->
                <div class="alert alert-success" role="alert" id="success_message" > <i class="glyphicon glyphicon-thumbs-up"></i> </div>
                <div class="alert alert-warning" role="alert" id="warming_message" > <i class="glyphicon glyphicon-thumbs-up"></i> </div>
                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label"></label>
                    <div class="col-md-4">
                        <button id="cancelButton" class="btn btn-success"onclick="document.getElementById('userUpdateModal').style.display='none'">cancel</button>
                        <button type="submit" id="submitButton" class="btn btn-warning" >update <span class="glyphicon glyphicon-send"></span></button>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>
</div><!-- /.container -->
</div>
</div>

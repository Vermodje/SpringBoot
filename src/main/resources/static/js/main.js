$(document).ready(function () {
        var $userTable = $("#users tbody");
        var $userTableRow;
        var $btnInsert = $('#insertBtn');
        var $btnDelete;
        var $btnEdit;
        var $editModalWindow = $("#exampleModal");
        var $userId = $("#user-id");
        var $userEmail = $("#user-email");
        var $userLogin = $("#user-login");
        var $userName = $("#user-name");
        var $userPassword = $("#user-password");
        var $btnUpdate = $("#updateBtn");
        $.ajax({
                url: "http://localhost:8081/admin/",
                type: "GET",
                success: function (data) {
                    $.each(data, function (i, user) {
                        var k = user.roles.length;
                        $userTable.append(
                            "<tr><td class=\"text-center align-middle\" rowspan='" + k +"'>" + user.id + "</td>" +
                            "<td class=\"text-center align-middle\">" + user.roles[0].id + "</td>" +
                            "<td class=\"text-center align-middle\">" + user.roles[0].role + "</td>" +
                            "<td class=\"text-center align-middle\" rowspan='" + k +"'>" + user.login + "</td>" +
                            "<td class=\"text-center align-middle\" rowspan='" + k +"'>" + user.password + "</td>" +
                            "<td class=\"text-center align-middle\" rowspan='" + k +"'>" + user.name + "</td>" +
                            "<td class=\"text-center align-middle\" rowspan='" + k +"'>" + user.email + "</td>" +
                            "<td class=\"text-center align-middle\" rowspan='" + k +"'><button type=\"button\" class=\"btn btn-primary btn-edit mr-2\" data-toggle=\"modal\" data-target=\"#exampleModal\">" + "Edit" + "</button></td>" +
                            "<td class=\"text-center align-middle\" rowspan='" + k +"'><button type=\"button\" class=\"btn btn-primary btn-delete\">" + "Delete" + "</button></td></tr>"
                        );
                        while (k > 1){
                            $userTable.append(
                                "<tr><td class=\"text-center align-middle\">" + user.roles[k-1].id + "</td>" +
                                "<td class=\"text-center align-middle\">" + user.roles[k-1].role + "</td></tr>"
                            );
                            k--;
                        }
                        $userTableRow = $userTable.find("tr:nth-child(" + (i + 1) + ")");
                        $btnDelete = $userTableRow.find("td:has(button.btn-delete)").children();
                        $btnEdit = $userTableRow.find("td:has(button.btn-edit)").children();
                        $btnEdit.on('click', function () {
                            $editModalWindow.find(".modal-title").text("Edit user" + " " + user.login);
                            $.ajax({
                                url: "http://localhost:8081/admin/user",
                                type: "GET",
                                data: {
                                    id: user.id
                                },
                                success: function (userForEdit) {
                                    $userId.attr("value", userForEdit.id);
                                    $userEmail.attr("value", userForEdit.email);
                                    $userLogin.attr("value", userForEdit.login);
                                    $userName.attr("value", userForEdit.name);
                                    $userPassword.attr("value", userForEdit.password);
                                    for(var i = 0; i < userForEdit.roles.length; i++){
                                        $("#user-role-" + userForEdit.roles[i].id).prop('checked', true);
                                    }

                                }
                            });
                        });

                        $btnDelete.on('click', function () {
                            $.ajax({
                                url: "http://localhost:8081/admin/delete",
                                type: "DELETE",
                                data: {
                                    id: user.id
                                },
                                success: function () {
                                    location.reload();
                                }
                            });
                        });

                    });

                }

            }
        );
        $btnInsert.on('click', function () {
            let $roles = checkRoles('input[id^="roleCheck"]');
            $.ajax({
                url: "http://localhost:8081/admin/insert",
                type: "POST",
                data: JSON.stringify({
                    email: $('#exampleInputEmail1').val(),
                    name: $('#exampleInputName1').val(),
                    login: $('#exampleInputLogin1').val(),
                    password: $('#exampleInputPassword1').val(),
                    roles: $roles
                }),
                contentType: 'application/json',
                success: function () {
                    location.reload();
                },
                error: function () {
                    $("#insert-error-msg").text("User with same login already exist please try again");
                }
            });
        });
        $btnUpdate.on('click', function () {
          let $roles = checkRoles('input[id^="user-role-"]');
            $.ajax({
                url: "http://localhost:8081/admin/update?id=" + $userId.val(),
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify({
                    id: $userId.val(),
                    email: $userEmail.val(),
                    name: $userName.val(),
                    login: $userLogin.val(),
                    password: $userPassword.val(),
                    roles: $roles
                }),
                success: function (data) {
                    location.reload();
                },
                error: function () {
                    $("#update-error-msg").text("User with same login already exist please try again");
                }
            });
        });
        function checkRoles(input) {
            let $selectedRoles = [];
            $(input).each(function () {
                if( $(this).is(":checked")) {
                    $(this).prop('checked',true);
                    $selectedRoles.push($(this).prop('checked'));
                }
                else {
                    $selectedRoles.push($(this).prop('checked'))
                }
            });
            let $roles = [];
            for(var i = 0; i < $selectedRoles.length; i++){
                if($selectedRoles[i] === true){
                    $roles.push({
                        "id": i + 1,
                        "role": $('#roleCheck' + (i+1)).val()
                    });
                }
            }
            return $roles;
        }
    }
);
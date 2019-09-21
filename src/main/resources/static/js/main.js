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
        var $userRole = $("#user-role");
        var $btnUpdate = $("#updateBtn");
        $.ajax({
                url: "http://localhost:8081/admin/",
                type: "GET",
                success: function (data) {
                    $.each(data, function (i, user) {
                        var k = user.roles.length;
                        $userTable.append(
                            "<tr><td class=\"userRow" + (i+1) + "\"  rowspan=\"" + k + " \" scope=\"col\">" + user.id + "</td>" +
                            "<td scope=\"col\">" + user.roles[k-1].id + "</td>" +
                            "<td scope=\"col\">" + user.roles[k-1].role + "</td>>" +
                            "<td rowspan=\"" + k + " \" scope=\"col\">" + user.login + "</td>" +
                            "<td rowspan=\"" + k + " \" scope=\"col\">" + user.password + "</td>" +
                            "<td rowspan=\"" + k + " \" scope=\"col\">" + user.name + "</td>" +
                            "<td rowspan=\"" + k + " \" scope=\"col\">" + user.email + "</td>" +
                            "<td rowspan=\"" + k + " \" scope=\"col\"><button type=\"button\" class=\"btn btn-primary btn-edit\" data-toggle=\"modal\" data-target=\"#exampleModal\">" + "Edit" + "</button></td>" +
                            "<td rowspan=\"" + k + " \" scope=\"col\"><button type=\"button\" class=\"btn btn-primary btn-delete\">" + "Delete" + "</button></td></tr>"
                        );
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
                                    $userRole.attr("value", userForEdit.roles[0].role);
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
            let $selectedRoles = [];
            let $rolesIndexes = [];
            let $rolesName = [];
            $('input[id^="roleCheck"]').each(function () {
                if( $(this).is(":checked")) {
                    $(this).prop('checked',true);
                    $selectedRoles.push($(this).prop('checked'));
                }
                else {
                    $selectedRoles.push($(this).prop('checked'))
                }
            })
            for(var i = 0; i < $selectedRoles.length; i++){
                if($selectedRoles[i] === true){
                    $rolesIndexes.push(i + 1);
                }
            }
            for(var i = 0; i < $rolesIndexes.length; i++){
                $rolesName.push($("#roleCheck" + $rolesIndexes[i]).val());
            }
            let $numberOfUserRoles = $rolesName.length;
            let $roles = [];
            for(var i = 0; i < $numberOfUserRoles; i++){
                $roles.push( {
                    "id": $rolesIndexes[i],
                    "role": $rolesName[i]
                });
            }
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
            let $roleId;
            let $role = $userRole.val();
            if ($role == "ROLE_ADMIN") {
                $roleId = 1;
            } else {
                $roleId = 2;
                $role = "ROLE_USER";
            }
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
                    roles: [
                        {
                            id: $roleId,
                            role: $role
                        }
                    ]
                }),
                success: function (data) {
                    location.reload();
                },
                error: function () {
                    $("#update-error-msg").text("User with same login already exist please try again");
                }
            });
        });
    }
);
$(document).ready(function () {
    init();
});

function init() {
    enable();
    getMembers();
}

function enable() {
    $("#btnSubmit").on("click", postMember);
    $("#btnSearch").on("click", searchMember);

}

function getMembers() {
    $.ajax({
        type: "GET",
        url: "/getAllMembers",
        success: function (data) {
        appendMember(data);
        }
    });
}

function postMember(event) {
    event.preventDefault();

    var newMember = {
        id : $("#txtId").val(),
        firstName : $("#txtFName").val(),
        lastName : $("#txtLName").val()

    };

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(newMember),
        url: "/add/member",
        success: function (data) {
            getMembers();
        }
    });
}

function searchMember(event) {
    event.preventDefault();

    var searchId = $("#txtSearch").val();

    $.ajax({
        type: "GET",
        url: "/get/" + searchId,
        success: function (data) {
            appendMember(data)
        }
    });
}

function appendMember(memberArray) {
    $("#container").empty();
    for (var i = 0; i < memberArray.length; i++){
        var person = memberArray[i];

        $("#container").append("<div></div>");
        var el = $("#container").children().last();
        el.append("<p>" + person.id + " : " +
                            person.firstName + " " +
                            person.lastName + "</p>");
    }
}
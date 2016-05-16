$(document).ready(function () {
    $('#createConversationForm').submit(function (event) {
        event.preventDefault();

        var conversation = {
            userIds: $('#participants').val().split(',')
        };
        $.ajax({
            data: JSON.stringify(conversation),
            contentType: 'application/json',
            method: 'post',
            url: 'conversations',
            success: function () {
                $('#participants').val('');
                refreshConversations();
            }
        });
    });

    loadConversations();
});

function loadConversations() {
    $.ajax({
        method: 'get',
        url: 'conversations/search',
        success: function (data, textStatus, jqXHR) {
            var conversations = data;
            conversations.forEach(function (element, index, array) {
                $('#conversationList').append($('<li>').append(getConversationSummary(element)));
            })
        }
    })
}

function refreshConversations() {
    $('#conversationList').empty();
    loadConversations();
}

function getConversationSummary(conversation) {
    var result = "";

    result += 'ID: ' + conversation.conversationId;

    result += "<br>Participants: "
    var participants = conversation.participants;
    (participants).forEach(function (element, index, array) {
        result += '[' + element.user.firstName + ' ' + element.user.lastName + ']';
    });
    return result;
}
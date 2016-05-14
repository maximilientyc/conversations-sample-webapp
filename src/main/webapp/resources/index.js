$(document).ready(function () {
    $('#createConversationForm').submit(function (event) {
        event.preventDefault();

        var conversation = {
            'userIds': $('#participants').val().split(',')
        };
        $.ajax({
            data: JSON.stringify(conversation),
            contentType: 'application/json',
            method: 'post',
            url: 'conversations',
            success: function () {
                $('#participants').val('');
            }
        });
    });
});
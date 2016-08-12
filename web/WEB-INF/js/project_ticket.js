$(document).ready(function () {
    bindUploadingFileHandler($('#inputFile'), $('#fileSizeError'), $('#submitNewTicketBtn'), $('#fileNameSpan'));
    $('#submitFilterBtn').click(ajaxFilterRequest);
});

function rowClicked(value) {
    location.href = "/ticket/" + value;
}

function resetFilter() {

}

function ajaxFilterRequest() {
    $.ajax($(location).attr('href') + '/filter', {
        type: 'POST',
        data: {
            status: $('#statusFilter').val(),
            priority: $('#priorityFilter').val(),
            datetime: $('#datetimeFilter').val()
        },
        success: function (response) {
            $(".rowTicket").remove();
            $("#ticketsTable").append(response);
        }
    });
}
$(document).ready(function () {
    bindUploadingFileHandler($('#inputFile'), $('#fileSizeError'), $('#submitNewTicketBtn'), $('#fileNameSpan'));
    $('#filterDateTimePicker').datetimepicker();
});

function rowClicked(value) {
    location.href = "/ticket/" + value;
}

function resetFilter() {

}
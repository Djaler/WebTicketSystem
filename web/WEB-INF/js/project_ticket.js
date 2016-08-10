$(document).ready(function () {
    bindUploadingFileHandler($('#inputFile'), $('#fileSizeError'), $('#submitNewTicketBtn'), $('#fileNameSpan'));
});

function rowClicked(value) {
    location.href = "/ticket/" + value;
}
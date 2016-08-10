var MAX_FILE_SIZE = 16777216;

function bindUploadingFileHandler(inputControl, fileSizeErrorText, submitBtn, fileNameContainer) {
    inputControl.bind('change', function () {
        if (this.files[0].size > MAX_FILE_SIZE) {
            fileSizeErrorText.show();
            submitBtn.addClass("disabled");
            submitBtn.attr('disabled', 'disabled');
        } else {
            fileSizeErrorText.hide();
            submitBtn.removeAttr('disabled');
            submitBtn.removeClass('disabled');
        }
        fileNameContainer.html(inputControl.val().split('\\').pop());
    });
}
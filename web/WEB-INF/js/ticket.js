/**
 * Created by djaler on 14.08.16.
 */
$('select').change(function (e) {
    var value = this.value;
    var parameter = this.name;
    $.ajax({
        type: 'POST',
        data: ({
            parameter: parameter,
            value: value
        })
    });
});

$('#assign_self').click(function () {
    $.ajax({
        type: 'POST',
        url: $(location).attr('pathname') + '/assign_self',
        contentType: 'application/json; charset=UTF-8',
        success: function (name) {
            $('#assignee').text(name);
        }
    });
});

$('form').submit(function (e) {
    e.preventDefault();

    var formData = new FormData();
    formData.append('file', $('#inputFile')[0].files[0]);
    formData.append('text', $('textarea').val());

    $.ajax({
        type: 'POST',
        url: $(location).attr('pathname') + '/add_comment',
        data: formData,
        processData: false,
        contentType: false,
        success: function (comment) {
            var text = comment['text'];
            var username = comment['username'];
            var datetime = comment['datetime'];
            var footer = username + ' ' + at + ' ' + datetime;

            $('form').trigger('reset');

            var code = '<div class="row">' +
                '<div class="col-md-12">' +
                '<blockquote class="blockquote">' +
                '<p class="m-b-0">' + text + '</p>';
            if (comment['attachName'] != null) {
                var href = comment['attachHref'];
                code += '<a class="btn btn-default" href="' + href + '">' +
                    comment['attachName'] + '</a>';
            }
            code += '<footer class="blockquote-footer">' + footer + '</footer>' +
                '</blockquote>' +
                '</div>' +
                '</div>';
            $('#comments').append(code);
        }
    });
});
bindUploadingFileHandler($('#inputFile'), $('#fileSizeError'), $('#submit'), $('#fileNameSpan'));
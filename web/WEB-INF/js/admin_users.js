$('select').change(function (e) {
    var role = this.value;
    var login = this.name;
    $.ajax({
        type: 'POST',
        data: ({
            login: login,
            role: role
        })
    });
});

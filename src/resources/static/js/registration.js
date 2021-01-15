$(function () {
    $('#registrationButton').click(function (e) {

        //Prevent default submission of form
        e.preventDefault();

        //Remove previous errors
        $('input').next('span').remove();

        $.post({
            url: '/registration',
            data: $('#registrationForm').serialize(),
            success: function (res) {
                var username = document.getElementById("username").value;

                if (res.validated) {
                    //take your successful action here; you may want to redirect to another page
                    $('li[name="section1"]').before('<div class="alert alert-success" role="alert">' +
                        'User with username ' + username + ' is registered' +
                        '</div>');
                } else {
                    $.each(res.errorMessages, function (key, value) {
                        if (key == 'userExist') {
                            $('li[name="section1"]').before('<div class="error alert alert-danger" role="alert">' + value + '</div>');
                        } else {
                            $('input[name=' + key + ']').after('<span class="badge rounded-pill bg-warning">' + value + '</span>');
                        }

                    });
                }
            }
        })
    });
});
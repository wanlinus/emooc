let logout = function () {
    $.post({
        url: '/logout',
        success: function () {
            window.location.href = ("/login");
        }
    })
}
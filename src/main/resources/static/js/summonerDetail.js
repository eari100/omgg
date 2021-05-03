var main = {
    init : function() {
        var _this = this
        $('#btn-refresh').on('click', function() {
            _this.renewData()
        })
    },
    renewData : function() {
        var data = {
            name: $('#summonerName').val()
        }

        $.ajax({
            type: 'POST',
            url: '/api/renew',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            window.location.href = '/userName='+data.name;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}

main.init()
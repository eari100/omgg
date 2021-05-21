var main = {
    init : function() {
        var _this = this

        $('#btn-refresh').click(function(e) {
            e.preventDefault();
            var l = Ladda.create(this);
            l.start()
            l.setProgress(0.3)

            var data = {
                name: $('#summonerName').val()
            }

            $.ajax({
                type: 'POST',
                url: '/api/renew',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data),
                success: function () {
                    l.setProgress(0.9);
                }
            }).done(function() {
                location.reload(true)
            }).fail(function(error) {
                alert(JSON.stringify(error))
            }).always(function() { l.stop(); });
        })

        $('#btn-gameMore').on('click', function() {
            _this.gameMore()
        })
    },
    gameMore : function() {
        var data = {
            accountId: 'yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr', // 임시 데이터
            name: $('#summonerName').val(),
            strIndex: 200,
            endIndex: 400  // 임시 데이터
        }

        $.ajax({
            type: 'POST',
            url: '/api/matchesLeadMore',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function (attr) {
                console.log(attr)
                alert('api를 불러오는 데 성공하였습니다. 콘솔 창을 확인해보세요.')
            },
            error: function (e) {
                alert('매치 기록을 더 가져오는 도중 문제가 있었습니다. 잠시 후 시도해주세요.')
            }
        }).done(function() {
            window.location.href = '#'
        }).fail(function(error) {
            alert(JSON.stringify(error))
        })
    }
}

main.init()
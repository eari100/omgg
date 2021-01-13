var main = {
    init : function() {
        var _this = this
        $('#btn-find').on('click', function() {
            _this.findByName()
        })

        $('#summonerName').keydown(function(key) {
            if (key.keyCode == 13) {
                _this.findByName()
                return false
            }
        })
    },
    findByName : function() {
        var name = $('#summonerName').val()
        window.location.href = '/userName='+name
    }
}

main.init()
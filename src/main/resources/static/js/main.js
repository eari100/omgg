var main = {
    init : function() {
        var _this = this

        $('#summonerName').keydown(function(key) {
            if (key.keyCode == 13) {
                _this.findByName()
                return false
            }
        })
    },
    findByName : function() {
        loadingBarStart()
        var name = $('#summonerName').val()
        window.location.href = '/userName='+name
    }
}

main.init()
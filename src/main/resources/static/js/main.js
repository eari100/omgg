var main = {
    init : function() {
        var _this = this
        $('#btn-find').on('click', function() {
            _this.findByName()
        })
    },
    findByName : function() {
        var name = $('#name').val()
        window.location.href = '/userName='+name
    }
}

main.init()
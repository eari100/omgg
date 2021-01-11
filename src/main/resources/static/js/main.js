var main = {
    init : function() {
        var _this = this
        $('#btn-find').on('click', function() {
            _this.findByName()
        })
    },
    findByName : function() {
        var data = $('#name').val()
        alert('입력 값은 '+data+'입니다')
    }
}

main.init()
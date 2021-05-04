function loadingBarStart() {
    var backHeight = $(document).height(); //뒷 배경의 상하 폭
    var backWidth = window.document.body.clientWidth; //뒷 배경의 좌우 폭
    var backGroundCover = "<div id='back'></div>"; //뒷 배경을 감쌀 커버
    var loadingBarImage = ''; //가운데 띄워 줄 이미지
    loadingBarImage += "<div id='loadingBar'>";
    loadingBarImage += "    <img src='../img/loadingbar.gif'/>"; //로딩 바 이미지
    loadingBarImage += "    <div>데이터를 가져오는 중입니다...</div>"
    loadingBarImage += "</div>";
    $('body').append(backGroundCover).append(loadingBarImage);
    $('#back').css({ 'width': backWidth, 'height': backHeight, 'opacity': '0.3' }); // 뒷 배경 어둡게
    $('#back').show();
    $('#loadingBar').show();
}

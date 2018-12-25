$(function () {
    $('.page-link').click(function () {
        var pageIndex = $(this).attr("pageIndex");
        var url = $('#form1').attr("action");
        if(url.indexOf("?") == -1){
                url += "?pageIndex="+ pageIndex;
            }else {
            url += "&pageIndex=" + pageIndex;
            }
        $('#form1').attr("action",url);
        $('#form1').submit();
    })
});
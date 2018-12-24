$(function () {
    $('.page-link').click(function () {
        var pageIndex = $(this).attr("pageIndex");
        var pageForm = document.form1;
        var url = pageForm.getAttribute("action");
        url+= "?pageIndex="+pageIndex;
        pageForm.action = url;
        pageForm.submit();
        // alert(url);
        // if(url.indexOf("?") == -1){
        //     url = url + "?pageIndex="+ pageIndex;
        // }else {
            //url = url + '?pageIndex=' + pageIndex;
        // }
        alert(url);
        // $("#form1").attr("action",url).submit();
    })
});
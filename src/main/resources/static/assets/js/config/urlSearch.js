function getRequestParamValue(param){
    var currentUrl = window.location.href;
    var url = new URL(currentUrl);
    var paramValue = url.searchParams.get(param);
    return paramValue;
}

function _(id){
    return document.getElementById(id);
}
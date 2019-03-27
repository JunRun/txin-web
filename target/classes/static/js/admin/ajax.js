//自定义post请求
function post(url, params, successFunction){
    $.post(url, params, function(response){
        successFunction(response);
    }, "json");
}
//自定义get请求
function get(url, successFunction){
    $.get(url, function(response){
        successFunction(response);
    }, "json");
}
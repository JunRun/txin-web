/**
 * 固定宽度，自动适应
 */
function autosize () {
    var width = window.screen.width;
    var fixedW = 320;
    var scale = width / fixedW;
    var meta = document.createElement('meta');
    meta.setAttribute('name','viewport');
    meta.setAttribute('content','width='+fixedW+', initial-scale='+scale+', maximum-scale='+scale+', user-scalable=0');
    document.getElementsByTagName('head')[0].appendChild(meta);
}
autosize();
window.onresize = function () {
    autosize();
}

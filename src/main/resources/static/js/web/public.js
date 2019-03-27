function header() { //头部导航下拉
    var navItem = $(".header-nav-item");
    var documentWidth = $(document).width()
    var isMobile = documentWidth < 767 ? true : false
    if(isMobile) {
        navItem.click(function(){
            $(this).find('.header-nav-item-child').slideToggle();
        })
    }else {
        navItem.hover(function(){
            if($(this).find('div').hasClass('header-nav-item-child')) {
                $('.header-nav-item').find('.header-nav-item-child').hide()
                $(this).find('.header-nav-item-child').show();
                $(this).find('a').addClass('active')
            }
        }, function () {
            if($(this).find('div').hasClass('header-nav-item-child')) {
                $('.header-nav-item').find('.header-nav-item-child').hide()
                $(this).find('a').removeClass('active')
            }
        })
    }
}

function seniorSearch(){ //搜索框展开或收缩
    var icon = $(".public-senior-search .senior-search-box .senior-search-item .header .icon");
    icon.click(function(){
        var type = $(this).parent().next();

        if(type.hasClass('type-close')) {
            $(this).removeClass('open');
            $(this).addClass('close');
            type.removeClass('type-close')
        }else {
            $(this).removeClass('close');
            $(this).addClass('open');
            type.addClass('type-close');
        }

    })
}

function model() {
    $('.model .confirm').click(function() {
        $(this).parent().parent().hide();
    })
}

function showModel() {
    $('.model').show();
}

function hideProductSelect() {
    $('.product-select-con .hide-product-select').click(function () {
        $('.product-select-con').removeClass('opened');
        $('.product-select-con').addClass('closed');
    })
}
function showProductSelect() {
    $('.product-select-con .show-product-select').click(function () {
        $('.product-select-con').removeClass('closed');
        $('.product-select-con').addClass('opened');
    })
}

function selectProduct() {
    var documentWidth = $(document).width()
    if(documentWidth >=768) {
        showCompareModel()
    }
}

function showCompareModel() {
    $('.product-select-con').removeClass('closed');
    $('.product-select-con').addClass('opened');
}

function hideCompareModel() {
    $('.product-select-con').removeClass('opened');
    $('.product-select-con').addClass('closed');
}

function appendInfo(){
    $('.appendInfo').click(function () {
            if(ids.length<4){
               if($(this).is(":checked")){
                 var str = ' <div  id="'+$(this).attr("data-id")+'" class="product" >' +
                     '<img src="' +$(this).attr("data-imgpath") + '" alt="">' +
                    '<div>' +
                     '<p  class="title ellipsis">' +$(this).attr("data-name") + '</p>' +
                     '<p  class="subtitle ellipsis visible-xs">' +$(this).attr("data-processor") + '</p>' +
                     '</div>'+
                     '<div data-id="'+$(this).attr("data-id")+'" class="close" id="deleteDiv"></div>' +
                     '</div>'
                 $(".product-list-item").prepend(str);
                 ids.push($(this).attr("data-id"));
                 if(ids.length>=4){
                     showModel();
                 }
             }else {
                 var index=$.inArray($(this).attr("data-id"),ids);
                 ids=ids.slice(index,1);
                   var node=document.getElementById(""+$(this).attr("data-id")+"");
                   node.remove();
             }
            }else {

                var node=document.getElementById(""+$(this).attr("data-id")+"P");
                node.checked=false;
                showModel();
            }
    })
}
function deleteInfo() {
    $('.product-list-item').on('click','.close',function () {

        var index=$.inArray($(this).attr("data-id"),ids);
         ids=ids.slice(index,1);
        var t=$(this).parent();

        var node=document.getElementById(""+$(this).attr("data-id")+"P");
        node.checked=false;
        t.remove();
    })

}
var ids=[];
(function(){
    header()
    seniorSearch()
    model()
    showProductSelect()
    hideProductSelect()
    hideCompareModel()
    deleteInfo()
    appendInfo()
})()



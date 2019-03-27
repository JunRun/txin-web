/**
 * Copyright (c) 2016 hustcc
 * License: MIT
 * Version: v1.0.0
 * GitHub: https://github.com/hustcc/x-return-top.js
 **/
! function() {
    var opt,__returnTopInterval,xEle,xBox;
    //x-return-top.js执行方法
    function xReturnTop() {
        __getOption();
        __addHtml();
        __addCss();
        //防止屏蔽已有的事件
        var oldOnscroll = window.onscroll;
        window.onscroll = function () {
            if(typeof oldOnscroll == 'function'){
                oldOnscroll.call(this);
            }
            __onWindowScroll();
        }; //卷动事件
        xEle.onclick = __animateReturnTop; //点击
    }

    function __getElesByTag(name) {
        return document.getElementsByTagName(name);
    }

    function __getAttr(node, attrName, defaultValue) {
        return node.getAttribute(attrName) || defaultValue;
    }

    function __getOption() {
        var scripts = __getElesByTag('script'), script = scripts[scripts.length - 1];
        opt = {
            l: __getAttr(script, 'left', '90%'),
            b: __getAttr(script, 'bottom', '15%'),
            t: __getAttr(script, 'text', '返回顶部')
        };
    }
    function __getScrollTopOffset() {
        return window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    }

    function __setScrollTopOffset(value) {
        if (document.body && document.body.scrollTop) {
            document.body.scrollTop = value;
        }
        if (document.documentElement && document.documentElement.scrollTop) {
            document.documentElement.scrollTop = value;
        }
    }

    function __animateHideXReturnTop() {
        xBox.style.bottom = '-60px';
    }

    //显示按钮动画
    function __animateShowXReturnTop() {
        xBox.style.bottom = opt.b;
    }

    function __scrollMove() {
        __setScrollTopOffset(__getScrollTopOffset() / 1.15);
        //滚动到最上面的时候，清除定时器
        if (__getScrollTopOffset() < 1) {
            clearInterval(__returnTopInterval);
            __returnTopInterval = null;
        }
    }
    function __animateReturnTop() {
        __returnTopInterval = setInterval(__scrollMove, 10); //每10毫秒只是一次方法
    }

    function __onWindowScroll() {
        if (!__returnTopInterval) __getScrollTopOffset() > 5 ? __animateShowXReturnTop() : __animateHideXReturnTop();
    }

    function __createNonde(tag) {
        return document.createElement(tag);
    }

    function __createText(t) {
        return document.createTextNode(t);
    }
    function __append(p, c) {
        p.appendChild(c);
    }

    function __addHtml() {
        xEle = __createNonde('div');
        xEle.id = 'xReturnTop';

        xBox = __createNonde('div');

        var xA = __createNonde('a');
        xA.href = 'javascript:void(0)';

        var xSpan = __createNonde('span');
        __append(xSpan, __createText(opt.t));

        __append(xBox, xA);
        __append(xBox, xSpan);
        __append(xEle, xBox);
        __append(__getElesByTag('body')[0], xEle);
    }

    function __addCss() {
        var x = document.createElement('div'),
            cssStr = `#xReturnTop{
                font-size:12px;position:fixed;
            }
            #xReturnTop div,
            #xReturnTop div a{
            -webkit-border-radius:50%;
            -moz-border-radius:50%;
            border-radius:50%;
            width:55px;
            height:55px;
            display:block;
            }
            #xReturnTop div{
            transition:0.3s;
            position:fixed;
            left:${opt.l};
            bottom:-60px;
            cursor:pointer;
            }
            #xReturnTop div a{
             background-image:url("/images/web/return-top-666.png");
             background-position: center;
             background-repeat:no-repeat;
             background-size: 30px 30px;
              border: 2px solid #666;
            }
            #xReturnTop div a:hover{
             background-color:#2870C2; 
             border-color: transparent;
             background-image:url("/images/web/return-top-fff.png");
            }
            #xReturnTop div span{position:absolute;bottom:-25px;color:#666;}
            #xReturnTop div a:hover +span {
                color: #2870C2;
            }`;
        x.innerHTML = 'x<style>' + cssStr + '</style>';
        __append(__getElesByTag('head')[0], x.lastChild);
    }
    xReturnTop();
}();

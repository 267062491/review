$(function(){
    loadProperties();
});

function loadProperties(){
    $.i18n.properties({//加载资浏览器语言对应的资源文件
        name:'strings', //资源文件名称
        path:'/static/i18n/', //资源文件路径
        mode:'map', //用Map的方式使用资源文件中的值
        callback: function() {//加载成功后设置显示内容
            $("[data-i18n-id]").each(function(){
                var i18nKey = $(this).data('i18n-id');
                //
                if($(this).is('input')){
                    $(this).val($.i18n.prop(i18nKey));
                } else if ($(this).is('div')) {
                    $(this).html($.i18n.prop(i18nKey));
                } else {
                    $(this).html($.i18n.prop(i18nKey));
                }
            });
        }
    });
}
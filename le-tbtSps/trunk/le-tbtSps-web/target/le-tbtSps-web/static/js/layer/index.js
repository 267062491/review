/**************************************************************

 @Name: 左侧菜单栏、top页及tab切换栏的js
 @Author: yaofengjiao
 @Date: 2017-01-17
 *************************************************************/

var setting = {
    view: {
        showLine: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: onClick
    },
    async: {
        enable: true,
        url:basePath + "/getLoginUserResource",
        autoParam:["id=1", "name=n", "level=lv"],
        otherParam:{"otherParam":"zTreeAsyncTest"},
        dataFilter: filter
    }

};

function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    if(childNodes.code!=200){
        if(childNodes.message==undefined){
//                        dialog.alert("请重新登录") ;
            window.location.href= basePath+"/";
        } else{
            dialog.alert(childNodes.message) ;
        }

    }else{
        var result = childNodes.result;
        for (var i=0, l=result.length; i<l; i++) {
            var resultItem = result[i];
            resultItem.name = resultItem.name.replace(/\.n/g, '.');
            resultItem.wmsurl=resultItem.url;
            resultItem.url="javascript:void(0)";
        }
        return result;
    }
}


//点击菜单
function onClick(event, treeId, treeNode, clickFlag) {
    if(treeNode.parentFlag==1){  //当点击父节点时
        $("#"+treeNode.tId+"_switch").trigger("click");
    }else{
        link(treeNode.name,treeNode.wmsurl,treeNode.id);
    }
}
var visUlWidth, //可视宽度
    windowHeight;//浏览器当前窗口可视区域高度
$(document).ready(function(){
    visUlWidth = $("#tab-nav-list-show").css("width").split("px")[0]; //可视宽度
    $("#firstMenu_id li:eq(0)").trigger("click");  //第一个一级菜单被选中
    //tab栏左滑动按钮
    $("#right").off().on("click",".arrow-left",function(){
        var ulWidth=140*$("#tab-nav-list-show").find("li").length,//tab总长度
            ulLeft = $("#tab-nav-list-show").css("left").split("px")[0];
        if(ulWidth+parseInt(ulLeft)>parseInt(visUlWidth)){
            var visualWidth = ulWidth-parseInt(visUlWidth); // tab总长度与可视区域长度差
            if(visualWidth>=140){
                if(0-parseInt(ulLeft)<visualWidth-140){
                    $("#tab-nav-list-show").animate({
                        left:"-=140px"   //这样就会累加140px的距离
                    },500);
                }else{
                    $("#tab-nav-list-show").animate({
                        left:"-"+visualWidth+"px"
                    },500);
                }
            }else{
                $("#tab-nav-list-show").animate({
                    left:"-="+visualWidth+"px"
                },500);
            }
        }
    })

    //tab栏右滑动按钮
    $(".tab-nav-bar").off().on("click",".arrow-right",function(){
        var leftVal=$("#tab-nav-list-show").css("left").split("px")[0];
        if(parseInt(leftVal)<=-139){
            $("#tab-nav-list-show").animate({
                left:"+=140px"   //这样就会累加100px的距离
            },500);
        }else if(parseInt(leftVal)<=0){
            $("#tab-nav-list-show").animate({
                left:"0px"   //这样就会累加100px的距离
            },500);
        }
    })
    // tab关闭
    $("#tab-nav-list-show").off().on("click",".tab-item-close",function(){
        if($(this).parents("li").prev().html()){
            $(this).parents("li").prev().trigger("click");
        }else if($(this).parents("li").next().html()){
            $(this).parents("li").next().trigger("click");
        }else{
            window.open(basePath+"/home","frame-content",false);
        }

        $(this).parents("li").remove();
        var frameId=$(this).parents("li").attr("id").split("item-")[1];
        $("#main #frame-"+frameId).remove();

        //改变tab的left值
        var ulLeft = $("#tab-nav-list-show").css("left").split("px")[0];
        if(ulLeft<-140){
            $("#tab-nav-list-show").css("left",parseInt(ulLeft)+140+"px");
        }else{
            $("#tab-nav-list-show").css("left","0px");
        }
    })

    //重置树高
    setZtreeHeight();
    $(window).resize(function(){
        setZtreeHeight();
    })

});
//重置树高
function setZtreeHeight(){
    windowHeight = $(window).height()-147;
    $(".ztree").css("max-height",windowHeight+"px");
}









function changeWarehouse(warehouseNo){
    jQuery.post(basePath +'/changeWarehouse',
        {warehouseNo:warehouseNo},
        function(data) {
            if(data.code==undefined){
                window.location.href= basePath+"/";
            }
            if(data.code = 200){
                $("#tab-nav-list-show").html("");
                $("#main").html("");
                $("#treeDemo").find(".curSelectedNode").removeClass("curSelectedNode");
            }else{
                window.location.href= basePath+"/quit";
            }
        }
    );
}
function changeFirstMenu(obj,id){
    $("#firstMenu_id li").removeClass("active");
    $(obj).addClass("active");
    setting.async.url=basePath + "/getLoginUserResource?id="+id;
    $.fn.zTree.init($("#treeDemo"), setting);
//            $("#tab-nav-list-show").html("");   //清空页面切换栏
//            $("#main").html('<iframe id="frame-content" name="frame-content" frameborder="0"></iframe>');
//            var home =basePath + "/home";
//            window.open(home,"frame-content",false);
}































/**
 * 点击左侧菜单的响应事件
 * User: yaofengjiao
 * Date: 2016-11-03
 * Time: 下午05:23:52
 * Version: 1.0
 */
function link(n,u,id){
    $("#treeDemo li a").removeClass("curSelectedNode");
    $("#treeDemo li a[title='"+n+"']").addClass("curSelectedNode");
    //判断是否已存在
    if($("#main").find("#frame-"+id).length==0) {  //不存在
        $("#main iframe").hide();
        var newIframe = '<iframe id="frame-'+id+'" name="frame-'+id+'" frameborder="0"></iframe>';
//                $("#main").append(newIframe);
        $("#main").prepend(newIframe);
        $('#frame-'+id).attr("src",u);
    }else{
        $("#main iframe").hide();
        $("#frame-"+id).show();
    }
    tabNavList(n,u,id);
}

function tabNavList(liContent,liURL,liId){
    $("#tab-nav-list-show li.tab-nav-actived").removeClass("tab-nav-actived");
    //判断是否已存在
    if($("#tab-nav-list-show").find("#item-"+liId).length==0) {  //不存在
        $("#tab-nav-list-show li.tab-nav-actived").removeClass("tab-nav-actived");
        var liHtml='<li id="item-'+liId+'"  class="bui-nav-tab-item tab-nav-actived" title="'+liContent+'" onclick="tabChange(\''+liId+'\',this,\''+liURL+'\')" >'+
            '<s class="l"></s>'+
            '<div class="tab-item-inner">'+
            '<span class="tab-item-title">'+liContent+'</span>'+
            '<s class="tab-item-close"></s>'+
            '</div>'+
            '<s class="r"></s>'+
            '</li>'
        //后面添加
//                $("#tab-nav-list-show").append(liHtml);
//                var liWidths=140*$("#tab-nav-list-show").find("li").length;
//                if(liWidths>$("#tab-nav-list-show").width()){
//                    $(".arrow-left").trigger("click");
//                    $("#tab-nav-list-show").css("width",liWidths+"px");
//                }
        //前面添加
        $("#tab-nav-list-show").prepend(liHtml);
        var liWidths=140*$("#tab-nav-list-show").find("li").length;
        if(liWidths>$("#tab-nav-list-show").width()){
            $(".arrow-right").trigger("click");
            $("#tab-nav-list-show").css("width",liWidths+"px");
        }

    }else{  //已存在
        $("#item-"+liId).addClass("tab-nav-actived");
    }

}

// tab切换
function tabChange(id,obj,liURL){
    $("#main iframe").hide();
    $("#main #frame-"+id).show();
    $("#tab-nav-list-show li.tab-nav-actived").removeClass("tab-nav-actived");
    $(obj).addClass("tab-nav-actived");
    $("#treeDemo li a").removeClass("curSelectedNode");
    $("#treeDemo li a[title='"+obj.innerText.trim()+"']").addClass("curSelectedNode");
}

//iframe页面的缩放
function scaling(){
    $("#navbar,#sidebar").toggle();
    if(parseInt($(".main-content").css("margin-left").split("px")[0])){
        $(".main-content").css("margin-left","0");
    }else{
        $(".main-content").css("margin-left","190px");
    }
}
//阻止冒泡
function stopPro(e){
    e.stopPropagation();
}
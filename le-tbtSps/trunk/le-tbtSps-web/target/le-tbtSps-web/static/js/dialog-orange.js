/**
 * 弹出框组件
 * User: yaofengjiao
 * Date: 2016-08-05
 */
var dialog={

    /**
     * 初始化弹出框入参
     * @param params
     * @returns {{id: *, title: *, content: *, type: *, width: *, height: *, callback: (*|callback|g._listeners.callback), isClose: boolean}}
     */
    initOpenParam: function (params) {
        var params = typeof(params) == 'object' ? params : {};
        var obj = {
            id: params.id ? params.id : new Date().getTime(),
            title: params.title ? params.title : "信息提示",
            content: params.content ? params.content : "还没有内容！",
            type: params.type ? params.type : "GET",
            data: params.data ? params.data : {},
            async: params.async ? params.async : true,
            target: params.target || $("body"),
            callback: params.callback,
            btnTxt :  params.btnTxt ? params.btnTxt : "",
            btnClass :  params.btnClass   //按钮样式
        };
        return obj;
    },


    /**
     * 展示弹框
     */
    buildDialog: function(paramsObj) {
        paramsObj.target.append('<div id="dialog'+paramsObj.id+'Backdrop" class="modal-backdrop fade in"></div>');
        $(".modal-backdrop").show();
    },


    /**
     * 当前页面弹出层，内容为paramsObj.content
     * @param paramsObj
     */
    buildContent: function (paramsObj){
        var containerHtml = this.buildCommonContent(paramsObj);
        paramsObj.target.append(containerHtml);
        this.dialogSetting(paramsObj.id);
    },


    /**
     * 当前页面弹出层，内容为指定url异步获取
     * @param paramsObj
     */
    buildContentByUrl: function (paramsObj) {
        var containerHtml,url,dialogId;
        url = paramsObj.content;
        dialogId = "dialog" + paramsObj.id;
        paramsObj.content = "<span>数据加载中...</span>";
        containerHtml = this.buildCommonContent(paramsObj);
        paramsObj.target.append(containerHtml);
        var thisObj=this;
        $.ajax({
            type:paramsObj.type,
            url: url,
            async:paramsObj.async,
            cache: false,
            contentType: paramsObj.contentType,
            data: paramsObj.data,
            success: function (result) {
                paramsObj.content = result;
            },
            error: function (xhr, error) {
                paramsObj.content = "请求出现错误";
            },
            complete : function (xhr,ts) {
                $("#" + dialogId).find(".modal-body").html(paramsObj.content);
                thisObj.dialogSetting(paramsObj.id);
            }
        });
    },


    /**
     * 当前页面弹出层，内容为指定id下内容
     * @param paramsObj
     */
    buildContentById: function(paramsObj) {
        var containerHtml = "";
        var content = $("#" + paramsObj.content).html();
        paramsObj.content = content;
        containerHtml = this.buildCommonContent(paramsObj);
        paramsObj.target.append(containerHtml);
        this.dialogSetting(paramsObj.id);
    },


    /**
     * 构建弹出层基础架构
     * @param paramsObj
     */
    buildCommonContent: function(paramsObj) {
        var thisObj=this;
        var mainarr = [],
            id = paramsObj.id,
//            width = paramsObj.width ? Math.floor(paramsObj.width) : 300,
//            height = paramsObj.height ? paramsObj.height  : 240,
//            left = ($(window).width() - width - 20) / 2 > 10 ? ($(window).width() - width - 20) / 2 : 10,
//            top = ($(window).height() - height - 140) / 2 > 10 ? ($(window).height() - height - 140) / 2 : 10,
//            dataParams = 'data-left="' + left + '" data-top="' + top + '" ',
//            contentStyle = 'width:' + width + 'px; height:' + height + 'px;',
            operateType = paramsObj.type,
            dialogId = 'dialog' + id,
            confirmId = 'confirm' + id,
            cancelId = 'cancel' + id;
//            ctop = top - 50 > 0 ? top - 50 : 0;
        mainarr.push('<div class="modal fade in" id="' + dialogId+ '" >');
//			mainarr.push('<div class="modal-dialog" ' + dataParams + ' style="left:' + left + 'px;');
//			mainarr.push(' top:' + ctop + 'px;">');
        mainarr.push('<div class="modal-dialog">');
        mainarr.push('<div class="modal-content">');
        mainarr.push('<div class="modal-header">');
        mainarr.push('<button type="button" class="close"><span>&times;</span></button>');
        mainarr.push('<h4 class="modal-title" id="myModalLabel">' + paramsObj.title + '</h4>');
        mainarr.push('</div>');
//			mainarr.push('<div class="modal-body" style="' + contentStyle + '">');
        mainarr.push('<div class="modal-body" >');
        mainarr.push(paramsObj.content);
        mainarr.push('</div>');
        if(operateType=="alert"){ //alert弹框按钮
            mainarr.push('<div class="modal-footer">');
            mainarr.push('<button class="btn btn-info btn-sm" id="' + confirmId + '">确 定</button>');
            mainarr.push('</div>');
        }else if(operateType=="confirm" || operateType=="prompt"){   //confirm和prompt弹框
            mainarr.push('<div class="modal-footer">');
            mainarr.push('<button class="btn btn-info btn-sm" id="' + cancelId + '">取 消</button>');
            mainarr.push('<button class="btn btn-sm" id="' + confirmId + '">确 定</button>');
            mainarr.push('</div>');
//			}else if(operateType=="" || !operateType || operateType=="GET" || operateType=="POST"){
        }else {
            if (paramsObj.btnTxt.length>0) {
                mainarr.push('<div class="modal-footer moreBtn">');
                for(var i=0;i<paramsObj.callback.length;i++){
                    if(paramsObj.btnTxt[i].length>0){
                        if(paramsObj.btnClass){
                            mainarr.push('<button class="'+paramsObj.btnClass[i]+'" id="' + confirmId + i + '">'+paramsObj.btnTxt[i]+'</button>');
                        }else{
                            mainarr.push('<button class="btn btn-info btn-sm" id="' + confirmId + i + '">'+paramsObj.btnTxt[i]+'</button>');
                        }
                    }
                }
                mainarr.push('</div>');
            }
        }


        mainarr.push('</div></div></div>');
        return mainarr.join("");
    },


    /**
     * 设置弹窗展现
     * @param id
     */
    dialogSetting: function(id) {
        $("body").css("overflow","hidden");
        var dialogObj = $("#dialog" + id);
        var t = dialogObj.attr("data-top");
        var l = dialogObj.attr("data-left");
        dialogObj.removeAttr("data-top");
        dialogObj.removeAttr("data-left");
        dialogObj.show().stop().animate({top:t, left:l} , 300);
    },


    /**
     * 关闭弹出框
     * @param id 弹出框id
     */
    close: function (id, callback) {
        $("#" + id).remove();
//        if ($(".modal-backdrop").size()) $(".modal-backdrop").remove();
        $("#" + id+"Backdrop").remove();
        if (callback)  callback();
        $("body").removeAttr("style");
    },


    /**
     * 基本
     * @param content 内容
     * @param callback 确认回调函数
     */
    alert: function (content,target) {
        var dlHtml='<i class="tsIcon"></i><span class="tsContent ml10">'+content+'</span>';
        var params = {};
        params.title = "信息提示";
        params.type = "alert";
        params.content =dlHtml;
//        params.width = "550";
//        params.height = "100";
        if (target) params.target = target;
        var paramsObj=this.initOpenParam(params);
        this.buildContent(paramsObj);
        this.buildDialog(paramsObj);
        this.bindBtn(paramsObj);
    },


    /**
     * 提示框
     * @param content 辅助文字
     * @param callback 确认回调函数
     */
    prompt:function (content,callback,target) {
        var params = {};
        var textarea = '<textarea rows="5" cols="70" class="mt10"></textarea>';
        params.title = "信息提示";
        params.type = "prompt";
        params.content = content;
//        params.width = "300";
        if (typeof params.content === "function") {
            params.callback = params.content;
            params.content = textarea;
        } else {
            params.content = '<p>' + content +'</p>' + textarea;
            params.callback = callback;
        }
        if (target) params.target = target;
        var paramsObj=this.initOpenParam(params);
        this.buildContent(paramsObj);
        this.buildDialog(paramsObj);
        this.bindBtn(paramsObj);
    },


    /**
     * 询问层确认层
     * @param content 内容
     * @param callback 确认回调函数
     */
    confirm: function (content, callback ,target) {
        var dlHtml='<i class="confirmIcon"></i><span class="tsContent ml10">'+content+'</span>';
        var params = {};
        params.title = "信息提示";
        params.type = "confirm";
        params.content = dlHtml;
        params.target = target;
        params.callback= callback;
        var paramsObj=this.initOpenParam(params);
        this.buildContent(paramsObj);
        this.buildDialog(paramsObj);
        this.bindBtn(paramsObj);
    },


    /**
     * 请求URL内容
     * @param title 标题
     * @param callback 确认回调函数
     * @param target 插入目标位置
     */
    openUrlContent: function (params) {
        params.content = params.url;
        params.target = params.target;
        params.type = params.type;
        params.url = null;
        var paramsObj=this.initOpenParam(params);
        this.buildContentByUrl(paramsObj);
        this.buildDialog(paramsObj);
        this.bindBtn(paramsObj);
    },



    /**
     * 引入Id内容
     * @param title 标题
     * @param id 请求地址
     * @param callback 确认回调函数
     */
    openIdContent: function (params) {
        params.content = params.id;
        params.target = params.target;
        params.type = "";
        params.id = null;
        var paramsObj=this.initOpenParam(params);
        this.buildContentById(paramsObj);
        this.buildDialog(paramsObj);
        this.bindBtn(paramsObj);
    },

    /**
     * 弹框按钮绑定事件
     */
    bindBtn:function(paramsObj){
        var lastFocus  = document.activeElement;
        if($('.modal-footer').length>0){    //定义了按钮
            if($('.modal-footer .btn-info:eq(0)').length>0){
                $('.modal-footer .btn-info:eq(0)').focus();
            }
        }
        var thisObj=this;
        var operateType = paramsObj.type,
            id = paramsObj.id,
            dialogId = 'dialog' + id,
            confirmId = 'confirm' + id,
            cancelId = 'cancel' + id;
        $(".moreBtn button").off("click").on("click", function () {     //多按钮的定义
            var btnIndex = $(".moreBtn button").index(this);
            paramsObj.callback[btnIndex](dialogId);
        });

        $("#" + confirmId).off("click").on("click", function () {
            if (operateType=="prompt") {
                var textarea = $(this).parents(".modal").find("textarea").val();
                paramsObj.callback(dialogId, textarea);
            } else if (operateType=="confirm"){
                paramsObj.callback(dialogId);
            } else{ //alert的弹框
                thisObj.close(dialogId);
                if(lastFocus){
                    lastFocus.focus();
                }
            }
        });
        $("#" + cancelId).off("click").on("click", function () {
            thisObj.close(dialogId);
        });
        $("#" + dialogId).find(".close").off("click").on("click", function () {
            thisObj.close(dialogId);
            return false;
        });
    },

    //创建loading
    buildLoading: function(coverId) {
        var coverHtml='<div class="modal-backdrop fade in coverLayer"></div><img width="32px" height="32px" class="loadingIcon coverLayer" src="/static/css/images/loading.gif" />';
        if(coverId){
            $("#"+coverId).append(coverHtml);
        }else{
            $("body").append(coverHtml);
        }
    },

    //关闭loading
    closeLoading: function(coverId) {
        if(coverId){
            $("#"+coverId).find(".coverLayer").remove();
        }else{
            $("body").find(".coverLayer").remove();
        }
    }
};

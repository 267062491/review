/**
 * 回显数据
 */
(function($) {
	//将data中值赋值到form表单对应元素中（input,select,textarea,span）
    $.fn.echoData = function(data) {
    	return this.each(function () {
            var $form = $(this);
            //判断对象是否是数组
            function isArray(v){
				return toString.apply(v) === '[object Array]';
			}
            //赋值
            function voluation(o,v){
            	if(v !== "" && v !== null && v !== undefined){
            		o.val(v);
            	}
            }
            //赋值
            function voluationText(o,v){
            	if(v !== "" && v !== null && v !== undefined){
            		o.text(v);
            	}
            }
            //触发控件事件（仅支持select、radio、checkbox元素的change、click事件）
            function trigger(o){
            	var objEvt = $._data(o[0], "events");
				if (objEvt && objEvt["change"]) {
					o.trigger("change");
				}
				/*if (objEvt && objEvt["click"]) {
					o.click();
				}*/
            }
            //判断data不为空不为数组的Object对象
    		if(data != null && data != undefined && typeof data == "object" && !isArray(data)){
    			var $inputs = $form.find("input,select,textarea,span");
    			//遍历当前form表单下的input,select,textarea,span
                $($inputs).each(function(){
                	var $this = $(this);
                	//获取标签名
                	var tagName = this.tagName;
                	var name = $this.attr("name");
                	if(name === "" || name === null || name === undefined){
                		return true;
                	}
                	if(tagName == "INPUT"){
                		var type = $this.attr("type");
                		if(type == "text" || type == "hidden" || type == "email" || type == "tel" || type == "number" || type == "datetime"){
                			voluation($this, data[name]);
                		} else if(type == "radio"){
                			$this.attr("checked", null);
							if(data[name] === "" || data[name] === null || data[name] === undefined){
		                		return true;
		                	}
                			if($this.val().toString() === data[name].toString()){
                    			$this.click();
                			}
                		} else if(type == "checkbox"){
							$this.attr("checked", null);
							if(data[name] === "" || data[name] === null || data[name] === undefined){
		                		return true;
		                	}
                			var eachData = data[name];
							if(typeof eachData != "object"){
								eachData = JSON.parse(eachData);
							}
							$(eachData).each(function(){
								//判断当前元素是否包含is属性有并为true 返回值为1时勾选
								if($this.val().toString() === this.toString() || ($this.attr("is") === "true" && this == 1)){
									$this.click();
		                			if(!$this.is(":checked")){
										$this.click();
									}
								}
							});
                		}
                	} else if(tagName == "SELECT"){
                		voluation($this, data[name]);
                		trigger($this);
                	} else if(tagName == "TEXTAREA"){
                		voluation($this, data[name]);
                	} else if(tagName == "SPAN"){
                		voluationText($this, data[name]);
                	}
                });
    		}
    	});
    };
})(jQuery);
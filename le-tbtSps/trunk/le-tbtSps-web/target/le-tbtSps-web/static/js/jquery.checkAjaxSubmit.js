/**
 * 验证提交数据
 */
(function($) {
	//验证当前元素
    $.fn.validate = function(){
        var $elem = $(this);
        function validateField(elem){
        	//验证库
        	var errmsg = {
        			required: {
        				msg: "必填选项!",
        				test: function (obj) {
        					return $.trim(obj.val()).length > 0;
        				}
        			},
        			radio: {
        				msg: "请选择一项!",
        				test: function (obj) {
        					return $(obj).parent().parent().find("input[name='"+$(obj).attr("name")+"']:checked").size() > 0;
        				}
        			},
        			checkbox: {
        				msg: "至少选择一项!",
        				test: function (obj) {
        					return $(obj).parent().parent().find("input[name='"+$(obj).attr("name")+"']:checked").size() > 0;
        				}
        			},
        			choosed: {
        				msg: "未选择任何文件!",
        				test: function (obj) {
        					return $.trim(obj.val()).length > 0;
        				}
        			},
        			uploadFile: {// 验证上传文件格式，需要自定义属性v_upType
        				msg: ["请选择要上传的文件！", "请上传后缀名正确的文件"],
        				test: function (obj) {
        					if (!obj.val()) return true;
        					var fileName = obj.val().replace(/^\s*/, "").replace(/\s*$/, "");
        					if (fileName == "") {
        						return 0;
        					}
        					else {
        						var pos = fileName.lastIndexOf(".");
        						if (pos != -1) {
        							var suf = fileName.substring(pos + 1, fileName.length).toLowerCase();
        							var upType = obj.getAttribute("v_upType").split(",");
        							for (var i = 0; i < upType.length; i++) {
        								if (upType[i] == suf) {
        									return true;
        								}
        							}
        						}
        						;
        						return 1;
        					}
        					return true;
        				}
        			},
        	    	phone: {
        	    		msg: "手机号格式错误",
        	    		test: function(obj){
	        				var reg= /^(13[0-9]|15[0|1|2|3|5|6|7|8|9]|17[0|6|7|8]|18[0-9])\d{8}$/i;
	        				if(!reg.test(obj.val())){
	        					return false;
	        				}
	        				return true;
        	    		}
        	    	},
        			landline:{
        				msg:"座机号码格式错误",
        				test: function (obj) {
        					if (!$.trim(obj.val())) return true;
        					var patrn = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
        					var sInput = $.trim(obj.val());
        					if (sInput.search(patrn) == -1) {
        						return false;
        					}
        					return true;
        				}
        			},
        			postCode: {
        				msg: "邮政编码格式错误",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					var patrn = /^[0-9]{1}(\d){5}$/;
        					var sInput = obj.val();
        					if (sInput.search(patrn) == -1) {
        						return false;
        					}
        					return true;
        				}
        			},
        			email: {
        				msg: "邮件地址不合法",
        				test: function (obj) {
        					return !$.trim(obj.val()) || /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.)+[a-z]{2,3}$/i.test(obj.val());
        				}
        			},
        			dateFormat: {
        				msg: "日期格式YYYY-MM-DD",
        				test: function (obj) {
        					return !$.trim(obj.val()) || /^(\d{4})-(\d{2})-(\d{2})$/.test(obj.val());
        					// 格式是MM/DD/YYYY
        				}
        			},
        	    	number: {
        	    		msg: "必须为数字",
        	    		test: function(v){
	        	    		var reg = /^[0-9]+$/;
	        	    		if(!reg.test(v)){
	        	    			return false;
	        	    		}
	        	    		return true;
        	    		}
        	    	},
        			upLetter: {
        				msg: "必须为大写英文字母",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					var patrn = /^[A-Z]+$/;
        					var sInput = obj.val();
        					if (sInput.search(patrn) == -1) {
        						// obj.select();
        						return false;
        					}
        					return true;
        				}
        			},
        			lowLetter: {
        				msg: "必须为小写英文字母",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					var patrn = /^[a-z]+$/;
        					var sInput = obj.val();
        					if (sInput.search(patrn) == -1) {
        						return false;
        					}
        					return true;
        				}
        			},
        			allLetter: {
        				msg: "必须为英文",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					var patrn = /^[A-Za-z]+$/;
        					var sInput = obj.val();
        					if (sInput.search(patrn) == -1) {
        						return false;
        					}
        					return true;
        				}
        			},
        			numOrLetter: {
        				msg: "必须为英文或数字",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					var patrn = /^[A-Za-z0-9]+$/;
        					var sInput = obj.val();
        					if (sInput.search(patrn) == -1) {
        						return false;
        					}
        					return true;
        				}
        			},
        			numAndLetter: {
        				msg: "必须为数字和字母的混合",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					var sInput = obj.val();
        					if (sInput && (!(/^([a-zA-Z]|[0-9]){0,}$/.test(sInput) && /[a-zA-Z]{1}/.test(sInput) && /\d{1}/.test(sInput)))) {
        						return false;
        					}
        					return true;
        				}

        			},
        	    	letterNumberSymbol: {
        	    		msg: "必须为字母、数字、下划线、横线",
        	    		test: function(v){
	        	    		var reg = /^[A-Za-z0-9-_]{1,50}$/;
	        	    		if(!reg.test(v)){
	        	    			return false;
	        	    		}
	        	    		return true;
        	    		}
        	    	},
        			numLetterChinese: {
        				msg: "必须为英文、数字、汉字",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					var patrn = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
        					var sInput = obj.val();
        					if (sInput.search(patrn) == -1) {
        						return false;
        					}
        					return true;
        				}
        			},
        			numLetterChineseLineation: {
        				msg: "必须为英文、数字、汉字、横线",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					var patrn = /^[A-Za-z0-9\u4e00-\u9fa5\-]+$/;
        					var sInput = obj.val();
        					if (sInput.search(patrn) == -1) {
        						return false;
        					}
        					return true;
        				}
        			},
        			ipAddress: {
        				msg: "IP地址错误",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					// var pattern =
        					// /^(\d{1,2}|1\d\d|1\d?\*|2[0-4][\d*]|2\*|25[0-5]|25\*)(\.(\d{1,2}|1\d\d|1\d?\*|2[0-4][\d*]|2\*|25[0-5]|25\*)){3}$/;
        					var pattern = /^([1-9]|[1-9]\d|(1[0-1|3-9]\d|12[0-6|8-9]|2[0-3]\d|24[0-7]))(\.(\d|[1-9]\d|(1\d{2}|2([0-4]\d|5[0-5])))){3}$/;
        					var sInput = obj.val();
        					if (sInput.search(pattern) == -1) {
        						return false;
        					}
        					return true;
        				}
        			},
        			byteSize: {
        				msg: "输入内容的字节长度不符",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					var maxlen = parseFloat(obj.getAttribute("v_maxlength"));
        					var minlen = parseFloat(obj.getAttribute("v_minlength"));
        					var val = obj.val().replace(/(^\s*)|(\s*$)/g, "");
        					var cArr = val.match(/[^\x00-\xff]/ig);
        					var byteLen = val.length + (cArr == null ? 0 : cArr.length);
        					if (!isNaN(maxlen)) {
        						if (byteLen > maxlen) {
        							return false;
        						}
        					}
        					if (!isNaN(minlen)) {
        						if (byteLen < minlen) {
        							return false;
        						}
        					}
        					return true;
        				}
        			},
        			samePW: {
        				msg: "密码不一致，请重新输入",
        				test: function (obj) {
        					var pw = document.getElementById(obj.getAttribute("v_pw"));
        					var confirmPw = document.getElementById(obj.getAttribute("v_confirmPw"));
        					if (pw.value != "" && confirmPw.value != "" && pw.value != confirmPw.value) {
        						return false;
        					} else if (pw.value != "" && confirmPw.value != "" && pw.value == confirmPw.value) {
        						if (pw.parentNode && pw.parentNode.errstate) {
        							hideErrors(pw);
        							pw.parentNode.removeAttribute("errstate");
        						}
        						if (confirmPw.parentNode && confirmPw.parentNode.errstate) {
        							hideErrors(confirmPw);
        							confirmPw.parentNode.removeAttribute("errstate");
        						}
        						return true;
        					}
        					return true;
        				}
        			},
        			haveSpe: {
        				msg: "不能输入\\ / < > \' \" & #等字符",
        				test: function (obj) {
        					if (!obj.val()) return true;
        					return haveSpe(obj.val());
        					function haveSpe(str) {
        						var comp = "\\/><\'\"&#";
        						var aChar = "";
        						for (var i = 0; i < str.length; i++) {
        							aChar = str.charAt(i);
        							if (comp.indexOf(aChar) != -1)
        								return false;
        						}
        						return true;
        					}
        				}
        			},
        			idCard: {
        				msg: ["号码位数不对","号码出生日期错误或含有非法字符","号码校验错误","号码前六位代表地区非法","身份证号格式错误"],
        				test: function (obj) {
        					var vcity={ 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",  
        	        	        21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",  
        	        	        33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",  
        	        	        42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",  
        	        	        51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",  
        	        	        63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"  
        	        	       };  
            	        	function IdentityCodeValid(card){
            	        		var tip = "";
            	        		//是否为空  
            	        		if(card === '' || (card.length != 15 && card.length != 18)){  
            	        			tip = '请输入身份证号，身份证号不能为空';   
            	        		    return 0;  
            	        		}  
            	        		//校验长度，类型  
            	        		if(isCardNo(card) === false){  
            	        			tip = '您输入的身份证号码不正确，请重新输入';   
            	        		    return 4;  
            	        		}  
            	        		//检查省份  
            	        		if(checkProvince(card) === false){  
            	        			tip = '您输入的身份证号码不正确,请重新输入';  
            	        		    return 3;  
            	        		}  
            	        		//校验生日  
            	        		if(checkBirthday(card) === false){  
            	        			tip = '您输入的身份证号码生日不正确,请重新输入';   
            	        		    return 1;  
            	        		}  
            	        		//检验位的检测  
            	        		if(checkParity(card) === false){  
            	        			tip = '您的身份证校验位不正确,请重新输入';  
            	        		    return 2;  
            	        		}
            	        		return true;  
            	        	}


            	        	//检查号码是否符合规范，包括长度，类型  
            	        	function isCardNo(card){  
            	        		//身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
            	        		var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;  
            	        		if(reg.test(card) === false){  
            	        		    return false;  
            	        		}  
            	        		return true;  
            	        	}

            	        	//取身份证前两位,校验省份  
            	        	function checkProvince(card){  
            	        		var province = card.substr(0,2);  
            	        		if(vcity[province] == undefined){  
            	        		    return false;  
            	        		}  
            	        		return true;  
            	        	}

            	        	//检查生日是否正确  
            	        	function checkBirthday(card){  
            	        		var len = card.length;  
            	        		//身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字  
            	        		if(len == '15'){  
            	        		    var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;   
            	        		    var arr_data = card.match(re_fifteen);  
            	        		    var year = arr_data[2];  
            	        		    var month = arr_data[3];  
            	        		    var day = arr_data[4];  
            	        		    var birthday = new Date('19'+year+'/'+month+'/'+day);  
            	        		    return verifyBirthday('19'+year,month,day,birthday);  
            	        		}  
            	        		//身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X  
            	        		if(len == '18'){  
            	        		    var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;  
            	        		    var arr_data = card.match(re_eighteen);  
            	        		    var year = arr_data[2];  
            	        		    var month = arr_data[3];  
            	        		    var day = arr_data[4];  
            	        		    var birthday = new Date(year+'/'+month+'/'+day);  
            	        		    return verifyBirthday(year,month,day,birthday);  
            	        		}  
            	        		return false;  
            	        	}

            	        	//校验日期  
            	        	function verifyBirthday(year,month,day,birthday){  
            	        		var now = new Date();  
            	        		var now_year = now.getFullYear();  
            	        		//年月日是否合理  
            	        		if(birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day){  
            	        		    //判断年份的范围（3岁到100岁之间)  
            	        		    var time = now_year - year;  
            	        		    if(time >= 3 && time <= 100){  
            	        		        return true;  
            	        		    }  
            	        		    return false;  
            	        		}  
            	        		return false;  
            	        	}

            	        	//校验位的检测  
            	        	function checkParity(card){  
            	        		//15位转18位  
            	        		card = changeFivteenToEighteen(card);  
            	        		var len = card.length;  
            	        		if(len == '18'){  
            	        		    var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);   
            	        		    var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');   
            	        		    var cardTemp = 0, i, valnum;   
            	        		    for(i = 0; i < 17; i ++){   
            	        		        cardTemp += card.substr(i, 1) * arrInt[i];   
            	        		    }   
            	        		    valnum = arrCh[cardTemp % 11];   
            	        		    if (valnum == card.substr(17, 1)){  
            	        		        return true;  
            	        		    }  
            	        		    return false;  
            	        		}  
            	        		return false;  
            	        	}

            	        	//15位转18位身份证号  
            	        	function changeFivteenToEighteen(card)  
            	        	{  
            	        		if(card.length == '15'){  
            	        		    var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);   
            	        		    var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');   
            	        		    var cardTemp = 0, i;     
            	        		    card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);  
            	        		    for(i = 0; i < 17; i ++){   
            	        		        cardTemp += card.substr(i, 1) * arrInt[i];   
            	        		    }   
            	        		    card += arrCh[cardTemp % 11];   
            	        		    return card;  
            	        		}  
            	        		return card;  
            	        	}
            	        	return IdentityCodeValid(obj.val());
        				}
        			},
        	    	//验证组织机构合法性方法
        	        orgCode: {
        				msg: ["组织机构代码位数不对","组织机构代码格式错误","组织机构代码校验错误"],
        	        	test: function (code) {
	        	    		//机构代码
	        	    		code = code.replace("-","");
	        	    		if(code.length != 9){
	        	    			return 0;
	        	    		}
	        	    		var ws = [3, 7, 9, 10, 5, 8, 4, 2];
	        	    		var str = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	        	    		var reg = /^([A-Za-z0-9]){8}[0-9|X]$/;// /^[A-Za-z0-9]{8}-[A-Za-z0-9]{1}$/
	        	    		var sum = 0;
	        	    		for (var i = 0; i < 8; i++){
	        	    			sum += str.indexOf(code.charAt(i)) * ws[i];
	        	    		}
	        	    		var c9 = 11 - (sum % 11);
	        	    		c9 = (c9 == 10 ? 'X' : (c9 == 11 ? '0' : c9));
	        	    		if (!reg.test(code)) {
	        	    			return 1;
	        	    		}
	        	    		if (c9 != code.charAt(8)) {
	        	    			return 2;
	        	    		}
	        	    		return true;
        	        	}
        	        },
        	        //税务登记号
        	        taxpayerNo: {
        				msg: ["税务登记号位数不对","税务登记号格式错误","税务登记号校验错误"],
        	        	test: function (code){
	        	        	var vcity={ 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",  
	        	        	        21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",  
	        	        	        33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",  
	        	        	        42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",  
	        	        	        51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",  
	        	        	        63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"  
	        	        	       };  
	        	    		code = code.replace("-","");
	        	    		var pass = true;
	        	    		//var reg = /^([0-9]){14}[0-9|X]|([0-9]){16}[0-9|X]|([0-9]){17}[0-9|X]|([0-9]){19}[0-9|A-X]$/;
	        	    		var reg = /^[0-9A-Z]*$/g;
	        	    		if(code.length != 15 && code.length != 17 && code.length != 18 && code.length != 20){
	        	    			pass = false;
	        	    			return 0;
	        	    		}
	        	    		else if(!reg.test(code)){
	        	    			pass = false;
	        	    			return 1;
	        	    		}
	        	    		/*else if(!vcity[code.substr(0,2)]){
	        	    	      tip = "地址编码错误";
	        	    	      pass = false;
	        	    		}
	        	    		else if(code.length == 15){
	        	    	  		pass = audit.OrgCodeValid(code.substr(6,code.length));//code.length - 9
	        	    		}*/
	        	    		return pass;
        	        	}
        	    	},
        	    	socialCreditCode: {
        				msg: ["社会信用代码位数不对","社会信用代码格式错误","社会信用代码校验错误"],
        	    		test:function(code){
	        				if(code.length != 18){
	        					return 0;
	        				}
	        					
	        				var reg = /^([0-9ABCDEFGY])([1239])([0-9]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{10})$/;
	        				if(!reg.test(code)){
	        					return 1;
	        				}
	        				
	        				var str = '0123456789ABCDEFGHJKLMNPQRTUWXY';
	        				var ws =[1,3,9,27,19,26,16,17,20,29,25,13,8,24,10,30,28];
	        				var codes  = new Array();
	        				codes[0] = code.substr(0,code.length-1);
	        				codes[1] = code.substr(code.length-1,code.length);	
	        				
	        				var sum = 0;
	        				for(var i=0;i<17;i++){	
	        					sum += str.indexOf(codes[0].charAt(i)) * ws[i];
	        				}	
	        				
	        				var c18 = 31 - (sum % 31);
	        				if(c18 == 31){
	        					c18 = '0';
	        				}else if(c18 == 30){
	        					c18 = 'Y';
	        				}
	        				else{
	        					c18 = str.substr(c18,1);
	        				}
	        				
	        				if(c18 != codes[1]){
	        					return 2;
	        				}
	        				return true;
        	    		}
        	    	}
        		};
        	
        	check(elem);
        	
        	//校验
        	function check(elem) {
        		var elem = $(elem);
        		var rules = elem.attr("data-validate");
        		if (!rules) return;
        		rules = rules.split(",");
        		
        		for (var i=0, j=rules.length; i<j; i++) {
        			var name = rules[i];
        			var msgObj = errmsg[name];
        			var status = errmsg[name].test(elem);
        			if (!msgObj || status.toString() == "true") {
        				hideErrors(elem);
        			} else {
        				if(typeof msgObj.msg == "object") {
        					elem.addClass("err-border");
        					//showErrors(elem, msgObj.msg[status]);
        				} else {
        					elem.addClass("err-border");
        					//showErrors(elem, msgObj.msg);
        				}
        				break;
        			}
        		}
        	}
        	
        	//关闭错误提示
        	function hideErrors(elem) {
        		elem.removeClass("err-border");
        		if (elem.prev().hasClass("err-tips")){
        			elem.prev().remove();
        		}
        	}

        	//显示错误提示
        	function showErrors(elem, errors) {
        		elem.addClass("err-border");
        		var l = elem.offset().left;
        		var t = elem.offset().top;
        		if (elem.prev().hasClass("err-tips")){
        			elem.prev().remove();
        		}
        		$('<span class="err-tips"><i class="t-icon">!</i>' + errors + '</span>').insertBefore(elem);
        		var top = elem.position().top - elem.outerHeight() - 11;
        		if($(elem).attr("type") == "radio" || $(elem).attr("type") == "checkbox"){
        			var top = elem.position().top - elem.outerHeight() - 21;
        		}
        		elem.prev().css({
        			"position": "absolute",
        			"top":top,
        			"z-index": 30
        		});   
        	}
        }
        
        //校验当前元素
        validateField($elem);
		return !$elem.hasClass("err-border");
    };
    
    //校验初始化当元素失去焦点时进行验证
    $.fn.validateInit = function(){
    	return this.each(function () {
            var $form = $(this);
	    	$form.find("[data-validate]").unbind().bind("blur", function () {
	    		$(this).validate();
			});
	    	$form.find(":radio[data-validate],:checkbox[data-validate]").each(function(){
	    		var $inputs = $form.find("input[name='"+$(this).attr("name")+"']");
	    		for(var i = 1, j = $inputs.size(); i < j; i++){
	    			$inputs.eq(i).unbind().bind("blur", function () {
	    				$inputs.eq(0).validate();
	    			});
	    		}
	    	});
    	});
    };
    
    //校验form表单下所有数据
    $.fn.validateEach = function(){
        var $form = $(this);
    	$form.find("[data-validate]").each(function(){
    		$(this).validate();
        });
		return $form.find(".err-border").size() > 0 ? false : true;
    };
    
    //校验并提交表单
    $.fn.checkAjaxSubmit = function(opts) {
    	opts = jQuery.extend({
    		url: "",
    		type: "post",
    		dataType: "json",
    		data: {},
    		async: true,
    		success: function () { return false; }
        }, opts || {});
    	return this.each(function () {
            var $form = $(this);
            if(!$form.validateEach()){
            	return false;
            }
            opts.data = getFormValues();
	        function getFormValues() {
				var inputs = $form.find("input,select,textarea");
				var formData = {};
				var radioExtra = "|";
				var checkboxExtra = "|";
				$(inputs).each(function() {
					var $this = $(this);
					var type = $this.attr("type");
					var name = $this.attr("name");
					var obj = $(this).val();
					if(type === "radio"){
						if(radioExtra.indexOf("|"+name+"|") >= 0){
							return;
						}
						var radioChecked = $form.find("input[name='"+name+"']:checked");
						obj = null;
						if(radioChecked.length > 0){
							obj = $(radioChecked).eq(0).val();
						}
						radioExtra += ($this.attr("name")+"|");
					}
					else if(type === "checkbox"){
						if(checkboxExtra.indexOf("|"+name+"|") >= 0){
							return;
						}
						var checkboxChecked = $form.find("input[name='"+name+"']:checked");
						if(checkboxChecked.length == 1){
							obj = checkboxChecked.eq(0).val();
						}
						else if(checkboxChecked.length > 1){
							obj = [];
							$(checkboxChecked).each(function(){
								obj.push($(this).val());
							});
						}
						checkboxExtra += ($this.attr("name")+"|");
					}
					formData[name] = obj;
				});
	
				return formData;
			}
			
    	});
    };
})(jQuery);
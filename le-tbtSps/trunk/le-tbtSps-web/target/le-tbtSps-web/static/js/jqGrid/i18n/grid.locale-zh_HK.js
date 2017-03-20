;(function($){
/**
 * jqGrid Chinese Translation for v4.2
 * henryyan 2011.11.30
 * http://www.wsria.com
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 * 
 * update 2011.11.30
 *		add double u3000 SPACE for search:odata to fix SEARCH box display err when narrow width from only use of eq/ne/cn/in/lt/gt operator under IE6/7
**/
$.jgrid = $.jgrid || {};
$.extend($.jgrid,{
	defaults : {
		recordtext: "{0} - {1}\u3000共 {2} 條",	// 共字前是全角空格
		emptyrecords: "無數據顯示",
		loadtext: "讀取中...",
		pgtext : " {0} 共 {1} 頁"
	},
	search : {
		caption: "搜索...",
		Find: "查找",
		Reset: "重置",
		odata : [{oper:'eq', text:'等於\u3000\u3000'},{oper:'ne', text: '不等\u3000\u3000'}, { oper:'lt', text:'小於\u3000\u3000'},{ oper:'le', text: '小於等於'},{ oper:'gt', text:'大於\u3000\u3000'},{ oper:'ge', text:'大於等於'},
			{oper:'bw', text:'開始於'},{ oper:'bn', text:'不開始於'},{ oper:'in', text:'屬於\u3000\u3000'},{ oper:'ni', text:'不屬於'},{ oper:'ew', text:'結束於'},{ oper:'en', text:'不結束於'},{ oper:'cn', text:'包含\u3000\u3000'},{ oper:'nc', text:'不包含'},{ oper:'nu', text:'空值於\u3000\u3000'},{ oper:'nn', text:'非空值'}],
		groupOps: [	{ op: "AND", text: "所有" },	{ op: "OR",  text: "任壹" }	]
	},
	edit : {
		addCaption: "添加記錄",
		editCaption: "編輯記錄",
		bSubmit: "提交",
		bCancel: "取消",
		bClose: "關閉",
		saveData: "數據已改變，是否保存？",
		bYes : "是",
		bNo : "否",
		bExit : "取消",
		msg: {
			required:"此字段必需",
			number:"請輸入有效數字",
			minValue:"輸值必須大於等於 ",
			maxValue:"輸值必須小於等於 ",
			email: "這不是有效的e-mail地址",
			integer: "請輸入有效整數",
			date: "請輸入有效時間",
			url: "無效網址。前綴必須為 ('http://' 或 'https://')",
			nodefined : " 未定義！",
			novalue : " 需要返回值！",
			customarray : "自定義函數需要返回數組！",
			customfcheck : "Custom function should be present in case of custom checking!"
			
		}
	},
	view : {
		caption: "查看記錄",
		bClose: "關閉"
	},
	del : {
		caption: "刪除",
		msg: "刪除所選記錄？",
		bSubmit: "刪除",
		bCancel: "取消"
	},
	nav : {
		edittext: "",
		edittitle: "編輯所選記錄",
		addtext:"",
		addtitle: "添加新記錄",
		deltext: "",
		deltitle: "刪除所選記錄",
		searchtext: "",
		searchtitle: "查找",
		refreshtext: "",
		refreshtitle: "刷新表格",
		alertcap: "註意",
		alerttext: "請選擇記錄",
		viewtext: "",
		viewtitle: "查看所選記錄"
	},
	col : {
		caption: "選擇列",
		bSubmit: "確定",
		bCancel: "取消"
	},
	errors : {
		errcap : "錯誤",
		nourl : "沒有設置url",
		norecords: "沒有要處理的記錄",
		model : "colNames 和 colModel 長度不等！"
	},
	formatter : {
		integer : {thousandsSeparator: " ", defaultValue: '0'},
		number : {decimalSeparator:".", thousandsSeparator: " ", decimalPlaces: 2, defaultValue: '0.00'},
		currency : {decimalSeparator:".", thousandsSeparator: " ", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
		date : {
			dayNames:   [
				"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat",
		         "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
			],
			monthNames: [
				"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
				"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
			],
			AmPm : ["am","pm","AM","PM"],
			S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th'},
			srcformat: 'Y-m-d',
			newformat: 'm-d-Y',
			parseRe : /[Tt\\\/:_;.,\t\s-]/,
			masks : {
				ISO8601Long:"Y-m-d H:i:s",
				ISO8601Short:"Y-m-d",
				ShortDate: "Y/j/n",
				LongDate: "l, F d, Y",
				FullDateTime: "l, F d, Y g:i:s A",
				MonthDay: "F d",
				ShortTime: "g:i A",
				LongTime: "g:i:s A",
				SortableDateTime: "Y-m-d\\TH:i:s",
				UniversalSortableDateTime: "Y-m-d H:i:sO",
				YearMonth: "F, Y"
			},
			reformatAfterEdit : false
		},
		baseLinkUrl: '',
		showAction: '',
		target: '',
		checkbox : {disabled:true},
		idName : 'id'
	}
});
})(jQuery);

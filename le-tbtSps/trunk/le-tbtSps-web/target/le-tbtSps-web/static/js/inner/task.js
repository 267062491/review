function toResetTasks() {
	var idValues = CheckboxUtils.getAllCheckedValues("subBox");
	if(idValues == ""){
		alert("请至少选择一个任务！");
		return;
	}
	var r = window.confirm("重置后会重新再次执行，确认重置任务吗？");
	if(r == false){
		return;
	}
	jQuery.ajax({
		type: "POST",
		url: basePath + "/task/resetTasks",
		dataType : 'json',
		data: { ids : idValues, tableName:$("#tableName").val() },
		
		success: function(data){
			if(data!=null && data.message!=null){
				alert(data.message);
			} else {
				alert("重置失败");
			}
			if(data!=null && data.code==200){
				$("#queryForm").submit();//刷新主界面
			}
		},
		error : function(data) {
			alert("重置任务出现异常");
		}
	});
}


function toDeleteTasks() {
	var idValues = CheckboxUtils.getAllCheckedValues("subBox");
	if(idValues == ""){
		alert("请至少选择一个任务！");
		return;
	}
	var r = window.confirm("删除后不能再执行，确认删除任务吗？");
	if(r == false){
		return;
	}
	jQuery.ajax({
		type: "POST",
		url: basePath + "/task/deleteTasks",
		dataType : 'json',
		data: { ids : idValues, tableName:$("#tableName").val() },
		
		success: function(data){
			if(data!=null && data.message!=null){
				alert(data.message);
			} else {
				alert("删除失败");
			}
			if(data!=null && data.code==200){
				$("#queryForm").submit();//刷新主界面
			}
		},
		error : function(data) {
			alert("删除任务出现异常");
		}
	});
}
$(function() {
	$('#queryForm').validate({
		errorElement:"label",
		errorClass:"red",
		rules: { taskId: {number:true} },
		messages: { taskId: {number: "请输入正确的任务ID(数字)"} }
	});
	$("#checkAll").click(function() {
    	CheckboxUtils.setCheckedAll("subBox",this.checked);
	});
	$("#btn-delete-task").click(function() {
		toDeleteTasks();
	});
	$("#btn-reset-task").click(function() {
		toResetTasks();
	});
	$("#btn-query").click(function() {
		pageSubmit(1);
	});
});
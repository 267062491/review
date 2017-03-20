var CustomForm = {

	getFormValues : function(formId) {
		var $form = $("#"+formId);
		var inputs = $form.find("input,select,textarea");
		var formData = {};
		var radioExtra = "|";
		var checkboxExtra = "|";
		$(inputs).each(function() {
			var $this = $(this);
			var type = $this.attr("type");
			var name = $this.attr("name");
			var obj = $(this).val();
			if (type === "radio") {
				if (radioExtra.indexOf("|" + name + "|") >= 0) {
					return;
				}
				var radioChecked = $form.find("input[name='" + name + "']:checked");
				obj = null;
				if (radioChecked.length > 0) {
					obj = $(radioChecked).eq(0).val();
				}
				radioExtra += ($this.attr("name") + "|");
			} else if (type === "checkbox") {
				if (checkboxExtra.indexOf("|" + name + "|") >= 0) {
					return;
				}
				var checkboxChecked = $form.find("input[name='" + name + "']:checked");
				if (checkboxChecked.length == 1) {
					obj = checkboxChecked.eq(0).val();
				} else if (checkboxChecked.length > 1) {
					obj = [];
					$(checkboxChecked).each(function() {
						obj.push($(this).val());
					});
				}
				checkboxExtra += ($this.attr("name") + "|");
			}
			if(name) {
				if(obj) {
					formData[name] = obj;
				} else {
					formData[name] = "";
				}
				
			}
			
			
		});

		return formData;
	}
}
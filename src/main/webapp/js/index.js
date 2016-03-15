$(function(){
	$("#inter1 td input[name='appKey']").keyup(function(){
		var value = $("#inter1 td input[name='appKey']").val();
		$("input[name='appKey']").not(this).val(value);
	});
	$("#inter1 td input[name='appName']").keyup(function(){
		var value = $("#inter1 td input[name='appName']").val();
		$("input[name='appName']").not(this).val(value);
	});
	$("#inter1 td input[name='md5Key']").keyup(function(){
		var value = $("#inter1 td input[name='md5Key']").val();
		$("input[name='md5Key']").not(this).val(value);
	});
	$("#inter1 td input[name='codeVersion']").keyup(function(){
		var value = $("#inter1 td input[name='codeVersion']").val();
		$("input[name='codeVersion']").not(this).val(value);
	});
	$("#inter2 td input[name='authId']").keyup(function(){
		var value = $("#inter2 td input[name='authId']").val();
		$("input[name='authId']").not(this).val(value);
	});
	$("#inter3 td input[name='cityCode']").keyup(function(){
		var value = $("#inter3 td input[name='cityCode']").val();
		$("input[name='cityCode']").not(this).val(value);
	});
	$("#inter4 td input[name='socialToken']").keyup(function(){
		var value = $("#inter4 td input[name='socialToken']").val();
		$("input[name='socialToken']").not(this).val(value);
	});
	
	//遍历绑定帮助事件
	var inputs = $("[name='appKey'],[name='appName'],[name='md5Key'],[name='codeVersion'],[name='authId'],[name='cityCode'],[name='socialToken'],[name='forms']");
	$.each(inputs,function(index,element){
		//绑定触发焦点事件
		$(element).bind("focus",function(){
			var name = this.name;
			$("#annotate").text(annotate[name]);
		});
		
		//绑定失去焦点事件
		$(element).bind("blur",function(){
			$("#annotate").text("");
		});
	});
});

//打开实例
function instance(){
	var result1 = $("#result1").text();
	
	//获取参数
	var appKey = $("#inter1 td input[name='appKey']").val(); 
	var appName = $("#inter1 td input[name='appName']").val(); 
	var md5Key = $("#inter1 td input[name='md5Key']").val(); 
	var codeVersion = $("#inter1 td input[name='codeVersion']").val(); 
	var authId = "";
	//获取授权码
	try {
		var jo = eval("["+result1+"]");
		authId = jo[0].data.authId;
	}catch(err){
		alert(ErrorDef.ERR_PLEASE_GET_AUTHID);
		return false;
	};
	
	//检测参数
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(codeVersion) || $.isBlank(md5Key) || $.isBlank(authId)){
		alert(ErrorDef.ERR_PARAM);
		return false;
	}
	
	var params = "appKey=" + appKey + "&appName=" + appName + "&authId=" + authId + "&md5Key=" + md5Key + "&codeVersion=" + codeVersion;
	document.location.href = "instance.html?" + params;
}

/**
 * 获取授权ID
 */
function index_getDeadbeatAuthID(num){
	var resultText = $("#result" + num);
	var interStr = "#inter" + num;
	var appKey = $(interStr + " td input[name='appKey']").val(); 
	var appName = $(interStr + " td input[name='appName']").val(); 
	var md5Key = $(interStr + " td input[name='md5Key']").val(); 
	var codeVersion = $(interStr + " td input[name='codeVersion']").val(); 

	//检测参数
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(md5Key) || $.isBlank(codeVersion)){
		alert(ErrorDef.ERR_PARAM);
		return false;
	}
	
	//请求
	$.ajax({
	    type:'POST',
	    beforeSend:function(){
	    	resultText.text(Constant.PLEASE_WAIT);
	    },
	    async:true,
	    url:URL.GET_DEADBEAT_AUTHID_SERVLET,
	    data:{
			appKey : appKey,
			appName : appName,
			md5Key : md5Key,
			codeVersion : codeVersion
		},
	    dataType:'text',
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	resultText.text(textStatus);
	    },
	    success: function(data, status){
	    	if(status !='success'){
				return ErrorDef.ERR_EXCEPTION;
			}
			
			//检测返回是否是服务器异常
			if(ErrorDef.ERR_EXCEPTION == data){
				return data;
			}
			
			//检测返回是否是JSON格式
			if(!$.isJson(data)){
				resultText.text(data);
				return false;
			}
			
			var jsonObj = eval("[" + data + "]");
			
			//输出json
			var result = jsonStringify(jsonObj[0] ,4);
			resultText.text(result);
	    }
	});
};

/**
 * 获取全国城市列表
 */
function index_socialCityList(num){
	var resultText = $("#result" + num);
	var interStr = "#inter" + num;
	var appKey = $(interStr + " td input[name='appKey']").val(); 
	var appName = $(interStr + " td input[name='appName']").val();
	var authId = $(interStr + " td input[name='authId']").val(); 
	var md5Key = $(interStr + " td input[name='md5Key']").val(); 
	var codeVersion = $(interStr + " td input[name='codeVersion']").val(); 
	
	//检测参数
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(authId) || $.isBlank(md5Key) || $.isBlank(codeVersion)){
		alert(ErrorDef.ERR_PARAM);
		return false;
	}
	
	//请求
	$.ajax({
	    type:'POST',
	    beforeSend:function(){
	    	resultText.text(Constant.PLEASE_WAIT);
	    },
	    async:true,
	    url:URL.SOCIAL_CITY_LIST,
	    data:{
			appKey : appKey,
			appName : appName,
			authId : authId,
			md5Key : md5Key,
			codeVersion : codeVersion
		},
	    dataType:'text',
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	resultText.text(textStatus);
	    },
	    success: function(data, status){
	    	if(status !='success'){
				return ErrorDef.ERR_EXCEPTION;
			}
			
			//检测返回是否是服务器异常
			if(ErrorDef.ERR_EXCEPTION == data){
				return data;
			}

			//检测返回是否是JSON格式
			if(!$.isJson(data)){
				resultText.text(data);
				return false;
			}
			
			var jsonObj = eval("[" + data + "]");
			
			//输出json
			var result = jsonStringify(jsonObj[0] ,4);
			resultText.text(result);
	    }
	});
}

/**
 * 获取指定城市的输入配置和用户标记
 */
function index_socialCityInput(num){
	var resultText = $("#result" + num);
	var interStr = "#inter" + num;
	var appKey = $(interStr + " td input[name='appKey']").val(); 
	var appName = $(interStr + " td input[name='appName']").val(); 
	var authId = $(interStr + " td input[name='authId']").val(); 
	var cityCode = $(interStr + " td input[name='cityCode']").val(); 
	var md5Key = $(interStr + " td input[name='md5Key']").val(); 
	var codeVersion = $(interStr + " td input[name='codeVersion']").val(); 
	
	//检测参数
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(authId) || $.isBlank(cityCode) || $.isBlank(md5Key) || $.isBlank(codeVersion)){
		alert(ErrorDef.ERR_PARAM);
		return false;
	}
	
	//请求
	$.ajax({
	    type:'POST',
	    beforeSend:function(){
	    	resultText.text(Constant.PLEASE_WAIT);
	    	
	    	//清空forms表
	    	$("#createFormsTable tbody").empty();
	    },
	    async:true,
	    url:URL.SOCIAL_CITY_INPUT,
	    data:{
			appKey : appKey,
			appName : appName,
			authId : authId,
			cityCode : cityCode,
			md5Key : md5Key,
			codeVersion : codeVersion
		},
	    dataType:'text',
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	resultText.text(textStatus);
	    },
	    success: function(data, status){
	    	if(status !='success'){
				return ErrorDef.ERR_EXCEPTION;
			}
			
			//检测返回是否是服务器异常
			if(ErrorDef.ERR_EXCEPTION == data){
				return data;
			}

			//检测返回是否是JSON格式
			if(!$.isJson(data)){
				resultText.text(data);
				return false;
			}
			
			var jsonObj = eval("[" + data + "]");
			
			//输出json
			var result = jsonStringify(jsonObj[0] ,4);
			resultText.text(result);
			
			//自动检测并创建forms表格
			createFormsJsonTable(jsonObj[0]);
	    }
	});
}
/**
 * 刷新验证码
 */
function index_socialIdentifyCode(num){
	var resultText = $("#result" + num);
	var interStr = "#inter" + num;
	var appKey = $(interStr + " td input[name='appKey']").val(); 
	var appName = $(interStr + " td input[name='appName']").val(); 
	var authId = $(interStr + " td input[name='authId']").val(); 
	var socialToken  = $(interStr + " td input[name='socialToken']").val(); 
	var md5Key = $(interStr + " td input[name='md5Key']").val(); 
	var codeVersion = $(interStr + " td input[name='codeVersion']").val(); 
	

	//检测参数
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(authId) || $.isBlank(socialToken) || $.isBlank(md5Key) || $.isBlank(codeVersion)){
		alert(ErrorDef.ERR_PARAM);
		return false;
	}
	
	//请求
	$.ajax({
	    type:'POST',
	    beforeSend:function(){
	    	$("#result4ImgDiv").empty();
	    	resultText.text(Constant.PLEASE_WAIT);
	    },
	    async:true,
	    url:URL.SOCIAL_IDENTIFY_CODE,
	    data:{
			appKey : appKey,
			appName : appName,
			authId : authId,
			socialToken : socialToken,
			md5Key : md5Key,
			codeVersion : codeVersion
		},
	    dataType:'text',
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	resultText.text(textStatus);
	    },
	    success: function(data, status){
	    	if(status !='success'){
				return ErrorDef.ERR_EXCEPTION;
			}
			
			//检测返回是否是服务器异常
			if(ErrorDef.ERR_EXCEPTION == data){
				return data;
			}

			//检测返回是否是JSON格式
			if(!$.isJson(data)){
				resultText.text(data);
				return false;
			}
			
			var jsonObj = eval("[" + data + "]");
			
			//输出json
			var result = jsonStringify(jsonObj[0] ,4);
			resultText.text(result);
			
			//将结果中的图片赋值到id为result4Img的图片上
			var imageURL = jsonObj[0].data.imageURL;
			$("#result4ImgDiv").html("<img src='" + imageURL + "'/>");
	    }
	});
}
/**
 * 获取社保详情
 */
function index_socialUserDetail(num){
	var resultText = $("#result" + num);
	var interStr = "#inter" + num;
	var appKey =$(interStr + " td input[name='appKey']").val(); 
	var appName = $(interStr + " td input[name='appName']").val(); 
	var authId = $(interStr + " td input[name='authId']").val(); 
	var socialToken  = $(interStr + " td input[name='socialToken']").val(); 
	var forms  = $(interStr + " td input[name='forms']").val(); 
	var md5Key = $(interStr + " td input[name='md5Key']").val(); 
	var codeVersion = $(interStr + " td input[name='codeVersion']").val(); 

	//检测参数
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(authId) || $.isBlank(forms) || $.isBlank(socialToken) || $.isBlank(codeVersion) || $.isBlank(md5Key)){
		alert(ErrorDef.ERR_PARAM);
		return false;
	}
	
	//请求
	$.ajax({
	    type:'POST',
	    beforeSend:function(){
	    	resultText.text(Constant.PLEASE_WAIT_A_MINUTE);
	    },
	    async:true,
	    url:URL.SOCIAL_USER_DETAIL,
	    data:{
			appKey : appKey,
			appName : appName,
			authId : authId,
			socialToken : socialToken,
			forms : forms,
			md5Key : md5Key,
			codeVersion : codeVersion
		},
	    dataType:'text',
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	resultText.text(textStatus);
	    },
	    success: function(data, status){
	    	if(status !='success'){
				return ErrorDef.ERR_EXCEPTION;
			}
			
			//检测返回是否是服务器异常
			if(ErrorDef.ERR_EXCEPTION == data){
				return data;
			}

			//检测返回是否是JSON格式
			if(!$.isJson(data)){
				resultText.text(data);
				return false;
			}
			
			var jsonObj = eval("[" + data + "]");
			
			//输出json
			var result = jsonStringify(jsonObj[0] ,4);
			resultText.text(result);
	    }
	});
}
/**
 * 结束当前用户请求
 */
function index_socialFinish(num){
	var resultText = $("#result" + num);
	var interStr = "#inter" + num;
	var appKey = $(interStr + " td input[name='appKey']").val(); 
	var appName = $(interStr + " td input[name='appName']").val(); 
	var authId = $(interStr + " td input[name='authId']").val(); 
	var socialToken  = $(interStr + " td input[name='socialToken']").val(); 
	var codeVersion = $(interStr + " td input[name='codeVersion']").val(); 
	var md5Key = $(interStr + " td input[name='md5Key']").val(); 
	
	//检测参数
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(authId) || $.isBlank(socialToken) || $.isBlank(codeVersion) || $.isBlank(md5Key)){
		alert(ErrorDef.ERR_PARAM);
		return false;
	}
	
	//请求
	$.ajax({
	    type:'POST',
	    beforeSend:function(){
	    	resultText.text(Constant.PLEASE_WAIT);
	    },
	    async:true,
	    url:URL.SOCIAL_FINISH,
	    data:{
			appKey : appKey,
			appName : appName,
			authId : authId,
			socialToken : socialToken,
			md5Key : md5Key,
			codeVersion : codeVersion
		},
	    dataType:'text',
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	resultText.text(textStatus);
	    },
	    success: function(data, status){
	    	if(status !='success'){
				return ErrorDef.ERR_EXCEPTION;
			}
			
			//检测返回是否是服务器异常
			if(ErrorDef.ERR_EXCEPTION == data){
				return data;
			}

			//检测返回是否是JSON格式
			if(!$.isJson(data)){
				resultText.text(data);
				return false;
			}
			
			var jsonObj = eval("[" + data + "]");
			
			//输出json
			var result = jsonStringify(jsonObj[0] ,4);
			resultText.text(result);
	    }
	});
};

/**
 * 创建form表格
 * @param formJO
 */
function createFormsJsonTable(resultJO){
	//清空表
	$("#createFormsTable tbody").empty();
	
	//自动检测是否有data素
	if(resultJO.data == null || resultJO.data ==""){
		return false;
	};
	
	//自动检测是否有forms元素
	if(resultJO.data.forms == null || resultJO.data.forms ==""){
		return false;
	};
	
	//检测forms的大小
	if(resultJO.data.forms.length <=0){
		return false;
	}
	
	$.each(resultJO.data.forms,function(index,element){
		var name = element.desc;
		var id = element.name;
		
		var trHtml = "<tr>"+
						"<td colspan='2' class='rightText'>" + name + ":</td>" + 
						"<td>" + 
							"<input id='" + id + "' type='text'>" +
						"</td>" +
						"<td>&nbsp;</td>" + 
						"<td>&nbsp;</td>" + 
						"<td>&nbsp;</td>" + 
				     "</tr>";
		$("#createFormsTable tbody").append(trHtml);
	});
	
	//$显示生成按钮
	$("#createFormsTable tbody").append("<tr>" + 
									"<td colspan='2'>&nbsp;</td>" + 
								  	"<td>" + 
								  		"<button onclick='createFormsJson()'>生成forms的json格式</button>" + 
								  	"</td>" +
								  	"<td>&nbsp;</td>" + 
								  	"<td>&nbsp;</td>" + 
								  	"<td>&nbsp;</td>" + 
	 							  "</tr>");
};

/**
 * 生成forms的json
 * @returns {Boolean}
 */
function createFormsJson(){
	var formsTRs = $("#createFormsTable tbody tr td input");
	var forms = {};
	//如果没有formsTRs元素，则
	if(formsTRs == null || formsTRs.length==0){
		return false;
	}
	
	//遍历input元素
	$.each(formsTRs,function(index,element){
		var id = element.id;
		var val = element.value;
		
		forms[id] = val;
	});
	
	var formsJSONStr = JSON.stringify(forms);
	$("#formsJson").val(formsJSONStr);
}
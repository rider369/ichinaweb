$(function(){
	//隐藏输入表单
	$("#inputDiv").addClass("hideDiv");
});

//隐藏所有的输入条件
function emptyInputList(){
	$("#inputDiv tbody").empty();
};

//显示需要的条件
function showInputList(forms){
	//清空输入条件
	emptyInputList();
	//隐藏载入中
	$("#mainContentLoading").addClass("hideDiv");
	//隐藏失败
	$("#mainContentError").addClass("hideDiv");
	
	//显示输入内容
	$("#inputDiv").removeClass("hideDiv");
	if(forms == null || !forms instanceof Array || forms.length == 0){
		return false;
	};
	
	//遍历显示条件
	$.each(forms,function(name,value) { 
		var inputValue = value.name;
		var inputName = value.desc;
		
		//如果是验证码类型，创建验证码tr
		if(inputValue == 'vcode'){
			//将验证码id保存到内存中
			Mydata.vcodeId = inputValue + "Img";
			
			var trStr = createVcodeTR(inputName, inputValue,Mydata.vcodeId);
			$("#inputDiv table").eq(0).append(trStr);
			
		}else{//否则创建输入类型
			var trStr = createInputTR(inputName, inputValue);
			$("#inputDiv table").eq(0).append(trStr);
		}
	});
};

//创建输入text的html
function createInputTR(text,id){
	var html =  "<tr>"+
					"<td class='rightText'>" + text + ":</td>"+
					"<td colspan='2'>"+
						"<input type='text' id='" + id + "'>"+
					"</td>"+
				"</tr>";
	return html;
};

//插件验证码图片的html
function createVcodeTR(text,id,imgId){
	var html =  "<tr>"+
					"<td class='rightText'>" + text + ":</td>" +
					"<td><input type='text' id='" + id + "' ></td>" +
					"<td><img id='" + imgId + "' onclick='socialIdentifyCode()' width='60px' height='26px;' src=''/></td>";
				"</tr>";
	return html;
};


//获取指定城市输入条件
function socialCityInput() {
	var cityCode = $("#citys option:selected").val();
	var cityName = $("#citys option:selected").text();
	var provinceName = $("#provinces option:selected").text();
	var appKey = Mydata.params.appKey;
	var appName = Mydata.params.appName;
	var authId = Mydata.params.authId;
	var md5Key = Mydata.params.md5Key;
	var codeVersion = Mydata.params.codeVersion;
	
	//参数检测
	if($.isBlank(cityCode) || cityCode == "none"){
		alert("请选择城市");
		return false;
	};
	
	//参数检测
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(authId) || $.isBlank(md5Key) || $.isBlank(codeVersion)){
		alert("参数不完整!");
		return false;
	};
	
	//请求
	$.ajax({
	    type:'POST',
	    beforeSend:function(){
	    	//关闭上一个请求socialToekn
	    	closeSocialToekn();
	    	//清空输入条件
	    	emptyInputList();
	    	//清空选择城市
	    	$("#cityName").text("还未选择城市");
	    	//隐藏输入表单
	    	$("#inputDiv").addClass("hideDiv");
	    	//显示输入表单载入中
	    	$("#mainContentLoading").removeClass("hideDiv");
	    	//隐藏读入失败
	    	$("#mainContentError").addClass("hideDiv");
	    	//清空社保网址
	    	$("#socialUrl").empty();
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
	    	//显示读取失败
	    	$("#mainContentError").removeClass("hideDiv");
	    	alert("获取指定城市的输入配置和用户标记socialCityInput接口出现异常！");
	    	
	    	return false;
	    },
	    success: function(data, status){
	    	if(status !='success'){
				return ErrorDef.ERR_EXCEPTION;
			};
			
			//检测返回是否是服务器异常
			if(ErrorDef.ERR_EXCEPTION == data){
				return data;
			};

			//检测返回是否是JSON格式
			if(!$.isJson(data)){
				alert(data);
				return false;
			};
			
			var jsonObj = eval("[" + data + "]");
			var result = jsonObj[0];
			var errorCode = result.ErrorCode;
			
			//检测是否授权码过期
			if(errorCode == ErrorCode.ERR_AUTHID) {
				alert("授权码过期，请重新生成实例！");
				
				//隐藏输入表单载入中
		    	$("#mainContentLoading").addClass("hideDiv");
		    	//显示读入失败
		    	$("#mainContentError").removeClass("hideDiv");
		    	
				return false;
			};
			
			//检测是否登陆失败
			if(errorCode == ErrorCode.ERR_FIAL_LOGIN) {
				alert("登陆失败，请重新确定城市！");
				return false;
			};
			
			//输出错误信息
			if(errorCode != 0){
				alert(result.ErrorMsg);
				
				//隐藏输入表单载入中
		    	$("#mainContentLoading").addClass("hideDiv");
		    	//显示读入失败
		    	$("#mainContentError").removeClass("hideDiv");
				
				return false;
			};
			
			//将用户标记保存到内存中
			Mydata.socialToken = result.data.socialToken;
			var forms = result.data.forms;
			
			//标注选择了什么城市
			$("#cityName").text(provinceName + "   " + cityName);
			//显示社保网站地址
			$("#socialUrl").html("<a href='" + result.data.socialUrl + "' target='blank'>" + result.data.socialUrl + "</a>");
			
			//展示输入条件
			showInputList(forms);
			
			//显示验证码
			socialIdentifyCode();
	    }
	});
};

//刷新验证码
function socialIdentifyCode() {
	var vcodeLen = $("#vcodeImg").length;
	
	//如果没有验证码 图片就不加载了
	if(vcodeLen == 0){
		return false;
	}
	
	var appKey = Mydata.params.appKey;
	var appName = Mydata.params.appName;
	var authId = Mydata.params.authId;
	var socialToken = Mydata.socialToken;//获取在内存中的社保帐号
	var md5Key = Mydata.params.md5Key;
	var codeVersion = Mydata.params.codeVersion;
	
	//参数检测
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(authId) || $.isBlank(md5Key) || $.isBlank(socialToken) || $.isBlank(codeVersion)){
		alert("参数不完整!");
		return false;
	};
	
	//请求
	$.ajax({
	    type:'POST',
	    beforeSend:function(){
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
	    	alert("刷新验证码接口socialIdentifyCode接口出现异常！");
	    	return false;
	    },
	    success: function(data, status){
	    	if(status !='success'){
				return ErrorDef.ERR_EXCEPTION;
			};
			
			//检测返回是否是服务器异常
			if(ErrorDef.ERR_EXCEPTION == data){
				return data;
			};

			//检测返回是否是JSON格式
			if(!$.isJson(data)){
				alert(data);
				return false;
			};
			
			var jsonObj = eval("[" + data + "]");
			var result = jsonObj[0];
			var errorCode = result.ErrorCode;
			
			//检测是否授权码过期
			if(errorCode == ErrorCode.ERR_AUTHID) {
				alert("授权码过期，请重新生成实例！");
				return false;
			};
			
			//输出错误信息
			if(errorCode != 0){
				alert(result.ErrorMsg);
				return false;
			};
			
			var imageURL = result.data.imageURL;
			$("#vcodeImg").attr("src",imageURL);
//			$("#" + data.vcodeId).src;
	    }
	});
};

//构造forms
function createFormsJSON(){
	var forms = {};
	var inputs = $("#inputDiv table tbody input");
	
	//遍历input
	$.each(inputs,function(name,value) { 
		var id = value.id;
		var val = value.value;
		
		forms[id] = val;
	});
	
	return forms;
};

//获取社保详情
function socialUserDetail() {
	//输出文本框
	var resultText = $("#detailText");
	
	var appKey = Mydata.params.appKey;
	var appName = Mydata.params.appName;
	var authId = Mydata.params.authId;
	var socialToken = Mydata.socialToken;//获取在内存中的社保帐号
	var md5Key = Mydata.params.md5Key;
	var codeVersion = Mydata.params.codeVersion;
	//构造forms
	var forms = createFormsJSON();
	//转换成Json字符串
	var formsJSONStr = JSON.stringify(forms);
	//参数检测
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(authId) || $.isBlank(md5Key) || $.isBlank(socialToken) || $.isEmpty(forms) || $.isBlank(formsJSONStr) || $.isBlank(codeVersion)){
		alert("参数不完整!");
		return false;
	};
	
	//请求
	$.ajax({
	    type:'POST',
	    beforeSend:function(){
	    	//清空详情中的内容
	    	$("#detailText").text("正在获取您的社保数据中，时间可能在一分钟左右，请不要关闭当前页面，耐心等待.....");
	    },
	    async:true,
	    url:URL.SOCIAL_USER_DETAIL,
	    data:{
	    	appKey : appKey,
			appName : appName,
			authId : authId,
			socialToken : socialToken,
			forms : formsJSONStr,
			md5Key : md5Key,
			codeVersion : codeVersion
		},
	    dataType:'text',
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	alert("获取指定城市的输入配置和用户标记socialCityInput接口出现异常！");
	    	return false;
	    },
	    success: function(data, status){
	    	if(status !='success'){
				return ErrorDef.ERR_EXCEPTION;
			};
			
			//检测返回是否是服务器异常
			if(ErrorDef.ERR_EXCEPTION == data){
				return data;
			};

			//检测返回是否是JSON格式
			if(!$.isJson(data)){
				resultText.text(data);
				return false;
			};
			
			var jsonObj = eval("[" + data + "]");
			var result = jsonObj[0];
			var errorCode = result.ErrorCode;
			
			//检测是否授权码过期
			if(errorCode == ErrorCode.ERR_AUTHID) {
				alert("授权码过期，请重新生成实例！");
				return false;
			};
			
			//检测是否登陆失败,失败则自动更新验证码图片
			if(errorCode == ErrorCode.ERR_FIAL_LOGIN) {
				socialIdentifyCode();//更新验证码
			};
			
			//输出json到文本框内
			var result = jsonStringify(result ,4);
			resultText.text(result);
	    }
	});
};

//结束当前用户请求
function socialFinish() {
	//输出文本框
	var resultText = $("#detailText");
	
	var appKey = Mydata.params.appKey;
	var appName = Mydata.params.appName;
	var authId = Mydata.params.authId;
	var socialToken = Mydata.socialToken;//获取在内存中的社保帐号
	var md5Key = Mydata.params.md5Key;
	var codeVersion = Mydata.params.codeVersion;

	//参数检测
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(authId) || $.isBlank(md5Key) || $.isBlank(socialToken) || $.isBlank(codeVersion)){
		alert("参数不完整!");
		return false;
	};
	
	//请求
	$.ajax({
		type:'POST',
		beforeSend:function(){
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
			alert("结束当前用户请求socialFinish接口出现异常！");
			return false;
		},
		success: function(data, status){
			if(status !='success'){
				return ErrorDef.ERR_EXCEPTION;
			};
			
			//检测返回是否是服务器异常
			if(ErrorDef.ERR_EXCEPTION == data){
				return data;
			};
			
			//检测返回是否是JSON格式
			if(!$.isJson(data)){
				resultText.text(data);
				return false;
			};
			
			var jsonObj = eval("[" + data + "]");
			var result = jsonObj[0];
			var errorCode = result.ErrorCode;
			
			//检测是否授权码过期
			if(errorCode == ErrorCode.ERR_AUTHID) {
				alert("授权码过期，请重新生成实例！");
				return false;
			};
			
			//输出错误信息
			if(errorCode != 0){
				alert(result.ErrorMsg);
				return false;
			};
			
			alert("已终止该用户的进程！");
		}
	});
};

//关闭socialToken资源（无聊成功失败，都不做任何提示）
function closeSocialToekn(){
	var appKey = Mydata.params.appKey;
	var appName = Mydata.params.appName;
	var authId = Mydata.params.authId;
	var socialToken = Mydata.socialToken;//获取在内存中的社保帐号
	var md5Key = Mydata.params.md5Key;
	var codeVersion = Mydata.params.codeVersion;
	
	//参数检测
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(authId) || $.isBlank(md5Key) || $.isBlank(socialToken) || $.isBlank(codeVersion)){
		return false;
	};

	//请求
	$.ajax({
		type:'POST',
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
		success: function(data, status){
		}
	});
}
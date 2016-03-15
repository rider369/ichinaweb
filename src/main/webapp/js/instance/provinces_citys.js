$(function(){
	var appKey = $.getUrlParam('appKey');
	var appName = $.getUrlParam('appName');
	var authId = $.getUrlParam('authId');
	var md5Key = $.getUrlParam('md5Key');
	var codeVersion = $.getUrlParam('codeVersion');
	
	//检测参数
	if($.isBlank(appKey) || $.isBlank(appName) || $.isBlank(codeVersion) || $.isBlank(md5Key) || $.isBlank(authId)){
		alert(ErrorDef.ERR_PARAM);
		return false;
	}
	
	//将参数存入到内存中
	Mydata.params.appKey = appKey;
	Mydata.params.appName = appName;
	Mydata.params.authId = authId;
	Mydata.params.md5Key = md5Key;
	Mydata.params.codeVersion = codeVersion;
	
	//初始化全国省市
	socialCityList(appKey, appName, authId, md5Key, codeVersion);
	
	$("#provinces").change(function(){
		var provinceNamw = $("#provinces option:selected").text();
		$("#citys").empty();
		var cs = getCityByProvince(provinceNamw);
		initCitysSelect(cs);
	});
});

//获取全国省市
function socialCityList(appKey, appName, authId, md5Key, codeVersion){
	//请求
	$.ajax({
	    type:'POST',
	    beforeSend:function(){
	    	emptyProvinceSelect();
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
	    	alert("调用获取全国城市socialCityList接口出现异常！");
	    	return false;
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
			
			var provinceObj = result.data.province;
			//初始化省列表
			initProvincesSelect(provinceObj);
	    }
	});
};

//清空省列表
function emptyProvinceSelect(){
	$("#province").empty();
	$("#province").html("<option value='none'>请选择</option>");
	
	//初始化城市列表
	emptyCitySelect();
	Mydata.citys = [];//初始化浏览器内存
	Mydata.socialToken = '';//初始化个人标记
}

//清空城市列表
function emptyCitySelect(){
	$("#citys").empty();
	$("#citys").html("<option value='none'>请选择</option>");
}

//初始化省列表
function initProvincesSelect(provinceObj){
	emptyProvinceSelect();
	try{
		$.each(provinceObj,function(name,value) { 
			var cityName = value.name;
			var citys = value.citys;
			
			var optionStr = "<option value='"+cityName+"'>"+cityName+"</option>";
			var p = new province(cityName, citys);
			
			//添加到省列表中
			$("#provinces").append(optionStr);
			
			//保存在浏览器内存中
			Mydata.citys.push(p);
		}); 
	}catch(err){
    	return false;
	};
};

//初始化对应省的城市列表
function initCitysSelect(cs){
	emptyCitySelect();
	$.each(cs,function(name,value) { 
		var code = value.code;
		var name = value.name;
		
		//添加到省列表中
		var optionStr = "<option value='"+code+"'>"+name+"</option>";
		$("#citys").append(optionStr);
	});
};

//获取对应省的城市集合
function getCityByProvince(provinceNamw) {
	var cs = [];
	
	if(Mydata.citys.length == 0){
		return [];
	};
	
	$.each(Mydata.citys,function(name,value) { 
		var name = value.name;
		var citys = value.citys;
		if(name == provinceNamw) {
			cs = citys;
			return false;
		};
	});
	
	return cs;
};
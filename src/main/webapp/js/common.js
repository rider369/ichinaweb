/**
 * 
 */

//检测参数是否为空
(function($) {
	//检验是否为空
	$.isBlank = function(obj) {
		return (!obj || $.trim(obj) === "");
	};
	
	//检验是否是json格式
	$.isJson = function(obj) {
		//判断返回值不是 json 格式
        if (!obj.match("^\{(.+:.+,*){1,}\}$"))
        {
            return false;
        }
        return true;
	};
	
	//从url中获取参数
	$.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    
    //对象内是否为空
    $.isEmpty = function(obj){
    	if(obj == null){
    		return true;
    	}else if(obj instanceof Array){
    		var len = obj.length;
    		return len > 0?false:true;
    	}else if(obj instanceof Object){
    		var len = 0;
    		$.each(obj,	function(num,element){
    			len ++;
    		});
    		
    		return len>0?false:true;
    	}
    };
})(jQuery);

//常量
var Constant = {
	PLEASE_WAIT : "请等待...",
	PLEASE_WAIT_A_MINUTE : "该接口解析数据较为复杂，请等待大约一分钟左右"
};

//错误码
var ErrorCode = {
	ERR_AUTHID : 1005,//授权码失效		
	ERR_FIAL_LOGIN : 3005//登陆失败
};

//错误常量
var ErrorDef = {
	ERR_PLEASE_GET_AUTHID : "请先获取授权码！",
	ERR_PARAM : "参数不完整！",
	ERR_EXCEPTION : "服务器异常！"
};

//URL常量
var URL = {
	GET_DEADBEAT_AUTHID_SERVLET : "/SocialDemo/getSocialAuthIDServlet",//获取授权码
	SOCIAL_CITY_LIST : "/SocialDemo/socialCityListServlet",//获取全国城市列表
	SOCIAL_CITY_INPUT : "/SocialDemo/socialCityInputServlet",//获取指定城市的输入配置和用户标记
	SOCIAL_IDENTIFY_CODE : "/SocialDemo/socialIdentifyCodeServlet",//刷新验证码
	SOCIAL_USER_DETAIL : "/SocialDemo/socialUserDetailServlet",//获取社保详情
	SOCIAL_FINISH : "/SocialDemo/socialFinishServlet",//结束当前用户请求
};

//json排版
function jsonStringify(data,space){
    var seen=[];
    return JSON.stringify(data,function(key,val){
        if(!val||typeof val !=='object'){
            return val;
        }
        if(seen.indexOf(val)!==-1){
            return '[Circular]';
        }
        seen.push(val);
        return val;
    },space);
};

//注释
var annotate = {
	appKey : '快查公司提供的appKey',
	appName : '快查公司提供的appName',
	md5Key : '快查公司提供的md5Key',
	codeVersion : '使用接口文档的版本号',
	authId : '授权码',
	cityCode : '城市编号',
	socialToken : '用户标记',
	forms : '用户输入参数的json'
};
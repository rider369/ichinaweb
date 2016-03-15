//定义省 类
function province(name,citys){
	this.name = name;
	this.citys = citys;
}

//内存数据
var Mydata = {
	params : {
		appKey :'',
		appName : '',
		authId : '',
		md5Key : '',
		codeVersion : ''	
	},
	socialToken : '',//个人标记
	vcodeId : '',//验证码图片ID
	citys :[]
	
};
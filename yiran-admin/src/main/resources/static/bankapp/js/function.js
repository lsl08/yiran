//var urlstrs=window.location.+'//'+window.location.host//自动获取
var urlstrs='http://api.yirantrade.com'

function url(){
	console.log(urlstrs)
	return urlstrs//自动获取
}

var urlobject={
	ycash:[{name:'杭州资产管理',id:'200001102828'},{name:'永达融资租赁',id:'200001091813'},{name:'永达小额贷款',id:'200001085735'},{name:'永昇融资租赁',id:'200001086651'}],//预投产//生产
	cscash:[{name:'杭州资产管理',id:'200001265041'},{name:'永达融资租赁',id:'200001265042'},{name:'永达小额贷款',id:'200001265043'},{name:'永昇融资租赁',id:'200001265044'}],//测试
	kf:[{name:'杭州资产管理',id:'200001135016'},{name:'永达融资租赁',id:'200001135016'},{name:'永达小额贷款',id:'200001135016'},{name:'永昇融资租赁',id:'200001135016'}],//开发
}
var partnerIdobj={}

if(urlstrs=='https://cash.yongdapay.com'){//生产
	partnerIdobj=urlobject.ycash
}
if(urlstrs=='http://ycash.yongdapay.com'){//预投产
	partnerIdobj=urlobject.ycash
}
if(urlstrs=='http://cscash.yongdapay.cn'){//测试
	partnerIdobj=urlobject.cscash
}
if(urlstrs=='http://192.168.110.22:8303'){//开发
	partnerIdobj=urlobject.kf
}
console.log(urlobject,partnerIdobj)

function partnerIdstr(str){
	var partnerId=''
	partnerIdobj.map(function(item,i){
		if(str==item.name){
			partnerId=item.id
		}
	})
	console.log(partnerId)
	return partnerId
}

function partnerIdstr2(str){
	var partnername=''
	partnerIdobj.map(function(item,i){
		if(str==item.id){
			partnername=item.name
		}
	})
	console.log(partnername)
	return partnername
}
//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

//function partnerIdstr(str){
//	var partnerId=''
//	if(str=='杭州永达资产管理有限公司'){
//		partnerId='200001265041'
//	}else if(str=='上海永达融资租赁有限公司'){
//		partnerId='200001265042'
//	}else if(str=='永昇融资租赁有限公司'){
//		partnerId='200001265044'
//	}else if(str=='上海永达小额贷款有限公司-消费信贷'){
//		partnerId='200001265043'
//	}
//	return partnerId
//}
//function partnerIdstr2(str){
//	var partnerId=''
//	if(str=='200001265041'){
//		partnerId='杭州永达资产管理有限公司'
//	}else if(str=='200001265042'){
//		partnerId='上海永达融资租赁有限公司'
//	}else if(str=='200001265044'){
//		partnerId='永昇融资租赁有限公司'
//	}else if(str=='200001265043'){
//		partnerId='上海永达小额贷款有限公司-消费信贷'
//	}
//	return partnerId
//}
//function partnerIdstr(str){
//	var partnerId=''
//	if(str=='杭州永达资产管理有限公司'){
//		partnerId='200001135016'
//	}else if(str=='上海永达融资租赁有限公司'){
//		partnerId='200001135016'
//	}else if(str=='永昇融资租赁有限公司'){
//		partnerId='200001135016'
//	}else if(str=='永达金融'){
//		partnerId='200001135016'
//	}
//	return partnerId
//}
//function partnerIdstr2(str){
//	var partnerId=''
//	if(str=='200001135016'){
//		partnerId='永达金融'
//	}else if(str=='200001135016'){
//		partnerId='上海永达融资租赁有限公司'
//	}else if(str=='200001135016'){
//		partnerId='永昇融资租赁有限公司'
//	}else if(str=='200001135016'){
//		partnerId='永达金融'
//	}
//	return partnerId
//}
function ajax(url,data,fn,fn2){
	
	for(var i in data){
		data[i].replace("<(.[^>]*)>", "");
	}
	
	$.ajax({
		type:"post",
		url:url,
		data:data,
		success:function(data){
			if(data.resultCode == "302"){
				return location.href = data.redirectUrl;
			}
			fn(data)
		},
		error:function(){
			//异常处理；
			mui.alert('error');
			if(fn2){
				fn2()
			}
		}
		
	});
	return false
}
//将数字转换成金额显示
function toMoney(num){
    num = Number(parseFloat(num).toFixed(2));
    num = num.toLocaleString();
    return num;//返回的是字符串23,245.12保留2位小数
}
//获取路径的参数
function GetQueryString(name) {
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
//cookie
function setCookie(name, value, day) {
	var oDate = new Date();
	oDate.setDate(oDate.getDate() + day);
	document.cookie = name + "=" + value + ";expires=" + oDate;
}

function getCookie(name) {

	var arrCookie = document.cookie.split("; ");
	for(var i = 0; i < arrCookie.length; i++) {
		var arr = arrCookie[i].split("=");
		if(arr[0] == name) {
			return arr[1];
		}
	}
}

function removeCookie(name) {
	setCookie(name, 1, -1);
}
//时间戳转换
//console.log(getTime(1523416213489))
function getTime(time){
	var date = new Date(time);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '.';
        M = date.getMonth()+1 + '.';
        D = date.getDate() + '';
        h = date.getHours()<10?('0'+date.getHours())+ ':':date.getHours()+ ':';
        m = date.getMinutes()<10?('0'+date.getMinutes())+ ':':date.getMinutes()+ ':';
        s = date.getSeconds()<10?('0'+date.getSeconds()):date.getSeconds();
        return Y+M+D;
}
//时间戳转换
//console.log(getTime(1523416213489))
function getTime2(time){
	var date = new Date(time);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '年';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '月';
        D = (date.getDate()<10?'0'+date.getDate():date.getDate()) + '日';
        h = date.getHours()<10?('0'+date.getHours())+ ':':date.getHours()+ ':';
        m = date.getMinutes()<10?('0'+date.getMinutes())+ ':':date.getMinutes()+ ':';
        s = date.getSeconds()<10?('0'+date.getSeconds()):date.getSeconds();
        return Y+M+D;
}
function contrastTime(evalue) {  
    var dB = new Date(evalue.replace(/'.'/g, "/"));//获取当前选择日期  
    var d = new Date();  
    var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();//获取当前实际日期  
    if (Date.parse(str) > Date.parse(dB)) {//时间戳对比  
           return 0;  
    }   
    return 1;  
} 
//身份证验证
function idcard(str){
	var reg=/^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
	return reg.test(str)
}
//银行卡 格式 444
function　bankcode(str){
	var str2=''
	for(var i=0;i<str.length;i++){
		str2+=str[i]
		if((i+1)%4==0 && (i+1)!=str.length){
			str2+=' '
		}
	}
	return str2
}
//手机号格式344
function getphone(str){
	var str2=''
	for(var i=0;i<str.length;i++){
		str2+=str[i]
		if(i==2 &&(i+1)!=str.length){
			str2+=' '
		}
		if(i==6&&(i+1)!=str.length){
			str2+=' '
		}
		if(i==10&&(i+1)!=str.length){
			str2+=' '
		}
		
	}
	return str2
}
function phone(str){
	var reg=/^1[3|4|5|7|8|6|9][0-9]{9}$/
	return reg.test(str)
}
function formatBankNo (BankNo){
    if (BankNo.value == "") return;
    var account = new String (BankNo.value);
    account = account.substring(0,22); /*帐号的总数, 包括空格在内 */
    if (account.match (".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null){
        /* 对照格式 */
        if (account.match (".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" +
        ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null){
            var accountNumeric = accountChar = "", i;
            for (i=0;i<account.length;i++){
                accountChar = account.substr (i,1);
                if (!isNaN (accountChar) && (accountChar != " ")) accountNumeric = accountNumeric + accountChar;
            }
            account = "";
            for (i=0;i<accountNumeric.length;i++){    /* 可将以下空格改为-,效果也不错 */
                if (i == 4) account = account + " "; /* 帐号第四位数后加空格 */
                if (i == 8) account = account + " "; /* 帐号第八位数后加空格 */
                if (i == 12) account = account + " ";/* 帐号第十二位后数后加空格 */
                account = account + accountNumeric.substr (i,1)
            }
        }
    }
    else
    {
        account = " " + account.substring (1,5) + " " + account.substring (6,10) + " " + account.substring (14,18) + "-" + account.substring(18,25);
    }
    if (account != BankNo.value) BankNo.value = account;
    return account
}

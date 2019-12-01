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
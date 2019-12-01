$(document).ready(function() {
	$.each($('td'), function(index, el) { //遍历循环键盘按键是否被点击
		$(this).click(function() {
			var phone_crod = $('#fom input[name=smsCode]').val()
			if ($(this).html() == $('.clear').html()) { //判断是否按了清除按键
				$('#fom input[name=smsCode]').val(phone_crod.substr(0, phone_crod.length - 1))
				if ($('#fom input[name=smsCode]').val().length > 0) {
					$('.nextbtn').css('background', '#BEA473')
				} else {
					$('.nextbtn').css('background', '#DDDDDD')
				}
				return false;
			}
			if ($(this).html() == $('.empty').html()) { //判断是否按了清空按键，文本框都清空并聚焦第一个文本框
				$('input').val('');
				$('.nextbtn').css('background', '#DDDDDD')

				return false;
			}
			if ($('#fom input[name=smsCode]').val().length >= 6) {
				return false
			}

			$('#fom input[name=smsCode]').val(phone_crod + $(this).html())
			if ($('#fom input[name=smsCode]').val().length > 0) {
				$('.nextbtn').css('background', '#BEA473')
				$('.nextbtn').attr('data', '1')

			} else {
				$('.nextbtn').css('background', '#DDDDDD')
				$('.nextbtn').attr('data', '0')

			}
		});
	});
});

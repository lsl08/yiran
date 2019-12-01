//function ipubancardNO(){
//			
//		var bankstr=$('#input_example input[name=bankCardNo]').val().replace(/\s+/g,"")
//		var bankstr2=bankcode(bankstr)
//		$('#input_example input[name=bankCardNo]').val(bankstr2)
//		var partnerId=partnerIdstr($('#input_example input[name=partnerId]').val())
//		if($('#input_example input[name=partnerId]').val()==''){
//			mui.toast('请先选择机构名称')
//			return false
//		}
//		if(bankstr.length>=14){
//			$('.yzbtn').css('background','#BEA473')
//			$('.yzbtn').attr('data','1')
//			ajax(url()+'/cashier/h5/checkCardBin.htm',{cardNo:bankstr ,partnerId:partnerId},function(data){
//				console.log(data)
//				if(!data.success){
//					mui.toast(data.message)
//					bankflag=false
//					return false
//				}else{
//					bankflag=true
//					$('.bank_icon span').html(data.data.bankName)
//					$('.bank_icon span').attr('instCode',data.data.bankCode)
//					$('.bank_icon span').attr('dbcr',data.data.cardType)
//					bankimgs(data.data.bankCode)
//				}
//				$('.bank_icon').css('display','block')
//			})
//		}else{
//			if(bankstr.length>=6){
//					ajax(url()+'/cashier/h5/checkCardBin.htm',{cardNo:bankstr ,partnerId:partnerId},function(data){
//						console.log(data)
//						if(!data.success){
//							mui.toast(data.message)
//							bankflag=false
//							return false
//						}else{
//							bankflag=true
//							$('.bank_icon span').html(data.data.bankName)
//							$('.bank_icon span').attr('instCode',data.data.bankCode)
//							$('.bank_icon span').attr('dbcr',data.data.cardType)
//							bankimgs(data.data.bankCode)
//						}
//						$('.bank_icon').css('display','block')
//					})
//				
//			}else{
//				bankflag=false
//				$('.bank_icon').css('display','none')
//			}
//			$('.yzbtn').css('background','#DDDDDD')
//			$('.yzbtn').attr('data','0')
//		}
//		}
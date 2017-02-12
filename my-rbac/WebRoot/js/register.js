/**
 * 自定义class选择器
 */
/*function getElementsByClassName(className, root, tagName) { // root：父节点，tagName：该节点的标签名。
 // 这两个参数均可有可无
 if (root) {
 root = typeof root == "string" ? document.getElementById(root) : root;
 } else {
 root = document.body;
 }
 tagName = tagName || "*";
 if (document.getElementsByClassName) { // 如果浏览器支持getElementsByClassName，就直接的用
 return root.getElementsByClassName(className);
 } else {
 var tag = root.getElementsByTagName(tagName); // 获取指定元素
 var tagAll = []; // 用于存储符合条件的元素
 for ( var i = 0; i < tag.length; i++) { // 遍历获得的元素
 for ( var j = 0, n = tag[i].className.split(' '); j < n.length; j++) { // 遍历此元素中所有class的值，如果包含指定的类名，就赋值给tagnameAll
 if (n[j] == className) {
 tagAll.push(tag[i]);
 break;
 }
 }
 }
 return tagAll;
 }
 }*/

/**
 * 刷新验证码
 */
/*function refreshVC() {
 var newVcImg = document.getElementById("newVcImg");
 newVcImg.onclick = function() {
 var verfiCode = document.getElementById("verfiCode");
 verfiCode.src = "/register/VerifiCodeServlet?" + new Date().getTime();
 };
 }*/

/**
 * 输入提示
 */
function prompt() {
	// var promptEle = getElementsByClassName("in-prompt", null, null);
	// data-prompt
	// 循环绑定事件
	/*
	 * for ( var i = 0; i < promptEle.length; i++) { promptEle[i].onfocus =
	 * function() { var inWords = $(this).attr("data-prompt"); if (this.value ==
	 * '账户') { this.value = ''; } }; promptEle[i].onblur = function() { if
	 * (this.value == '') { this.value = '账户'; } }; }
	 */
}

// ============================================================================
// ============================================================================
$(document).ready(function() {
	// 刷新验证码
	$(".vcode-button").click(function(){
		$(".verfiCode").attr("src", "/dao/VerifiCodeServlet?" + new Date().getTime());
	});

	// 表单提示
	$(".reg-in").each(function(index, element) {
		$(this).focus(function() {
			$(".prompt-" + $(this).attr("name")).css("display", "none");
			$(this).parents(".reg-item").find(".reg-msg").remove();
		}).keydown(function(){
			$(this).triggerHandler("focus");
		});
		$(this).mouseleave(function() {
			if ($(this).attr("value").length == 0)
				$(".prompt-" + $(this).attr("name")).css("display", "block");
		});
		
		// 清空表单信息
		if($(this).val().length > 0){
			$(".prompt-" + $(this).attr("name")).css("display", "none");
		}
	});
	
	
	// 表单验证，文本框失去焦点后
	var errorMsg = {
			email : "请输入正确的E-Mail地址!",
			name : "16位以内大小写英文字母和数字",
			password : "密码应该在6-16位!",
			confirm : "两次输入的密码不一致!",
			vcode : "验证码不合法!"
	};
	var successMsg = "输入合法!";
    $('form :input').blur(function(e){
    	 var msg = "";
    	 var className = "";
         $(this).parents(".reg-item").find(".reg-msg").remove();
         // 验证账户
         if( $(this).is('#email') ){
        	 if( this.value=="" || ( this.value!="" && !/.+@.+\.[a-zA-Z]{2,4}$/.test(this.value) ) ){
        		msg = errorMsg.email;
        		className = "errMsg";
        		
        	 }else{
        		msg = successMsg;
        		className = "successMsg";
        	 }
         }
         //验证用户名
         if( $(this).is('#name') ){
        	 //^[\u4E00-\u9FA5A-Za-z][\u4E00-\u9FA5A-Za-z0-9]{0,15}$
        	 //^[a-zA-Z\d]{1,16}$
        	 if( this.value=="" || ( this.value!="" && !/^[a-zA-Z\d]{1,16}$/.test(this.value) ) ){
         		msg = errorMsg.name;
         		className = "errMsg";
         		
         	 }else{
         		msg = successMsg;
         		className = "successMsg";
         	 }
         }
         // 验证验证码
         if( $(this).is("#vcode") ){
        	 if(this.value.length != 4){
        		 msg = errorMsg.vcode;
        		 className = "errMsg";
        		 
        	 }else{
        		 msg = successMsg;
        		 className = "successMsg";
             }
         }
         // 验证密码
         if( $(this).is("#password") ){
        	 if(this.value.length > 16 || this.value.length < 6){
        		 msg = errorMsg.password;
        		 className = "errMsg";
        	 }else{
        		 msg = successMsg;
        		 className = "successMsg";
             }
         }
         // 验证确认密码
         if( $(this).is("#confirm") ){
        	 if(this.value != $("#password").val() || this.value.length > 16 || this.value.length < 6){
        		 msg = errorMsg.confirm;
        		 className = "errMsg";
        	 }else{
        		 msg = successMsg;
        		 className = "successMsg";
        	 }
         }
         
       // 显示信息
         if(className =="errMsg")
        	 $(this).parents('.reg-item').append("<div class='reg-msg "+ className +"'>" + msg +"</div>");
         else if(className =="successMsg")
        	 $(this).parents('.reg-item').append("<div class='reg-msg "+ className +"'>" + "<img class='reg-msg-img' src='/privilege/img/ok.png'/>" +"</div>");
        	 
    })/*
		 * .mouseleave(function() { $(this).triggerHandler("blur"); })
		 *//*
		 * .keyup(function(){ $(this).triggerHandler("blur"); })
		 *//*
		 * .focus(function(){ $(this).triggerHandler("blur"); })
		 */;// end blur
    
    // 提交，最终验证。
    $('#reg-send').click(function(){
    	$("form :input").trigger('blur');
        var numError = $('form .errMsg').length;
        if(numError){
            return false;
        } 
        // alert("注册成功,密码已发到你的邮箱,请查收.");
        $("#register").submit();
    });
    
    
    
});
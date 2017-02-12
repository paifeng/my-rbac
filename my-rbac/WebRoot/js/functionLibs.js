	/**
	 * 常用的js函数
	 * 
	 * 事件绑定函数		myAddEvent(obj, ev, fn)
	 * 取消事件绑定函数	myRemoveEvent(obj, ev, fn)
	 * 获取浏览器信息	windowObj   这个是对象
	 * 创建AJAX对象		creatXmlHttp()
	 * 异步请求		OpenAjax( URL , Data ,method , type , backFunctionOK , backFunctionNO )
	 * 识别终端是否PC	IsPC()
	 * 创建DOM节点	newElement( name , attr , contents , appendParent)
	 * 移除DOM节点	removeElement( obj )
	 * 对数据经行url编码	urlEncode (str)
	 */
 
/*
 * 绑定事件的函数
 * @param	obj	事件对象
 * @param	ev	时间类型
 * @param	fn	回调函数
 */
function myAddEvent(obj, ev, fn){
    if(obj.attachEvent){
      obj.attachEvent('on'+ev, fn);
    }
   else{
      obj.addEventListener(ev, fn,false);
   }
}
//事件取消绑定函数
function myRemoveEvent(obj, ev, fn){
    if(obj.detachEvent){
      obj.detachEvent('on'+ev, fn);
   }
   else{
      obj.removeEventListener(ev, fn,false);
   }
}


/*
 * 获取浏览器信息
 * @return	.innerWidth 文档宽度
 * @return	.innerHeight 文档高度
 * @return	.pageYOffset 滚动高度
 * @param	setRollHeight( h ) 设置高度	
 */
function windowObj(){
	this.getDocWidth=function(){
		var innerWidth=window.innerWidth;
		if(innerWidth==undefined){
			innerWidth=document.documentElement.clientWidth;
		}
		return innerWidth;
	}
	this.getDocHeight=function(){
		var innerHeight=window.innerHeight
		if(innerHeight==undefined){
			innerHeight=document.documentElement.clientHeight;
		}
		return innerHeight;
	}
	this.getRollHeight=function(){
		var pageYOffset=window.pageYOffset;
		if(pageYOffset==undefined){
			pageYOffset=document.documentElement.scrollTop;
		} 
		return pageYOffset;
	}
	this.setRollHeight=function(h){
		if(window.pageYOffset!=undefined){
			window.pageYOffset == h;
		}else{
			document.documentElement.scrollTop == h;
		}
	}
}
var mywindow=new windowObj();

/*
 * 创建ajax对象
 * @return 返回创建的对象
 */
function creatXmlHttp(){
	try{
	var xmlHttp=new XMLHttpRequest();
	}catch(err){
		try{
			var xmlHttp=new ActiveXObject("MSxml2.XMLHTTP");
		}catch(err){
			var xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xmlHttp;
}

/*
 * 发起ajax请求	如果使用默认参数则传入null
 * @param	URL	请求的服务器地址
 * @param	Data	请求提交数据
 * @param	type	请求类型	POST / GET
 * @param	method	是否开启异步	true / false
 * @param	backFunctionOK		请求成功的回调函数
 * @param	backFunctionNO	请求失败的回调函数
 */
function OpenAjax( URL , Data ,method , type , backFunctionOK , backFunctionNO ){        //Ajax请求
		xmlHttp = creatXmlHttp();
		if( method ==null ) method = 'POST';	//默认POST请求
		if( type ==null ) type = true;	//默认开启异步
		//window.alert(URL);
		//创建请求
		xmlHttp.open( method , URL , type );
		//创建回调函数
		xmlHttp.onreadystatechange=function (){
				if(xmlHttp.readyState==4){
					if(xmlHttp.status==200){
						if(backFunctionOK){
							
							backFunctionOK(xmlHttp.responseText);
						}
					}
					else{
						if(backFunctionNO){
							backFunctionNO(xmlHttp.status);
						}
					}
				}
			};
		//post请求头
		xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		//发送请求
		//alert(Data);
		xmlHttp.send(Data);
}

/*
 * 判断终端是手机还是PC
 * @return true 是PC
 * @return false 不是PC
 */
function IsPC() {
	var userAgentInfo = navigator.userAgent;
	var Agents = ["Android", "iPhone",
				"SymbianOS", "Windows Phone",
				"iPad", "iPod"];
	var flag = true;
	for (var v = 0; v < Agents.length; v++) {
		if (userAgentInfo.indexOf(Agents[v]) > 0) {
			flag = false;
			break;
		}
	}
	return flag;
}

/*
 * 创建DOM节点
 * @param	name	要创建的节点名称
 * @param	attr		节点的节点属性	数组或json		{ id:'idName' , style:'height:500px;' }
 * @param	contents
 * @param	appendParent
 */
function newElement( name , attr , contents , appendParent){
	var element = document.createElement( name );
	element.text =contents;
	for( var key in attr ){
		element.setAttribute( key , attr[ key ] );
	}
	appendParent.appendChild( element );
}

/*
 * 移除一个节点
 * @param	id要移除的元素对象
 */
function removeElement( obj ){
	if( obj ) obj.parentNode.removeChild( obj );
}

/*
 * 将数据进行urlencode编码
 */
function urlEncode (str) {  
	str = (str + '').toString();
	return encodeURIComponent(str).replace(/!/g, '%21').replace(/'/g, '%27').replace(/\(/g, '%28').  
	replace(/\)/g, '%29').replace(/\*/g, '%2A').replace(/%20/g, '+');  
} 

/**
 * 特殊字符过滤
 */
 function stripscript(s) { 
 
	var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？]");	//过滤中文特殊符号
	var rs = ""; 
	for (var i = 0; i < s.length; i++) { 
		rs = rs+s.substr(i, 1).replace(pattern, ''); 
	} 
	rs = d = rs.replace(/\"/g, "");	//过滤"
	rs = d = rs.replace(/\\/g, "");	//过滤\
	return rs; 
} 

/**
 *	兼容火狐浏览器的dom操作将空格和换行当作一个元素的问题
 *	在进行dom操作之前调用一下这个函数
 *	elem是父节点
 */
function del_ff(elem){

	var elem_child = elem.childNodes;

	for(var i=0; i<elem_child.length;i++){

		if(elem_child[i].nodeName == "#text" && !/\s/.test(elem_child.nodeValue)){
			elem.removeChild(elem_child)
		}
	}

}



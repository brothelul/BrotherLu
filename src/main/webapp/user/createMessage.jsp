<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<title>Around You</title>
<%@include file="js&css.jsp" %>

<c:if test="${userInfo.userType == 1}">  
<script type="text/javascript">
$(document).ready(function(){    	   
	   var page1 = 1;
	   var page2 = 1;
	   var pageSize = 4;
	   var pageCount1 = loadUserMessage(page1,pageSize,0);
	   var pageCount2 = loadUserMessage(page2,pageSize,1);
	   loadAllCate();
	   
	   $("#prev1").click(function(){ //上一页
		   alert(1);
	 		  if(page1 > 1){
	 			page1--;
	 			loadUserMessage(page1,pageSize,0);
	 		  }else{
     			  alert("已经到第一页了");
     			  return false;
     		  }
	 	  });
	 	  
	   $("#next1").click(function(){//下一页
	 		  if(page1 < pageCount1){
	  			 page1++;
	  			loadUserMessage(page1,pageSize,0);  
	 		  }else{
     			  alert("已经到最后一页了");
     			  return false;
     		  }
	   });
	   $("#prev2").click(function(){ //上一页
	 		  if(page2 > 1){
	 			page2--;
	 			loadUserMessage(page2,pageSize,1);
	 		  }else{
     			  alert("已经到第一页了");
     			  return false;
     		  }
	 	  });
	 	  
	  $("#next2").click(function(){//下一页
	 		  if(page2 < pageCount2){
	  			 page2++;
	  			loadUserMessage(page2,pageSize,1);  
	 		  }else{
     			  alert("已经到最后一页了");
     			  return false;
     		  }
	 	});
});

function loadUserMessage(page,pageSize,type){
	var pageCount = 0;
	$.ajax({		  
		  type:"post",
		  dataType:"json",
		  data:{"page":page,"pageSize":pageSize,"type":type},
		  url:"loadAuthorMessages.do",
		  cache:true,
		  async:false,
		  
		  success:function(data){
			  var item =jQuery.parseJSON(data);
	   	      var str = "<tr><th>编号</th><th>信息标题</th><th>关键字</th><th>信息分类</th><th>创建时间</th><th class='last_cell'>操作</th></tr>";
			  if(item.status == "200"){
				  var pagin = item.result;
				  pageCount = pagin.pagesCount;
				  $.each(pagin.items,function(i,msg){
						   str = str + "<tr><td>"+msg.messageNo+"</td><td style='width:200px;'>"+msg.messageTitle+"</td>";
						   str = str + "<td>"+msg.messageName+"</td>";
						   str = str + "<td>"+msg.secondMessageCategory.categoryName+"</td>";
						   str = str +"<td>"+msg.createDateString+"</td>";
						   if(type == 0){
							   str = str +"<td class='last_cell'><a href='javascript:;' onclick='edit(this);' class='test'><img src='resources/layout/images/edit.png' style='width:14px;height:14px;'/></a>&nbsp;&nbsp;<a href='deleteMsg.do?msgNo="+msg.messageNo+"'><img style='width:16px;height:16px;' src='resources/layout/images/delete.png'/></a>&nbsp;&nbsp;<a href='publishMessage.do?msgNo="+msg.messageNo+"'><img src='resources/layout/images/update.png' style='width:16px;height:16px;'/></a></td></tr>";
						   }
						   if(type == 1){
							   str = str +"<td class='last_cell'><a  href='deleteMsg.do?msgNo="+msg.messageNo+"'><img style='width:16px;height:16px;' src='resources/layout/images/delete.png'/></a></td></tr>";
						   }                    
				  });
	
				  if(type == 0){
					  $("#table1").empty();
					  $("#table1").append(str);
	 				  if(pagin.rowsCount == 0){
						  var str1 = "<div style='margin-top:10px;' id='msg1'><center>暂无信息记录</center></div>";
						  $("#table1").after(str1);
					  }
	 				  if( pagin.pagesCount <= 1){
	 					 $("#prev1").remove();
	 					 $("#next1").remove();
	 				  }
				  }
				  if(type == 1){
					  $("#table2").empty();
					  $("#table2").append(str);
	 				  if(pagin.rowsCount == 0){
	 					var str1 = "<div style='margin-top:10px;' id='msg2'><center>暂无信息记录</center></div>";
	 					$("#table2").after(str1);
					  }
	 				  if( pagin.pagesCount <= 1){
		 					 $("#prev2").remove();
		 					 $("#next2").remove();
		 				  }
				  }

			  }
		  }
	});
	return pageCount;
};

function loadAllCate(){
	
	   $.ajax({		   
		   type:"get",
		   dataType:"json",
		   url:"loadCategory.do",
		   cache:true,
		   aynsc:false,
		   
		   success:function(data){
			   var item = eval(data);	
			   var str ="";
			   $.each(item,function(i,cate){
				   str = str +"<option label='"+cate.categoryName+"' value='"+cate.categoryNo+"'/>";
			   });
			   $("#cate").empty();
			   $("#cate").append(str);
		   }
	   });
};

function edit(test){
	var msgNo = $(test).parents("tr").children(":first-child").html();
	
	$.ajax({		  
		  type:"post",
		  dataType:"json",
		  data:{"msgNo":msgNo},
		  url:"getMsg.do",
		  cache:true,
		  
		  success:function(data){
			  var item =jQuery.parseJSON(data);
			  var msg = item.result;
			  if(item.status == "200"){
				  $("#title").val(msg.messageTitle);
				  $("#name").val(msg.messageName);
				  $("#mcontent").val(msg.messageContent);
				  $("#cate").val(msg.secondMessageCategory.categoryNo);
			      $("#msgNo").val(msg.messageNo);
				  $("#named").after("<div id='msg'><a href='javascript:;'><img style='width: 50%;height: 200px;' src='"+msg.photoUri+"' alt='' id='msgUrl'/></a></div>");
			  }
		  }
	});
	
    $('.block_tabs_type_2 .tabs').tabs('.block_tabs_type_2 .tab_content', {
        initialIndex : 2
    });
};

function save(type){
	var msgNo = $("#msgNo").val();
	var title = $("#title").val();
	var name = $("#name").val();
	var content = $("#mcontent").val();
	var cateNo = $("#cate").val();
	var msgUrl = $("#msgUrl").attr("src");
	alert(msgUrl);
	
	$.ajax({		  
		  type:"post",
		  dataType:"json",
		  data:{"msgNo":msgNo,"title":title,"name":name,"content":content,"cateNo":cateNo,"msgUrl":msgUrl,"type":type},
		  url:"saveMessage.do",
		  cache:true,
		  
		  success:function(data){
			  var item =jQuery.parseJSON(data);
			  if(item.status == "200"){
				  if(type == 0){
					  $('.block_tabs_type_2 .tabs').tabs('.block_tabs_type_2 .tab_content', {
					        initialIndex : 0
					  });
				  }
				  if(type == 1){

					  $('.block_tabs_type_2 .tabs').tabs('.block_tabs_type_2 .tab_content', {
					        initialIndex : 1
					  });
				  }
		   	      $("#msg1").remove();
		   	      $("#msg2").remove();
				  loadUserMessage(1,6,0);
				  loadUserMessage(1,6,1);
				  $("#msg").remove('');
				  $("#msgNo").val('');
				  $("#title").val('');
				  $("#name").val('');
				  $("#mcontent").val('');
				  $("#cate").val('');
			  }
		  }
	});
};

function uploadImg(){
	var formData = new FormData();
	var file = $("#file")[0].files[0];
	formData.append("file",file);
	alert(file.name);
	alert(file.size);
	
	$.ajax({ 
		url : "fileUpload.do", 
		type : "post",
		dataType:"json",
		data : formData, 
		// 告诉jQuery不要去处理发送的数据
		processData : false, 
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,

		success : function(data){
			var item = jQuery.parseJSON(data);
			alert(item.status);
			if(item.status == "200"){
				 $("#named").after("<div id='msg'><a href='javascript:;'><img style='width: 50%;height: 200px;' src='"+item.result+"' alt='' id='msgUrl'/></a></div>");
			}
		 }
		});
};

</script>
</c:if>
<c:if test="${userInfo.userType == 0}">  
<script type="text/javascript">
//上传用户的认证信息
function uploadImgU(){
	var formData = new FormData();
	var file = $("#userId")[0].files[0];
	formData.append("file",file);
	
	$.ajax({ 
		url : "uploadUserId.do", 
		type : "post",
		dataType:"json",
		data : formData, 
		// 告诉jQuery不要去处理发送的数据
		processData : false, 
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,

		success : function(data){
			var item = jQuery.parseJSON(data);
			alert(item.status);
			if(item.status == "200"){
				window.location.reload();
			}
		 }
		});
};

</script>
</c:if>
<script type="text/javascript">
function uploadIcon(){
	var formData = new FormData();
	var file = $("#icon")[0].files[0];
	formData.append("file",file);
	alert(file.name);
	alert(file.size);
	
	$.ajax({ 
		url : "fileUpload.do", 
		type : "post",
		dataType:"json",
		data : formData, 
		// 告诉jQuery不要去处理发送的数据
		processData : false, 
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,

		success : function(data){
			var item = jQuery.parseJSON(data);
			alert(item.status);
			if(item.status == "200"){
				 $("#uIcon").removeAttr("src");
				 $("#uIcon").attr("src",item.result);
			}
		 }
		});
};
function saveUserChange(){
	var photo = $("#uIcon").attr("src");
	var email = $("#mail").val();
	$.ajax({		  
		  type:"post",
		  dataType:"json",
		  data:{"photo":photo,"email":email},
		  url:"saveUserChange.do",
		  cache:false,
		  
		  success:function(data){
			  var item =jQuery.parseJSON(data);
			  if(item.status == "200"){
				  window.location.reload();
				  alert("修改成功，重新登录后生效");
				}
			  }
	});
}

function validateEmail(){
	 var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	 var email_val = $("#email").val();
	 if(!search_str.test(email_val)){       
	 alert("请输入正确的邮箱");
	 $('#email').focus();
	 return false;
	 }
};
</script>
<body>
	<div class="wrapper sticky_footer">
    	<!-- HEADER BEGIN -->
        <%@include file="create_header.jsp" %>       
        <!-- CONTENT BEGIN -->
        <div id="content" class="right_sidebar">
        	<div class="inner">
            <div class="general_content">
            	<div class="main_content">                      
                        <h2>个人中心</h2>                        
                        <div class="line_4" style="margin:0px 0px 20px;"></div>
             <c:if test="${userInfo == null}"><div style="font-size: 15px;text-align: center;">请先<a style="font-size: 15px;color: #ed4040;" href="#login" class="open_popup">登录</a></div></c:if>
             <c:if test="${userInfo.userType == 1}">                                              
                        <div class="block_tabs_type_2">
                            <div class="tabs">
                                <ul>
                                    <li><a href="#1" class="current">草稿消息</a></li><!-- tab link -->
                                    <li><a href="#2">待审核消息</a></li><!-- tab link -->
                                    <li><a href="#3">写消息</a></li><!-- tab link -->      
                                    <li><a href="#4">个人资料</a></li>                            
                                </ul>
                            </div>
                                        
                            <div class="tab_content">
                             
                        <table cellpadding="0" cellspacing="0" class="table_1" id="table1">
                        </table>
                        <div class="block_pager" id="page" style="margin-top: 10px;">
                        	<a href="javascript:;" class="prev" id ="prev1">Previous</a>
                            <a href="javascript:;" class="next" id ="next1">Next</a>                                                       
                            <div class="clearboth"></div>
                        </div>
                        </div>
                                        
                      		<div class="tab_content">
                      		 
                        <table cellpadding="0" cellspacing="0" class="table_1" id="table2">
                        </table>
                         <div class="block_pager" id="page" style="margin-top: 10px;">
                        	<a href="javascript:;" class="prev" id="prev2">Previous</a>
                            <a href="javascript:;" class="next" id="next2">Next</a>
                                                       
                            <div class="clearboth"></div>
                        </div>
                            </div>
                                        
                            <div class="tab_content">
                      		<div class="block_leave_reply">
                      	     <form class="w_validation" id="comment" enctype="multipart/form-data">
                      	     <input type="hidden" id="msgNo"/>
                      	       <div class="field"><input type="text" placeholder ="信息标题" id="title"/></div>
                      	       <div class="field" id="named"><input type="text" placeholder ="关键字" id="name"/></div>
                      	       
                      	       <div style="margin-top: 10px;margin-bottom: 15px;">
                      	       <label>文章图片</label>
                      	       <input type="file" placeholder ="上传文章图片" id="file" onchange="uploadImg();"/>
                      	       </div>
                      	       <div style="margin-bottom: 15px;">
                      	       <label>分类</label>
                      	       <select id="cate">
                      	       </select>
                      	       </div>
                               <div class="textarea">
                               <textarea cols="1" rows="1" placeholder="文章内容" id="mcontent"></textarea>
                               </div> 
                               <div style="margin-bottom:10px;">
                               <div style="float: left;"><input type="button" class="general_button" value="保存"  onclick="save(0);"/></div>
                               <div style="float: right;"><input type="button" class="general_button" value="保存并提交" onclick="save(1);" /></div>
                               </div>
                            </form>
                            </div>
                            </div>
                            <div class="tab_content">
                             <div><a href="javascript:;"><img style="margin-top:15px;margin-left:40px;height: 80px;border-radius:38px;width: 80px;" src="${userInfo.faviconUri}" alt=""  id="uIcon"/></a>                              
                             </div>
                             <div style="margin-top: 10px;"><label>上传新头像</label><input type="file" id="icon" onchange="uploadIcon();"/></div>
                             <div style="margin-top: 15px;" id="email"><label>邮箱</label><input type="text" value="${userInfo.emailAddress}" id="mail"/></div>
                              <div style="margin-bottom:15px;margin-top: 15px;">
                              <div style="float: left;margin-left:40px;margin-bottom: 20px;"><input type="button" class="general_button" value="确认修改" onclick="saveUserChange();"/></div>
                              </div>
                             </div>
                                        
                          	<script type="text/javascript">
                                $('.block_tabs_type_2 .tabs').tabs('.block_tabs_type_2 .tab_content', {
                                    initialIndex : 0
                                });
                            </script>
                        </div>
                        
     </c:if> 
     <c:if test="${userInfo.userType == 0}">
                        <div class="block_tabs_type_2">
                            <div class="tabs">
                                <ul>
                                    <li><a href="#1" class="current">申请成为商家</a></li>
                                    <li><a href="#2">个人资料</a></li>
                                </ul>
                            </div>
                            <div class="tab_content">
                      		<div class="block_leave_reply">
                      		<c:if test="${userInfo.passIdentify == 0}">
                      	     <form class="w_validation" id="comment" >
  
                      	       <div style="margin-top: 10px;margin-bottom: 15px;">
                      	       <label>认证照片</label>
                      	       <input type="file" placeholder ="上传文章图片" id="userId"/>
                      	       </div>
                               <div style="margin-bottom:10px;">
                               <div style="float: right;"><input type="button" class="general_button" value="提交申请" onclick="uploadImgU()"/></div>
                               </div>
                            </form>
                            </c:if>
                            <c:if test="${userInfo.passIdentify == 1}">
                                                                                                 已经提交申请，管理员正在火速处理...
                            </c:if>
                            </div>
                            </div>
                             
                             <div class="tab_content">
                             <div><a href="javascript:;"><img style="margin-top:15px;margin-left:40px;height: 80px;border-radius:38px;width: 80px;" src="${userInfo.faviconUri}" alt=""  id="uIcon"/></a>                              
                             </div>
                             <div style="margin-top: 10px;"><label>上传新头像</label><input type="file" id="icon" onchange="uploadIcon();"/></div>
                             <div style="margin-top: 10px;" id="email"><label>邮箱</label><input type="text" value="${userInfo.emailAddress}" id="mail"/></div>
                              <div style="margin-bottom:10px;margin-top: 10px;">
                              <div style="float: left;margin-left:40px;"><input type="button" class="general_button" value="确认修改" onclick="saveUserChange();"/></div>
                              </div>
                             </div>
                                        
                          	<script type="text/javascript">
                                $('.block_tabs_type_2 .tabs').tabs('.block_tabs_type_2 .tab_content', {
                                    initialIndex : 0
                                });
                            </script>
                        </div>
              </c:if>                      
                        <div class="line_2" style="margin:22px 0px 20px;"></div>    
                        <div class="clearboth"></div>
                       </div>   
                                                                              
                      <%@include file="side.jsp" %>                                       
                	   <div class="clearboth"></div>
                </div>
                </div>
                </div>
    	<!-- CONTENT END -->
        <!-- FOOTER BEGIN -->
        <%@include file="footer.jsp" %>
    </div>
        <%@include file="loginPopup.jsp" %>
</body>

</html>
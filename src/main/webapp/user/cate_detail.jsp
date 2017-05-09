<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Around You</title>

<%@include file="js&css.jsp" %>
        <script type="text/javascript">
  	   
        $(document).ready(function(){     	       	       	   
     	   //获取所有消息    	   
     	var page = 1;
     	var pageSize = 6;
     	var pageCount = loadCateMessage(page,pageSize);
   	  $(".prev").click(function(){ //上一页
 		  if(page > 1){
 			page--;
 			loadCateMessage(page,pageSize);
 		  }else{
 			  alert("已经到第一页了");
 			  return false;
 		  }
 	  });
 	  
 	  $(".next").click(function(){//下一页
 		  if(page < pageCount){
  			 page++;
  			loadCateMessage(page,pageSize);  
 		  }else{
 			  alert("已经到最后一页了");
 			  return false;
 		  }

 	  });
        }); 
        
     function getUrlVars(){
            var vars = [], hash;
            var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            for(var i = 0; i < hashes.length; i++)
            {
                hash = hashes[i].split('=');
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
            return vars;
        }

        //得到指定参数的value
        function getUrlVar(name){
            return getUrlVars()[name];
        }
 
      function loadCateMessage(page,pageSize){
    	  var pageCount = 0;
            var cateNo = getUrlVar("cateNo");
        	$.ajax({		  
        		  type:"post",
        		  dataType:"json",
        		  data:{"page":page,"pageSize":pageSize,"cateNo":cateNo},
        		  url:"loadCatMessages.do",
        		  cache:true,
        		  async:false,
        		  
        		  success:function(data){
        			  var item =jQuery.parseJSON(data);
        			  if(item.status == "200"){
        				  var header = "";
         				  var desc="";
             			  var str = "";
        				  var pagin = item.result;
        				  $.each(pagin.items,function(i,msg){
        					  
            				   if(i==0){
             					   str = str +"<article class='block_news_post_feature'><div class='f_pic'>";
             					   header = msg.secondMessageCategory.categoryName;
             					   desc = msg.secondMessageCategory.categoryDesc;
             				   }else{
             					   /* str = str +"<article class='block_news_post'><div class='f_pic'>"; */
             					  str = str +"<article class='block_news_post_feature'><div class='f_pic'>";
             				   }
         					   str = str +"<a href='getMessage.do?messageNo="+msg.messageNo+"' class='general_pic_hover scale'>";
         					   str = str + "<img src='"+msg.photoUri+"' alt=''/></a></div><h4 class='title'>";
         					   str = str + "<a href='getMessage.do?messageNo="+msg.messageNo+"'>";
         					   str = str + msg.messageTitle+"</a></h4><div class='info'>";
         					   str = str + "<div class='date'><p>"+msg.createDateString+"</p></div><div class='r_part'>";
         					   str = str + "<div class='category'><p><a class='general_button w_icon tag'><span>"+msg.messageName+"</span></a></p></div>";
         					   str = str + "<a class='comments'>"+msg.cmtCount+"</a></div><div class='clearboth'></div></div></article>";					   
             			   
        			  });
        				  if(pagin.pagesCount <= 1){
        					  $("#page").remove();
        				  }
        				  if(pagin.rowsCount == 0){
        					  desc="暂无该分类的消息信息";
        					  $("#page").remove();
        					  $("#line").remove();
        				  }
            			   $(".main_content h2").empty();
             			   $(".main_content h2").append(header);
             			   $(".general_subtitle").empty();
             			   $(".general_subtitle").append(desc);
             			   $(".block_main_news").empty();
             			   $(".block_main_news").append(str);
      				       pageCount = pagin.pagesCount;
        			 }
        		  }
        	});
        	return pageCount;
        };
        </script>

<body>
	<div class="wrapper sticky_footer">

        <%@include file="header.jsp" %>
        
        
        <!-- CONTENT BEGIN -->
        <div id="content" class="right_sidebar">
        	<div class="inner">
            	<div class="general_content">
                	<div class="main_content">
                        
                        <h2></h2>
                        
                        <p class="general_subtitle"></p>
                        
                        <div class="line_4" style="margin:0px 0px 22px;"></div>
                        
                        <div class="block_main_news">                       
                
                        </div>
                        
                        <div class="line_2" style="margin:8px 0px 25px;" id ="line"></div>
                        
                        <div class="block_pager" id="page">
                        	<a href="javascript:;" class="prev">Previous</a>
                            <a href="javascript:;" class="next">Next</a>                                                      
                            <div class="clearboth"></div>
                        </div>
                        
                    </div>
 
                      <%@include file="side.jsp" %>
                      
                   </div>
            </div>
        </div>
    	<!-- CONTENT END -->
        
        <%@include file="footer.jsp" %>
        
    </div>
       
    <%@include file="loginPopup.jsp" %>
       
</body>
</html>
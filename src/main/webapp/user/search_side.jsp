<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>  
<script type="text/javascript">   
var keyword = "";
$(document).ready(function(){

    $.ajax({		   
 	   type:"get",
 	   dataType:"json",
 	   url:"loadHostKey.do",
 	   cache:true,
 	   
 	   success:function(data){	
		   var items =jQuery.parseJSON(data); 
		   if(items.status == "200"){
			   $.each(items.result,function(i,item){
				   $("#hostKey").append("<li style='line-height: 20px;'><a style='font-size: 16px;' href='search.jsp?keyWord="+item+"'><i class='search-no search-no-"+(i+1)+"'>"+(i+1)+"</i>"+item+"</a></li>");
				   if(i == 0){
					   keyword = item;
				   }
			   });
		   }
 	   }
 	});
});
</script>
        <div class="sidebar">                       
                     <div class="block_search_top">
                        	<h4 style="font-size: 18px;">搜索排行榜</h4>                           
                           <div class="clearboth"></div>                  
                        <div class="line_2"></div>                                           
                        <div> 
                           <ul id="hostKey">
<!--                               <li style="line-height: 30px;"><a >1 test1</a></li>
                              <li><a>test2</a></li> -->
                           </ul>  
                        </div>    
                	<div class="clearboth"></div>
        </div>
</div>
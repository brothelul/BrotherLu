<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
    <script type="text/javascript">

    (function(){    	       	   

  	   $.ajax({
  		   
  		   cashe:true,
  		   dataType:"json",
  		   type:"get",
  		   url:"getTodayMostHot.do",
  		   
  		   success:function(data){
  			   
  			   var item = eval(data);
  			   if(item != null){
  				 var str = "";
    			   $.each(item,function(i,item){
      				   if(i==0){
      					 str = str + "<h4>今日最热</h4>";
      				   }
      				   str = str + "<div class='article'><div class='pic'><a class='w_hover'><img src='"+item.photoUri+"' alt='' /><span></span></a></div>";
      				   str = str +"<div class='text'><p class='title'><a href='getMessage.do?messageNo="+item.messageNo+"'>"+item.messageTitle+"</a></p><div class='date'><p>"
      				   str = str +item.createDateString+"</p></div><div class='icons'><ul><li><a class='comments'>"+item.cmtCount+"</a></li></ul></div></div></div>";
      				   str = str +"	<div class='line_3'></div>";
      				   
      			   });
  				   $(".block_popular_posts").append(str);
  			   } 			   
  			   return false;
  		   }
  		   
  	   });
    })();
    </script>
      
        <div class="sidebar">                       
                     <div class="block_search_top">
                        	<h4>热门搜索</h4>                           
                            <form action="search.jsp" method="get">
                            	<div class="field">
                            	<input type="text" class="w_def_text" title="输入关键字" name="keyWord"/>
                            	</div>
                                <button type="submit" class="button" value="submit"></button>                               
                                <div class="clearboth"></div>
                            </form>
                          </div>
                           <div class="clearboth"></div>                       
                        <div class="line_2"></div>     
                                                        
                     <div class="separator" style="height:31px;"></div>
                        
                        <div class="block_popular_posts"></div>
                        
                        <div class="separator" style="height:31px;"></div>
<!--                         
                        <div class="block_popular_stuff">
                        	<h4>热门视频</h4>
                            
                            <div class="content">
                            	<a href="#" class="view_all">查看所有</a>
                            	<div class="media"><a href="http://www.youtube.com/watch?v=ySIvism2af8" class="general_pic_hover play no_fx" data-rel="prettyPhoto" title="Popular Video"><img src="resources/images/pic_pop_video.jpg" alt="" /></a></div>
                                <p><a href="blog_post_w_video.html">Publishing packages and web page editors their default model.</a> <img src="resources/images/icon_video.gif" alt="" /></p>
                                <p class="date">11 July, 2012</p>
                            </div>
                            
                            <div class="info">
                            	<ul>
                                	<li class="comments"><a href="#">115</a></li>
                                    <li class="views"><a href="#">220</a></li>
                                </ul>
                            </div>
                            
                            <div class="clearboth"></div>
                            
                            <div class="line_2"></div>
                        </div>
                        
                        <div class="separator" style="height:31px;"></div> -->
                        
                        <div class="block_calendar">
<!--                         	<h4>日历</h4> -->                           
                           <div class="calendar" id="calendar_sidebar">
                            </div>
                            
                            <script type="text/javascript">
								var today = new Date();
								var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
								$('#calendar_sidebar').DatePicker({
									flat : true,
									date : date,									
									calendars : 1,
									starts : 0,
									locale : {
										days : ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'],
										daysMin : ['日', '一', '二', '三', '四', '五', '六', '日'],
										months : ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
										monthsShort : ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
										weekMin : '周',
									}
								});
							</script>
                            
                            <div class="line_2"></div>
                        </div>
                        
                        <div class="separator" style="height:31px;"></div>                    
                        
                    </div>
                    <div class="block_all">
                      <div class="block_to_top ">
                        	<a href="#" >返回顶部</a>
                      </div>   
                      <br/>
                      <div class="block_refresh">
                        	<a href="#">刷新页面</a>
                        </div>
                         
                    </div>
                	<div class="clearboth"></div>
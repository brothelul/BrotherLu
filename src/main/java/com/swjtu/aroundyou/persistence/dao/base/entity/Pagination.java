package com.swjtu.aroundyou.persistence.dao.base.entity;

import java.util.List;

/**
 * 
 * @author brotherlu
 *
 * @param <T>  泛型任意实体类对象
 */
public class Pagination<T>{

        private long preIndex;  
	    private long curIndex;    
	    private long nextIndex;   
	    private long pageSize;  //每页条数 
	    private long rowsCount;  //总条数  
	    private long pagesCount;  //总页数
	    private List<T> items;  //列表对象
	  
	    public Pagination() {  
	        updateInfo(0, 0, 0);  
	    }  
	    
	    public Pagination(long pageIndex, long pageSize, long rowsCount) {  
	        updateInfo(pageIndex, pageSize, rowsCount);  
	    }  
	    
	    public Pagination(long pageIndex, long pageSize) {  
	        updateInfo(pageIndex, pageSize, 0);  
	    }  
	    
	    public void setPreIndex(long preIndex) {  
	        this.preIndex = preIndex;  
	    }  
	  
	    public void setCurIndex(long curIndex) {  
	        this.curIndex = curIndex;  
	    }  
	  
	    public void setNextIndex(long nextIndex) {  
	        this.nextIndex = nextIndex;  
	    }  
	  
	    public void setPageSize(long pageSize) {  
	        this.pageSize = pageSize;  
	    }  
	  
	    public long getFirstIndex() {  
	        return 1;   //默认初始为第一页
	    }  
	    
	    public long getLastIndex() {  
	        return pagesCount;   //末页默认为最大页数
	    }  
	    
	    public void setRowsCount(long rowsCount) {  
	        updateInfo(curIndex, pageSize, rowsCount);  
	    } 
	     
	    public long getPagesCount() {
			return pagesCount;
		}

		public void setPagesCount(long pagesCount) {
			this.pagesCount = pagesCount;
		}

		public List<T> getItems() {
			return items;
		}

		public void setItems(List<T> items) {
			this.items = items;
		}

		public long getPreIndex() {
			return preIndex;
		}

		public long getCurIndex() {
			return curIndex;
		}

		public long getNextIndex() {
			return nextIndex;
		}

		public long getPageSize() {
			return pageSize;
		}

		public long getRowsCount() {
			return rowsCount;
		}

		private void updateInfo(long pageIndex, long pageSize, long rowsCount) {  
	  
	        if (pageSize > 0) {  
	  
	            this.curIndex = pageIndex;  
	            this.rowsCount = rowsCount;  
	            this.pageSize = pageSize;  
	  
	            // 确定页数  
	            pagesCount = (rowsCount + pageSize - 1) / pageSize;  
	            // 确定当前页码  
	            if (curIndex <= 0)  
	                curIndex = 1;  
	            if (curIndex > pagesCount)  
	                curIndex = pagesCount;  
	            // 确定下一页码  
	            nextIndex = curIndex + 1;  
	            if (nextIndex > pagesCount)  
	                nextIndex = pagesCount;  
	            // 确定上一页码  
	            preIndex = curIndex - 1;  
	            if (preIndex <= 0)  
	                preIndex = 1;  
	        } else {  
	            this.preIndex = 1;  
	            this.curIndex = 1;  
	            this.nextIndex = 1;  
	            this.pageSize = 0;  
	            this.pagesCount = 1;  
	        }  
	    }  	      
	  
}

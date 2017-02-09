package com.swjtu.aroundyou.persistence.dao.base.entity;

import java.util.List;

/**
 * 
 * @author brotherlu
 *
 * @param <T>  ��������ʵ�������
 */
public class Pagination<T>{

        private long preIndex;  
	    private long curIndex;    
	    private long nextIndex;   
	    private long pageSize;  //ÿҳ���� 
	    private long rowsCount;  //������  
	    private long pagesCount;  //��ҳ��
	    private List<T> items;  //�б����
	  
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
	        return 1;   //Ĭ�ϳ�ʼΪ��һҳ
	    }  
	    
	    public long getLastIndex() {  
	        return pagesCount;   //ĩҳĬ��Ϊ���ҳ��
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
	  
	            // ȷ��ҳ��  
	            pagesCount = (rowsCount + pageSize - 1) / pageSize;  
	            // ȷ����ǰҳ��  
	            if (curIndex <= 0)  
	                curIndex = 1;  
	            if (curIndex > pagesCount)  
	                curIndex = pagesCount;  
	            // ȷ����һҳ��  
	            nextIndex = curIndex + 1;  
	            if (nextIndex > pagesCount)  
	                nextIndex = pagesCount;  
	            // ȷ����һҳ��  
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

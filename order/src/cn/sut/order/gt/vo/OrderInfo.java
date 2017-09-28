package cn.sut.order.gt.vo;

public class OrderInfo {
	
	private int orderId;
	private String orderBeginDate;
	private String orderEndDate;
	private int waiterId;
	private int orderState;
	private int tableId;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderBeginDate() {
		return orderBeginDate;
	}
	public void setOrderBeginDate(String orderBeginDate) {
		this.orderBeginDate = orderBeginDate;
	}
	public String getOrderEndDate() {
		return orderEndDate;
	}
	public void setOrderEndDate(String orderEndDate) {
		this.orderEndDate = orderEndDate;
	}
	public int getWaiterId() {
		return waiterId;
	}
	public void setWaiterId(int waiterId) {
		this.waiterId = waiterId;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	

}

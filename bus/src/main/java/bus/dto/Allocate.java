package bus.dto;

public class Allocate {
	int allocateid;
	int busid;
	int driverid;
	boolean operate_check;
	String allo_date;
	String bus_num;
	String state;
	String name;
	int limit_passenger;
	boolean cancel_check;
	String cancel_reason;
	
	public String getCancel_reason() {
		return cancel_reason;
	}
	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}
	public int getAllocateid() {
		return allocateid;
	}
	public void setAllocateid(int allocateid) {
		this.allocateid = allocateid;
	}
	public int getBusid() {
		return busid;
	}
	public void setBusid(int busid) {
		this.busid = busid;
	}
	public int getDriverid() {
		return driverid;
	}
	public void setDriverid(int driverid) {
		this.driverid = driverid;
	}
	public boolean isOperate_check() {
		return operate_check;
	}
	public void setOperate_check(boolean operate_check) {
		this.operate_check = operate_check;
	}
	public String getAllo_date() {
		return allo_date;
	}
	public void setAllo_date(String allo_date) {
		this.allo_date = allo_date;
	}
	public String getBus_num() {
		return bus_num;
	}
	public void setBus_num(String bus_num) {
		this.bus_num = bus_num;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLimit_passenger() {
		return limit_passenger;
	}
	public void setLimit_passenger(int limit_passenger) {
		this.limit_passenger = limit_passenger;
	}
	public boolean isCancel_check() {
		return cancel_check;
	}
	public void setCancel_check(boolean cancel_check) {
		this.cancel_check = cancel_check;
	}
}

package bus.dto;

public class Driver {
	int driverid;
	int busid;
	int allocateid;
	String name;
	String birth;
	String join_date;
	String state;
	boolean cancel_check;
	String allo_date;
	
	public int getDriverid() {
		return driverid;
	}
	public void setDriverid(int driverid) {
		this.driverid = driverid;
	}
	public int getBusid() {
		return busid;
	}
	public void setBusid(int busid) {
		this.busid = busid;
	}
	public int getAllocateid() {
		return allocateid;
	}
	public void setAllocateid(int allocateid) {
		this.allocateid = allocateid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isCancel_check() {
		return cancel_check;
	}
	public void setCancel_check(boolean cancel_check) {
		this.cancel_check = cancel_check;
	}
	public String getAllo_date() {
		return allo_date;
	}
	public void setAllo_date(String allo_date) {
		this.allo_date = allo_date;
	}
}

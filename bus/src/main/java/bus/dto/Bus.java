package bus.dto;

public class Bus {
	int busid;
	String bus_num;
	int limit_passenger;
	int intro_year;
	String state;
	String name;
	String allo_date;
	boolean cancel_check;
	int allocateid;
	
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
	public String getBus_num() {
		return bus_num;
	}
	public void setBus_num(String bus_num) {
		this.bus_num = bus_num;
	}
	public int getLimit_passenger() {
		return limit_passenger;
	}
	public void setLimit_passenger(int limit_passenger) {
		this.limit_passenger = limit_passenger;
	}
	public int getIntro_year() {
		return intro_year;
	}
	public void setIntro_year(int intro_year) {
		this.intro_year = intro_year;
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
	public String getAllo_date() {
		return allo_date;
	}
	public void setAllo_date(String allo_date) {
		this.allo_date = allo_date;
	}
	public boolean isCancel_check() {
		return cancel_check;
	}
	public void setCancel_check(boolean cancel_check) {
		this.cancel_check = cancel_check;
	}
}

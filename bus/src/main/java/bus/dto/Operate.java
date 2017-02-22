package bus.dto;

public class Operate {
	int operateid;
	int allocateid;
	String oper_date;
	int oper_count;
	int accu_passenger;
	int average;
	String bus_num;
	
	public int getOperateid() {
		return operateid;
	}
	public void setOperateid(int operateid) {
		this.operateid = operateid;
	}
	public int getAllocateid() {
		return allocateid;
	}
	public void setAllocateid(int allocateid) {
		this.allocateid = allocateid;
	}
	public String getOper_date() {
		return oper_date;
	}
	public void setOper_date(String oper_date) {
		this.oper_date = oper_date;
	}
	public int getOper_count() {
		return oper_count;
	}
	public void setOper_count(int oper_count) {
		this.oper_count = oper_count;
	}
	public int getAccu_passenger() {
		return accu_passenger;
	}
	public void setAccu_passenger(int accu_passenger) {
		this.accu_passenger = accu_passenger;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public String getBus_num() {
		return bus_num;
	}
	public void setBus_num(String bus_num) {
		this.bus_num = bus_num;
	}
}

package bus.dto;

public class Route {
	int gid;
	int objectid_1;
	String road_name;
	double shape_leng;
	String geom;
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getObjectid_1() {
		return objectid_1;
	}
	public void setObjectid_1(int objectid_1) {
		this.objectid_1 = objectid_1;
	}
	public String getRoad_name() {
		return road_name;
	}
	public void setRoad_name(String road_name) {
		this.road_name = road_name;
	}
	public double getShape_leng() {
		return shape_leng;
	}
	public void setShape_leng(double shape_leng) {
		this.shape_leng = shape_leng;
	}
	public String getGeom() {
		return geom;
	}
	public void setGeom(String geom) {
		this.geom = geom;
	}
}

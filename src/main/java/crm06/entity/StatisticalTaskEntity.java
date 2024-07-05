package crm06.entity;

public class StatisticalTaskEntity {
	private int chuaThucHien;
    private int dangThucHien;
    private int daThucHien;
    private double chuaThucHienPercent;
    private double dangThucHienPercent;
    private double daThucHienPercent;
    
    
    
	public StatisticalTaskEntity(double chuaThucHienPercent, double dangThucHienPercent, double daThucHienPercent) {
		super();
		this.chuaThucHienPercent = chuaThucHienPercent;
		this.dangThucHienPercent = dangThucHienPercent;
		this.daThucHienPercent = daThucHienPercent;
	}
	public StatisticalTaskEntity(int chuaThucHien, int dangThucHien, int daThucHien) {
		super();
		this.chuaThucHien = chuaThucHien;
		this.dangThucHien = dangThucHien;
		this.daThucHien = daThucHien;
	}
	public int getChuaThucHien() {
		return chuaThucHien;
	}
	public void setChuaThucHien(int chuaThucHien) {
		this.chuaThucHien = chuaThucHien;
	}
	public int getDangThucHien() {
		return dangThucHien;
	}
	public void setDangThucHien(int dangThucHien) {
		this.dangThucHien = dangThucHien;
	}
	public int getDaThucHien() {
		return daThucHien;
	}
	public void setDaThucHien(int daThucHien) {
		this.daThucHien = daThucHien;
	}
	public double getChuaThucHienPercent() {
		return chuaThucHienPercent;
	}
	public void setChuaThucHienPercent(double chuaThucHienPercent) {
		this.chuaThucHienPercent = chuaThucHienPercent;
	}
	public double getDangThucHienPercent() {
		return dangThucHienPercent;
	}
	public void setDangThucHienPercent(double dangThucHienPercent) {
		this.dangThucHienPercent = dangThucHienPercent;
	}
	public double getDaThucHienPercent() {
		return daThucHienPercent;
	}
	public void setDaThucHienPercent(double daThucHienPercent) {
		this.daThucHienPercent = daThucHienPercent;
	}
    
	
}

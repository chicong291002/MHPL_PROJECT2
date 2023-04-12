package QuanLyMayTinh.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="khachhang")
public class KhachHang {
	private int maKH;
	private String hoKH;
	private String tenKH;
	private String gioitinh;
	private int tongchitieu;
	private int tinhTrang;
	
	public KhachHang() {
	}

	public KhachHang(int maKH, String hoKH, String tenKH, String gioitinh, int tongchitieu, int tinhTrang) {
		this.maKH = maKH;
		this.hoKH = hoKH;
		this.tenKH = tenKH;
		this.gioitinh = gioitinh;
		this.tongchitieu = tongchitieu;
		this.tinhTrang = tinhTrang;
	}
@Id
@GeneratedValue
@Column(name="maKH")
	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
@Column(name="Ho")
	public String getHoKH() {
		return hoKH;
	}

	public void setHoKH(String hoKH) {
		this.hoKH = hoKH;
	}
@Column(name="Ten")
	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
@Column(name="Gioitinh")
	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
@Column(name="Tongchitieu")
	public int getTongchitieu() {
		return tongchitieu;
	}

	public void setTongchitieu(int tongchitieu) {
		this.tongchitieu = tongchitieu;
	}
@Column(name="Tinhtrang")
	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	
	
}

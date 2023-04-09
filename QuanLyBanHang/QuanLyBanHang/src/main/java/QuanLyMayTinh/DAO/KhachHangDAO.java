package QuanLyMayTinh.DAO;

import QuanLyMayTinh.DTO.KhachHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class KhachHangDAO {
	public List<KhachHang> getListKhachHang() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = (Session) factory.getCurrentSession();
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			session.getTransaction().begin();
			String sql = "Select e from " + KhachHang.class.getName() + " e";
			@SuppressWarnings("unchecked")
			List<KhachHang> danhsachkhachhang = session.createQuery(sql).list();
			
			for (KhachHang kh : danhsachkhachhang) {
				KhachHang kh1 = new KhachHang();
				kh1.setMaKH(kh.getMaKH());
				kh1.setHoKH(kh.getHoKH());
				kh1.setTenKH(kh.getTenKH());
				kh1.setGioitinh(kh.getGioitinh());
				kh1.setTongchitieu(kh.getTongchitieu());
				dskh.add(kh1);
			}
			session.getTransaction().commit();
			return dskh;
		} catch (Exception ex) {
			session.getTransaction().rollback();
		}
		return dskh;

	}

	public KhachHang getKhachHang(int maKH) {
		KhachHang kh = null;
		try {
			String sql = "SELECT * FROM khachhang WHERE MaKH=? AND TinhTrang=1";
			PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
			prep.setInt(1, maKH);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				kh = new KhachHang();
				kh.setMaKH(rs.getInt(1));
				kh.setHoKH(rs.getString(2));
				kh.setTenKH(rs.getString(3));
				kh.setGioitinh(rs.getString(4));
				kh.setTongchitieu(rs.getInt(5));
			}
		} catch (SQLException ex) {
			return null;
		}
		return kh;
	}

	public boolean addKhachHang(KhachHang kh) {
		boolean result = false;

		return result;
	}

	public boolean deleteKhachHang(int maKH) {
		boolean result = false;

		return result;
	}

	public boolean updateKhachHang(KhachHang kh) {
		boolean result = false;

		return result;
	}

	public boolean updateTongChiTieu(int maKH, int tongChiTieu) {
		boolean result = false;

		return result;
	}

	public boolean nhapExcel(KhachHang kh) {
		try {
			String sql = "DELETE * FROM khachhang; " + "INSERT INTO khachhang(maKH, Ho, Ten, gioiTinh, tongchitieu) "
					+ "VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
			pre.setInt(1, kh.getMaKH());
			pre.setString(2, kh.getHoKH());
			pre.setString(3, kh.getTenKH());
			pre.setString(4, kh.getGioitinh());
			pre.setInt(5, kh.getTongchitieu());
			return true;
		} catch (SQLException ex) {
		}
		return false;
	}

}

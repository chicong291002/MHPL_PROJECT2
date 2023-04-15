/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyMayTinh.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import QuanLyMayTinh.DTO.HibernateUtils;
import QuanLyMayTinh.DTO.KhachHang;

/**
 * @author User
 */
public class KhachHangDAO {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public ArrayList<KhachHang> getListKhachHang() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			session.getTransaction().begin();
			String sql = "Select e from " + KhachHang.class.getName() + " e";
			Query<KhachHang> query = session.createQuery(sql);
			List<KhachHang> danhsachkhachhang = query.getResultList();
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

	//It's working :>>
		public KhachHang getKhachHang(int maKH) {
			KhachHang kh = null;
			SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				session.getTransaction().begin();
				String sql = "Select e from " + KhachHang.class.getName() + " e where e.maKH = :maKH";
				@SuppressWarnings("unchecked")
				Query<KhachHang> query = session.createQuery(sql);
				query.setParameter("maKH", maKH);
				kh = query.getSingleResult();
				session.getTransaction().commit();
				return kh;
			} catch (Exception ex) {
				ex.printStackTrace();
				session.getTransaction().rollback();
				return null;
			}
		}

	//Works
		public boolean addKhachHang(KhachHang kh) {
			SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				session.getTransaction().begin();
				String sql = "INSERT INTO khachhang VALUES(:MaKH,:Ho,:Ten,:GioiTinh,:TongChiTieu,1)";
				Query<KhachHang> query = session.createNativeQuery(sql);
				query.setParameter("MaKH", kh.getMaKH());
				query.setParameter("Ho", kh.getHoKH());
				query.setParameter("Ten", kh.getTenKH());
				query.setParameter("GioiTinh", kh.getGioitinh());
				query.setParameter("TongChiTieu", kh.getTongchitieu());
				int count = query.executeUpdate();
				System.out.println(count);
				if (count > 0) {
					session.getTransaction().commit();
					System.out.println("abc");
					return true;
				} else {
					session.clear();
					session.close();

					return false;

				}
			} catch (Exception e) {
			}
			return false;
		}

	//Yay, it worked
		public boolean deleteKhachHang(int maKH) {
			SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				session.getTransaction().begin();
				String sql = "Delete from " + KhachHang.class.getName() + " d" + " where d.maKH like :MaKH";
				Query<KhachHang> query = session.createQuery(sql);
				query.setParameter("MaKH", maKH);
				int count = query.executeUpdate();
				System.out.println(count);
				if (count > 0) {
					session.getTransaction().commit();
					return true;
				} else {
					session.clear();
					session.close();
					return false;
				}
			} catch (Exception ex) {
				return false;
			}
		}

	//Works
		public boolean updateKhachHang(int maKH, KhachHang kh) {
			SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				session.getTransaction().begin();
				String sql = "UPDATE khachhang SET Ho=:Ho, Ten=:Ten, GioiTinh=:GioiTinh WHERE MaKH=:MaKH";
				Query<KhachHang> query = session.createNativeQuery(sql);
				query.setParameter("Ho", kh.getHoKH());
				query.setParameter("Ten", kh.getTenKH());
				query.setParameter("GioiTinh", kh.getGioitinh());
				query.setParameter("MaKH", maKH);
				int count = query.executeUpdate();
				System.out.println(count);
				if (count > 0) {
					session.getTransaction().commit();
					return true;
				} else {
					session.clear();
					session.close();
					return false;
				}
			} catch (Exception ex) {
				return false;
			}
		}

	//Thumbs up
		public boolean updateTongChiTieu(int maKH, int tongChiTieu) {
			boolean result = false;
			SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				session.getTransaction().begin();
//	      String sql = "UPDATE khachhang SET TongChiTieu=" + tongChiTieu + " WHERE MaKH=" + maKH;
				String sql = "UPDATE khachhang SET TongChiTieu= :TongChiTieu WHERE MaKH= :MaKH";
				Query<KhachHang> query = session.createNativeQuery(sql);
				query.setParameter("TongChiTieu", tongChiTieu);
				query.setParameter("MaKH", maKH);
				int count = query.executeUpdate();
				System.out.println(count);
				if (count > 0) {
					session.getTransaction().commit();
					return true;
				} else {
					session.clear();
					session.close();
					return false;
				}
			} catch (Exception ex) {
				return false;
			}
		}
    
    public boolean nhapExcel(KhachHang kh) {
        try {
            String sql = "DELETE * FROM khachhang; " +
                    "INSERT INTO khachhang(maKH, Ho, Ten, gioiTinh, tongchitieu) " +
                    "VALUES(?, ?, ?, ?, ?)";
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

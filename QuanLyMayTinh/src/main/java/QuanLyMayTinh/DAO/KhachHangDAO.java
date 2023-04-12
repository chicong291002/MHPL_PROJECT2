/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyMayTinh.DAO;

import QuanLyMayTinh.DTO.KhachHang;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import DTO.HibernateUtils;

/**
 * @author User
 */
public class KhachHangDAO {

//    public ArrayList<KhachHang> getListKhachHang() {
//        try {
//            String sql = "SELECT * FROM khachhang WHERE TinhTrang=1";
//            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
//            ResultSet rs = pre.executeQuery();
//            ArrayList<KhachHang> dskh = new ArrayList<>();
//            while (rs.next()) {
//                KhachHang kh = new KhachHang();
//                kh.setMaKH(rs.getInt(1));
//                kh.setHoKH(rs.getString(2));
//                kh.setTenKH(rs.getString(3));
//                kh.setGioitinh(rs.getString(4));
//                kh.setTongchitieu(rs.getInt(5));
//                dskh.add(kh);
//            }
//            return dskh;
//        } catch (SQLException ex) {
//        }
//        return null;
//    }
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
        try {
            String sql = "INSERT INTO khachhang VALUES(?,?,?,?,?,1)";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, kh.getMaKH());
            prep.setString(2, kh.getHoKH());
            prep.setString(3, kh.getTenKH());
            prep.setString(4, kh.getGioitinh());
            prep.setInt(5, kh.getTongchitieu());         
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteKhachHang(int maKH) {
        boolean result = false;
        try {
            String sql = "UPDATE khachhang SET TinhTrang=0 WHERE MaKH=?";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, maKH);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateKhachHang( KhachHang kh) {
    	boolean result = false;
        try {
            String sql = "UPDATE khachhang SET Ho=?, Ten=?, GioiTinh=?, TongChiTieu=? WHERE MaKH=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, kh.getHoKH());
            pre.setString(2, kh.getTenKH());
            pre.setString(3, kh.getGioitinh());
            pre.setInt(4, kh.getTongchitieu());
            pre.setInt(5, kh.getMaKH());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateTongChiTieu(int maKH, int tongChiTieu) {
        boolean result = false;
        try {
            String sql = "UPDATE khachhang SET TongChiTieu=" + tongChiTieu + " WHERE MaKH=" + maKH;
            Statement stmt = MyConnect.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
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

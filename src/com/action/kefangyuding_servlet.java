package com.action;

import com.dao.DB;
import com.orm.Kefangyuding;
import com.service.liuService;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class kefangyuding_servlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String type = req.getParameter("type");
		if (type.endsWith("kefangyudingMana")) {
			kefangyudingMana(req, res);
		}
		if (type.endsWith("kefangyudingAdd")) {
			kefangyudingAdd(req, res);
		}
		if (type.endsWith("kefangyudingDel")) {
			kefangyudingDel(req, res);
		}
	}

	public void kefangyudingAdd(HttpServletRequest req, HttpServletResponse res) {
		int kefang_id = Integer.parseInt(req.getParameter("kefang_id"));
		String shijian = req.getParameter("shijian");
		String kehuname = req.getParameter("kehuname");
		String kehutel = req.getParameter("kehutel");
		String tianshu = req.getParameter("tianshu");
		String del = "no";
		String sql = "insert into t_kefangyuding(kefang_id,shijian,kehuname,kehutel,tianshu,del) values(?,?,?,?,?,?)";
		Object[] params = { Integer.valueOf(kefang_id), shijian, kehuname, kehutel, tianshu, del };
		DB mydb = new DB();
		mydb.doPstm(sql, params);
		mydb.closed();

		req.setAttribute("message", "�����ɹ�");
		req.setAttribute("path", "kefangyuding?type=kefangyudingMana");

		String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	public void kefangyudingDel(HttpServletRequest req, HttpServletResponse res) {
		String sql = "update t_kefangyuding set del='yes' where id=" + Integer.parseInt(req.getParameter("id"));
		Object[] params = new Object[0];
		DB mydb = new DB();
		mydb.doPstm(sql, params);
		mydb.closed();

		req.setAttribute("message", "�����ɹ�");
		req.setAttribute("path", "kefangyuding?type=kefangyudingMana");

		String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	public void kefangyudingMana(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List kefangyudingList = new ArrayList();
		String sql = "select * from t_kefangyuding where del='no'";
		Object[] params = new Object[0];
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				Kefangyuding kefangyuding = new Kefangyuding();
				kefangyuding.setId(rs.getInt("id"));
				kefangyuding.setKefang_id(rs.getInt("kefang_id"));
				kefangyuding.setShijian(rs.getString("shijian"));
				kefangyuding.setKehuname(rs.getString("kehuname"));
				kefangyuding.setKehutel(rs.getString("kehutel"));
				kefangyuding.setTianshu(rs.getString("tianshu"));
				kefangyuding.setKefang_fanjianhao(liuService.getKefangFangjianhao(rs.getInt("kefang_id")));
				kefangyudingList.add(kefangyuding);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();

		req.setAttribute("kefangyudingList", kefangyudingList);
		req.getRequestDispatcher("admin/kefangyuding/kefangyudingMana.jsp").forward(req, res);
	}

	public void dispatch(String targetURI, HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try {
			dispatch.forward(request, response);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void destroy() {
	}
}

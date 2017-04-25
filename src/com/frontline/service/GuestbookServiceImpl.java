package com.frontline.service;

import java.util.ArrayList;

import com.frontline.dao.GuestbookDao;
import com.frontline.dao.GuestbookDaoImpl;
import com.frontline.model.Guestbook;

public class GuestbookServiceImpl implements GuestbookService {

	// 新增留言
	public void createMessage(Guestbook gb) {
		GuestbookDao gbDAO = new GuestbookDaoImpl();
		gbDAO.createMessage(gb);
	}

	// 刪除留言
	public void deleteMessage(Guestbook gb) {
		GuestbookDao gbDAO = new GuestbookDaoImpl();
		gbDAO.deleteMessage(gb);
	}

	// 修改留言
	public void updateMessage(Guestbook gb) {
		GuestbookDao gbDAO = new GuestbookDaoImpl();
		gbDAO.updateMessage(gb);
	}

	// 查詢留言
	public ArrayList<Guestbook> queryMessage(String condition,
			ArrayList<String> psValues) {
		GuestbookDao gbDAO = new GuestbookDaoImpl();
		ArrayList<Guestbook> result = gbDAO.queryMessage(condition, psValues);
		return result;
	}

	// 單筆查詢留言

	public Guestbook queryMessage(int id) {
		GuestbookDao gbDAO = new GuestbookDaoImpl();
		Guestbook gb = gbDAO.queryMessage(id);
		return gb;
	}

}

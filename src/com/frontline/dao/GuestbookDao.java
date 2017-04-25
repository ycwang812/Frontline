package com.frontline.dao;

import java.util.ArrayList;

import com.frontline.model.Guestbook;

public interface GuestbookDao {

	// 新增留言
	public void createMessage(Guestbook gb);

	// 刪除留言
	public void deleteMessage(Guestbook gb);

	// 修改留言
	public void updateMessage(Guestbook gb);

	// 查詢留言
	public ArrayList<Guestbook> queryMessage(String condition,
			ArrayList<String> psValues);

	// 單筆查詢
	public Guestbook queryMessage(int id);

}
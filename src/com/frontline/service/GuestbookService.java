package com.frontline.service;

import java.util.ArrayList;

import com.frontline.model.Guestbook;

public interface GuestbookService {

	public void createMessage(Guestbook gb);

	public void deleteMessage(Guestbook gb);

	public void updateMessage(Guestbook gb);

	public ArrayList<Guestbook> queryMessage(String condition,
			ArrayList<String> psValues);

	public Guestbook queryMessage(int id);
}

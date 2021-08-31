package net.scit.guestbook.dao;

import java.util.List;

import net.scit.guestbook.vo.Guestbook;

public interface GuestbookMapper {
	public int insert(Guestbook guestbook);
	public List<Guestbook> select();
	public int delete(Guestbook guestbook);
}

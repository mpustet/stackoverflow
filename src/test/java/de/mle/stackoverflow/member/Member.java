package de.mle.stackoverflow.member;

import javax.annotation.Resource;

public class Member {
	@Resource
	MemberDao memberDao;

	public String createNewId() {
		return memberDao.findNext().getId();
	}
}
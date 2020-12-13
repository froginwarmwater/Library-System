package service;

import dao.AdministratorDao;

public class AdminFunc {
	
	public static boolean login(String username,String pwd) {
		AdministratorDao dao = new AdministratorDao();
		String result = dao.queryAdministratorPassword(username);
		if(pwd.equals(result))
			return true;
		return false;
	}
}

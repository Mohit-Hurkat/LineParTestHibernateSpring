package com.test.bl;

import java.io.IOException;
import java.sql.SQLException;

import com.test.bean.Admin;

public class LoginCheck 
{
	private Admin checkAd=null;
	public boolean check(Admin ad) throws ClassNotFoundException, IOException, SQLException{
		AdminLogic adLogic=new AdminLogic();
		checkAd=adLogic.search(ad.getUsername());
		if(checkAd.getUsername().equals(ad.getUsername()) && checkAd.getPassword().equals(ad.getPassword()))
		{
			return true;
		}
		
		return  false;
	}
}

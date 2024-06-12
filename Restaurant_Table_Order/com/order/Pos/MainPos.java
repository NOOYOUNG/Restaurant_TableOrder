package com.order.Pos;

import javax.swing.*;
import java.awt.*;

import com.order.Model.LogIn;
import com.order.Pos.PosController;
import com.order.Table.OrderController;

public class MainPos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
		@Override
			public void run() {
				LogIn login=new LogIn();
				String id=login.adminLogin();
				
				if(id=="adminId") {
					PosController Pc=new PosController();
					Pc.tableList();
				}
				else {
					System.out.println("등록되지 않은 관리자 및 회원입니다.");
				}
			}
		});
	}
	
}
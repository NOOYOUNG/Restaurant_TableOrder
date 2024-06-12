package com.order.Table;

import javax.swing.*;
import java.awt.*;

import com.order.Model.Member;
import com.order.Model.LogIn;
import com.order.Table.OrderController;

public class MainOrder implements Runnable{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogIn login=new LogIn();
		login.guestLogin();
		SwingUtilities.invokeLater(new MainOrder());
    }

    @Override
    public void run() {
    	new OrderController();
    }

}

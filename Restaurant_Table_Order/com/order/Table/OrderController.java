package com.order.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.order.Model.Menu;
import com.order.Model.LogIn;
import com.order.Model.Member;
import com.order.Pos.MainPos;

public class OrderController {
	private JFrame frame;
	private JPanel panel;
	LogIn adminLogIn = new LogIn();
	private Member orderGuest;
	private static ArrayList<Menu> orderList;

	public OrderController() {
		menuList();
		this.setOrderGuest(LogIn.getOrderGuest());
		orderList = new ArrayList<>();
	}
	
	public Member getOrderGuest() {
		return orderGuest;
	}

	public void setOrderGuest(Member orderGuest) {
		this.orderGuest = orderGuest;
	}
	
	public static ArrayList<Menu> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Menu> orderList) {
		this.orderList = orderList;
	}

	public void MenuFrame() {
		frame = new JFrame();
		frame.setTitle("Customer Order");
		frame.setSize(600, 300);

		panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setLayout(new GridLayout(0, 2));

		frame.setVisible(true);
		frame.add(panel);
	}

	public void menuList() {
		MenuFrame();

		addMenu("메인메뉴1", 10000);
		addMenu("메인메뉴2", 15000);
		addMenu("메인메뉴3", 18000);
		addMenu("메인메뉴4", 20000);

		addMenu("사이드메뉴1", 8000);
		addMenu("사이드메뉴2", 5000);
		addMenu("사이드메뉴3", 6000);
		panel.add(new JLabel());

		addMenu("콜라", 2000);
		addMenu("사이다", 2000);

		JButton total_order = new JButton("총 주문내역");
		total_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTotalOrder();
			}
		});
		panel.add(total_order);

	}

	public void addMenu(String menuItem, int price) {
		JButton button = new JButton(menuItem);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addToOrderList(menuItem, 1, price);
			}
		});
		panel.add(button);
	}

	public void addToOrderList(String menuItem, int count, int price) {
		boolean itemFound = false;

		for (Menu item : orderList) {
			if (item.getMenuItem().equals(menuItem)) {
				item.setMenuItem(menuItem);
				item.setCount(item.getCount() + count);
				item.setSum(item.getSum() + price * count);
				itemFound = true;
				break;
			}
		}

		if (!itemFound) {
			orderList.add(new Menu(menuItem, count, price));
		}
	}

	public void showTotalOrder() {
		int totalSum=0;
		StringBuilder orderDetail=new StringBuilder("<"+getOrderGuest().getNum()+"번 테이블 총 주문내역>\n");
		for(Menu item : orderList) {
			if(item.getCount()!=0) {
				orderDetail.append(item.getMenuItem()).append(" ").append(item.getCount()).append("\n");
				totalSum+=item.getSum();
			}
		}
		orderDetail.append("총 주문금액: ").append(totalSum).append("원");
		System.out.println(orderDetail);
		
		JFrame orderDetailFrame=new JFrame("총 주문내역");
		JTextArea orderDetailArea=new JTextArea(orderDetail.toString());
		orderDetailArea.setEditable(false);
		
		JButton orderComplete = new JButton("주문하기");
		
		orderComplete.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 orderComplete.setText("주문이 완료되었습니다!");
				 orderComplete.setEnabled(false); // 버튼 비활성화
				 Timer timer=new Timer(2000, new ActionListener() {
					 @Override
					 public void actionPerformed(ActionEvent e) {
						 orderDetailFrame.dispose();
				 sendToAdmin();
					 }
				 });
				 timer.setRepeats(false); // 타이머 한번만 실행
				 timer.start();
	         }
		});
		 
	    orderDetailFrame.add(orderDetailArea, BorderLayout.CENTER);
	    orderDetailFrame.add(orderComplete, BorderLayout.SOUTH);

	    orderDetailFrame.setSize(300, 200);
	    orderDetailFrame.setVisible(true);
	    
	}

	public void sendToAdmin() {
		MainPos.main(new String[] {});

	}

}
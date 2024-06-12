package com.order.Pos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.order.Model.Member;
import com.order.Model.Menu;
import com.order.Model.LogIn;
import com.order.Table.OrderController;

public class PosController {
	private JFrame frame;
	private JPanel panel;
	private LogIn logins;
	private Member payGuest;
	private ArrayList<Menu> payList;

	public PosController() {
		logins = new LogIn();
		this.payGuest = LogIn.getOrderGuest();
		this.payList = OrderController.getOrderList();
	}

	public void AdminFrame() {
		frame = new JFrame();
		frame.setTitle("Admin Counter");
		frame.setSize(400, 800);

		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setLayout(new GridLayout(5, 2));

		frame.add(panel);
		frame.setVisible(true);
	}

	public void tableList() {
		AdminFrame();

		for (Member guest : logins.getGuests()) {
			addTable(guest);
		}
	}

	private void addTable(Member guest) {
		JButton table = new JButton(guest.getNum() + "번 테이블");
		table.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				payTotalOrder(guest);
			}
		});
		panel.add(table);
	}

	public void payTotalOrder(Member guest) {
		int totalSum = 0;
		StringBuilder orderDetail = new StringBuilder("<" + guest.getNum() + "번 테이블 총 결제내역>\n");
		if (payGuest.getId().equals(guest.getId())) {
			for (Menu item : payList) {
				if (item.getCount() != 0) {
					orderDetail.append(item.getMenuItem()).append(" ").append(item.getCount()).append("\n");
					totalSum += item.getSum();
				}
			}
		}

		orderDetail.append("총 주문금액: ").append(totalSum).append("원");
		System.out.println(orderDetail);

		JFrame orderDetailFrame = new JFrame("총 주문내역");
		JTextArea orderDetailArea = new JTextArea(orderDetail.toString());
		orderDetailArea.setEditable(false);

		JButton orderComplete = new JButton("결제하기");

		orderComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderComplete.setText("결제가 완료되었습니다!");
				orderComplete.setEnabled(false); // 버튼 비활성화
				Timer timer = new Timer(1000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						orderDetailFrame.dispose();
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

}
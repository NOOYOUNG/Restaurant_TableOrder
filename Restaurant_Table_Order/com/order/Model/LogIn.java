package com.order.Model;

import java.util.Scanner;

import com.order.Model.Member;

public class LogIn {
	Scanner s;
	private Member admins;
	private Member[] guests;
	private static Member orderGuest;
	private boolean adminLogin;

	public LogIn() {
		s = new Scanner(System.in);
		admins = new Member("admin", "admin"); // 관리자 계정 생성
		guests = new Member[10]; // 10개의 손님 계정 생성
		for (int i = 0; i < 10; i++) {
			guests[i] = new Member(i + 1, "guest" + (i + 1), "password" + (i + 1));
		}
	}
	
	public Member[] getGuests() {
		return guests;
	}

	public static Member getOrderGuest() {
		return orderGuest;
	}

	public static void setOrderGuest(Member orderGuest) {
		LogIn.orderGuest = orderGuest;
	}

	public String adminLogin() {
		boolean loginFound = true;

		while (loginFound) {
			System.out.println("<관리자 계정 로그인>");
			System.out.print("아이디를 입력하세요: ");
			String adminId = s.nextLine();
			
			if(adminId.equals(admins.getId())) {
				System.out.print("비밀번호를 입력하세요: ");
				String adminpwd = s.nextLine();
				
				if(adminpwd.equals(admins.getPassword())){
					System.out.println("로그인 성공. 관리자 화면으로 이동합니다.");
					loginFound=false;
					return "adminId";
				} else {
					System.out.println("비밀번호가 틀렸습니다.");
					return "fail";
				}
				
			} else {
				System.out.println("존재하지 않는 아이디입니다.");
				return "fail";
			}
		}
		return "none";
	}

	public void guestLogin() {
		boolean loginFound = true;

		while (loginFound) {
			System.out.println("<주문자 로그인>");
			System.out.print("아이디를 입력하세요: ");
			String guestId = s.nextLine();

			for (int i = 0; i < 10; i++) {
				if (guestId.equals(guests[i].getId())) {
					System.out.print("비밀번호를 입력하세요: ");
					String guestPwd = s.nextLine();

					if (guestPwd.equals(guests[i].getPassword())) {
						System.out.println("로그인 성공. 주문 화면으로 이동합니다.");
						setOrderGuest(guests[i]);
						loginFound = false;
						break;
					} else {
						System.out.println("비밀번호가 틀렸습니다.");
					}
				} 
			}
		}
	}

}
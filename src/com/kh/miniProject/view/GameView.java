package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kh.miniProject.controller.CookingTime;
import com.kh.miniProject.controller.CustomerManager;
import com.kh.miniProject.model.dao.OrderDao;
import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.run.Run;

public class GameView extends JPanel{
	//패널 동시 사용을 위해 전역 선언
	public MainFrame mf;
	public GuestPanel gP;
	public MenuPanel mP;
	public EquipmentPanel eP;
	public CustomerManager cm;
	//타이머 클래스 (테스트) & Back 버튼
	private CookingTime cookTimer;
	private JPanel gView;
	private TimerTest gameTimer;
	private JButton backButton;
	private Image backButtonImage;
	//음식 수 저장용 변수
	private int drinksNo;	//음료개수
	private int friedNo;	//튀김개수
	private int tbkNo;		//떡볶이개수
	private int odengNo;	//오뎅개수
	private int ramenNo;	//라면개수
	//Member 정보 입출력위해
	private Member m;
	//stage 변수 관리
	private int stageLv;
	private int stageGold;
	private JButton gold;
	//주문 내역 관리
	private OrderDao orderDao;
	
	//메뉴별 개수 판정
	private int[] tableLv;

	//cons
	public GameView(MainFrame mf,Member m,int stageLv) {
		orderDao = new OrderDao(this);			//스테이지 당 orderDao 생성 단한번만!!
		this.gView = this;
		this.mf = mf;
		this.m = m;
		this.stageLv = stageLv;
		this.tableLv = m.getTableLv();
		this.setLayout(null);
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);

		//고객 패널 추가
		gP = new GuestPanel(new ImageIcon("images/스크린샷-2017-09-24-오전-6.00.47.png")
				.getImage().getScaledInstance(1024, 318, 0),orderDao);
		gP.setLayout(null);
		gP.setSize(Run.SCREEN_WIDTH,318);
		this.add(gP);

		//고객매니저 실행
		if(stageLv<3) {
			cm = new CustomerManager(gP,orderDao,2,stageLv);
		}else {
			cm = new CustomerManager(gP,orderDao,3,stageLv);
		}
		//스테이지 Timer
		gameTimer = new TimerTest(gP,cm,this);
		gP.add(gameTimer);

		//backButton
		backButton = new JButton();
		backButtonImage = new ImageIcon("images/backButton.png").getImage().getScaledInstance(50,50,0);
		backButton.setIcon(new ImageIcon(backButtonImage));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] command = {"확인","취소"};
				int result;

				result = JOptionPane.showOptionDialog(null,
						"정말 그만두시겠습니까 ? ",
						"부글부글분식",JOptionPane.YES_NO_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, null, command, command[0]);

				if(result==0) {
					gameTimer.timerStop();
					ChangePanel.changePanel(mf, gView, new StageView(mf,m));
				}
			}		
		});
		backButton.setBounds(950,0,50,50);
		backButton.setContentAreaFilled(false);
		gP.add(backButton);


		//골드 출력
		gold = new JButton("골드");
		gold.setEnabled(false);
		gold.setBackground(Color.yellow);
		gold.setBounds(0,0,200,30);
		gold.setText(0 + "원");
		gP.add(gold);

		//메뉴 패널 추가
		mP = new MenuPanel(m);
		mP.setting(mP,drinksNo,tbkNo,friedNo);
		this.add(mP);

		//장비 패널 추가
		eP = new EquipmentPanel();
		eP.equipsSetting(eP,m);
		this.add(eP);


		//ActionListener setting
		JButton[] equips = eP.getEquips();
		for (int i = 0; i < equips.length; i++) {
			equips[i].addActionListener(new Event_Cook());
		}		
		JButton[] menuButton = mP.getMenuButton();
		for(int i=0; i<menuButton.length;i++) {
			menuButton[i].addActionListener(new Event_Cook());
		}
	}
	public void updateGold(int stageGold) {
		this.stageGold = stageGold;
		gold.setText(stageGold + "원");
	}
	public void gameOver() {
		//하트 3개 소진시 gameover
	}
	public void endStage() {
		String[] command = {"결과보기","스테이지로 이동"};
		int result;
		
		Dialog dialog = new Dialog(mf);
		dialog.setBounds(150, 150, 200, 200);
		m.setStageGold(stageGold);
		m.setGold(m.getGold()+stageGold);
		m.setMaxStage(stageLv+1);
		
		result = JOptionPane.showOptionDialog(null,
				"STAGE "+stageLv+" CLEAR!!\n Earned Gold : "+stageGold,
				"부글부글분식",JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, command, command[0]);

		if(result==0) {
			ChangePanel.changePanel(mf, gView, new ResultPanel(mf,m));
		}else {
			ChangePanel.changePanel(mf, gView, new StageView(mf,m));
		}
	}
	//btn Action
	class Event_Cook implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Equips Panel ActionListener
			JButton[] equips = eP.getEquips();
			if(e.getActionCommand().equals("떡볶이기계")) {
				System.out.println("떡볶이기계");
				if(tbkNo<4) {
					
					if(tableLv[0]==1 && tbkNo<1) {
						judgeLv("떡볶이",equips);					
					}else if(tableLv[0]==2 && tbkNo<2) {
						judgeLv("떡볶이",equips);			
					}else if(tableLv[0]==3 && tbkNo<3) {
						judgeLv("떡볶이",equips);	
					}else if(tableLv[0]==4 && tbkNo<4) {
						judgeLv("떡볶이",equips);	
					}
				}else {
					System.out.println("떡볶이가 최대 충전입니다.");
				}
				refreshMenuTable();
				System.out.println("떡볶이 충전 잔여 개수"+tbkNo);
			}		
			if(e.getActionCommand().equals("순대기계")) {
				System.out.println("순대기계");
			}
			if(e.getActionCommand().equals("자판기")) {
				System.out.println("자판기");
				if(drinksNo<3) {
					if(tableLv[1]==1 && drinksNo<1) {
						judgeLv("음료수",equips);					
					}else if(tableLv[1]==2 && drinksNo<2) {
						judgeLv("음료수",equips);			
					}else if(tableLv[1]==3 && drinksNo<3) {
						judgeLv("음료수",equips);	
					}else if(tableLv[1]==4 && drinksNo<4) {
						judgeLv("음료수",equips);	
					}
				}else {
					System.out.println("음료수가 최대 충전입니다.");
				}
				refreshMenuTable();
				System.out.println("음료수 충전 잔여 개수"+drinksNo);
			}
			if(e.getActionCommand().equals("오뎅기계")) {
				System.out.println("오뎅기계");
			}
			if(e.getActionCommand().equals("튀김기")) {
				System.out.println("튀김기");
				if(friedNo<4) {
					if(tableLv[2]==1 && friedNo<1) {
						judgeLv("튀김",equips);					
					}else if(tableLv[2]==2 && friedNo<2) {
						judgeLv("튀김",equips);			
					}else if(tableLv[2]==3 && friedNo<3) {
						judgeLv("튀김",equips);	
					}else if(tableLv[2]==4 && friedNo<4) {
						judgeLv("튀김",equips);	
					}
				}else {
					System.out.println("튀김이 최대 충전입니다.");
				}
				refreshMenuTable();
				System.out.println("튀김 충전 잔여 개수"+friedNo);
			}

			//MenuPanel ActionListener
			int temp;
			if(e.getActionCommand().equals("떡볶이")) {
				if(tbkNo>0) {
					if((temp=orderDao.searchOrder(new MenuOrder("떡볶이")))>=0) {
						cm.deleteLabel(temp);
						tbkNo--;
						System.out.println("떡볶이 잔여 개수 : " + tbkNo);
						mP.setting(mP,drinksNo,tbkNo,friedNo);
					}else {
						System.out.println("주문된 떡볶이가 없습니다.");
					}
				}else {
					System.out.println("떡볶이가 없습니다.");
				}
			}
			if(e.getActionCommand().equals("튀김")) {
				if(friedNo>0) {
					if((temp=orderDao.searchOrder(new MenuOrder("튀김")))>=0) {
						cm.deleteLabel(temp);
						friedNo--;
						System.out.println("튀김 잔여 개수 : " + friedNo);
						mP.setting(mP,drinksNo,tbkNo,friedNo);
					}else {
						System.out.println("주문된 튀김이 없습니다.");
					}
				}else {
					System.out.println("튀김이 없습니다.");
				}
			}
			if(e.getActionCommand().equals("음료수")) {
				if(drinksNo>0) {
					if((temp=orderDao.searchOrder(new MenuOrder("음료수")))>=0) {
						System.out.println("temp:"+temp);
						cm.deleteLabel(temp);
						drinksNo--;
						System.out.println("음료수 잔여 개수 : " + drinksNo);
						mP.setting(mP,drinksNo,tbkNo,friedNo);
					}else {
						System.out.println("주문된 음료수가 없습니다.");
					}
				}else {
					System.out.println("음료수가 없습니다.");
				}
			}
		}
	}
	public void refreshMenuTable() {
		//자판기, 떡볶이, 튀김
		mP.setting(mP,drinksNo,tbkNo,friedNo);
	}
	
	public void judgeLv(String menuName,JButton[] equips) {
		if(menuName.equals("떡볶이")) {
			equips[1].setEnabled(false);
			cookTimer = new CookingTime(equips[1],m,6,"떡볶이");
			gView.add(cookTimer);
			tbkNo++;		
		}else if(menuName.equals("음료수")) {
			equips[0].setEnabled(false);
			cookTimer = new CookingTime(equips[0],m,5,"음료수");
			gView.add(cookTimer);
			drinksNo++;
		}else if(menuName.equals("튀김")) {
			equips[2].setEnabled(false);
			cookTimer = new CookingTime(equips[2],m,10,"튀김");
			gView.add(cookTimer);
			friedNo++;
		}
	}
}
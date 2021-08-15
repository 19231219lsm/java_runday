package cn.sqc.runday.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.sqc.runday.controller.GamePanel;
import cn.sqc.runday.model.Person;


public class EndFrame extends JFrame implements MouseListener {
	//����������Ϸ��ť���������˵���ť���˳���ť ���
		JLabel again,back,exit;
		
	public EndFrame(Person person) { 
		again = new JLabel(new ImageIcon("image\\hh5.png"));
		again.setBounds(520, 622, 60, 25);
		again.addMouseListener(this);
		this.add(again);	
		back = new JLabel(new ImageIcon("image/hh6.png"));
		back.setBounds(520, 722, 60, 25);
		back.addMouseListener(this);
		this.add(back);
		exit = new JLabel(new ImageIcon("image/hh3.png"));
		exit.setBounds(520, 822, 60, 25);
		exit.addMouseListener(this);
		this.add(exit);
		
		EndPanel end = new EndPanel(person);
		this.add(end);//���������������ӵ�����������
		
		this.setSize(1500, 900);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("image/115.png").getImage());
		this.setVisible(true);
	}
	
	public static void main(String[] args) { 
		//new EndFrame();
	}
	class EndPanel extends JPanel{
		Image background;
		Person p;
		public EndPanel(Person person) {//���int a
			this.p = person;//�������󡢴�ֵ
			try {
				background = ImageIO.read(new File("image/chou.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawImage(background, 0, 0,1500,900 ,null);
			g.setColor(Color.CYAN);
			g.setFont(new Font("����",Font.BOLD,30));
			g.drawString(p.getScore()+"",1110,705);// + �� �� ��ʵ��
			g.drawString(p.getDistance() + " ", 1110, 622);
			
			g.setFont(new Font("����",Font.BOLD,50));
			g.setColor(Color.ORANGE);
			g.drawString(p.getTotalScore() + "", 1075, 500);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(again)){
			//��ת����һ����	
			 new WindowFrame().Start();
			//�رյ�ǰ����
			 dispose();
		}	else if(e.getSource().equals(back)){
			new MainFrame();
			dispose();
		}else if(e.getSource().equals(exit)){
			System.exit(0);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
package cn.sqc.runday.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginFrame extends JFrame{
	//�û����������ı���
	JLabel userLabel;
	//�û���������ı������
	JTextField userField;
	//����������ı���
	JLabel userLabel2;
	//����������ı������
	JPasswordField userField2;
	//��¼��ť��ȡ����ť����ť��
	JButton Login,Cancel,register;

	public LoginFrame() throws IOException {//ֱ�� alt / ���޲ι��죩
		userLabel = new JLabel("�û���");	
		//��������
		userLabel.setFont(new Font("΢���ź�",Font.BOLD,18));				
		userLabel2 = new JLabel("��  ��");
		userLabel2.setFont(new Font("΢���ź�",Font.BOLD,18));
			
		//���ַ�ʽ�����Բ���
		userLabel.setBounds(20, 220, 100, 30);//xλ�ã�yλ�ã���ռ��ʾ�ռ�Ĵ�С
		this.add(userLabel);//���û�������������ӵ���¼�����ϣ�����ͬ��
		userLabel2.setBounds(20, 280, 100, 30);
		this.add(userLabel2);

		//�û��������
		userField = new JTextField();
		userField.setBounds(80, 220, 100, 30);
		//�����������Ч��
		userField.setBorder(BorderFactory.createLoweredBevelBorder());
		//��������򱳾�͸��
		userField.setOpaque(false);
		this.add(userField);
		
		userField2 = new JPasswordField();
		userField2.setBounds(80, 280, 100, 30);
		userField2.setBorder(BorderFactory.createLoweredBevelBorder());
		userField2.setOpaque(false);
		this.add(userField2);
		



		Login = new JButton("��¼");
		Login.setBounds(45,350,60,36);
		//Login.setBackground(new Color(44,22,44));//����ɫ
		Login.setForeground(Color.BLUE);//ǰ��ɫ

		Login.addActionListener(new ActionListener() {//ActionListener alt /
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("�����¼��ť");
				//��ȡ�û�������������
				String userName = userField.getText();
				String passWord = userField2.getText();//���ԭ�򣺷���̫���ˣ����Ƽ���
				File file=new File("user.txt");
				if(!file.exists()||file.isDirectory()) //�ж��ļ��Ƿ����
					//throw new FileNotFoundException();
				{
					try {
						file.createNewFile();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
				}
				BufferedReader br= null; //�������뻺���������ж���
				try {
					br = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException fileNotFoundException) {
					fileNotFoundException.printStackTrace();
				}
				String temp=null;
				try {
					assert br != null;
					temp=br.readLine();   //�ȶ�ȡһ��
					//System.out.println(temp);
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				boolean is=false;
				while(temp!=null){
					String usernametemp = temp;   //ת��Ϊstring
					//System.out.println(temp);
					if(usernametemp.equals("�˺�:"+userName)){
						try {
							temp=br.readLine();
						} catch (IOException ioException) {
							ioException.printStackTrace();
						}
						String temppwd=temp;
						if(!temppwd.equals("����:"+passWord)){
							JOptionPane.showMessageDialog(null, "��������������������룡");
						}
						else {
							JOptionPane.showMessageDialog(null, "��ӭ"+userName+"�������������Ϸ");
							is=true;
							new MainFrame();
							dispose();
						}
					}
					else if("".equals(userName) || "".equals(passWord)){
//					//����Ϊ��
					JOptionPane.showMessageDialog(null, "�û��� / ���벻��Ϊ�գ����������룡");}
					else {
						for (int i=0;i<5;i++) {
							try {
								temp = br.readLine();
							} catch (IOException ioException) {
								ioException.printStackTrace();
							}
						}
					}
				}
				if(!is){
					JOptionPane.showMessageDialog(null, "�û��� / ���������������������");
				}



//				if("".equals(userName) && "123".equals(passWord)){
//					//��¼�ɹ�
//					JOptionPane.showMessageDialog(null, "��ӭ"+userName+"�������������Ϸ");
//					//��ת����һ����
//					new MainFrame();
//					//�رյ�ǰ����
//					dispose();
//				}else if("".equals(userName) || "".equals(passWord)){
//					//����Ϊ��
//					JOptionPane.showMessageDialog(null, "�û��� / ���벻��Ϊ�գ����������룡");
//				}else{
//					JOptionPane.showMessageDialog(null, "�û��� / ��������������������룡");
//				}
				
			}
		});
		this.add(Login);
		
//ȡ����ť
		Cancel = new JButton("ȡ��"); 
		Cancel.setBounds(135,350,60,36);
		this.add(Cancel);
		Cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		register=new JButton("ע��");
		register.setBounds(225,350,60,36);
		register.addActionListener(new ActionListener() {//ActionListener alt /

			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterFrame();
				dispose();
			}
		});
		this.add(register);
		//����������壬����ӵ�������ȥ
		LoginPanel panel = new LoginPanel();
		this.add(panel);	
		
		//���õ�¼����Ļ�������
		this.setSize(900,530);
		this.setLocationRelativeTo(null);//λ�þ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		
		//���ô����Logoͼ��
		this.setIconImage(new ImageIcon("image\\115.png").getImage());//�洢ͼƬ
		this.setVisible(true);
	}
	
	
	
	//�����õ�main����       main + Alt /
	public static void main(String[] args) throws IOException {
		new LoginFrame();
	}
	
	class LoginPanel extends JPanel{
		//����ͼƬ����
		Image background;
		public LoginPanel() {
			try {
				background = ImageIO.read(new File("image\\login.jpg"));//----read����ΪFile����
			} catch (IOException e) {//-------�����쳣��Ϣ
				// ��ӡ�쳣��־��Ϣ
				e.printStackTrace();
			}
		}
		//���Ʒ���
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			//���Ʊ���ͼƬ
			g.drawImage(background, 0, 0,900,530, null);//900,530Ϊ���
		}
	}
}
//throws ......���쳣����������쳣������,�����ϼ���������   
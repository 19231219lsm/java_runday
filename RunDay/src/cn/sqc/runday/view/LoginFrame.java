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
	//用户名变量（文本）
	JLabel userLabel;
	//用户名输入框（文本输入框）
	JTextField userField;
	//密码变量（文本）
	JLabel userLabel2;
	//密码输入框（文本输入框）
	JPasswordField userField2;
	//登录按钮、取消按钮（按钮）
	JButton Login,Cancel,register;

	public LoginFrame() throws IOException {//直接 alt / （无参构造）
		userLabel = new JLabel("用户名");	
		//设置字体
		userLabel.setFont(new Font("微软雅黑",Font.BOLD,18));				
		userLabel2 = new JLabel("密  码");
		userLabel2.setFont(new Font("微软雅黑",Font.BOLD,18));
			
		//布局方式：绝对布局
		userLabel.setBounds(20, 220, 100, 30);//x位置，y位置，所占显示空间的大小
		this.add(userLabel);//将用户名这三个字添加到登录界面上，以下同理
		userLabel2.setBounds(20, 280, 100, 30);
		this.add(userLabel2);

		//用户名输入框
		userField = new JTextField();
		userField.setBounds(80, 220, 100, 30);
		//设置输入框凹陷效果
		userField.setBorder(BorderFactory.createLoweredBevelBorder());
		//设置输入框背景透明
		userField.setOpaque(false);
		this.add(userField);
		
		userField2 = new JPasswordField();
		userField2.setBounds(80, 280, 100, 30);
		userField2.setBorder(BorderFactory.createLoweredBevelBorder());
		userField2.setOpaque(false);
		this.add(userField2);
		



		Login = new JButton("登录");
		Login.setBounds(45,350,60,36);
		//Login.setBackground(new Color(44,22,44));//背景色
		Login.setForeground(Color.BLUE);//前景色

		Login.addActionListener(new ActionListener() {//ActionListener alt /
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("点击登录按钮");
				//获取用户名输入框的内容
				String userName = userField.getText();
				String passWord = userField2.getText();//横杠原因：方法太老了，不推荐用
				File file=new File("user.txt");
				if(!file.exists()||file.isDirectory()) //判断文件是否存在
					//throw new FileNotFoundException();
				{
					try {
						file.createNewFile();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
				}
				BufferedReader br= null; //创建读入缓冲流，按行读入
				try {
					br = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException fileNotFoundException) {
					fileNotFoundException.printStackTrace();
				}
				String temp=null;
				try {
					assert br != null;
					temp=br.readLine();   //先读取一行
					//System.out.println(temp);
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				boolean is=false;
				while(temp!=null){
					String usernametemp = temp;   //转化为string
					//System.out.println(temp);
					if(usernametemp.equals("账号:"+userName)){
						try {
							temp=br.readLine();
						} catch (IOException ioException) {
							ioException.printStackTrace();
						}
						String temppwd=temp;
						if(!temppwd.equals("密码:"+passWord)){
							JOptionPane.showMessageDialog(null, "密码输入错误，请重新输入！");
						}
						else {
							JOptionPane.showMessageDialog(null, "欢迎"+userName+"来到天天酷跑游戏");
							is=true;
							new MainFrame();
							dispose();
						}
					}
					else if("".equals(userName) || "".equals(passWord)){
//					//不能为空
					JOptionPane.showMessageDialog(null, "用户名 / 密码不能为空，请重新输入！");}
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
					JOptionPane.showMessageDialog(null, "用户名 / 密码输入错误，请重新输入");
				}



//				if("".equals(userName) && "123".equals(passWord)){
//					//登录成功
//					JOptionPane.showMessageDialog(null, "欢迎"+userName+"来到天天酷跑游戏");
//					//跳转到下一界面
//					new MainFrame();
//					//关闭当前界面
//					dispose();
//				}else if("".equals(userName) || "".equals(passWord)){
//					//不能为空
//					JOptionPane.showMessageDialog(null, "用户名 / 密码不能为空，请重新输入！");
//				}else{
//					JOptionPane.showMessageDialog(null, "用户名 / 密码输入错误，请重新输入！");
//				}
				
			}
		});
		this.add(Login);
		
//取消按钮
		Cancel = new JButton("取消"); 
		Cancel.setBounds(135,350,60,36);
		this.add(Cancel);
		Cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		register=new JButton("注册");
		register.setBounds(225,350,60,36);
		register.addActionListener(new ActionListener() {//ActionListener alt /

			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterFrame();
				dispose();
			}
		});
		this.add(register);
		//创建背景面板，并添加到窗体上去
		LoginPanel panel = new LoginPanel();
		this.add(panel);	
		
		//设置登录界面的基本属性
		this.setSize(900,530);
		this.setLocationRelativeTo(null);//位置居中
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		
		//设置窗体的Logo图标
		this.setIconImage(new ImageIcon("image\\115.png").getImage());//存储图片
		this.setVisible(true);
	}
	
	
	
	//测试用的main方法       main + Alt /
	public static void main(String[] args) throws IOException {
		new LoginFrame();
	}
	
	class LoginPanel extends JPanel{
		//背景图片变量
		Image background;
		public LoginPanel() {
			try {
				background = ImageIO.read(new File("image\\login.jpg"));//----read参数为File类型
			} catch (IOException e) {//-------捕获异常信息
				// 打印异常日志信息
				e.printStackTrace();
			}
		}
		//绘制方法
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			//绘制背景图片
			g.drawImage(background, 0, 0,900,530, null);//900,530为宽高
		}
	}
}
//throws ......抛异常，将下面的异常向上抛,交给上级：不建议   
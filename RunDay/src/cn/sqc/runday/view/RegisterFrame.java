package cn.sqc.runday.view;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

//该窗口继承自JFrame, 实现了ActionListener接口
public class RegisterFrame extends JFrame implements ActionListener {
    //定义需要的组件
    JTextField jtfName, jtfEmail;
    JPasswordField jpf;
    JRadioButton jrb1, jrb2;
    JComboBox<String> jcb;
    JButton jbReset, jbSingUp;
    //常量
    static final String NEW_LINE = System.getProperty("line.separator");// 获取系统的换行符
    static final String FILE_PATH = "user.txt";//指定文件的路径
    //构造器
    public RegisterFrame() {
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("账号");
        jtfName = new JTextField(15);
        jp1.add(jl1);
        jp1.add(jtfName);

        JPanel jp2 = new JPanel();
        JLabel jl2 = new JLabel("密码");
        jpf = new JPasswordField(15);
        jp2.add(jl2);
        jp2.add(jpf);

        JPanel jp3 = new JPanel();
        JLabel jl3 = new JLabel("邮箱");
        jtfEmail = new JTextField(15);
        jp3.add(jl3);
        jp3.add(jtfEmail);

        JPanel jp4 = new JPanel();
        JLabel jl4 = new JLabel("性别");
        ButtonGroup bg = new ButtonGroup();
        jrb1 = new JRadioButton("男");
        jrb1.setSelected(true);// 默认选中男性
        jrb2 = new JRadioButton("女");
        bg.add(jrb1);
        bg.add(jrb2);
        jp4.add(jl4);
        jp4.add(jrb1);
        jp4.add(jrb2);

        JLabel jl5 = new JLabel("年龄");
        String[] ary = new String[12];
        for (int i = 18; i < 30; i++) {// 18~30可供选中的范围
            ary[i - 18] = i + "";
        }
        jcb = new JComboBox<String>(ary);
        jp4.add(jl5);
        jp4.add(jcb);

        JPanel jpc = new JPanel(new GridLayout(4, 1));// 4行1列布局
        jpc.add(jp1);
        jpc.add(jp2);
        jpc.add(jp3);
        jpc.add(jp4);
        add(jpc);

        JPanel jps = new JPanel();
        jbReset = new JButton("重填");
        jbReset.addActionListener(this);//给按钮添加响应
        jbSingUp = new JButton("确定");
        jbSingUp.addActionListener(this);//给按钮添加响应
        jps.add(jbReset);
        jps.add(jbSingUp);
        add(jps, BorderLayout.SOUTH);


        setTitle("注册窗口");// 窗口标题
        setSize(300, 285);// 窗口大小
        setLocationRelativeTo(null);// 窗口居中
        this.setUndecorated(true);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 当窗口关闭时,程序结束
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jb = (JButton) e.getSource();
        if (jb == jbReset) {
            jtfName.setText("");
            jpf.setText("");
            jtfEmail.setText("");
            jrb1.setSelected(true);
            jcb.setSelectedIndex(0);// 选中第1个选项(18)
        } else if (jb == jbSingUp) {

            String name = jtfName.getText().trim();
            String pswd = new String(jpf.getPassword());
            String email = jtfEmail.getText().trim();
            String xb = jrb1.isSelected() ? "男" : "女";
            String age = (String) jcb.getSelectedItem();
            if(name.equals("")||pswd.equals("")||email.equals("")) {//对输入的信息进行一个简单的判断
                JOptionPane.showMessageDialog(null, "请完整填写所有的信息", "提示",JOptionPane.WARNING_MESSAGE);
                return;
            }

            StringBuffer sb = new StringBuffer();
            sb.append("账号:" + name + NEW_LINE + "密码:" + pswd + NEW_LINE + "邮箱:" + email + NEW_LINE + "性别:" + xb
                    + NEW_LINE + "年龄:" + age+NEW_LINE);
            boolean flag = saveInfo(sb.toString());
            if(flag) {
                JOptionPane.showMessageDialog(null, "注册成功 ,保存成功");
                try {
                    new LoginFrame();
                    dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Sorry!保存失败.注册出现了问题...", "IO错误",JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    //方法:保存信息到文件
    public boolean saveInfo(String info) {
        FileWriter fw;
        try {
            fw = new FileWriter(FILE_PATH, true);//追加文字到文件尾部
            fw.write(info);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;//IO异常,保存不成功
        }
        return true;//保存成功
    }

    //main方法
    public static void main(String[] args) {
        new RegisterFrame();
    }

   // @Override
//    public void mouseClicked(MouseEvent e) {
//        if(e.getSource().equals(start)){//e指一个事件。e.getSource()获取事件
//            //如果鼠标移入到（start）组件（图片按钮）
//            start.setEnabled(true);
//        }else if(e.getSource().equals(help)){
//            help.setEnabled(true);
//        }else if(e.getSource().equals(exit)){
//            exit.setEnabled(true);
//        }
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
////
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
////
//    }
}
package cn.sqc.runday.view;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

//�ô��ڼ̳���JFrame, ʵ����ActionListener�ӿ�
public class RegisterFrame extends JFrame implements ActionListener {
    //������Ҫ�����
    JTextField jtfName, jtfEmail;
    JPasswordField jpf;
    JRadioButton jrb1, jrb2;
    JComboBox<String> jcb;
    JButton jbReset, jbSingUp;
    //����
    static final String NEW_LINE = System.getProperty("line.separator");// ��ȡϵͳ�Ļ��з�
    static final String FILE_PATH = "user.txt";//ָ���ļ���·��
    //������
    public RegisterFrame() {
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("�˺�");
        jtfName = new JTextField(15);
        jp1.add(jl1);
        jp1.add(jtfName);

        JPanel jp2 = new JPanel();
        JLabel jl2 = new JLabel("����");
        jpf = new JPasswordField(15);
        jp2.add(jl2);
        jp2.add(jpf);

        JPanel jp3 = new JPanel();
        JLabel jl3 = new JLabel("����");
        jtfEmail = new JTextField(15);
        jp3.add(jl3);
        jp3.add(jtfEmail);

        JPanel jp4 = new JPanel();
        JLabel jl4 = new JLabel("�Ա�");
        ButtonGroup bg = new ButtonGroup();
        jrb1 = new JRadioButton("��");
        jrb1.setSelected(true);// Ĭ��ѡ������
        jrb2 = new JRadioButton("Ů");
        bg.add(jrb1);
        bg.add(jrb2);
        jp4.add(jl4);
        jp4.add(jrb1);
        jp4.add(jrb2);

        JLabel jl5 = new JLabel("����");
        String[] ary = new String[12];
        for (int i = 18; i < 30; i++) {// 18~30�ɹ�ѡ�еķ�Χ
            ary[i - 18] = i + "";
        }
        jcb = new JComboBox<String>(ary);
        jp4.add(jl5);
        jp4.add(jcb);

        JPanel jpc = new JPanel(new GridLayout(4, 1));// 4��1�в���
        jpc.add(jp1);
        jpc.add(jp2);
        jpc.add(jp3);
        jpc.add(jp4);
        add(jpc);

        JPanel jps = new JPanel();
        jbReset = new JButton("����");
        jbReset.addActionListener(this);//����ť�����Ӧ
        jbSingUp = new JButton("ȷ��");
        jbSingUp.addActionListener(this);//����ť�����Ӧ
        jps.add(jbReset);
        jps.add(jbSingUp);
        add(jps, BorderLayout.SOUTH);


        setTitle("ע�ᴰ��");// ���ڱ���
        setSize(300, 285);// ���ڴ�С
        setLocationRelativeTo(null);// ���ھ���
        this.setUndecorated(true);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �����ڹر�ʱ,�������
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jb = (JButton) e.getSource();
        if (jb == jbReset) {
            jtfName.setText("");
            jpf.setText("");
            jtfEmail.setText("");
            jrb1.setSelected(true);
            jcb.setSelectedIndex(0);// ѡ�е�1��ѡ��(18)
        } else if (jb == jbSingUp) {

            String name = jtfName.getText().trim();
            String pswd = new String(jpf.getPassword());
            String email = jtfEmail.getText().trim();
            String xb = jrb1.isSelected() ? "��" : "Ů";
            String age = (String) jcb.getSelectedItem();
            if(name.equals("")||pswd.equals("")||email.equals("")) {//���������Ϣ����һ���򵥵��ж�
                JOptionPane.showMessageDialog(null, "��������д���е���Ϣ", "��ʾ",JOptionPane.WARNING_MESSAGE);
                return;
            }

            StringBuffer sb = new StringBuffer();
            sb.append("�˺�:" + name + NEW_LINE + "����:" + pswd + NEW_LINE + "����:" + email + NEW_LINE + "�Ա�:" + xb
                    + NEW_LINE + "����:" + age+NEW_LINE);
            boolean flag = saveInfo(sb.toString());
            if(flag) {
                JOptionPane.showMessageDialog(null, "ע��ɹ� ,����ɹ�");
                try {
                    new LoginFrame();
                    dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Sorry!����ʧ��.ע�����������...", "IO����",JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    //����:������Ϣ���ļ�
    public boolean saveInfo(String info) {
        FileWriter fw;
        try {
            fw = new FileWriter(FILE_PATH, true);//׷�����ֵ��ļ�β��
            fw.write(info);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;//IO�쳣,���治�ɹ�
        }
        return true;//����ɹ�
    }

    //main����
    public static void main(String[] args) {
        new RegisterFrame();
    }

   // @Override
//    public void mouseClicked(MouseEvent e) {
//        if(e.getSource().equals(start)){//eָһ���¼���e.getSource()��ȡ�¼�
//            //���������뵽��start�������ͼƬ��ť��
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
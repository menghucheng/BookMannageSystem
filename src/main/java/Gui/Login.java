package Gui;

import Utils.ConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * Created by mhc on 15-5-30.
 */
public class Login extends JFrame implements ActionListener{
    private int retry = 3;//重试只能是3次
    Container cp1,cp2,cp3,cp0;
    JFrame f = null;
    JButton jb1,jb2;
    JTextField jt1;
    JPasswordField jpf;
    JLabel jl1,jl2,jl0;
    Color color;
    public Login(){
        cp0 = new JPanel();
        cp1 = new JPanel();
        cp2 = new JPanel();
        cp3 = new JPanel();
        //设置布局
        this.setLayout(new GridLayout(4,1));
        jb1 = new JButton("登录");
        jb2 = new JButton("退出");
        jt1 = new JTextField(13);
        jpf = new JPasswordField(13);
        jl0 = new JLabel("欢迎使用该系统！");
        jl1 = new JLabel("用户名：");
        jl2 = new JLabel("密  码：");
        jl0.setFont(new Font("Dialog",1,20));
        jl0.setForeground(Color.red);
        cp0.add(jl0);
        //第一块添加用户名和文本框
        cp1.add(jl1);
        cp1.add(jt1);
        //第二块添加密码和密码输入框
        cp2.add(jl2);
        cp2.add(jpf);
        //第三块添加登录和退出
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        cp3.add(jb1);
        cp3.add(jb2);
        cp3.setLayout(new FlowLayout());//这是默认的
        this.add(cp0);
        this.add(cp1);
        this.add(cp2);
        this.add(cp3);

        this.setTitle("图书管理系统-登录");
        this.setSize(300,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void confirm(){
        Connection conn = ConnectionFactory.getConnction();
        try {
            Statement sql = conn.createStatement();
            String username = jt1.getText().trim();

            String password = jpf.getText().trim();

            if(username == null || password == null){
                JOptionPane.showMessageDialog(null,"密码或者用户名不能为空",null,JOptionPane.OK_OPTION);

            }
            //System.out.println(username +"===="+password);
            String Qpassword = "select * from UserInfo where UserName='"
                    +username+"' and PassWord='"+password+"'";
            ResultSet rs = sql.executeQuery(Qpassword);
            if (rs.next()){
                //JOptionPane.showMessageDialog(null,"success","提示！",JOptionPane.OK_OPTION);
                int id = rs.getInt("ID");
                System.out.println(id+"==========");
                new BookOperation(id);
                this.setVisible(false);
                System.out.println("登录成功");

            }else{
                retry--;
                if(retry>0){
                    JOptionPane.showMessageDialog(null,"该用户不存在或者密码错误，请重试(剩余"+retry+"次机会)","提示！",JOptionPane.YES_NO_OPTION);
                }else {
                    JOptionPane.showMessageDialog(null,"输入错误次数达到3次，谢谢使用","提示！",JOptionPane.YES_NO_OPTION);
                    System.exit(0);
                }

                //jt1.setText("");
                jpf.setText("");
            }
            System.out.println(password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"数据库错误","提示！",JOptionPane.OK_OPTION);
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if(Objects.equals(str,"登录")){
            confirm();
        }else{
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        new Login();
    }
}

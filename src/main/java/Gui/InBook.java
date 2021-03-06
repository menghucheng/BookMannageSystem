package Gui;

import Utils.ConnectionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mhc on 15-5-31.
 */

public class InBook extends  BookGui{

    public  InBook(int id){
        super();
        this.id = id;
        jb1.addActionListener(this);//绑定监听事件
        jb2.addActionListener(this);
    }

    private boolean check(){
        //检查是否有空的未填
        boolean flag=true;
       // System.out.println("有啊啊啊啊");

        for(int i=0;i<jtfs.length && flag; i++){
            if(jtfs[i].getText().equals("")) {
                flag=false;
                break;
            }
        }
        if (!flag) {
            JOptionPane.showMessageDialog(f, "请填写完整图书资料");
            return false;
        }
        return true;

    }
        public void insertRecord(){
        Statement sql;
        Connection conn = ConnectionFactory.getConnction();
            String s = null;//查询输入的图书号是否在数据库中存在
            try {
                s = "insert into BookInfo(BookId,BookName,Author,Publish,Price,Time)  values('" + jtfs[0].getText() + "','" + jtfs[1].getText() + "','" + jtfs[2].getText() + "','" +jtfs[3].getText()+ "','"+ Float.parseFloat(jtfs[4].getText()) +"','"+jtfs[5].getText()+ "');";
                System.out.println(s);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(f, "不能为空");

                e.printStackTrace();
                return;
            }
            String query = "select * from BookInfo where BookId ='"+jtfs[0].getText()+"'";
        if (!check()){
            return;
        }
        try {
            sql = conn.createStatement();
            System.out.println(query);
            ResultSet rs = sql.executeQuery(query);//返回查询结果
            boolean moreRecords=rs.next();//判断结果集是否有数据
            System.out.println(moreRecords);
            if(moreRecords){
                //按图书名字查重
                JOptionPane.showMessageDialog(f,"该图书已被录入，请重新输入");
                jtfs[2].setText("");
                return;
            }else{
                System.out.println("查询"+s);
                int innum = sql.executeUpdate(s);
                 if(innum==1){
                     JOptionPane.showMessageDialog(null, "图书信息录入成功！");
                     jtfs[0].setText("");
                     jtfs[1].setText("");
                     jtfs[2].setText("");
                     jtfs[3].setText("");
                     jtfs[4].setText("");
                     jtfs[5].setText("");
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
            conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
        }
      /*   
         
        int insert=sql.executeUpdate(s);

         */
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        //System.out.println(command);
        if(command.equals("确定")){
            //System.out.println("我执行了");
            insertRecord();
        }else{//取消的情况
            f.setVisible(false);
            new BookOperation(id).setVisible(true);

        }

    }

    public static void main(String[] args) {
        new InBook(1);
    }
}

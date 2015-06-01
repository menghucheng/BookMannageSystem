package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mhc on 15-5-31.
 */
public abstract class BookGui extends JFrame implements ActionListener{

    protected int id;//存放当前user标识
    JFrame f = new JFrame();
    String[] nlabels = new String[]{"  图书入库  ","图 书 号","图 书 名","作    者"," 出 版 社","   单  价  ","  入库时间  "};
    JLabel [] labels = new JLabel[7];
    JButton jb1 = new JButton("确定");
    JButton jb2 = new JButton("取消");
    Container cp;
    JPanel[] jps = new JPanel[8];
    JTextField[] jtfs = new JTextField[6];
    public BookGui(){
        //创建label
        for (int i=0;i<labels.length;i++){
            labels[i] = new JLabel(nlabels[i]);
        }
        labels[0].setFont(new Font("Dialog",1,18));
        labels[0].setForeground(Color.magenta);
        //创建Jpanel
        for (int i=0;i<jps.length;i++){
            jps[i] = new JPanel();
        }
        //创建jtf
        for (int i=0;i<jtfs.length;i++){
            jtfs[i] = new JTextField(20);
        }

        cp = f.getContentPane();
        cp.setLayout(new GridLayout(8,1));
        //每一个jtf框前面都有标签,除了最后一个 是两个button

        for (int i=0;i<jps.length-1;i++){

            jps[i].add(labels[i]);//
            if(i!=0){
                jps[i].add(jtfs[i-1]);
            }
        }

        jps[jps.length-1].add(jb1);
        jps[jps.length-1].add(jb2);//jp7添加buton
        //添加所有的jpanel
        for (int i = 0; i < jps.length; i++) {
            cp.add(jps[i]);
        }
        jps[0].setBackground(Color.cyan);
        cp.add(jps[jps.length-1]);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(460,320);
        f.setVisible(true);
    }


    public static void main(String[] args) {
        //new BookGui();
    }
}

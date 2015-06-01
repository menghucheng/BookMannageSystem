package Gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Created by mhc on 15-5-31.
 */
public class BookOperation extends JFrame implements ActionListener{

    String[] bookItems = new String[]{"图书查询","图书入库","图书删除","图书概览"};
    String[] userItems = new String[]{"修改密码"};
    String[] otherItems = new String[]{"关于","退出"};
    JMenuBar jmb = new JMenuBar();//菜单栏
    JMenu bookOperate = new JMenu("图书信息操作");

    JMenu userOperate = new JMenu("用户信息操作");
    JMenu other = new JMenu("其他");
    Container cp = getContentPane();
    JPanel jp = new JPanel();//用来填放子模块
    int id;//存放user表中的主键id

    //背景图片
    ImageIcon img = new ImageIcon(System.getProperty("user.dir")+"/src/main/resources/picture/background.jpg");
    JLabel imageLable = new JLabel(img);//将图片放到标签里

    /**
     * Constructor
     * @param id primary key of userInfo
     */
    public BookOperation(int id){

        //设置背景图片的位置
        imageLable.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
        jp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Color.blue,2),null, TitledBorder.CENTER,TitledBorder.TOP));
        jp.setLayout(new BorderLayout());
        //System.out.println(imageLable);
        //添加背景图片
        jp.add(imageLable,"Center");

        cp.add(jp);
        this.id = id;

        //实现显示在当前屏幕的中间
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int y = screen.height;
        int x = screen.width;
        this.setLocation((x-580)/2,(y-800)/2);



       // jp.setLayout(new GridLayout(3,1));
        cp.add(jmb, "North");


        jmb.add(bookOperate);
        jmb.add(userOperate);
        jmb.add(other);


        //组装图书操作菜单
        for (int i=0;i<bookItems.length;i++){
            JMenuItem item = new JMenuItem(bookItems[i]);
            item.setFont(new Font("Dialog", 1, 14));
            item.setForeground(Color.magenta);
            item.addActionListener(this);
            bookOperate.add(item);
        }
        //组装用户的操作菜单
        for (int i=0;i<userItems.length;i++){
            JMenuItem item = new JMenuItem(userItems[i]);
            item.setFont(new Font("Dialog", 1, 14));
            item.setForeground(Color.magenta);
            item.addActionListener(this);
            userOperate.add(item);
        }
        //组装其他的操作菜单
        for (int i=0;i<otherItems.length;i++){
            JMenuItem item = new JMenuItem(otherItems[i]);
            item.setFont(new Font("Dialog", 1, 14));
            item.setForeground(Color.magenta);
            item.addActionListener(this);
            other.add(item);
        }
        this.setTitle("萌先森的图书管理系统");
        this.setSize(800,580);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //this.pack();//自动设置大小
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        //System.out.println(command);
        if(Objects.equals(command,"退出")){
            System.exit(0);
        }else if(command.equals("图书入库")){
            this.setVisible(false);
            new InBook(id);
        }
    }

    public static void main(String[] args) {
        new BookOperation(1);
    }
}

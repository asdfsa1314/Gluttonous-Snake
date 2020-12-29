package swjd;

//import java.awt.*;

import sun.awt.AWTAccessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
//import java.awt.event.KeyEvent;

public class GamePanle extends JPanel implements KeyListener , ActionListener {
//    默认游戏不开始
    boolean isStack = false;

    boolean isFail = false;

    sncak flag;
    Timer timer;

    int FoodX;
    int FoodY;
    Random random = new Random();


    public GamePanle(){

        flag = new sncak();
        timer = new Timer(55, this);

        FoodX = 25+25*random.nextInt(22);
        FoodY = 25+25*random.nextInt(11);



        //游戏一开始定时器启动
        timer.start();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制一个面板,黑色大小固定
        g.fillRect(0,0,850,1000);
        //绘制一个长方形的广告面板
        Date.AD.paintIcon(this,g,850,0);
        //绘制小蛇的头
        Date.HEAD.paintIcon(this,g,flag.snackX[0],flag.snackY[0]);

        //绘制食物的样子
        Date.FOOD.paintIcon(this, g, FoodX, FoodY);


//        //用for循环输出小蛇身体
        for (int i =1; i < flag.length; i++) {
            Date.BODY.paintIcon(this, g, flag.snackX[i], flag.snackY[i]);
        }

        //绘制暂停时间的样式
        if (isStack == false){
            g.setColor(Color.ORANGE);
            g.setFont(new Font("微软雅黑",50,50));
            g.drawString("蔡阿康傻逼,游戏暂停",150,50);
        }

        //绘制失败的图
        if (isFail) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("微软雅黑", 100, 100));
            g.drawString("GAME OVER", 250, 250);
        }

    }


    //建立一个键盘监听事件,用于控制方向
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int KeyCode = e.getKeyCode();

        if (KeyCode == KeyEvent.VK_W){
            flag.Aim ="U";
        }else if (KeyCode == KeyEvent.VK_A){
            flag.Aim ="L";
        }else if (KeyCode == KeyEvent.VK_D){
            flag.Aim="R";
        }else if (KeyCode == KeyEvent.VK_S){
            flag.Aim="D";
        }
        if (KeyCode == KeyEvent.VK_SPACE){
            if(isFail){
                //这边要重新开始,失败了就要重新开始进行初始化蛇与初始化
                flag = new sncak();
                isFail = false;

                FoodX = 25+25*random.nextInt(22);
                FoodY = 25+25*random.nextInt(11);

            }else {
                isStack = !isStack;
                //这里必须要用repaint来重绘
                repaint();
            }
        }
        System.out.println(flag.Aim);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }




    //建立一共背面监听事件,用来处理
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStack && isFail == false) {//如果两个都为假,那么就真的是假,停止游戏


        //每吃一个食物,就变长
            if (flag.snackX[0] == FoodX && flag.snackY[0] == FoodY){
                flag.length = flag.length + 1;
                FoodX = 25+25*random.nextInt(22);
                FoodY = 25+25*random.nextInt(11);
            }


        //目的就是让小蛇动起来
        for (int i = flag.length - 1; i > 0; i--) {
            flag.snackX[i] = flag.snackX[i - 1];
            flag.snackY[i] = flag.snackY[i - 1];
        }

        //判断头的方向
            if ("R".equals(flag.Aim)){
                flag.snackX[0] = flag.snackX[0] + 25;
                if (flag.snackX[0]>825){
                    flag.snackX[0]=25;
                }
            }else if ("L".equals(flag.Aim)){
                flag.snackX[0] = flag.snackX[0] - 25;
                if (flag.snackX[0]<0){
                    flag.snackX[0]=825;
                }
            }else if ("U".equals(flag.Aim)){
                flag.snackY[0] = flag.snackY[0] - 25;
                if (flag.snackY[0]<0){
                    flag.snackY[0]=1000;
                }
            }else if ("D".equals(flag.Aim)){
                flag.snackY[0] = flag.snackY[0] + 25;
                if (flag.snackY[0]>1000) {
                    flag.snackY[0]=0;
                }
            }
            //写一个循环用于判断蛇是不是撞到自己了
            for (int i =1; i<flag.length; i++){
                if (flag.snackX[i] == flag.snackX[0] && flag.snackY[i] == flag.snackY[0]){
                    isFail = true;
                }
            }

        }
        repaint();
    }
}
class Main_0 {
    public static void main(String[] args) {
        JFrame FUCK = new JFrame();
        GamePanle HEHE = new GamePanle();
        FUCK.setVisible(true);
        FUCK.setBounds(250,250,1000,1000);
        FUCK.setDefaultCloseOperation(3);
        FUCK.setResizable(false);
        FUCK.add(HEHE);

//        FUCK.setFocusable(true);
//        FUCK.addKeyListener(HEHE);

        //来获得焦点\添加键盘监听事件\后台监听事件
        FUCK.setFocusable(true);
        FUCK.addKeyListener(HEHE);
//

    }
}

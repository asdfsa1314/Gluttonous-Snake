package swjd;


public class sncak {
    int length;

    //
    int[] snackX = new int[800];
    int[] snackY = new int[800];
    String Aim ="R";

    public sncak(){
        length = 3;
        snackX[0] =100;snackY[0] = 100;// 脑袋的坐标
        snackX[1] = 75; snackY[1] = 100;// 第一个身体的坐标
        snackX[2] = 50;snackY[2]=100;//第三个身体的坐标
    }
}

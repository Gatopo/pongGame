package threadobjects;

/**
 * Created by mario on 26/08/14.
 */
public class Score extends Thread{
    static int score = 0;

    public static void setScore(int newScore){
        if(newScore >= 0) {
            score = newScore;
        }else{score = 0;}
    }
}

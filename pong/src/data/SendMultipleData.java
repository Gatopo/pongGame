package data;

/**
 * Created by Steven on 17/08/2014.
 */
public class SendMultipleData {

    public static String sendRacketPositions(int racketL, int racketR){
        String racketPositions = "000,";
        racketPositions += racketL + ",";
        racketPositions += racketR;
        return racketPositions;
    }
}

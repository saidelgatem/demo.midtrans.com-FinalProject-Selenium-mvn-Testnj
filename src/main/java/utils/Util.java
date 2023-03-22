package utils;

public class Util {

    public static void holdExecution(int time){
        try {
            Thread.sleep(time*1000);
        }catch (Exception e){

        }
    }
}



package cn.pfc.pfc523.config;


public class AppConfig {
    private static String IP = "192.168.90.13";
    private static final int PORT = 8080;
    public static final String BASE_URL = "http://" + IP + ":" + PORT
            + "/transportservice/action/";
    public static final String BASE_URL_IMG = "http://" + IP + ":" + PORT
            + "/transportservice/";

    public static final String SET_CAR_ACTION = "SetCarMove.do";
    public static final String KEY_CAR_ID = "CarId";
    public static final String KEY_CAR_ACTION = "CarAction";
}

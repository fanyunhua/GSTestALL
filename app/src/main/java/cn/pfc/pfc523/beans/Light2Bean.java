package cn.pfc.pfc523.beans;

public class Light2Bean {
    int road;
    String red;
    String yellow;
    String green;

    public Light2Bean(int road, String red, String yellow, String green) {
        this.road = road;
        this.red = red;
        this.yellow = yellow;
        this.green = green;
    }

    public int getRoad() {
        return road;
    }

    public String getRed() {
        return red;
    }

    public String getYellow() {
        return yellow;
    }

    public String getGreen() {
        return green;
    }
}

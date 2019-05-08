package cn.pfc.pfc523.beans;

public class MyFeed {
    private String title,state,submitTime,hfContent,hfTime;

    public MyFeed(String title, String state, String submitTime, String hfContent, String hfTime) {
        this.title = title;
        this.state = state;
        this.submitTime = submitTime;
        this.hfContent = hfContent;
        this.hfTime = hfTime;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public String getHfContent() {
        return hfContent;
    }

    public String getHfTime() {
        return hfTime;
    }
}

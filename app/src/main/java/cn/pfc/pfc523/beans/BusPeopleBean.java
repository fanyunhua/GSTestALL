package cn.pfc.pfc523.beans;

public class BusPeopleBean {
    String id,busId,prople;

    public BusPeopleBean(String id, String busId, String prople) {
        this.id = id;
        this.busId = busId;
        this.prople = prople;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public void setProple(String prople) {
        this.prople = prople;
    }

    public String getId() {
        return id;
    }

    public String getBusId() {
        return busId;
    }

    public String getProple() {
        return prople;
    }
}

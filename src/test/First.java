package test;

import java.util.List;

/**
 * Created by WaterMelon on 2018/4/26.
 */
public class First{
    private String task;
    private String name;
    private String id;
    private String desc;
    private String orderid;
    private List<inputfilelist> inputfilelist;
    private List<outputfilelist> outputfilelist;
    private List<params> params;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public List<inputfilelist> getInputfilelist() {
        return inputfilelist;
    }

    public void setInputfilelist(List<inputfilelist> inputfilelist) {
        this.inputfilelist = inputfilelist;
    }

    public List<outputfilelist> getOutputfilelist() {
        return outputfilelist;
    }

    public void setOutputfilelist(List<outputfilelist> outputfilelist) {
        this.outputfilelist = outputfilelist;
    }

    public List<params> getParams() {
        return params;
    }

    public void setParams(List<params> params) {
        this.params = params;
    }
}


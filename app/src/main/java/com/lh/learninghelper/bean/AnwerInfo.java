package com.lh.learninghelper.bean;

import java.io.Serializable;
import java.util.List;

public class AnwerInfo{


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private List<SubDataBean> data;

        public List<SubDataBean> getData() {
            return data;
        }

        public void setData(List<SubDataBean> data) {
            this.data = data;
        }

        public static class SubDataBean implements Serializable {




        }
    }
}

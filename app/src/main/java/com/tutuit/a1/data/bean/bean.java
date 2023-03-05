package com.tutuit.a1.data.bean;

import java.io.Serializable;
import java.util.List;

public class bean implements Serializable {

    /**
     * data : [{"id":33894312,"url":"http://m7.music.126.net/20230226133902/772f5760f43cdcc414b5849771df15c3/ymusic/0fd6/4f65/43ed/a8772889f38dfcb91c04da915b301617.mp3","br":320000,"size":10691439,"md5":"a8772889f38dfcb91c04da915b301617","code":200,"expi":1200,"type":"mp3","gain":-6.3072,"peak":1,"fee":0,"uf":null,"payed":0,"flag":1,"canExtend":false,"freeTrialInfo":null,"level":"exhigh","encodeType":"mp3","freeTrialPrivilege":{"resConsumable":false,"userConsumable":false,"listenType":null,"cannotListenReason":null},"freeTimeTrialPrivilege":{"resConsumable":false,"userConsumable":false,"type":0,"remainTime":0},"urlSource":0,"rightSource":0,"podcastCtrp":null,"effectTypes":null,"time":267232}]
     * code : 200
     */

    private int code;
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 33894312
         * url : http://m7.music.126.net/20230226133902/772f5760f43cdcc414b5849771df15c3/ymusic/0fd6/4f65/43ed/a8772889f38dfcb91c04da915b301617.mp3
         * br : 320000
         * size : 10691439
         * md5 : a8772889f38dfcb91c04da915b301617
         * code : 200
         * expi : 1200
         * type : mp3
         * gain : -6.3072
         * peak : 1
         * fee : 0
         * uf : null
         * payed : 0
         * flag : 1
         * canExtend : false
         * freeTrialInfo : null
         * level : exhigh
         * encodeType : mp3
         * freeTrialPrivilege : {"resConsumable":false,"userConsumable":false,"listenType":null,"cannotListenReason":null}
         * freeTimeTrialPrivilege : {"resConsumable":false,"userConsumable":false,"type":0,"remainTime":0}
         * urlSource : 0
         * rightSource : 0
         * podcastCtrp : null
         * effectTypes : null
         * time : 267232
         */

        private int id;
        private String url;
        private int br;
        private int size;
        private String md5;
        private int code;
        private int expi;
        private String type;
        private double gain;
        private int peak;
        private int fee;
        private Object uf;
        private int payed;
        private int flag;
        private boolean canExtend;
        private Object freeTrialInfo;
        private String level;
        private String encodeType;
        private FreeTrialPrivilegeBean freeTrialPrivilege;
        private FreeTimeTrialPrivilegeBean freeTimeTrialPrivilege;
        private int urlSource;
        private int rightSource;
        private Object podcastCtrp;
        private Object effectTypes;
        private int time;

        public String getUrl() {
            return url;
        }

        public static class FreeTrialPrivilegeBean implements Serializable {
            /**
             * resConsumable : false
             * userConsumable : false
             * listenType : null
             * cannotListenReason : null
             */

            private boolean resConsumable;
            private boolean userConsumable;
            private Object listenType;
            private Object cannotListenReason;
        }

        public static class FreeTimeTrialPrivilegeBean implements Serializable {
            /**
             * resConsumable : false
             * userConsumable : false
             * type : 0
             * remainTime : 0
             */

            private boolean resConsumable;
            private boolean userConsumable;
            private int type;
            private int remainTime;
        }
    }
}

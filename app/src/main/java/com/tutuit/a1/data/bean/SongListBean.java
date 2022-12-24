package com.tutuit.a1.data.bean;

import java.io.Serializable;
import java.util.List;

public class SongListBean implements Serializable {

    /**
     * songs : [{"name":"きみにとどけ","id":419250418,"pst":0,"t":0,"ar":[{"id":1182972,"name":"佐藤亜美菜","tns":[],"alias":[]}],"alia":[],"pop":95,"st":0,"rt":null,"fee":0,"v":5,"crbt":null,"cf":"","al":{"id":34754096,"name":"THE IDOLM@STER CINDERELLA MASTER Cool jewelries! 003","picUrl":"https://p1.music.126.net/6AhCZOo2yixW0JTdpt3lxg==/3420580698151443.jpg","tns":[],"pic":3420580698151443},"dt":206471,"h":{"br":320000,"fid":0,"size":8259962,"vd":-45405,"sr":44100},"m":{"br":192000,"fid":0,"size":4955995,"vd":-45405,"sr":44100},"l":{"br":128000,"fid":0,"size":3304011,"vd":-45405,"sr":44100},"sq":null,"hr":null,"a":null,"cd":"1","no":2,"rtUrl":null,"ftype":0,"rtUrls":[],"djId":0,"copyright":0,"s_id":0,"mark":262144,"originCoverType":0,"originSongSimpleData":null,"tagPicList":null,"resourceState":true,"version":5,"songJumpInfo":null,"entertainmentTags":null,"awardTags":null,"single":0,"noCopyrightRcmd":null,"rtype":0,"rurl":null,"mst":9,"cp":663018,"mv":0,"publishTime":1467199427570,"tns":["向你传达吧"]}]
     * privileges : [{"id":419250418,"fee":0,"payed":0,"st":0,"pl":320000,"dl":320000,"sp":7,"cp":1,"subp":1,"cs":false,"maxbr":320000,"fl":320000,"toast":false,"flag":256,"preSell":false,"playMaxbr":320000,"downloadMaxbr":320000,"maxBrLevel":"exhigh","playMaxBrLevel":"exhigh","downloadMaxBrLevel":"exhigh","plLevel":"exhigh","dlLevel":"exhigh","flLevel":"exhigh","rscl":null,"freeTrialPrivilege":{"resConsumable":false,"userConsumable":false,"listenType":null},"chargeInfoList":[{"rate":128000,"chargeUrl":null,"chargeMessage":null,"chargeType":0},{"rate":192000,"chargeUrl":null,"chargeMessage":null,"chargeType":0},{"rate":320000,"chargeUrl":null,"chargeMessage":null,"chargeType":0},{"rate":999000,"chargeUrl":null,"chargeMessage":null,"chargeType":1}]}]
     * code : 200
     */

    private int code;
    private List<SongsBean> songs;
    private List<PrivilegesBean> privileges;

    public List<SongsBean> getSongs() {
        return songs;
    }

    public static class SongsBean implements Serializable {
        /**
         * name : きみにとどけ
         * id : 419250418
         * pst : 0
         * t : 0
         * ar : [{"id":1182972,"name":"佐藤亜美菜","tns":[],"alias":[]}]
         * alia : []
         * pop : 95
         * st : 0
         * rt : null
         * fee : 0
         * v : 5
         * crbt : null
         * cf :
         * al : {"id":34754096,"name":"THE IDOLM@STER CINDERELLA MASTER Cool jewelries! 003","picUrl":"https://p1.music.126.net/6AhCZOo2yixW0JTdpt3lxg==/3420580698151443.jpg","tns":[],"pic":3420580698151443}
         * dt : 206471
         * h : {"br":320000,"fid":0,"size":8259962,"vd":-45405,"sr":44100}
         * m : {"br":192000,"fid":0,"size":4955995,"vd":-45405,"sr":44100}
         * l : {"br":128000,"fid":0,"size":3304011,"vd":-45405,"sr":44100}
         * sq : null
         * hr : null
         * a : null
         * cd : 1
         * no : 2
         * rtUrl : null
         * ftype : 0
         * rtUrls : []
         * djId : 0
         * copyright : 0
         * s_id : 0
         * mark : 262144
         * originCoverType : 0
         * originSongSimpleData : null
         * tagPicList : null
         * resourceState : true
         * version : 5
         * songJumpInfo : null
         * entertainmentTags : null
         * awardTags : null
         * single : 0
         * noCopyrightRcmd : null
         * rtype : 0
         * rurl : null
         * mst : 9
         * cp : 663018
         * mv : 0
         * publishTime : 1467199427570
         * tns : ["向你传达吧"]
         */

        private String name;
        private int id;
        private int pst;
        private int t;
        private int pop;
        private int st;
        private Object rt;
        private int fee;
        private int v;
        private Object crbt;
        private String cf;
        private AlBean al;
        private int dt;
        private HBean h;
        private MBean m;
        private LBean l;
        private Object sq;
        private Object hr;
        private Object a;
        private String cd;
        private int no;
        private Object rtUrl;
        private int ftype;
        private int djId;
        private int copyright;
        private int s_id;
        private int mark;
        private int originCoverType;
        private Object originSongSimpleData;
        private Object tagPicList;
        private boolean resourceState;
        private int version;
        private Object songJumpInfo;
        private Object entertainmentTags;
        private Object awardTags;
        private int single;
        private Object noCopyrightRcmd;
        private int rtype;
        private Object rurl;
        private int mst;
        private int cp;
        private int mv;
        private long publishTime;
        private List<ArBean> ar;
        private List<?> alia;
        private List<?> rtUrls;
        private List<String> tns;

        public String getName() {
            return name;
        }

        public List<ArBean> getAr() {
            return ar;
        }

        public int getId() {
            return id;
        }

        public static class AlBean implements Serializable {
            /**
             * id : 34754096
             * name : THE IDOLM@STER CINDERELLA MASTER Cool jewelries! 003
             * picUrl : https://p1.music.126.net/6AhCZOo2yixW0JTdpt3lxg==/3420580698151443.jpg
             * tns : []
             * pic : 3420580698151443
             */

            private int id;
            private String name;
            private String picUrl;
            private long pic;
            private List<?> tns;
        }

        public static class HBean implements Serializable {
            /**
             * br : 320000
             * fid : 0
             * size : 8259962
             * vd : -45405
             * sr : 44100
             */

            private int br;
            private int fid;
            private int size;
            private int vd;
            private int sr;
        }

        public static class MBean implements Serializable {
            /**
             * br : 192000
             * fid : 0
             * size : 4955995
             * vd : -45405
             * sr : 44100
             */

            private int br;
            private int fid;
            private int size;
            private int vd;
            private int sr;
        }

        public static class LBean implements Serializable {
            /**
             * br : 128000
             * fid : 0
             * size : 3304011
             * vd : -45405
             * sr : 44100
             */

            private int br;
            private int fid;
            private int size;
            private int vd;
            private int sr;
        }

        public static class ArBean implements Serializable {
            /**
             * id : 1182972
             * name : 佐藤亜美菜
             * tns : []
             * alias : []
             */

            private int id;
            private String name;
            private List<?> tns;
            private List<?> alias;

            public String getName() {
                return name;
            }
        }
    }

    public static class PrivilegesBean implements Serializable {
        /**
         * id : 419250418
         * fee : 0
         * payed : 0
         * st : 0
         * pl : 320000
         * dl : 320000
         * sp : 7
         * cp : 1
         * subp : 1
         * cs : false
         * maxbr : 320000
         * fl : 320000
         * toast : false
         * flag : 256
         * preSell : false
         * playMaxbr : 320000
         * downloadMaxbr : 320000
         * maxBrLevel : exhigh
         * playMaxBrLevel : exhigh
         * downloadMaxBrLevel : exhigh
         * plLevel : exhigh
         * dlLevel : exhigh
         * flLevel : exhigh
         * rscl : null
         * freeTrialPrivilege : {"resConsumable":false,"userConsumable":false,"listenType":null}
         * chargeInfoList : [{"rate":128000,"chargeUrl":null,"chargeMessage":null,"chargeType":0},{"rate":192000,"chargeUrl":null,"chargeMessage":null,"chargeType":0},{"rate":320000,"chargeUrl":null,"chargeMessage":null,"chargeType":0},{"rate":999000,"chargeUrl":null,"chargeMessage":null,"chargeType":1}]
         */

        private int id;
        private int fee;
        private int payed;
        private int st;
        private int pl;
        private int dl;
        private int sp;
        private int cp;
        private int subp;
        private boolean cs;
        private int maxbr;
        private int fl;
        private boolean toast;
        private int flag;
        private boolean preSell;
        private int playMaxbr;
        private int downloadMaxbr;
        private String maxBrLevel;
        private String playMaxBrLevel;
        private String downloadMaxBrLevel;
        private String plLevel;
        private String dlLevel;
        private String flLevel;
        private Object rscl;
        private FreeTrialPrivilegeBean freeTrialPrivilege;
        private List<ChargeInfoListBean> chargeInfoList;

        public static class FreeTrialPrivilegeBean implements Serializable {
            /**
             * resConsumable : false
             * userConsumable : false
             * listenType : null
             */

            private boolean resConsumable;
            private boolean userConsumable;
            private Object listenType;
        }

        public static class ChargeInfoListBean implements Serializable {
            /**
             * rate : 128000
             * chargeUrl : null
             * chargeMessage : null
             * chargeType : 0
             */

            private int rate;
            private Object chargeUrl;
            private Object chargeMessage;
            private int chargeType;
        }
    }
}
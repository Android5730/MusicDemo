package com.tutuit.a1.data.bean;

import java.io.Serializable;
import java.util.List;

// 歌单数据
public class SongCategoryBean implements Serializable {

    /**
     * playlists : [{"name":"少女心ღ｜恋爱是颗苹果糖","id":805407063,"trackNumberUpdateTime":1664433605635,"status":0,"userId":45441555,"createTime":1499671792464,"updateTime":1664434451000,"subscribedCount":84803,"trackCount":35,"cloudTrackCount":0,"coverImgUrl":"http://p2.music.126.net/KjskMc2kvX6vGMqkOIvzKQ==/19186477904926453.jpg","coverImgId":19186477904926452,"description":"酸酸甜甜的苹果糖，像极了恋爱的味道。\n\n「唉，少女心是什么感觉啊？」\n「大概是会紧张到走不动路吧。心里乱跳，手心冷热不均。」\n「这大概是病了吧。」\n「我喜欢就这样病着吧。」\n\n┄┅┄┅┅┄┅┅┄┅┅┄┅┅┄┅┅┄┅┄\n\n封面来源 p站 id = 63727217 画师：EKマサト\n原图在 2017年7月22日动态里，欢迎自取(=ﾟωﾟ)ﾉ","tags":["日语","治愈","ACG"],"playCount":2367283,"trackUpdateTime":1669907612362,"specialType":0,"totalDuration":0,"creator":{"defaultAvatar":false,"province":110000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/LKv5pOhQkQOQIiTitUdgrg==/109951167383157216.jpg","accountStatus":0,"gender":0,"city":110101,"birthday":915984000000,"userId":45441555,"userType":200,"nickname":"阿卡琳","signature":"欢迎小团子们关注阿卡琳 🎉","description":"","detailDescription":"","avatarImgId":109951167383157220,"backgroundImgId":109951166082525970,"backgroundUrl":"http://p1.music.126.net/C799kaHEjWhBpshPix39bA==/109951166082525965.jpg","authority":0,"mutual":false,"expertTags":["日语","ACG","轻音乐"],"experts":null,"djStatus":0,"vipType":11,"remarkName":null,"authenticationTypes":524352,"avatarDetail":{"userType":200,"identityLevel":4,"identityIconUrl":"https://p5.music.126.net/obj/wo3DlcOGw6DClTvDisK1/4761340194/0903/b735/7c7a/0dddcdf78047d397d24125840e54ab5b.png"},"avatarImgIdStr":"109951167383157216","backgroundImgIdStr":"109951166082525965","anchor":false,"avatarImgId_str":"109951167383157216"},"tracks":null,"subscribers":[{"defaultAvatar":false,"province":370000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/62JM85ffJ3pvKxTpwJ5MuQ==/109951165702891474.jpg","accountStatus":0,"gender":2,"city":370900,"birthday":1041572961000,"userId":3826693705,"userType":0,"nickname":"酒酿小樱桃2008","signature":"","description":"","detailDescription":"","avatarImgId":109951165702891470,"backgroundImgId":109951162868126480,"backgroundUrl":"http://p1.music.126.net/_f8R60U9mZ42sSNvdPn2sQ==/109951162868126486.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"authenticationTypes":0,"avatarDetail":null,"avatarImgIdStr":"109951165702891474","backgroundImgIdStr":"109951162868126486","anchor":false,"avatarImgId_str":"109951165702891474"}],"subscribed":false,"commentThreadId":"A_PL_0_805407063","newImported":false,"adType":0,"highQuality":true,"privacy":0,"ordered":true,"anonimous":false,"coverStatus":3,"recommendInfo":null,"shareCount":1109,"coverImgId_str":"19186477904926453","commentCount":418,"copywriter":"","tag":"日语,治愈,ACG"}]
     * code : 200
     * more : true
     * lasttime : 1664434451000
     * total : 39
     */

    private int code;
    private boolean more;
    private long lasttime;
    private int total;
    private List<PlaylistsBean> playlists;

    public List<PlaylistsBean> getPlaylists() {
        return playlists;
    }

    public static class PlaylistsBean implements Serializable {


        /**
         * name : 少女心ღ｜恋爱是颗苹果糖
         * id : 805407063
         * trackNumberUpdateTime : 1664433605635
         * status : 0
         * userId : 45441555
         * createTime : 1499671792464
         * updateTime : 1664434451000
         * subscribedCount : 84803
         * trackCount : 35
         * cloudTrackCount : 0
         * coverImgUrl : http://p2.music.126.net/KjskMc2kvX6vGMqkOIvzKQ==/19186477904926453.jpg
         * coverImgId : 19186477904926452
         * description : 酸酸甜甜的苹果糖，像极了恋爱的味道。

         「唉，少女心是什么感觉啊？」
         「大概是会紧张到走不动路吧。心里乱跳，手心冷热不均。」
         「这大概是病了吧。」
         「我喜欢就这样病着吧。」

         ┄┅┄┅┅┄┅┅┄┅┅┄┅┅┄┅┅┄┅┄

         封面来源 p站 id = 63727217 画师：EKマサト
         原图在 2017年7月22日动态里，欢迎自取(=ﾟωﾟ)ﾉ
         * tags : ["日语","治愈","ACG"]
         * playCount : 2367283
         * trackUpdateTime : 1669907612362
         * specialType : 0
         * totalDuration : 0
         * creator : {"defaultAvatar":false,"province":110000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/LKv5pOhQkQOQIiTitUdgrg==/109951167383157216.jpg","accountStatus":0,"gender":0,"city":110101,"birthday":915984000000,"userId":45441555,"userType":200,"nickname":"阿卡琳","signature":"欢迎小团子们关注阿卡琳 🎉","description":"","detailDescription":"","avatarImgId":109951167383157220,"backgroundImgId":109951166082525970,"backgroundUrl":"http://p1.music.126.net/C799kaHEjWhBpshPix39bA==/109951166082525965.jpg","authority":0,"mutual":false,"expertTags":["日语","ACG","轻音乐"],"experts":null,"djStatus":0,"vipType":11,"remarkName":null,"authenticationTypes":524352,"avatarDetail":{"userType":200,"identityLevel":4,"identityIconUrl":"https://p5.music.126.net/obj/wo3DlcOGw6DClTvDisK1/4761340194/0903/b735/7c7a/0dddcdf78047d397d24125840e54ab5b.png"},"avatarImgIdStr":"109951167383157216","backgroundImgIdStr":"109951166082525965","anchor":false,"avatarImgId_str":"109951167383157216"}
         * tracks : null
         * subscribers : [{"defaultAvatar":false,"province":370000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/62JM85ffJ3pvKxTpwJ5MuQ==/109951165702891474.jpg","accountStatus":0,"gender":2,"city":370900,"birthday":1041572961000,"userId":3826693705,"userType":0,"nickname":"酒酿小樱桃2008","signature":"","description":"","detailDescription":"","avatarImgId":109951165702891470,"backgroundImgId":109951162868126480,"backgroundUrl":"http://p1.music.126.net/_f8R60U9mZ42sSNvdPn2sQ==/109951162868126486.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"authenticationTypes":0,"avatarDetail":null,"avatarImgIdStr":"109951165702891474","backgroundImgIdStr":"109951162868126486","anchor":false,"avatarImgId_str":"109951165702891474"}]
         * subscribed : false
         * commentThreadId : A_PL_0_805407063
         * newImported : false
         * adType : 0
         * highQuality : true
         * privacy : 0
         * ordered : true
         * anonimous : false
         * coverStatus : 3
         * recommendInfo : null
         * shareCount : 1109
         * coverImgId_str : 19186477904926453
         * commentCount : 418
         * copywriter :
         * tag : 日语,治愈,ACG
         */

        private String name;
        private long id;
        private long trackNumberUpdateTime;
        private int status;
        private int userId;
        private long createTime;
        private long updateTime;
        private int subscribedCount;
        private int trackCount;
        private int cloudTrackCount;
        private String coverImgUrl;
        private long coverImgId;
        private String description;
        private int playCount;
        private long trackUpdateTime;
        private int specialType;
        private int totalDuration;
        private CreatorBean creator;
        private Object tracks;
        private boolean subscribed;
        private String commentThreadId;
        private boolean newImported;
        private int adType;
        private boolean highQuality;
        private int privacy;
        private boolean ordered;
        private boolean anonimous;
        private int coverStatus;
        private Object recommendInfo;
        private int shareCount;
        private String coverImgId_str;
        private int commentCount;
        private String copywriter;
        private String tag;
        private List<String> tags;
        private List<SubscribersBean> subscribers;

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public String getDescription() {
            return description;
        }

        public static class CreatorBean implements Serializable {
            /**
             * defaultAvatar : false
             * province : 110000
             * authStatus : 0
             * followed : false
             * avatarUrl : http://p1.music.126.net/LKv5pOhQkQOQIiTitUdgrg==/109951167383157216.jpg
             * accountStatus : 0
             * gender : 0
             * city : 110101
             * birthday : 915984000000
             * userId : 45441555
             * userType : 200
             * nickname : 阿卡琳
             * signature : 欢迎小团子们关注阿卡琳 🎉
             * description :
             * detailDescription :
             * avatarImgId : 109951167383157220
             * backgroundImgId : 109951166082525970
             * backgroundUrl : http://p1.music.126.net/C799kaHEjWhBpshPix39bA==/109951166082525965.jpg
             * authority : 0
             * mutual : false
             * expertTags : ["日语","ACG","轻音乐"]
             * experts : null
             * djStatus : 0
             * vipType : 11
             * remarkName : null
             * authenticationTypes : 524352
             * avatarDetail : {"userType":200,"identityLevel":4,"identityIconUrl":"https://p5.music.126.net/obj/wo3DlcOGw6DClTvDisK1/4761340194/0903/b735/7c7a/0dddcdf78047d397d24125840e54ab5b.png"}
             * avatarImgIdStr : 109951167383157216
             * backgroundImgIdStr : 109951166082525965
             * anchor : false
             * avatarImgId_str : 109951167383157216
             */

            private boolean defaultAvatar;
            private int province;
            private int authStatus;
            private boolean followed;
            private String avatarUrl;
            private int accountStatus;
            private int gender;
            private int city;
            private long birthday;
            private int userId;
            private int userType;
            private String nickname;
            private String signature;
            private String description;
            private String detailDescription;
            private long avatarImgId;
            private long backgroundImgId;
            private String backgroundUrl;
            private int authority;
            private boolean mutual;
            private Object experts;
            private int djStatus;
            private int vipType;
            private Object remarkName;
            private int authenticationTypes;
            private AvatarDetailBean avatarDetail;
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private boolean anchor;
            private String avatarImgId_str;
            private List<String> expertTags;
            public String getAvatarUrl() {
                return avatarUrl;
            }

            public String getNickname() {
                return nickname;
            }

            public static class AvatarDetailBean implements Serializable {
                /**
                 * userType : 200
                 * identityLevel : 4
                 * identityIconUrl : https://p5.music.126.net/obj/wo3DlcOGw6DClTvDisK1/4761340194/0903/b735/7c7a/0dddcdf78047d397d24125840e54ab5b.png
                 */

                private int userType;
                private int identityLevel;
                private String identityIconUrl;
            }
        }

        public static class SubscribersBean implements Serializable {
            /**
             * defaultAvatar : false
             * province : 370000
             * authStatus : 0
             * followed : false
             * avatarUrl : http://p1.music.126.net/62JM85ffJ3pvKxTpwJ5MuQ==/109951165702891474.jpg
             * accountStatus : 0
             * gender : 2
             * city : 370900
             * birthday : 1041572961000
             * userId : 3826693705
             * userType : 0
             * nickname : 酒酿小樱桃2008
             * signature :
             * description :
             * detailDescription :
             * avatarImgId : 109951165702891470
             * backgroundImgId : 109951162868126480
             * backgroundUrl : http://p1.music.126.net/_f8R60U9mZ42sSNvdPn2sQ==/109951162868126486.jpg
             * authority : 0
             * mutual : false
             * expertTags : null
             * experts : null
             * djStatus : 0
             * vipType : 0
             * remarkName : null
             * authenticationTypes : 0
             * avatarDetail : null
             * avatarImgIdStr : 109951165702891474
             * backgroundImgIdStr : 109951162868126486
             * anchor : false
             * avatarImgId_str : 109951165702891474
             */

            private boolean defaultAvatar;
            private int province;
            private int authStatus;
            private boolean followed;
            private String avatarUrl;
            private int accountStatus;
            private int gender;
            private int city;
            private long birthday;
            private long userId;
            private int userType;
            private String nickname;
            private String signature;
            private String description;
            private String detailDescription;
            private long avatarImgId;
            private long backgroundImgId;
            private String backgroundUrl;
            private int authority;
            private boolean mutual;
            private Object expertTags;
            private Object experts;
            private int djStatus;
            private int vipType;
            private Object remarkName;
            private int authenticationTypes;
            private Object avatarDetail;
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private boolean anchor;
            private String avatarImgId_str;
        }
    }
}
package com.tutuit.a1.data.bean;


import java.util.List;

public class HotBean {
    private List<Tags> tags;
    // 判断成功的码，200
    private int code;
    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
    public List<Tags> getTags() {
        return tags;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    class Tags {
        // 小集合
        private PlaylistTag playlistTag;
        private boolean activity;
        private long usedCount;
        private boolean hot;
        private long createTime;
        // 位置 1
        private int position;
        private int category;
        // 类型 华语
        private String name;
        private int id;
        private int type;
        public void setPlaylistTag(PlaylistTag playlistTag) {
            this.playlistTag = playlistTag;
        }
        public PlaylistTag getPlaylistTag() {
            return playlistTag;
        }

        public void setActivity(boolean activity) {
            this.activity = activity;
        }
        public boolean getActivity() {
            return activity;
        }

        public void setUsedCount(long usedCount) {
            this.usedCount = usedCount;
        }
        public long getUsedCount() {
            return usedCount;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }
        public boolean getHot() {
            return hot;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
        public long getCreateTime() {
            return createTime;
        }

        public void setPosition(int position) {
            this.position = position;
        }
        public int getPosition() {
            return position;
        }

        public void setCategory(int category) {
            this.category = category;
        }
        public int getCategory() {
            return category;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setType(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public class PlaylistTag {

            private int id;
            private String name;
            private int category;
            private long usedCount;
            private int type;
            private int position;
            private long createTime;
            private int highQuality;
            private int highQualityPos;
            private int officialPos;
            public void setId(int id) {
                this.id = id;
            }
            public int getId() {
                return id;
            }

            public void setName(String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }

            public void setCategory(int category) {
                this.category = category;
            }
            public int getCategory() {
                return category;
            }

            public void setUsedCount(long usedCount) {
                this.usedCount = usedCount;
            }
            public long getUsedCount() {
                return usedCount;
            }

            public void setType(int type) {
                this.type = type;
            }
            public int getType() {
                return type;
            }

            public void setPosition(int position) {
                this.position = position;
            }
            public int getPosition() {
                return position;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
            public long getCreateTime() {
                return createTime;
            }

            public void setHighQuality(int highQuality) {
                this.highQuality = highQuality;
            }
            public int getHighQuality() {
                return highQuality;
            }

            public void setHighQualityPos(int highQualityPos) {
                this.highQualityPos = highQualityPos;
            }
            public int getHighQualityPos() {
                return highQualityPos;
            }

            public void setOfficialPos(int officialPos) {
                this.officialPos = officialPos;
            }
            public int getOfficialPos() {
                return officialPos;
            }

        }

    }
}

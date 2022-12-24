package com.tutuit.a1.data.bean;

import java.util.List;

// 精选歌单
public class ChoicenessBean {
    private List<Tags> tags;
    // 200
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


    public class Tags {
/*             "id": 5001,
                     "name": "华语",
                     "type": 0,
                     "category": 0,
                     "hot": false*/
        private int id;
        private String name;
        private int type;
        private int category;
        private boolean hot;
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

        public void setType(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public void setCategory(int category) {
            this.category = category;
        }
        public int getCategory() {
            return category;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }
        public boolean getHot() {
            return hot;
        }

    }
}

package com.xy.simplewandroid.bean;

import com.kunminx.linkage.bean.BaseGroupedItem;

public class NavigationGroupItem extends BaseGroupedItem<NavigationGroupItem.ItemInfo> {


    public NavigationGroupItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public NavigationGroupItem(ItemInfo info) {
        super(info);
    }

    public static class ItemInfo extends BaseGroupedItem.ItemInfo {
        private String content;
        private String link;
        private int id;

        public ItemInfo(String title, String group, String content) {
            super(title, group);
            this.content = content;
        }

        public ItemInfo(String title, String group, String content, String link) {
            this(title, group, content);
            this.link = link;
        }

        public ItemInfo(String title, String group, String content, String link, int id) {
            this(title, group,content,link);
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

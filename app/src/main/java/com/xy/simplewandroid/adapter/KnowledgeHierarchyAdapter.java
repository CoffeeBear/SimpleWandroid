package com.xy.simplewandroid.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.bean.KnowledgeHierarchyData;

import java.util.List;

import androidx.annotation.Nullable;

public class KnowledgeHierarchyAdapter extends BaseQuickAdapter<KnowledgeHierarchyData,BaseViewHolder> {
    public KnowledgeHierarchyAdapter(int layoutResId, @Nullable List<KnowledgeHierarchyData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeHierarchyData item) {
        if(item.getName() == null) {
            return;
        }
        helper.setText(R.id.item_knowledge_hierarchy_title, item.getName());
        helper.setTextColor(R.id.item_knowledge_hierarchy_title, Color.parseColor("#3F9FE0"));
        if (item.getChildren() == null) {
            return;
        }
        StringBuilder content = new StringBuilder();
        for (KnowledgeHierarchyData data: item.getChildren()) {
            content.append(data.getName()).append("   ");
        }
        helper.setText(R.id.item_knowledge_hierarchy_content, content.toString());
    }
}

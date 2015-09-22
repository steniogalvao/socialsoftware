package br.com.vsgdev.socialsoftware.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Charity;

/**
 * Created by root on 9/17/15.
 */
public class CharityAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    private ArrayList<Charity> charities;
    public InstituitionAdapter listAdapter;


    public CharityAdapter(Activity activity, ArrayList<Charity> charities) {
        this.activity = activity;
        this.charities = charities;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getGroupCount() {
        return charities.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return charities.get(groupPosition).getInstitutions().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return charities.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return charities.get(groupPosition).getInstitutions().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        GroupHolder groupHolder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.expandable_charity_layout, null);
            groupHolder = new GroupHolder();
            groupHolder.tvCharityNameHolder = (TextView) view.findViewById(R.id.tv_charity_expandable_charity_layout);
            view.setTag(groupHolder);
        } else {
            view = convertView;
            groupHolder = (GroupHolder) view.getTag();
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder;
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_charity, null);
            childHolder = new ChildHolder();
            Charity charity = (Charity) getGroup(groupPosition);
            listAdapter = new InstituitionAdapter(activity.getApplicationContext(), charity.getInstitutions());
            childHolder.lvInstitutiesHolder = (ListView) view.findViewById(R.id.lv_charity_charity_list);
            childHolder.lvInstitutiesHolder.setAdapter(listAdapter);
            view.setTag(childHolder);
        } else {
            view = convertView;
            childHolder = (ChildHolder) view.getTag();
        }
        System.out.println("child pos" + childPosition);
        return view;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupHolder {
        TextView tvCharityNameHolder;
    }

    private class ChildHolder {
        ListView lvInstitutiesHolder;
    }
}

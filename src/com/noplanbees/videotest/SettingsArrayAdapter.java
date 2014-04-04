package com.noplanbees.videotest;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SettingsArrayAdapter extends ArrayAdapter<String[]> {
	private final Context context;
	private final String[][] values;
 
	public SettingsArrayAdapter(Context context, String[][] values) {
		super(context, R.layout.settings, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.settings, parent, false);
		TextView title = (TextView) rowView.findViewById(R.id.title);
		TextView content = (TextView) rowView.findViewById(R.id.content);
		title.setText(values[position][0]);
		content.setText(values[position][1]);
		return rowView;
	}
}


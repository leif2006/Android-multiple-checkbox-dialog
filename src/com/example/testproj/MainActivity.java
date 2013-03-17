package com.example.testproj;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements ArrayAdapterClickHelper {

	ArrayList<String> content;
	ArrayList<Integer> selectedData;
	ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		content = new ArrayList<String>();
		content.add("abc");
		content.add("xyz");
		setContentView(R.layout.activity_main);

		Button popupList = (Button) findViewById(R.id.button1);
		popupList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupMultipleCheckBoxDialog(content);
			}
		});

	}

	/**
	 * This would popup dialog with list of input data
	 */
	protected void popupMultipleCheckBoxDialog(ArrayList<String> totalData) {

		selectedData = new ArrayList<Integer>();

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		View view = inflater.inflate(R.layout.list_item, null);
		listview = (ListView) view.findViewById(R.id.listItems);
		MultipleCheckBoxAdapter adapter = new MultipleCheckBoxAdapter(this,
				R.layout.listdata, totalData, this);
		listview.setAdapter(adapter);

		Dialog dialog = builder
				.setView(view)
				.setPositiveButton(R.string.action_settings,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								onPositiveButtonClick(selectedData);
							}
						})
				.setNegativeButton(R.string.action_settings,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
							}

						}).create();
		dialog.show();

	}

	@Override
	public void clickDelegate(int position, boolean checked) {
		if (checked) {
			selectedData.add(position);
		} else {
			selectedData.remove(Integer.valueOf(position));

		}
	}

	@Override
	public void onPositiveButtonClick(ArrayList<Integer> selectedData) {

	}

}

package com.ameron32.knbasic.core;

import com.ameron32.knbasic.core.Loader.Fonts;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends MasterActivity {

	// random demonstration textview for custom fonts
	TextView tv;
	// store the current font sequence, needed for "Cycle Font"
	private int currentFont = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tv = (TextView) findViewById(R.id.tvHello);
		
		// create and configure new custom settings buttons in the sliding menu
		addMenuButton("Cycle Font", 2, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int max = Fonts.values().length;
				int newFont = 0;
				if (currentFont != max - 1) {
					currentFont++;
					newFont = currentFont;
				} else {
					currentFont = 0;
				}

				tv.setTypeface(Loader.fonts.get(Fonts.values()[newFont]));
			}
		});
		addMenuButton("Settings", 1, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showMessage("Not yet implemented", true);
			}
		});
		
		// demo textview font-switch
		tv.setTypeface(Loader.fonts.get(Fonts.temphisdirty));
		
		// change the font of the titlebar in ActionBar Sherlock
		getCustomTitle().setTypeface(Loader.fonts.get(Fonts.temphisdirty));
		getCustomTitle().setTextSize(getCustomTitle().getTextSize() * 1.5f);
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();

		// Testing commands (comment out or delete prior to production)
		performTest();
	}

	// ------------------------------------------------------------------------------------------------
	// DUMMY DATA FOR LISTVIEW
	// ------------------------------------------------------------------------------------------------

	private void performTest() {
		fillDummyData();
	}

	private void fillDummyData() {
		final String[] dummyList = new String[] { "Item 1", "Item 2", "Item 3",
				"Item 4", "Item 5" };

		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dummyList));

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				showMessage(item, false);
			}
		});
	}
}

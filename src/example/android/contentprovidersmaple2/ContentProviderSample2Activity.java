package example.android.contentprovidersmaple2;

import android.os.Bundle;
import android.provider.CallLog;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ContentProviderSample2Activity extends Activity {

	TableLayout tablelayout = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_provider_sample2);

		//テーブルレイアウト取得
		tablelayout = (TableLayout)findViewById(R.id.tl_tablelayout);

		try {
			//データ取得
			Cursor cur
					= getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);

			//データが取得できた場合
			if(cur.getCount()!=0){
				//取得したデータ表示
				while (cur.moveToNext()) {
					String name = cur.getString(
							cur.getColumnIndex( CallLog.Calls.NUMBER));
					setItems(name);
				}
			}else{
				TextView message = new TextView(this);
				message.setText("データが取得できませんでした");
				LinearLayout linearlayout = (LinearLayout)findViewById(R.id.ll_linerlayout);
				linearlayout.addView(message);
			}
		} catch (Exception e) {
			Log.e("ERROR", e.getMessage());
		}
	}

	private void setItems(String name) {
		// 名前を設定
		TableRow row = new TableRow(this);
		TextView displayName = new TextView(this);

		displayName.setText(name);
		row.addView(displayName);
		//テーブルレイアウトに設定
		tablelayout.addView(row);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_content_provider_sample2,
				menu);
		return true;
	}

}

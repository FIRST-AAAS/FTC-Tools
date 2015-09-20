
package org.aaas.stem.first.ftc;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.qualcomm.ftcrobotcontroller.R;

import org.aaas.stem.first.ftc.AAASOpModeRegister;
import org.aaas.stem.first.ftc.opmodes.AAASOpMode;
import org.aaas.stem.first.ftc.opmodes.AutoRotateOp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotControllerDriverOpModesActivity extends Activity {

  public static final String SHARED_PREY_KEY = "sharedPrefKey";
  private Map<String,String> opModeNameMap;
  private ListView listView;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ftc_controller_driver_opmodes);


    }

    @Override
    protected void onStart() {
        super.onStart();

        listView = (ListView) findViewById(R.id.list);

        opModeNameMap = new HashMap<String,String>();

        List<Class<? extends AAASOpMode>> regClasses = AAASOpModeRegister.getOpModeClasses();
        if (regClasses != null ) {
            initOpModeSelector(regClasses);

        }
    }

    private void initOpModeSelector(List<Class<? extends AAASOpMode>> regClasses) {


        String[] values = new String[regClasses.size()];
        int i = 0;

        for ( Class opMode : regClasses) {
            values[i++] = opMode.getSimpleName();
            opModeNameMap.put(opMode.getSimpleName() ,opMode.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                int itemPosition     = position;

                String  itemValue    = (String) listView.getItemAtPosition(position);

                SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREY_KEY, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("opModeName", opModeNameMap.get(itemValue));
                editor.commit();

                startActivity(new Intent(getBaseContext(), RobotControllerDriverActivity.class));
                finish();


            }

        });
    }
}

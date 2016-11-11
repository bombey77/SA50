package bombey77.sa50;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String LLCOLOR = "llColor";
    private static final String TEXT = "text";
    private static final String PBSTATUS = "pbStatus";

    private final int[] status = {89, 19, 73, 48, 13, 17, 74, 85, 6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> m;
        for (int i = 0; i < status.length; i++) {
            m = new HashMap<String, Object>();
            m.put(TEXT, "Day " + (i + 1) + ". Load " + status[i] + "%.");
            m.put(LLCOLOR, status[i]);
            m.put(PBSTATUS, status[i]);
            data.add(m);
        }

        String[] from = {TEXT, LLCOLOR, PBSTATUS};
        int[] to = {R.id.tvLoad, R.id.llValue, R.id.pbValue};

        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        adapter.setViewBinder(new MyViewBinder());

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);



    }

    class MyViewBinder implements SimpleAdapter.ViewBinder {

        int green = getResources().getColor(R.color.colorGreen);
        int orange = getResources().getColor(R.color.colorOrange);
        int red = getResources().getColor(R.color.colorRed);

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {

            int i = 0;
            switch (view.getId()) {
                case R.id.llValue:
                    i = ((Integer) data).intValue();
                    if (i < 40) view.setBackgroundColor(green);
                    else if (i < 70) view.setBackgroundColor(orange);
                    else view.setBackgroundColor(red);
                    return true;
                case R.id.pbValue:
                    i = ((Integer)data).intValue();
                    ((ProgressBar)view).setProgress(i);
                    return true;
            }
            return false;

        }
    }

}

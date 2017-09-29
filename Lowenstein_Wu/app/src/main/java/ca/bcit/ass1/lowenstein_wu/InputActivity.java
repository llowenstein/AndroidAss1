package ca.bcit.ass1.lowenstein_wu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class InputActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button convertButton = (Button) findViewById(R.id.convert);
        convertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onConvert(view);
            }
        });
    }

    public void onConvert(View view){
        //find the current value of userValue (string given by user)
        TextView valueView = (TextView) findViewById(R.id.userValue);

        //find the position fo the conversion, using its ID
        Spinner spin = (Spinner) findViewById(R.id.conversionOptions);

        //create intent
        Intent intent = new Intent(this, OutputActivity.class);
        //create bundle
        Bundle bundle = new Bundle();

        //add data to bundle
        String value;
        int position;
        try {
            value = valueView.getText().toString();
            position = spin.getSelectedItemPosition();
            bundle.putDouble("originalValue", Double.parseDouble(value));
            bundle.putInt("position", position);
        } catch (Exception e) {
            System.out.println("User must input a value or something wrong.");
            return;
        }

        //insert the bundle into the intent
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

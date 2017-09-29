package ca.bcit.ass1.lowenstein_wu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class OutputActivity extends Activity {

    public static final double K_TO_P = 2.20462;
    public static final double P_TO_K = 0.453592;
    public static final double K_TO_S = 0.157473;
    public static final double S_TO_K = 6.35029;
    public static final double G_TO_O = 0.035274;
    public static final double O_TO_G = 28.3495;



    private int userValue;
    private int convertPos;
    private double converted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        converted = 0;
        userValue = 0;

        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(OutputActivity.this, InputActivity.class));
            }
        });

        //retrieve intent
        Intent intent = getIntent();
        //retrieve data from intent
        Bundle bundle = intent.getExtras();
        //calls a method to extract the items from the bundle and save in the class variables
        extractItems(bundle, intent);
        convertValue();
    }

    private void extractItems(Bundle bundle, Intent intent){
        userValue = bundle.getInt("originalValue");

        convertPos = bundle.getInt("position");

        //find the original value item through its ID, and set the new value
        TextView value = (TextView) findViewById(R.id.original);
        value.setText("" + userValue);

        TextView to = (TextView) findViewById(R.id.conversion);
        Context con = getApplicationContext();
        String[] titles = con.getResources().getStringArray(R.array.conversion);
        to.setText(titles[convertPos]);
    }


    public void convertValue(){
        if(convertPos == 0){
            converted = userValue*K_TO_P;
        } else if(convertPos == 1){
            converted = userValue*P_TO_K;
        } else if(convertPos == 2){
            converted = userValue*K_TO_S;
        } else if(convertPos == 3){
            converted = userValue*S_TO_K;
        } else if(convertPos == 4){
            converted = userValue*G_TO_O;
        } else if(convertPos == 5){
            converted = (userValue*O_TO_G);
        }
        DecimalFormat format = new DecimalFormat("0.00");
        TextView value = (TextView) findViewById(R.id.converted);
        value.setText("" + format.format(converted));
    }
}

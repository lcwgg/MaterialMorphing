package test.com.materialmorphing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by laetitia on 9/9/15.
 */
public class HomeActivity extends Activity {

    private Button mScaleButton;
    private Button mMorphButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mScaleButton = (Button) findViewById(R.id.scale);
        mMorphButton = (Button) findViewById(R.id.morph);

        mScaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ScalingActivity.class));
            }
        });

        mMorphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MorphingActivity.class));
            }
        });
    }
}

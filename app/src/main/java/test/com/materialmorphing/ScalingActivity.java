package test.com.materialmorphing;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ScalingActivity extends Activity {


    private final int ANIMATION_INTERVAL_MS = 600;
    private final int ANIMATION_DURATION_MS = 700;
    private final int ANIMATION_DELAY_MS = 400;

    private CardView mCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);
        mCardView = (CardView) findViewById(R.id.card);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMaterialAnimation();
            }
        });
    }

    private void doMaterialAnimation() {
//        mCardView.setPivotX(0);
//        mCardView.setPivotY(0);

        // scale width
//        ValueAnimator scaleXTwice = ObjectAnimator.ofFloat(mCardView, View.SCALE_X, 1f, 2f); // A set of values that the animation will animate between over time.
//
//        // scale width and height
//        ValueAnimator scaleXThreeTimes = ObjectAnimator.ofFloat(mCardView, View.SCALE_X, 2f, 3f); // A set of values that the animation will animate between over time.
//        scaleXThreeTimes.setStartDelay(ANIMATION_INTERVAL_MS + ANIMATION_DELAY_MS);
//        ValueAnimator scaleYThreeTimes = ObjectAnimator.ofFloat(mCardView, View.SCALE_Y, 1f, 3f); // A set of values that the animation will animate between over time.
//        scaleYThreeTimes.setStartDelay(ANIMATION_INTERVAL_MS);
//
//        // go back to original size
//        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(View.SCALE_X, 3f, 1f);
//        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 3f, 1f);
//        ObjectAnimator scaleToOrig = ObjectAnimator.ofPropertyValuesHolder(mCardView, holderX, holderY);
//
//        AnimatorSet aset = new AnimatorSet();
//        aset.play(scaleXTwice).before(scaleYThreeTimes).with(scaleXThreeTimes);
//        aset.play(scaleToOrig).after(scaleYThreeTimes);
//
//        aset.setDuration(ANIMATION_DURATION_MS);
//        aset.setInterpolator(new PathInterpolator(.75f, 0, .25f, 1)); // see http://cubic-bezier.com/#.75,0,.25,1
//        aset.start();


        AnimatorSet aset = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.animation);
        aset.setTarget(mCardView);
        aset.start();
    }

}

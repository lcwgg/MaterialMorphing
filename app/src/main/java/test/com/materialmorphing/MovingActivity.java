package test.com.materialmorphing;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.PathInterpolator;

/**
 * Created by laetitia on 9/15/15.
 */
public class MovingActivity extends Activity {

    private final int ANIMATION_INTERVAL_MS = 600;
    private final int ANIMATION_DURATION_MS = 700;

    private final int X_ORIG_POSITION = 0;
    private final int X_UP_POSITION = 350;
    private final int Y_ORIG_POSITION = 0;
    private final int Y_UP_POSITION = -540;
    private final int Z_ORIG_POSITION = 0;
    private final int Z_UP_POSITION = 50;
    private final int ORIG_ANGLE = 0;
    private final int QUARTER_ANGLE = 90;

    private CardView mCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        mCardView = (CardView) findViewById(R.id.card);
        mCardView.setScaleX(0);
        mCardView.setScaleY(0);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMaterialAnimation();
            }
        });
    }

    private void doMaterialAnimation() {

        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0, 1);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0, 1);
        ObjectAnimator appear = ObjectAnimator.ofPropertyValuesHolder(mCardView, holderX, holderY);
        appear.setInterpolator(new PathInterpolator(.75f, 0, .25f, 1));
        appear.setDuration(ANIMATION_DURATION_MS / 2);

        holderX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, X_ORIG_POSITION, X_UP_POSITION);
        holderY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, Y_ORIG_POSITION, Y_UP_POSITION);
        PropertyValuesHolder holderAngle = PropertyValuesHolder.ofFloat(View.ROTATION, ORIG_ANGLE, QUARTER_ANGLE);
        final ObjectAnimator translateUp = ObjectAnimator.ofPropertyValuesHolder(mCardView, holderX, holderY, holderAngle);
        translateUp.setInterpolator(new PathInterpolator(0, 0, .58f, 1)); // see : http://cubic-bezier.com/#0,0,.58,1
        translateUp.setStartDelay(ANIMATION_INTERVAL_MS);
        translateUp.setDuration(ANIMATION_DURATION_MS);

        final ObjectAnimator zTranslationUp = ObjectAnimator.ofFloat(mCardView, View.TRANSLATION_Z, Z_ORIG_POSITION, Z_UP_POSITION);
        zTranslationUp.setStartDelay(ANIMATION_INTERVAL_MS);
        zTranslationUp.setDuration(ANIMATION_DURATION_MS / 2);

        final ObjectAnimator zTranslationDown = ObjectAnimator.ofFloat(mCardView, View.TRANSLATION_Z, Z_UP_POSITION, Z_ORIG_POSITION);
        zTranslationDown.setStartDelay(ANIMATION_INTERVAL_MS);
        zTranslationDown.setDuration(ANIMATION_DURATION_MS / 2);

        holderX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, X_UP_POSITION, X_ORIG_POSITION);
        holderY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, Y_UP_POSITION, Y_ORIG_POSITION);
        final ObjectAnimator translateDown = ObjectAnimator.ofPropertyValuesHolder(mCardView, holderX, holderY, holderAngle);
        translateDown.setInterpolator(new PathInterpolator(0, 0, .58f, 1)); // see : http://cubic-bezier.com/#0,0,.58,1
        translateDown.setStartDelay(ANIMATION_INTERVAL_MS);
        translateDown.setDuration(ANIMATION_DURATION_MS);

        holderX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 0);
        holderY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 0);
        final ObjectAnimator disappear = ObjectAnimator.ofPropertyValuesHolder(mCardView, holderX, holderY);
        disappear.setStartDelay(ANIMATION_INTERVAL_MS);
        disappear.setInterpolator(new PathInterpolator(.75f, 0, .25f, 1));
        disappear.setDuration(ANIMATION_DURATION_MS / 2);

        AnimatorSet aset = new AnimatorSet();
        aset.playSequentially(appear, translateUp, zTranslationUp, zTranslationDown, translateDown, disappear);
        aset.start();
    }
}

package test.com.materialmorphing;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.PathInterpolator;

/**
 * Created by laetitia on 9/9/15.
 */
public class MorphingActivity extends Activity {

    private final int ANIMATION_INTERVAL_MS = 600;
    private final int ANIMATION_DURATION_MS = 700;

    private CardView mCardView;

    int mOrigSize;
    int mOrigRadius;
    int mTargetRadius1;
    int mTargetRadius2;
    int mTargetRadius3;

    int mTargetSize1;
    int mTargetSize2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morphing);
        mCardView = (CardView) findViewById(R.id.card);

        mOrigSize = getResources().getDimensionPixelSize(R.dimen.morphing_original_size);
        mOrigRadius = getResources().getDimensionPixelSize(R.dimen.morphing_original_radius);

        mTargetSize1 = mOrigSize * 2;
        mTargetSize2 = mOrigSize * 4;

        mTargetRadius1 = getResources().getDimensionPixelSize(R.dimen.morphing_target_radius_1);
        mTargetRadius2 = mOrigRadius * 4;
        mTargetRadius3 = getResources().getDimensionPixelSize(R.dimen.morphing_target_radius_2);


        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMaterialAnimation();
            }
        });
    }

    private void doMaterialAnimation() {

        // From circle to small square
        ValueAnimator toSmallSquare = ObjectAnimator.ofFloat(1, 0); // A set of values that the animation will animate between over time.
        toSmallSquare.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Redraw the cardview on each update
                transformMaterial(mOrigSize, mTargetSize1, mOrigSize, mTargetSize1, mOrigRadius, mTargetRadius1, animation);
            }
        });

        // From small square to big circle
        ValueAnimator toBigCircle = ObjectAnimator.ofFloat(1, 0); // A set of values that the animation will animate between over time.
        toBigCircle.setStartDelay(ANIMATION_INTERVAL_MS);
        toBigCircle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Redraw the cardview on each update
                transformMaterial(mTargetSize1, mTargetSize2, mTargetSize1, mTargetSize2, mTargetRadius1, mTargetRadius2, animation);
            }
        });

        // From big circle to small rectangle
        ValueAnimator toSmallRectangle = ObjectAnimator.ofFloat(1, 0); // A set of values that the animation will animate between over time.
        toSmallRectangle.setStartDelay(ANIMATION_INTERVAL_MS);
        toSmallRectangle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Redraw the cardview on each update
                transformMaterial(mTargetSize2, mTargetSize1, mTargetSize2, mOrigSize, mTargetRadius2, mTargetRadius3, animation);
            }
        });

        // From small rectangle to circle
        ValueAnimator toCircle = ObjectAnimator.ofFloat(1, 0); // A set of values that the animation will animate between over time.
        toCircle.setStartDelay(ANIMATION_INTERVAL_MS);
        toCircle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Redraw the cardview on each update
                transformMaterial(mTargetSize1, mOrigSize, mOrigSize, mOrigSize, mTargetRadius3, mOrigRadius, animation);
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(toSmallSquare, toBigCircle, toSmallRectangle, toCircle);
        animatorSet.setDuration(ANIMATION_DURATION_MS);
        animatorSet.setInterpolator(new PathInterpolator(0.75f, 0, 0.25f, 1f)); // see http://cubic-bezier.com/#.75,0,.25,1
        animatorSet.start();
    }

    private void transformMaterial(int origWidth,
                                   int targetWidth,
                                   int origHeight,
                                   int targetHeight,
                                   int origRadius,
                                   int targetRadius,
                                   ValueAnimator animation) {

        float fraction = (float) animation.getAnimatedValue();
        // update the cardView rounded corners
        mCardView.setRadius(interpolate(origRadius, targetRadius, fraction));

        // update cardview size
        if (origWidth != targetWidth) {
            mCardView.getLayoutParams().width = (int) ((targetWidth - origWidth) * (1 - fraction) + origWidth);
        }
        if (origHeight != targetHeight) {
            mCardView.getLayoutParams().height = (int) ((targetHeight - origHeight) * (1 - fraction) + origWidth);
        }

        // request the cardview to redraw
        mCardView.requestLayout();
    }

    private float interpolate(int from, int to, float fraction) {
        return ((from - to) * fraction) + to;
    }

}

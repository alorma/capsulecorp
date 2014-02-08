package cat.alorma.capsulecorp.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsulecorp.library.type.Type;
import cat.alorma.capsulecorp.library.type.TypeFactory;

/**
 * Created by Adria on 8/02/14.
 */
public class DispenserAnimatedView extends DispenserView{

    public DispenserAnimatedView(Context context) {
        super(context);
        init();
    }

    public DispenserAnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DispenserAnimatedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private int seg = 2;
    private void init(){
        Timer myTimer = new Timer();
        UpdateCapsulesTask task = new UpdateCapsulesTask();
        myTimer.schedule(task, 0, seg * 1000);
        task.run();
    }
    private ArrayList<Capsule> allCapsules;
    public void addCapsule(Capsule capsule) {
        if(allCapsules == null){
            allCapsules = new ArrayList<Capsule>();
        }
        if(capsule != null){
            capsule.setCapsuleListener(this);
            allCapsules.add(capsule);
        }
    }

    class UpdateCapsulesTask extends TimerTask {
        public void run() {
            capsules = new ArrayList<Capsule>();
            if(concretType != null){
                int count = concretType.size();
                for(int i = 0 ; i < count; i++){
                    int num = Math.round((float)(Math.random() * allCapsules.size()-1));
                    num = num >= 0 ? num : 0;
                    capsules.add(allCapsules.get(num));
                }
            }
            DispenserAnimatedView.this.postInvalidate();
        }
    }





}
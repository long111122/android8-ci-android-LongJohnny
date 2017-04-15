package techkids.vn.fkinginstrument.touches;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EDGY on 4/15/2017.
 */

public class TouchManager {
    public static List<Touch> toTouches(MotionEvent event){
        int action = event.getActionMasked();
        ArrayList<Touch> touches = new ArrayList<>();
        if(action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN || action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP){
            int pointerIndex = event.getActionIndex();
            float pointerX = event.getX(pointerIndex);
            float pointerY = event.getY(pointerIndex);
            int pointerId = event.getPointerId(pointerIndex);
            touches.add(new Touch(pointerX, pointerY, pointerId, action));
        } else if(action == MotionEvent.ACTION_MOVE) {
            for(int pointerIndex = 0; pointerIndex < event.getPointerCount(); pointerIndex++){
                float pointerX = event.getX(pointerIndex);
                float pointerY = event.getY(pointerIndex);
                int pointerId = event.getPointerId(pointerIndex);
                touches.add(new Touch(pointerX, pointerY, pointerId, action));
            }
        }
        return touches;
    }

}

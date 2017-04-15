package techkids.vn.fkinginstrument.touches;

import android.view.View;

/**
 * Created by EDGY on 4/15/2017.
 */

public class Touch {
    private float x;
    private float y;
    private int id;
    private int action;

    public Touch(float x, float y, int id, int action) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public int getAction() {
        return action;
    }

    public boolean isInside(View view){
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int right = left + view.getWidth();
        int top = location[1];
        int bottom = top + view.getHeight();
        return (x > left && x < right && y > top && y < bottom);
    }
}

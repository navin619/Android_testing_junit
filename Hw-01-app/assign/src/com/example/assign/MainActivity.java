package com.example.assign;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener,
GestureDetector.OnDoubleTapListener{
	//The centerX and centerY coordinates describe the center co-ordinates of the screen.
	private float centerX,centerY;
	///TextView Width and height 
	private float textViewWidth,textViewHeight;
	/// Relative layout width and height
	private float rLayoutWidth,rlayoutHeight;
	//Text view object Containing the Assignment Text
	private TextView assignmentText,myTextView;
	// Relative layout object to figure out the center co-ordinates
	private RelativeLayout relativeLayout;
	//A gesture detector object to call the gesture listener class 
	private GestureDetectorCompat gestureDetector;

	@Override
	/**
	 * The oncreate functions creates all the layout variable
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		///Sets the Main content
		setContentView(R.layout.activity_main);
		///Assignment text assigned with the textview object
		assignmentText=(TextView)findViewById(R.id.gestureStatusText);
		///Gesture detector object
		this.gestureDetector = new GestureDetectorCompat(this,this);
		///Setting on double tap listner
		gestureDetector.setOnDoubleTapListener(this);
		///setting up relative layout object
		relativeLayout = (RelativeLayout) findViewById(R.id.mainlayout);
	}
	/**
	 * This functions detects the occurrence of a touch event 
	 * activates the gesture listener class to check for the type of touch event
	 */
	@Override 
	public boolean onTouchEvent(MotionEvent event) { 
		/// the gesture detector checks for the type of gesture it has to select
		this.gestureDetector.onTouchEvent(event);
		/// Be sure to call the superclass implementation
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * Not implemented
	 */
	@Override
	public boolean onDoubleTap(MotionEvent arg0) {
		///Not Implemeted
		return true;
	}
	/**
	 * Function is called when an double touch event occours
	 * The center coordinates of the screen are calculated and set to the text view 
	 * The countdown timer of 3 seconds is also started setting the assignment text visible
	 * param[in] arg0 Motionevent
	 */
	@Override
	public boolean onDoubleTapEvent(MotionEvent arg0) {
		///Setting the Visibility of the assignment text	
		//assignmentText.setVisibility(TextView.VISIBLE);
		///Process to fetch the center of the coordinates of the screen
		//centerX = relativeLayout.getRight()/2;
		//centerY = relativeLayout.getBottom()/2;
		
		Log.w("main","x,y "+centerX+" "+centerY);
		/// Setting the center co-ordinated for the assignment text
		//assignmentText.setX(centerX- (assignmentText.getMeasuredWidth()/2));
		assignmentText.setX(centerX);
		assignmentText.setY(centerY);
		text_show();
		return true;
	}
	/**
	 * Function is called when there is a Single tap event 
	 * param[in] arg0 Motionevent
	 */
	@Override
	public boolean onSingleTapConfirmed(MotionEvent arg0) {
		///Checks for the visilibity of the text
		if (assignmentText.getVisibility()!= 0)
		{
			text_show();
		}
		else
		{
			assignmentText.setVisibility(TextView.INVISIBLE);
		}	
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * Not implemented
	 */
	@Override
	public boolean onDown(MotionEvent arg0) {
		///Not implememted
		return true;
	}
	/**
	 * Not implemented
	 */
	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		///Not Implemented
		return true;
	}
	/**
	 * Shifts the coordinates of the assignment text to the place when the long press event took place 
	 * the first letter is considered as the press point coordinate when displayed
	 * param[in] arg0 Motionevent
	 */
	@Override
	public void onLongPress(MotionEvent arg0) {
		///Assigning the text width and height to the variables
		textViewWidth = assignmentText.getMeasuredWidth();
		textViewHeight = assignmentText.getMeasuredHeight();
		///Assigning the relative layout width and height to the variables 
		rLayoutWidth = relativeLayout.getMeasuredWidth();
		rlayoutHeight = relativeLayout.getMeasuredHeight();
		///setting the textview with considering the center of the tiextview wigdet as the point of touch
		assignmentText.setX(arg0.getX()
				- (assignmentText.getMeasuredWidth() / 2));

		///to check for the left side truncation
		if (assignmentText.getX() < 0)
		{
			assignmentText.setX(0);
		}
		///to check for truncation on the right hand side
		if (assignmentText.getX() > (rLayoutWidth - textViewWidth))
		{
			assignmentText.setX(rLayoutWidth - textViewWidth);
		}
		///setting the Y coordinates properly as the actual Y coordinates differ from the on screen app Y coordinates
		///Also adjusting the center of the textview as the point of touch
		assignmentText.setY(arg0.getY() - pixelToDpConvert(48 + 25, this)
				- (assignmentText.getMeasuredHeight() / 2));
		///check for the truncation in the top side of the screen
		if (assignmentText.getY() < 0)
		{
			assignmentText.setY(0);
		}
		///check of the truncation in the bottom of the screen
		if (assignmentText.getY() > ( rlayoutHeight - textViewHeight))
		{
			assignmentText.setY(rlayoutHeight - textViewHeight);
		}
		text_show();
	}
	/**
	 * Not implemented method
	 */
	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		///Not Implemented
		return true;
	}
	/**
	 * Not implemented method
	 */
	@Override
	public void onShowPress(MotionEvent arg0) {
		///Not Implemented
	}
	/**
	 * Not implemented method
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		///Not Implemented
		return true;
	}
	public static float pixelToDpConvert(float d_p, Context cont){
		Resources res = cont.getResources();
		DisplayMetrics met = res.getDisplayMetrics();
		float pixel = d_p * (met.densityDpi / 160f);
		return pixel;
	}
	/**
	 * This function Displays the text for 3 Seconds and then makes it Invisible
	 */
	public void text_show() {
		assignmentText.setVisibility(TextView.VISIBLE);
		///The delay of 3 seconds is applied which makes the textview visible for 3 seconds
		Handler timer_handler = new Handler(); 
		timer_handler.postDelayed(new Runnable() { 
			public void run() { 
				assignmentText.setVisibility(TextView.INVISIBLE);
			} 
		}, 3000); 
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		myTextView = (TextView) findViewById(R.id.gestureStatusText);
		if (centerX == 0) {
			centerX = myTextView.getX();
		}
		if (centerY == 0) {
			centerY = myTextView.getY();
		}
	}
}

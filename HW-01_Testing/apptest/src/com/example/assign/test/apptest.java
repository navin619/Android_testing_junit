package com.example.assign.test;

import com.example.assign.MainActivity;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.opengl.Visibility;
import android.os.SystemClock;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class apptest extends ActivityInstrumentationTestCase2<MainActivity> {


	MainActivity mActivity;
	TextView gesture_text;
	String resourceString;
	RelativeLayout relativeLayout;
	float centerX,centerY;
	String expected="Navin Chaganti"+"\n"+"ECE573 HW01";



	public apptest() {
		super("com.example.assign", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mActivity = this.getActivity();
		gesture_text = (TextView) mActivity.findViewById
				(com.example.assign.R.id.gestureStatusText);      
		resourceString = mActivity.getString
				(com.example.assign.R.string.gesture);    
		relativeLayout = (RelativeLayout) mActivity.findViewById
				(com.example.assign.R.id.mainlayout); 
		centerX=gesture_text.getX();
		centerY=gesture_text.getY();

	}



	/**
	 * @requirement: On application start the image should be displayed on the background and the text should be invisible
	 *  
	 */
	/**
	 * Test case 1: textview should be invisible
	 * -check for the textview invisibility
	 */
	public void test_1_On_Start_Visibility_of_Text() {
		/**
		 * Check for the visibility on the start of the app
		 */
		assertEquals(View.INVISIBLE , gesture_text.getVisibility());
	}

	/**
	 * @requirement: On single tap the text should be visible on the center of the screen initally
	 * and the text should contain "Navin Chaganti ECE573 HW01"
	 * Text should be visible
	 * text should disappear after 3 seconds
	 * text should go invisible on 2 taps
	 */
	/**
	 * Test case 2: test Text should be visible
	 * perform single tap and check visibility
	 */
	public void test_2_OnSingleTapConfirmed_textvisible() {

		///Single tap and wait for 2 seconds
		TouchUtils.tapView(this, gesture_text);
		try {
			synchronized (this) {
				wait(2000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		///Check for the visibility of the text
		assertEquals(View.VISIBLE , gesture_text.getVisibility());

	}

	/**
	 * Test case 3: test the content of the text view
	 * test the content of the text view by comparing "Navin Chaganti"+"\n"+"ECE573 HW01"
	 */
	public void test_3_contentoftext() {
		///Singletap
		TouchUtils.tapView(this, gesture_text);
		/// check the content of the text
		assertEquals("Navin Chaganti"+"\n"+"ECE573 HW01", gesture_text.getText());
		//fail("Not yet implemented");
	}

	/**
	 * Test case 4:  test for position of text
	 * check the coordinates of x and y
	 */
	public void test_4_poisitonoftext()
	{
		/// Checking the X and Y co-rdinates of the textview
		assertEquals(centerX, gesture_text.getX());
		assertEquals(centerY, gesture_text.getY());

	}
	
	/**
	 * Test case 5: test if the text disappears after 3 seconds
	 * perform single tap and wait for 4 seconds
	 */
	public void test_5_textdisppears()
	{
		/// Single tap and wait for 4 seconds
		TouchUtils.tapView(this, gesture_text);
		try {
			synchronized (this) {
				wait(4000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/// checking if the text is visible
		assertEquals(View.INVISIBLE , gesture_text.getVisibility());
	}

	
	/**
	 * Test case 6:  test for the text visibility after 2 continuous taps
	 * - perform single tap twice
	 */
	public void test_6_continuous2taps()
	{
		/// single tap
		TouchUtils.tapView(this, gesture_text);
		try {
			synchronized (this) {
				wait(1000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/// single tap
		TouchUtils.tapView(this, gesture_text);
		try {
			synchronized (this) {
				wait(500); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/// checking for the visibility of the text
		assertEquals(View.INVISIBLE , gesture_text.getVisibility());
	}

	/**
	 * @requirements for long press
	 * 1) On long press the position of the text should be the location the long press has occured
	 * 2) The text should be visible 
	 * 3) text should contain "Navin Chaganti ECE573 HW01"
	 * 4)Text should disappear after 3 seconds
	 * 5) rotation of the screen should not change the text display orientation
	 * 6)single tap should function
	 */


	/**
	 * Test case 7: testing the visibility of the text within 2 seconds
	 * -perform a long press on the screen
	 * - test the visibility 
	 */
	public void test_7_textvisible()
	{
		/// The X and Y coordinates of the long press
		float pos_x=relativeLayout.getWidth()/2;
		float pos_y=relativeLayout.getHeight()/2;
		/// Long press and wait for 2 seconds
		Instrumentation Inst_obj = new Instrumentation();
		Inst_obj.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
				SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,pos_x, pos_y, 1));
		try {
			synchronized (this) {
				wait(2000); 
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/// check for the text visibility
		assertEquals(View.VISIBLE , gesture_text.getVisibility());

	}

	/**
	 * Test case 8: testing the text visibility of the text view for 3 seconds
	 * -perform a long press on the screen
	 * - test the visibility of the text view 
	 */
	public void test_8_textdisppearslongpress()
	{
		/// The X and Y coordinates of the long press
		float pos_x=relativeLayout.getWidth()/2;
		float pos_y=relativeLayout.getHeight()/2;
		/// Long press and wait for 2 seconds
		Instrumentation Inst_obj = new Instrumentation();
		Inst_obj.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
				SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,pos_x, pos_y, 1));
		try {
			synchronized (this) {
				wait(4000); 
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/// check the invisibility of the text 
		assertEquals(View.INVISIBLE , gesture_text.getVisibility());

	}

	/**
	 * Test case 9: testing the single tap functionality after performing a long press
	 * -perform a long press on the screen
	 * -test the single tap functioality 
	 */
	public void test_9_singletapin_longpress()
	{
		/// The X and Y coordinates of the long press
		float pos_x=relativeLayout.getWidth()/2;
		float pos_y=relativeLayout.getHeight()/2;
		/// Long press and wait for 1 seconds
		Instrumentation Inst_obj = new Instrumentation();
		Inst_obj.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
				SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,pos_x, pos_y, 1));
		try {
			synchronized (this) {
				wait(1000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/// Single tap and wait for 1sec
		TouchUtils.tapView(this, gesture_text);
		try {
			synchronized (this) {
				wait(1000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//Log.d(TAG, "Waiting didnt work!!");
			e.printStackTrace();
		}
		/// check for the text invisibility
		assertEquals(View.INVISIBLE , gesture_text.getVisibility());
	}

	/**
	 * Test case 10:  testing the text position for the long press by comparing it with its coordinates
	 * -perform a long press on the screen
	 * - compare the textview coordinates
	 */
	public void test_10_textposition()
	{
		/// The X and Y coordinates of the long press
		float pos_x=relativeLayout.getWidth()/2;
		float pos_y=relativeLayout.getHeight()/2;
		/// Long press and wait for 2 seconds
		Instrumentation Inst_obj = new Instrumentation();
		Inst_obj.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
				SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,pos_x, pos_y, 1));
		try {
			synchronized (this) {
				wait(2000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/// check for the x and Y coordinates 
		assertEquals(pos_x- (gesture_text.getMeasuredWidth() / 2), gesture_text.getX(),0.1);
		DisplayMetrics met= mActivity.getResources().getDisplayMetrics();
		float pixel = (48 + 25) * (met.densityDpi / 160f);
		assertEquals(pos_y-pixel- (gesture_text.getMeasuredHeight() / 2), gesture_text.getY(),0.1);
	}

	/**
	 * @ requirements :entirety test
	 * 1)the textview should always be visible and should not get cut on the extreme ends.
	 */
	
	/**
	 * Test case 11: testing entirety of the text view on the left hand side
	 * -perform a long press on the extreme left
	 * -check the cordinates of the textview 
	 */
	public void test_11_entirety_left()
	{
		/// The X and Y coordinates of the long press
		float pos_x=10;
		float pos_y=relativeLayout.getHeight()/2;
		/// Long press and wait for 2 seconds
		Instrumentation Inst_obj = new Instrumentation();
		Inst_obj.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
				SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,pos_x, pos_y, 1));
		try {
			synchronized (this) {
				wait(2000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/// check for the entirety  of the text view by checking its position
		assertTrue(pos_x>gesture_text.getX());
	}
	/**
	 * Test case 12: testing entirety of the text view on the right hand side
	 * -perform a long press on the extreme right
	 * -check the cordinates of the textview 
	 */
	public void test_12_entirety_right()
	{
		/// The X and Y coordinates of the long press
		float pos_x=relativeLayout.getWidth();
		float pos_y=relativeLayout.getHeight()/2;
		/// Long press and wait for 2 seconds
		Instrumentation Inst_obj = new Instrumentation();
		Inst_obj.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
				SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,pos_x, pos_y, 1));
		try {
			synchronized (this) {
				wait(2000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			synchronized (this) {
				wait(1000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/// Check the positon of the text view 
		assertTrue(gesture_text.getX()<pos_x);
	}

	/**
	 * Double tap test cases:
	 * @requirement: Double tapping an anywhere on the screen should cause the text to
	 * come at the center 
	 * 1)text should come in the center 
	 * 2)text should be visible for 3 seconds
	 * 3) text should contain the earlier text
	 */

	/**
	 * Test case 13:
	 * -perform a long press
	 * -implement a double tap on the long press coordinates
	 * -check the visibility of the text view 
	 */
	public void test_13_double_tap_text_visibility()
	{
		/// The X and Y coordinates of the long press
		float pos_x=relativeLayout.getWidth()/2;
		float pos_y=relativeLayout.getHeight()/2;
		/// Long press and wait for  500 mili seconds
		Instrumentation Inst_obj = new Instrumentation();
		Inst_obj.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
				SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,pos_x, pos_y, 0));
		try {
			synchronized (this) {
				wait(1000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/// double tap
		TouchUtils.tapView(this, gesture_text);
		try {
			synchronized (this) {
				wait(50); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		TouchUtils.tapView(this, gesture_text);

		/// wait for 500 mili sec
		try {
			synchronized (this) {
				wait(500); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/// check for the text visibiity
		assertEquals(View.VISIBLE , gesture_text.getVisibility());
	}

	/**
	 * Test case 14:
	 * -perform a long press
	 * -implement a double tap on the long press coordinates
	 * -check the textview is located at the center of the screen 
	 */
	public void test_14_double_tap_text_centerposition()
	{
		/// The X and Y coordinates of the long press
		float pos_x=relativeLayout.getWidth()/2;
		float pos_y=relativeLayout.getHeight()/2;
		/// Long press and wait for 1 seconds
		Instrumentation Inst_obj = new Instrumentation();
		Inst_obj.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
				SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,pos_x, pos_y, 1));
		try {
			synchronized (this) {
				wait(1000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/// single tap  and wait for 50 mili sec
		TouchUtils.tapView(this, gesture_text);
		try {
			synchronized (this) {
				wait(50); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/// single tap again with wait for a double tap
		TouchUtils.tapView(this, gesture_text);
		try {
			synchronized (this) {
				wait(1000); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//Log.d(TAG, "Waiting didnt work!!");
			e.printStackTrace();
		} 

		assertEquals(centerX,gesture_text.getX(),2.0);
		assertEquals(centerY,gesture_text.getY());
	}

	/**
	 * Test case 15 : Conent of the textview on double tap
	 * -perform a long press
	 * -implement a double tap on the long press coordinates
	 * -check the context of the textview
	 */
	public void test_15_doubletap_textcontent()
	{
		/// The X and Y coordinates of the long press
		float pos_x=relativeLayout.getWidth()/2;
		float pos_y=relativeLayout.getHeight()/2;
		/// Long press and wait for 2 seconds
		Instrumentation Inst_obj = new Instrumentation();
		Inst_obj.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
				SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,pos_x, pos_y, 1));
		try {
			synchronized (this) {
				wait(500); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//Log.d(TAG, "Waiting didnt work!!");
			e.printStackTrace();
		} 
		// double tap
		TouchUtils.tapView(this, gesture_text);
		TouchUtils.tapView(this, gesture_text);


		try {
			synchronized (this) {
				wait(500); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//Log.d(TAG, "Waiting didnt work!!");
			e.printStackTrace();
		} 

		assertEquals("Navin Chaganti"+"\n"+"ECE573 HW01", gesture_text.getText());
	}

}

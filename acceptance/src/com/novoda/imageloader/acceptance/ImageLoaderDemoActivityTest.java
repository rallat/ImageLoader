package com.novoda.imageloader.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.ListView;

import com.jayway.android.robotium.solo.Solo;
import com.novoda.imageloader.demo.ImageLoaderDemoActivity;

public class ImageLoaderDemoActivityTest extends ActivityInstrumentationTestCase2<ImageLoaderDemoActivity>{

	private static final int QUICK = 2;
	private static final int NORMAL = 7;
	
	private Solo robotium;
	private ListView list;

	public ImageLoaderDemoActivityTest() {
		super("com.novoda.imageloader.demo", ImageLoaderDemoActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		robotium = new Solo(getInstrumentation(), getActivity());
		list = getActivity().getListView();
	}
	
	public void testOpenTheApp(){
		robotium = new Solo(getInstrumentation(), getActivity());
		assertNotNull(robotium);
	}
	
	@LargeTest
	public void ignore_testScrollingDownToBottomAndBackToTopQuickly(){
		try{
			scrollToBottom(QUICK);
			scrollToTop(QUICK);
		}catch (OutOfMemoryError e){
			fail();
		}
	}
	
	@LargeTest
	public void ignore_testScrollingDownToBottomAndBackToTopSlowlyToLoadAllImages(){
		try{
			scrollToBottom(NORMAL);
			scrollToTop(NORMAL);
		}catch (OutOfMemoryError e){
			fail();
		}
	}
	
	private void scrollToBottom(int stepCount) {
		while(!isListAtTheBottom()){
			robotium.drag(1, 1, 200, 100, stepCount);
		}
	}
	
	private void scrollToTop(int stepCount){
		while(!isListAtTheTop()){
			robotium.drag(1, 1, 100, 200, stepCount);
		}
	}
	
	private boolean isListAtTheTop() {
		return list.getFirstVisiblePosition() == 0;
	}

	private boolean isListAtTheBottom(){
		return list.getLastVisiblePosition() == (list.getCount() - 1);
	}

	public void startNextActivity(){
//		btn = getActivity().getString(R.string.)
//		robotium.clickOnButton(btn);
	}

}

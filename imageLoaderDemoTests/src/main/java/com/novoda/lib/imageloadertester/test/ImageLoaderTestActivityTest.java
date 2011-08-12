package com.novoda.lib.imageloadertester.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.jayway.android.robotium.solo.Solo;
import com.novoda.lib.imageloadertester.ImageLoaderTestActivity;

public class ImageLoaderTestActivityTest extends ActivityInstrumentationTestCase2<ImageLoaderTestActivity>{

	private Solo robotium;
	private ListView list;

	public ImageLoaderTestActivityTest() {
		super("com.novoda.lib.imageloadertester",ImageLoaderTestActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		robotium = new Solo(getInstrumentation(), getActivity());
		list = getActivity().getListView();
	}

	public void testScrollingDownToBottomAndBackToTop(){
		try{
			scrollToBottom();
			scrollToTop();
		}catch (OutOfMemoryError e){
			fail();
		}
	}
	
	private void scrollToBottom() {
		while(!isListAtTheBottom()){
			robotium.drag(1, 1, 200, 100, 7);
		}
	}
	
	private void scrollToTop(){
		while(!isListAtTheTop()){
			robotium.drag(1, 1, 100, 200, 7);
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

package com.novoda.imageloader.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.jayway.android.robotium.solo.Solo;
import com.novoda.imageloader.demo.ImageLoaderDemoWithMeaninglessQueryActivity;

public class ImageLoaderDemoWithMeaninglessQueryActivityTest extends
    ActivityInstrumentationTestCase2<ImageLoaderDemoWithMeaninglessQueryActivity> {

  private static final int NORMAL = 7;
  
  private Solo robotium;
  private ListView list;

  public ImageLoaderDemoWithMeaninglessQueryActivityTest() {
    super("com.novoda.imageloader.demo", ImageLoaderDemoWithMeaninglessQueryActivity.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    robotium = new Solo(getInstrumentation(), getActivity());
    list = getActivity().getListView();
  }

  public void testOpenTheApp() {
    robotium = new Solo(getInstrumentation(), getActivity());
    assertNotNull(robotium);
  }

  public void testScrollingDownToBottomAndBackToTopSlowlyToLoadAllImages() {
    try {
      scrollToBottom(NORMAL);
      scrollToTop(NORMAL);
    } catch (OutOfMemoryError e) {
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
//    btn = getActivity().getString(R.string.)
//    robotium.clickOnButton(btn);
  }

}

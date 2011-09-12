package com.novoda.imageloader.core.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import android.content.Context;
import android.content.Intent;

import com.novoda.imageloader.core.service.CacheCleaner;
import com.novoda.imageloader.core.service.CacheCleaner.CacheCleanerUtil;

public class CacheCleanerTest {

  private static final long EXPIRATION_PERIOD = 1500L;
  private Context context;
  private File cacheDir;
  private CacheCleanerUtil cacheCleaner;
  private File testFile;
  private Intent intent;

  @Test
  public void SHOULD_cleanOldFiles_IF_reduceCacheActionIsSet() throws IOException {
    prepareTestFile(System.currentTimeMillis() - EXPIRATION_PERIOD);
    when(intent.hasExtra(CacheCleaner.EXPIRATION_PERIOD_EXTRA)).thenReturn(true);
    when(intent.getLongExtra(CacheCleaner.EXPIRATION_PERIOD_EXTRA, -1)).thenReturn(
        EXPIRATION_PERIOD);

    cacheCleaner.onReceive(context, intent);

    assertFalse(testFile.exists());
  }

  @Test
  public void SHOULD_notCleanNewFiles_WHIT_reduceAction() throws IOException {
    prepareTestFile();
    when(intent.hasExtra(CacheCleaner.EXPIRATION_PERIOD_EXTRA)).thenReturn(true);
    when(intent.getLongExtra(CacheCleaner.EXPIRATION_PERIOD_EXTRA, -1)).thenReturn(
        EXPIRATION_PERIOD);

    cacheCleaner.onReceive(context, intent);

    assertTrue(testFile.exists());
  }

  @Test
  public void SHOULD_cleanUpFilesOnly_IF_theActionIsSet() throws IOException {
    prepareTestFile(System.currentTimeMillis());
    when(intent.getAction()).thenReturn(null);

    cacheCleaner.onReceive(context, intent);

    assertTrue(testFile.exists());
  }

  @Test
  public void SHOULD_cleanUpFilesOnly_IF_theCorrectActionIsSet() throws IOException {
    prepareTestFile(System.currentTimeMillis());
    when(intent.getStringExtra(CacheCleaner.CACHE_DIR_EXTRA)).thenReturn("wrong action");

    cacheCleaner.onReceive(context, intent);

    assertTrue(testFile.exists());
  }

  @Test
  public void SHOULD_cleanAllFiles_IF_cleanCacheActionIsSet() throws IOException {
    prepareTestFile();

    cacheCleaner.onReceive(context, intent);

    assertFalse(testFile.exists());
  }

  @Test
  public void SHOULD_returnWithoutActions_IF_cacheExtraDirIsNull() {
    prepareTestFile();
    when(intent.getStringExtra(CacheCleaner.CACHE_DIR_EXTRA)).thenReturn(null);

    cacheCleaner.onReceive(context, intent);

    assertTrue(testFile.exists());
  }

  @Test
  public void SHOULD_returnWithoutActions_IF_cacheDirExtraIsEmpty() {
    prepareTestFile();
    when(intent.getStringExtra(CacheCleaner.CACHE_DIR_EXTRA)).thenReturn("");

    cacheCleaner.onReceive(context, intent);

    assertTrue(testFile.exists());
  }

  @Test
  public void SHOULD_returnWithoutActions_IF_cacheDirExtraIsNotSet() {
    prepareTestFile();
    when(intent.hasExtra(CacheCleaner.CACHE_DIR_EXTRA)).thenReturn(false);

    cacheCleaner.onReceive(context, intent);

    assertTrue(testFile.exists());
  }

  @Test
  public void SHOULD_notCrash_ON_IntentNull() {
    try {
      cacheCleaner.onReceive(context, null);
    } catch (Exception e) {
      fail("shouldn't fail on null intent");
    }
  }

  @Before
  public void setUp() {
    cacheDir = new File("target/unit-test/tmp");
    cacheDir.mkdir();
    cacheCleaner = new CacheCleanerUtil();
    intent = prepareStandardIntent();
  }

  @After
  public void tearDown() throws IOException {
    FileUtils.deleteDirectory(cacheDir);
  }

  private Intent prepareStandardIntent() {
    Intent i = Mockito.mock(Intent.class);
    when(i.getAction()).thenReturn(CacheCleaner.CLEAN_CACHE_ACTION);
    when(i.hasExtra(CacheCleaner.CACHE_DIR_EXTRA)).thenReturn(true);
    when(i.getStringExtra(CacheCleaner.CACHE_DIR_EXTRA)).thenReturn(cacheDir.getAbsolutePath());
    return i;
  }

  private void prepareTestFile() {
    prepareTestFile(System.currentTimeMillis());
  }

  private void prepareTestFile(long lastModified) {
    try {
      testFile = new File(cacheDir, "1.tmp");
      FileUtils.write(testFile, "test1");
      testFile.setLastModified(lastModified);
      assertTrue(testFile.exists());
    } catch (Exception e) {
      fail("Setup of the file for the test failed " + e.getMessage());
    }
  }

}

package com.openthinks.assist.helpdesk.util;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public final class MapDBHelper {

  private static String storeDBPath;
  private static DB dbDisk;
  private static DB dbMemory;
  private static Lock lock = new ReentrantLock();

  static {
    try {
      setUp(new File(System.getProperty("user.dir")), StaticDict.STORE_DB);
    } catch (Exception e) {
    }
  }

  public static String getStoreDBPath() {
    return storeDBPath;
  }

  /**
   * set location of DB
   * 
   * @param dir File director of DB
   * @param name String DB name
   */
  public static final void setUp(File dir, String name) {
    // File dbFile = new File(WebUtils.getWebClassDir(),
    // StaticDict.STORE_DB);
    File dbFile = new File(dir, name);
    storeDBPath = dbFile.getAbsolutePath();
  }

  public static final void setUp(File dbFile) {
    storeDBPath = dbFile.getAbsolutePath();
  }

  public static final void initialize() {
    lock.lock();
    try {
      if (dbDisk == null || dbDisk.isClosed()) {
        dbDisk = DBMaker.fileDB(storeDBPath).closeOnJvmShutdown().transactionEnable().make();
      }
      if (dbMemory == null || dbMemory.isClosed()) {
        dbMemory = DBMaker.memoryDB().closeOnJvmShutdown().transactionEnable().make();
      }
    } finally {
      lock.unlock();
    }
  }

  public static final void destroy() {
    lock.lock();
    try {
      if (dbMemory != null && !dbMemory.isClosed()) {
        dbMemory.close();
      }
      if (dbDisk != null && !dbDisk.isClosed()) {
        dbDisk.close();
      }
    } finally {
      lock.unlock();
    }
  }

  public static final DB getDiskDB() {
    lock.lock();
    try {
      if (dbDisk == null || dbDisk.isClosed()) {
        dbDisk = DBMaker.fileDB(storeDBPath).closeOnJvmShutdown().transactionEnable().make();
      }
    } finally {
      lock.unlock();
    }
    return dbDisk;
  }

  public static final DB getMemoryDB() {
    lock.lock();
    try {
      if (dbMemory == null || dbMemory.isClosed()) {
        dbMemory = DBMaker.memoryDB().closeOnJvmShutdown().transactionEnable().make();
      }
    } finally {
      lock.unlock();
    }
    return dbMemory;
  }

}

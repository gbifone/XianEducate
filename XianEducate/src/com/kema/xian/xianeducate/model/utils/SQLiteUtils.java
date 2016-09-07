  package com.kema.xian.xianeducate.model.utils;
  
  import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.kema.xian.xianeducate.activity.base.AppConfig;
import com.kema.xian.xianeducate.activity.base.ApplicationContext;
import com.kema.xian.xianeducate.model.db.SQLiteManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.util.Log;


  
  /*
   * This is class is used to manager SQLite database.
   */
  
  /**
   * @author hl.ding
   * 
   */
  public class SQLiteUtils {
    
    	private static SQLiteUtils sqliteOperator;
    
    	private static SQLiteManager manager;
    
    	private static SQLiteDatabase onlyreaddb;
    
    	private static SQLiteDatabase readwritedb;
    
    	private static boolean local = AppConfig.SQLITE_LIBRARY_IS_LOCAL;
    
    	/**
    	 * ���ݿ���
    	 */
    	private static final String DATABASE_NAME = AppConfig.DB_NAME;
    	/**
    	 * ����
    	 */
    	private static final String PACKAGE_NAME = "com.kema.xian.xianeducate";
    	/**
    	 * ���ݿ�·��
    	 */
    	private static final String DB_FILE = "data/"
    			+ Environment.getDataDirectory().getAbsolutePath() + "/"
    			+ PACKAGE_NAME + "/" + DATABASE_NAME;
    	
   
    	
    	/**
    	 * ��ȡ���ݿ��ʵ����
    	 * 
    	 * @param local
    	 *            ���Ϊtrue,���ȡ�ڴ濨������ݣ������false���ȡ�ֻ�/data/dataĿ¼�µ����ݿ��ļ�
    	 * @return
    	 */
    	public static SQLiteUtils getInstance() {
    		if (sqliteOperator == null) {
    			sqliteOperator = new SQLiteUtils();
    			if (!local) {
    				manager = new SQLiteManager(ApplicationContext.mContext,
    						DATABASE_NAME);
    				onlyreaddb = manager.getReadableDatabase();
    				readwritedb = manager.getWritableDatabase();
    				//������
    				createNoticeTB();
    				createQaTB();
    				createHistoryTB();
    				createSchoolAddressTB();
    			
    			} else {

    				File dbFile = new File(DB_FILE);
    				File parent = dbFile.getParentFile();
    				if (!parent.exists()) {
    					parent.mkdirs();
    				}
    				if (!dbFile.exists()) {
    					try {
    						dbFile.createNewFile();
    						InputStream inputStream = ApplicationContext.mContext
    								.getAssets().open(DATABASE_NAME);
    						FileUtils.copyFile(inputStream, dbFile);
    						readwritedb = SQLiteDatabase.openOrCreateDatabase(
    								dbFile, null);
    					} catch (IOException e) {
    						e.printStackTrace();
    					}
    				} else {
    					readwritedb = SQLiteDatabase.openOrCreateDatabase(dbFile,
    							null);
    				}
    			}
    
    		}
    		return sqliteOperator;
    	}
    
    		
    	/**
    	 * �Ѷ����ɿ�
    	 */
    	public void clean() {
    
    		sqliteOperator = null;
    		onlyreaddb = null;
    		readwritedb = null;
    	}
    
    	/**
    	 * ��ȡֻ�����ݿ�
    	 * 
    	 * @return
    	 */
    	public SQLiteDatabase getReadableDatabase() {
    
    		return onlyreaddb;
    	}
    
    	/**
    	 * ��ȡ�ɶ�ȡ�����ݿ�
    	 * 
    	 * @return
    	 */
    	public SQLiteDatabase getWritableDatabase() {
    
    		return readwritedb;
    	}
    
    
    
    	
    	
    
    
    
//    	/**
//    	 * �ж���Ϣ�Ƿ����
//    	 * 
//    	 * @param messageId
//    	 * @return
//    	 */
//    	public boolean isExistMessage(String messageId) {
//    
//    		boolean flag = false;
//    		if (ApplicationContext.userInfo != null
//    				&& !StringUtils.isEmpty(ApplicationContext.userInfo.userId)) {
//    			Cursor cursor = readwritedb.query(AppConfig.DB_TABLE_NOTICE, null,
//    					"messageId=? and userId="
//    							+ ApplicationContext.userInfo.userId,
//    					new String[] { messageId }, null, null, null);
//    			if (cursor != null && cursor.getCount() > 0) {
//    				flag = true;
//    			}
//    			cursor.close();
//    		}
//    		return flag;
//    	}
    
//    	/**
//    	 * ������Ϣ״̬
//    	 */
//    	public void updateNoticeStatus(String messageId) {
//    		if (ApplicationContext.userInfo != null) {
//    
//    			ContentValues contentValues = new ContentValues();
//    			contentValues.put("status", 1);
//    			readwritedb.update(AppConfig.DB_TABLE_NOTICE, contentValues,
//    					"messageId=? and userId="
//    							+ ApplicationContext.userInfo.userId,
//    					new String[] { messageId });
//    		}
//    	}
    
//    	/**
//    	 * ��ȡ��Ϣ״̬
//    	 * 
//    	 * @param messageId
//    	 */
//    	public int getNoticeStatus(String messageId) {
//    		int flag = -1;
//    		if (ApplicationContext.userInfo != null) {
//    			Cursor cursor = readwritedb.query(AppConfig.DB_TABLE_NOTICE, null,
//    					"messageId=? and userId="
//    							+ ApplicationContext.userInfo.userId,
//    					new String[] { messageId }, null, null, null);
//    			if (cursor != null && cursor.getCount() > 0) {
//    				cursor.moveToFirst();
//    				flag = cursor.getInt(cursor.getColumnIndex("status"));
//    			}
//    			cursor.close();
//    		}
//    		return flag;
//    	}
//    
//    	/**
//    	 * ��ȡ������Ϣ
//    	 * 
//    	 * @return
//    	 */
//    	public List<PushInfo> getAllNotice() {
//    
//    		List<PushInfo> list = new ArrayList<PushInfo>();
//    		if (ApplicationContext.userInfo != null
//    				&& !StringUtils.isEmpty(ApplicationContext.userInfo.userId)) {
//    
//    			Cursor cursor = readwritedb.query(AppConfig.DB_TABLE_NOTICE, null,
//    					"userId=" + ApplicationContext.userInfo.userId, null, null,
//    					null, "pushTime asc");
//    			
//    			if (cursor != null && cursor.getCount() > 0) {
//    				while (cursor.moveToNext()) {
//    					PushInfo pushInfo = new PushInfo();
//    					pushInfo.messageId = cursor.getString(cursor
//    							.getColumnIndex("messageId"));
//    					pushInfo.pushTime = cursor.getString(cursor
//    							.getColumnIndex("pushTime"));
//    					pushInfo.title = cursor.getString(cursor
//    							.getColumnIndex("title"));
//    					pushInfo.status = cursor.getInt(cursor
//    							.getColumnIndex("status"));
//    					list.add(pushInfo);
//    				}
//    			}
//    			cursor.close();
//    		}
//    		return list;
//    	}
//    
//    	/**
//    	 * ��ȡ��Ϣ����
//    	 * 
//    	 * @return
//    	 */
//    	public int getMessageCount() {
//    
//    		int count = 0;
//    		if (ApplicationContext.userInfo != null
//    				&& !StringUtils.isEmpty(ApplicationContext.userInfo.userId)) {
//    			Cursor cursor = readwritedb.query(AppConfig.DB_TABLE_NOTICE, null,
//    					"userId=" + ApplicationContext.userInfo.userId, null, null,
//    					null, null);
//    			count = cursor.getCount();
//    			cursor.close();
//    		}
//    		return count;
//    	}
//    
//    	/**
//    	 * ɾ����Ϣ
//    	 * 
//    	 * @param messageId
//    	 */
//    	public void deleteNotice(String messageId) {
//    		if (ApplicationContext.userInfo != null
//    				&& !StringUtils.isEmpty(ApplicationContext.userInfo.userId)) {
//    			readwritedb.delete(AppConfig.DB_TABLE_NOTICE,
//    					"messageId=? and userId="
//    							+ ApplicationContext.userInfo.userId,
//    					new String[] { messageId });
//    		}
//    	}
//    
//    	/**
//    	 * ���һ����Ϣ
//    	 * 
//    	 * @param pushInfo
//    	 */
//    	public void addNotice(PushInfo pushInfo) {
//    		if (ApplicationContext.userInfo != null
//    				&& !StringUtils.isEmpty(ApplicationContext.userInfo.userId)) {
//    			ContentValues values = new ContentValues();
//    			values.put("pushTime", pushInfo.pushTime);
//    			values.put("messageId", pushInfo.messageId);
//    			values.put("title", pushInfo.title);
//    			values.put("status", 0);
//    			values.put("userId", ApplicationContext.userInfo.userId);
//    			readwritedb.insert(AppConfig.DB_TABLE_NOTICE, null, values);
//    		}
//    	}
//    
//    	/**
//    	 * ��ȡ��Ϣ���µ�ʱ��
//    	 * 
//    	 * @return
//    	 */
//    	public String getMessageLastTime() {
//    
//    		String time = null;
//    		if (ApplicationContext.userInfo != null
//    				&& !StringUtils.isEmpty(ApplicationContext.userInfo.userId)) {
//    
//    			Cursor cursor = readwritedb.query(AppConfig.DB_TABLE_NOTICE,
//    					new String[] { "pushTime" }, "userId="
//    							+ ApplicationContext.userInfo.userId, null, null,
//    					null, "pushTime DESC");
//    			if (cursor != null && cursor.getCount() > 0) {
//    				cursor.moveToFirst();
//    				time = cursor.getString(cursor.getColumnIndex("pushTime"));
//    			}
//    			cursor.close();
//    		}
//    		return time;
//    	}
//    
//    	/**
//    	 * �Ƿ����δ�Ķ�����Ϣ
//    	 * 
//    	 * @return
//    	 */
//    	public boolean isExistNotRead() {
//    
//    		boolean flag = false;
//    		if (ApplicationContext.userInfo != null
//    				&& !StringUtils.isEmpty(ApplicationContext.userInfo.userId)) {
//    			Cursor cursor = readwritedb
//    					.query(AppConfig.DB_TABLE_NOTICE, null,
//    							"status=0 and userId="
//    									+ ApplicationContext.userInfo.userId, null,
//    							null, null, null);
//    			if (cursor != null && cursor.getCount() > 0) {
//    				flag = true;
//    			}
//    			cursor.close();
//    		}
//    		return flag;
//    	}
//    	
//    	/**
//    	 * ��ȡδ����Ϣ����
//    	 * @return
//    	 */
//    	public int getNotReadMessageCount() {
//    
//    		int count = 0;
//    		if (ApplicationContext.userInfo != null
//    				&& !StringUtils.isEmpty(ApplicationContext.userInfo.userId)) {
//    			Cursor cursor = readwritedb.query(AppConfig.DB_TABLE_NOTICE, null,
//    					"status=0 and userId=" + ApplicationContext.userInfo.userId, null, null,
//    					null, null);
//    			count = cursor.getCount();
//    			cursor.close();
//    		}
//    		return count;
//    	}
//    	
    	
    	/**
    	 * ����֪ͨ��
    	 */
    	public static void createNoticeTB()
    	{
    		Log.d("����֪ͨ��ɹ�", "����֪ͨ��ɹ�");
    		StringBuffer sb = new StringBuffer();
    		sb.append("create table if not exists tb_notice(")
    		.append("messageId INTEGER,")
    		.append("title TEXT,")
    		.append("pushTime TEXT,")
    		.append("status INTEGER,")
    		.append("_ID INTEGER,")
    		.append("userId TEXT)");
    		Log.d("", "");
    		readwritedb.execSQL(sb.toString());
    	}
    	
    	/**
    	 * ����������
    	 */
    	public static void createQaTB()
    	{
    		StringBuffer sb = new StringBuffer();
    		sb.append("create table if not exists tb_qa(")
    		.append("qaId varchar(6) PRIMARY KEY NOT NULL,")
    		.append("qaTitle varchar(200) NOT NULL,")
    		.append("qaType varchar(100),")
    		.append("qaQuestion varchar(500),")
    		.append("cmsId varchar(100),")
    		.append("sUrl varchar(200) NOT NULL,")
    		.append("qaOwnedSys varchar(6),")
    		.append("qaOwnedSysDesc varchar(100))");
    		readwritedb.execSQL(sb.toString());
    	}
    	
    	/**
    	 * ������ʷ��¼��
    	 */
    	public static void createHistoryTB()
    	{
    		StringBuffer sb = new StringBuffer();
    		sb.append("create table if not exists tb_history(")
    		.append("_ID INTEGER PRIMARY KEY AUTOINCREMENT,")
    		.append("goodsId TEXT,")
    		.append("time TEXT,")
    		.append("userId TEXT,")
    		.append("goodsName TEXT,")
    		.append("codeNumber TEXT)");
    		readwritedb.execSQL(sb.toString());
    	}
    	
    	/**
    	 * ����ѧУ��ַ��
    	 */
    	public static void createSchoolAddressTB(){
    		Log.d("����ѧУ��ɹ�", "����ѧУ��ɹ�");
    		StringBuffer sb = new StringBuffer();
    		sb.append("create table if not exists tb_school(")
    		.append("id integer primary key autoincrement not null,")
 			.append("province_code integer not null,")
 			.append("province_name text not null,")
 			.append("city_code integer not null,")
 			.append("city_name text not null,")
 			.append("county_code integer not null,")
 			.append("county_name text not null,")
 			.append("school_id integer not null,")
 			.append("school_name text not null,")
 			.append("grade_id integer not null,")
 			.append("grade_code text not null,")
 			.append("grade_name text not null,")
 			.append("class_id integer not null,")
 			.append("class_name text not null)");
 			readwritedb.execSQL(sb.toString());
    	}
    	/**
    	 * ������
    	 */
    	public void begin(){
    		readwritedb.beginTransaction();
    	}
    	/**
    	 * ����ɹ�
    	 */
    	public void success(){
    		readwritedb.setTransactionSuccessful();
    	}
    	/**
    	 * ��������
    	 */
    	public void end(){
    		readwritedb.endTransaction();
    	}
  }

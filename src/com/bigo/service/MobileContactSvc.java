package com.bigo.service;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bigo.activity.R;

public class MobileContactSvc {
	private static final int PICK_CONTACT_REQUEST = 0;

	private final Activity activity;
	
	private final EditText fPersonName;
	private final EditText fEmail;
	private final EditText fPhoneNo;
	private final ImageView photoView;
	
	public MobileContactSvc(Activity activity){
		this.activity = activity;
		
		fPersonName = (EditText) activity.findViewById(R.id.fPersonName);
		fEmail = (EditText) activity.findViewById(R.id.fEmail);
		fPhoneNo = (EditText) activity.findViewById(R.id.fPhone);
		photoView = (ImageView) activity.findViewById(R.id.photo);
		
		
	}
	
	public void startActivityForResult(){
		Intent intent = new Intent(	Intent.ACTION_PICK,	ContactsContract.Contacts.CONTENT_URI );
		activity.startActivityForResult(intent,PICK_CONTACT_REQUEST);
	}
	
	public void onActivityResult(int requestCode,int resultCode,Intent intent){
		if(requestCode!=PICK_CONTACT_REQUEST){
			return;
		}
		
		if(resultCode==Activity.RESULT_OK){
			renderContact(intent.getData());
			Log.d("Selection",intent.toString());
		}
	}

	private String personalName = null,phoneName=null,phoneNo = null;
	private Set<String> emailList = null;
	private Bitmap photo = null;
	
	private void renderContact(Uri uri) {
		if(uri==null){
			return ;
		}
		
		//Get all data
		getData(uri);
		
		personalName = 
				phoneName !=null ? 
				personalName + " [" +   phoneName + "]" 
				: personalName;
		fPersonName.setText(personalName);//Set display name
		
		String list = emailList.toString().replace("[", "").replace("]", "");
		fEmail.setText(list);//set email list
		
		fPhoneNo.setText(phoneNo);//set phone no. from contact list in gmail..
		photoView.setImageBitmap(photo);//set image from contact list in gmail.
		photoView.setVisibility(View.VISIBLE);
	}
	

	private CharSequence getData(Uri uri) {
		emailList = new LinkedHashSet<String>();
		
		ContentResolver resvolver = activity.getContentResolver();
		Cursor cur = resvolver.query(uri,null,null,null,null);
		
		if (cur.moveToFirst()) {
			//Get Contact id
            String id  =cur.getString(cur.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
            //Get Display name
            personalName=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            
            //Get photo
            Uri imageUri =ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,	Long.parseLong(id));
    		InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(
    						resvolver,imageUri);
    		if(input!=null){
    			photo = BitmapFactory.decodeStream(input);
    		}
    		
    		//Get data of mobile phone
            Cursor phoneCur = resvolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID  + " = ?", 
                    new String[] { id }, 
                    null);


            if (phoneCur.moveToFirst()) {
            	phoneName = phoneCur.getString(
                		phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                	);
            	
                phoneNo = phoneCur.getString(
                		phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                	);
            }

            //Get email
            Cursor emailCur = resvolver.query(
            		ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            		null,
            		ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", 
            		new String[] { id }, 
            		null);
            
            String email = null;
            while (emailCur.moveToNext()) {
                // This would allow you get several email addresses
                // if the email addresses were stored in an array
                email = emailCur.getString(
                			emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)
                        );
                emailList.add(email);
            }

            phoneCur.close();
            emailCur.close();
        }
		
		cur.close();
		return personalName;
	}
	
	public Map<String,String>  searchMobile()
	{
		Map<String,String> dataMap = new LinkedHashMap<String,String>();
		
		ContentResolver resvolver = activity.getContentResolver();
	    Cursor phones = resvolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
	    while (phones.moveToNext())//Search all mobile in SIM card.
	    {
	      String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
	      String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	      dataMap.put(name,name + " (" + phoneNumber +")");
	    }
	           
	    phones.close();// close cursor
	    
	    return dataMap;
	}

}

package com.bigo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.bigo.util.GPSTracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapFlgActv extends FragmentActivity implements
								OnMapClickListener, 
								OnMapLongClickListener,
								OnInfoWindowClickListener{//implement interface ����ͧ�����ҹ����͹	{

	private GoogleMap myMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.google_map);
		
		 SupportMapFragment mySupportMapFragment = 
				 (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
		 myMap = mySupportMapFragment.getMap();		 
		 
		 myMap.setOnMapClickListener(this);
		 myMap.setOnMapLongClickListener(this);
		 myMap.setOnInfoWindowClickListener(this);
		 
		 double myLatd = 13.7120165d;
		 double myLongtd = 100.5344332d;
		  
		 // ��駤�� poistion �ͧ map ����дѺ��� zoom
		 myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLatd,myLongtd),8));
		 
		 /*
		 // ��� add marker Ẻ��ҧ�
		  myMap.addMarker(new MarkerOptions()
	  		.position(new LatLng(15.1123,100.05612))		  			  		
	  		.title("Nomal Marker")
	  		.snippet("content"));
		  
		  myMap.addMarker(new MarkerOptions()
	  		.position(new LatLng(15.4231,100.45612))
	  		//.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
	  		//.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
	  		//.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
	  		.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
	  		.title("Nomal Color Marker")
	  		.snippet("content"));		  
	
		  myMap.addMarker(new MarkerOptions()
	  		.position(new LatLng(15,100.45612))		  		
	  		.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))		  		
	  		.title("Nomal Green Marker")
	  		.snippet("content"));*/
		  
		  GPSTracker gps = new GPSTracker(getApplicationContext());
		  
		  // check if GPS enabled  
		  if(gps.canGetLocation()){
				
			 myMap.addMarker(new MarkerOptions()
			 .position(new LatLng( gps.getLatitude() , gps.getLongitude()))		  		
			 .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))		  		
			 .title("�س����ç���")
			 .snippet("content"));
				
		  }else{
 			// can't get location
             // GPS or Network is not enabled
             // Ask user to enable GPS/network in settings
				
			 gps.showSettingsAlert();
		  }
			
		 
		  myMap.addMarker(new MarkerOptions()
//	        .position(new LatLng(15.55234,100))
	        .position(new LatLng(myLatd,myLongtd))//My position
	        .icon(BitmapDescriptorFactory.fromResource(R.drawable.home_icon))
	        .title("��ҹ��ͺ���ǵ�� �.")
	        .snippet("content"));		
		  
	}

	@Override
	protected void onResume() {		
		super.onResume();
		
		// ત��� �� google play service �����������
		final int RQS_GooglePlayServices = 1;
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
		
		if (resultCode == ConnectionResult.SUCCESS){
			Toast.makeText(getApplicationContext(),	"GooglePlayServices is Available",Toast.LENGTH_LONG).show();
		}else{
			GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices);
		}
		
	} // end onResume
	
	@Override
	public void onMapLongClick(LatLng point) {// LongClick ��� Map
		// ����� add Marker
		myMap.addMarker(new MarkerOptions().position(point).title(point.toString()));
		Toast.makeText(getApplicationContext(), "����  Marker �����",	Toast.LENGTH_LONG).show();		
	}	

	@Override
	public void onMapClick(LatLng point) {	// Click ��� map
		// ��ҧ  overley �� map
		//myMap.clear();
		// ����͹���˹觷��١ click �ҵç��ҧ
		myMap.animateCamera(CameraUpdateFactory.newLatLng(point));
	}

	@Override
	public void onInfoWindowClick(Marker marker) {// ��ԡ��� infowindow �ͧ marker ���е��
		
		marker.hideInfoWindow();
		Toast.makeText(getApplicationContext(), 
				marker.getTitle()+"\n"+marker.getPosition().toString(), 
				Toast.LENGTH_LONG).show();		
	}


}

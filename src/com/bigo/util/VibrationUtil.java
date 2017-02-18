package com.bigo.util;

import android.content.Context;
import android.os.Vibrator;

public class VibrationUtil {
	private final	Vibrator vibrator; 
	
	// This example will cause the phone to vibrate "SOS" in Morse Code
	// In Morse Code, "s" = "dot-dot-dot", "o" = "dash-dash-dash"
	// There are pauses to separate dots/dashes, letters, and words
	// The following numbers represent millisecond lengths
	static public int dot = 200;      // Length of a Morse Code "dot" in milliseconds
	static public int dash = 500;     // Length of a Morse Code "dash" in milliseconds
	static public int short_gap = 200;    // Length of Gap Between dots/dashes
	static public int medium_gap = 500;   // Length of Gap Between Letters
	static public int long_gap = 1000;    // Length of Gap Between Words
	static public long[] SOS_PATTERN = {
	    0,  // Start immediately
	    dot, short_gap, dot, short_gap, dot,    // s
	    medium_gap,
	    dash, short_gap, dash, short_gap, dash, // o
	    medium_gap,
	    dot, short_gap, dot, short_gap, dot,    // s
	    long_gap
	};
	// Start immediately
	// Vibrate for 200 milliseconds
	// Sleep for 500 milliseconds
	static public long[] PATTERN_1 = { 0, 200, 500 };
	
	public VibrationUtil(Context context){
		// Get instance of Vibrator from current Context
		this.vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public void vibration(long msec){
		 // Vibrate for msec milliseconds
		 vibrator.vibrate(msec);
	}
	
	public static void vibration(Context context,long msec) {
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(msec);
	}
	
	public void cancel(){
		// Stop the Vibrator in the middle of whatever it is doing
		// CUIDADO: Do *not* do this immediately after calling .vibrate().
		// Otherwise, it may not have time to even begin vibrating!
		vibrator.cancel();
	}
	
	public void vibrationRepeat(long []pattern){
		// The "0" means to repeat the pattern starting at the beginning
		// CUIDADO: If you start at the wrong index (e.g., 1) then your pattern will be off --
		// You will vibrate for your pause times and pause for your vibrate times !
		
		//In another part of your code, you can handle turning off the vibrator as
		//call cacel()
		
		vibrator.vibrate(pattern, 0);
	}
	
	public void vibration(long []pattern){
		// Only perform this pattern one time (-1 means "do not repeat")
		vibrator.vibrate(pattern, -1);
	}
	
	public static void vibration(Context context,long []pattern){
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(pattern, -1);
	}
}

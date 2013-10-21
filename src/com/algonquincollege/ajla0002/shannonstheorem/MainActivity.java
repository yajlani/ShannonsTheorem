/* Legend
 * BW   - Bandwidth
 * SNR  - Signal to Noise Ratio
 * MDR  - Maximum Data Rate
 * */

/*******************************************************************/
/**                                                               **/
/**                                                               **/
/**    Student Name                :  Yazan Ajlani                **/
/**    EMail Address               :  ajla0002@algonquinlive.com  **/
/**    Student Number              :  040706252                   **/
/**    Student User ID             :  ajla0002				      **/
/**    Course Number               :  MAD 9132   		          **/
/**    Lab Section Number          :  							  **/
/**    Professor Name              :  Gerald Hurdle  		      **/
/**    Assignment Name/Number/Date :  Lab 4/ Assignment 1         **/
/**    Optional Comments           :                              **/
/**                                                               **/
/**                                                               **/
/*******************************************************************/

/**
 * Data model for Shannon's Theorem for network capacity.
 * 
 * The model holds the data.
 * 
 * @author Yazan Ajlani ajla0002@algonquinlive.com
 * @version 1.0
 */

package com.algonquincollege.ajla0002.shannonstheorem;

import java.util.Observable;
import java.util.Observer;

import model.ShannonsTheoremModel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements Observer, OnClickListener {

	/* Instance Variables */
	private EditText bwEditText;
	private Button mdrButton;
	private String mdrFormat;
	private TextView mdrTextView;
	private ShannonsTheoremModel model;
	private Button resetButton;
	private EditText snrEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		model = new ShannonsTheoremModel();
		/* register this Activity as an Observer of the Model */
		model.addObserver(this);

		/* initialize each Instance variable */
		bwEditText = (EditText) findViewById(R.id.bwEditText);
		mdrButton = (Button) findViewById(R.id.mdrButton);
		mdrTextView = (TextView) findViewById(R.id.mdrTextView);
		resetButton = (Button) findViewById(R.id.resetButton);
		snrEditText = (EditText) findViewById(R.id.snrEditText);

		// register the Buttons to be handled by this Activity 
		mdrButton.setOnClickListener(this);
		resetButton.setOnClickListener(this);

		mdrFormat = getResources().getString(R.string.mdrFormat);
		mdrTextView
				.setText(String.format(mdrFormat, model.getMaximumDataRate()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* Method created by Eclipse to implement the OnClickListener */
	@Override
	public void onClick(View v) {

		/* determine which button was clicked and handle that event */
		if (v == mdrButton) {
			this.handleCalculateMDR();
		} else if (v == resetButton) {
			this.handleReset();
		}

	}

	/* runs when user clicks Calculate MDR button */
	private void handleCalculateMDR() {
		
		try {
			/* get text from BW edit text and convert it to a string, then
			* convert the string to a double, then set it to the BW in the
			* model
			*/
			model.setBandwidth(Double.valueOf(bwEditText.getText().toString()));
			/* get text from SNR edit text and convert it to a string, then
			* convert the string to a double, then set it to the SNR in the
			* model
			*/
			model.setSignalToNoise(Double.valueOf(snrEditText.getText()
					.toString()));

			model.calculateMDR();
		}
		/* run error message if not */
		catch (NumberFormatException e) {
			System.err.println("Shannon's Theorem: Error calculating MDR");
		}
	}

	/* runs when user clicks Reset button */
	private void handleReset() {
		/* sets BW edit text and SNR edit text to null */
		bwEditText.setText(null);
		snrEditText.setText(null);

		model.reset();

	}

	@Override
	public void update(Observable observable, Object data) {
		/* display MDR to the user */
		mdrTextView
				.setText(String.format(mdrFormat, model.getMaximumDataRate()));
	}

}

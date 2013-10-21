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

package model;

import java.util.Observable;

public class ShannonsTheoremModel extends Observable {

	/* Instance Variables */
	private Double bandwidth;
	private Double maximumDataRate;
	private Double signalToNoise;

	public ShannonsTheoremModel() {
		/* fill entire register with zeros */
		this(0.0D, 0.0D);
	}

	public ShannonsTheoremModel(Double bandwidth, Double signalToNoise) {
		super();
		/* set instance variables */
		this.bandwidth = bandwidth;
		this.signalToNoise = signalToNoise;

		this.calculateMDR();
	}

	public void setBandwidth(Double bandwidth) {
		this.bandwidth = bandwidth;
	}

	public Double getBandwidth() {
		return bandwidth;
	}

	public void calculateMDR() {
		/* calculateMDR based on Shannon's Theorem */
		maximumDataRate = (bandwidth * (Math.log(1 + Math.pow(10,
				signalToNoise / 10)) / Math.log(2)));

		// the model has changed state!
		// notify all registered observers
		this.setChanged();
		this.notifyObservers();
	}

	public Double getSignalToNoise() {
		return signalToNoise;
	}

	public void setSignalToNoise(Double signalToNoise) {
		this.signalToNoise = signalToNoise;
	}

	public Double getMaximumDataRate() {
		return maximumDataRate;
	}

	/**
	 * @param args
	 */

	@Override
	public String toString() {
		/* create string that shows up after clicking calculate MDR */
		return "ShannonsTheoremModel[bandwith=" + bandwidth
				+ ", signalToNoise=" + signalToNoise + ", maximumDataRate="
				+ maximumDataRate + "]";
	}

	public void reset() {
		/* reset all values to zero */
		bandwidth = signalToNoise = maximumDataRate = 0.0D;

		// the model has changed state!
		// notify all registered observers
		this.setChanged();
		this.notifyObservers();
	}

	public static void main(String[] args) {
		/* test using the main method */
		ShannonsTheoremModel m = new ShannonsTheoremModel(3000D, 30D);
		m.calculateMDR();

		/* display BW and SNR to user */
		System.out.println("BW: " + m.getBandwidth() + "\tSNR: "
				+ m.getSignalToNoise());
		/* display MDR to user */
		System.out.println("The MDR is: " + m.getMaximumDataRate());
	}

}

package Alexandra_The_Bot;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

//import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;

import net.sourceforge.javaflacencoder.FLACFileWriter;

/**
 * This is where all begins.
 * API developed by GOXR3PLUS and edited by Lucas
 * @author GOXR3PLUS
 * @author Lucas Tijerina
 *
 */

public class Application{
	
	private final Microphone mic = new Microphone(FLACFileWriter.FLAC);
	private final GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
	private File soundfile = new File("test.wav");
	String oldText = "";
	
	/**
	 * Constructor
	 */
	public Application() {
		
		//Duplex Configuration
		duplex.setLanguage("en");
		
		duplex.addResponseListener(new GSpeechResponseListener() {
			
			/**
			 * onResponse method sends data stream to Google services and gets text back
			 */
			public void onResponse(GoogleResponse googleResponse) {
				String output = "";
				String[] pos_resp = new String[10000];
				int counter = 1;
				//Get the response from Google Cloud
				output = googleResponse.getResponse();
				if (!googleResponse.getOtherPossibleResponses().isEmpty()) {
					pos_resp[counter] = (String) googleResponse.getOtherPossibleResponses().get(0);
					pos_resp[counter] = pos_resp[counter].trim();
					output = output + " |"+ pos_resp +"|";
				}
				//System.out.println(pos_resp[counter]);
				System.out.println(output);
				if (output != null) {
					makeDecision(output);
				}
				counter = counter + 1;
			}
		});
		startSpeechRecognition();
	}
	
	/**
	 * This method makes a decision based on the given text of the Speech Recognition
	 * Later on keywords like 'Exam' will be added to emphasize important parts of the lecture
	 * @param text
	 */
	public void makeDecision(String output) {
		output = output.trim();
		
		//We don't want duplicate responses
		if (!oldText.equals(output))
			oldText = output;
		else
			return;	
}
	/**
	 * Starts the Speech Recognition
	 */
	public void startSpeechRecognition() {
		//Start a new Thread so our application don't lags
		new Thread(() -> {
			try {
				duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
			} catch (LineUnavailableException | InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	/**
	 * Stops the Speech Recognition
	 */
	public void stopSpeechRecognition() {
		mic.close();
		System.out.println("Stopping Speech Recognition...." + " , Microphone State is:" + mic.getState());
	}
	
	public static void main(String[] args) {
		new Application();	
	}
	
}
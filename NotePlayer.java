package MusicPlayer;

//update file to play at a certain speed
//different lengths of notes
/**
 * Condensed program used only to play notes at a constant tick(default
 * of 500ms for each note). The audio format uses a sampling rate of 
 * 44,100 samples per second with 8-bit audio.
 * 
 */

import javax.sound.sampled.*;

public class NotePlayer {
	public static float SAMPLE_RATE = 44100;
	public static final double FREQUENCY = 440.0; // Hz
	private static final int BITS_PER_SAMPLE = 16; // 16-bit audio
	public static double MILLISECONDS = 500; // milliseconds per note

	public NotePlayer() {
	}

	/**
	 * 
	 * Throws IllegalArugmentException if hz <= 0 || vol > 1.0 || vol < 0.0
	 * Plays a note based on frequency, milliseconds and volume level. Created a
	 * buffer and writes audio onto the buffer.
	 * 
	 */
	public static void play(double hz, double ms, double vol)
			throws LineUnavailableException {

		if (hz <= 0) {
			throw new IllegalArgumentException(
					"Frequency cannot be less than or equal than zero");
		} else if (vol > 1.0 || vol < 0.0) {
			throw new IllegalArgumentException(
					"Volume is out of the acceptable range");
		}
		byte[] buffer = new byte[(int) (SAMPLE_RATE * ms / 1000.0)];
		//
		for (int i = 0; i < buffer.length; i++) {
			double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
			buffer[i] = (byte) (Math.sin(angle) * 127.0 * vol);
		}

		// shapes the first and last 10ms of the wave form to make sound
		// smoother
		for (int i = 0; i < SAMPLE_RATE / 100.0 && i < buffer.length / 2; i++) {
			buffer[i] = (byte) (buffer[i] * i / (SAMPLE_RATE / 100.0));
			buffer[buffer.length - 1 - i] = (byte) (buffer[buffer.length - 1
					- i]
					* i / (SAMPLE_RATE / 100.0));
		}

		AudioFormat af = new AudioFormat(SAMPLE_RATE, BITS_PER_SAMPLE, 1, true, false);
		SourceDataLine line = AudioSystem.getSourceDataLine(af);
		// opens the audio stream, plays the buffer, then closes the line
		line.open(af);
		line.start();
		line.write(buffer, 0, buffer.length);
		line.drain();
		line.close();
	}

	/**
	 * Plays a note based on the number of half steps from concert A
	 */
	public static void play(double note) throws LineUnavailableException {
		play(Math.pow(2.0, note / 12.0) * FREQUENCY, MILLISECONDS, 0.8); // 800hz,
																			// vol
																			// 0.8
	}

}
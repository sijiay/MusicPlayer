package MusicPlayer;

import javax.sound.sampled.LineUnavailableException;

import MusicPlayer.MusicPlayer;

/**
 * The MusicPlayer class that test out NotePlayer.java. Plays 2 melodies(Mary
 * had a little lamb and Ode to Joy), and 2 Scale(A Major Scale and an a
 * harmonic minor scale) Allows user to change song/scale choice.
 */

public class MusicPlayer {
	public static void main(String[] args) throws LineUnavailableException {
		// A, B, C#, D, E, F#,G#, A
		double[] aMajScale = { 0, 2, 4, 5, 7, 9, 11, 12 };
		
		// A, B, C, D, E, F, G#, A													
		double[] aMinScale = { 0, 2, 3, 5, 7, 8, 11, 12 };
		
		//mary had a little lamb
		double[] mary = { 4, 2, 0, 2, 4, 4, 4, 100, 2, 2, 2, 100, 4, 4, 4, 100,
				4, 2, 0, 2, 4, 4, 4, 4, 2, 2, 4, 2, 0 };
		
		//Ode to Joy
		double[] odeToJoy = { 2, 2, 3, 5, 5, 3, 2, 0, -2, -2, 0, 2, 2, 0, 0,
				100, 2, 2, 3, 5, 5, 3, 2, 0, -2, -2, 0, 2, 0, -2, -2 };

		for (double note : aMinScale) { // change array name to play different
										// songs
			if (note == 100) { // Uses 100 as a default to play a rest
				NotePlayer.play(note, 500.0, 0.0); // sets volume to 0.0
			} else {
				NotePlayer.play(note);
			}
		}
	}

}

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.Patch;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;

public class Player extends JFrame {
	MidiChannel channel;
	int instrument=0;
	int decalage=30;
	int velocity = 64;
	Synthesizer synth = null;
	Instrument[] instrus = null;
	public Player() {
		super();
		CustomSynthesizer cs = new CustomSynthesizer();
		cs.loadSoundbank("sdb.sf2");
		Drummer drum = new Drummer(cs);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(50, 128);  // We use window width as volume control
		this.setVisible(true);


		this.addKeyListener(new KeyAdapter( ) {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode( );
				switch (key) {
				case 32: //space bar
					drum.noteOn(Drummer.KICK, velocity);
					break;
				case 71: //G
					drum.noteOn(Drummer.HIGH_TOM, velocity);
					break;
				case 72: //H
					drum.noteOn(Drummer.MIDDLE_TOM, velocity);
					break;
				case 78: //N
					drum.noteOn(Drummer.FLOOR_TOM, velocity);
					break;
				case 74: //J
					drum.noteOn(Drummer.RIDE, velocity);
					break;
				case 70: //F
					drum.noteOn(Drummer.CRASH, velocity);
					break;
				case 86: //V
					drum.noteOn(Drummer.SNARE, velocity);
					break;
				case 67: //C
					drum.noteOn(Drummer.HITHAT, velocity);
					break;
				default:
					break;
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				velocity = e.getX( );
			}
		});

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Patch patch = synth.getLoadedInstruments()[instrument++].getPatch();
				channel.programChange(patch.getBank(),patch.getProgram());	
				System.out.println(instrument);
			}
		});

	}

	public void printInstrument(){
		for (Instrument instru : this.synth.getAvailableInstruments()) {
			System.out.println(instru.getName()+ (instru.getPatch().getProgram()));

		}
	}

	public static void main(String[] args) {
		System.out.println("Coucou");
		new Player();
	}




}

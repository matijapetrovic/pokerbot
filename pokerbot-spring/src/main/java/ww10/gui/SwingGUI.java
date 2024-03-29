package ww10.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

final class SwingGUI {

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	private final DataModel dataModel;

	public SwingGUI(DataModel dataModel) {
		this.dataModel = dataModel;
		// created by non-swing thread, so do nothing.
	}

	void initialize() {

		statusbar = new JProgressBar();
		actionPanel = new ActionPanel(dataModel);
		averageProfitPanel = new AverageProfitPanel(dataModel);

		// set up frame
		frame = new JFrame("Wetenschapsweek");
		JSplitPane splitPane = new JSplitPane();

		JTabbedPane tabs = new JTabbedPane();

		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		splitPane.setBottomComponent(tabs);
		splitPane.setLeftComponent(statusbar);

		frame.getContentPane().add(splitPane);

		//		this.progressFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		//		Toolkit tk = Toolkit.getDefaultToolkit();
		//		int xSize = ((int) tk.getScreenSize().getWidth());
		//		int ySize = ((int) tk.getScreenSize().getHeight());
		//		this.progressFrame.setSize(xSize, ySize);
		//		this.progressFrame.setSize(1200, 800);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statusbar.setStringPainted(true);
		//setting this causes gnome bars to be on top
		//		this.progressFrame.setResizable(false);
		this.frame.setLocation(0, 0);
		//		Toolkit tk = Toolkit.getDefaultToolkit();
		//		int xSize = ((int) tk.getScreenSize().getWidth());
		//		int ySize = ((int) tk.getScreenSize().getHeight());
		//		this.progressFrame.setSize(xSize, ySize);
		if (!this.frame.isDisplayable()) {
			// Can only do this when the frame is not visible
			this.frame.setUndecorated(true);
		}
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (gd.isFullScreenSupported()) {
			gd.setFullScreenWindow(this.frame);
		} else {
			// Can't run fullscreen, need to bodge around it (setSize to screen size, etc)
		}
		//		this.frame.addKeyListener(new KeyListener() {
		//
		//			public void keyPressed(KeyEvent e) {
		//			}
		//
		//			public void keyReleased(KeyEvent e) {
		//			}
		//
		//			public void keyTyped(KeyEvent e) {
		//				if (e.getKeyChar() == 'f') {
		//					//					progressFrame.setResizable(false);
		//					//					//					progressFrame.setUndecorated(true);
		//					//					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//					//					GraphicsDevice graphicsDevice = ge.getDefaultScreenDevice();
		//					//					graphicsDevice.setFullScreenWindow(progressFrame);
		//					//					//myDevice.setFullScreenWindow(null);
		//				} else if (e.getKeyChar() == 'x') {
		//					System.exit(0);
		//				}
		//			}
		//
		//		});
		tabs.addTab("Average Profit", averageProfitPanel);
		tabs.addTab("Actions", actionPanel);

		this.frame.pack(); // not needed?
		this.frame.setVisible(true);
	}

	public void redraw() {

	}

	public void changeStatus(String status) {
		statusbar.setString(status);
	}

	JFrame frame;
	JProgressBar statusbar;
	ActionPanel actionPanel;
	AverageProfitPanel averageProfitPanel;

}
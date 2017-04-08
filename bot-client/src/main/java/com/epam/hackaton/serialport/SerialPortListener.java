package com.epam.hackaton.serialport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;

import com.epam.hackaton.bot.activities.BotActivityHandler;

public class SerialPortListener implements SerialPortEventListener {
	SerialPort serialPort;
	/** The port we're normally going to use. */
	/*private static final String PORT_NAMES[] = {
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyUSB0", // Linux
			"COM6", // Windows
	};*/

	private BufferedReader input;
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;

	public void initialize(String port) {

		String PORT_NAMES[] = {port};

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		// First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = null;
				if (input.ready()) {
					inputLine = input.readLine();
					handleInfoFromPort(inputLine);
				}

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other
		// ones.
	}

	private void handleInfoFromPort(String line) throws InterruptedException {
		int number = Integer.valueOf(line);
		if(number > 0 ) {
			System.out.println("Input from Serial Port: " + number);
			BotActivityHandler.defaultBotActivity();
		}
	}

	public static void main(String[] args) throws Exception {
		boolean isArduinoEnabled = true;
		if(isArduinoEnabled) {
			SerialPortListener main = new SerialPortListener();
			main.initialize("COM7");
		}

		Thread t = new Thread() {
			public void run() {
				// the following line will keep this app alive for 1000 seconds,
				// waiting for events to occur and responding to them (printing
				// incoming messages to console).
				try {
					Thread.sleep(30000);
				} catch (InterruptedException ie) {
				}
			}
		};
		t.start();
		System.out.println("Started");
	}
}

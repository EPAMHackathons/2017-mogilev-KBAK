package com.epam.hackaton.utilities.speech;

import java.beans.PropertyVetoException;
import java.util.Locale;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import com.epam.hackaton.bot.skype.BotSingleton;

public class SpeechUtils {

	SynthesizerModeDesc desc;
	Synthesizer synthesizer;
	Voice voice;

	private static SpeechUtils su = null;

	public static SpeechUtils getInstance() {
		if(su == null) {
			su = new SpeechUtils();
			try {
				su.init("kevin16");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return su;
	}

	public static void speakMsg(String msg) {
		if(BotSingleton.isSoundEnabled()) {
			try {
				getInstance().doSpeak(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void terminateSpeaker() {
		if(su != null) {
			try {
				su.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void init(String voiceName) throws EngineException, AudioException, EngineStateError, PropertyVetoException {
		if (desc == null) {
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
			desc = new SynthesizerModeDesc(Locale.US);
			Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
			synthesizer = Central.createSynthesizer(desc);
			synthesizer.allocate();
			synthesizer.resume();
			SynthesizerModeDesc smd = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
			Voice[] voices = smd.getVoices();
			Voice voice = null;
			for (int i = 0; i < voices.length; i++) {
				if (voices[i].getName().equals(voiceName)) {
					voice = voices[i];
					break;
				}
			}
			synthesizer.getSynthesizerProperties().setVoice(voice);
		}
	}

	private void terminate() throws EngineException, EngineStateError {
		synthesizer.deallocate();
	}

	private void doSpeak(String speakText)
			throws EngineException, AudioException, IllegalArgumentException, InterruptedException {
		synthesizer.speakPlainText(speakText, null);
		synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
	}

	public static void main(String[] args) throws Exception {
		SpeechUtils su = new SpeechUtils();
		su.init("kevin16");
		su.doSpeak("Hello");
		su.terminate();
	}
}
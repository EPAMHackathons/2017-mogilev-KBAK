Run

Run StartWindow

Say your skype name to bot (! from skype user settings)

----------------------------

Bot Account:

Gmail:

kvak.bot@gmail.com
kv@k-2017

Skype:

login: kvak.bot@gmail.com
name: live:kvak.bot
password: kv@k-2017


----------------------------

To implement client (activities package):

1) BotActivityHandler

2) JobsChecker


---------------------------


To connect Arduino (not necessary):

1) install Arduino IDE for COM port drivers;

2) Configure Java libraries (use zip ExternalLibs):


2.1)

Append the directory containing rxtxSerial.dll into your PATH environment variable.

2.2)
You should also ensure that the RXTXcomm.jar is in your CLASSPATH.


Or


 copy rxtxSerial.dll to %JAVA_HOME%\bin,
(%JAVA_HOME% is the folder where JRE is installed on your system; e.g. c:\Program Files\Java\j2re1.4.1_01)
 copy RXTXcomm.jar to %JAVA_HOME%\lib\ext
 


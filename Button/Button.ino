/*
 http://arduino.ru/tutorials/button
 
  Кнопка
  
 Включаем и выключаем светодиод нажатием кнопки.
  
 created 2005
 by DojoDave <http://www.0j0.org>
 modified 28 Oct 2010
 by Tom Igoe
  
 This example code is in the public domain.
 */
 
// задаем константы
const int BUTTON_PIN = 2;     // номер входа, подключенный к кнопке
const int LED_PIN =  13;      // номер выхода светодиода
 
 
void setup() {
  Serial.begin(9600);
  // инициализируем пин, подключенный к светодиоду, как выход
  pinMode(LED_PIN, OUTPUT);     
  // инициализируем пин, подключенный к кнопке, как вход
  pinMode(BUTTON_PIN, INPUT);   
}
 
void loop(){
  // считываем значения с входа кнопки
  int buttonState = digitalRead(BUTTON_PIN);
 
  // проверяем нажата ли кнопка
  // если нажата, то buttonState будет HIGH:
  if (buttonState == HIGH) {   
    // включаем светодиод   
    digitalWrite(LED_PIN, HIGH);
    Serial.println(buttonState, DEC);
    delay(1000);
  }
  else {
    // выключаем светодиод
    digitalWrite(LED_PIN, LOW);
  }
   
}


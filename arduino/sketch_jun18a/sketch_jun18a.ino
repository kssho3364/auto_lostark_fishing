#include<Servo.h>
#include<SoftwareSerial.h>

Servo servo;

int count = 0;
int countMillis = 4;
long myTime = 0;

unsigned long millisTime;
unsigned long timee = 0;

int TXNum = 2;//신호 송신
int RXNum = 3;//신호 수신
SoftwareSerial bluetooth( TXNum, RXNum );

byte buffer[1024];

int bufferPosition;

int value = 0;

void setup() {
  bluetooth.begin(9600);
  servo.attach(7);
  servo.write(40);
  Serial.begin(9600);
  bufferPosition = 0;

}

void loop() {
    
      myTime++;

      if(myTime > 7000){
        count = 1;
      }
      
      if(bluetooth.available() || count == 1){
      byte data = bluetooth.read();
      if(data == 'a' || count == 1){
        value = 0;
        servo.write(value);
        delay(100);
        value = 40;
        servo.write(value);
        delay(10000);
        value = 0;
        servo.write(value);
        delay(100);
        value = 40;
        servo.write(value);
        delay(5000);  
        bluetooth.print('b');
        count = 0;
        myTime = 0;
      }
    }   
}

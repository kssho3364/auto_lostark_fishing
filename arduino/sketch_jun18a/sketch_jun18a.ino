#include<Servo.h>
#include<SoftwareSerial.h>

Servo servo;

int TXNum = 2;//신호 송신
int RXNum = 3;//신호 수신
SoftwareSerial bluetooth( TXNum, RXNum );

byte buffer[1024];

int bufferPosition;

int value = 0;

void setup() {
  bluetooth.begin(9600);
  servo.attach(7);
  Serial.begin(9600);
  bufferPosition = 0;

}

void loop() {
  int reData;

  if(bluetooth.available()){
    byte data = bluetooth.read();
    if(data == 'a'){
      value = 180;
      servo.write(value);
      delay(300);
      value = 0;
      servo.write(value);
    }else if(data == 'b'){
      value = 0;
      servo.write(value);
    }
    




  
//  if(Serial.available()){
//    char in_data;
//    in_data = Serial.read();
//    if(in_data == '1'){
//      value = 180;
//    }else if(in_data == '0'){
//    value = 0;
//  }else{
//    
//    }
//  servo.write(value);
//  }
  }
}

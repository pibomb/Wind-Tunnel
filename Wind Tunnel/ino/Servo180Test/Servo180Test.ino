#include <Servo.h>

Servo myservo;  // create servo object to control a servo
// twelve servo objects can be created on most boards

void setup() {
  Serial.begin(9600);
  myservo.attach(2);  // attaches the servo on pin 2 to the servo object
}

void loop() {
  myservo.write(180);
  delay(1000);
  myservo.write(0);
  delay(1000);
  /*
  if(Serial.available() > 0) {
    int pos = Serial.parseInt();
    myservo.write(pos);
  }
  */
}


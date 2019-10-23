package adamf59.SystemHostController.IO;

import java.io.IOException;

import com.pi4j.io.gpio.exception.UnsupportedBoardType;
import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;
import com.pi4j.io.serial.StopBits;
import java.lang.InterruptedException;

public class SerialArduino{
    Serial arduino;
    SerialConfig config;
    public SerialArduino(){
        config = new SerialConfig();
        arduino = SerialFactory.createInstance();
    }
    public void init(){
        try{
            config.device("/dev/ttyUSB0").baud(Baud._19200).dataBits(DataBits._8).parity(Parity.NONE)
                    .stopBits(StopBits._1).flowControl(FlowControl.NONE);
                    arduino.open(config);
        }
        catch(UnsupportedBoardType | IOException e){

        }
    }
    public void sendSerial(String str){
        try{
            arduino.write(str);
        }
        catch(Exception e){

        }
    }
}


// A180B180C180D180

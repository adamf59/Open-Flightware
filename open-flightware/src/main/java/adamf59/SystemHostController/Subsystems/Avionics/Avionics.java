/**
* JAGSAT MISSION 2019
* Written by Adam Frank, with references to contributors at GitHub and Stack Overflow
*  Updates at GitHub.com/adamf59
*/
package adamf59.SystemHostController.Subsystems.Avionics;

import java.io.IOException;
import java.util.Scanner;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinAnalog;
import com.pi4j.io.gpio.GpioPinAnalogOutput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

import adamf59.Core.Subsystem;
import adamf59.SystemHostController.SystemHost;
import adamf59.SystemHostController.IO.*;
import adamf59.SystemHostController.System.Console;

public class Avionics extends Subsystem {

    public Avionics(int id) {
        super("JAGSAT_AVIONICS_SUBSYSTEM", id);
    }

    @Override
    public void init() {
        Console.printInfo("Initializing Avionics Subsystem");

    }

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        try {
            SystemHost.s_communications.transmit(in);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
            
            
    }
    @Override
    public void executeAlways() {
        
    }

}
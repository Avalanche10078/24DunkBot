package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Grabber extends SubsystemBase {   // defines Grabber subsystem and extends subsystembase
    private final static int GRABBER_ID = 8;   // Assinging GRABBER_ID a vaulue of 8

    private final static double GRABBER_IN_VOLTS = 8.0; // Assings GRABBER_IN_VOLTS a value of 8

    private final TalonFX motor = new TalonFX(GRABBER_ID);  // Defines a new motor

    private final VoltageOut control = new VoltageOut(0);  // Defines a new control

    private void setMotorPower(double volts) { // Sets the motor power takes volts as an argument
        //motor.setControl(control.withOutput(volts)); // 
        control.Output = volts; // Changing value stored in control 
        motor.setControl(control); // Tell the motor what to run
    }
    
    public Command grabby() { // New method called grabby, returns a cmd
        return startEnd( // method that create a cmd
            () -> setMotorPower(GRABBER_IN_VOLTS), // setting motopower to high voltage, when cmd starting
            () -> setMotorPower(0) // when cmd ends we set it to 0
        );
    }
}

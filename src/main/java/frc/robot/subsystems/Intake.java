package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private final static int INTAKE_ID = 9;

    private final static double INTAKE_IN_VOLTS = 7.0;

    private final TalonFX motor = new TalonFX(INTAKE_ID);

    private final VoltageOut control = new VoltageOut(0);

    private void setMotorPower(double volts) {
        motor.setControl(control.withOutput(volts));
    }

    public Command moveIntakeIn() {
        return startEnd(() -> setMotorPower(INTAKE_IN_VOLTS), () -> setMotorPower(0));
    }
}

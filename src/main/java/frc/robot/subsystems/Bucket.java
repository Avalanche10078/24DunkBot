package frc.robot.subsystems;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase; 

public class Bucket extends SubsystemBase {
 private static final int BUCKET_ID = 9;

  private static final double BUCKET_HOME_POSITION = 7.0;
  private static final double BUCKET_FLIPPED_POSITION = 14.0;


  private final TalonFX motor = new TalonFX(BUCKET_ID);

  private final PositionVoltage control = new PositionVoltage (0);

  private void setMotorPosition(double volts) {
    motor.setControl(control.withPosition(volts));
  }

  public Command homeCommand() {
    return runOnce(() -> setMotorPosition(BUCKET_HOME_POSITION));
  }

  public Command moveFlipped() {
    return runOnce(() -> setMotorPosition(BUCKET_FLIPPED_POSITION));
  }
}

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase{
    private static final int ELEVATOR_ID = 6;

  private static final double ELEVATOR_DOWN_POSITION = 8.0;
  private static final double ELEVATOR_UP_POSITION = 16.0;


  private final TalonFX motor = new TalonFX(ELEVATOR_ID);

  private final PositionVoltage control = new PositionVoltage (0);

  private void setMotorPosition(double volts) {
    motor.setControl(control.withPosition(volts));
  }

  public Command downCommand() {
    return runOnce(() -> setMotorPosition(ELEVATOR_DOWN_POSITION));
  }

  public Command upCommand() {
    return runOnce(() -> setMotorPosition(ELEVATOR_UP_POSITION));
  }
}

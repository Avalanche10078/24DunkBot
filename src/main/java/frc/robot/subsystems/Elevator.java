package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  private static final int ELEVATOR_ID = 11;

  private static final double ELEVATOR_DOWN_POSITION = 0;
  private static final double ELEVATOR_UP_POSITION = 16;

  private final TalonFX motor = new TalonFX(ELEVATOR_ID);

  {
    motor
        .getConfigurator()
        .apply(new TalonFXConfiguration().withSlot0(new Slot0Configs().withKP(1)));
  }

  private final PositionVoltage control = new PositionVoltage(0);

  private void setMotorPosition(double pos) {
    motor.setControl(control.withPosition(pos));
  }

  public Command downCommand() {
    return runOnce(() -> setMotorPosition(ELEVATOR_DOWN_POSITION));
  }

  public Command upCommand() {
    return runOnce(() -> setMotorPosition(ELEVATOR_UP_POSITION));
  }
}

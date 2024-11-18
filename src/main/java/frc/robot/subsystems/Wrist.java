package frc.robot.subsystems;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Util;

public class Wrist extends SubsystemBase {
  private static final int WRIST_ID = 15;

  private static final double WRIST_HOME_POSITION = 0;
  private static final double WRIST_FLIPPED_POSITION = -0.2;

  private final TalonFX motor = new TalonFX(WRIST_ID);

  private final PositionVoltage control = new PositionVoltage(0);

  private void setMotorPosition(double pos) {
    motor.setControl(control.withPosition(pos));
  }

  public Command homeCommand() {
    return runOnce(() -> setMotorPosition(WRIST_HOME_POSITION));
  }

  public Command moveFlipped() {
    return runOnce(() -> setMotorPosition(WRIST_FLIPPED_POSITION));
  }

  private final DCMotorSim motorSim =
      new DCMotorSim(
          LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60Foc(1), 0.001, 1),
          DCMotor.getKrakenX60Foc(1));

  public void simulationPeriodic() {
    Util.advanceSimulation(motor, motorSim);
  }
}

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Util;

public class Grabber extends SubsystemBase {
  private static final int GRABBER_ID_1 = 12;
  private static final int GRABBER_ID_2 = 13;

  private static final double GRABBER_IN_VOLTS = 8.0;

  private final TalonFX motor1 = new TalonFX(GRABBER_ID_1);
  private final TalonFX motor2 = new TalonFX(GRABBER_ID_2);

  private final VoltageOut voltageControl = new VoltageOut(0);

  private void setMotorPower(double volts) {
    motor1.setControl(voltageControl.withOutput(volts));
    motor2.setControl(voltageControl.withOutput(volts));
  }

  public Command grabCmd() {
    return startEnd(() -> setMotorPower(GRABBER_IN_VOLTS), () -> setMotorPower(0));
  }

  private final DCMotorSim motor1Sim =
      new DCMotorSim(
          LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60Foc(1), 0.001, 1),
          DCMotor.getKrakenX60Foc(1));
  private final DCMotorSim motor2Sim =
      new DCMotorSim(
          LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60Foc(1), 0.001, 1),
          DCMotor.getKrakenX60Foc(1));

  public void simulationPeriodic() {
    Util.advanceSimulation(motor1, motor1Sim);
    Util.advanceSimulation(motor2, motor2Sim);
  }
}

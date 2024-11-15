package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Grabber extends SubsystemBase {
  private static final int GRABBER_ID = 9;

  private static final double GRABBER_IN_VOLTS = 8.0;

  private final TalonFX motor = new TalonFX(GRABBER_ID);

  private final VoltageOut voltageControl = new VoltageOut(0);

  private void setMotorPower(double volts) {
    motor.setControl(voltageControl.withOutput(volts)); //
  }

  public Command grabCmd() {
    return startEnd(() -> setMotorPower(GRABBER_IN_VOLTS), () -> setMotorPower(0));
  }

  private final DCMotorSim motorSim =
      new DCMotorSim(
          LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60Foc(1), 0.001, 1),
          DCMotor.getKrakenX60Foc(1));

  public void simulationPeriodic() {
    var talonFXSim = motor.getSimState();
    talonFXSim.setSupplyVoltage(RobotController.getBatteryVoltage());
    var motorVoltage = talonFXSim.getMotorVoltage();
    motorSim.setInputVoltage(motorVoltage);
    motorSim.update(TimedRobot.kDefaultPeriod);
    talonFXSim.setRawRotorPosition(motorSim.getAngularPosition());
    talonFXSim.setRotorVelocity(motorSim.getAngularVelocity());
  }
}

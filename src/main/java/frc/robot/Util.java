package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class Util {
  public static void advanceSimulation(TalonFX motor, DCMotorSim motorSim) {
    var talonFXSim = motor.getSimState();
    talonFXSim.setSupplyVoltage(RobotController.getBatteryVoltage());
    var motorVoltage = talonFXSim.getMotorVoltage();
    motorSim.setInputVoltage(motorVoltage);
    motorSim.update(TimedRobot.kDefaultPeriod);
    talonFXSim.setRawRotorPosition(motorSim.getAngularPosition());
    talonFXSim.setRotorVelocity(motorSim.getAngularVelocity());
  }
}

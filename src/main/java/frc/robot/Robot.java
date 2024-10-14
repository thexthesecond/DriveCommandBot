package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {

  public RobotContainer m_RobotContainer;
  public DriveTrain driveTrain = new DriveTrain();
  public Joystick joy = driveTrain.joy;

  @Override
  public void robotInit() {
    m_RobotContainer = new RobotContainer(driveTrain);
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  public void DriveDashboard() {
    SmartDashboard.putNumber("Motor esq.", driveTrain.Lspeed);
    SmartDashboard.putNumber("Motor dir.", driveTrain.Rspeed);
    SmartDashboard.putNumber("Diff.", driveTrain.diff);
    SmartDashboard.putNumber("Quad.", driveTrain.quad);
    SmartDashboard.putNumber("Mag.", driveTrain.mag);
  }

  @Override
  public void teleopPeriodic() {
    m_RobotContainer.HandleDriveCommands();
    
    DriveDashboard();
  }
}
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AxisCommand;
import frc.robot.commands.POVCommand;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {

  public AxisCommand axisCommand;
  public POVCommand povCommand;

  public RobotContainer m_RobotContainer;
  public DriveTrain driveTrain = new DriveTrain();
  public Joystick joy = driveTrain.joy;
  public double Px, Py, Px2, Py2;
  public int pov;

  @Override
  public void robotInit() {
    m_RobotContainer = new RobotContainer(driveTrain);

    axisCommand = m_RobotContainer.axisCommand;
    povCommand = m_RobotContainer.povCommand;
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void robotPeriodic() {
      SmartDashboard.putNumber("Motor esq.", driveTrain.Lspeed);
      SmartDashboard.putNumber("Motor dir.", driveTrain.Rspeed);
      SmartDashboard.putNumber("Diff.", driveTrain.diff);
  }

  @Override
  public void teleopPeriodic() {
    Px = driveTrain.Px;
    Py = driveTrain.Py;
    Px2 = driveTrain.Px2;
    Py2 = driveTrain.Py2;
    pov = driveTrain.pov;

    if ((Py != 0 || Px != 0) || (Px2 != 0 || Py2 != 0)) {
      axisCommand.schedule();
    }

    if (pov != -1) {
      povCommand.schedule();
    }

    CommandScheduler.getInstance().run();

    SmartDashboard.putNumber("Quad.", driveTrain.quad);
    SmartDashboard.putNumber("Mag.", driveTrain.mag);
  }
}
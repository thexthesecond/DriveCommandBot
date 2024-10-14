package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.AxisCommand;
import frc.robot.commands.POVCommand;
import frc.robot.subsystems.DriveTrain;

public class RobotContainer {
  public DriveTrain driveTrain = new DriveTrain();
  public Joystick joy = driveTrain.joy;
  public double Px, Py, Px2, Py2, pov;

  public final AxisCommand axisCommand;
  public final POVCommand povCommand;

  public RobotContainer(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;

    axisCommand = new AxisCommand(driveTrain, joy);
    povCommand = new POVCommand(driveTrain, joy);
    configureBindings();
  }

  public void configureBindings() {
  }

  public void HandleDriveCommands() {
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
  }
}
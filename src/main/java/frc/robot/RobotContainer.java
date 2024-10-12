package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.AxisCommand;
import frc.robot.commands.POVCommand;
import frc.robot.subsystems.DriveTrain;

public class RobotContainer {
  public DriveTrain driveTrain = new DriveTrain();
  public Joystick joy = driveTrain.joy;
  public final AxisCommand axisCommand;
  public final POVCommand povCommand;

  public RobotContainer(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;

    axisCommand = new AxisCommand(driveTrain, joy, this);
    povCommand = new POVCommand(driveTrain, joy);
    configureBindings();
  }

  public void configureBindings() {
  }
}
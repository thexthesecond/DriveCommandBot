package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AxisCommand;
import frc.robot.commands.POVCommand;
import frc.robot.subsystems.DriveTrain;

public class RobotContainer {
  public DriveTrain driveTrain = new DriveTrain();
  public Joystick joy;
  public Trigger axisTrigger;
  public Trigger povTrigger;

  public final AxisCommand axisCommand;
  public final POVCommand povCommand;

  public RobotContainer(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    joy = driveTrain.joy;

    axisCommand = new AxisCommand(driveTrain, joy);
    povCommand = new POVCommand(driveTrain, joy);
    configureBindings();
  }

  public void configureBindings() {

    axisTrigger = new Trigger(() -> joy.getRawAxis(1) != 0)
    .or(() -> joy.getRawAxis(2) != 0)
    .or(() -> joy.getRawAxis(4) != 0)
    .or(() -> joy.getRawAxis(5) != 0);

    povTrigger = new Trigger(() -> joy.getPOV() != -1);

    axisTrigger.onTrue(axisCommand);
    povTrigger.onTrue(povCommand);
  }
}
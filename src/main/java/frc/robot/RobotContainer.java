package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveTrain;

public class RobotContainer {
  public DriveTrain driveTrain = new DriveTrain();
  public Joystick joy;
  public Trigger driveTrigger;

  private final DriveCommand axisCommand;

  public RobotContainer(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    joy = driveTrain.joy;

    driveCommand = new DriveCommand(driveTrain, joy);
    configureBindings();
  }

  public void configureBindings() {
    driveTrain.setDefaultCommand(driveCommand);
    
    driveTrigger = new Trigger(() -> joy.getRawAxis(0) != 0)
    .or(() -> joy.getRawAxis(1) != 0)
    .or(() -> joy.getRawAxis(4) != 0)
    .or(() -> joy.getRawAxis(5) != 0)
    .or(() -> joy.getPOV() != -1);

    driveTrigger.onTrue(driveCommand);
  }
}

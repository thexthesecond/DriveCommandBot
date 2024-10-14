package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class POVCommand extends Command {
    DriveTrain driveTrain;
    Joystick joy;
    
    public POVCommand(DriveTrain driveTrain, Joystick joy) {
        this.driveTrain = driveTrain;
        this.joy = joy;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        driveTrain.POV();
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.Lspeed = driveTrain.Rspeed = 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}

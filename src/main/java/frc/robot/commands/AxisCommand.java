package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class AxisCommand extends Command {
    DriveTrain driveTrain;
    Joystick joy;
    
    public AxisCommand(DriveTrain driveTrain, Joystick joy) {
        this.driveTrain = driveTrain;
        this.joy = joy;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if ((driveTrain.Px != 0 || driveTrain.Py != 0) && (driveTrain.Px2 == 0 && driveTrain.Py2 == 0)) {
            driveTrain.AxiSpeeds(driveTrain.Px, driveTrain.Py);
        } else {
            driveTrain.AxiSpeeds(driveTrain.Px2, driveTrain.Py2);
        }
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

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
        int PovAngle = driveTrain.pov;

        switch (PovAngle) {
            case 0:
                driveTrain.Lspeed = 1;
                driveTrain.Rspeed = 1;
                break;
            case 45:
                driveTrain.Lspeed = 1;
                driveTrain.Rspeed = 0;
                break;
            case 90:
                driveTrain.Lspeed = 1;
                driveTrain.Rspeed = -1;
                break;
            case 135:
                driveTrain.Lspeed = -1;
                driveTrain.Rspeed = 0;
                break;
            case 180:
                driveTrain.Lspeed = -1;
                driveTrain.Rspeed = -1;
                break;
            case 225:
                driveTrain.Lspeed = 0;
                driveTrain.Rspeed = -1;
                break;
            case 270:
                driveTrain.Lspeed = -1;
                driveTrain.Rspeed = 1;
                break;
            case 315:
                driveTrain.Lspeed = 0;
                driveTrain.Rspeed = 1;
                break;
            default:
                driveTrain.Lspeed = 0;
                driveTrain.Rspeed = 0;
                break;
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

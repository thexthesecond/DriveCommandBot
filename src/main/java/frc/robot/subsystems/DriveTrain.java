package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{
    
    public Joystick joy = new Joystick(Constants.JoyPort);

    public final VictorSPX l_motorF = new VictorSPX(Constants.lmotor_port1);
    public final VictorSPX l_motorB = new VictorSPX(Constants.lmotor_port2);
    public final VictorSPX r_motorF = new VictorSPX(Constants.rmotor_port1);
    public final VictorSPX r_motorB = new VictorSPX(Constants.rmotor_port2);
    
    public double Lspeed, Rspeed, Px, Py, Px2, Py2, rad, diff, mag;
    public double TriggerVel;
    public double velocity = 0.5;
    public boolean a,b,x;
    public int quad, pov;

    public DriveTrain() {
        l_motorB.follow(l_motorF);
        r_motorF.setInverted(true);
        r_motorB.follow(l_motorF);
        r_motorB.setInverted(InvertType.FollowMaster);
    }

    public int QuadDetector(double x, double y) {
        if (x > 0 && y > 0) return 1;
        if (x < 0 && y > 0) return 2;
        if (x < 0 && y < 0) return 3;
        if (x > 0 && y < 0) return 4;
        else return 0;
    }

    public double Deadzone(double val) {
        if (Math.abs(val) < 0.04) {return 0;}
        else {return val;}
      }

    public double Difference(double rad) {
        return Math.pow(Math.sin(rad), 2);
    }

    public void AxiSpeeds(double Px, double Py) {
        rad = Math.atan2(Py, Px);
        diff = Difference(rad); 
        mag = Math.hypot(Px, Py);
        quad = QuadDetector(Px, Py);
        
        switch (quad) {
            case 1: Lspeed = mag; Rspeed = diff; break;
            case 2: Rspeed = mag; Lspeed = diff; break;
            case 3: Rspeed = -mag; Lspeed = -diff; break;
            case 4: Lspeed = -mag; Rspeed = -diff; break;
            default: Lspeed =Rspeed = 0; break;
        }

        if (Px != 0 && Py == 0) {
            Rspeed = -(mag * Math.cos(rad));
            Lspeed = mag * Math.cos(rad);
        }

        if (Py != 0 && Px == 0) {
            Lspeed = Rspeed = mag * Math.sin(rad);
        }

        if (TriggerVel != 0 && (Px != 0 || Py != 0)) {Lspeed *= TriggerVel; Rspeed *= TriggerVel;}
        if (TriggerVel != 0 && (Px == 0 || Py == 0)) {Lspeed = Rspeed += TriggerVel;}

        Lspeed = Math.max(-1 , Math.min(1, Lspeed)) * velocity;
        Rspeed = Math.max(-1 , Math.min(1, Rspeed)) * velocity;
    }

    public void setMotors(double Lspeed, double Rspeed) {
        l_motorF.set(ControlMode.PercentOutput, Lspeed);
        r_motorF.set(ControlMode.PercentOutput, Rspeed);
    }

    public double SpeedMode() {
        if (a) return 0.5;
        if (b) return 0.25;
        if (x) return 1;
        else return velocity;
    }

    public void POV() {
        switch (pov) {
            case 0: Lspeed = 1; Rspeed = 1; break;
            case 45: Lspeed = 1; Rspeed = 0; break;
            case 90: Lspeed = 1; Rspeed = -1; break;
            case 135: Lspeed = -1; Rspeed = 0; break;
            case 180: Lspeed = -1; Rspeed = -1; break;
            case 225: Lspeed = 0; Rspeed = -1; break;
            case 270: Lspeed = -1; Rspeed = 1; break;
            case 315: Lspeed = 0; Rspeed = 1; break;
            default: Lspeed = 0; Rspeed = 0; break;
        }
    }

    @Override
    public void periodic() {
        a = joy.getRawButton(1);
        b = joy.getRawButton(2);
        x = joy.getRawButton(3);

        velocity = SpeedMode();
        pov = joy.getPOV();

        Px = Deadzone(joy.getRawAxis(0));
        Py = Deadzone(-joy.getRawAxis(1));
        Px2 = Deadzone(-joy.getRawAxis(4));
        Py2 = Deadzone(joy.getRawAxis(5));

        TriggerVel = joy.getRawAxis(3) - joy.getRawAxis(2);

        setMotors(Lspeed, Rspeed);
    }
}

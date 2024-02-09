// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
public class ShooterandSourceIntake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private final CANSparkMax motorController1;
  private final CANSparkMax motorController2;
  //private final CANSparkMax motorcontroller3;
  private final CommandXboxController xboxController;


  public ShooterandSourceIntake(CANSparkMax motorController1, CANSparkMax motorController2) {
    this.motorController1 = motorController1;
    this.motorController2 = motorController2;
    // Invert the second motor (motorController2)
    motorController2.setInverted(true);
     xboxController = new CommandXboxController(1);
}
   // Add the following methods to control motor power based on button inputs
    public Command spinMotorOutAtSpeaker() {
        motorController1.set(1.0);
        motorController2.set(1.0);
        return runOnce(
            () -> {
              /* one-time action goes here */
            });
    }

    public Command spinMotorOutAtAmp() {
        motorController1.set(0.3);//set the value between 0 and 1 for Amp Motor Power
        motorController2.set(0.3);//set the value between 0 and 1 for Amp Motor Power
    
        return runOnce(
            () -> {
              /* one-time action goes here */
            });}

    public Command spinMotorInFromSource() {
        motorController1.set(-0.3);
        motorController2.set(-0.3);
    
        return runOnce(
            () -> {
              /* one-time action goes here */
            });}

    public Command stopMotors() {
        motorController1.set(0.0);
        motorController2.set(0.0);
    
        return runOnce(
            () -> {
              /* one-time action goes here */
            });}

    // Command to spin motors in at 30% power
    public static class SpinMotorInFromSourceCommand extends Command{
        private final ShooterandSourceIntake subsystem;

        public SpinMotorInFromSourceCommand(ShooterandSourceIntake subsystem) {
            this.subsystem = subsystem;
            addRequirements(subsystem);
        }

        @Override
        public void execute() {
            subsystem.spinMotorInFromSource();
        }

        @Override
        public void end(boolean interrupted) {
            subsystem.stopMotors();
        }
    }

    // Command to spin motors out at 30% power
    public static class SpinMotorOutToAmpCommand extends Command {
        private final ShooterandSourceIntake subsystem;

        public SpinMotorOutToAmpCommand(ShooterandSourceIntake subsystem) {
            this.subsystem = subsystem;
            addRequirements(subsystem);
        }

        @Override
        public void execute() {
            subsystem.spinMotorOutAtAmp();
        }

        @Override
        public void end(boolean interrupted) {
            subsystem.stopMotors();
        }
    }
    // Command to spin motors out at 100% power
    public static class SpinMotorOutToSpeakerCommand extends Command {
        private final ShooterandSourceIntake subsystem;

        public SpinMotorOutToSpeakerCommand(ShooterandSourceIntake subsystem) {
            this.subsystem = subsystem;
            addRequirements(subsystem);
        }

        @Override
        public void execute() {
            subsystem.spinMotorOutAtSpeaker();
        }

        @Override
        public void end(boolean interrupted) {
            subsystem.stopMotors();
        }
    }
       





    /* boolean returns for the Condition of buttons, sensors, etc. */

    public boolean buttonACondition() {
        // Replace this with your actual condition based on button press
        return xboxController.button(1) != null;
    }

    public boolean buttonXCondition() {
        // Replace this with your actual condition based on button press
        return xboxController.button(3) != null;
    }
    
    public boolean buttonBCondition() {
        // Replace this with your actual condition based on button press
        return xboxController.button(2) != null;
    }
    
    public boolean rightTriggerCondition() {
        // Replace this with your actual condition based on trigger press
        return xboxController.getRightTriggerAxis() > 0.1;
    }
    

    // ... other commands can be added for different actions
    // Chat GPT 3.5 prompted for coding assistance with some frustration and some learning like my brain each year when code gets updated and I can't get it just right.  Chat GPT is stuck at 2022 and could not get it just right.  It was a team effort.

}
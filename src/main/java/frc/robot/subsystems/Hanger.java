// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

//***************************** Subsystem class and it's main method ***************************************/

public class Hanger extends SubsystemBase {
   private final TalonSRX motorcontroller21;
    private final CommandXboxController xboxController; //Instantiate your video game controller.

    /** Creates a new FloorIntakeRoller. */
  public Hanger() {
        motorcontroller21 = new TalonSRX(21);
        xboxController = new CommandXboxController(1);  //set your usb port for the video game controller.
  
      }
  
      //***************************** Other methods in subsystem ***************************************/

      public Command HangerUp() {
        return new RunCommand(() -> {
          motorcontroller21.set(TalonSRXControlMode.PercentOutput, 0.3); //include only this motor for a single motor. Set the value between 0 and 1.  Examples: 0.3 = 30%; -0.4 = 40% reverse.
      }, this);
    }

      public Command HangerDown() {  //method to turn motor(s) out; 0.3 = 30% forwards.  Copy this method to make different powers or directions.
        return new RunCommand(() -> {
          motorcontroller21.set(TalonSRXControlMode.PercentOutput, -0.3); //include only this motor for a single motor. Set the value between 0 and 1.  Examples: 0.3 = 30%; -0.4 = 40% reverse.  
        }, this);
      }
      
      
      public Command StopHanger() {  // ALWAYS include a stopMotors command; 0.0 = 0% stop.
        return new RunCommand(() -> {
          motorcontroller21.set(TalonSRXControlMode.PercentOutput,0.0);
        }, this);
      }
    
//***************************** Button Returns for the RobotContaner File ***************************************/

        public boolean ButtonLeftStickClickCondition() { //rename button or take out
          return xboxController.button(9) != null;
        }
        public boolean ButtonRightStickClickCondition() { //rename button or take out
          return xboxController.button(10) != null;
        }
    
    
//***************************** Static Command Classes used within the subsystem ***************************************/

public static class HangerUp extends RunCommand { /*This is a command class within the original class.  
  You will need to change the class name.  Its not how we ordinarily code but chat GPT did it and it works so we will keep it. */
  private final Hanger subsystem;

  public HangerUp (Hanger subsystem) { //rename the method name custom to your subsystem.  Two public methods cannot be named the same.
    super(subsystem::HangerUp, subsystem); //rename the method name custom to your subsystem.
    this.subsystem = subsystem;  //leave this.
    addRequirements(subsystem);  //leave this.
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.StopHanger(); //Make sure to include the .stopMotors() in the end method.
  }
}

public static class HangerDown extends RunCommand { /*This is a command class within the original class.  
  You will need to change the class name.  Its not how we ordinarily code but chat GPT did it and it works so we will keep it. */
  private final Hanger subsystem;

  public HangerDown(Hanger subsystem) { //rename the method name custom to your subsystem.  Two public methods cannot be named the same.
    super(() -> subsystem.HangerDown(), subsystem); //rename the method name custom to your subsystem.
    this.subsystem = subsystem;  //leave this.
    addRequirements(subsystem);  //leave this.
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.StopHanger(); //Make sure to include the .stopMotors() in the end method.
  }
}


  public void setSpinningIn(boolean spinningIn) {
  }
  
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
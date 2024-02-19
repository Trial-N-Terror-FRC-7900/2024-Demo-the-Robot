package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private static final int IntakeMotor1CanID = 9;
  //private static final int kCanID = 9;
  //private static final MotorType kMotorType = MotorType.kBrushless;

  private CANSparkMax m_intakeMotor1;
  public IntakeSubsystem() {

    m_intakeMotor1 = new CANSparkMax(IntakeMotor1CanID, CANSparkLowLevel.MotorType.kBrushless);
    m_intakeMotor1.restoreFactoryDefaults();
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command intakeOnCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem. 
    m_intakeMotor1.set(-0.25);
    return this.run(() -> m_intakeMotor1.set(-0.25));
  }

  public Command intakeOffCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    m_intakeMotor1.set(0);
    return this.run(() -> m_intakeMotor1.set(0));
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
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
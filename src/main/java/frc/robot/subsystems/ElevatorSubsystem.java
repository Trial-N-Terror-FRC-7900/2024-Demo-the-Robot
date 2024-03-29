package frc.robot.subsystems;

//import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private CANSparkMax m_elevatorMotor1;
  private CANSparkMax m_elevatorMotor2;
  private SparkPIDController m_pidController1;
  private SparkPIDController m_pidController2;

  public ElevatorSubsystem() {

    m_elevatorMotor1 = new CANSparkMax(ElevatorConstants.ElevatorMotor1CanID, CANSparkLowLevel.MotorType.kBrushless);
    m_elevatorMotor1.restoreFactoryDefaults();

    m_elevatorMotor2 = new CANSparkMax(ElevatorConstants.ElevatorMotor2CanID, CANSparkLowLevel.MotorType.kBrushless);
    m_elevatorMotor2.restoreFactoryDefaults();

  /**
   * In order to use PID functionality for a controller, a SparkPIDController object
   * is constructed by calling the getPIDController() method on an existing
   * CANSparkMax object
   */
  m_pidController1 = m_elevatorMotor1.getPIDController();
  m_pidController2 = m_elevatorMotor1.getPIDController();

  // set PID coefficients
  m_pidController1.setP(ElevatorConstants.kP);
  m_pidController1.setI(ElevatorConstants.kI);
  m_pidController1.setD(ElevatorConstants.kD);
  m_pidController1.setIZone(ElevatorConstants.kIz);
  m_pidController1.setFF(ElevatorConstants.kFF);
  m_pidController1.setOutputRange(ElevatorConstants.kMinOutput, ElevatorConstants.kMaxOutput);
  
  m_pidController2.setP(ElevatorConstants.kP);
  m_pidController2.setI(ElevatorConstants.kI);
  m_pidController2.setD(ElevatorConstants.kD);
  m_pidController2.setIZone(ElevatorConstants.kIz);
  m_pidController2.setFF(ElevatorConstants.kFF);
  m_pidController2.setOutputRange(ElevatorConstants.kMinOutput, ElevatorConstants.kMaxOutput);

  m_elevatorMotor1.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, ElevatorConstants.elevatorForwardLimit);
  m_elevatorMotor1.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, ElevatorConstants.elevatorReverseLimit);

  m_elevatorMotor1.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
  m_elevatorMotor1.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

  m_elevatorMotor2.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, ElevatorConstants.elevatorForwardLimit);
  m_elevatorMotor2.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, ElevatorConstants.elevatorReverseLimit);

  m_elevatorMotor2.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
  m_elevatorMotor2.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

  }
  
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command elevatorMotorMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

public Command elevatorUp() {
    return this.run(() -> {
      m_pidController1.setReference(ElevatorConstants.elevatorup, CANSparkMax.ControlType.kPosition);
      m_pidController2.setReference(ElevatorConstants.elevatorup, CANSparkMax.ControlType.kPosition);
    });

}

public Command elevatorDown() {
    return this.run(() -> {
      m_pidController1.setReference(ElevatorConstants.elevatordown, CANSparkMax.ControlType.kPosition);
      m_pidController2.setReference(ElevatorConstants.elevatordown, CANSparkMax.ControlType.kPosition);
    });
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
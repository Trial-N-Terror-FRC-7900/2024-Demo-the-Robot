package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private CANSparkMax m_elevatorMotor1;
  private CANSparkMax m_elevatorMotor2;
  private SparkPIDController m_pidController;
  private AbsoluteEncoder m_encoder;

  public ElevatorSubsystem() {

    m_elevatorMotor1 = new CANSparkMax(ElevatorConstants.ElevatorMotor1CanID, CANSparkLowLevel.MotorType.kBrushless);
    m_elevatorMotor1.restoreFactoryDefaults();



    m_elevatorMotor2 = new CANSparkMax(ElevatorConstants.ElevatorMotor2CanID, CANSparkLowLevel.MotorType.kBrushless);
    m_elevatorMotor2.restoreFactoryDefaults();

  m_elevatorMotor1.restoreFactoryDefaults();

  /**
   * In order to use PID functionality for a controller, a SparkPIDController object
   * is constructed by calling the getPIDController() method on an existing
   * CANSparkMax object
   */
  m_pidController = m_elevatorMotor1.getPIDController();

  // Encoder object created to display position values
  m_encoder = m_elevatorMotor1.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);

  // set PID coefficients
  m_pidController.setP(ElevatorConstants.kP);
  m_pidController.setI(ElevatorConstants.kI);
  m_pidController.setD(ElevatorConstants.kD);
  m_pidController.setIZone(ElevatorConstants.kIz);
  m_pidController.setFF(ElevatorConstants.kFF);
  m_pidController.setOutputRange(ElevatorConstants.kMinOutput, ElevatorConstants.kMaxOutput);
  m_pidController.setFeedbackDevice(m_encoder);

  m_elevatorMotor1.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, ElevatorConstants.elevatorForwardLimit);
  m_elevatorMotor1.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, ElevatorConstants.elevatorReverseLimit);

  m_elevatorMotor1.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
  m_elevatorMotor1.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

  m_elevatorMotor2.follow(m_elevatorMotor1, true);
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
    return this.run(() -> m_pidController.setReference(ElevatorConstants.elevatorup, CANSparkMax.ControlType.kPosition));
}

public Command elevatorDown() {
    return this.run(() -> m_pidController.setReference(ElevatorConstants.elevatordown, CANSparkMax.ControlType.kPosition));
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
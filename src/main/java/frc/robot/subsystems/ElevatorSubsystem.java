package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkBase;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private static final int ElevatorMotor1CanID = 9;
  private static final int ElevatorMotor2CanID = 9;

  private int flip = 1;
  //private static final int kCanID = 9;
  //private static final MotorType kMotorType = MotorType.kBrushless;

  private CANSparkMax m_elevatorMotor1;
  private CANSparkMax m_elevatorMotor2;
  private CANSparkMax m_placeholder;
  private SparkPIDController m_pidController;
  private AbsoluteEncoder m_encoder;

  public ElevatorSubsystem() {

    m_elevatorMotor1 = new CANSparkMax(ElevatorMotor1CanID, CANSparkLowLevel.MotorType.kBrushless);
    m_elevatorMotor1.restoreFactoryDefaults();



    m_elevatorMotor2 = new CANSparkMax(ElevatorMotor2CanID, CANSparkLowLevel.MotorType.kBrushless);
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
  }
  

  public void placeholder(CANSparkMax motorset1, CANSparkMax motorset2, double value){
    m_placeholder.set(value);
    motorset1.set(m_placeholder.get());
    motorset2.set(m_placeholder.get()*flip);
    return;
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
    m_elevatorMotor1.set(1*flip);
    m_elevatorMotor2.set(-1*flip);
    m_elevatorMotor1.get();
    return this.run(() -> placeholder(m_elevatorMotor1, m_elevatorMotor2, 0.2));
}

public Command elevatorDown() {
    m_elevatorMotor1.set(1*flip);
    m_elevatorMotor2.set(-1*flip);
    m_elevatorMotor1.get();
    return this.run(() -> placeholder(m_elevatorMotor1, m_elevatorMotor2, 0.2));
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
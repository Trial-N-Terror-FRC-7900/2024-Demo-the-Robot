package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkBase;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  //private static final MotorType kMotorType = MotorType.kBrushless;

  private CANSparkMax m_armMotor1;
  private CANSparkMax m_armMotor2; 
  private SparkPIDController m_pidController;
  private AbsoluteEncoder m_encoder;

  public ArmSubsystem() {

    //Right from front arm motor
    m_armMotor1 = new CANSparkMax(ArmConstants.armMotor1CanID, CANSparkLowLevel.MotorType.kBrushless);
    m_armMotor1.restoreFactoryDefaults();



    //Left from front arm motor
    m_armMotor2 = new CANSparkMax(ArmConstants.armMotor2CanID, CANSparkLowLevel.MotorType.kBrushless);
    m_armMotor2.restoreFactoryDefaults();

    /**
     * From here on out, code looks exactly like running PID control with the 
     * built-in NEO encoder, but feedback will come from the alternate encoder
     */ 
    m_armMotor1.restoreFactoryDefaults();

    /**
     * In order to use PID functionality for a controller, a SparkPIDController object
     * is constructed by calling the getPIDController() method on an existing
     * CANSparkMax object
     */
    m_pidController = m_armMotor1.getPIDController();

    // Encoder object created to display position values
    m_encoder = m_armMotor1.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);

    // set PID coefficients
    m_pidController.setP(ArmConstants.kP);
    m_pidController.setI(ArmConstants.kI);
    m_pidController.setD(ArmConstants.kD);
    m_pidController.setIZone(ArmConstants.kIz);
    m_pidController.setFF(ArmConstants.kFF);
    m_pidController.setOutputRange(ArmConstants.kMinOutput, ArmConstants.kMaxOutput);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command armMotorMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }
  public Command armOff(){
    //call to specific rotation first
    m_armMotor1.set(0);
    return this.run(() -> m_armMotor1.set(0));
  }

  public Command armAmp(){
    return null;
  }

  public Command armSpeaker(){
    return null;
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
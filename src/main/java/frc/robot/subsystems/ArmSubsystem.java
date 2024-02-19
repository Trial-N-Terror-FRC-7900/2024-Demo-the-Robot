package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private static final int armMotor1CanID = 15;
  private static final int armMotor2CanID = 16;
  //private static final MotorType kMotorType = MotorType.kBrushless;

  private CANSparkMax m_armMotor1;
  private CANSparkMax m_armMotor2; 
  private SparkPIDController m_pidController;
  private AbsoluteEncoder m_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  public ArmSubsystem() {

    //Right from front arm motor
    m_armMotor1 = new CANSparkMax(armMotor1CanID, CANSparkLowLevel.MotorType.kBrushless);
    m_armMotor1.restoreFactoryDefaults();



    //Left from front arm motor
    m_armMotor2 = new CANSparkMax(armMotor2CanID, CANSparkLowLevel.MotorType.kBrushless);
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
    m_encoder = m_armMotor1.getAbsoluteEncoder()

    // PID coefficients
    kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);
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
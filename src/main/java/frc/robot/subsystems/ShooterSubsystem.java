package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel;
//import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  //private static final MotorType kMotorType = MotorType.kBrushless;

  private CANSparkMax m_shooterMotorTop;
  private CANSparkMax m_shooterMotorBottom;
  private CANSparkMax m_shooterMotorAmp;
  private SparkPIDController m_pidController;
  private AbsoluteEncoder m_encoder;

  public ShooterSubsystem() {

    //Top shooterMotor motor
    m_shooterMotorTop = new CANSparkMax(ShooterConstants.shooterMotorTopCanID, CANSparkLowLevel.MotorType.kBrushless);
    m_shooterMotorTop.restoreFactoryDefaults();



    //Bottom shooterMotor motor
    m_shooterMotorBottom = new CANSparkMax(ShooterConstants.shooterMotorBottomCanID, CANSparkLowLevel.MotorType.kBrushless);
    m_shooterMotorBottom.restoreFactoryDefaults();

    m_shooterMotorAmp = new CANSparkMax(ShooterConstants.shooterMotorAmpCanID, CANSparkLowLevel.MotorType.kBrushless);
    m_shooterMotorAmp.restoreFactoryDefaults();


    /**
     * From here on out, code looks exactly like running PID control with the 
     * built-in NEO encoder, but feedback will come from the alternate encoder
     */ 

    // PID coefficients
    m_pidController = m_shooterMotorTop.getPIDController();

    // set PID coefficients
    m_pidController.setP(ArmConstants.kP);
    m_pidController.setI(ArmConstants.kI);
    m_pidController.setD(ArmConstants.kD);
    m_pidController.setIZone(ArmConstants.kIz);
    m_pidController.setFF(ArmConstants.kFF);
    m_pidController.setOutputRange(ArmConstants.kMinOutput, ArmConstants.kMaxOutput);
    m_pidController.setFeedbackDevice(m_encoder);

    m_shooterMotorTop.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, ArmConstants.armForwardLimit);
    m_shooterMotorTop.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, ArmConstants.armReverseLimit);

    m_shooterMotorTop.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    m_shooterMotorTop.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    m_shooterMotorBottom.follow(m_shooterMotorTop, true);
    m_shooterMotorTop.follow(m_shooterMotorAmp);

    this.setDefaultCommand(shootOff());
  }

  public Command shootOn(){
    m_shooterMotorTop.set(ShooterConstants.shootSpeed);
    return this.run(() -> m_shooterMotorTop.set(ShooterConstants.shootSpeed));
  }

  public Command shootOff(){
    m_shooterMotorTop.set(0);
    //m_shooterMotorBottom.set(0);
    return this.run(() -> m_shooterMotorTop.set(0));
  }

  public Command Intake(){
    m_shooterMotorTop.set(IntakeConstants.IntakeSpeed);
    return this.run(() -> m_shooterMotorTop.set(IntakeConstants.IntakeSpeed));
  }

  public Command AmpShotOn(){
    return this.run(()-> m_shooterMotorAmp.set(ShooterConstants.AmpShotSpeed));
  }
  public Command AmpShotOff(){
    return this.run(()-> m_shooterMotorAmp.set(0));
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
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private static final int shooterMotorTopCanID = 12;
  private static final int shooterMotorBottomCanID = 13;
  private static final int shooterMotorAmpCanID = 14;
  private static final int placeholderCanID = 200;

  private int flip = 1;
  //private static final MotorType kMotorType = MotorType.kBrushless;

  private CANSparkMax m_shooterMotorTop;
  private CANSparkMax m_shooterMotorBottom;
  private CANSparkMax m_shooterMotorholder;
  private CANSparkMax m_placeholder;

  public ShooterSubsystem() {

    //Top shooterMotor motor
    m_shooterMotorTop = new CANSparkMax(shooterMotorTopCanID, CANSparkLowLevel.MotorType.kBrushless);
    m_shooterMotorTop.restoreFactoryDefaults();



    //Bottom shooterMotor motor
    m_shooterMotorBottom = new CANSparkMax(shooterMotorBottomCanID, CANSparkLowLevel.MotorType.kBrushless);
    m_shooterMotorBottom.restoreFactoryDefaults();



    m_shooterMotorholder = new CANSparkMax(shooterMotorAmpCanID, CANSparkLowLevel.MotorType.kBrushless);
    m_shooterMotorholder.restoreFactoryDefaults();

    m_placeholder= new CANSparkMax(placeholderCanID, CANSparkLowLevel.MotorType.kBrushless);
    m_placeholder.restoreFactoryDefaults();


    /**
     * From here on out, code looks exactly like running PID control with the 
     * built-in NEO encoder, but feedback will come from the alternate encoder
     */ 

    // PID coefficients
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
  public Command armMotorMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  public Command shootOn(){
    m_shooterMotorBottom.set(1*flip);
    m_shooterMotorTop.set(-1*flip);
    m_shooterMotorBottom.get();
    return this.run(() -> placeholder(m_shooterMotorBottom, m_shooterMotorTop, 1));
  }

  public Command shootOff(){
    m_shooterMotorBottom.set(0);
    m_shooterMotorTop.set(0);
    return this.run(() -> placeholder(m_shooterMotorBottom, m_shooterMotorTop,0));
  }

  public Command Intake(){
    m_shooterMotorBottom.set(0.2);
    m_shooterMotorTop.set(-0.2);
    return this.run(() -> placeholder(m_shooterMotorBottom, m_shooterMotorTop, 0.25));
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
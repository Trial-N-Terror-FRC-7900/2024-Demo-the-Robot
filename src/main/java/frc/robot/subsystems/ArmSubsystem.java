package frc.robot.subsystems;

import java.util.List;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  //private static final MotorType kMotorType = MotorType.kBrushless;
  //private int flip = -1;

  private CANSparkMax m_armMotor1;
  private CANSparkMax m_armMotor2; 
  private SparkPIDController m_pidController;
  
  //private AbsoluteEncoder m_encoder;
  //private CANSparkMax m_placeholder;
  private CANSparkMax m_leadMotor;
  private CANSparkMax m_followMotor;

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
    //m_encoder = m_armMotor1.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);

    // set PID coefficients
    m_pidController.setP(ArmConstants.kP);
    m_pidController.setI(ArmConstants.kI);
    m_pidController.setD(ArmConstants.kD);
    m_pidController.setIZone(ArmConstants.kIz);
    m_pidController.setFF(ArmConstants.kFF);
    m_pidController.setOutputRange(ArmConstants.kMinOutput, ArmConstants.kMaxOutput);

    m_leadMotor = new CANSparkMax(m_armMotor1.getDeviceId(), MotorType.kBrushless);
    m_followMotor = new CANSparkMax(m_armMotor2.getDeviceId(), MotorType.kBrushless);
    m_leadMotor.restoreFactoryDefaults();
    m_followMotor.restoreFactoryDefaults();
    m_followMotor.follow(m_leadMotor);
  }

  /*
  public void placeholder(CANSparkMax motorset1, CANSparkMax motorset2, double value){
    m_placeholder.set(value);
    motorset1.set(m_placeholder.get());
    motorset2.set(m_placeholder.get()*flip);
    return;
  }
  */

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

  public Command armOn(){
    //m_leadMotor.set(0.1);
    return this.run(() -> m_leadMotor.set(0.1));
  }
  public Command armOff(){
    //call to specific rotation first
    //m_armMotor1.set(0);
    return this.run(() -> m_leadMotor.set(0));
  }

  public Command armUp() {
    //m_armMotor1.set(1*flip);
    //m_armMotor2.set(-1*flip);
    //m_armMotor1.get();
    return this.run(() -> m_leadMotor.set(0.1));
}

/*
  public Command armAmp(){
    return null;
  }

  public Command armSpeaker(){
    return null;
  }

  //public List<List> i = [];
  public Command clickUp(){
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